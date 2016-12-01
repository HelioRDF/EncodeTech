package br.com.encodetech.domain.localizacao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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

	@JoinColumn
	@OneToOne
	private Estado estado;

	@JoinColumn
	@OneToOne
	private Cidade cidade;

	@Column()
	private String rua;

	@Column()
	private String numero;

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



	public String getNumero() {
		return numero;
	}



	public void setNumero(String numero) {
		this.numero = numero;
	}



	public void setRua(String rua) {
		this.rua = rua;
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
