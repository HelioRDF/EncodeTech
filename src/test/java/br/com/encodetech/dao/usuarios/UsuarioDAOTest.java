package br.com.encodetech.dao.usuarios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import br.com.encodetech.domain.usuarios.Usuario;

public class UsuarioDAOTest {

	
	@Test
	@Ignore
	public void salvar() throws ParseException{
		String aniversario="23/03/1989";
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario= new Usuario();
		
		usuario.setCpf("21312");
		usuario.setDataCadastro(new Date());
		
		usuario.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(aniversario));
		usuario.setEmail("jp@hotmail");
		usuario.setNome("Helio");
		usuario.setRg("33333");
		usuario.setStatus(true);
		usuario.setSenha("1234556");
		
		dao.salvar(usuario);
		
	}
	
	
	@Test
	public void autenticar(){
		
		String email="teste@bol.com.br";
		String senha="xxxx";
		
		UsuarioDAO dao = new UsuarioDAO();
		Usuario usuario = dao.autenticar(email, senha);
		
		System.out.println("Saida: "+usuario.getNome());
				
				
		
	}
	

}
