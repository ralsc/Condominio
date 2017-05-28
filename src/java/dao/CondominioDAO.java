/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Condominio;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Rafael
 */
public class CondominioDAO {
    
    public static void salvar(Condominio condominio) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(condominio);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Condominio condominio) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(condominio);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Condominio> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Condominio.class);
	//crit.addOrder(Order.asc("login"));
	List<Condominio> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
    
    public static List<Condominio> findByFiltros(Integer idMorador, Date dtInicial, Date dtFinal, boolean emAberto) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Condominio.class);
        Criteria subCrit = crit.createCriteria("pagamento", "pagamento", JoinType.INNER_JOIN);
        Criteria subCrit2 = crit.createCriteria("morador", "morador", JoinType.INNER_JOIN);
        
        if(emAberto){
            subCrit.add(Restrictions.isNull("data"));
            crit.add(Restrictions.lt("data", new Date()));
        }
        if(idMorador != null && idMorador > 0){
            subCrit2.add(Restrictions.eq("id", idMorador));
        }
        if(dtInicial != null){
            crit.add(Restrictions.ge("data", dtInicial));
        }
        if(dtFinal != null){
            crit.add(Restrictions.le("data", dtFinal));
        }
        
        List<Condominio> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
}
