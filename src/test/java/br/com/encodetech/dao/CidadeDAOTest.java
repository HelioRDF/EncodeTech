package br.com.encodetech.dao;

import org.junit.Test;

import br.com.encodetech.domain.Cidade;
import br.com.encodetech.domain.Estado;

public class CidadeDAOTest extends GenericDAO<Cidade> {
	
	@Test
	public void salvar(){
		
		
		Estado estado = new Estado();
		estado.setNome("São Paulo");
		estado.setSigla("SP");
		
		Cidade cidade = new Cidade();
		cidade.setNome("Embu-Guaçu");
		cidade.setEstado(estado);
		

		CidadeDAO cidadeDAO = new CidadeDAO();
		EstadoDAO estadoDAO = new EstadoDAO();
		estadoDAO.salvar(estado);
		cidadeDAO.salvar(cidade);
		
	}

}
