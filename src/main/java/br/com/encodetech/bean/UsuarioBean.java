package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

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

			}

			else {

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

		}

		finally {

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
