package MagicFonts;

import java.awt.Component;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Клас за преглед на избраният шрифт
 * в таблица
 * @author ipopov
 */
public class ShowCustomFont extends JTable {
    DefaultTableModel model = new DefaultTableModel();
    SimpleMessage sm = new SimpleMessage();

    private final static byte columnsCount = 15;
    private final static byte columnsWidth = 30;
    private final static byte rowsHeight = 30;
    private final static byte rowsCount = 10;

    private static int currentPos = 0;

    String[] emptyData = null;

    public ShowCustomFont() {
        super();
        
        this.setModel(model);
        model.setColumnCount(columnsCount);
        this.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.setRowHeight(rowsHeight);

        formatColumns();
        
        this.setPreferredScrollableViewportSize(this.getPreferredSize());
        this.getTableHeader().setVisible(false);

        this.setInputVerifier(null);

        //инициализиране
        emptyData = new String[columnsCount];
        for( int i = 0; i < emptyData.length; i++) {
            emptyData[i] = "";
        }

        for( int i = 0; i < rowsCount; i++) {
            model.insertRow(0,  emptyData);
        }
    }

    /**
     * Попълва модела на таблицата с определен символ data
     * Взема се от ShowFontTable.java
     * @param data данни за попълване на модела
     */
    public void setData(Object data) {
        model.setValueAt(data, currentPos / columnsCount, currentPos % columnsCount);
        currentPos++;
    }

    /**
     * Настройки за колоните
     */
    private void formatColumns() {
        TableCellRenderer cellRenderer = new CellRenderer();

        TableColumn col = null;
        //установяваме широчината на колоните
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Enumeration enum1 = this.getColumnModel().getColumns();
        while( enum1.hasMoreElements() ) {
            col = (TableColumn)enum1.nextElement();
            col.setPreferredWidth(columnsWidth);
            col.setCellRenderer( cellRenderer );
        }
    }
    
    /**
     * Попълва модела на таблицата по зададен стринг запазен в xml.
     * За четене от проектният файл, попълва и edit полето за представяне на
     * шрифта в текстови вид.
     * @param data данни от проекта
     */
    public void setStringData(String data) {
        this.clearAll();
        
        String str = data.replaceAll("\\[", "");
        str = str.replaceAll("\\]", "");
        String[] arrData = str.split(",");
        int counter = 0;

        for( int i = 0; i < columnsCount; i++ ) {
            for( int j = 0; j < rowsCount; j++ ) {
                if( arrData[counter].trim().length() > 0 ) {
                    String cellData = String.valueOf( (char) ( Integer.parseInt(arrData[counter].trim()) ) );
                    this.setData( cellData );
                    
                    MainFrame.textFont.setText(MainFrame.textFont.getText() + cellData);
                    counter++;
                }
            }
        }
    }

    public Vector getData() {
        return model.getDataVector();
    }

    /**
     * Връща въведените символи като вектор от числа. За коректно записване в xml.
     * @return въведените символи като вектор от числа
     */
    public Vector getNumberData() {
        DefaultTableModel result = new DefaultTableModel();
        result.setColumnCount(columnsCount);

        for( int i = 0; i < rowsCount; i++) {
            result.insertRow(0,  emptyData);
        }

        Object tmp = null;

        for( int i = 0; i < rowsCount; i++ ) {
            for( int j = 0; j < columnsCount; j++ ) {
                tmp = model.getValueAt( i, j );

                if( tmp.toString().length() > 0 ) {
                    result.setValueAt((int)tmp.toString().charAt(0), i, j);
                }
            }
        }

        return result.getDataVector();
    }
    
    /**
     * Изтриване на данните от цялата таблица
     */
    private void clearAll() {
        for( int i = 0; i < columnsCount; i++ ) {
            for( int j = 0; j < rowsCount; j++ ) {
                model.setValueAt("", j, i);
            }
        }
        currentPos = 0;
    }

    public void clearAllButton() {
        sm.setMessageType("quest");
        sm.setNumberButtons(2);
        sm.setStrButton1(MainFrame.langResources.getString("message_button_ok"));
        sm.setStrButton2(MainFrame.langResources.getString("message_button_cancel"));
        sm.setBaseMessage(MainFrame.langResources.getString("sure_to_delete_all_message"));
        sm.setCaption(MainFrame.langResources.getString("info"));
        if (sm.Message()) {
            this.clearAll();
        }
    }

    /**
     * При изтриване на ред
     */
    public void clearSelectedRows() {

        int end = this.getSelectedRows().length;
        if(end > 0) {
            sm.setMessageType("quest");
            sm.setNumberButtons(2);
            sm.setStrButton1(MainFrame.langResources.getString("message_button_ok"));
            sm.setStrButton2(MainFrame.langResources.getString("message_button_cancel"));
            sm.setBaseMessage(MainFrame.langResources.getString("sure_to_delete_row_message"));
            sm.setCaption(MainFrame.langResources.getString("info"));
            if (sm.Message()) {
                int begin = this.getSelectedRows()[0];

                for( int i = 0; i < columnsCount; i++ ) {
                    for( int j = begin; j < begin + end; j++ ) {
                        model.setValueAt("", j, i);
                    }
                }
                this.packData();
            }
        } else { //ако не сме избрали ред за изтриване
            sm.setMessageType("info");
            sm.setNumberButtons(1);
            sm.setStrButton1(MainFrame.langResources.getString("message_button_ok"));
            sm.setBaseMessage(MainFrame.langResources.getString("select_to_delete_row_message"));
            sm.setCaption(MainFrame.langResources.getString("info"));
            sm.Message();
        }
    }

    /**
     * При изтриване на символ от клетка
     */
    public void clearSelectedCell() {
        int c = this.getSelectedColumn();
        int r = this.getSelectedRow();

        if (c > -1 && r > -1) {
            sm.setMessageType("quest");
            sm.setNumberButtons(2);
            sm.setStrButton1(MainFrame.langResources.getString("message_button_ok"));
            sm.setStrButton2(MainFrame.langResources.getString("message_button_cancel"));
            sm.setBaseMessage(MainFrame.langResources.getString("sure_to_delete_cell_message"));
            sm.setCaption(MainFrame.langResources.getString("info"));
            if (sm.Message()) {
                model.setValueAt("", r, c);
                this.packData();
            }
        } else {
            sm.setMessageType("info");
            sm.setNumberButtons(1);
            sm.setStrButton1(MainFrame.langResources.getString("message_button_ok"));
            sm.setBaseMessage(MainFrame.langResources.getString("select_to_delete_cell_message"));

            sm.setCaption(MainFrame.langResources.getString("info"));
            sm.Message();
        }
    }

    /**
     * "Пакетира" символите след изтриване на редове и
     * символи лежащи в клетки между други символи
     */
    private void packData() {
        DefaultTableModel result = new DefaultTableModel();
        result.setColumnCount(columnsCount);

        for( int i = 0; i < rowsCount; i++) {
            result.insertRow(0,  emptyData);
        }
        
        Object tmp = null;
        int pos = 0;

        for( int i = 0; i < rowsCount; i++ ) {
            for( int j = 0; j < columnsCount; j++ ) {
                tmp = model.getValueAt( i, j );
                if( tmp.toString().length() > 0 ) {
                    result.setValueAt(tmp.toString().charAt(0), pos / columnsCount, pos % columnsCount);
                    pos++;
                }
            }
        }
        currentPos = pos;
        model = result;

        this.setModel(result);
        formatColumns();
    }
    
    /**
     * Така клетките на таблицата не могат да се редактират
     * @param rowIndex
     * @param vColIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int vColIndex) {
        return false;
    }


    /**
     * центриране на символите в клетките на таблицата
     */
    class CellRenderer extends DefaultTableCellRenderer
    {
        public CellRenderer() {
            setHorizontalAlignment( CENTER );
        }

        @Override
        public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
        {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            return this;
        }
    }
}
