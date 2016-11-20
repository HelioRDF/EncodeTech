package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.encodetech.dao.usuarios.UsuarioDAO;
import br.com.encodetech.domain.usuarios.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private UsuarioDAO dao;
	private List<Usuario> listaUsuario;
	private String confirmar;
	boolean validarInfos = false;

	public void salvar() {

		try {

			if (!(usuario.getSenha().length() >= 4)) {
				Messages.addGlobalWarn("Verifique os requisitos minímos de segurança (min 4 digítos)");
			} else {
				if (usuario.getSenha().equals(confirmar)) {
					validarInfos = true;
				} else
					Messages.addGlobalWarn("A Confirmação de senha está incorreta");
			}

			if (validarInfos) {
				usuario.setDataCadastro(new Date());
				dao.merge(usuario);
				Messages.addGlobalInfo("Usuário salvo com sucesso: " + usuario.getNome());
			}

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o usuário, preencha todos os campos corretamente! ");
		} finally {
			validarInfos = false;
		}
	}

	public void novo() {
		usuario = new Usuario();
		dao = new UsuarioDAO();
	}

	public void fechar() {
		usuario = null;
		dao = null;
	}

	public void carregar() {

		try {
			usuario = new Usuario();
			dao = new UsuarioDAO();
			listaUsuario = dao.listar();
			dao = null;
			usuario = null;
			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("select");
			UsuarioDAO dao = new UsuarioDAO();
			Messages.addGlobalInfo("Nome Removido: " + usuario.getNome());
			dao.excluir(usuario);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + usuario.getNome());

		}

	}

	public void editar() {

		try {

			dao = new UsuarioDAO();
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário Editado com sucesso: " + usuario.getNome());


		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		}

	}
	
	public void editarSenha() {

		try {
			
			if (!(usuario.getSenha().length() >= 4)) {
				Messages.addGlobalWarn("Verifique os requisitos minímos de segurança (min 4 digítos)");
			} else {
				if (usuario.getSenha().equals(confirmar)) {
					validarInfos = true;
				} else
					Messages.addGlobalWarn("A Confirmação de senha está incorreta");
			}

			if (validarInfos) {

			dao = new UsuarioDAO();
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário Editado com sucesso: " + usuario.getNome());

			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		} finally {
			validarInfos = false;
		}

	}

	public void getinstancia(ActionEvent evento) {

		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("selectUser");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());	

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		}

	}

	
	// ----------------------------------------------------------------------

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public String getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(String confirmar) {
		this.confirmar = confirmar;
	}

}
