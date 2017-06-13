package br.com.encodetech.dao.usuarios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.usuarios.FormacaoAcademica;
import br.com.encodetech.util.HibernateUtil;

public class FormacaoAcademicaDAO extends GenericDAO<FormacaoAcademica>{
	
	
	
	
@SuppressWarnings("unchecked")
public List<FormacaoAcademica> buscarPorUsuario(Long usuarioCodigo){
		
		
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(FormacaoAcademica.class);
			consulta.add(Restrictions.eq("usuario.codigo", usuarioCodigo));
						
			List<FormacaoAcademica> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		}finally{
		sessao.close();	
		}
		
		
	}

// Excluir Formações
// -------------------------------------------------------------------------------------------


public void excluirFormacoes (Long usuarioCod) {
	Session sessao = HibernateUtil.getFabricadeSessoes().openSession();


	try {
		
		Query query = sessao.createQuery("delete FormacaoAcademica where usuarioCodigo = :usuarioCodigo");
		query.setParameter("usuarioCodigo", usuarioCod);
		int result = query.executeUpdate();
		
		System.out.println("Quantidade de Formação deletada:"+result);

	} catch (RuntimeException erro) {

		System.out.println("Erro em excluir Formacoes: (FormacaooAcademicaDAO)");

	} finally {
		sessao.close();
	}

}

}
