package com.mycompany.lab11_2;

import javax.persistence.*;
import EntityPackage.Family;
import EntityPackage.Person;
import EntityPackage.Job;

public class Main {

    public static void main(String[] args) throws Exception {
        
        Main m = new Main();
       
        m.setUp();
        System.out.println("---------");
        m.checkAvailablePeople();
        m.checkFamily();
        System.out.println("---------");
        m.showJobListOfPerson(30);
        System.out.println("---------");
        m.showSumOfSalary(30);
        System.out.println("---------");
        m.showAvarageSalaryInFamily(3);
        System.out.println("---------");
    }
    private EntityManagerFactory factory;
    private void createFactory(){
         factory = Persistence.createEntityManagerFactory("people");
    }
    public void setUp() throws Exception {
        factory = Persistence.createEntityManagerFactory("people");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();  //nowa transakcja    
        Query q = em.createQuery("select m from Person m"); //lista rekordów   
        boolean createNewEntries = (q.getResultList().size() == 0);
        if (createNewEntries) {// No, so lets create new entries    
            Family family = new Family();
            family.setDescription("Family for the Knopfs");
            Job job = new Job();
            job.setJobDescr("Job for the Knopfs");
            job.setSalery(1000);
            Job job2 = new Job();
            job2.setJobDescr("Job for the Knopfs");
            job2.setSalery(2000);
            em.persist(job);
            em.persist(job2);
            em.persist(family);
            for (int i = 0; i < 40; i++) {
                Person person = new Person();
                person.setFirstName("Jim_" + i);
                person.setLastName("Knopf_" + i);
                em.persist(person);
                if (i >= 20) {
                    person.getJobList().add(job2);
                }
                family.getMembers().add(person);
                person.getJobList().add(job);
                person.setFamily(family);
                em.persist(person);
                em.persist(family);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    public void checkAvailablePeople() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select m from Person m"); // We should have 40 Persons in the database   
        System.out.println(q.getResultList().size());
        em.close();
    }

    public void checkFamily() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select f from Family f"); // We should have one family with 40 persons    
        System.out.println(q.getResultList().size());
        System.out.println(((Family) q.getSingleResult()).getMembers().size());
        em.close();
    }

    public void deletePerson() {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.firstName = :firstName AND p.lastName = :lastName");
        q.setParameter("firstName", "Jim_1");
        q.setParameter("lastName", "Knopf_1");
        Person user = (Person) q.getSingleResult();
        em.remove(user);
        em.getTransaction().commit();
        Person person = (Person) q.getSingleResult();
        em.close();
    }

    public void addPerson(Person person) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public void addFamily(Family family) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.getTransaction().begin();
        em.persist(family);
        em.getTransaction().commit();
        em.close();
    }

    public void addJob(Job job) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.getTransaction().begin();
        em.persist(job);
        em.getTransaction().commit();
        em.close();
    }

    public void addPersonToFamily(Family family, Person person) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.getTransaction().begin();
        em.persist(family);
        em.persist(person);
        family.getMembers().add(person);
        person.setFamily(family);
        em.persist(family);
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public void addJobToPerson(Person person, Job job) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        em.getTransaction().begin();
        em.persist(job);
        em.persist(person);
        person.getJobList().add(job);
        em.persist(person);
        em.getTransaction().commit();
        em.close();
    }

    public void showJobListOfPerson(int personID) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        q.setParameter("id", Integer.toString(personID));
        ((Person) q.getSingleResult()).getJobList().size(); //instiantate list
        Person temp = (Person) q.getSingleResult();
        System.out.println(temp.getFirstName() + " " + temp.getLastName());
        System.out.println(temp.getJobList());
        em.close();
    }

    public void showSumOfSalary(int personID) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        Query q = em.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        q.setParameter("id", Integer.toString(personID));
        //((Person) q.getSingleResult()).getJobList().size(); //instiantate list
        Person temp = (Person) q.getSingleResult();
        int sum = 0;
        for (Job j : temp.getJobList()) {
            sum += j.getSalery();
        }
        System.out.println(temp.getFirstName() + " " + temp.getLastName() + " sum of salaries is: " + sum);
        em.close();
    }

    public void showAvarageSalaryInFamily(int familyID) {
        EntityManager em = factory.createEntityManager();    // Begin a new local transaction so that we can persist a new entity    
        Query q = em.createQuery("SELECT f FROM Family f WHERE f.id = :id");
        q.setParameter("id", familyID);
        //((Family) q.getSingleResult()).getMembers().size(); //instiantate list
        Family temp = (Family) q.getSingleResult();
        float sum = 0;
        for (Person p : temp.getMembers()) {
            
            for (Job j : p.getJobList()) {
                sum += j.getSalery();
                
            }
        }
        System.out.println("avarage salary is: "+ sum/temp.getMembers().size() );
        em.close();
    }

}
