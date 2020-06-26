package br.com.projeto.estoque.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ControllerGrupo {
	private static EntityManager manager;
	private static Random rand;

	// Método para cadastrar novos Grupos
	public Grupo cadastrarGrupo(String nome, String descricao, Integer id_categoria, int qtdMinima, int qtdMaxima,
			JComboBox[] comboBoxGrupos) {
		manager = new JPAUtil().getEntityManager();
		rand = new Random();

		Categoria c = manager.find(Categoria.class, id_categoria);

		Grupo grupo = new Grupo();
		grupo.setNome(nome);
		grupo.setDescricao(descricao);
		grupo.setCategoria(c);
		// O subtotal não é definido até que um Produto desse Grupo seja cadastrado
		grupo.setQtdMinima(qtdMinima);
		grupo.setQtdMaxima(qtdMaxima);
		// Quando um Grupo é inicialmente inserido no banco, ele não possui nenhum
		// Produto associado, então ainda não está "estocado"
		grupo.setEstocado(false);

		// O código do Grupo é gerado de forma randômica, em hexadecimal
		int valorRandomico = rand.nextInt(0x1000000);
		String codigoGerado = Integer.toHexString(valorRandomico);

		grupo.setCodigo(codigoGerado);

		manager.getTransaction().begin();
		manager.persist(grupo);
		manager.getTransaction().commit();

		JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!", "Grupo cadastrado",
				JOptionPane.INFORMATION_MESSAGE);
		ControllerValidationGrupo.grupoCadastrado = true;

		// Depois de o grupo ser cadastrado, ele é adicionado na JComboBox da view de
		// CadastrarProdutos
		comboBoxGrupos[0].addItem(grupo.getNome());
		comboBoxGrupos[1].addItem(grupo.getNome());
		comboBoxGrupos[2].addItem(grupo.getNome());

		manager.close();
		return grupo;
	}

	// Método para listar todos os grupos, retorna uma List<Grupo>
	public static List<Grupo> listarGrupos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select g from Grupo g");
		List<Grupo> grupos = query.getResultList();
		manager.close();
		return grupos;
	}

	// Esse método busca o nome do Grupo baseado no id do Produto inserido
	public static Object encontrarGrupoPeloProduto(Integer id) {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select grupo.nome from Produto p where p.id=:idProduto");
		query.setParameter("idProduto", id);
		Object grupo = query.getSingleResult();
		manager.close();
		return grupo;
	}

	// Método para encontrar um único grupo
	public Grupo encontrarGrupoPeloCodigo(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Grupo grupoEncontrado = manager.find(Grupo.class, id);
		manager.close();
		return grupoEncontrado;
	}

	// Método para checar se já não existe um grupo com o mesmo nome
	public static boolean checarGrupo(String nome) {
		for (Grupo grupo : listarGrupos()) {
			if (grupo.getNome().equals(nome)) {
				JOptionPane.showMessageDialog(null, "Esse grupo já existe!\nID: " + grupo.getId(), "Grupo existente",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	// Método que checa os Grupos não estocados, e que também define como não
	// estocado o Grupo que tiver seu subtotal igual a 0
	public static void checarEstocado() {
		manager = new JPAUtil().getEntityManager();

		Query query = manager.createQuery("select g from Grupo g where g.subtotal = 0");

		List<Grupo> grupos = query.getResultList();

		if (grupos.size() > 0) {

			List<String> nomesDosGrupos = new ArrayList();

			for (Grupo grupo : grupos) {
				grupo.setEstocado(false);
				manager.getTransaction().begin();
				manager.merge(grupo);
				manager.getTransaction().commit();
				nomesDosGrupos.add(grupo.getNome() + "\n");
			}

			JOptionPane.showMessageDialog(null,
					"Lista de Grupos não estocados:" + nomesDosGrupos.toString().replace("[", "\n ").replace(",", "")
							.replace("]", "Abasteça-os assim que possível!\n"),
					"Grupos não estocados", JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Todos os Grupos cadastrados estão estocados!", "Grupos estocados",
					JOptionPane.INFORMATION_MESSAGE);
		}
		manager.close();
	}

	// Método para checar se há algum problema de quantidade em algum grupo
	public static boolean problemasNoEstoque() {
		List<String> estoquesPrecarios = new ArrayList();
		List<String> estoquesCheios = new ArrayList();

		for (Grupo grupo : listarGrupos()) {
			if (grupo.getSubtotal() < grupo.getQtdMinima()) {

				estoquesPrecarios.add("ID: " + grupo.getId() + " - Quantidade atual: " + grupo.getSubtotal()
						+ " - Quantidade mínima: " + grupo.getQtdMinima() + "\n");
			}

			if (grupo.getSubtotal() == grupo.getQtdMaxima()) {
				estoquesCheios.add(("ID: " + grupo.getId() + " - Quantidade atual: " + grupo.getSubtotal()
						+ " - Quantidade máxima: " + grupo.getQtdMaxima() + "\n"));
			}
		}

		if (estoquesPrecarios.size() > 0 || estoquesCheios.size() > 0) {
			if (estoquesPrecarios.size() > 0) {
				JOptionPane.showMessageDialog(null,
						"Grupos com estoque abaixo do mínimo:\n\n" + estoquesPrecarios.toString().replace("[", " ")
								.replace(",", "").replace("]", "\nReabasteça-os assim que possível!"),
						"Estoques escassos", JOptionPane.WARNING_MESSAGE);
			}

			if (estoquesCheios.size() > 0) {
				JOptionPane.showMessageDialog(null,
						"Grupos com estoque lotado:\n\n" + estoquesCheios.toString().replace("[", " ").replace(",", "")
								.replace("]", "\nRisco de mercadoria naufragada!"),
						"Estoques cheios", JOptionPane.WARNING_MESSAGE);
			}

			return true;
		}

		JOptionPane.showMessageDialog(null, "Os estoques dos grupos estão estáveis!", "Grupos em dia",
				JOptionPane.INFORMATION_MESSAGE);
		return false;
	}
}
