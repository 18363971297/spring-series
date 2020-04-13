package com.lsl.mybatis.builder.xml;

import com.lsl.mybatis.builder.BuilderException;
import com.lsl.mybatis.io.Resource;
import com.lsl.mybatis.parsing.XNode;
import com.lsl.mybatis.parsing.XPathParser;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

/**
 * @Author: liushoulong
 * @Date: 2019/11/26 16:18
 * 封装XPathParse工具类
 *  用于解析xml并映射到configuration对象中
 */
public class XMLConfigBuilder {

    /**
     * xml文件的解析对象
     */
    private XPathParser parser;

    /**
     *
     * @param parser 传入xml解析类
     */
    public XMLConfigBuilder(XPathParser parser){
        this.parser = parser;
    }

    /***
     * 解析各标签元素
     */

    /**
     * 获取根元素节点
     */
    public XNode getRoot(){
        // 根据根元素表达式获取根元素对象
        XNode root = parser.evalNode("/configuration");
        return root;
    }

    public static void main(String[] agrs){
        // 解析工具类
        XMLMapperEntityResolver entityResolver = new XMLMapperEntityResolver();

        // 资源
        InputStream is =  XMLConfigBuilder.class.getResourceAsStream("/MapperConfig.xml");
        //InputStream is = ClassLoader.getSystemResourceAsStream("/MapperConfig.xml");
        //InputStream is = Resource.getResourceAsStream("/MapperConfig.xml");

        XPathParser parser = new XPathParser(is,false,null,entityResolver);

        // 获取根节点
        XNode root = parser.evalNode("/configuration");

        // 解析properties标签 保存全局的key：value值
        Properties varis = new Properties(); // 存入解析的properties标签数据

        handlerProperties(root, varis);



        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xPath = xPathFactory.newXPath();
        try{
            Node settings = (Node) xPath.evaluate("settings", root.getNode(), XPathConstants.NODE);
            NodeList settingsList = settings.getChildNodes();
            Properties propSettings = new Properties();
            for(int i=0;i<settingsList.getLength();i++){
                Node setting = settingsList.item(i);
                NamedNodeMap attSetting = setting.getAttributes();
                String name = "";
                String value = "";
                for(int j=0;j<attSetting.getLength();j++){
                    Node attr = attSetting.item(j);
                    String n = attr.getNodeName();
                    if(n == "name"){
                        name = attr.getTextContent();
                    }
                    if(n == "value"){
                        value = attr.getTextContent();
                    }
                }
                if(name != "" && value != ""){
                    propSettings.put(name, value);
                }
                System.out.println("" + setting.getNodeName());
            }
            varis.putAll(propSettings);
            System.out.println("settings: " + settings.getNodeName());
        }catch (XPathExpressionException xp){

        }
        //XNode settings  = parser.evalNode("settings");
        // 打印输出
        printProperties(varis);
    }

    public static void printProperties(Properties varis) {
        int size = varis.size();
        Enumeration<String> k = (Enumeration<String>) varis.propertyNames();
        while(k.hasMoreElements()){
            String key = k.nextElement();
            String value = varis.getProperty(key);
            System.out.println("key:" + key +",value:"+value);
        }
    }

    public static void handlerProperties(XNode root, Properties varis) {
        XNode propertieeNode = root.evalNode("properties");
        if (propertieeNode != null){
            // 获取属性
            String resouce = propertieeNode.getStringAttribute("resource" );
            String url = propertieeNode.getStringAttribute("url" );
            // 二者不能同时存在
            if(resouce != null && url != null){
                throw new BuilderException("解析mybatis-config文件的properties标签下的属性异常:resource、url属性仅能存在一个!");
            }
            // 获取子孩子属性
            NodeList nodeList = propertieeNode.getNode().getChildNodes();
            // 定义子元素值
            Properties prop = new Properties();
            for(int i=0;i<nodeList.getLength();i++){
                Node node = nodeList.item(i);
                System.out.println("" + node.getNodeName());
                NamedNodeMap nnm = node.getAttributes();
                String value = "";
                String name = "";
                for(int j=0;j<nnm.getLength();j++){
                    Node n = nnm.item(j);
                    String ns = n.getNodeName();
                    String vs = n.getTextContent();
                    if(ns == "name"){
                        name = n.getTextContent();
                    }
                    if(ns == "value"){
                        value = n.getTextContent();
                    }
                }
                prop.put(name, value);
            }

            varis.putAll(prop);

            // 解析properties文件
            if(resouce != null){
                // 加载properties文件
                InputStream isResource = XMLConfigBuilder.class.getResourceAsStream(resouce);
                Properties isPro = new Properties();
                try{
                    isPro.load(isResource);
                } catch (IOException io){
                    io.printStackTrace();
                }

                varis.putAll(isPro);
            }else if(url != null){

            }

        }
    }

}
