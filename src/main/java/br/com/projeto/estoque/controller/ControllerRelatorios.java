package br.com.projeto.estoque.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import br.com.projeto.estoque.util.Banco;
import br.com.projeto.estoque.view.Janela_route;
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

	public void gerarRelatorioProdutos(JTextField tfNome) {
		try {
			if (StringUtils.isBlank(tfNome.getText())) {
				JOptionPane.showMessageDialog(null, "O nome precisa ser preenchido!", "Nome inválido",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				String nome = tfNome.getText();

				jasper = new File("jasper/relatorio_produtos.jasper").getCanonicalPath();
				relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
				arq = new File(nome + ".pdf");
				caminho = jr.route(arq);
				if (caminho != null) {
					arquivo = new File(caminho);
					bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
					if (arquivo.exists()) {
						arquivo.delete();
					}
					fos = new FileOutputStream(arquivo);
					fos.write(bytes);
					fos.flush();
					fos.close();

					JOptionPane.showMessageDialog(null, "Relatório criado com sucesso!", "Relatório criado",
							JOptionPane.INFORMATION_MESSAGE);

					tfNome.setText("");
				}
			}
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void gerarRelatorioMovimentacoes(JTextField tfNome) {
		try {
			if (StringUtils.isBlank(tfNome.getText())) {
				JOptionPane.showMessageDialog(null, "O nome precisa ser preenchido!", "Nome inválido",
						JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				String nome = tfNome.getText();

				jasper = new File("jasper/relatorio_movimentacoes.jasper").getCanonicalPath();
				relatorio = (JasperReport) JRLoader.loadObjectFromFile(jasper);
				arq = new File(nome + ".pdf");
				caminho = jr.route(arq);
				if (caminho != null) {
					arquivo = new File(caminho);
					bytes = JasperRunManager.runReportToPdf(relatorio, null, new Banco().getConnection());
					if (arquivo.exists()) {
						arquivo.delete();
					}
					fos = new FileOutputStream(arquivo);
					fos.write(bytes);
					fos.flush();
					fos.close();

					JOptionPane.showMessageDialog(null, "Relatório criado com sucesso!", "Relatório criado",
							JOptionPane.INFORMATION_MESSAGE);

					tfNome.setText("");
				}
			}
		} catch (JRException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
