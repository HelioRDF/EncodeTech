package br.com.encodetech.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.encodetech.domain.complementos.GenericDomain;


/**
 * [ Detalhes... ]
 * 
 * 
 */


@SuppressWarnings("serial")
@Entity
public class InformacoesAdicionais extends GenericDomain {

	@Column(nullable = false)
	private String descricao;

	// ----------------------------------------------------------

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
