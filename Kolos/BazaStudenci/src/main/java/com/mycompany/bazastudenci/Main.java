package com.mycompany.bazastudenci;

import javax.persistence.*;

import javafx.util.Pair;
import EntityPackage.Subject;
import EntityPackage.Student;
import EntityPackage.Mark;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {
        setUpSubject();
        setUpStudent();
       // showStudent("Jim_0", "Knopf_0");
        //showAvarageMarks();
        System.out.println(showSubjectList());
        //deleteSubject("Subject_1");
        System.out.println(showSubjectList());
        showStudent("Jim_0", "Knopf_0");
        deleteSubject("Subject_1");
        showStudent("Jim_1", "Knopf_1");
        showAvarageMarks();
    }

    private static EntityManagerFactory factory;

    public static void setUpSubject() throws Exception {
        factory = Persistence.createEntityManagerFactory("people");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();  //nowa transakcja
        Query q = em.createQuery("select m from Student m"); //lista rekordów
        boolean createNewEntries = (q.getResultList().size() == 0);
        if (createNewEntries) {// No, so lets create new entries

            for (int i = 0; i < 5; i++) {
                Subject przedmiot = new Subject();
                przedmiot.setName("Subject_" + i);
                em.persist(przedmiot);
            }
        }
        em.getTransaction().commit();
        em.close();
    }

    public static void setUpStudent() throws Exception {
        factory = Persistence.createEntityManagerFactory("people");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();  //nowa transakcja
        Query q = em.createQuery("select m from Student m"); //lista rekordów
        boolean createNewEntries = (q.getResultList().size() == 0);
        if (createNewEntries) {// No, so lets create new entries

            List<Subject> availableSubjects = showSubjectList();
            Random generator = new Random();

            for (int i = 0; i < 2; i++) {
                Student person = new Student();
                person.setFirstName("Jim_" + i);
                person.setLastName("Knopf_" + i);
                for (int j = 0; j < 3; j++) {
                    Mark ocena = new Mark();
                    ocena.setMark(generator.nextInt(5) + 1);
                    ocena.setSubject(availableSubjects.get(j));
                    ocena.student = person;
                    availableSubjects.get(j).oceny.add(ocena);
                    em.persist(ocena);
                    person.marksList.add(ocena);
                    em.persist(person);
                    em.persist(ocena);
                    em.merge(availableSubjects.get(j));
                }

                em.merge(person);

            }
        }

        em.getTransaction().commit();
        em.close();
    }

    public static List<Subject> showSubjectList() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select f from Subject f");
        List<Subject> result = q.getResultList();
        em.close();
        return result;
    }

    public static List<Student> showStudentsList() {
        EntityManager em = factory.createEntityManager();
        Query q = em.createQuery("select f from Student f");
        List<Student> result = q.getResultList();
        em.close();
        return result;
    }

    public static void showStudent(String fName, String lName) {
        EntityManager em = factory.createEntityManager();
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();
        Query q = em
                .createQuery("SELECT p FROM Student p WHERE p.firstName = :firstName AND p.lastName = :lastName");
        q.setParameter("firstName", fName);
        q.setParameter("lastName", lName);
        Student user = (Student) q.getSingleResult();
        em.getTransaction().commit();
        List<Mark> oceny = user.getMarksList();
        oceny.isEmpty();
        System.out.println(user.toString());

        // Begin a new local transaction so that we can persist a new entity
        em.close();
    }

    public static void showAvarageMarks() {
        List<Mark> wszystkieOceny = new ArrayList<>();
        for (Student s : showStudentsList()) {
            for (Mark o : s.getMarksList()) {
                wszystkieOceny.add(o);
            }
        }
        // System.out.println(wszystkieOceny);
        System.out.println(wszystkieOceny.stream().collect(Collectors.groupingBy(
                p -> p.getSubject(),
                Collectors.averagingDouble(p -> p.getMark()))));
    }

    public static void deleteSubject(String subject) {
        EntityManager em = factory.createEntityManager();
        // Begin a new local transaction so that we can persist a new entity
        em.getTransaction().begin();
        Query q = em
                .createQuery("SELECT p FROM Subject p WHERE p.name = :nazwa");
        q.setParameter("nazwa", subject);
        Subject sub = (Subject) q.getSingleResult();
        for (Student s : showStudentsList()) {
            List<Integer> indexToRemove = new ArrayList<>();
            for (Mark o : s.marksList) {
                if (o.getSubject().toString().equals(subject)) {
                    indexToRemove.add(s.marksList.indexOf(o));
                }
            }
            for(Integer i : indexToRemove){
                s.marksList.remove(i);
            }
            em.merge(s);
        }
        em.remove(sub);
        em.getTransaction().commit();
        // Begin a new local transaction so that we can persist a new entity

        em.close();
    }
}
