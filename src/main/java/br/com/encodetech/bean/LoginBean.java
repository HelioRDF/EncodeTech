package br.com.encodetech.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.encodetech.dao.usuarios.UsuarioDAO;
import br.com.encodetech.domain.usuarios.Usuario;

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	
	private Usuario usuario;
	private Usuario usuarioLogado;
	
	
	
	
	@PostConstruct
	public void iniciar(){
		usuario = new Usuario();
	}
	
	public void autenticar(){
		
		try {
			
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioLogado = usuarioDAO.autenticar(usuario.getEmail(), usuario.getSenha());
			
			if(usuarioLogado==null){
				Messages.addGlobalWarn("Usuário e/ou senha, incorretos");
				return;
				
			} else{
				
				//Verifica se usuário é um ADM
				if(!usuarioLogado.getAdmin()){
					
					Messages.addGlobalError("Usuário sem permissões de acesso Administrativo.");
					return;
					
				}
				
				//Verifica se usuário está Ativo
				if(!usuarioLogado.getStatus()){
					
					Messages.addGlobalError("Usuário Desativado.");
					return;
					
				}
				
				
				
			}
			
			
			Faces.redirect("./pages/administrativas/usuario.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
			
		}
		
		
	}
	
	public void sair(){
		
		try {
			
			//Destroi as sessões após loggof do usuário.
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
			session.invalidate();
			
			usuarioLogado  =null;
			Faces.redirect("./pages/publicas/login.xhtml");
			Messages.addGlobalInfo("Logout");
			
			return;
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//------------------------------------------------------------
	

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	
	
	
	
	
}
