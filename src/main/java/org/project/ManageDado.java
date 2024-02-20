package org.project;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.project.entity.DadosTable;

import java.util.Iterator;
import java.util.List;

//https://www.tutorialspoint.com/hibernate/hibernate_examples.htm

public class ManageDado {
    private static SessionFactory factory;

    public static void main(String[] args) {

        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageDado MD = new ManageDado();

        Integer empID1 = MD.addDado("Informação");
        Integer empID2 = MD.addDado("Informação 2");


        /* List down all the employees */
        empID2 = MD.listDado();

        /* Update employee's records */
        MD.updateDado(empID2 - 1, "Nova Informação em Dado1");

        /* Delete an employee from the database */
        MD.deleteDado(empID2);

        /* List down new list of the employees */
        MD.listDado();

    }

    public Integer addDado(String dado1) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer dadoID = null;

        try {
            tx = session.beginTransaction();
            DadosTable dadosTable = new DadosTable(dado1);
            dadoID = (Integer) session.save(dadosTable);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dadoID;
    }

    public Integer listDado( ){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer dadoID = null;

        try {
            tx = session.beginTransaction();
            List dadosTableList = session.createQuery("FROM DadosTable").list();
            for (Iterator iterator = dadosTableList.iterator(); iterator.hasNext();){
                DadosTable dadosTable = (DadosTable) iterator.next();
                dadoID = dadosTable.getId();
                System.out.print("Dado: " + dadosTable.getDado1());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        //Tive que fazer isso, pois session.save(dadosTable) não está voltando o ID correto. Sempre é 0
        return dadoID;
    }

    /* Method to UPDATE dado1 for an employee */
    public void updateDado(Integer dadoID, String dado1 ){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            DadosTable dadosTable = (DadosTable)session.get(DadosTable.class, dadoID);
            dadosTable.setDado1( dado1 );
            session.update(dadosTable);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an employee from the records */
    public void deleteDado(Integer dadoID){
        Session session = factory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            DadosTable dadosTable = (DadosTable)session.get(DadosTable.class, dadoID);
            session.delete(dadosTable);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}