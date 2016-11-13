package br.com.encodetech.dao.localizacao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.encodetech.dao.localizacao.EstadoDAO;
import br.com.encodetech.domain.localizacao.Estado;

/**
 * Detalhes:
 * 
 * -Test - Informa que o método é um test
 * -Ignore - Informa que não é para testar o método
 * 
 */

public class EstadoDAOTest {

	@Test
	@Ignore
	public void salvar() {
		Estado estado = new Estado();
		estado.setNome("Rio Grande do Sul");
		estado.setSigla("RS");

		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		
		Estado estado2 = new Estado();
		estado2.setNome("São Paulo");
		estado2.setSigla("SP");
		
		EstadoDAO estadoDAO2 = new EstadoDAO();
		estadoDAO2.salvar(estado2);
		
		Estado estado3 = new Estado();
		estado3.setNome("Rio de Janeiro");
		estado3.setSigla("RJ");

		EstadoDAO estadoDAO3 = new EstadoDAO();
		estadoDAO3.salvar(estado3);

	
	
	}

	@Test
	//@Ignore
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();

		System.out.println("Total de Registros Encontrados: " + resultado.size());

		for (Estado estado : resultado) {
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);

		if (estado == null) {
			System.out.println("Nenhum registro encontrado");
		} else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo() + " - " + estado.getSigla() + " - " + estado.getNome());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		Long codigo = 3L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigo);
		estadoDAO.excluir(estado);
		
	}
	
	@Test
	public void editar(){
		Long codigo=3l;
		EstadoDAO dao = new EstadoDAO();
		Estado estado = new Estado();
		
		estado = dao.buscar(codigo);
		estado.setNome("Tesmillll");
		dao.editar(estado);
	}
	
}