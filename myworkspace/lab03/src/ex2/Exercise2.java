package ex2;

import ex2.HealthProfileReader;
import ex2.pojos.HealthProfile;
import ex2.pojos.Person;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.SyncFailedException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by JC on 07/10/2016.
 */
public class Exercise2 {

    public static void main(String[] args)  throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {
		HealthProfileReader hpr = new HealthProfileReader();

        String firstname = "Paul";
        String lastname = "Pogba";
        String weight = hpr.getWeight(firstname, lastname);
        System.out.println("---------------------");
        System.out.println("Fullname: " + firstname + " " + lastname);
        System.out.println("Weight: " + weight);

        String fullname = "Gino Peruzzi";
        HealthProfile hp = hpr.displayHealthProfile(fullname);
        System.out.println("---------------------");
        System.out.println("Fullname: " + fullname);
        System.out.println(hp.toString());

        String operator = ">";
        double searchWeight  = 80;
        System.out.println("---------------------");
        System.out.println("Searching for people with weight " + operator + " " + searchWeight);
        List<Person> heavyPlayers = hpr.getPersonByWeight(searchWeight, operator);
        for (Iterator<Person> i = heavyPlayers.iterator(); i.hasNext();) {
            Person p = i.next();
            System.out.println();
            System.out.println("Fullname: " + p.getFirstname() + " " + p.getLastname());
            System.out.println("Weight:" + p.gethProfile().getWeight());
        }
    }
}
