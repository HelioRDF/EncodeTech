
package br.com.encodetech.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;
import br.com.encodetech.dao.localizacao.CidadeDAO;
import br.com.encodetech.dao.localizacao.EnderecoDAO;
import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.dao.usuarios.FormacaoAcademicaDAO;
import br.com.encodetech.dao.usuarios.UsuarioDAO;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Endereco;
import br.com.encodetech.domain.localizacao.Estado;
import br.com.encodetech.domain.usuarios.AtividadesProfissionais;
import br.com.encodetech.domain.usuarios.Curriculo;
import br.com.encodetech.domain.usuarios.ExperienciaProfissional;
import br.com.encodetech.domain.usuarios.FormacaoAcademica;
import br.com.encodetech.domain.usuarios.InformacoesAdicionais;
import br.com.encodetech.domain.usuarios.Usuario;

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
public class UsuarioBean implements Serializable {

	private Usuario usuario;
	private UsuarioDAO dao;
	private List<Usuario> listaUsuario;
	private List<Cidade> listaCidade;
	private List<Estado> listaEstado;
	private CidadeDAO cidadeDao;
	private Estado estado;
	private EstadoDAO estadoDao;
	
	private Endereco endereco;
	private EnderecoDAO enderecoDAO;
	
	private Boolean botaoEditar =false;
	private Boolean botaoSalvar =false;
	
	private FormacaoAcademica formacaoAcademica;
	private List<FormacaoAcademica> listaFormacao;
	private FormacaoAcademicaDAO daoFormacao;
		
	private Curriculo curriculo;
	private FormacaoAcademica formacaoAcademicaUm;
	private FormacaoAcademica formacaoAcademicaDois;
	private FormacaoAcademica formacaoAcademicaTres;
	
	private ExperienciaProfissional experienciaProfissionalUm;
	private ExperienciaProfissional experienciaProfissionalDois;
	private ExperienciaProfissional experienciaProfissionalTres;
	
	private AtividadesProfissionais atividadesProfissionaisUm;
	private AtividadesProfissionais atividadesProfissionaisDois;
	private AtividadesProfissionais atividadesProfissionaisTres;
	private AtividadesProfissionais atividadesProfissionaisQuatro;
	private AtividadesProfissionais atividadesProfissionaisCinco;
	private AtividadesProfissionais atividadesProfissionaisSeis;
	
	private InformacoesAdicionais informacoesAdicionaisUm;
	private InformacoesAdicionais informacoesAdicionaisDois;

	

	

	// Salvar usuário
	// -------------------------------------------------------------------------------------
		public void salvar() {

		try {
			
			enderecoDAO.salvar(endereco);
			usuario.setEndereco(endereco);
			usuario.setDataCadastro(new Date());
			dao.salvar(usuario);
			
			Messages.addGlobalInfo("Usuário(a) " + usuario.getNome() + ", salvo com sucesso.");

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar o usuário, tente novamente mais tarde ... ");
		} finally {
			
			fechar();

		}
	}
		
		// Salvar formação
		// -------------------------------------------------------------------------------------
		public void salvarFormacao() {
			System.out.println("Salvar Formação");

			try {

				formacaoAcademica.setUsuario(usuario);
				daoFormacao.salvar(formacaoAcademica);
				carregarCurriculo();

				Messages.addGlobalInfo("Formação  salva com sucesso.");

			} catch (Exception e) {
				Messages.addGlobalError("Não foi possível salvar a formação, tente novamente mais tarde ... ");
			} finally {

				fechar();

			}
		}


	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {
		
		botaoEditar=false;
		botaoSalvar=true;
		

		System.out.println("Método novo");
		listarInfos();
		endereco = new Endereco();
		enderecoDAO = new EnderecoDAO();
		usuario = new Usuario();
		dao = new UsuarioDAO();

	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		System.out.println("Método fechar");
		
		RequestContext.getCurrentInstance().reset("dialogform");
			
		usuario = new Usuario();
		dao = new UsuarioDAO();
		endereco=new Endereco();
	}

	// Carregar
	// -------------------------------------------------------------------------------------------
	public void carregar() {

		try {
			usuario = new Usuario();
			dao = new UsuarioDAO();
			listaUsuario = dao.listar();
			
		
		
			

			Messages.addGlobalInfo("Lista atualizada com sucesso ");

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} finally {
			fechar();
		}

	}
	
	// Carregar Curriculo
			// -------------------------------------------------------------------------------------------
			public void carregarCurriculo() {

				try {
					formacaoAcademica = new FormacaoAcademica();
					daoFormacao = new FormacaoAcademicaDAO();
					listaFormacao = daoFormacao.listar();
					
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

			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			UsuarioDAO dao = new UsuarioDAO();
			Messages.addGlobalInfo("Usuário(a) ' " + usuario.getNome() + "' Removido com sucesso!!!");
			dao.excluir(usuario);

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + usuario.getNome());

		} finally {
			fechar();
		}

	}

	// Editar usuário
	// -------------------------------------------------------------------------------------------
	public void editar() {

		try {
			
			listarInfos();
			dao = new UsuarioDAO();
			dao.merge(usuario);
			
			
			Messages.addGlobalInfo("Usuário(a) ' " + usuario.getNome() + "' Editado com sucesso!!!");

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar Usuário(a) '" + usuario.getNome() + "'");

		} finally {
			
			fechar();
		}

	}

	// Salvar Senha
	// -------------------------------------------------------------------------------------------
	public void editarSenha() {

		try {

			dao = new UsuarioDAO();
			dao.merge(usuario);
			Messages.addGlobalInfo("Usuário Editado com sucesso: " + usuario.getNome());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

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
			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());
			endereco=usuario.getEndereco();
			listarInfos();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------
	
	public void getinstanciaCurriculo(ActionEvent evento) {
		System.out.println("Método Curriculo: "+usuario.getNome());

		try {
			
			carregarCurriculo();
			
			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());
			
			
			System.out.println("Usuario selecionado: "+usuario.getNome());
			
			
			curriculo = new Curriculo();
			
			formacaoAcademicaUm = new FormacaoAcademica();
			formacaoAcademicaDois = new FormacaoAcademica();
			formacaoAcademicaTres = new FormacaoAcademica();
			
			 experienciaProfissionalUm = new ExperienciaProfissional();
			 experienciaProfissionalDois = new ExperienciaProfissional();
			 experienciaProfissionalTres = new ExperienciaProfissional();
			 
			 
			 atividadesProfissionaisUm = new AtividadesProfissionais();
			 atividadesProfissionaisDois = new AtividadesProfissionais();
			 atividadesProfissionaisTres = new AtividadesProfissionais();
			 atividadesProfissionaisQuatro = new AtividadesProfissionais();
			 atividadesProfissionaisCinco = new AtividadesProfissionais();
			 atividadesProfissionaisSeis = new AtividadesProfissionais();
			 
			 informacoesAdicionaisUm = new InformacoesAdicionais();
			 informacoesAdicionaisDois = new InformacoesAdicionais();
	

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());
			System.out.println("catch do Método Curriculo: "+usuario.getNome());

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
			
			System.out.println("Filtrar Cidade");
			cidadeDao = new CidadeDAO();
			listaCidade = cidadeDao.buscarPorEstado(estado.getCodigo());	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
		

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuario() {
		return listaUsuario;
	}

	public void setListaUsuario(List<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Curriculo getCurriculo() {
		return curriculo;
	}

	public void setCurriculo(Curriculo curriculo) {
		this.curriculo = curriculo;
	}

	public FormacaoAcademica getFormacaoAcademicaUm() {
		return formacaoAcademicaUm;
	}

	public void setFormacaoAcademicaUm(FormacaoAcademica formacaoAcademicaUm) {
		this.formacaoAcademicaUm = formacaoAcademicaUm;
	}

	public FormacaoAcademica getFormacaoAcademicaDois() {
		return formacaoAcademicaDois;
	}

	public void setFormacaoAcademicaDois(FormacaoAcademica formacaoAcademicaDois) {
		this.formacaoAcademicaDois = formacaoAcademicaDois;
	}

	public FormacaoAcademica getFormacaoAcademicaTres() {
		return formacaoAcademicaTres;
	}

	public void setFormacaoAcademicaTres(FormacaoAcademica formacaoAcademicaTres) {
		this.formacaoAcademicaTres = formacaoAcademicaTres;
	}

	public ExperienciaProfissional getExperienciaProfissionalUm() {
		return experienciaProfissionalUm;
	}

	public void setExperienciaProfissionalUm(ExperienciaProfissional experienciaProfissionalUm) {
		this.experienciaProfissionalUm = experienciaProfissionalUm;
	}

	public ExperienciaProfissional getExperienciaProfissionalDois() {
		return experienciaProfissionalDois;
	}

	public void setExperienciaProfissionalDois(ExperienciaProfissional experienciaProfissionalDois) {
		this.experienciaProfissionalDois = experienciaProfissionalDois;
	}

	public ExperienciaProfissional getExperienciaProfissionalTres() {
		return experienciaProfissionalTres;
	}

	public void setExperienciaProfissionalTres(ExperienciaProfissional experienciaProfissionalTres) {
		this.experienciaProfissionalTres = experienciaProfissionalTres;
	}

	public AtividadesProfissionais getAtividadesProfissionaisUm() {
		return atividadesProfissionaisUm;
	}

	public void setAtividadesProfissionaisUm(AtividadesProfissionais atividadesProfissionaisUm) {
		this.atividadesProfissionaisUm = atividadesProfissionaisUm;
	}

	public AtividadesProfissionais getAtividadesProfissionaisDois() {
		return atividadesProfissionaisDois;
	}

	public void setAtividadesProfissionaisDois(AtividadesProfissionais atividadesProfissionaisDois) {
		this.atividadesProfissionaisDois = atividadesProfissionaisDois;
	}

	public AtividadesProfissionais getAtividadesProfissionaisTres() {
		return atividadesProfissionaisTres;
	}

	public void setAtividadesProfissionaisTres(AtividadesProfissionais atividadesProfissionaisTres) {
		this.atividadesProfissionaisTres = atividadesProfissionaisTres;
	}

	public AtividadesProfissionais getAtividadesProfissionaisQuatro() {
		return atividadesProfissionaisQuatro;
	}

	public void setAtividadesProfissionaisQuatro(AtividadesProfissionais atividadesProfissionaisQuatro) {
		this.atividadesProfissionaisQuatro = atividadesProfissionaisQuatro;
	}

	public AtividadesProfissionais getAtividadesProfissionaisCinco() {
		return atividadesProfissionaisCinco;
	}

	public void setAtividadesProfissionaisCinco(AtividadesProfissionais atividadesProfissionaisCinco) {
		this.atividadesProfissionaisCinco = atividadesProfissionaisCinco;
	}

	public AtividadesProfissionais getAtividadesProfissionaisSeis() {
		return atividadesProfissionaisSeis;
	}

	public void setAtividadesProfissionaisSeis(AtividadesProfissionais atividadesProfissionaisSeis) {
		this.atividadesProfissionaisSeis = atividadesProfissionaisSeis;
	}

	public InformacoesAdicionais getInformacoesAdicionaisUm() {
		return informacoesAdicionaisUm;
	}

	public void setInformacoesAdicionaisUm(InformacoesAdicionais informacoesAdicionaisUm) {
		this.informacoesAdicionaisUm = informacoesAdicionaisUm;
	}

	public InformacoesAdicionais getInformacoesAdicionaisDois() {
		return informacoesAdicionaisDois;
	}

	public void setInformacoesAdicionaisDois(InformacoesAdicionais informacoesAdicionaisDois) {
		this.informacoesAdicionaisDois = informacoesAdicionaisDois;
	}

	public FormacaoAcademica getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}

	public List<FormacaoAcademica> getListaFormacao() {
		return listaFormacao;
	}

	public void setListaFormacao(List<FormacaoAcademica> listaFormacao) {
		this.listaFormacao = listaFormacao;
	}


	
	
	
	
	
	

}