package com.lsl.core.mybatis.parsing;

import com.lsl.core.mybatis.builder.xml.XMLMapperEntityResolver;
import org.w3c.dom.*;
import org.xml.sax.*;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * @Author: liushoulong
 * @Date: 2019/11/25 16:15
 * 解析xml工具类
 *
 * 1  class.getResourceAsStream加载xml资源
 * 2  DecumentBuilderFactory构建
 * 3  EntityResolver 解析具体的DTD并加载对应的DTD规则
 * 4  生成Document
 * 5  解析Document的具体标签
 * 6  将解析的标签映射到Configuration实例上
 */
public class XPathParseBak {


    private EntityResolver entityResolver;
    private boolean validation = false;
    private Document document;

    public XPathParseBak(EntityResolver entityResolver, boolean validation, InputSource inputSource){
        this.entityResolver = entityResolver;
        this.validation = validation;
        this.document = createDocument(inputSource);
    }

    public XPathParseBak(InputSource inputSource){
        this.entityResolver = new XMLMapperEntityResolver();
        this.document = createDocument(inputSource);
    }

    public XPathParseBak(){
        this.entityResolver = new XMLMapperEntityResolver();
    }



    public  Document createDocument(InputSource inputSource) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            // 指定此代码生成的解析器将在文档解析时验证文档 默认false不校验
            factory.setValidating(validation);
            // 指定此代码生成的解析器将为XML命名空间提供支持。 默认情况下，该值设置为false
            factory.setNamespaceAware(false);
            // 指定此代码生成的解析器将忽略注释。 默认情况下，该值设置为false 。
            factory.setIgnoringComments(true);
            // 指定是否解析器必须在解析XML文档时消除元素内容中的空格
            factory.setIgnoringElementContentWhitespace(false);
            // 解析器将CDATA节点转换为文本节点并将其附加到相邻（如果有的话）文本节点
            factory.setCoalescing(false);
            // 指定此代码生成的解析器将扩展实体引用节点。
            factory.setExpandEntityReferences(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            // 解析要解析的XML文档中存在的实体的EntityResolver
            builder.setEntityResolver(this.entityResolver);
            builder.setErrorHandler(new ErrorHandler() {
                @Override
                public void error(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    throw exception;
                }

                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    // NOP
                }
            });
            return builder.parse(inputSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] agrs) throws Exception{
        //InputStream is = XPathParse.class.getResourceAsStream("/MapperConfig.xml");
        test02();
    }

    public static void test02(){
        InputStream is = XPathParseBak.class.getResourceAsStream("/MapperConfig.xml");
        XPathParseBak xp = new XPathParseBak();
        InputSource ii = new InputSource(is);
        Document document = xp.createDocument(ii);
        Element element = document.getDocumentElement();
        System.out.println("" + element.getTagName());
        NodeList nodeList = element.getElementsByTagName("properties");
        Node node = nodeList.item(0);
        System.out.println("" + node.getNodeName());
        NamedNodeMap nnm = node.getAttributes();
        for(int i=0;i<nnm.getLength();i++){
            Node n = nnm.item(i);
            System.out.println("" + n.getNodeName());
            System.out.println("" + n.getTextContent());
        }

    }

    public static void test01(){
        InputStream is = XPathParseBak.class.getResourceAsStream("/MapperConfig.xml");

        XPathParseBak xPathParse = new XPathParseBak();
        Document document = xPathParse.createDocument(new InputSource(is));
        NodeList nodeList = document.getChildNodes();

        for(int i=0;i<nodeList.getLength();i++){
            Node node = nodeList.item(i);
            NodeList l = node.getChildNodes();
            System.out.println("--" + node.getNodeName());
            for(int j=0;j<l.getLength();j++){
                Node n = l.item(j);
                System.out.println("----" + n.getNodeName());
            }

        }
    }
}
