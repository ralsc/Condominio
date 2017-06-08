package bean;

import dao.RelatoriosDAO;
import entidades.TipoTaxaMulta;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import util.FacesMessagesUtil;
import util.RelatorioUtil;

@ManagedBean(name = "relReciboBean")
@ViewScoped
public class RelReciboBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tipo;
    
    public void gerarRelatorio() throws Exception {
        try {			
            List<TipoTaxaMulta> dataSource = RelatoriosDAO.listChamadaCapitalorMulta(this.getTipo());

            Map<String, Object> parametros = new HashMap<>();
//            parametros.put("TIPO_RELATORIO", this.getTipo());
//            // Logo
//            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
//            InputStream logo = ec.getResourceAsStream("/img/Townhouse_Logo.jpeg");
//            parametros.put("LOGO", logo);

            String url = "/jasper/MA_Recibo.jasper";

            RelatorioUtil.gerarRelatorio("Recibo.pdf", dataSource, parametros, url);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessagesUtil.addErrorMessage("msgs","msgs","Erro ao salvar!\n"+e.getMessage());
        }
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}