package MagicFonts;

/**
 *
 * @author ipopov
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * клас реализиращ прости съобщения Използване
 * <code>
 * <pre>
 *       SimpleMessage sm = new SimpleMessage("quest",2);
 *       sm.setStrButton1("Изход");
 *       sm.setStrButton2("Откажи");
 *       sm.setBaseMessage("Наистина ли искате да напуснете програмата?");
 *       sm.setCaption("Въпрос");
 *       if (sm.Message()) {
 *           System.exit(0);
 *       } else {
 *       }
 * </pre>
 * </code>
 */
public class SimpleMessage {

    //Текст върху ляв бутон
    private String strButton1 = "Изход";
    //Текст върху десен бутон
    private String strButton2 = "Откажи";
    //Основно съобщение
    private String BaseMessage = "";
    //Заглавие на прозореца
    private String Caption = "";
    //тип на съобщението
    private int MessageType = -1;
    //брой бутони
    private int cbuttons;

    /**
     * конструктор type - тип на съобщението error,info,warn,quest, иначе без
     * стандартна иконка cb - брой бутони
     *
     * @param type
     * @param cb
     */
    public SimpleMessage(String type, int cb) {
        if (type.equals("error")) {
            MessageType = 0;
        } else if (type.equals("info")) {
            MessageType = 1;
        } else if (type.equals("warn")) {
            MessageType = 2;
        } else if (type.equals("quest")) {
            MessageType = 3;
        } else {
            MessageType = -1;
        }
        if (cb == 1 || cb == 2) {
            cbuttons = cb;
        } else {
            cbuttons = 2;
        }
    }

    public SimpleMessage() {
        
    }


    public void setMessageType(String type) {
        if (type.equals("error")) {
            MessageType = 0;
        } else if (type.equals("info")) {
            MessageType = 1;
        } else if (type.equals("warn")) {
            MessageType = 2;
        } else if (type.equals("quest")) {
            MessageType = 3;
        } else {
            MessageType = -1;
        }
    }

    /**
     * Връща true или false в зависимост от избрания бутон
     *
     * @return
     */
    public boolean Message() {
        Object[] options;
        if (cbuttons == 2) {
            options = new Object[]{strButton1, strButton2};
        } else if (cbuttons == 1) {
            options = new Object[]{strButton1};
        } else {
            options = new Object[]{strButton1, strButton2};
        }

        JFrame frame = new JFrame();
        int n = JOptionPane.showOptionDialog(frame,
                BaseMessage,
                Caption,
                JOptionPane.YES_NO_OPTION,
                MessageType,
                null,
                options,
                strButton1);
        if (n == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Установява текста върху левия бутон
     *
     * @param str
     */
    public void setStrButton1(String str) {
        strButton1 = str;
    }

    /**
     * Установява текста върху десния бутон
     *
     * @param str
     */
    public void setStrButton2(String str) {
        strButton2 = str;
    }

    /**
     * Установява текста върху десния бутон
     *
     * @param str
     */
    public void setCaption(String str) {
        Caption = str;
    }

    /**
     * Установява текста върху BaseMessage
     *
     * @param str
     */
    public void setBaseMessage(String str) {
        BaseMessage = str;
    }

    /**
     * Установява броят на бутоните, които ще се показват
     *
     * @param nb
     */
    public void setNumberButtons(int nb) {
        if (nb <= 2 && nb >= 1) {
            cbuttons = nb;
        } else {
            try {
                throw new SimpleMessage.SimpleMessageException("Броят на бутоните трябва да е <=2 и >=1!");
            } catch (SimpleMessage.SimpleMessageException ex) {
                Logger.getLogger("global").log(Level.SEVERE, null, ex);
            }
        }
    }

    class SimpleMessageException extends Exception {

        private String detail;

        SimpleMessageException(String a) {
            detail = a;
        }

        @Override
        public String toString() {
            return detail;
        }
    }
}
