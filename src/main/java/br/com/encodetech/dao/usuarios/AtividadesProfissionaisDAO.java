package br.com.encodetech.dao.usuarios;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.usuarios.AtividadesProfissionais;
import br.com.encodetech.util.HibernateUtil;

public class AtividadesProfissionaisDAO extends GenericDAO<AtividadesProfissionais>{
		
		
		
		
		@SuppressWarnings("unchecked")
		public List<AtividadesProfissionais> buscarPorUsuario(Long usuarioCodigo){
				
				
				Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
				try {
					Criteria consulta = sessao.createCriteria(AtividadesProfissionais.class);
					consulta.add(Restrictions.eq("usuario.codigo", usuarioCodigo));
								
					List<AtividadesProfissionais> resultado = consulta.list();
					return resultado;

				} catch (RuntimeException erro) {
					throw erro;
				}finally{
				sessao.close();	
				}
				
				
			}
		
		// Excluir AtividadesProfissionais
		// -------------------------------------------------------------------------------------------


		public void excluirAtividadesProfissionais (Long usuarioCod) {
			Session sessao = HibernateUtil.getFabricadeSessoes().openSession();


			try {
				
				Query query = sessao.createQuery("delete AtividadesProfissionais where usuarioCodigo = :usuarioCodigo");
				query.setParameter("usuarioCodigo", usuarioCod);
				int result = query.executeUpdate();
				
				System.out.println("Quantidade de Atividades Profissionais deletadas:"+result);

			} catch (RuntimeException erro) {

				System.out.println("Erro em excluir AtividadesProfissionais: (AtividadesProfissionaisDAO)");

			} finally {
				sessao.close();
			}

		}
		
		

}
