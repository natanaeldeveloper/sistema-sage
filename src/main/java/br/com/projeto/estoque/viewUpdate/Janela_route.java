package br.com.projeto.estoque.viewUpdate;


import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Janela_route {
	public String route(File arq) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setDialogTitle("Escolha um local para downloand");
		jfc.setAcceptAllFileFilterUsed(false);
//		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		jfc.setSelectedFile(arq);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "Relatorio.pdf");
		jfc.addChoosableFileFilter(filter);

		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			System.out.println(jfc.getSelectedFile().getPath());
		}
		return jfc.getSelectedFile().getPath();
	}
	public static void main(String[] args) {
	}

}