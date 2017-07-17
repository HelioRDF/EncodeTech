package br.com.encodetech.dao;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.encodetech.dao.complementos.FichaDAO;
import br.com.encodetech.dao.complementos.OportunidadeDAO;
import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.dao.localizacao.CidadeDAO;
import br.com.encodetech.dao.usuarios.UsuarioDAO;
import br.com.encodetech.domain.complementos.Ficha;
import br.com.encodetech.domain.complementos.Oportunidade;
import br.com.encodetech.domain.empresas.Empresa;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.usuarios.Usuario;

public class OportunidadeDAOTest {

	@Test
	//@Ignore
	public void selecionarOportunidades() {
		
		//Oportunidade oportunidade= new Oportunidade(); 
		OportunidadeDAO dao = new OportunidadeDAO();
		//Empresa empresa = new Empresa();
		//EmpresaDAO empresaDAO = new EmpresaDAO();
		//empresa = empresaDAO.buscar(1l);
		
		dao.excluirOportunidades(2l);
		
		

	}

	@Test
	@Ignore
	public void listarQuestionario() {

	}

	@Test
	@Ignore
	public void listarFichas() {
		Oportunidade oportunidade = new Oportunidade();
		OportunidadeDAO dao = new OportunidadeDAO();
		oportunidade = dao.buscar(1l);

		int total = oportunidade.getCandidatos().size();
		System.out.println("\n -----------------");
		System.out.println("\n Total: " + total);
		System.out.println("\n -----------------");

		for (Ficha opor : oportunidade.getCandidatos()) {
			System.out.println("\nLista:" + opor.getCodigo());
		}
	}

	@Test
	@Ignore
	public void salvarFicha() throws InterruptedException {

		Usuario usuario = new Usuario();
		UsuarioDAO daoUser = new UsuarioDAO();
		usuario = daoUser.buscar(3l);

		Oportunidade oportunidade = new Oportunidade();
		OportunidadeDAO dao = new OportunidadeDAO();
		oportunidade = dao.buscar(3l);

		FichaDAO fichaDAO = new FichaDAO();
		Ficha fichaA = new Ficha();
		fichaA.setOportunidade_id(oportunidade);
		fichaA.setDataCadastro(new Date());
		fichaA.setResposta("teste");

		System.out.println("\n -----------------");
		System.out.println("\n\nCod:" + fichaA.getCodigo());
		
		System.out.println("Data: " + fichaA.getDataCadastro());
		System.out.println("\n -----------------");

		dao.merge(oportunidade);
		fichaDAO.merge(fichaA);
	}

	@Test
	@Ignore
	public void salvar() {

		Oportunidade oportunidade = new Oportunidade();
		OportunidadeDAO dao = new OportunidadeDAO();

		Cidade cidade = new Cidade();
		CidadeDAO cidadeDAO = new CidadeDAO();
		cidade = cidadeDAO.buscar(1l);

		Empresa empresa = new Empresa();
		EmpresaDAO empresaDAO = new EmpresaDAO();
		empresa = empresaDAO.buscar(1l);

		oportunidade.setArea("Ninja");
		oportunidade.setBeneficios("Armas");
		oportunidade.setCargo("Assasino");
		oportunidade.setDataCadastro(new Date());
		oportunidade.setDescricao("Matar ou morrer");
		oportunidade.setHorario("Noite");
		oportunidade.setModalidade("Missão");
		oportunidade.setMostrarEmpresa(true);
		oportunidade.setMostrarSalario(true);
		oportunidade.setNivel("Master");
		oportunidade.setPcd(false);
		oportunidade.setPreRequisitos("Nível A");
		// oportunidade.setQuantidade((Short)3);
		// oportunidade.setSalario(333);
		// oportunidade.setSalarioAux(3333,00);
		oportunidade.setCidade(cidade);
		oportunidade.setEmpresa(empresa);
		// oportunidade.setEstado(cidade);
		dao.salvar(oportunidade);
	}
}
