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
public class AtividadesProfissionais extends GenericDomain {

	@Column(length = 30, nullable = false)
	private String nomeCurso;

	@Column(length = 30, nullable = false)
	private String instituicao;

	@Column(length = 6, nullable = false)
	private String anoCurso;

	@Column(length = 5, nullable = false)
	private String cargaHoraria;

	// ----------------------------------------------------------

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getAnoCurso() {
		return anoCurso;
	}

	public void setAnoCurso(String anoCurso) {
		this.anoCurso = anoCurso;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

}
