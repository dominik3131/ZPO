package EntityPackage;

import javax.persistence.*;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    private double salery;
    private String jobDescr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", salery=" + salery + ", jobDescr=" + jobDescr + '}';
    }

    public double getSalery() {
        return salery;
    }

    public void setSalery(double salery) {
        this.salery = salery;
    }

    public String getJobDescr() {
        return jobDescr;
    }

    public void setJobDescr(String jobDescr) {
        this.jobDescr = jobDescr;
    }
}
