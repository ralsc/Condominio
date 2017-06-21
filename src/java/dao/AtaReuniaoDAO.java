/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.AtaReuniao;
import entidades.TipoTaxaMulta;
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
public class AtaReuniaoDAO {
    
    public static void salvar(AtaReuniao ataReuniao) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(ataReuniao);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(AtaReuniao ataReuniao) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(ataReuniao);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<AtaReuniao> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(AtaReuniao.class);
	crit.addOrder(Order.asc("descricao"));
	List<AtaReuniao> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
