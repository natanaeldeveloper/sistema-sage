package br.com.projeto.estoque.controller;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Categoria;
import br.com.projeto.estoque.model.Grupo;
import br.com.projeto.estoque.util.JPAUtil;
import br.com.projeto.estoque.util.RecursoNaoEncontradoException;

@SuppressWarnings("unchecked")
public class ControllerGrupo {
	private static EntityManager manager;
	private static Random rand;

	public Grupo cadastrarGrupo(String nome, String descricao, Integer id_categoria, Double peso, String unidade) {
		manager = new JPAUtil().getEntityManager();
		rand = new Random();

		Categoria c = manager.find(Categoria.class, id_categoria);

		Grupo grupo = new Grupo();
		grupo.setNome(nome);
		grupo.setDescricao(descricao);
		grupo.setCategoria(c);
		grupo.setPeso(peso);
		grupo.setUnidade(unidade);
		grupo.setEstocado(false);

		int valorRandomico = rand.nextInt(0x1000000);
		String codigoGerado = Integer.toHexString(valorRandomico);

		grupo.setCodigo(codigoGerado);

		manager.getTransaction().begin();
		manager.persist(grupo);
		manager.getTransaction().commit();

		manager.close();
		return grupo;
	}

	public Grupo deletarGrupo(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Grupo grupoDeletado = manager.find(Grupo.class, id);

		try {
			if (grupoDeletado.getCodigo().equals(id)) {
				manager.getTransaction().begin();
				manager.remove(grupoDeletado);
				manager.getTransaction().commit();
				manager.close();
			}
		} catch (Exception e) {
			throw new RecursoNaoEncontradoException();
		}
		manager.close();
		return null;
	}

	public Grupo encontrarGrupoPeloCodigo(Integer id) {
		manager = new JPAUtil().getEntityManager();

		Grupo grupoEncontrado = manager.find(Grupo.class, id);

		try {
			if (grupoEncontrado.getCodigo() != null) {

				Query query = manager.createQuery("select g from Grupo g where g.id=:idGrupo");
				query.setParameter("idGrupo", grupoEncontrado.getCodigo());

				List<Grupo> grupos = query.getResultList();

				for (Grupo grupo : grupos) {
					System.out.println(grupo.getNome());
				}
			}
		} catch (Exception e) {
			throw new RecursoNaoEncontradoException();
		}
		manager.close();
		return null;
	}

	public List<Grupo> encontrarGruposNaoEstocados() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select g from Grupo g where g.estocado = 0");
		List<Grupo> grupos = query.getResultList();
		manager.close();
		return grupos;
	}

	public List<Grupo> encontrarTodosOsGrupos() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select g from Grupo g");
		List<Grupo> grupos = query.getResultList();
		manager.close();
		return grupos;
	}
}
