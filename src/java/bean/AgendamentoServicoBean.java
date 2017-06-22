/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.AgendamentoServicoDAO;
import dao.ServicoDAO;
import entidades.AgendamentoServico;
import entidades.Pagamento;
import entidades.Servico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.FacesMessagesUtil;
import util.RelatorioUtil;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "agendamentoServicoBean")
@ViewScoped
public class AgendamentoServicoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private AgendamentoServico agendamentoServico = new AgendamentoServico();
    private AgendamentoServico agendamentoServicoRel = new AgendamentoServico();
    private List<AgendamentoServico> listAgendamentoServicos;
    private List<Servico> listServicos;
    private boolean edicao;
    private Integer idServico;
    
    @PostConstruct
    public void preload() {
        agendamentoServico.setPagamento(new Pagamento());
    }
    
    public void changeServico() {
        for(Servico servico: listServicos) {
            if(servico.getId().equals(this.getIdServico())){
                agendamentoServico.setValor(servico.getPreco());
            }    
        }
        this.changeValor();
    }
    
    public void changeValor(){
        this.getAgendamentoServico().getPagamento().setValor(this.getAgendamentoServico().getValor());
    }
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getIdServico() == null || this.getIdServico().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Serviço: campo obrigatório!");
                return;
            }
            if(this.getAgendamentoServico().getData() == null){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Data: campo obrigatório!");
                return;
            }
            if(this.getAgendamentoServico().getSituacao() == null || this.getAgendamentoServico().getSituacao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Situação: campo obrigatório!");
                return;
            }
            if(this.getAgendamentoServico().getValor() == null || this.getAgendamentoServico().getValor().equals(0)){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Valor: campo obrigatório!");
                return;
            }
            
            this.getAgendamentoServico().setServico(new Servico(this.getIdServico()));
            AgendamentoServicoDAO.salvar(this.getAgendamentoServico());
            this.setListAgendamentoServicos(null);
            this.setAgendamentoServico(new AgendamentoServico());
            agendamentoServico.setPagamento(new Pagamento());
            this.setIdServico(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
        if(this.getAgendamentoServico().getServico() != null){
            this.setIdServico(this.getAgendamentoServico().getServico().getId());
        }
    }
    
    public void novo(){
        this.setAgendamentoServico(new AgendamentoServico());
        agendamentoServico.setPagamento(new Pagamento());
        this.setIdServico(null);
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            AgendamentoServicoDAO.delete(this.getAgendamentoServico());
            this.setListAgendamentoServicos(null);
            this.setAgendamentoServico(new AgendamentoServico());
            agendamentoServico.setPagamento(new Pagamento());
            this.setIdServico(null);
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluído com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public void gerarRelatorio() throws Exception {
        try {			
            List<AgendamentoServico> dataSource = new ArrayList<>();
            if(this.getAgendamentoServicoRel().getNumeroRecibo() == null){
                this.getAgendamentoServicoRel().setNumeroRecibo(
                    AgendamentoServicoDAO.incNumRecibo(this.getAgendamentoServicoRel()));
            }
            dataSource.add(this.getAgendamentoServicoRel());
            Map<String, Object> parametros = new HashMap<>();

            String url = "/jasper/MA_Recibo.jasper";

            RelatorioUtil.gerarRelatorio("Recibo.pdf", dataSource, parametros, url);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+e.getMessage());
        }
    }
    
    public AgendamentoServico getAgendamentoServico() {
        return agendamentoServico;
    }
    
    public void setAgendamentoServico(AgendamentoServico agendamentoServico) {
        this.agendamentoServico = agendamentoServico;
    }
    
    public List<AgendamentoServico> getListAgendamentoServicos() {
        if (this.listAgendamentoServicos == null) {
            try {
                this.listAgendamentoServicos = AgendamentoServicoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(AgendamentoServicoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listAgendamentoServicos;
    }
    
    public void setListAgendamentoServicos(List<AgendamentoServico> listAgendamentoServicos) {
        this.listAgendamentoServicos = listAgendamentoServicos;
    }

    public List<Servico> getListServicos() {
        if (this.listServicos == null) {
            try {
                this.listServicos = ServicoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(AgendamentoServicoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listServicos;
    }
        
    public void setListServicos(List<Servico> listServicos) {
        this.listServicos = listServicos;
    }
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public AgendamentoServico getAgendamentoServicoRel() {
        return agendamentoServicoRel;
    }

    public void setAgendamentoServicoRel(AgendamentoServico agendamentoServicoRel) {
        this.agendamentoServicoRel = agendamentoServicoRel;
    }
    
}
