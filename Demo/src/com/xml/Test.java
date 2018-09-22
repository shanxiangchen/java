package com.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Test {

	public static void main(String[] args) {
		Document document = getDocument();
		//document.setXMLEncoding("GBK");
		String strs = document.asXML();
		//System.out.println(strs);
		System.out.println(parseXMLStyle(document));
		try {
			document = DocumentHelper.parseText(strs);
			Element root = document.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list = root.selectNodes("Policy");
			for (Iterator<Element> iterator = list.iterator(); iterator.hasNext();) {
				Element element = iterator.next();
				System.out.print(element.attributeValue("ID").toString() + " ## ");
				System.out.print(element.selectSingleNode("PolicyNo").getText() + " ## ");
				System.out.print(element.selectSingleNode("DocuID").getText() + " ## ");
				System.out.print(element.selectSingleNode("Name").getText() + " ## ");
				System.out.println();
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
    public static String parseXMLStyle(Document document) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setNewLineAfterDeclaration(false);
        format.setSuppressDeclaration(false);
        //format.setEncoding("GBK");
        StringWriter strwriter = new StringWriter();
        XMLWriter xmlwriter = new XMLWriter(strwriter, format); 
        try {
            xmlwriter.write(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strwriter.toString().trim();
     }
	
    public static String parseXMLStyle(String str) {
       org.dom4j.Document document = null;
       try {
           document = DocumentHelper.parseText(str);
       } catch (DocumentException e) {
           e.printStackTrace();
       }
       OutputFormat format = OutputFormat.createPrettyPrint();
       format.setNewLineAfterDeclaration(false);
       format.setSuppressDeclaration(false);
       //format.setEncoding("GBK");
       StringWriter strwriter = new StringWriter();
       XMLWriter xmlwriter = new XMLWriter(strwriter, format); 
       try {
           xmlwriter.write(document);
       } catch (IOException e) {
           e.printStackTrace();
       }
       return strwriter.toString().trim();
    }
 
	public static Document getDocument() {
		Document document = null;
		try {
			Element root = DocumentHelper.createElement("Package");
			document = DocumentHelper.createDocument(root);

			Element policy = root.addElement("Policy");
			policy.addAttribute("ID", "1001");
			policy.addElement("PolicyNo").addText("1210056");
			policy.addElement("DocuID").addText("1101");
			policy.addElement("Name").addText("张无忌");

			policy = root.addElement("Policy");
			policy.addAttribute("ID", "1002");
			policy.addElement("PolicyNo").addText("1210057");
			policy.addElement("DocuID").addText("1102");
			policy.addElement("Name").addText("张三丰");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

}