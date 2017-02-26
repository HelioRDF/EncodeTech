package br.com.encodetech.dao.localizacao;

import org.junit.Test;

import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Endereco;
import br.com.encodetech.domain.localizacao.Estado;

public class EnderecoDAOTest {
	
	
	@Test
	public void salvar(){
		
		
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = new Estado();
		estado.setNome("SPXXX");
		estado.setSigla("XX");
		estadoDAO.salvar(estado);
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = new Cidade();
		cidade.setEstado(estado);
		cidade.setNome("XXXSPPPPPP");
		cidadeDAO.salvar(cidade);
		
		EnderecoDAO dao = new EnderecoDAO();
		Endereco endereco = new Endereco();
		endereco.setBairro("PQ SP");
		endereco.setCelular("12312312");
		endereco.setCep("123123123");

		endereco.setEstado(estado);
		endereco.setCidade(cidade);
		
		endereco.setComplemento("Igrejinha");
		endereco.setNumero("224");
		endereco.setRua("Josivlado");
		endereco.setTelefone("23243");
		dao.salvar(endereco);
		
//		EnderecoDAO dao2 = new EnderecoDAO();
//		Endereco endereco2 = new Endereco();
//		
//		CidadeDAO cidadeDAO2 = new CidadeDAO();
//		Cidade cidade2 = new Cidade();
//		
//		EstadoDAO estadoDAO2 = new EstadoDAO();
//		Estado estado2 = new Estado();
//	
		
	}

}
