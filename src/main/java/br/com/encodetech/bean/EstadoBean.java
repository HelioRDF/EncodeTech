package br.com.encodetech.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Messages;

import br.com.encodetech.domain.localizacao.Estado;


/**
 * [ Detalhes... ]
 * 
 * -Omnifaces
 * Gera mensagems ligando controle e visão 
 * Messages.addGlobalInfo("ddd");
 *
 */

@ManagedBean
@SessionScoped

public class EstadoBean {
	
	private Estado estado;
	
	
	public void salvar(){
		
		
		
Messages.addGlobalInfo("Codigo: "+estado.getCodigo());
Messages.addGlobalInfo("Estado: "+estado.getNome());
Messages.addGlobalInfo("Sigla: "+estado.getSigla());
		
	}
	
	public void novo(){
		estado= new Estado();
		
	}
	
	
	
	//-------------------------------------------------


	public Estado getEstado() {
		return estado;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	

}
