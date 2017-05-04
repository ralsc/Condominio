/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Produto;
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
public class ProdutoDAO {
    
    public static void salvar(Produto produto) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(produto);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Produto produto) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(produto);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Produto> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Produto.class);
	crit.addOrder(Order.asc("descricao"));
	List<Produto> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
