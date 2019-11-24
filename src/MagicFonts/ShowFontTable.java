package MagicFonts;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Клас визуализиращ символите от избран шрифт
 * @author ipopov
 */
public class ShowFontTable extends JTable{
    DefaultTableModel model = new DefaultTableModel();

    private String customFont = "";
    private String physicalFont = "";
    private String logicalFont = "";

    private final static byte columnCount = 15;
    private final static byte columnsWidth = 30;
    private final static byte rowsHeight = 30;


    private final static String defaultFont = "Times";

    public ShowFontTable() {
        super();
        this.addMouseListener(new TableMouseAdapter());
        this.setModel(model);
        model.setColumnCount(columnCount);

        this.setRowHeight(rowsHeight);
        TableCellRenderer cellRenderer = new CellRenderer();

        TableColumn col = null;
        //установяваме широчината на колоните
        this.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        Enumeration enum1 = this.getColumnModel().getColumns();
        for (; enum1.hasMoreElements(); ) {
            col = (TableColumn)enum1.nextElement();
            col.setPreferredWidth(columnsWidth);
            col.setCellRenderer( cellRenderer );
        }

        this.setPreferredScrollableViewportSize(this.getPreferredSize());
        this.getTableHeader().setVisible(false);

        setData();
    }

    /**
     * Попълване на таблицата за визуализиране
     * на символите от избран шрифт
     */
    public void setData() {
        Font font = null;
        FileInputStream in = null;

        if( this.customFont.length() > 0) {
            this.physicalFont = "";
            //зареждане на потребителски шрифт
            File f = new File(this.customFont);
            try {
                in = new FileInputStream(f);
                font = Font.createFont(Font.TRUETYPE_FONT, in);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (FontFormatException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if( this.physicalFont.length() > 0 ) {
            this.customFont = "";
            font = new Font(this.physicalFont, Font.PLAIN, 18);
        } else {
            font = new Font( defaultFont, Font.PLAIN, 18 );
        }

        //за да се виждат потребителските шрифтове !!!
        Font dynamicFont = font.deriveFont(20f);
        this.setFont(dynamicFont);
        
        //инициализиране на потребителският шрифт
        MainFrame.showCustomFont.setFont(dynamicFont);

        int off = 0;

        String[] data = new String[columnCount];
        int row = 0;
        for (int i = 32; i <= font.getNumGlyphs(); i++) {
          if( ((32 <= i && i < font.getNumGlyphs())) ) {
              if (font.canDisplay((char) i)) {
                data[off % columnCount] = Character.toString((char)i);

                if(off % columnCount == columnCount - 1) {
                    model.insertRow(row,  data );
                    row++;
                }
                off++;
             }
          }
       }
    }

    public void setCustomFont(String customFont) {
        this.customFont = customFont;
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

    public void setPhysicalFont(String physicalFont) {
        this.physicalFont = physicalFont;
    }

    /**
     * Клас за обработка на събитията от мишката (click)
     * и записването им в потребителската таблица
     */
    class TableMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int r = ShowFontTable.this.getSelectedRow();
            int c = ShowFontTable.this.getSelectedColumn();

            MainFrame.textFont.setText( MainFrame.textFont.getText() + ShowFontTable.this.getValueAt( r, c ).toString() );
            
            MainFrame.showCustomFont.setData(ShowFontTable.this.getValueAt( r, c ));
            //премахва SAVED
            ProjectTools.statuses.remove(ProjectTools.projectStatus[3]);
            //поставя NOTSAVED
            ProjectTools.statuses.put(ProjectTools.projectStatus[2], ProjectTools.projectStatus[2]);
        }
    }

    public void setLogicalFont() {
        this.logicalFont = defaultFont;
    }
    
    /**
     * центриране на символите в клетките на таблицата
     */
    class CellRenderer extends DefaultTableCellRenderer {
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
