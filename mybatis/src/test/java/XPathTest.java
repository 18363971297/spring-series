import com.sun.org.apache.xalan.internal.res.XSLMessages;
import com.sun.org.apache.xpath.internal.res.XPATHErrorResources;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: liushoulong
 * @Date: 2019/11/26 14:34
 */
public class XPathTest {

    public static void main(String[] agrs){
        XPathFactory factory = XPathFactory.newInstance();
        XPath xpath = factory.newXPath();
        String expression = "/configuration";

        Document root = createDocument();
        try {
            /**
             * evaluate(expression ,root,returnType)
             * expression: 查找的路径表达式
             * root: document文档，即加载的xml文件对象
             * returnType
             */
           Node nodeList = (Node) xpath.evaluate(expression, root, XPathConstants.NODE);

           System.out.println("" +nodeList.getNodeName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Document createDocument(){
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try{
            InputStream is = XPathTest.class.getResourceAsStream("MapperConfig.xml");
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(is);
            return document;
        } catch (ParserConfigurationException p){
            p.printStackTrace();
        } catch (SAXException sax){
            sax.printStackTrace();
        } catch (IOException io){
            io.printStackTrace();
        }

        return null;
    }
}
