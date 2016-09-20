import java.util.*;

import pojos.HealthProfile;
import pojos.Person;


public class HealthProfileReader {

    public static Map<Long, Person> database = new HashMap<Long, Person>();

    static {
        Person pallino = new Person();
        Person pallo = new Person("Pinco", "Pallo");
        HealthProfile hp = new HealthProfile(68.0, 1.72);
        Person john = new Person("John", "Doe", hp);

        /*
        database.put(pallino.getFirstname() + " " + pallino.getLastname(), pallino);
        database.put(pallo.getFirstname() + " " + pallo.getLastname(), pallo);
        database.put(john.getFirstname() + " " + john.getLastname(), john);
        */
        database.put((long) 0, pallino);
        database.put((long) 1, pallo);
        database.put((long) 2, john);
    }

    /**
     * The health profile reader gets information from the command line about
     * weight and height and calculates the BMI of the person based on this
     * parameters
     *
     * @param args
     */
    public static void main(String[] args) {
        // initializeDatabase();

        Calendar calendar = new GregorianCalendar(1946,5,14);
        createPerson("Donald", "Trump", new Date(calendar.getTimeInMillis()));

        displayHealthProfile((long) 2);
        displayHealthProfile((long) 3);

        updateHealthProfile((long) 3, 1.88, 90.0);
        displayHealthProfile((long) 3);

        /*
        int argCount = args.length;
        if (argCount == 0) {
            System.out.println("I cannot create people out of thing air. Give me at least a name and lastname.");
        } else if (argCount < 2) {
            System.out.println("Are you sure you gave me a first and lastname?");
        } else if (argCount == 2) {
            String fname = args[0];
            String lname = args[1];
            // read the person from the DB
            Person p = database.get(fname + " " + lname);
            if (p != null) {
                System.out.println(fname + " " + lname + "'s health profile is: " + p.gethProfile().toString());
            } else {
                System.out.println(fname + " " + lname + " is not in the database");
            }
        }
        */
        // add the case where there are 3 parameters, the third being a string that matches "weight", "height" or "bmi"
    }

    /*
    public static void initializeDatabase() {
    	Person pallino = new Person();
    	Person pallo = new Person("Pinco","Pallo");
    	HealthProfile hp = new HealthProfile(68.0,1.72);
    	Person john = new Person("John","Doe",hp);

    	database.put(pallino.getFirstname()+" "+pallino.getLastname(), pallino);
    	database.put(pallo.getFirstname()+" "+pallo.getLastname(), pallo);
    	database.put(john.getFirstname()+" "+john.getLastname(), john);
    }
    */

    public static void createPerson(String firstname, String lastname, Date birthdate) {
        Person p = new Person(firstname, lastname, birthdate);
        Integer dbsize = database.size();
        // System.out.println(dbsize);
        database.put((long) dbsize, p);
    }

    public static void displayHealthProfile(Long personId) {
        Person p = database.get(personId);
        if (p != null) {
            if (p.gethProfile() != null) {
                System.out.println(p.getFirstname() + " " + p.getLastname() + "'s health profile is: " + p.gethProfile().toString());
            } else {
                System.out.println(p.getFirstname() + " " + p.getLastname() + " does not have a health profile.");
            }
        } else {
            System.out.println("There is no person with ID " + personId.toString() + " in the database.");
        }
    }

    public static void updateHealthProfile(Long personId, Double height, Double weight) {
        Person p = database.get(personId);
        if (p != null) {
            HealthProfile hp = new HealthProfile(weight, height);
            p.sethProfile(hp);
            database.put(personId, p);
        } else {
            System.out.println("There is no person with ID " + personId.toString() + " in the database.");
        }
    }
}