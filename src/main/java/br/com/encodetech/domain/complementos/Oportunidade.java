package br.com.encodetech.domain.complementos;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.encodetech.domain.empresas.Empresa;



/**
 * [ Detalhes... ]
 * 
 * -Entity - Diz que a classe é uma entidade do hibernate
 * 
 * -Column(
 * | length = Tamanho do campo 
 * | name = define o nome no banco 
 * | nullable = Diz se o campo pode ou não ser nulo "True ou False");
 * | precision = Define quantidade de digitos
 * | scale= Define quantos digitos ficam após a virgula 
 * 
 * -JoinColumn - Permite personalizar colunas que são chaves estrangeiras
 * 
 */

@SuppressWarnings("serial")
@Entity
public class Oportunidade extends GenericDomain {
	
	
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataCadastro;
	
	@Column(nullable = false)
	private String cargo;
	
	@Column
	private String nivel;	//Junior | Senior | Pleno | Trainee

	@Column(nullable = false)
	@Lob
	private String descricao;
	
	@Column
	@Lob
	private String preRequisitos;

	@Column(nullable = false)
	private Short quantidade;

	@Column(nullable = false, precision = 8, scale = 2)
	private BigDecimal salario;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Empresa empresa;
	
	@Column
	private String tipo; //CLT | CLT PCD | PJ | Estágio ???
	
	private String setor; //MKT | Engenharia | ???
	
	
	//--------------------------------------------------
	
	

	public String getDescricao() {
		return descricao;
	}

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getPreRequisitos() {
		return preRequisitos;
	}

	public void setPreRequisitos(String preRequisitos) {
		this.preRequisitos = preRequisitos;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	@Override
	public String toString() {
		return "Oportunidade [dataCadastro=" + dataCadastro + ", cargo=" + cargo + ", nivel=" + nivel + ", descricao="
				+ descricao + ", preRequisitos=" + preRequisitos + ", quantidade=" + quantidade + ", salario=" + salario
				+ ", empresa=" + empresa + ", tipo=" + tipo + ", setor=" + setor + "]";
	}
	
	
	
	
	
	

}
