
package lab10_1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dominik
 */


public class Student implements Serializable {
    private String firstName;
    private String lastName;
    private int index;
    private List<Integer> marksList;

    public Student(String firstName, String lastName, int index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.index = index;
        this.marksList = new ArrayList<>();
    }
     public Student(int index) {
        this.firstName = "";
        this.lastName = "";
        this.index = index;
        this.marksList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return firstName + " "+ lastName + " " + index + " marksList=" + marksList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.index;
        return hash;
    }

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
        final Student other = (Student) obj;
        if (this.index != other.index) {
            return false;
        }
        return true;
    }
    public void addMark(Integer mark){
        marksList.add(mark);
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public List<Integer> getMarksList() {
        return marksList;
    }

    public void setMarksList(List<Integer> marksList) {
        this.marksList = marksList;
    }
    
    
}
