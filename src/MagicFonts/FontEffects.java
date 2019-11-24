/*
 * Клас реализиращ ефектите прилагани към шрифта
 */

package MagicFonts;

import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author ipopov
 */
public class FontEffects {
    private static Hashtable attributes = new Hashtable();
    private static AffineTransform at = new AffineTransform();
    //текущо избрани ефекти
    private static Vector effects = new Vector();
    //Всички ефекти
    public static final Map EFFECTS = new HashMap();
    public static Graphics2D g2d = null;
    //шрифт с който се работи
    private static Font font = null;
    protected static boolean isOutlineEffect = false;

    //инициализиране
    static {
        //описание на ефектите - ефекти само за дефинираните в java !!!!!!!!!!!!!!!
        //тук не влиза Outline !!!!!!!!!!!!!!!!!!!!!!!!!!!
        EFFECTS.put("BOLD", "BOLD");
        EFFECTS.put("ITALIC" , "ITALIC");
        EFFECTS.put("STRIKETHROUGH" , "STRIKETHROUGH");
    }

    private static Font setBold(Font font) {
        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        return font.deriveFont(attributes);
    }

    private static Font setItalic(Font font) {
//        at.shear(-0.35, 0);
        attributes.put(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        return font.deriveFont(attributes);
    }

    public static void addEffect(Object effect) {
        effects.add(effect);
    }
    
    public static void removeEffect(Object effect) {
        effects.remove(effect);
    }

    public static Vector getEffects() {
        return effects;
    }

    public static void setFont(Font font) {
        FontEffects.font = font;
    }

    public static Font getFont() {
        return FontEffects.font;
    }

    /**
     * Задава начални стойности на трансформацията и "нулира" атрибутите
     * на стандартните ефекти, вградени в java !!!!!
     */
    public static void clearEffects() {
        //"нулиране" на трансформацията
        at.shear(0.35, 0);
        attributes.clear();
    }

    /**
     * 
     * @param s - стринг към който се прилагат ефектите
     * @param x - x координата на разположение на символа
     * @param y - y координата на разположение на символа
     */
    public static void applyEffects(String s, Integer x, Integer y) {
        Vector eff = FontEffects.getEffects();
        AttributedString as = new AttributedString(s);
        
        for( int i = 0; i < eff.size(); i++ ) {
            if( eff.elementAt(i).equals( FontEffects.EFFECTS.get("BOLD") ) ) {
                font = setBold(font);
            }
            if( eff.elementAt(i).equals( FontEffects.EFFECTS.get("ITALIC") ) ) {
                font = setItalic(font);
            }
            if( eff.elementAt(i).equals( FontEffects.EFFECTS.get("STRIKETHROUGH") ) ) {
                as.addAttribute(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            }
        }
        
        //Прилагане на italic и bold
        as.addAttribute(TextAttribute.FONT, FontEffects.font);
        FontRenderContext frc = g2d.getFontRenderContext();
        TextLayout tl = new TextLayout (as.getIterator(), frc);
        tl.draw(g2d, x, y);

        //Outline
        Shape sha = tl.getOutline(AffineTransform.getTranslateInstance(x, y));
        if (isOutlineEffect) {
            g2d.setStroke(new BasicStroke( (Float)MainFrame.lineWidthSpinner.getValue() ) );
            g2d.setColor(MainFrame.newColorOutline); // цвят на бордера
            g2d.draw(sha);
            g2d.setColor( MainFrame.newColorFont ); //цвят на шрифта
            g2d.fill(sha);
        }
    }
}
