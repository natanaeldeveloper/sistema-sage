//package br.com.projeto.estoque.controller;
//
//import java.util.List;
//
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableModel;
//
//import br.com.projeto.estoque.model.Gerente;
//import br.com.projeto.estoque.model.RegistroGerente;
//import br.com.projeto.estoque.util.Essencial;
//import br.com.projeto.estoque.util.JPAUtil;
//
//public class ControllerTablesModels {
//	
//	public ControllerTablesModels (JTable table_registros, JTable table_gerente) {
//		Essencial.setManager(new JPAUtil().getEntityManager());
//		Essencial.getManager().getTransaction().begin();
//		popularTableRegistrosGerente(table_registros);
//		popularTableGerente(table_gerente);
//		Essencial.getManager().getTransaction().commit();
//		Essencial.getManager().close();		
//	}
//	
//	public void popularTableRegistrosGerente (JTable table_registros) {
//
//		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarRegistrosGerentes"));
//		List<RegistroGerente> registrosGerente;
//		registrosGerente = Essencial.getQuery().getResultList();
//		
//		DefaultTableModel modelo = new DefaultTableModel();
//		table_registros.setModel(modelo);
//		modelo.addColumn("id");
//		modelo.addColumn("gerente");
//		modelo.addColumn("entrada/sa�da");
//		modelo.addColumn("data e hora");
//		
//		for(RegistroGerente registro : registrosGerente) {
//			modelo.addRow(
//					new Object[] {
//							registro.getId(),
//							registro.getGerente().getId(),
//							registro.getTipoComportamento(),
//							registro.getDataEHora()
//					}
//			);
//		}
//		
//	}
//	
//	public void popularTableGerente (JTable table_gerente) {
//
//		Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarGerentes"));
//		List<Gerente> registrosGerente;
//		registrosGerente = Essencial.getQuery().getResultList();
//		
//		DefaultTableModel modelo = new DefaultTableModel();
//		table_gerente.setModel(modelo);
//		modelo.addColumn("id");
//		modelo.addColumn("CPF");
//		
//		for(Gerente registro : registrosGerente) {
//			modelo.addRow(
//					new Object[] {
//							registro.getId(),
//							registro.getCpf(),
//					}
//			);
//		}
//		
//	}
//	
//}
