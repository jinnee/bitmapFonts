package MagicFonts;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import org.xml.sax.SAXException;

/**
 *
 * @author ipopov
 */
public class ProgramSettings {
    private static final String fileName = "settings.xml";
    private String expression = "";

    DocumentBuilderFactory factory;
    DocumentBuilder docBuilder;
    private Document doc = null;
    
    private XPath xpath = null;

    public ProgramSettings() {
        DocumentBuilder parser;
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            doc =  parser.parse(new java.io.File( fileName ));
            xpath = XPathFactory.newInstance().newXPath();
        } catch (SAXException ex) {
            Logger.getLogger(ProgramSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProgramSettings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProgramSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getField(String field) {
        String result = "";
        try {
            if( field.equals("Name")) {
                expression = "/ProgramSettings/Header/Name";
            } else if(field.equals("Version")) {
                expression = "/ProgramSettings/Header/Version";
            } else if(field.equals("LoadFontDefaultPath")) {
                expression = "/ProgramSettings/Settings/LoadFontDefaultPath";
            }

            result = xpath.evaluate(expression, doc);

            //Ако не е попълнена стойност за директорията по подразбиране
            if(field.equals("LoadFontDefaultPath") && result.trim().isEmpty()){
                result = System.getProperty("user.dir");
            }

        } catch (XPathExpressionException ex) {
            createXmlSettings();
            Logger.getLogger(ProgramSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private void createXmlSettings(){
        try {
            factory = DocumentBuilderFactory.newInstance();
            docBuilder = factory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ProgramSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
