/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab11zad2;

/**
 *
 * @author Paweł
 */

import javax.persistence.*;


public class Main 
{
    public static void main(String[] args) throws Exception 
    {
        int amountOfPeoleInFamily = 30;
        setUpData(amountOfPeoleInFamily);

        showJobOfPerson(25);
        showSumOfSalary(25);
        showAvarageSalaryFamily(3);
    }
    public static EntityManagerFactory factory;

    public static void setUpData(int amountOfPeoleInFamily) throws Exception 
    {
        factory = Persistence.createEntityManagerFactory("people");
        EntityManager entitymanager = factory.createEntityManager();
        entitymanager.getTransaction().begin();
     
        Query query = entitymanager.createQuery("SELECT p FROM Person p");  
        if(query.getResultList().size() == 0) 
        {   
            Family family = new Family();
            family.setDescription("Family Wolski");
            
            Job job = new Job();
            job.setJobDescr("Job Wolski");
            job.setSalary(1000);
            
            Job job2 = new Job();
            job2.setJobDescr("Job Wolski");
            job2.setSalary(2000);
            
            entitymanager.persist(job);
            entitymanager.persist(job2);
            entitymanager.persist(family);
            
            for (int i = 0; i < amountOfPeoleInFamily; i++) 
            {
                Person person = new Person();
                person.setFirstName("Pawel" + i);
                person.setLastName("Wolski" + i);
                entitymanager.persist(person);
                
                if (i >= 20) 
                {
                    person.getJobList().add(job2);
                }
                family.getMembers().add(person);
                person.getJobList().add(job);
                person.setFamily(family);
                
                entitymanager.persist(person);
                entitymanager.persist(family);
            }
        }
        
        entitymanager.getTransaction().commit();
        entitymanager.close();
    }
    public static void showJobOfPerson(int personID) 
    {
        EntityManager entitymanager = factory.createEntityManager();
        
        Query query = entitymanager.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        query.setParameter("id", Integer.toString(personID));
        
        Person person = (Person)query.getSingleResult();
        
        System.out.println(person.getFirstName() + " " + person.getLastName() + "\n");
        for(int i = 0; i < person.jobList.size(); i++)
        {
            Job j = person.jobList.get(i);
            System.out.print("id: " + j.getId() + " salary: " + j.getSalary() + ", ");
        }
        System.out.println("");
        
        entitymanager.close();
    }
    public static void showSumOfSalary(int personID) 
    {
        EntityManager entitymanager = factory.createEntityManager();   
        Query query = entitymanager.createQuery("SELECT p FROM Person p WHERE p.id = :id");
        query.setParameter("id", Integer.toString(personID));
        
        Person person = (Person)query.getSingleResult();
        int sum = 0;
        for(int i = 0; i < person.jobList.size(); i++)
        {
            Job j = person.jobList.get(i);
            sum += j.getSalary();
        }

        System.out.println(person.getFirstName() + " " + person.getLastName() + " Sum of salaries: " + sum);
        entitymanager.close();
    }
    public static void showAvarageSalaryFamily(int familyID) 
    {
        EntityManager entitymanager = factory.createEntityManager(); 
   
        Query query = entitymanager.createQuery("SELECT f FROM Family f WHERE f.id = :id");
        query.setParameter("id", familyID);
        Family family = (Family)query.getSingleResult();
        
        double sum = 0;
        for(int i = 0; i < family.getMembers().size(); i++)
        {
            Person p = family.getMembers().get(i);
            for(int k = 0; k < p.getJobList().size(); k++)
            {
                Job j = p.getJobList().get(k);
                sum += j.getSalary();
            }
        }
        System.out.println("Avarage salary in Family: " + sum/family.getMembers().size());
        entitymanager.close();
    }

}
