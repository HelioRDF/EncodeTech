package br.com.encodetech.domain.empresas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.encodetech.domain.complementos.GenericDomain;
import br.com.encodetech.domain.localizacao.Endereco;


/**
 * [ Detalhes... ]
 * 
 * -Entity - Diz que a classe é uma entidade do hibernate
 * 
 * -Column(param)
 * | length = Tamanho do campo 
 * | name = define o nome no banco 
 * | nullable = Diz se o campo pode ou não ser nulo "True ou False"
 * | precision = Define quantidade de digitos
 * | scale= Define quantos digitos ficam após a virgula 
 * 
 * -JoinColumn - Permite personalizar colunas que são chaves estrangeiras
 * 
 * -Sobre o CNPJ:
 *  Tem 14 números.
 *  Possui o seguinte formato:
 *  "XX.XXX.XXX/0001-XX"
 *  {@link http://atendimento.contadorx.com/hc/pt-br/articles/205698465-O-que-%C3%A9-CNPJ-}
 * 
 *  -Temporal(TemporalType.param)
 *  |DATE=Data
 *  |Time=Hora
 *  |TIMESTAMP=Data e hora
 *  
 *  -MD5 - A senha está utilizando MD5 para criptografia.
 * 
 *  -Atributos:
 * 	dataCadastro - status - nomeEmpresa - senha - seguimento - descrição  
 *  cnpj - rua - numero - bairro - cep - complemento - telefone - email
 *
 */


@SuppressWarnings("serial")
@Entity
public class Empresa extends GenericDomain {

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;

	@Column(nullable = false)
	private Boolean status;

	@Column(length = 50, nullable = false)
	private String nomeEmpresa;

	@Column(length = 32)
	private String senha;

	@Column(length = 50, nullable = false)
	private String seguimento;

	@Column(length = 100, nullable = false)
	private String descricao;

	@Column(length = 14, nullable = false)
	private String cnpj;
	
	@Column
	private Endereco endereco;

	// -------------------------------------------------------

	public Boolean getStatus() {
		return status;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getSeguimento() {
		return seguimento;
	}

	public void setSeguimento(String seguimento) {
		this.seguimento = seguimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

}
