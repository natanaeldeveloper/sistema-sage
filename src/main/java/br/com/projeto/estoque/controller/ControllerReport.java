package br.com.projeto.estoque.controller;

import java.awt.Desktop;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.projeto.estoque.util.Essencial;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class ControllerReport {
	public static void generateManagerReport() throws IOException {
		String jasper = "C:\\Users\\leand\\JaspersoftWorkspace\\gerente\\RelatorioGerente.jasper";
		byte[] bytes = null;
		try {
			JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper) ;
			bytes = JasperRunManager.runReportToPdf(relatorio, null, Essencial.getConnection());
			
			File arq = new File("C:\\Users\\leand\\WorkSpace\\tecfouur\\Reports", "reportManager.pdf");
			FileOutputStream fos = new FileOutputStream(arq);
			fos.write(bytes);
			fos.flush();
			fos.close();
			Desktop desktop = Desktop.getDesktop();
			desktop.open(arq);
		} catch (JRException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro");
		}
	}
}
