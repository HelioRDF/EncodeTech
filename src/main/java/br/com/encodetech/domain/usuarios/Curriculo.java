package br.com.encodetech.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.encodetech.domain.complementos.GenericDomain;
import br.com.encodetech.domain.localizacao.Endereco;


/**
 * [ Detalhes... ]
 * 
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Curriculo extends GenericDomain {

	// Objetivo
	@Column(length = 30, nullable = false)
	private String cargoPretendido;

	// Formação
	@Column
	private FormacaoAcademica formacao;

	// Experiencia Profissional
	@Column
	private ExperienciaProfissional experiencia;

	// Atividades Profissionais
	@Column
	private AtividadesProfissionais atividades;

	// Informações adicionais
	@Column
	private InformacoesAdicionais info;
	
	@Column
	private Endereco endereco;

	// ------------------------------------------------------------------

	public String getCargoPretendido() {
		return cargoPretendido;
	}

	public void setCargoPretendido(String cargoPretendido) {
		this.cargoPretendido = cargoPretendido;
	}

	public FormacaoAcademica getFormacao() {
		return formacao;
	}

	public void setFormacao(FormacaoAcademica formacao) {
		this.formacao = formacao;
	}

	public ExperienciaProfissional getExperiencia() {
		return experiencia;
	}

	public void setExperiencia(ExperienciaProfissional experiencia) {
		this.experiencia = experiencia;
	}

	public AtividadesProfissionais getAtividades() {
		return atividades;
	}

	public void setAtividades(AtividadesProfissionais atividades) {
		this.atividades = atividades;
	}

	public InformacoesAdicionais getInfo() {
		return info;
	}

	public void setInfo(InformacoesAdicionais info) {
		this.info = info;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
}
