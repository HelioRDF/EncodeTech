package br.com.encodetech.dao;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.junit.Test;

import br.com.encodetech.dao.complementos.OportunidadeDAO;
import br.com.encodetech.dao.empresas.EmpresaDAO;
import br.com.encodetech.domain.complementos.Oportunidade;
import br.com.encodetech.domain.empresas.Empresa;

public class OportunidadeDAOTest {

	
	@Test
	public void salvar() throws ParseException{
		
		Oportunidade oportunidade = new Oportunidade();
		OportunidadeDAO dao = new OportunidadeDAO();
		
		
		EmpresaDAO edao =new  EmpresaDAO();
		Empresa empresa = new Empresa();
		
		empresa.setNomeEmpresa("Inovax");
	//	empresa.setBairro("PQ SP");
		//empresa.setCep("0690000");
		empresa.setCnpj("12312313123");
	//	empresa.setComplemento("Centro");
		//empresa.setDataCadastro(new Date());
		empresa.setDataCadastro(new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("11/11/2016 01:30"));
		empresa.setDescricao("TIC");
	//	empresa.setEmail("Jp@bol");
		//empresa.setNumero(new Short("224"));
	//	empresa.setRua("Libero");
		empresa.setSeguimento("TI");
		empresa.setStatus(true);
		//empresa.setTelefone("9999");
		empresa.setSenha("123!");
		
		edao.salvar(empresa);
		
		
		oportunidade.setDescricao("Vaga na inovax");
		oportunidade.setEmpresa(empresa);
		oportunidade.setQuantidade(new Short("7"));
		oportunidade.setSalario(new BigDecimal(2000.00));
		
		dao.salvar(oportunidade);
	}
}
