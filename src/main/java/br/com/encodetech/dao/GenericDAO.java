package br.com.encodetech.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.encodetech.util.HibernateUtil;

/**
 * [ Detalhes... ]
 * 
 * API Reflection
 * 
 */

public class GenericDAO<Entidade> {
	
	private Class<Entidade> classe;

	//Construtor
	@SuppressWarnings("unchecked")
	public GenericDAO() {
		// API Reflection
		this.classe = (Class<Entidade>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	public void salvar(Entidade entidade) {
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(entidade);
			transacao.commit();

		} catch (RuntimeException erro) {

			if (transacao != null) {
				transacao.rollback();
			}
			throw erro;
		} finally {
			sessao.close();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Entidade> listar() {
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(classe);
			List<Entidade> resultado = consulta.list();
			return resultado;

		} catch (RuntimeException erro) {
			throw erro;
		}finally{
		sessao.close();	
		}
	}

}