package br.com.projeto.estoque.testes;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;

public class d {
	public static void main(String[] args) throws JRException {
		//Obtem o valor atual do sistema
		long inicioContagem = System.currentTimeMillis();
		
		//Compilacao no formato jasper para o jrprint
		JasperFillManager.fillReportToFile("reports/relatorioTeste.jasper", null, new JREmptyDataSource(1));
		System.err.println("Tempo de compilacao jasper -> jrprint: " + (System.currentTimeMillis() - inicioContagem));
		
		//Reinicia o contador
		inicioContagem = System.currentTimeMillis();
		
		//Geracao do PDF
		JasperExportManager.exportReportToPdfFile("reports/relatorioTeste.jrprint");
		System.err.println("Tempo de geracao do PDF: " + (System.currentTimeMillis() - inicioContagem));
	}
}
