package EntityPackage;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String id;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy = "student", orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<Mark> marksList = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String Id) {
        this.id = Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Leave the standard column name of the table
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Mark> getMarksList() {
        return this.marksList;
    }

    public void setMarksList(List<Mark> nickName) {
        this.marksList = nickName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + marksList;
    }

}
