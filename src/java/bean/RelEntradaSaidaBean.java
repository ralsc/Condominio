package bean;

import dao.RelatoriosDAO;
import dto.EntradaSaidaDTO;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import util.FacesMessagesUtil;
import util.RelatorioUtil;

@ManagedBean(name = "relEntradaSaidaBean")
@ViewScoped
public class RelEntradaSaidaBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String movimento;
    private Date dataInicial;
    private Date dataFinal;
    
    public void gerarRelatorio() throws Exception {
        try {			
            List<EntradaSaidaDTO> dataSource = RelatoriosDAO.listEntradaSaida(this.getMovimento(),
                this.getDataInicial(), this.getDataFinal());

            Map<String, Object> parametros = new HashMap<>();

            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            // Logo
            InputStream logo = ec.getResourceAsStream("/img/Townhouse_Logo.jpeg");
            parametros.put("LOGO", logo);

            String url = "/jasper/MA_EntradaSaida.jasper";

            RelatorioUtil.gerarRelatorio("EntradaSaida.pdf", dataSource, parametros, url);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+e.getMessage());
        }
    }

    public String getMovimento() {
        return movimento;
    }

    public void setMovimento(String movimento) {
        this.movimento = movimento;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }
}