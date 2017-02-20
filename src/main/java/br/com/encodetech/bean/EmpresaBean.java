package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.dao.localizacao.CidadeDAO;
import br.com.encodetech.dao.localizacao.EnderecoDAO;
import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.domain.empresas.Empresa;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Endereco;
import br.com.encodetech.domain.localizacao.Estado;

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
@SessionScoped
public class EmpresaBean implements Serializable {

	private Empresa empresa;
	private EmpresaDAO dao;
	private List<Empresa> listaEmpresa;
	private List<Cidade> listaCidade;
	private List<Estado> listaEstado;
	private CidadeDAO cidadeDao;
	private String auxCidade="Selecione uma Cidade";
	private EstadoDAO estadoDao;
	private Estado estado;
	private String auxEstado=" Selecione um Estado";
	
	
	private Endereco endereco;
	private EnderecoDAO enderecoDAO;
	

	private Boolean botaoEditar =false;
	private Boolean botaoSalvar =false;
	private Boolean telaEditar =false;
	
	
	
	

	// Salvar
	// -------------------------------------------------------------------------------------
	public void salvar() {

		try {
			
			//Cria um hash e criptografa a senha
			SimpleHash hash = new SimpleHash("md5", empresa.getSenhaSemCriptografia());
			empresa.setSenha(hash.toHex());
			
			empresa.setDataCadastro(new Date());
			enderecoDAO.salvar(endereco);
			empresa.setEndereco(endereco);
			
			dao.salvar(empresa);
			Messages.addGlobalInfo("Empresa " + empresa.getNomeEmpresa() + ", salva com sucesso.");

		} catch (Exception e) {
			System.out.println("--------------- Catch: " + e.getCause());
			System.out.println("--------------- Mensagem: " + e.getMessage());
			Messages.addGlobalError("Não foi possível salvar a empresa, tente novamente mais tarde ... ");
		} finally {



			System.out.println("Finally: ");
			fechar();

		}
	}

	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {

		listarInfos();
		telaEditar=false;
		botaoEditar=false;
		botaoSalvar=true;
		telaEditar = false;
		empresa = new Empresa();
		endereco = new Endereco();
		enderecoDAO = new EnderecoDAO();
		dao = new EmpresaDAO();
		auxCidade="Selecione uma Cidade";
		auxEstado=" Selecione um Estado";
		System.out.println("Método novo");

	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		RequestContext.getCurrentInstance().reset("dialogform");
		System.out.println("Método Fechar");
	}

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

			listarInfos();
			
			enderecoDAO= new EnderecoDAO();
			dao = new EmpresaDAO();
			
			endereco.setEstado(estado);
			empresa.setEndereco(endereco);
			
			enderecoDAO.merge(endereco);
			dao.merge(empresa);
			

			auxCidade=empresa.getEndereco().getCidade().getNome() ;
			auxEstado=empresa.getEndereco().getCidade().getEstado().getNome();
			
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
			
			
			//Cria um hash e criptografa a senha
			SimpleHash hash = new SimpleHash("md5", empresa.getSenhaSemCriptografia());
			empresa.setSenha(hash.toHex());
			
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
			
			botaoSalvar=false;
			botaoEditar=true;
			telaEditar = true;
			
			empresa = (Empresa) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + empresa.getNomeEmpresa());
			endereco=empresa.getEndereco();
			
			auxCidade=empresa.getEndereco().getCidade().getNome() ;
			auxEstado=empresa.getEndereco().getCidade().getEstado().getNome();
			
			listarInfos();
			filtrarCidadeTwo();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + empresa.getNomeEmpresa());

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void listarInfos() {

		try {

			estadoDao = new EstadoDAO();
			

			listaEstado = estadoDao.listar("nome");
			listaCidade = cidadeDao.listar("nome");

		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	public void filtrarCidade(){
		
		try {
			
			cidadeDao = new CidadeDAO();
			listaCidade = cidadeDao.buscarPorEstado(estado.getCodigo());	
			
			
			auxCidade="Selecione uma Cidade";			
			empresa.setEndereco(null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	public void filtrarCidadeTwo() {

		try {
	
			cidadeDao = new CidadeDAO();
			listaCidade = cidadeDao.buscarPorEstado(estado.getCodigo());		
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	
	
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}


	public Boolean getBotaoEditar() {
		return botaoEditar;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Boolean getTelaEditar() {
		return telaEditar;
	}

	public void setTelaEditar(Boolean telaEditar) {
		this.telaEditar = telaEditar;
	}

	public String getAuxCidade() {
		return auxCidade;
	}

	public void setAuxCidade(String auxCidade) {
		this.auxCidade = auxCidade;
	}

	public String getAuxEstado() {
		return auxEstado;
	}

	public void setAuxEstado(String auxEstado) {
		this.auxEstado = auxEstado;
	}
	 
	
	
	
	


	// ------------------------------------------------------------------------------------------------------------------------------------------------------

}
