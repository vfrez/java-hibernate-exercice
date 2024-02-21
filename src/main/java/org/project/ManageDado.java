package org.project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.project.entity.DadosTable;

public class ManageDado {
    private EntityManager em;
    private EntityManagerFactory emf;
    public void initEmfAndEm()
    {
        emf= Persistence.createEntityManagerFactory("my-persistence-unit");
        em=emf.createEntityManager();
    }
    public void cleanup()
    {
        em.close();
    }
    public void insertAndRetrieve()
    {
        System.out.println("-------------------Creating the Objects---------------------");
        DadosTable empObj1=new DadosTable("Informação");
        DadosTable empObj2=new DadosTable("Informação2");
        DadosTable empObj3=new DadosTable("Informação3");

        System.out.println("-------------------Starting the transaction---------------------");
        em.getTransaction().begin();
        em.persist(empObj1);
        em.persist(empObj2);
        em.persist(empObj3);

        System.out.println("-------------------Committing the transaction---------------------");
        em.getTransaction().commit();

        System.out.println("-------------------Objects saved successfully--------------------");
        System.out.println("*******************************************************************");
        System.out.println("------------------- Reading Objects--------------------");

//        List emps=em.createQuery("select p from DadosTable p").getResultList();
//        for (DadosTable current:emps)
//            System.out.println(current);
        System.out.println("-------------------Finished Reading Objects--------------------");
    }
    public static void main(String[] args) {

        ManageDado myClient=new ManageDado();
        System.out.println("-------------------Starting the Client---------------------");
        myClient.initEmfAndEm();
        myClient.insertAndRetrieve();
        myClient.cleanup();
        System.out.println("---------------Shutting down the Client---------------------");

    }

}