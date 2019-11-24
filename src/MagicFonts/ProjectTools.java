/*
 * LoadProject - зарежда проек от xml файл
 * SaveProject - записва проект във файл
 * StringToColor - Конвентира стринг от вида java.awt.Color[r=204,g=204,b=255] към Color
 * copyFile - копира файл. Използва се да копира временният файл картинка (.png) в друга картинка
 * OnlyDigit - връща true, ако стринга е само от цифри
 */
package MagicFonts;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author ipopov
 */
public class ProjectTools {
    public static final String projectStatus[] = {"NEW", "OPENED" , "NOTSAVED", "SAVED"};
    public static Map statuses = new HashMap();
    public static String status = "";

    private String projectName = "";
    private String fontName = "";
    private String customFontPath = "";
    private String fontType = "";
    private String AbsolutePath = "";

    private String fontSize = "";
    private boolean italic = false;
    private boolean bold = false;
    private boolean outline = false;
    private Color colorOutline = null;
    private Float outlineLineWidth = 0.0f;

    private Color color = null;

    private String alphabetSize = "";
    private String sideChar = "";
    private boolean bitmapFWT = false;

    private String numberData = "";


    private Document doc = null;
    private DocumentBuilder db = null;
    private DocumentBuilderFactory dbf = null;

    public ProjectTools() {
        fontType = Constants.FONT_TYPE_LOGICAL;
        
        try {
            dbf = DocumentBuilderFactory.newInstance();
            db = dbf.newDocumentBuilder();
            doc = db.newDocument();

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void loadProject() {
        try {
            XPath xpath = null;
            db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc = db.parse(new java.io.File(this.AbsolutePath));
            xpath = XPathFactory.newInstance().newXPath();

            status = projectStatus[1];

            projectName = xpath.evaluate("/MagicFontsProject/ProjectName", doc);
            fontName = xpath.evaluate("/MagicFontsProject/Fonts/FontName", doc);
            customFontPath = xpath.evaluate("/MagicFontsProject/Fonts/CustomFontPath", doc);
            fontType = xpath.evaluate("/MagicFontsProject/Fonts/FontType", doc);

            fontSize = xpath.evaluate("/MagicFontsProject/FontSettings/FontSize", doc);


            italic = Boolean.parseBoolean( xpath.evaluate("/MagicFontsProject/FontSettings/Italic", doc));
            bold = Boolean.parseBoolean( xpath.evaluate("/MagicFontsProject/FontSettings/Bold", doc));
            color = stringToColor( xpath.evaluate("/MagicFontsProject/FontSettings/Color", doc) );

            outline = Boolean.parseBoolean( xpath.evaluate("/MagicFontsProject/FontEffects/Outline/isOtline", doc));
            colorOutline = stringToColor( xpath.evaluate("/MagicFontsProject/FontEffects/Outline/ColorOutline", doc));
            outlineLineWidth = Float.parseFloat(  xpath.evaluate("/MagicFontsProject/FontEffects/Outline/LineWidth", doc) );

            alphabetSize = xpath.evaluate("/MagicFontsProject/AlphabetSettings/AlphabetSize", doc);
            sideChar = xpath.evaluate("/MagicFontsProject/AlphabetSettings/SideChar", doc);
            bitmapFWT = Boolean.parseBoolean( xpath.evaluate("/MagicFontsProject/AlphabetSettings/BitmapFWT", doc));
            numberData = xpath.evaluate("/MagicFontsProject/AlphabetSettings/NumberData", doc);

        } catch (XPathExpressionException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
    }

    public void saveProject() {
        try {
            doc = db.newDocument();
            Element root = doc.createElement("MagicFontsProject");
            doc.appendChild(root);

            Element ProjectName = doc.createElement("ProjectName");
            ProjectName.setTextContent(projectName);
            root.appendChild(ProjectName);

            Element Fonts = doc.createElement("Fonts");

            Element FontName = doc.createElement("FontName");
            FontName.setTextContent(fontName);
            Fonts.appendChild(FontName);

            Element CustomFontPath = doc.createElement("CustomFontPath");
            CustomFontPath.setTextContent(customFontPath);
            Fonts.appendChild(CustomFontPath);

            Element FontType = doc.createElement("FontType");
            FontType.setTextContent(fontType);
            Fonts.appendChild(FontType);

            /////////////////////////////
            Element FontSettings = doc.createElement("FontSettings");

            Element FontSize = doc.createElement("FontSize");
            FontSize.setTextContent(fontSize);
            FontSettings.appendChild(FontSize);

            Element Italic = doc.createElement("Italic");
            Italic.setTextContent( String.valueOf(italic) );
            FontSettings.appendChild(Italic);

            Element Bold = doc.createElement("Bold");
            Bold.setTextContent( String.valueOf(bold) );
            FontSettings.appendChild(Bold);

            Element Color = doc.createElement("Color");
            Color.setTextContent( color.toString() );
            FontSettings.appendChild(Color);
            /////////////////////////////
            Element FontEffects = doc.createElement("FontEffects");
            //ефект Outline
            Element effectOutline = doc.createElement("Outline");
            FontEffects.appendChild(effectOutline);

            Element isOtline = doc.createElement("isOtline");
            isOtline.setTextContent(String.valueOf(outline));
            effectOutline.appendChild(isOtline);

            Element ColorOutline = doc.createElement("ColorOutline");
            ColorOutline.setTextContent( colorOutline.toString() );
            effectOutline.appendChild(ColorOutline);
            //LineWidth
            Element LineWidth = doc.createElement("LineWidth");
            LineWidth.setTextContent( outlineLineWidth.toString() );
            effectOutline.appendChild(LineWidth);

            //ефект Outline

            /////////////////////////////
            Element AlphabetSettings = doc.createElement("AlphabetSettings");

            Element AlphabetSize = doc.createElement("AlphabetSize");
            AlphabetSize.setTextContent(alphabetSize);
            AlphabetSettings.appendChild(AlphabetSize);

            Element SideChar = doc.createElement("SideChar");
            SideChar.setTextContent(sideChar);
            AlphabetSettings.appendChild(SideChar);

            Element BitmapFWT = doc.createElement("BitmapFWT");
            BitmapFWT.setTextContent( String.valueOf(bitmapFWT) );
            AlphabetSettings.appendChild(BitmapFWT);

            Element NumberData = doc.createElement("NumberData");
            NumberData.setTextContent( String.valueOf(numberData) );
            AlphabetSettings.appendChild(NumberData);
            /////////////////////////////

            root.appendChild(Fonts);
            root.appendChild(FontSettings);
            root.appendChild(FontEffects);
            root.appendChild(AlphabetSettings);

            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            Source src = new DOMSource(doc);
            Result dest = new StreamResult( this.AbsolutePath );
            aTransformer.transform(src, dest);
        } catch (TransformerException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Конвентира стринг от вида java.awt.Color[r=204,g=204,b=255]
     * към Color
     * @param scolor String
     * @return Color
     */
    public Color stringToColor(String scolor) {
        String r,g,b = "";
        String[] splitColors = scolor.split(",");

        r = splitColors[0].substring(splitColors[0].lastIndexOf("=")+1, splitColors[0].length());
        g = splitColors[1].substring(splitColors[1].lastIndexOf("=")+1, splitColors[1].length());
        b = splitColors[2].substring(splitColors[2].lastIndexOf("=")+1, splitColors[2].length()-1);

        Color result = new Color( Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));

        return result;
    }

    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    public static void copyFile(File in, File out) throws IOException {
        FileChannel inChannel = new  FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();

        try {
            inChannel.transferTo(0, inChannel.size(),
                    outChannel);
        } catch (IOException e) {
            throw e;
        } finally {
            if (inChannel != null) inChannel.close();
            if (outChannel != null) outChannel.close();
        }
    }
    
    /**
     *
     * @param expresion
     * @return
     */
    public static boolean onlyDigit(String expresion){
        Pattern p = Pattern.compile("\\d{1,10}");
        Matcher m = p.matcher(expresion);
        return m.matches();
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return this.projectName;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public String getFontName() {
        return this.fontName;
    }

    public void SetFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public String getFontSize() {
        return this.fontSize;
    }

    public void setItalic(boolean italic) {
        this.italic = italic;
    }

    public boolean isItalic() {
        return this.italic;
    }

    public boolean isBold() {
        return bold;
    }

    public void setBold(boolean bold) {
        this.bold = bold;
    }

    public boolean isOutline() {
        return outline;
    }

    public void setOutline(boolean outline) {
        this.outline = outline;
    }

    public Color getColorOutline() {
        return colorOutline;
    }

    public void setColorOutline(Color color) {
        this.colorOutline = color;
    }

    public Float getOutlineLineWidth() {
        return outlineLineWidth;
    }

    public void setOutlineLineWidth(Float lineWidth) {
        this.outlineLineWidth = lineWidth;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getAlphabetSize() {
        return alphabetSize;
    }

    public void setAlphabetSize(String alphabetSize) {
        this.alphabetSize = alphabetSize;
    }

    public String getSideChar() {
        return sideChar;
    }

    public void setSideChar(String sideChar) {
        this.sideChar = sideChar;
    }

    public boolean isBitmapFWT() {
        return bitmapFWT;
    }

    public void setBitmapFWT(boolean bitmapFWT) {
        this.bitmapFWT = bitmapFWT;
    }

    public String getNumberData() {
        return numberData;
    }

    public void setNumberData(String numberData) {
        this.numberData = numberData;
    }

    public String getCustomFontPath() {
        return customFontPath;
    }

    public void setCustomFontPath(String customFontPath) {
        this.customFontPath = customFontPath;
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public String getAbsolutePath() {
        return this.AbsolutePath;
    }

    public void setAbsolutePath( String absolutePath ) {
        this.AbsolutePath = absolutePath;
    }
}
