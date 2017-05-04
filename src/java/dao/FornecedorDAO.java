/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Fornecedor;
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
public class FornecedorDAO {
    
    public static void salvar(Fornecedor fornecedor) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(fornecedor);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Fornecedor fornecedor) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(fornecedor);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Fornecedor> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Fornecedor.class);
	crit.addOrder(Order.asc("nome"));
	List<Fornecedor> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
