package br.com.projeto.estoque.teste;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class Relatorio {

	public static void gerarRelatorio(String nomeRelatorio, String nomeArquivo) {
		String jasper = "C:\\Users\\andre\\JaspersoftWorkspace\\TecFour/" + nomeRelatorio + ".jasper";

		byte[] bytes = null;
		JasperReport relatorio;
		try {
			relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);

			File arq = new File("C:\\Users\\andre\\Desktop", nomeArquivo + ".pdf");
			try {
				bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (arq.exists()) {
				arq.delete();
			}
			FileOutputStream fos;
			try {
				fos = new FileOutputStream(arq);
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
