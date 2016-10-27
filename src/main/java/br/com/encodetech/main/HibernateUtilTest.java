package br.com.encodetech.main;

import org.hibernate.Session;

import br.com.encodetech.util.HibernateUtil;

public class HibernateUtilTest {
	public static void main(String[] args) {
		
		Session sessao = HibernateUtil.getFabricadeSessoes().openSession();
		sessao.close();//Fecha a sessão
		HibernateUtil.getFabricadeSessoes().close();//Fecha a fabrica de sessão

	}
}
