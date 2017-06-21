/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package bean;

import dao.AtaReuniaoDAO;
import entidades.AtaReuniao;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import util.FacesMessagesUtil;

/**
 *
 * @author Rafael
 */
@ManagedBean(name = "ataReuniaoBean")
@ViewScoped
public class AtaReuniaoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private AtaReuniao ataReuniao = new AtaReuniao();
    private AtaReuniao ataReuniaoDown = new AtaReuniao();
    private List<AtaReuniao> listAtaReuniao;
    private boolean edicao;
    
    public void save(){
        try {
            // valida campos obrigatórios
            if(this.getAtaReuniao().getDescricao() == null || this.getAtaReuniao().getDescricao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Descrição: campo obrigatório!");
                return;
            }
            if(this.getAtaReuniao().getDescricao() == null || this.getAtaReuniao().getDescricao().equals("")){
                FacesMessagesUtil.addErrorMessage("msgs","msgs","Selecione e salve um arquivo!");
                return;
            }
            
            AtaReuniaoDAO.salvar(this.getAtaReuniao());
            this.setListAtaReuniao(null);
            this.setAtaReuniao(new AtaReuniao());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Salvo com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+ex.getMessage());
        }
    }
    
    public void editar(){
        this.setEdicao(true);
    }
    
    public void novo(){
        this.setAtaReuniao(new AtaReuniao());
        this.setEdicao(false);
    }
    
    public void delete(){
        try {
            AtaReuniaoDAO.delete(this.getAtaReuniao());
            this.setListAtaReuniao(null);
            this.setAtaReuniao(new AtaReuniao());
            this.setEdicao(false);
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Excluido com sucesso!");
        } catch (Exception ex) {
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao excluir!\n"+ex.getMessage());
        }
    }
    
    public void fileUpload(FileUploadEvent event) {
	try {
            this.getAtaReuniao().setArquivo(event.getFile().getContents());
            this.getAtaReuniao().setNomeArquivo(event.getFile().getFileName());
            //AtaReuniaoDAO.salvar(this.getAtaReuniao());
            FacesMessagesUtil.addInfoMessage("msgs","msgs","Arquivo carregado com sucesso!");
	} catch (Exception e) {
            e.printStackTrace();
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao carregar arquivo!\n"+e.getMessage());
	}
    }
    
    public StreamedContent fileDownload() {
        StreamedContent sc = null;
	try {
            String nomeArquivo = this.getAtaReuniaoDown().getNomeArquivo();
            InputStream input = new ByteArrayInputStream(this.getAtaReuniaoDown().getArquivo());
            sc = new DefaultStreamedContent(input, "img", nomeArquivo);
	} catch (Exception e) {
            e.printStackTrace();
        }
	return sc;
	}
    
    public boolean isEdicao() {
        return edicao;
    }
    
    public void setEdicao(boolean edicao) {
        this.edicao = edicao;
    }

    public AtaReuniao getAtaReuniao() {
        return ataReuniao;
    }

    public void setAtaReuniao(AtaReuniao ataReuniao) {
        this.ataReuniao = ataReuniao;
    }

    public List<AtaReuniao> getListAtaReuniao() {
        if (this.listAtaReuniao == null) {
            try {
                this.listAtaReuniao = AtaReuniaoDAO.findAll();
            } catch (Exception ex) {
                Logger.getLogger(AgendamentoServicoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listAtaReuniao;
    }

    public void setListAtaReuniao(List<AtaReuniao> listAtaReuniao) {
        this.listAtaReuniao = listAtaReuniao;
    }

    public AtaReuniao getAtaReuniaoDown() {
        return ataReuniaoDown;
    }

    public void setAtaReuniaoDown(AtaReuniao ataReuniaoDown) {
        this.ataReuniaoDown = ataReuniaoDown;
    }
    
}
