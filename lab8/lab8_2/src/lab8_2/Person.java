package lab8_2;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.METHOD) 
@interface IgnoreEquals {} 

public class Person {

    public Person(String name, int age, boolean isMale, int phoneNumber) {
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.phoneNumber = phoneNumber;
    }

    private String name;
    private int age;
    private boolean isMale;
    private int phoneNumber;
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        
        Method[] declaredMethods = Person.class.getDeclaredMethods();
        Method[] getterMethods = Arrays.stream(declaredMethods)
                .filter(m -> m.getName().contains("get"))
                .filter(m -> !m.isAnnotationPresent(IgnoreEquals.class))
                .toArray(m -> new Method[m]);
        for(Method m : getterMethods){
            try {
                if(!m.invoke(this).equals(m.invoke(other)))
                    return false;
            } catch (Exception ex) {
                return false;
            } 
        }
        return true;
    }
    
    /*
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (this.isMale != other.isMale) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    */
    public boolean isIsMale() {
        return isMale;
    }
    @IgnoreEquals
    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsMale(boolean isMale) {
        this.isMale = isMale;
    }
    
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.age;
        hash = 37 * hash + (this.isMale ? 1 : 0);
        return hash;
    }

}
