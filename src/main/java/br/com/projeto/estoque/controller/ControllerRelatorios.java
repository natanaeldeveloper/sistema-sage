package br.com.projeto.estoque.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.projeto.estoque.util.Banco;
import br.com.projeto.estoque.viewUpdate.Janela_route;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

public class ControllerRelatorios {
	Janela_route jr = new Janela_route();
	String jasper = null;
	byte[] bytes = null;
	JasperReport relatorio;
	File arq;
	String caminho;
	File arquivo;
	FileOutputStream fos;
	
	public void gerarRelatorioProdutos(String nome) {	
		try {
			jasper = new File("jasper/relatorio_produtos.jasper").getCanonicalPath();
			relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
			arq = new File(nome + ".pdf");
			caminho = jr.route(arq);
			arquivo = new File(caminho);
			bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
			if (arquivo.exists()) {
				arquivo.delete();
			}
			fos = new FileOutputStream(arquivo);
			fos.write(bytes);
			fos.flush();
			fos.close();
		} catch (JRException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Relatório criado com sucesso!", "Relatório criado",JOptionPane.INFORMATION_MESSAGE);
		
	}
//	public void gerarRelatorioMovimentacoes(String nome) {	
//		try {
//			jasper = new File("jasper/relatorio_movimentacoes.jasper").getCanonicalPath();
//			relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
//			arq = new File(nome + ".pdf");
//			caminho = jr.route(arq);
//			arquivo = new File(caminho);
//			bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
//			if (arquivo.exists()) {
//				arquivo.delete();
//			}
//			fos = new FileOutputStream(arquivo);
//			fos.write(bytes);
//			fos.flush();
//			fos.close();
//		} catch (JRException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		JOptionPane.showMessageDialog(null, "Relatório criado com sucesso!", "Relatório criado",JOptionPane.INFORMATION_MESSAGE);
//		
//	}
}
