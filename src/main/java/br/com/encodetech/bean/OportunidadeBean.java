package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.encodetech.dao.complementos.OportunidadeDAO;
import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.domain.complementos.Oportunidade;
import br.com.encodetech.domain.empresas.Empresa;

@ManagedBean
@ViewScoped
public class OportunidadeBean implements Serializable {

	private Oportunidade oportunidade;
	private OportunidadeDAO dao;
	private List<Oportunidade> listaOportunidade;
	
	private EmpresaDAO empresaDAO;
	private List<Empresa> listaEmpresas;
	

	private Boolean botaoEditar = false;
	private Boolean botaoSalvar = false;

	// Salvar usuário
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {

	
			System.out.println("Método salvar");
			
			
			oportunidade.setDataCadastro(new Date());
			dao.salvar(oportunidade);

			Messages.addGlobalInfo("Salvo com sucesso.");
			
			

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o usuário, tente novamente mais tarde ... ");
		} finally {
			System.out.println("finally: "+oportunidade.toString());

			fechar();

		}
	}

	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {


		System.out.println("Método novo");
		
		botaoEditar = false;
		botaoSalvar = true;
		listarInfos();


		oportunidade = new Oportunidade();
		dao = new OportunidadeDAO();

	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		System.out.println("Método fechar");
		RequestContext.getCurrentInstance().reset("dialogform");
		oportunidade = new Oportunidade();
		dao = new OportunidadeDAO();
	}

	// Carregar
	// -------------------------------------------------------------------------------------------
	public void carregar() {

		try {
			oportunidade = new Oportunidade();
			dao = new OportunidadeDAO();
			listaOportunidade = dao.listar();

			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} finally {
			fechar();
		}

	}

	// Excluir usuário
	// -------------------------------------------------------------------------------------------
	public void excluir(ActionEvent evento) {

		try {

			oportunidade = (Oportunidade) evento.getComponent().getAttributes().get("meuSelect");
			OportunidadeDAO dao = new OportunidadeDAO();
			Messages.addGlobalInfo("Removido com sucesso!!!");
			dao.excluir(oportunidade);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: ");

		} finally {
			fechar();
		}

	}

	// Editar usuário
	// -------------------------------------------------------------------------------------------
	public void editar() {

		try {

			listarInfos();
			dao = new OportunidadeDAO();
			dao.merge(oportunidade);
			Messages.addGlobalInfo(" Editado com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar ");

		} finally {
			fechar();
		}

	}

	

	// Instanciar
	// -------------------------------------------------------------------------------------------

	public void getinstancia(ActionEvent evento) {

		try {

			botaoSalvar = false;
			botaoEditar = true;
			oportunidade = (Oportunidade) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: ");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: ");

		}

	}

	// Lista as empresas
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void listarInfos() {

		try {

	empresaDAO = new EmpresaDAO();
	listaEmpresas = empresaDAO.listar();
	
	
	for (Empresa empresa : listaEmpresas) {
		
		System.out.println(empresa.getNomeEmpresa());
		
	}
		

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	public Boolean getBotaoEditar() {
		return botaoEditar;
	}

	public Oportunidade getOportunidade() {
		return oportunidade;
	}

	public void setOportunidade(Oportunidade oportunidade) {
		this.oportunidade = oportunidade;
	}

	public List<Oportunidade> getListaOportunidade() {
		return listaOportunidade;
	}

	public void setListaOportunidade(List<Oportunidade> listaOportunidade) {
		this.listaOportunidade = listaOportunidade;
	}

	public void setBotaoEditar(Boolean botaoEditar) {
		this.botaoEditar = botaoEditar;
	}

	public Boolean getBotaoSalvar() {
		return botaoSalvar;
	}

	public void setBotaoSalvar(Boolean botaoSalvar) {
		this.botaoSalvar = botaoSalvar;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public void setListaEmpresas(List<Empresa> listaEmpresas) {
		this.listaEmpresas = listaEmpresas;
	}
	
	

}