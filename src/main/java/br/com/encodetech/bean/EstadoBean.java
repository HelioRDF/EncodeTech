package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

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
@ViewScoped

public class EstadoBean implements Serializable {

	private Estado estado;
	private boolean validarInfos = false;
	private EstadoDAO dao;
	private List<Estado> listaEstado;

	public void salvar() {

		try {

			if (!(estado == null)) {
				dao = new EstadoDAO();
			}

			if (estado.getSigla().length() == 2) {
				validarInfos = true;

				if (estado.getNome().length() >= 3) {

				} else {
					validarInfos = false;

					Messages.addGlobalWarn("O nome do estado não está correto");
				}

			} else {
				Messages.addGlobalWarn("A sigla não está correta.(precisa ter  2 digítos)");

			}

			if (validarInfos) {

				dao.merge(estado);
				Messages.addGlobalInfo("Estado salvo com sucesso: " + estado.getNome());
			}

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o Estado, preencha todos os campos corretamente! ");
		} finally {
			validarInfos = false;
		}

	}

	public void novo() {
		

		fechar();

		estado = new Estado();
		dao = new EstadoDAO();

	}

	public void fechar() {
		estado = null;
		dao = null;
	}

	public void carregar() {

		try {
			estado = new Estado();
			dao = new EstadoDAO();
			this.listaEstado = dao.listar();
			dao = null;
			estado = null;
			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		}

	}

	public void excluir(ActionEvent evento) {

		try {

			estado = (Estado) evento.getComponent().getAttributes().get("meuSelect");
			EstadoDAO dao = new EstadoDAO();
			Messages.addGlobalInfo("Nome Removido: " + estado.getNome());
			dao.excluir(estado);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + estado.getNome());

		}

	}

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

}
