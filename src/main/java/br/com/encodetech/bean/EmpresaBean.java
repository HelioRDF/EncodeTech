package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.domain.empresas.Empresa;

/**
 * [ Detalhes... ]
 * 
 * 
 * -Mensagen |FacesContext.getCurrentInstance().addMessage(null,new
 * |FacesMessage(FacesMessage.SEVERITY_WARN, "A confirmação de |senha está
 * incorreta", null));
 */

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class EmpresaBean implements Serializable {

	private Empresa empresa;
	private EmpresaDAO dao;
	private List<Empresa> listaEmpresa;

	// Salvar
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {

			empresa.setDataCadastro(new Date());
			dao.merge(empresa);
			Messages.addGlobalInfo("Empresa " + empresa.getNomeEmpresa() + ", salva com sucesso.");

		} catch (Exception e) {
			System.out.println("Catch: " + e.getCause());

			System.out.println("Mensagem: " + e.getMessage());
			Messages.addGlobalError("Não foi possível salvar a empresa, tente novamente mais tarde ... ");
		} finally {

			fechar();

		}
	}

	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {

		empresa = null;
		dao = null;
		empresa = new Empresa();
		dao = new EmpresaDAO();
	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		empresa = null;
		dao = null;
	}

	// Carregar
	// -------------------------------------------------------------------------------------------
	public void carregar() {

		try {
			empresa = new Empresa();
			dao = new EmpresaDAO();
			listaEmpresa = dao.listar();

			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} finally {
			fechar();
		}

	}

	// Excluir
	// -------------------------------------------------------------------------------------------
	public void excluir(ActionEvent evento) {

		try {

			empresa = (Empresa) evento.getComponent().getAttributes().get("meuSelect");
			EmpresaDAO dao = new EmpresaDAO();
			Messages.addGlobalInfo("Empresa ' " + empresa.getNomeEmpresa() + "' Removida com sucesso!!!");
			dao.excluir(empresa);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + empresa.getNomeEmpresa());

		} finally {
			fechar();
		}

	}

	// Editar usuário
	// -------------------------------------------------------------------------------------------
	public void editar() {

		try {

			dao = new EmpresaDAO();
			dao.merge(empresa);
			Messages.addGlobalInfo("Usuário(a) ' " + empresa.getNomeEmpresa() + "' Editado com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar Usuário(a) '" + empresa.getNomeEmpresa() + "'");

		} finally {

			fechar();

		}

	}

	// Salvar Senha
	// -------------------------------------------------------------------------------------------
	public void editarSenha() {

		try {

			dao = new EmpresaDAO();
			dao.merge(empresa);
			Messages.addGlobalInfo("Usuário Editado com sucesso: " + empresa.getNomeEmpresa());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + empresa.getNomeEmpresa());

		} finally {

			fechar();

		}

	}

	// Instanciar
	// -------------------------------------------------------------------------------------------

	public void getinstancia(ActionEvent evento) {

		try {
			empresa = (Empresa) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + empresa.getNomeEmpresa());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + empresa.getNomeEmpresa());

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public EmpresaDAO getDao() {
		return dao;
	}

	public void setDao(EmpresaDAO dao) {
		this.dao = dao;
	}

	public List<Empresa> getListaEmpresa() {
		return listaEmpresa;
	}

	public void setListaEmpresa(List<Empresa> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

}
