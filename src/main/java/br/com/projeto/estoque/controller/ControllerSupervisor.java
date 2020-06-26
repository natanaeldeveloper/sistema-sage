package br.com.projeto.estoque.controller;

import javax.persistence.NoResultException;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;

import br.com.projeto.estoque.model.Status;
import br.com.projeto.estoque.model.Supervisor;
import br.com.projeto.estoque.model.TipoComportamento;
import br.com.projeto.estoque.util.Aviso;
import br.com.projeto.estoque.util.Criptografar;
import br.com.projeto.estoque.util.Essencial;
import br.com.projeto.estoque.util.JPAUtil;
import br.com.projeto.estoque.util.SupervisorAtual;
import br.com.projeto.estoque.util.SupervisorDeletado;
import br.com.projeto.estoque.viewUpdate.Janela_confirmar_delecao;

public class ControllerSupervisor extends ControllerGlobal {
	private static int erros = 0;
	ControllerRegistroSupervisor controller = new ControllerRegistroSupervisor();

	public boolean fazerLogin(String cpf_ou_login, String senha, TipoComportamento tipoComportamento) {
		boolean login = false;
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (verificarCredenciais(cpf_ou_login, senha) == true) {
			if (buscarSupervisorAtivoPeloLogin(cpf_ou_login) != null) {
				SupervisorAtual.setSupervisor(buscarSupervisorAtivoPeloLogin(cpf_ou_login));
			} else {
				SupervisorAtual.setSupervisor(buscarSupervisorAtivoPeloCpf(cpf_ou_login));
			}
			controller.criarRegistroSupervisor(tipoComportamento, SupervisorAtual.getSupervisor());
			login = true;
		} else {
			ControllerSupervisor.erros += 1;
			login = false;
			if (ControllerSupervisor.erros == 3) {
				Aviso.avisar(17);
			}
			if (ControllerSupervisor.erros == 4) {
				System.exit(0);
			}
		}
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
		return login;
	}

	// Verifica se os dados digitados nos campos de login do Supervisor est�o de
	// acordo com o banco de dados

	public boolean verificarCredenciais(String login_ou_cpf, String senha) {

		Supervisor supervisorLogin = new Supervisor();
		Supervisor supervisorCpf = new Supervisor();
		supervisorCpf = buscarSupervisorAtivoPeloCpf(login_ou_cpf);
		supervisorLogin = buscarSupervisorAtivoPeloLogin(login_ou_cpf);

		if (supervisorCpf == null) {
			if (supervisorLogin == null) {
				Aviso.avisar(2);
				return false;
			} else {
				if (supervisorLogin.getSenha().equals(Criptografar.encriptografar(senha))) {
					return true;
				} else {
					Aviso.avisar(2);
					return false;
				}
			}
		} else {
			if (supervisorCpf.getSenha().equals(Criptografar.encriptografar(senha))) {
				return true;
			} else {
				Aviso.avisar(2);
				return false;
			}
		}

	}

	// Cria um objeto do tipo supervisor

	public Supervisor criarSupervisor(String cpf, String senha, String login) {

		Supervisor supervisor = new Supervisor();
		supervisor.setCpf(cpf);
		supervisor.setSenha(senha);
		supervisor.setLogin(login);
		supervisor.setStatus(Status.ATIVO);

		return supervisor;

	}

	public void cadastrarSupervisor(String cpf, String senha, String confSenha, String login,
			JFormattedTextField campoLoginSupervisor, JFormattedTextField campoCpfSupervisor,
			JPasswordField campoSenhaSupervisor, JPasswordField campoConfSenhaSupervisor) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		if (testarCamposCadastroSupervisor(cpf, senha, login, confSenha) == true) {
			if (buscarSupervisorPeloCpf(cpf) != null) {
				Aviso.avisar(3);
			} else {

				if (buscarSupervisorPeloLogin(login) != null) {
					Aviso.avisar(14);
				} else {
					if (confirmarSenha(senha, confSenha) == true) {
						criarUsuario(criarSupervisor(cpf, Criptografar.encriptografar(senha), login));
						ControllerAuxiliar ctrlAux = new ControllerAuxiliar();
						ctrlAux.limparCampos(campoLoginSupervisor, campoSenhaSupervisor, campoConfSenhaSupervisor,
								campoCpfSupervisor);
					} else {
						Aviso.avisar(6);
					}
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

	public static Supervisor buscarSupervisorAtivoPeloCpf(String cpf) {
		Supervisor supervisor;
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisor"));
			Essencial.getQuery().setParameter("Scpf", cpf);
			supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
			return supervisor;
		} catch (NoResultException e) {
			supervisor = null;
		}
		return supervisor;
	}

	public Supervisor buscarSupervisorPeloCpf(String cpf) {
		Supervisor supervisor;
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisorComCpf"));
			Essencial.getQuery().setParameter("Scpf", cpf);
			supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
			return supervisor;
		} catch (NoResultException e) {
			supervisor = null;
		}
		return supervisor;
	}

	public static Supervisor buscarSupervisorPeloLogin(String login) {
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisorLogin"));
			Essencial.getQuery().setParameter("Slogin", login);
			Supervisor supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
			return supervisor;
		} catch (NoResultException e) {
			return null;
		} finally {

		}
	}

	public static Supervisor buscarSupervisorAtivoPeloLogin(String login) {
		try {
			Essencial.setQuery(Essencial.getManager().createNamedQuery("buscarSupervisorAtivoLogin"));
			Essencial.getQuery().setParameter("Slogin", login);
			Supervisor supervisor = (Supervisor) Essencial.getQuery().getSingleResult();
			return supervisor;
		} catch (NoResultException e) {
			return null;
		} finally {

		}
	}

	public Supervisor buscarSupervisorPeloId(int id) {
		Supervisor supervisor = new Supervisor();
		try {
			supervisor = Essencial.getManager().find(Supervisor.class, id);
			if (supervisor.getStatus().equals(Status.INATIVO)) {
				supervisor = null;
			}
		} catch (NullPointerException e) {
			supervisor = null;
		}

		return supervisor;
	}

	public void mostrarDadosDoSupervisor(JFormattedTextField id_supervisor, JFormattedTextField cpfCampo,
			JFormattedTextField login) {
		try {
			int id = Integer.valueOf(id_supervisor.getText());

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();
			cpfCampo.setText(buscarSupervisorPeloId(id).getCpf());
			login.setText(buscarSupervisorPeloId(id).getLogin());

		} catch (Exception erro) {
			Aviso.avisar(7);
		}

	}

	public Supervisor criarAtualizacaoSupervisor(int id, String senha, String login) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setLogin(login);
		supervisor.setStatus(Status.ATIVO);
		supervisor.setSenha(Criptografar.encriptografar(senha));

		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorCpf(int id, String cpf) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(cpf);
		supervisor.setStatus(Status.ATIVO);
		supervisor.setSenha(buscarSupervisorPeloId(id).getSenha());
		supervisor.setLogin(buscarSupervisorPeloId(id).getLogin());
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorSenha(int id, String senha) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(buscarSupervisorPeloId(id).getCpf());
		supervisor.setLogin(buscarSupervisorPeloId(id).getLogin());
		supervisor.setStatus(Status.ATIVO);
		supervisor.setSenha(Criptografar.encriptografar(senha));
		return supervisor;
	}

	public Supervisor criarAtualizacaoSupervisorLogin(int id, String login) {
		Supervisor supervisor = new Supervisor();
		supervisor.setId(id);
		supervisor.setCpf(buscarSupervisorPeloId(id).getCpf());
		supervisor.setSenha(buscarSupervisorPeloId(id).getSenha());
		supervisor.setStatus(Status.ATIVO);
		supervisor.setLogin(login);
		return supervisor;
	}

	@SuppressWarnings("deprecation")
	public boolean atualizarSupervisor(String senha, JFormattedTextField campo, String login,
			JFormattedTextField cpf_user_AtualizacaoSupervisor, JPasswordField senha_user_AtualizacaoSupervisor) {
		Boolean supervisorAtualizado = false;
		int id = 0;

		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();

		if (validarConfirmacaoGerente(cpf_user_AtualizacaoSupervisor.getText(),
				senha_user_AtualizacaoSupervisor.getText()) == true || SupervisorAtual.getSupervisor() != null) {

			if (evitarValorVazio(campo, id) == false && SupervisorAtual.getSupervisor() == null) {
				Aviso.avisar(9);
			} else {

				if (SupervisorAtual.getSupervisor() == null) {
					id = Integer.valueOf(campo.getText());
				} else {
					id = SupervisorAtual.getSupervisor().getId();
				}

				if (buscarSupervisorPeloId(id) == null) {
					Aviso.avisar(11);
				} else {
					if (ControllerValidacao.testarCampos(senha, login) == false) {
						Aviso.avisar(1);
					} else {

						if (buscarSupervisorPeloLogin(login) != null) {
							Aviso.avisar(14);
						} else {
							if (SupervisorAtual.getSupervisor() != null) {
								if (validarConfirmacaoSupervisor(cpf_user_AtualizacaoSupervisor.getText(),
										senha_user_AtualizacaoSupervisor.getText())) {
									Essencial.getManager().merge(criarAtualizacaoSupervisorSenha(id, senha));
									Aviso.avisar(16);
									supervisorAtualizado = true;
								}
							} else {
								Essencial.getManager().merge(criarAtualizacaoSupervisorSenha(id, senha));
								Aviso.avisar(10);
								supervisorAtualizado = true;
							}
						}

					}
				}

			}

		}

		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();
		return supervisorAtualizado;

	}

	@SuppressWarnings("deprecation")
	public boolean atualizarSupervisorSenha(JFormattedTextField campo, String senha,
			JFormattedTextField cpf_userAtualizacaoSupervisor, JPasswordField senha_user_AtualizacaoSupervisor) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();
		Boolean supervisorAtualizado = false;
		int id = 0;

		if (validarConfirmacaoGerente(cpf_userAtualizacaoSupervisor.getText(),
				senha_user_AtualizacaoSupervisor.getText()) == true || SupervisorAtual.getSupervisor() != null) {
			if (evitarValorVazio(campo, id) == false && SupervisorAtual.getSupervisor() == null) {
				Aviso.avisar(9);
			} else {
				if (SupervisorAtual.getSupervisor() == null) {
					id = Integer.valueOf(campo.getText());
				} else {
					id = SupervisorAtual.getSupervisor().getId();
				}

				if (buscarSupervisorPeloId(id) == null) {
					Aviso.avisar(11);
				} else {
					if (testarCampoSenha(senha) == false) {
						Aviso.avisar(13);
					} else {
						if (SupervisorAtual.getSupervisor() != null) {
							if (validarConfirmacaoSupervisor(cpf_userAtualizacaoSupervisor.getText(),
									senha_user_AtualizacaoSupervisor.getText())) {
								Essencial.getManager().merge(criarAtualizacaoSupervisorSenha(id, senha));
								Aviso.avisar(16);
								supervisorAtualizado = true;
							}
						} else {
							Essencial.getManager().merge(criarAtualizacaoSupervisorSenha(id, senha));
							Aviso.avisar(10);
							supervisorAtualizado = true;
						}

					}

				}

			}

		}

		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();

		return supervisorAtualizado;

	}

	@SuppressWarnings("deprecation")
	public boolean atualizarSupervisorLogin(JFormattedTextField campo, String login,
			JFormattedTextField cpf_user_AtualizacaoSupervisor, JPasswordField senha_user_AtualizacaoSupervisor) {

		Boolean supervisorAtualizado = false;
		int id = 0;

		Essencial.setManager(new JPAUtil().getEntityManager());
		Essencial.getManager().getTransaction().begin();

		if (validarConfirmacaoGerente(cpf_user_AtualizacaoSupervisor.getText(),
				senha_user_AtualizacaoSupervisor.getText()) == true || SupervisorAtual.getSupervisor() != null) {
			if (evitarValorVazio(campo, id) == false && SupervisorAtual.getSupervisor() == null) {
				Aviso.avisar(9);
			} else {
				if (SupervisorAtual.getSupervisor() == null) {
					id = Integer.valueOf(campo.getText());
				} else {
					id = SupervisorAtual.getSupervisor().getId();
				}

				if (buscarSupervisorPeloId(id) == null) {
					Aviso.avisar(11);
				} else {
					if (testarCampoLogin(login) == false) {
						Aviso.avisar(12);
					} else {
						if (buscarSupervisorPeloLogin(login) != null) {
							Aviso.avisar(3);
						} else {

							if (SupervisorAtual.getSupervisor() != null) {
								if (validarConfirmacaoSupervisor(cpf_user_AtualizacaoSupervisor.getText(),
										senha_user_AtualizacaoSupervisor.getText()) == true) {
									Essencial.getManager().merge(criarAtualizacaoSupervisorLogin(id, login));
									Aviso.avisar(16);
									supervisorAtualizado = true;
								}

							} else {
								Essencial.getManager().merge(criarAtualizacaoSupervisorLogin(id, login));
								Aviso.avisar(10);
								supervisorAtualizado = true;
							}

						}
					}

				}
			}
			Essencial.getManager().getTransaction().commit();
			Essencial.getManager().close();

		}
		return supervisorAtualizado;

	}

	public void excluirContaSupervisor(JFormattedTextField campoId, String cpf, String senha) {

		int id = 0;
		if (validarConfirmacaoGerente(cpf, senha) == true) {
			if (evitarValorVazio(campoId, id) == false) {
				Aviso.avisar(9);
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

				}

				Essencial.getManager().getTransaction().commit();
				Essencial.getManager().close();

			}

		}

	}

	public void excluirContaPropria(String cpf, String senha) {
		if (validarConfirmacaoSupervisor(cpf, senha) == true) {

			Essencial.setManager(new JPAUtil().getEntityManager());
			Essencial.getManager().getTransaction().begin();

			Janela_confirmar_delecao confDell = new Janela_confirmar_delecao();
			SupervisorDeletado.setId(SupervisorAtual.getSupervisor().getId());
			confDell.setVisible(true);

			Essencial.getManager().getTransaction().commit();
			Essencial.getManager().close();
		}

	}

	public boolean excluirConta(int id) {
		Essencial.setManager(new JPAUtil().getEntityManager());
		Supervisor usuario_Removido = Essencial.getManager().find(Supervisor.class, id);
		usuario_Removido.setId(id);
		usuario_Removido.setStatus(Status.INATIVO);
		Essencial.getManager().getTransaction().begin();
		Essencial.getManager().merge(usuario_Removido);
		Essencial.getManager().getTransaction().commit();
		Essencial.getManager().close();

		return true;
	}

}