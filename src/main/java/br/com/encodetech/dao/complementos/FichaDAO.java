package br.com.encodetech.dao.complementos;

import org.hibernate.Query;
import org.hibernate.Session;

import br.com.encodetech.domain.complementos.Ficha;
import br.com.encodetech.util.HibernateUtil;

public class FichaDAO extends GenericDAO<Ficha> {
	
	
	
//Exclui as fichas vinculadas a oportunidades excluidas.
	public void excluirFichasVinculadas (Long oportunidadeCod) {
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();

		try {
			
//			//Exclui as fichas vinculadas a uma lista de oprtunidades especifica de uma empresa.
//			Criteria consulta = sessao.createCriteria(Oportunidade.class);
//			consulta.add(Restrictions.eq("empresa_codigo", empresaCod));
//			@SuppressWarnings("unchecked")
//			List <Oportunidade> resultado = consulta.list();
//			for (Oportunidade obj : resultado) {
//				//Exclui as fichas vinculadas a oportunidade
//				FichaDAO fichaDAO = new FichaDAO();
//				fichaDAO.excluirFichasVinculadas(obj.getCodigo());
//			}

			//Query escrita em HQL.
			Query query = sessao.createQuery("DELETE  ficha  where oportunidade_id = :meuCodigo");
			query.setParameter("meuCodigo", oportunidadeCod);
			int result = query.executeUpdate();
			
			System.out.println("\nQuantidade de Fichas deletadas:"+result);

		} catch (RuntimeException erro) {

			System.out.println("\n\nErro em excluir Oportunidades: (OportunidadeDAO)"+erro.getMessage());

		} finally {
			sessao.close();
		}

	}

}
