/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Unidade;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

/**
 *
 * @author Rafael
 */
public class UnidadeDAO {
    
    public static void salvar(Unidade unidade) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(unidade);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Unidade unidade) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(unidade);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Unidade> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Unidade.class);
	crit.addOrder(Order.asc("numero"));
	List<Unidade> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
