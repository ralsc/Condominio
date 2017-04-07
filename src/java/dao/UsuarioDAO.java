/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.Condominio;
import entidades.Usuario;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;

/**
 *
 * @author Rafael
 */
public class UsuarioDAO {
    
    public static void salvar(Usuario usuario) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(usuario);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(Usuario usuario) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(usuario);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<Usuario> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Usuario.class);
	crit.addOrder(Order.asc("login"));
	List<Usuario> list = crit.list();
        session.flush();
        session.close();
        return list;
    }
    
    public static Usuario findByFiltros(String login, String senha) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(Usuario.class);
        crit.add(Restrictions.eq("login", login));
        crit.add(Restrictions.eq("senha", senha));
        
        Usuario usuario = (Usuario) crit.uniqueResult();
        session.flush();
        session.close();
        return usuario;
    }
}
