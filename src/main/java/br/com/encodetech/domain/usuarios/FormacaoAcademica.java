package br.com.encodetech.domain.usuarios;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.encodetech.domain.complementos.GenericDomain;

/**
 * [ Detalhes... ]
 * 
 * 
 */

@SuppressWarnings("serial")
@Entity
public class FormacaoAcademica extends GenericDomain {

	// Graduação | Técnico | Primeiro Grau| Segundo Grau| ???

	@Column(length = 30, nullable = false)
	private String instituicao; // Anhembi Morumbi

	@Column(length = 30, nullable = false)
	private String titulo; // Graduação | Técnico | Primeiro Grau| Segundo
							// Grau|?
	
	@Column(length = 30, nullable = false)
	private String status; 
	
	@Column(length = 30, nullable = false)
	private String nomeCurso;// Analise de Sistemasng

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date inicio;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fim;





	// -------------------------------------------------------

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}




	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
