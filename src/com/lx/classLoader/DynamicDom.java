package com.lx.classLoader;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element; 
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
//解析xml文件，获取类和方法
public class DynamicDom {
    private static DocumentBuilderFactory dbFactory = null;  
    private static DocumentBuilder db = null;  
    private static Document document = null;  
    
    static{  
        try {  
            dbFactory = DocumentBuilderFactory.newInstance();  
            db = dbFactory.newDocumentBuilder();  
        } catch (ParserConfigurationException e) {  
            e.printStackTrace();  
        }  
    }  
    
   
    public Map<String,List<String>> getMethods(String fileName) throws SAXException, IOException{  
        Map<String,List<String>> classes = new HashMap<String, List<String>>();
        document = db.parse(fileName);  
        NodeList nList = document.getElementsByTagName("class");
        for(int i = 0 ; i<nList.getLength();i++){ 
            Node node = nList.item(i); 
            Element ele = (Element)node; 
            if(node.getNodeType() == Element.ELEMENT_NODE){
              String clazz = ele.getAttribute("name"); 
              List<String> methods = new ArrayList<String>();
              String method = ele.getElementsByTagName("method").item(0).getTextContent();
              methods.add(method);
              classes.put(clazz, methods);           
            }
        }
        return classes;
    } 
}