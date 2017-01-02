package br.com.encodetech.dao.usuarios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.usuarios.ExperienciaProfissional;
import br.com.encodetech.util.HibernateUtil;

public class ExperienciaProfissionalDAO extends GenericDAO<ExperienciaProfissional>{
	
	
	@SuppressWarnings("unchecked")
	public List<ExperienciaProfissional> buscarPorUsuario(Long usuarioCodigo){
			
			
			Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
			try {
				Criteria consulta = sessao.createCriteria(ExperienciaProfissional.class);
				consulta.add(Restrictions.eq("usuario.codigo", usuarioCodigo));
							
				List<ExperienciaProfissional> resultado = consulta.list();
				return resultado;

			} catch (RuntimeException erro) {
				throw erro;
			}finally{
			sessao.close();	
			}
			
			
		}
	
	

}
