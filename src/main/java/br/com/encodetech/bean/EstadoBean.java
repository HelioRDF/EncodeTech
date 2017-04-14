package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.domain.localizacao.Estado;

/**
 * [ Detalhes... ]
 * 
 * -Omnifaces Gera mensagems ligando controle e visão
 * Messages.addGlobalInfo("ddd");
 *
 */

@SuppressWarnings("serial")
@ManagedBean
@SessionScoped

public class EstadoBean implements Serializable {

	private Estado estado;
	private EstadoDAO dao;
	private List<Estado> listaEstado;
	private int totalEstados;

	

	// Salvar
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {

			if (!(estado == null)) {
				dao = new EstadoDAO();
			}


				dao.merge(estado);
				Messages.addGlobalInfo("Estado salvo com sucesso: " + estado.getNome());


		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o Estado, preencha todos os campos corretamente! ");
		} finally {
		
		}

	}
	

	// Novo
	// -------------------------------------------------------------------------------------

	public void novo() {
		RequestContext.getCurrentInstance().reset("formCadastro");

		estado = new Estado();
		dao = new EstadoDAO();

	}


	// Fechar
	// -------------------------------------------------------------------------------------
	public void fechar() {
		RequestContext.getCurrentInstance().reset("formCadastro");
		estado = null;
		dao = null;
	}
	

	// Carregar
	// -------------------------------------------------------------------------------------

	public void carregar() {

		try {
			estado = new Estado();
			dao = new EstadoDAO();
			this.listaEstado = dao.listar();
			dao = null;
			estado = null;
			totalEstados=listaEstado.size();
			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		}

	}

	

	// Excluir
	// -------------------------------------------------------------------------------------
	public void excluir(ActionEvent evento) {

		try {

			estado = (Estado) evento.getComponent().getAttributes().get("meuSelect");
			EstadoDAO dao = new EstadoDAO();
			dao.excluir(estado);
			Messages.addGlobalInfo("Estado Removido: " );

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + estado.getNome());

		}

	}
	

	// Instaciar
	// -------------------------------------------------------------------------------------

	public void getinstancia(ActionEvent evento) {

		try {
			estado = (Estado) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + estado.getNome());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + estado.getNome());

		}

	}

	// -------------------------------------------------

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Estado> getListaEstado() {
		return listaEstado;
	}

	public void setListaEstado(List<Estado> listaEstado) {
		this.listaEstado = listaEstado;
	}


	public int getTotalEstados() {
		return totalEstados;
	}


	public void setTotalEstados(int totalEstados) {
		this.totalEstados = totalEstados;
	}


	
	
	
}
