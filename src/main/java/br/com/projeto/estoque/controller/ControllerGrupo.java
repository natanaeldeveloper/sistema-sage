package br.com.projeto.estoque.controller;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerGrupo {
	private static EntityManager manager;
	private static Random rand;

	// Método para cadastrar novos Grupos
	public Grupo cadastrarGrupo(String nome, String descricao, Integer id_categoria, Double medida, String unidade) {
		manager = new JPAUtil().getEntityManager();
		rand = new Random();

		Categoria c = manager.find(Categoria.class, id_categoria);

		Grupo grupo = new Grupo();
		grupo.setNome(nome);
		grupo.setDescricao(descricao);
		grupo.setCategoria(c);
		grupo.setMedida(medida);
		grupo.setUnidade(unidade);
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
	
	public static Object encontrarGrupoPeloProduto(Integer id) {
		manager = new JPAUtil().getEntityManager();
		Query query  = manager.createQuery("select grupo.nome from Produto p where p.id=:idProduto");
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

	// Método para listar todos os grupos que não estão com "estocado" = true
	public static List<Grupo> listarGruposNaoEstocados() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select g from Grupo g where g.estocado = 0");
		List<Grupo> gruposNaoEstocados = query.getResultList();
		manager.close();
		return gruposNaoEstocados;
	}

	// Método para atualizar um grupo. Os dados alteráveis são limitados à descrição
	// e à categoria
	public Grupo atualizarGrupo(Integer id, String descricao, Integer id_categoria) {
		manager = new JPAUtil().getEntityManager();

		Categoria categoria = manager.find(Categoria.class, id_categoria);

		Grupo grupoAtualizado = manager.find(Grupo.class, id);
		grupoAtualizado.setDescricao(descricao);
		grupoAtualizado.setCategoria(categoria);
		manager.getTransaction().begin();
		manager.merge(grupoAtualizado);
		manager.getTransaction().commit();

		manager.close();
		return grupoAtualizado;
	}
}
