package ex2;

import ex2.pojos.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ex2.pojos.HealthProfile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HealthProfileReader {

    static Document doc;
    static XPathFactory factory;
    static XPath xpath;

	public HealthProfileReader() throws ParserConfigurationException, SAXException,
			IOException {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		System.out.println("Loading people.xml...");
		this.doc = builder.parse("resources/people.xml");
		this.factory = XPathFactory.newInstance();
		this.xpath = this.factory.newXPath();
	}

	public String getWeight(String firstname, String lastname) throws XPathExpressionException {
		String searchExpr = "/people/person[./firstname/text()='" + firstname
				+ "' and ./lastname/text()='" + lastname + "']";
		XPathExpression expr = this.xpath.compile(searchExpr);
		Object result = expr.evaluate(this.doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            NodeList weightNodes = e.getElementsByTagName("weight");
			return weightNodes.item(0).getChildNodes().item(0).getNodeValue();
		}
		return "";
	}

	public String getHeight(String firstname, String lastname) throws XPathExpressionException {
        String searchExpr = "/people/person[./firstname/text()='" + firstname
                + "' and ./lastname/text()='" + lastname + "']";
        XPathExpression expr = this.xpath.compile(searchExpr);
        Object result = expr.evaluate(this.doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            NodeList heightNodes = e.getElementsByTagName("height");
            return heightNodes.item(0).getChildNodes().item(0).getNodeValue();
        }
        return "";
	}

	public HealthProfile displayHealthProfile(String fullname) throws XPathExpressionException {
        String searchExpr = "/people/person";
        XPathExpression expr = this.xpath.compile(searchExpr);
        Object result = expr.evaluate(this.doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            NodeList firstnameNodes = e.getElementsByTagName("firstname");
            NodeList lastnameNodes = e.getElementsByTagName("lastname");
            String firstname = firstnameNodes.item(0).getChildNodes().item(0).getNodeValue();
            String lastname = lastnameNodes.item(0).getChildNodes().item(0).getNodeValue();
            if (fullname.equals(firstname + " " + lastname)) {
                NodeList weightNodes = e.getElementsByTagName("weight");
                NodeList heightNodes = e.getElementsByTagName("height");
                double weight = Double.parseDouble(weightNodes.item(0).getChildNodes().item(0).getNodeValue());
                double height = Double.parseDouble(heightNodes.item(0).getChildNodes().item(0).getNodeValue());
                return new HealthProfile(weight, height);
            }
        }
        return new HealthProfile(0, 0);
	}

    public List<Person> getPersonByWeight(double weight, String operator) throws XPathExpressionException {
        List<Person> results = new ArrayList<Person>();
        String searchExpr = "/people/person[healthprofile/weight" + operator + weight + "]";
        XPathExpression expr = this.xpath.compile(searchExpr);
        Object result = expr.evaluate(this.doc, XPathConstants.NODESET);
        NodeList nodes = (NodeList) result;
        for (int i = 0; i < nodes.getLength(); i++) {
            Element e = (Element) nodes.item(i);
            NodeList firstnameNodes = e.getElementsByTagName("firstname");
            NodeList lastnameNodes = e.getElementsByTagName("lastname");
            String firstname = firstnameNodes.item(0).getChildNodes().item(0).getNodeValue();
            String lastname = lastnameNodes.item(0).getChildNodes().item(0).getNodeValue();
            results.add(new Person((long)i, firstname, lastname, ""));
        }
        return results;
    }
}