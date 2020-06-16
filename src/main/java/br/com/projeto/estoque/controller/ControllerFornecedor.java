package br.com.projeto.estoque.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.estoque.model.Endereco;
import br.com.projeto.estoque.model.Fornecedor;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.util.JPAUtil;

@SuppressWarnings("unchecked")
public class ControllerFornecedor {
	private static EntityManager manager;

	//Método para criar fornecedores
	public Fornecedor criarFornecedor(String nome, String cnpj, String razaoSocial, String telefone, String email,
			Endereco endereco) {
		manager = new JPAUtil().getEntityManager();

		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj(cnpj);
		fornecedor.setNome(nome);
		fornecedor.setEmail(email);
		//O endereço precisa ser criado previamente
		fornecedor.setEndereco(endereco);
		fornecedor.setRazaoSocial(razaoSocial);
		fornecedor.setTelefone(telefone);
		//Quando um fornecedor é cadastrado no banco, ele já entra definido como ATIVO
		fornecedor.setStatus(Status.ATIVO);

		manager.getTransaction().begin();
		manager.persist(fornecedor);
		manager.getTransaction().commit();

		manager.close();
		return fornecedor;
	}

	// Método para listar todos os Fornecedores, retorna uma List<Fornecedor>
	public static List<Fornecedor> listarFornecedores() {
		manager = new JPAUtil().getEntityManager();
		Query query = manager.createQuery("select f from Fornecedor f");
		List<Fornecedor> fornecedores = query.getResultList();
		manager.close();
		return fornecedores;
	}

	// Método para atualizar um Fornecedor
	public void atualizarFornecedor(Integer id, String cnpj, String nome, String email, String razaoSocial,
			String telefone, Endereco endereco) {
		manager = new JPAUtil().getEntityManager();
		Fornecedor fornecedorAtualizado = manager.find(Fornecedor.class, id);
		fornecedorAtualizado.setCnpj(cnpj);
		fornecedorAtualizado.setNome(nome);
		fornecedorAtualizado.setEmail(email);
		fornecedorAtualizado.setRazaoSocial(razaoSocial);
		fornecedorAtualizado.setTelefone(telefone);
		fornecedorAtualizado.setEndereco(endereco);
		manager.getTransaction().begin();
		manager.merge(fornecedorAtualizado);
		manager.getTransaction().commit();
		manager.close();
	}

	//Método para inativar um fornecedor
	public void inativarFornecedor(Integer id) {
		manager = new JPAUtil().getEntityManager();
		Fornecedor fornecedorInativo = manager.find(Fornecedor.class, id);
		fornecedorInativo.setStatus(Status.INATIVO);
		manager.getTransaction().begin();
		manager.merge(fornecedorInativo);
		manager.getTransaction().commit();
		manager.close();
	}
}
