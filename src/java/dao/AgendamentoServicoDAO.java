/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.AgendamentoServico;
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
public class AgendamentoServicoDAO {
    
    public static void salvar(AgendamentoServico agendamentoServico) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(agendamentoServico);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(AgendamentoServico agendamentoServico) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(agendamentoServico);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<AgendamentoServico> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(AgendamentoServico.class);
	crit.addOrder(Order.desc("data"));
	List<AgendamentoServico> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
