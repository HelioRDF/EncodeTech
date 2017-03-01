package br.com.encodetech.util;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;

import br.com.encodetech.bean.LoginBean;
import br.com.encodetech.domain.usuarios.Usuario;



public class AutenticacaoListener implements PhaseListener {


	private static final long serialVersionUID = 5370242150329490062L;

	@Override
	public void afterPhase(PhaseEvent event) {
		
		String paginaAtual = Faces.getViewId(); 
		
		boolean paginaDeAutenticaçao = paginaAtual.contains("login.xhtml");
		
		//Verifica se a tela é publica ou privada
		if(!paginaDeAutenticaçao){
			LoginBean loginBean = Faces.getSessionAttribute("loginBean");	
			
			//Verifica se o Bean foi criado
			if(loginBean==null){
				Faces.navigate("/pages/publicas/login.xhtml");
				return;
				
			}
			//Verifica se a usuário existe
			Usuario usuario = loginBean.getUsuarioLogado();
			if(usuario==null){
				Faces.navigate("/pages/publicas/login.xhtml");
			
				return;
			}
			
//			//Encaminha para a tela principal
//			Faces.navigate("/pages/administrativas/usuario.xhtml");
			
		}
		
		
		
		
		
		System.out.println("\nAfterPhase:"+ event.getPhaseId());
//		System.out.println("LoginBean:"+ loginBean);
		System.out.println("Página Atual:"+ paginaAtual);
		
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		System.out.println("BeforePhase:"+event.getPhaseId());
		
	}

	@Override
	public PhaseId getPhaseId() {
		// TODO Auto-generated method stub
		return PhaseId.RESTORE_VIEW;
	}

}
