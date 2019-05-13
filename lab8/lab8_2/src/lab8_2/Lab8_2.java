
package lab8_2;

public class Lab8_2 {

    
    public static void main(String[] args) {
        Person person_one   = new Person("Dominik",22,true,7781243);
        Person person_two   = new Person("Dominik",22,true,778111243);
        Person person_three = new Person("Dominik",23,true,778111243);
        
        System.out.println(person_one.equals(person_two));
        System.out.println(person_two.equals(person_three));
        System.out.println(person_one.equals(person_three));
    }
    
}
