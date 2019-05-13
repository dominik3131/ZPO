package EntityPackage;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Mark implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private double mark;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    public Student student;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @ManyToOne
    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return subject.toString() + " " + mark;
    }
}
