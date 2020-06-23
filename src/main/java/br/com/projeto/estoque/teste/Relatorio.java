package br.com.projeto.estoque.teste;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.projeto.estoque.viewUpdate.Janela_route;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class Relatorio {

	public static void gerarRelatorio(String nomeArquivo) {
//		String jasper = "C:\\Users\\Natanael Oliveira\\projects eclipse\\project_SAGE/jasper/relatorio_produtos.jasper";
		String jasper = null;
		try {
			jasper = new File("jasper/relatorio_produtos.jasper").getCanonicalPath();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		byte[] bytes = null;
		JasperReport relatorio;
		try {
			relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
			
			File arq = new File(nomeArquivo + ".pdf");
			Janela_route jr = new Janela_route();
			String caminho = jr.route(arq);
			File arquivo = new File(caminho);
			
			try {
				bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (arquivo.exists()) {
				arquivo.delete();
			}
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(arquivo);
				fos.write(bytes);
				fos.flush();
				fos.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Relatório criado com sucesso!", "Relatório criado",
				JOptionPane.INFORMATION_MESSAGE);
		
	}
}
