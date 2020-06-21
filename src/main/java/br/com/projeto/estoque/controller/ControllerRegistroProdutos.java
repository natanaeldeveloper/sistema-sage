package br.com.projeto.estoque.controller;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JTextField;
import br.com.projeto.estoque.util.ConectionJDBC;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class ControllerRegistroProdutos {
	
	public void gerarRegistros(String nome) {
		ConectionJDBC cc = new ConectionJDBC();
		//String erro = "";
		String empresa = nome;
		Connection con =  cc.getConnectionJDBC();
		HashMap<String,Object> param = new HashMap<String,Object>();
		String jasper = "C:\\Users\\Natanael Oliveira\\projects eclipse\\project_SAGE\\src\\main\\java\\model_registros\\relatorio_produto.jasper";
		param.put("EMPRESA",empresa);
		byte[] bytes = null;
		try {
			JasperReport relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
			bytes = JasperRunManager.runReportToPdf(relatorio,param,con);
			
			File arq = new File("C:\\Users\\Natanael Oliveira\\projects eclipse\\project_SAGE\\src\\main\\java\\model_registros\\",empresa+".pdf");
			if(arq.exists()) {
				arq.delete();
			}
			FileOutputStream fos = new FileOutputStream(arq);
			fos.write(bytes);
			fos.flush();
			fos.close();
			Desktop desk = Desktop.getDesktop();
			desk.open(arq);
		}catch(Exception e) {}
			
	}
}
