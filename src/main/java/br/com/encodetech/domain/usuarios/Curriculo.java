package br.com.encodetech.domain.usuarios;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.encodetech.domain.complementos.GenericDomain;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Estado;

/**
 * [ Detalhes... ]
 * 
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Curriculo extends GenericDomain {

	@Column
	private Estado estado;

	@Column
	private Cidade cidade;

	@Column(length = 100, nullable = false)
	private String rua;

	@Column(nullable = false)
	private Short numero;

	@Column(length = 30, nullable = false)
	private String bairro;

	@Column(length = 10, nullable = false)
	private String cep;

	@Column(length = 10, nullable = false)
	private String complemento;

	@Column(length = 13, nullable = false)
	private String telefone;

	@Column(length = 14, nullable = false)
	private String celular;

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

	// ------------------------------------------------------------------

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public Short getNumero() {
		return numero;
	}

	public void setNumero(Short numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

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

}
