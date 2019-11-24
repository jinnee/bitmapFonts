package MagicFonts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Клас за преглед на избраният шрифт
 *
 * @author ipopov
 */
public class ShowImageFont extends JPanel {

    //брой колони
    private int alphabetSize = 33;
    //скалиране на грида - размер на клетката на основата
    private int side = 22;
    //големина на шрифта
    private int fontSize = 22;
    Color fontColor = Color.BLACK;

    private String customFont = "";
    private String physicalFont = "";
    private String logicalFont = "";

    private int imageWidth;

    private Font font = null;
    //всички избрани символи
    private static Vector data = null;

    public ShowImageFont() {
        super();
        setLayout(new BorderLayout());
    }

    public void setCustomFont(String customFont) {
        this.customFont = customFont;
    }

    public void setAlphabetSize(int alphabetSize) {
        this.alphabetSize = alphabetSize;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public void setPhysicalFont(String physicalFont) {
        this.physicalFont = physicalFont;
    }

    public void setLogicalFont(String logicalFont) {
        this.logicalFont = logicalFont;
    }

    public void setData(Vector data) {
        ShowImageFont.data = data;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
        this.repaint();
    }

    public String getCurrentFont() {
        return font.getFontName();
    }

    public void setCurrentFont(Font font) {
        this.font = font;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    /**
     *
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2ImageFile;
        Graphics2D g2Preview;
        g2ImageFile = (Graphics2D) g;
        g2Preview = (Graphics2D) g;

        FileInputStream in = null;

        if (this.customFont.length() > 0) {
            this.physicalFont = "";
            File f = new File(this.customFont);
            try {
                in = new FileInputStream(f);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            try {
                font = Font.createFont(Font.TRUETYPE_FONT, in);
            } catch (FontFormatException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (this.physicalFont.length() > 0) {
            this.customFont = "";
            font = new Font(this.physicalFont, Font.PLAIN, fontSize);
        } else {
            font = new Font(Font.DIALOG, Font.PLAIN, fontSize);
        }

        font = font.deriveFont((float) fontSize);

        //Font Effects
        FontEffects.setFont(font);
        font = FontEffects.getFont();

        FontMetrics fm = g2Preview.getFontMetrics(font);
        g2Preview.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2Preview.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2Preview.setColor(this.fontColor);
        g2Preview.setFont(font);

        FontRenderContext frc = g2Preview.getFontRenderContext();

        //големина на картинката
        BufferedImage image = new BufferedImage(alphabetSize * side, fm.getHeight(), Transparency.BITMASK);

        g2ImageFile = (Graphics2D) image.createGraphics();
        g2ImageFile.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2ImageFile.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2ImageFile.setFont(font);
        g2ImageFile.setColor(fontColor);

        int off = 0;
        int x = 0;
        int y = fm.getHeight() - fm.getMaxDescent();
        Rectangle2D bounds = null;

        //Ако искаме формата да е с Bitmap Font Writer technique
        //и да зареждаме с fontManager.getFont(getImage("Constants.TMP_PICTURE"));
        if (MainFrame.bitmapFWTCheckBox.isSelected()) {
            BufferedImage bbbt = createBitmapFont(font, g2ImageFile);
            BufferedImage img = null;

            try {
                ImageIO.write(bbbt, "png", new File(Constants.TMP_PICTURE));
                img = ImageIO.read(new File(Constants.TMP_PICTURE));
                g2Preview.drawImage(img, 0, 0, null);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else //Ако искаме да зареждаме с fontManager.getFont(getImages(Constants.TMP_PICTURE, 33, 1), " ABCDEFGHIJKLMNOPQRSTUVWXYZ123456");
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < ((Vector) data.get(i)).size(); j++) {
                    String currentSymbol = ((Vector) data.get(i)).get(j).toString();
                    if (currentSymbol.length() > 0) {
                        bounds = g2Preview.getFont().getStringBounds(currentSymbol, frc);
                        float width1 = (float) bounds.getWidth();
                        //отместване по x - добавяне на число прави цялостно отместване
                        //((side - (int) width1) / 2) се добавя заради центрирането на символа в клетката!
                        x = off % alphabetSize * side + ((side - (int) width1) / 2);
                        //отместване по y - добавяне на число прави цялостно отместване
                        FontEffects.g2d = g2Preview;
                        FontEffects.applyEffects(currentSymbol, x, y - 2);
                        FontEffects.g2d = g2ImageFile;
                        FontEffects.applyEffects(currentSymbol, x, y - 2);

                        off++;
                    }
                }
            }

            try {
                //записваме картинката
                //записва се, дори когато нямаме промени т.е при всеки repaint. трябва да се промени !!!
                ImageIO.write(image, "png", new File(Constants.TMP_PICTURE));

                //променяме размера на показваната картинка в зависимост от големината на шрифта
                this.setSize(image.getWidth(), this.getHeight());
                imageWidth = image.getWidth();

                g2Preview.dispose();
                g2ImageFile.dispose();

            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        }
    }

    public BufferedImage createBitmapFont(Font font, Graphics2D g) {
        FontMetrics fm = g.getFontMetrics(font);
        g.dispose();

        String allLetters = "";
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < ((Vector) data.get(i)).size(); j++) {
                    String currentSymbol = ((Vector) ShowImageFont.data.get(i)).get(j).toString();
                    if (currentSymbol.length() > 0) {
                        allLetters = allLetters + currentSymbol;
                    }
                }
            }
        }

        int w = fm.stringWidth(allLetters), // image width
                h = fm.getHeight(), // and height
                len = allLetters.length(), // total letter count
                x = 0, // draw letter to <x, y>
                y = h - fm.getDescent() + 1;

        Color delim = Color.GREEN; // delimiter at <0, 0>

        // to avoid the color same like font color
        if (delim.equals(fontColor)) {
            delim = Color.YELLOW;
        }

        // draw all letters to the bitmap
        BufferedImage bitmap = new BufferedImage(w, h, Transparency.BITMASK);
        //imageWidth = w;
        g = (Graphics2D) bitmap.createGraphics();

        g = bitmap.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setFont(font);

        FontEffects.g2d = g;

        for (int i = 0; i < len; i++) {
            char c = allLetters.charAt(i);

            // draw delimiter
            g.setColor(delim);
            g.drawLine(x, 0, x, 0);

            // draw letter
            g.setColor(fontColor);
            FontEffects.applyEffects(String.valueOf(c), x, y - 2);

            x += fm.charWidth(c);
        }

        g.dispose();

        return bitmap;
    }
}
