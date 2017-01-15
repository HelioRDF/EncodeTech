
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
import br.com.encodetech.dao.usuarios.AtividadesProfissionaisDAO;
import br.com.encodetech.dao.usuarios.ExperienciaProfissionalDAO;
import br.com.encodetech.dao.usuarios.FormacaoAcademicaDAO;
import br.com.encodetech.dao.usuarios.InformacoesAdicionaisDAO;
import br.com.encodetech.dao.usuarios.UsuarioDAO;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Endereco;
import br.com.encodetech.domain.localizacao.Estado;
import br.com.encodetech.domain.usuarios.AtividadesProfissionais;
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
	private String auxCidade="Selecione uma Cidade";
	
	private Estado estado;
	private EstadoDAO estadoDao;
	private String auxEstado=" Selecione um Estado";
	
	private Boolean telaEditar =false;

	private Endereco endereco;
	private EnderecoDAO enderecoDAO;

	private Boolean botaoEditar = false;
	private Boolean botaoSalvar = false;

	private FormacaoAcademica formacaoAcademica;
	private List<FormacaoAcademica> listaFormacao;
	private FormacaoAcademicaDAO daoFormacao;
	private Boolean botaoFormacao = false;
	private Boolean statusBoolean = false;

	private ExperienciaProfissional experienciaProfissional;
	private List<ExperienciaProfissional> listaExperiencia;
	private ExperienciaProfissionalDAO daoExperiencia;
	private Boolean botaoExperiencia = false;

	private AtividadesProfissionais atividadesProfissionais;
	private List<AtividadesProfissionais> listaAtividades;
	private AtividadesProfissionaisDAO daoAtividades;
	private Boolean botaoAtividades = false;
	
	private InformacoesAdicionais informacoesAdicionais;
	private List<InformacoesAdicionais> listInfo;
	private InformacoesAdicionaisDAO daoInfo;
	private Boolean botaoInfo;
	


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

			System.out.println("Erro" + e);

		} finally {

			fechar();

		}
	}

	// Salvar formação
	// -------------------------------------------------------------------------------------
	public void salvarFormacao() {

		try {
			if (botaoFormacao = true) {

				if (statusBoolean.equals(true)) {
					formacaoAcademica.setStatus("Concluído");
				} else {
					formacaoAcademica.setStatus("Incompleto");
				}

				formacaoAcademica.setUsuario(usuario);
				daoFormacao.merge(formacaoAcademica);

				Messages.addGlobalInfo("Formação  salva com sucesso: " + formacaoAcademica.getNomeCurso());
				carregarCurriculo();
			}

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar a formação, Preencha os campos corretamente. ");

		} finally {

		}
	}

	// Salvar formação
	// -------------------------------------------------------------------------------------
	public void salvarExperiencia() {

		try {
			if (botaoExperiencia = true) {
				experienciaProfissional.setUsuario(usuario);
				daoExperiencia.merge(experienciaProfissional);
				Messages.addGlobalInfo("Experiencia  salva com sucesso: " + experienciaProfissional.getCargo());
				carregarCurriculo();
			}

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar a formação, Preencha os campos corretamente. ");

		} finally {

		}
	}

	// Salvar Atividades
	// -------------------------------------------------------------------------------------
	public void salvarAtividades() {

		try {
			if (botaoAtividades = true) {
				atividadesProfissionais.setUsuario(usuario);
				daoAtividades.merge(atividadesProfissionais);
				Messages.addGlobalInfo("Qualificação  salva com sucesso: " + atividadesProfissionais.getNomeCurso());
				carregarCurriculo();
			}

		} catch (Exception e) {
			Messages.addGlobalError("Não foi possível salvar a Qualificação, Preencha os campos corretamente. ");

		} finally {

		}
	}

	
	
	// Salvar Infos
		// -------------------------------------------------------------------------------------
		public void salvarInfo() {

			try {
				if (botaoInfo = true) {
					informacoesAdicionais.setUsuario(usuario);
					daoInfo.merge(informacoesAdicionais);
					Messages.addGlobalInfo("Informação salva com sucesso: ");
					carregarCurriculo();
				}

			} catch (Exception e) {
				Messages.addGlobalError("Não foi possível salvar as Informações, Preencha os campos corretamente. ");

			} finally {

			}
		}

	
	
	
	// Novo
	// -------------------------------------------------------------------------------------------
	public void novo() {
		
		telaEditar=false;
		listarInfos();
		botaoEditar = false;
		botaoSalvar = true;
		auxCidade="Selecione uma Cidade";
		auxEstado=" Selecione um Estado";
		

		usuario = new Usuario();
		endereco = new Endereco();
		enderecoDAO = new EnderecoDAO();
		dao = new UsuarioDAO();

		
	}

	// Fechar
	// -------------------------------------------------------------------------------------------
	public void fechar() {
		System.out.println("Método fechar");

		RequestContext.getCurrentInstance().reset("dialogform");

		usuario = new Usuario();
		dao = new UsuarioDAO();
		endereco = new Endereco();
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
		

		
			
		// Atividades Profissionais  ----------------------------------------------------------
		
		try {

			atividadesProfissionais = new AtividadesProfissionais();
			daoAtividades = new AtividadesProfissionaisDAO();
			listaAtividades = daoAtividades.buscarPorUsuario(usuario.getCodigo());

			if (listaAtividades.size() < 10) {
				botaoAtividades = true;

			} else {
				botaoAtividades = false;
				Messages.addGlobalWarn("Numéro maximo de Qualificações atingido. (max = 10)");

			}

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} 
		
		// Fim Atividades Profissionais  ----------------------------------------------------------
		
		
		
		
		// Experiência  ----------------------------------------------------------
		try {

			experienciaProfissional = new ExperienciaProfissional();
			daoExperiencia = new ExperienciaProfissionalDAO();
			listaExperiencia = daoExperiencia.buscarPorUsuario(usuario.getCodigo());

			if (listaExperiencia.size() < 4) {
				botaoExperiencia = true;

			} else {
				botaoExperiencia = false;
				Messages.addGlobalWarn("Numéro maximo de Experiência atingido. (max = 4)");

			}

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		} 
		// Fim Experiência  ----------------------------------------------------------
		
			
		// Formação Academica  ----------------------------------------------------------
		try {
			formacaoAcademica = new FormacaoAcademica();
			daoFormacao = new FormacaoAcademicaDAO();
			listaFormacao = daoFormacao.buscarPorUsuario(usuario.getCodigo());

			if (listaFormacao.size() < 7) {
				botaoFormacao = true;

			} else {
				botaoFormacao = false;
				Messages.addGlobalWarn("Numéro maximo de Formações atingido. (max = 7)");

			}

		} catch (Exception e) {
			Messages.addGlobalError("Falha ao tentar  atualizadar a lista  ");
		}
		
		// Fim Formação Academica  ----------------------------------------------------------

	
	
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

	// Excluir Formacao
	// -------------------------------------------------------------------------------------------
	public void excluirFormacao(ActionEvent evento) {

		try {

			formacaoAcademica = (FormacaoAcademica) evento.getComponent().getAttributes().get("meuSelect");

			FormacaoAcademicaDAO dao = new FormacaoAcademicaDAO();
			Messages.addGlobalInfo("Formação removida com sucesso: " + formacaoAcademica.getNomeCurso());
			dao.excluir(formacaoAcademica);
			carregarCurriculo();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + formacaoAcademica.getNomeCurso());

		} finally {

		}

	}

	// Excluir Formacao
	// -------------------------------------------------------------------------------------------
	public void excluirExperiencia(ActionEvent evento) {

		try {

			experienciaProfissional = (ExperienciaProfissional) evento.getComponent().getAttributes().get("meuSelect");

			ExperienciaProfissionalDAO dao = new ExperienciaProfissionalDAO();
			Messages.addGlobalInfo("Experiência removida com sucesso: " + experienciaProfissional.getCargo());
			dao.excluir(experienciaProfissional);
			carregarCurriculo();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + experienciaProfissional.getCargo());

		} finally {

		}

	}

	// Excluir Qualificação
	// -------------------------------------------------------------------------------------------
	public void excluirQualificacao(ActionEvent evento) {

		try {

			atividadesProfissionais = (AtividadesProfissionais) evento.getComponent().getAttributes().get("meuSelect");

			AtividadesProfissionaisDAO dao = new AtividadesProfissionaisDAO();
			Messages.addGlobalInfo("Qualificação removida com sucesso: " + atividadesProfissionais.getNomeCurso());
			dao.excluir(atividadesProfissionais);
			carregarCurriculo();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: " + atividadesProfissionais.getNomeCurso());

		} finally {

		}

	}
	
	// Excluir Info
	// -------------------------------------------------------------------------------------------
	public void excluirInfo(ActionEvent evento) {

		try {

			informacoesAdicionais = (InformacoesAdicionais) evento.getComponent().getAttributes().get("meuSelect");

			InformacoesAdicionaisDAO daoinf = new InformacoesAdicionaisDAO();
			Messages.addGlobalInfo("Qualificação removida com sucesso: " + informacoesAdicionais.getCargoPretendido());
			daoinf.excluir(informacoesAdicionais);
			carregarCurriculo();

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Remover: "  + informacoesAdicionais.getCargoPretendido());

		} finally {

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

			telaEditar = true;
			botaoSalvar = false;
			botaoEditar = true;

			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());

			endereco = usuario.getEndereco();
			
			auxCidade=usuario.getEndereco().getCidade().getNome() ;
			auxEstado=usuario.getEndereco().getCidade().getEstado().getNome();
			
			
			listarInfos();
			cidadeDao = new CidadeDAO();
			listaCidade = cidadeDao.buscarPorEstado(estado.getCodigo());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());

		}

	}

	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getinstanciaCurriculo(ActionEvent evento) {
	

		try {

			usuario = (Usuario) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + usuario.getNome());

			carregarCurriculo();

			

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + usuario.getNome());
			System.out.println("catch do Método Curriculo: " + usuario.getNome());

		}

	}

	// Instancia de Formação
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getinstanciaFormação(ActionEvent evento) {

		try {
			botaoFormacao = true;

			formacaoAcademica = (FormacaoAcademica) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + formacaoAcademica.getNomeCurso());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + formacaoAcademica.getNomeCurso());

		}

	}

	// Instancia de Experiencia
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getinstanciaExperiencia(ActionEvent evento) {

		try {
			botaoExperiencia = true;

			experienciaProfissional = (ExperienciaProfissional) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + experienciaProfissional.getCargo());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + experienciaProfissional.getCargo());

		}

	}

	// Instancia de Qualificações
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getinstanciaQualificacao(ActionEvent evento) {

		try {
			botaoAtividades = true;

			atividadesProfissionais = (AtividadesProfissionais) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + atividadesProfissionais.getNomeCurso());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + atividadesProfissionais.getNomeCurso());

		}

	}

	
	
	// Instancia de Infos
	// ------------------------------------------------------------------------------------------------------------------------------------------------------

	public void getinstanciaInfo(ActionEvent evento) {

		try {
			botaoInfo = true;

			informacoesAdicionais = (InformacoesAdicionais) evento.getComponent().getAttributes().get("meuSelect");
			Messages.addGlobalInfo("Seleção: " + informacoesAdicionais.getCargoPretendido());

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Editar: " + informacoesAdicionais.getCargoPretendido());
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

	public void filtrarCidade() {

		try {

		
			cidadeDao = new CidadeDAO();
			listaCidade = cidadeDao.buscarPorEstado(estado.getCodigo());
			auxCidade="Selecione uma Cidade";
			
			

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

	public Boolean getBotaoFormacao() {
		return botaoFormacao;
	}

	public void setBotaoFormacao(Boolean botaoFormacao) {
		this.botaoFormacao = botaoFormacao;
	}

	public ExperienciaProfissional getExperienciaProfissional() {
		return experienciaProfissional;
	}

	public void setExperienciaProfissional(ExperienciaProfissional experienciaProfissional) {
		this.experienciaProfissional = experienciaProfissional;
	}

	public List<ExperienciaProfissional> getListaExperiencia() {
		return listaExperiencia;
	}

	public void setListaExperiencia(List<ExperienciaProfissional> listaExperiencia) {
		this.listaExperiencia = listaExperiencia;
	}

	public Boolean getBotaoExperiencia() {
		return botaoExperiencia;
	}

	public void setBotaoExperiencia(Boolean botaoExperiencia) {
		this.botaoExperiencia = botaoExperiencia;
	}

	public AtividadesProfissionais getAtividadesProfissionais() {
		return atividadesProfissionais;
	}

	public void setAtividadesProfissionais(AtividadesProfissionais atividadesProfissionais) {
		this.atividadesProfissionais = atividadesProfissionais;
	}

	public List<AtividadesProfissionais> getListaAtividades() {
		return listaAtividades;
	}

	public void setListaAtividades(List<AtividadesProfissionais> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public Boolean getBotaoAtividades() {
		return botaoAtividades;
	}

	public void setBotaoAtividades(Boolean botaoAtividades) {
		this.botaoAtividades = botaoAtividades;
	}

	public Boolean getStatusBoolean() {
		return statusBoolean;
	}

	public void setStatusBoolean(Boolean statusBoolean) {
		this.statusBoolean = statusBoolean;
	}

	public InformacoesAdicionais getInformacoesAdicionais() {
		return informacoesAdicionais;
	}

	public void setInformacoesAdicionais(InformacoesAdicionais informacoesAdicionais) {
		this.informacoesAdicionais = informacoesAdicionais;
	}

	public List<InformacoesAdicionais> getListInfo() {
		return listInfo;
	}

	public void setListInfo(List<InformacoesAdicionais> listInfo) {
		this.listInfo = listInfo;
	}

	public Boolean getBotaoInfo() {
		return botaoInfo;
	}

	public void setBotaoInfo(Boolean botaoInfo) {
		this.botaoInfo = botaoInfo;
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
	
	

	
}