package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.encodetech.dao.localizacao.CidadeDAO;
import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Estado;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class CidadeBean implements Serializable {

	Cidade cidade;
	CidadeDAO dao;
	List<Cidade> listaCidade;
	Estado estado;

	EstadoDAO daoEstado;
	List<Estado> listaEstado;

	public void salvar() {

		estado = new Estado();
		cidade.setEstado(estado);

		dao.merge(cidade);
		Messages.addGlobalInfo("Cidade salva com sucesso: " + cidade.getNome());

	}

	public void novo() {
		daoEstado = new EstadoDAO();
		listaEstado = daoEstado.listar();

		cidade = new Cidade();
		dao = new CidadeDAO();

	}

	public void fechar() {
		cidade = null;
		dao = null;
		daoEstado = null;
		listaEstado = null;
		estado = null;

	}

	public void carregar() {

		try {
			cidade = new Cidade();
			dao = new CidadeDAO();
			this.listaCidade = dao.listar();
			dao = null;
			cidade = null;
			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		}

	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getListaCidade() {
		return listaCidade;
	}

	public void setListaCidade(List<Cidade> listaCidade) {
		this.listaCidade = listaCidade;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}
