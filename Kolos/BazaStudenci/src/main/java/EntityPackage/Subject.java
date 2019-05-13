package EntityPackage;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private String name;
    @OneToMany(mappedBy = "subject", orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<Mark> oceny = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void removeMark(Mark mark) {
        oceny.remove(mark);
        if (mark != null) {
            mark.setSubject(null);
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
