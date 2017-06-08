/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.HibernateUtil;
import dto.EntradaSaidaDTO;
import entidades.TipoTaxaMulta;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Rafael
 */
public class RelatoriosDAO {
    
    public static List<EntradaSaidaDTO> listEntradaSaida(String movimento, Date dtIni, Date dtFim) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
	StringBuilder sql = new StringBuilder();
            sql.append(
            " SELECT pag.data AS datPag, 'SERVIÇO' AS tipo, ser.descricao AS descricao, 'S' AS movimento, pag.valor AS valor " + 
            " FROM AgendamentoServico ags " + 
            " INNER JOIN Servico ser ON ser.id = fk_servico " + 
            " INNER JOIN Pagamento pag ON pag.id = fk_pagamento " + 
            " WHERE pag.data IS NOT NULL " + 
            " AND 'S' = COALESCE(:movimento, 'S') " + 
            " AND pag.data >= COALESCE(:dtIni, '1900-12-31') " + 
            " AND pag.data <= COALESCE(:dtFim, '2099-12-31') " + 
            " UNION " + 
            " SELECT pag.data AS datPag, 'PRODUTO' AS tipo, pro.descricao AS descricao, 'S' AS movimento, pag.valor AS valor " + 
            " FROM PedidoProduto ppr " + 
            " INNER JOIN Pagamento pag ON pag.id = fk_pagamento " + 
            " INNER JOIN ItemPedido itp ON itp.fk_pedidoProduto = ppr.id " + 
            " INNER JOIN Produto pro ON pro.id = itp.fk_produto " + 
            " WHERE pag.data IS NOT NULL " + 
            " AND 'S' = COALESCE(:movimento, 'S') " + 
            " AND pag.data >= COALESCE(:dtIni, '1900-12-31') " + 
            " AND pag.data <= COALESCE(:dtFim, '2099-12-31') " + 
            " UNION " + 
            " SELECT pag.data AS datPag, 'BOLETO' AS tipo, 'Condominio' AS descricao, 'E' AS movimento, pag.valor AS valor " + 
            " FROM Condominio con " + 
            " INNER JOIN Pagamento pag ON pag.id = fk_pagamento " + 
            " WHERE pag.data IS NOT NULL " + 
            " AND 'E' = COALESCE(:movimento, 'E') " + 
            " AND pag.data >= COALESCE(:dtIni, '1900-12-31') " + 
            " AND pag.data <= COALESCE(:dtFim, '2099-12-31') " + 
            " ORDER BY datPag, movimento ");
			
	Iterator it = session.createSQLQuery(sql.toString()).setString("movimento", movimento)
                .setDate("dtIni", dtIni).setDate("dtFim", dtFim).list().iterator();
		
	List<EntradaSaidaDTO> list = new ArrayList<EntradaSaidaDTO>();
	while (it.hasNext()) {
            Object[] e = (Object[]) it.next();
            EntradaSaidaDTO dto = new EntradaSaidaDTO();
            dto.setDataPagamento((Date) e[0]);
            dto.setTipo((String) e[1]);
            dto.setDescricao((String) e[2]);
            dto.setMovimento((String) e[3]);
            dto.setValor((Float) e[4]);
            list.add(dto);
	}
        
        session.flush();
        session.close();
        return list;
    }
    
    public static List<TipoTaxaMulta> listChamadaCapitalorMulta(String tipo) throws Exception{
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
	StringBuilder sql = new StringBuilder();
            sql.append(" SELECT * FROM TIPOTAXAMULTA ");
        if(tipo.equalsIgnoreCase("Multa")){
            sql.append(" WHERE TIPO = 'Multa' ");
        } else {
            sql.append(" WHERE DESCRICAO LIKE 'chamada de capital%' ");
        }
			
	Iterator it = session.createSQLQuery(sql.toString()).list().iterator();
		
	List<TipoTaxaMulta> list = new ArrayList<TipoTaxaMulta>();
	while (it.hasNext()) {
            Object[] e = (Object[]) it.next();
            TipoTaxaMulta ttm = new TipoTaxaMulta();
            ttm.setId((Integer) e[0]);
            ttm.setDescricao((String) e[1]);
            ttm.setTipo((String) e[2]);
            ttm.setValor((Float) e[3]);
            list.add(ttm);
	}
        
        session.flush();
        session.close();
        return list;
    }
}
