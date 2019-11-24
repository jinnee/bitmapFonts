package MagicFonts;

import java.awt.Component;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 *
 * @author ipopov
 */
class ClipboardOperations implements  ActionListener {
    //компонента от която ще се копира

    private Component comp = null;
    //определя
    private String action = "";
    // Поведение при копиране
    // при copyOnNull = true - тогава ако селектираният стринг е null се копира целият текст
    // при copyOnNull = false - не се копира
    private boolean copyOnNull = true;
    private Clipboard clipbd = Toolkit.getDefaultToolkit().getSystemClipboard();
    private StringSelection clipString = null;

    public ClipboardOperations() {
        clipbd.addFlavorListener(new FlavorListener() {
            public void flavorsChanged(FlavorEvent e) {
                Clipboard localClipbd =  new Clipboard("local");
                localClipbd = clipbd;
                StringSelection clipString = null;

                try {
                    //четене от системния clipboard
                    //System.out.println(clipbd);
                    Transferable content = clipbd.getContents(null);
                    String dstData = (String) content.getTransferData(DataFlavor.stringFlavor );
                    clipString = new StringSelection( dstData );
                    localClipbd.setContents(clipString, clipString);
                    
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(ClipboardOperations.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ClipboardOperations.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalStateException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void setComponentAction(Component comp, String action) {
        this.comp = comp;
        this.action = action;
    }

    public void actionPerformed(ActionEvent e) {
        /*реализиране за JTextPane*/
        if (this.comp instanceof JTextPane) {
            if (this.action.equals(Constants.CLIPBOARD_ACTIONS_COPY)) {//реализиране на copy за JTextPane
                String selection = ((JTextPane) this.comp).getSelectedText();
                //ако нищо не е избрано ще се копира цялото съдържание (и имаме съответният флаг)
                //подобно поведение е добро, тъй като реализираме ограничена функционалност
                if (selection == null && copyOnNull == false) {
                    return;
                } else if (selection == null && copyOnNull == true) {
                    //това се прави защото getText() връща текста заедно с html таговете
                    ((JTextPane) this.comp).selectAll();
                    selection = ((JTextPane) this.comp).getSelectedText();
                    //запис в system clipboard
                    clipString = new StringSelection(selection);
                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipString, clipString);
                }
                clipString = new StringSelection(selection);
                clipbd.setContents(clipString, clipString);

            }
        } else if (this.comp instanceof JTextArea) { /*реализиране за JTextArea*/
            if (this.action.equals(Constants.CLIPBOARD_ACTIONS_COPY)) { //реализиране на copy за JTextArea
                String selection = ((JTextArea) this.comp).getSelectedText();
                if (selection == null && copyOnNull == false) {
                    return;
                } else if (selection == null && copyOnNull == true) {
                    selection = ((JTextArea) this.comp).getText();
                }
                 clipString = new StringSelection(selection);
                clipbd.setContents(clipString, clipString);
            } else if (this.action.equals(Constants.CLIPBOARD_ACTIONS_PASTE)) { //реализиране на paste за JTextArea
                Transferable clipData = clipbd.getContents(this);
                try {
                    String localClipString = (String) clipData.getTransferData(DataFlavor.stringFlavor);
                    ((JTextArea) this.comp).replaceRange(localClipString, ((JTextArea) this.comp).getSelectionStart(), ((JTextArea) this.comp).getSelectionEnd());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (this.action.equals(Constants.CLIPBOARD_ACTIONS_CUT)) { //реализиране на cut за JTextArea
                String selection = ((JTextArea) this.comp).getSelectedText();
                if (selection == null) {
                    return;
                }
                clipString = new StringSelection(selection);
                clipbd.setContents(clipString, clipString);
                ((JTextArea) this.comp).replaceRange("", ((JTextArea) this.comp).getSelectionStart(), ((JTextArea) this.comp).getSelectionEnd());
            }
        }  else if (this.comp instanceof JTextField) { /*реализиране за JTextField*/
            if (this.action.equals(Constants.CLIPBOARD_ACTIONS_COPY)) { //реализиране на copy за JTextArea
                String selection = "";

                ((JTextField) this.comp).selectAll();
                selection = ((JTextField) this.comp).getSelectedText();

                //запис в system clipboard
                clipString = new StringSelection(selection);
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(clipString, clipString);
                clipString = new StringSelection(selection);
                clipbd.setContents(clipString, clipString);

            }
        }
    }

    public void setCopyOnNull(boolean con) {
        copyOnNull = con;
    }

    public boolean getCopyOnNull() {
        return this.copyOnNull;
    }

}
