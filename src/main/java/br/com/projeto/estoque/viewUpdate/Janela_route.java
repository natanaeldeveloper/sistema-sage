package br.com.projeto.estoque.viewUpdate;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class Janela_route {
	public String route(File arq) {
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		jfc.setAcceptAllFileFilterUsed(false);
		jfc.setDialogTitle("Escolha um local para salvar o arquivo");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Formato PDF", ".pdf");
		jfc.addChoosableFileFilter(filter);
		jfc.setSelectedFile(arq);
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			return jfc.getSelectedFile().getPath();
		} else {
			return null;
		}
	}
}