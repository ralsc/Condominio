package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Rafael Lopes Escobar <br/>
 *         Class Util para manipula��o de pastas e arquivos.<br/>
 * @version 1.0
 * @date 09/06/2014
 */

public class FileUtil {
	
	public static final String SEPARATOR = System.getProperty("file.separator");
	
	/**
	 * M�todo utilizado para criar os arquivos .txt do resultado de importa��o na pasta do projeto
	 * @throws IOException 
	 */
	public static void createFileTxt(String texto) throws IOException {
		// Cria a pasta ResultadoImportacao na pasta raiz do JBOSS
        File diretorio = new File(".."+SEPARATOR+"ArquivosGerados");  
        
        // Verifica se diret�rio existe, se n�o existir o mesmo � criado
        if  (!diretorio.exists() ) {  
          diretorio.mkdir();  
        }
		String data = new SimpleDateFormat("dd-MM-yyyy  HH-mm").format(new Date());
		String nomeArq = "textoGerado "+data+".txt";
		
		FileWriter writer;
		writer = new FileWriter(new File(diretorio+SEPARATOR+nomeArq));
		writer.write(texto.toString());
		writer.flush();
		writer.close();
	}
}
