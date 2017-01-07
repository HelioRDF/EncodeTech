package br.com.encodetech.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.encodetech.domain.complementos.GenericDomain;


/**
 * [ Detalhes... ]
 * 
 * 
 */


@SuppressWarnings("serial")
@Entity
public class InformacoesAdicionais extends GenericDomain {

	@Column
	private String descricao;


	// Formação
	// Objetivo
	@Column
	private String cargoPretendido;
	
	//Pretenção Salarial
	@Column
	private String pretensaoSalarial;
	
	@OneToOne
	@JoinColumn(name="usuarioCodigo")
	private Usuario usuario;
	

	// ----------------------------------------------------------

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(String cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public String getPretensaoSalarial() {
		return pretensaoSalarial;
	}

	public void setPretensaoSalarial(String pretensaoSalarial) {
		this.pretensaoSalarial = pretensaoSalarial;
	}
	
	
	
}
