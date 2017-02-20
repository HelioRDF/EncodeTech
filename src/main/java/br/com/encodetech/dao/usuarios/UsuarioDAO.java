package br.com.encodetech.dao.usuarios;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.usuarios.Usuario;
import br.com.encodetech.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	
	public Usuario autenticar(String  email, String senha ){
		
		//Abre uma sessão com Hibernate
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		
		try {
	
			Criteria consulta = sessao.createCriteria(Usuario.class);			
			
		//	consulta.createAlias("Usuario", "user");
			consulta.add(Restrictions.eq("email",email ));
			
			
			SimpleHash hash = new SimpleHash("md5",senha);
			consulta.add(Restrictions.eq("senha", hash.toHex() ));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			
			return resultado;
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		finally {
			sessao.close();
		}
		
		
	
		
	}

}
