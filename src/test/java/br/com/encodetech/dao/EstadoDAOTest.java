package br.com.encodetech.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

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
	public void salvar(){
		Estado estado = new Estado();
		estado.setNome("Rio de Janeiro");
		estado.setSigla("RJ");
		
		Estado estado2 = new Estado();
		estado2.setNome("São Paulo");
		estado2.setSigla("SP");
		
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		estadoDAO.salvar(estado2);
		
		
	}
	
	@Test
	public void listar() {
		EstadoDAO estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		for (Estado estado : resultado) {
			System.out.println("\nSigla: " + estado.getSigla() + "\n" + "Nome: " + estado.getNome());
		}

	}


	}
