import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTest {

	  public static void main(String[] args)  throws ParserConfigurationException, SAXException,
	          											IOException, XPathExpressionException {

	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true);
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    System.out.println("Loading books.xml...");
	    Document doc = builder.parse("resources/books.xml");

	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    System.out.println("Reading list of titles...");
	    System.out.println("(using xpath = /bookstore/book)");
	    XPathExpression expr = xpath.compile("/bookstore/book");

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    for (int i = 0; i < nodes.getLength(); i++) {
			Element e = (Element) nodes.item(i);
			NodeList titleList = e.getElementsByTagName("title");
			NodeList authorList = e.getElementsByTagName("author");
			System.out.println("---------------------");
	        System.out.println("Title: " + titleList.item(0).getChildNodes().item(0)
					.getNodeValue());
			System.out.println("Author: " + authorList.item(0).getChildNodes().item(0)
					.getNodeValue());
	    }

	  }
}
