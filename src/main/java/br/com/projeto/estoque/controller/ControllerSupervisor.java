package br.com.projeto.estoque.controller;

import javax.persistence.NoResultException;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

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

	// Verifica se os dados digitados nos campos de login do Supervisor est�o de
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

		return supervisor;

	}

	public void cadastrarSupervisor(String cpf, String senha, String confSenha, JFormattedTextField campoCpf,
			JPasswordField campoSenha, JPasswordField campoConfSenha) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (testarCampos(cpf, senha) == true) {
			if (buscarSupervisorPeloCpf(cpf) != null) {
				Aviso.avisar(3);
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

	// Esse m�todo � respons�vel por verificar se a senha digitada � igual � senha
	// de confirma��o

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

		try {
			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();
			cpfCampo.setText(buscarSupervisorPeloId(id).getCpf());
			senha.setText(buscarSupervisorPeloId(id).getSenha());
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, e);
			Aviso.avisar(7);
		} finally {
			Essencial.getManager().close();
		}
	}

	public Supervisor criarAtualizacaoSupervisor(int id, String cpf, String senha) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(cpf);
		supervisor.setSenha(Criptografar.encriptografar(senha));
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorCpf(int id, String cpf) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(cpf);
		supervisor.setSenha(buscarSupervisorPeloId(id).getSenha());
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorSenha(int id, String senha) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(buscarSupervisorPeloId(id).getCpf());
		supervisor.setSenha(Criptografar.encriptografar(senha));
		return supervisor;
	}

	public boolean atualizarSupervisor(String cpf, String senha, JFormattedTextField campo) {
		Boolean supervisorAtualizado = false;
		int id = 0;

		if (evitarValorVazio(campo, id) == false) {
//			Só vai executar as demais se for digitado um id
		} else {

			id = Integer.valueOf(campo.getText());

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();

			if (buscarSupervisorPeloId(id) == null) {
				Aviso.avisar(11);
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
//			Só vai executar as demais se for digitado um id
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
//			Só vai executar as demais se for digitado um id
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

	public void excluirContaSupervisor(JFormattedTextField campoId, String cpf, String senha, JFormattedTextField campoCpfGerente, JPasswordField campoSenhaGerente, JFormattedTextField campoCpfSupervisor) {
		int id = 0;

		if (validarConfirmacaoGerente(cpf, senha) == true) {
			if (evitarValorVazio(campoId, id) == false) {
//				Só vai executar as demais se for digitado um id
			} else {

				id = Integer.valueOf(campoId.getText());

				Essencial.setManager(new JPAUtil().getEntityManager());
				Essencial.getManager().getTransaction().begin();

				if (buscarSupervisorPeloId(id) == null) {
					Aviso.avisar(11);
				} else {
					Janela_confirmar_delecao confDell = new Janela_confirmar_delecao();
					SupervisorDeletado .setId(id);
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
		Supervisor usuario_Removido = Essencial.getManager().find(Supervisor.class, id);
		Essencial.getManager().getTransaction().begin();
		Essencial.getManager().remove(usuario_Removido);
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();

		return true;
	}

}
