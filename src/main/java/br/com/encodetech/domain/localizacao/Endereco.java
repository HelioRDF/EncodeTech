package br.com.encodetech.domain.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;

import br.com.encodetech.domain.complementos.GenericDomain;


/**
 * Detalhes:
 * 
 * 
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Endereco extends GenericDomain {

	@Column
	private int estado;

	@Column
	private int cidade;

	@Column()
	private String rua;

	@Column()
	private Short numero;

	@Column()
	private String bairro;

	@Column()
	private String cep;

	@Column()
	private String complemento;

	@Column()
	private String telefone;

	@Column()
	private String celular;


	// ----------------------------------------------



	

	public String getRua() {
		return rua;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getCidade() {
		return cidade;
	}

	public void setCidade(int cidade) {
		this.cidade = cidade;
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
	
	

	@Override
	public String toString() {
		return "Endereco [estado=" + estado + ", cidade=" + cidade + ", rua=" + rua + ", numero=" + numero + ", bairro="
				+ bairro + ", cep=" + cep + ", complemento=" + complemento + ", telefone=" + telefone + ", celular="
				+ celular + "]";
	}

	
	
	

}
