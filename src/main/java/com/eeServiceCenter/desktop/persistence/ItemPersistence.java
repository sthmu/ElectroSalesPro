package com.eeServiceCenter.desktop.persistence;

import com.eeServiceCenter.desktop.Entity.Item;
import com.eeServiceCenter.desktop.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemPersistence implements CrudPresistence<Item>{


    public static List<Item> getAll() {
        Session session= HibernateUtil.getSession();
        session.beginTransaction();
        Query query=session.createQuery("FROM Item");
        List<Item> list=query.list();
        session.close();
        return list;

    }

    @Override
    public boolean save(Item entity) {
        Session session=HibernateUtil.getSession();
        Transaction transaction= session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
        return true;
    }
@Override
    public boolean delete(String code) {
    Session session=HibernateUtil.getSession();
    Transaction transaction= session.beginTransaction();
    System.out.println(code);
    session.delete(session.find(Item.class,code));
    transaction.commit();
    session.close();
    return true;
    }
}
