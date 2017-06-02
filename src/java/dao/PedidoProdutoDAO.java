/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import entidades.ItemPedido;
import entidades.PedidoProduto;
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
public class PedidoProdutoDAO {
    
    public static void salvar(PedidoProduto pedidoProduto) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.saveOrUpdate(pedidoProduto);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static void delete(PedidoProduto pedidoProduto) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Transaction tr = session.beginTransaction();
        session.delete(pedidoProduto);
        tr.commit();
        session.flush();
        session.close();
    }
    
    public static List<PedidoProduto> findAll() throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        Criteria crit = session.createCriteria(PedidoProduto.class);
	crit.addOrder(Order.asc("numero"));
	List<PedidoProduto> list = crit.list();
        for (PedidoProduto pedidoProduto : list) {
            for(ItemPedido item: pedidoProduto.getListItemPedido()){
                System.out.println(item.getDetalhes());
            }
        }
        session.flush();
        session.close();
        return list;
    }
}
