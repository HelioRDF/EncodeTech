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

/**
 * [ Detalhes... ]
 * 
 * 
 * -Mensagen |FacesContext.getCurrentInstance().addMessage(null,new
 * |FacesMessage(FacesMessage.SEVERITY_WARN, "A confirmação de |senha está
 * incorreta", null));
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private UsuarioDAO dao;
	private List<Usuario> listaUsuario;

	// Salvar usuário
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {
			
			System.out.println("Método salvar");

			usuario.setDataCadastro(new Date());
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário(a) " + usuario.getNome() + ", salvo com sucesso.");

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o usuário, tente novamente mais tarde ... ");
		} finally {
			
			fechar();

		}
	}

	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {

		System.out.println("Método novo");
		
		usuario = new Usuario();
		dao = new UsuarioDAO();

	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		System.out.println("Método fechar");
		
		usuario = new Usuario();
		dao = new UsuarioDAO();
	}

	// Carregar
	// -------------------------------------------------------------------------------------------
	public void carregar() {

		try {
			usuario = new Usuario();
			dao = new UsuarioDAO();
			listaUsuario = dao.listar();

			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} finally {
			fechar();
		}

	}

	// Excluir usuário
	// -------------------------------------------------------------------------------------------
	public void excluir(ActionEvent evento) {

		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			UsuarioDAO dao = new UsuarioDAO();
			Messages.addGlobalInfo("Usuário(a) ' " + usuario.getNome() + "' Removido com sucesso!!!");
			dao.excluir(usuario);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + usuario.getNome());

		} finally {
			fechar();
		}

	}

	// Editar usuário
	// -------------------------------------------------------------------------------------------
	public void editar() {

		try {

			dao = new UsuarioDAO();
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário(a) ' " + usuario.getNome() + "' Editado com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar Usuário(a) '" + usuario.getNome() + "'");

		} finally {
			fechar();
		}

	}

	// Salvar Senha
	// -------------------------------------------------------------------------------------------
	public void editarSenha() {

		try {

			dao = new UsuarioDAO();
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário Editado com sucesso: " + usuario.getNome());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		} finally {

			fechar();
		}

	}

	// Instanciar
	// -------------------------------------------------------------------------------------------

	public void getinstancia(ActionEvent evento) {

		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

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

}
