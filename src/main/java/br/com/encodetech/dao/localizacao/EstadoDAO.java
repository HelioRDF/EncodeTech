package br.com.encodetech.dao.localizacao;



import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.localizacao.Estado;
import br.com.encodetech.util.HibernateUtil;

public class EstadoDAO extends GenericDAO<Estado> {
	
	
	public String buscarEstadoPorCodigo(Long codigo){
		
		
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		
		Criteria consulta = sessao.createCriteria(Estado.class);
		consulta.add(Restrictions.eq("codigo", codigo));
		
		Estado obj =(Estado) consulta.uniqueResult();
		String resultado = obj.getNome();
		System.out.println("Retornando Estado: "+resultado);
		
		return resultado;
		
		
	}
	

}
