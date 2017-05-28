package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

/**
 * @author Rafael Lopes Escobar <br/>
 *         Class Util para gerar Relatorios.<br/>
 *         Pode ser exibido no browser
 * @version 1.0
 * @date 27/01/2014
 */

public class RelatorioUtil {

	/**
	 * Este m�todo n�o faz o download do arquivo, o pdf � interpretado
	 * diretamente no browser
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void gerarRelatorio(String nome, Collection lista, Map parametros, String caminho) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/* Coloca o tipo que o browser deve interpretar */
		response.sendRedirect("../relatorios/"+nome);

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);

		/* Cria a lista do relatorio */
		JRDataSource colecao = new JRBeanCollectionDataSource(lista == null ? new ArrayList() : lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros, colecao);
		
		FileOutputStream outputStream = new FileOutputStream(context.getExternalContext().getRealPath("relatorios")+FileUtil.SEPARATOR+nome);
		
		JRExporter jrExporter = new JRPdfExporter();
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	/**
	 * 
	 * @param lista
	 * @param parametros
	 * @param caminho
	 * @param nomePDF
	 * @throws JRException
	 * @throws IOException
	 * @funcionalidade: Gerar relatorio com lista e parametos
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void gerarRelatorio(Collection lista, Map parametros, String caminho, String nomePDF) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/** Coloca o nome do PDF e o Tipo que o browser deve interpretar */
		response.setContentType("application/pdf");
		// Faz realizar o download, se remover a linha abaixo o arquivo � exibido diretamento no browser.
		response.setHeader("Content-Disposition", "attachment; filename=" + nomePDF);

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);

		/* Cria a lista do relatorio */
		JRDataSource colecao = new JRBeanCollectionDataSource(lista == null ? new ArrayList() : lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros, colecao);
		ServletOutputStream outputStream = response.getOutputStream();
		JRExporter jrExporter = new JRPdfExporter();
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	/**
	 * 
	 * @param lista
	 * @param parametros
	 * @param caminho
	 * @param nomePDF
	 * @throws JRException
	 * @throws IOException
	 * @funcionalidade: Gerar relatorio com parametos
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void gerarRelatorio(Map parametros, String caminho, String nomePDF) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/* Coloca o nome do PDF e o Tipo que o browser deve interpretar */

		response.setHeader("Content-Disposition", "attachment; filename=" + nomePDF);
		response.setContentType("application/pdf");

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);

		/* Quando nao passa a lista da erro */
		/* Para resolver vou criar uma lista sem nada */
		Collection lista = new ArrayList();
		lista.add(new Object());
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros);
		ServletOutputStream outputStream = response.getOutputStream();
		JRExporter jrExporter = new JRPdfExporter();
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();

	}

	/**
	 * 
	 * @param lista
	 * @param parametros
	 * @param caminho
	 * @param nomePDF
	 * @throws JRException
	 * @throws IOException
	 * @funcionalidade: Gerar relatorio com Lista
	 */
	@SuppressWarnings("rawtypes")
	public static void gerarRelatorio(Collection lista, String caminho, String nomePDF) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/* Coloca o nome do PDF e o Tipo que o browser deve interpretar */
		response.setHeader("Content-Disposition", "attachment; filename=" + nomePDF);
		response.setContentType("application/pdf");

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);

		/* Cria a lista do relatorio */
		JRDataSource dataSource = new JRBeanCollectionDataSource(lista == null ? new ArrayList() : lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, new HashMap<String, Object>(), dataSource);
		ServletOutputStream outputStream = response.getOutputStream();
		JRExporter jrExporter = new JRPdfExporter();
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	/**
	 * 
	 * @param lista
	 * @param parametros
	 * @param caminho
	 * @param nomePDF
	 * @throws JRException
	 * @throws IOException
	 * @funcionalidade: Gerar relatorio
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void gerarRelatorio(String caminho, String nomePDF) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/* Coloca o nome do PDF e o Tipo que o browser deve interpretar */
		response.setHeader("Content-Disposition", "attachment; filename=" + nomePDF);
		response.setContentType("application/pdf");

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);
		/* Quando nao passa a lista da erro */
		/* Para resolver vou criar uma lista sem nada */
		Collection lista = new ArrayList();
		lista.add(new Object());
		JRDataSource colecao = new JRBeanCollectionDataSource(lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, new HashMap<String, Object>(), colecao);
		ServletOutputStream outputStream = response.getOutputStream();
		JRExporter jrExporter = new JRPdfExporter();
		jrExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "deprecation" })
	public static void gerarRelatorioHtml(Collection lista, Map parametros, String caminho) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

		/* Coloca o nome do PDF e o Tipo que o browser deve interpretar */
		response.setContentType("text/html");

		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);

		/* Cria a lista do relatorio */
		JRDataSource colecao = new JRBeanCollectionDataSource(lista == null ? new ArrayList() : lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros, colecao);
		ServletOutputStream outputStream = response.getOutputStream();
		JRHtmlExporter jrHtmlExporter = new JRHtmlExporter();
		jrHtmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jrHtmlExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		jrHtmlExporter.setParameter(JRHtmlExporterParameter.SIZE_UNIT, JRHtmlExporterParameter.SIZE_UNIT_POINT);
		jrHtmlExporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES, Boolean.TRUE);
		jrHtmlExporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/image.servlet?image=");
		jrHtmlExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

	public static void exportarTxt(String texto, String nomeTxt) throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

		/* Coloca o nome do txt e o Tipo que o browser deve interpretar */
		response.setHeader("Content-Disposition", "attachment; filename=" + nomeTxt);
		response.setContentType("application/txt");

		ServletOutputStream outputStream = response.getOutputStream();

		PrintStream output = new PrintStream(outputStream);
		output.print(texto);
		output.flush();
		output.close();
		context.renderResponse();
		context.responseComplete();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void exportarXls(Collection lista, Map parametros, String caminho, String nomeXls) throws JRException, IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		/* Cria o response */
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment; filename=" + nomeXls);
		/* Coloca o nome do PDF e o Tipo que o browser deve interpretar */
		response.setContentType("text/html");
		/* Cria o Stream com o caminho */
		InputStream stream = context.getExternalContext().getResourceAsStream(caminho);
		ServletOutputStream outputStream = response.getOutputStream();

		JRDataSource colecao = new JRBeanCollectionDataSource(lista == null ? new ArrayList() : lista);
		JasperPrint jasperPrint = JasperFillManager.fillReport(stream, parametros, colecao);

		JRXlsxExporter jRXlsxExporter = new JRXlsxExporter();
		jRXlsxExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		jRXlsxExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
		
		jRXlsxExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);     
		jRXlsxExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);     
		jRXlsxExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE); 
		
		jRXlsxExporter.exportReport();
		outputStream.flush();
		outputStream.close();
		context.renderResponse();
		context.responseComplete();
	}

}
