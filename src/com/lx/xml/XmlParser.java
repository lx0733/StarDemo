package com.lx.xml;

import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlParser {
    public static void main(String[] args) throws Exception {
        //getXML("src/parameters.xml", "HolderName1");
        System.out.println(new Dto());
        //readXmlFile("src/parameters.xml");
    }

    /**
     * @desc 根据key属性的值，获取节点的值
     * 
     * 
     */
    public static String getXML(String sqlXMLPath, String node) throws Exception {

        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        FileInputStream xmlInputStream = new FileInputStream(sqlXMLPath);
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document document = builder.parse(xmlInputStream);

        Node n = document.getChildNodes().item(0);
        NodeList nl = n.getChildNodes();

        for (int i = 0; i < nl.getLength(); i++) {
            if (!nl.item(i).getNodeName().equals("#text")) {
                Element e = (Element) nl.item(i);
                System.out.println(nl.item(i).getNodeName() + "\t" + e.getAttribute("key") + "\t" + nl.item(i).getTextContent());
                String key = e.getAttribute("key");
                String value = nl.item(i).getTextContent();
                if (key.equals(node)) {
                    return value;
                }
            }
        }
        return null;
    }

    public static void readXmlFile(String fileName) throws Exception {
        FileInputStream xmlInputStream = new FileInputStream(fileName);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // 返回documentBuilderFactory对象
        DocumentBuilder db = dbf.newDocumentBuilder();// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
        Document dt = db.parse(xmlInputStream); // 得到一个DOM并返回给document对象
        Element element = dt.getDocumentElement();// 得到一个elment根元素
        System.out.println("根元素：" + element.getNodeName()); // 获得根节点
        NodeList childNodes = element.getChildNodes(); // 获得根元素下的子节点
        for (int i = 0; i < childNodes.getLength(); i++) // 遍历这些子节点
        {
            Node node1 = childNodes.item(i); // childNodes.item(i);
            // 获得每个对应位置i的结点
            if ("Account".equals(node1.getNodeName())) {
                // 如果节点的名称为"Account"，则输出Account元素属性type
                System.out.println("\r\n找到一个账号. 所属区域:s" + node1.getAttributes().getNamedItem("type").getNodeValue() + "$$");
                NodeList nodeDetail = node1.getChildNodes(); // 获得<Accounts>下的节点
                for (int j = 0; j < nodeDetail.getLength(); j++) { // 遍历<Accounts>下的节点
                    Node detail = nodeDetail.item(j); // 获得<Accounts>元素每一个节点
                    if ("code".equals(detail.getNodeName())) // 输出code
                        System.out.println("卡号: " + detail.getTextContent());
                    else if ("pass".equals(detail.getNodeName())) // 输出pass
                        System.out.println("密码: " + detail.getTextContent());
                    else if ("name".equals(detail.getNodeName())) // 输出name
                        System.out.println("姓名: " + detail.getTextContent());
                    else if ("money".equals(detail.getNodeName())) // 输出money
                        System.out.println("余额: " + detail.getTextContent());
                }
            }
        }

    }

}
