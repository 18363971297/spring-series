package com.lsl.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: liushoulong
 * @Date: 2019/11/14 22:42
 */
public class W3CDomTest {
    public static void main(String[] agrs){
        // 构建一个document工厂类
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 创建一个DocumentBuilder实例
        try{
            InputStream is = W3CDomTest.class.getClassLoader().getResourceAsStream("person.xml");
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document dom = db.parse(is);

            NodeList nl = dom.getChildNodes();
            System.out.println("--" + nl.getLength());
            for(int i = 0;i<nl.getLength() ; i++){
                Node n = nl.item(i);
                System.out.println(""+ n.getNodeName());
            }
        }catch (ParserConfigurationException pce){
pce.printStackTrace();
        }catch (SAXException sax){
sax.printStackTrace();
        }catch (IOException io){
           io.printStackTrace();
        }
    }
}
