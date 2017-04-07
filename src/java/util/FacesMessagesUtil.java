package util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Classe utilitário para adição de mensagens na pilha de mensagens do FacesContext.
 *
 */
public class FacesMessagesUtil {
    
    private static void addMessage(String clientId, FacesMessage.Severity severity, String summary, String detail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(clientId, new FacesMessage(severity, summary, detail));
    }
    
    private static void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        addMessage(null, severity, summary, detail);
    }
    
    /**
     * Adiciona mensagem de erro
     * @param clientId
     * @param summary
     * @param detail
     */
    public static void addErrorMessage(String clientId, String summary, String detail) {
        addMessage(clientId, FacesMessage.SEVERITY_ERROR, summary, detail);
    }
    
    /**
     * Adiciona mensagem de erro
     * @param summary
     * @param detail
     */
    public static void addErrorMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
    }
    
    /**
     * Adiciona mensagem de erro
     * @param detail
     */
    public static void addErrorMessage(String detail) {
        addMessage(FacesMessage.SEVERITY_ERROR, null, detail);
    }
    
    /**
     * Adiciona mensagem de informação
     * @param clientId
     * @param summary
     * @param detail
     */
    public static void addInfoMessage(String clientId, String summary, String detail) {
        addMessage(clientId, FacesMessage.SEVERITY_INFO, summary, detail);
    }
    
    /**
     * Adiciona mensagem de informação
     * @param summary
     * @param detail
     */
    public static void addInfoMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
    }
    
    /**
     * Adiciona mensagem de informação
     * @param detail
     */
    public static void addInfoMessage(String detail) {
        addMessage(FacesMessage.SEVERITY_INFO, null, detail);
    }
    
    /**
     * Adiciona mensagem de erro fatal (erros sem possibilidade de recuperação da aplicação)
     * @param clientId
     * @param summary
     * @param detail
     */
    public static void addFatalMessage(String clientId, String summary, String detail) {
        addMessage(clientId, FacesMessage.SEVERITY_FATAL, summary, detail);
    }
    
    /**
     * Adiciona mensagem de erro fatal (erros sem possibilidade de recuperação da aplicação)
     * @param summary
     * @param detail
     */
    public static void addFatalMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
    }
    
    /**
     * Adiciona mensagem de erro fatal (erros sem possibilidade de recuperação da aplicação)
     * @param detail
     */
    public static void addFatalMessage(String detail) {
        addMessage(FacesMessage.SEVERITY_FATAL, null, detail);
    }
    
    /**
     * Adiciona mensagem de alerta
     * @param clientId
     * @param summary
     * @param detail
     */
    public static void addWarnlMessage(String clientId, String summary, String detail) {
        addMessage(clientId, FacesMessage.SEVERITY_WARN, summary, detail);
    }
    
    /**
     * Adiciona mensagem de alerta
     * @param summary
     * @param detail
     */
    public static void addWarnlMessage(String summary, String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, summary, detail);
    }
    
    /**
     * Adiciona mensagem de alerta
     * @param detail
     */
    public static void addWarnlMessage(String detail) {
        addMessage(FacesMessage.SEVERITY_WARN, null, detail);
    }
    
}