package br.com.projeto.estoque.controller;

import javax.persistence.NoResultException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import br.com.projeto.estoque.model.Nivel;
import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.Supervisor;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.JPAUtil;
import br.com.projeto.estoque.util.SupervisorAtual;
import br.com.projeto.estoque.util.SupervisorDeletado;
import br.com.projeto.estoque.view.Janela_confirmar_delecao;

public class ControllerSupervisor extends ControllerGlobal {

	ControllerRegistroSupervisor controller = new ControllerRegistroSupervisor();

	public boolean fazerLogin(String cpf, String senha, TipoComportamento tipoComportamento) {
		boolean login = false;
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (verificarCredenciais(cpf, senha) == true) {
			SupervisorAtual.setSupervisor(buscarSupervisorPeloCpf(cpf));
			controller.criarRegistroSupervisor(tipoComportamento, SupervisorAtual.getSupervisor());
			login = true;
		} else {
			login = false;
		}
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
		return login;
	}

	// Verifica se os dados digitados nos campos de login do Supervisor estï¿½o de
	// acordo com o banco de dados

	public boolean verificarCredenciais(String cpf, String senha) {

		Supervisor supervisor = new Supervisor();
		supervisor = buscarSupervisorPeloCpf(cpf);

		if (supervisor == null) {
			Aviso.avisar(2);
			return false;
		} else {
			if (supervisor.getSenha().equals(Criptografar.encriptografar(senha))) {
				return true;
			} else {
				Aviso.avisar(2);
				return false;
			}
		}
	}

	// Cria um objeto do tipo supervisor

	public Supervisor criarSupervisor(String cpf, String senha) {

		Supervisor supervisor = new Supervisor();
		supervisor.setCpf(cpf);
		supervisor.setSenha(senha);
		supervisor.setStatus(Status.ATIVO);

		return supervisor;

	}

	public void cadastrarSupervisor(String cpf, String senha, String confSenha, JFormattedTextField campoCpf,
			JPasswordField campoSenha, JPasswordField campoConfSenha) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (testarCampos(cpf, senha) == true) {
			if (evitarDuplicacaoSupervisor(cpf) == false) {
				// cpf já estará no sistema
			} else {
				if (confirmarSenha(senha, confSenha) == true) {
					criarUsuario(criarSupervisor(cpf, Criptografar.encriptografar(senha)));
					ControllerAuxiliar.limparCampos(campoCpf, campoSenha, campoConfSenha);
				} else {
					Aviso.avisar(6);
				}
			}
		} else {
			Aviso.avisar(1);
		}
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();

	}

	// Esse mï¿½todo ï¿½ responsï¿½vel por verificar se a senha digitada ï¿½ igual
	// ï¿½ senha
	// de confirmaï¿½ï¿½o

	public boolean confirmarSenha(String senha, String confSenha) {
		if (senha.equals(confSenha)) {
			return true;
		} else {
			return false;
		}
	}

	public static Supervisor buscarSupervisorPeloCpf(String cpf) {
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisor"));
			Essencial.getQuery().setParameter("Scpf", cpf);
			Supervisor supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
			return supervisor;
		} catch (NoResultException e) {
			return null;
		} finally {

		}
	}

	public Supervisor buscarSupervisorPeloId(int id) {

		Supervisor supervisor = new Supervisor();
		supervisor = Essencial.getManager().find(Supervisor.class, id);

		return supervisor;
	}

	public void mostrarDadosDoSupervisorPeloId(int id, JFormattedTextField cpfCampo, JPasswordField senha) {

		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		try {
			if (verificarStatusSupervisor(id) == false) {
				// Supervisor está inativo
			} else {

				cpfCampo.setText(buscarSupervisorPeloId(id).getCpf());
				senha.setText(buscarSupervisorPeloId(id).getSenha());
			}
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e);
			Aviso.avisar(7);
		}
		finally {
			Essencial.getManager().close();
		}
	}

	public Supervisor criarAtualizacaoSupervisor(int id, String cpf, String senha) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(cpf);
		supervisor.setSenha(Criptografar.encriptografar(senha));
		supervisor.setStatus(Status.ATIVO);
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorCpf(int id, String cpf) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(cpf);
		supervisor.setSenha(buscarSupervisorPeloId(id).getSenha());
		supervisor.setStatus(Status.ATIVO);
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorSenha(int id, String senha) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(buscarSupervisorPeloId(id).getCpf());
		supervisor.setSenha(Criptografar.encriptografar(senha));
		supervisor.setStatus(Status.ATIVO);
		return supervisor;
	}

	public boolean atualizarSupervisor(String cpf, String senha, JFormattedTextField campo) {
		Boolean supervisorAtualizado = false;
		int id = 0;

		if (evitarValorVazio(campo, id) == false) {
//			SÃ³ vai executar as demais se for digitado um id
		} else {

			id = Integer.valueOf(campo.getText());

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();

			if (buscarSupervisorAtivo(buscarSupervisorPeloId(id).getCpf()) == false) {
//				Aviso.avisar(11);
			} else {
				if (testarCampos(cpf, senha) == false) {
					Aviso.avisar(1);
				} else {
					if (evitarDuplicacaoSupervisor(cpf) == true) {
						Essencial.getManager().merge(criarAtualizacaoSupervisor(id, cpf, senha));
						Aviso.avisar(10);
						supervisorAtualizado = true;
					}
				}

			}
			Essencial.getManager().getTransaction().commit();
			Essencial.getManager().close();
		}

		return supervisorAtualizado;

	}

	public boolean atualizarSupervisorCpf(JFormattedTextField campo, String cpf) {

		Boolean supervisorAtualizado = false;
		int id = 0;

		if (evitarValorVazio(campo, id) == false) {
//			SÃ³ vai executar as demais se for digitado um id
		} else {

			id = Integer.valueOf(campo.getText());

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();
			System.out.println(id);
			if (buscarSupervisorPeloId(id) == null) {
				Aviso.avisar(11);
			} else {
				if (testarCampoCpf(cpf) == false) {
					Aviso.avisar(12);
				} else {
					if (evitarDuplicacaoSupervisor(cpf) == true) {
						Essencial.getManager().merge(criarAtualizacaoSupervisorCpf(id, cpf));
						Aviso.avisar(10);
						supervisorAtualizado = true;
					}
				}

			}

			Essencial.getManager().getTransaction().commit();
			Essencial.getManager().close();

		}

		return supervisorAtualizado;

	}

	public boolean atualizarSupervisorSenha(JFormattedTextField campo, String senha) {

		Boolean supervisorAtualizado = false;
		int id = 0;

		if (evitarValorVazio(campo, id) == false) {
//			SÃ³ vai executar as demais se for digitado um id
		} else {

			id = Integer.valueOf(campo.getText());

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();
			if (buscarSupervisorPeloId(id) == null) {
				Aviso.avisar(11);
			} else {
				if (testarCampoSenha(senha) == false) {
					Aviso.avisar(12);
				} else {
					Essencial.getManager().merge(criarAtualizacaoSupervisorSenha(id, senha));
					Aviso.avisar(10);
					supervisorAtualizado = true;
				}

			}

			Essencial.getManager().getTransaction().commit();
			Essencial.getManager().close();

		}

		return supervisorAtualizado;

	}

	public void excluirContaSupervisor(JFormattedTextField campoId, String cpf, String senha,
			JFormattedTextField campoCpfGerente, JPasswordField campoSenhaGerente,
			JFormattedTextField campoCpfSupervisor) {
		int id = 0;

		if (validarConfirmacaoGerente(cpf, senha) == true) {
			if (evitarValorVazio(campoId, id) == false) {
//				SÃ³ vai executar as demais se for digitado um id
			} else {

				id = Integer.valueOf(campoId.getText());

				Essencial.setManager(new JPAUtil().getEntityManager());
				Essencial.getManager().getTransaction().begin();

				if (buscarSupervisorPeloId(id) == null) {
					Aviso.avisar(11);
				} else {
					Janela_confirmar_delecao confDell = new Janela_confirmar_delecao();
					SupervisorDeletado.setId(id);
					confDell.setVisible(true);
					ControllerAuxiliar ctrlAux = new ControllerAuxiliar();
					ctrlAux.limparCampos(campoId, campoCpfGerente, campoSenhaGerente, null, campoCpfSupervisor);
				}

				Essencial.getManager().getTransaction().commit();
				Essencial.getManager().close();

			}

		}

	}

	public boolean excluirConta(int id) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Supervisor usuario = Essencial.getManager().find(Supervisor.class, id);
		Supervisor usuario_removido = new Supervisor();
		usuario_removido.setId(id);
		usuario_removido.setCpf(usuario.getCpf());
		usuario_removido.setNivel(usuario.getNivel());
		usuario_removido.setSenha(usuario.getSenha());
		usuario_removido.setStatus(Status.INATIVO);
		Essencial.getManager().getTransaction().begin();
		Essencial.getManager().merge(usuario_removido);
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();

		Aviso.avisar(14);

		return true;
	}

}
