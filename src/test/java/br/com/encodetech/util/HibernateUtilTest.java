package br.com.encodetech.util;

import org.hibernate.Session;
import org.junit.Test;

public class HibernateUtilTest {

	@Test
	public void conectar() {

		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		sessao.close();// Fecha a sessão
		HibernateUtil.getFabricadeSessoes().close();// Fecha a fabrica de sessão

	}

}
