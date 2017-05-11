/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Servico;
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
public class ServicoDAO {
    
    public static void salvar(Servico servico) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(servico);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Servico servico) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(servico);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Servico> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Servico.class);
	crit.addOrder(Order.asc("descricao"));
	List<Servico> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
