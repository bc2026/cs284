import javax.xml.namespace.QName;

public class Main {
    /**
     * The Person class implements an application that
     * assigns attributes name, age and address
     *
     * @author  Bhagawat Chapagain
     * @version 1.0
     * @created 01•19•2022
     */

    public static class Person
    {
        String name;
        int age;
        String address;


        public Person(String name, int age, String address)
        {
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String getName()
        {
            return name;
        }
        public int getAge()
        {
            return age;
        }

        public String getAddress()
        {
            return address;
        }


        public void setName(String newName)
        {
            name = newName;
        }
        public void setAge(int newAge)
        {
            age = newAge;
        }

        public void setAddress(String newAddy)
        {
            address = newAddy;
        }

    }


    public class Undergraduate{

        String CWID;
        String GPA;

        public Undergraduate(String CWID, String GPA)
        {
            this.CWID = CWID;
            this.GPA = GPA;
        }

        public String getCredentials(){
            returnString = CWID + ", " + GPA;
            return returnString;
        }
    }
    public class Graduate{
        String CWID;
        String GPA;
        String major;


        public String getCredentials(){
            returnString = CWID + ", " + GPA + ", " + major;

            return returnString;
        }
    }
    public class Faculty {
        public String getCredentials(){pass}
    }
    public class Tenured {
        public String getCredentials(){pass}
    }
    public class NonTenured{
        public String getCredentials(){pass}
    }
    public static void main(String[] args) {

        // new Person instance P is defined below:
        Person P = new Person("Joe", 18, "123 Seesame St");

        // check values given to instance P
        System.out.println(P.getName());
        System.out.println(P.getAge());
        System.out.println(P.getAddress());

        System.out.println();

        // check if setters work
        P.setName("Joseph");
        P.setAge(19);
        P.setAddress("123 Sesame St., USA");

        System.out.println();

        // check if values have changed from setters
        System.out.println(P.getName());
        System.out.println(P.getAge());
        System.out.println(P.getAddress());

    }
}