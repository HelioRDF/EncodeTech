package br.com.encodetech.dao.localizacao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.encodetech.dao.complementos.GenericDAO;
import br.com.encodetech.domain.localizacao.Cidade;
import br.com.encodetech.domain.localizacao.Estado;

/**
 * @author Helio Franca
 *
 */
public class CidadeDAOTest extends GenericDAO<Cidade> {

	@Test
	
	public void salvar() {
		Long codigoEstado = 1L;

		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);

		Cidade cidade = new Cidade();
		cidade.setNome("Embu-Guaçu");
		cidade.setEstado(estado);

		CidadeDAO cidadeDAO = new CidadeDAO();
		cidadeDAO.salvar(cidade);
	}

	@Test
	@Ignore
	public void lista() {

		CidadeDAO dao = new CidadeDAO();
		List<Cidade> resultado = dao.listar();

		for (Cidade cidade : resultado) {

			System.out.print("\nCod:" + cidade.getCodigo());
			System.out.print("\tCidade Nome:" + cidade.getNome());

			System.out.print("\tEstado Nome:" + cidade.getEstado().getNome());
			System.out.print("\tSigla Estado:" + cidade.getEstado().getSigla());

		}

	}

	@Test
	@Ignore
	public void buscar() {
		Long codigo = 7L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);

		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
	}

	@Test
	@Ignore
	public void excluir() {
		Long codigo = 7L;

		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigo);
		cidadeDAO.excluir(cidade);

		System.out.println("Cidade Removida");
		System.out.println("Código da Cidade:" + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());
	}

	@Test
	@Ignore
	public void editar() {
		Long codigoCidade = 6L;
		Long codigoEstado = 11L;
		EstadoDAO estadoDAO = new EstadoDAO();
		Estado estado = estadoDAO.buscar(codigoEstado);
		
		System.out.println("Código do Estado: " + estado.getCodigo());
		System.out.println("Sigla do Estado: " + estado.getSigla());
		System.out.println("Nome do Estado: " + estado.getNome());
		
		CidadeDAO cidadeDAO = new CidadeDAO();
		Cidade cidade = cidadeDAO.buscar(codigoCidade);
		
		System.out.println("Cidade A Ser Editada");
		System.out.println("Código da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do  Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do Estado: " + cidade.getEstado().getNome());

		cidade.setNome("Guarapuava");
		cidade.setEstado(estado);

		cidadeDAO.editar(cidade);

		System.out.println("Cidade Editada");
		System.out.println("Código  da Cidade: " + cidade.getCodigo());
		System.out.println("Nome da Cidade: " + cidade.getNome());
		System.out.println("Código do  Estado: " + cidade.getEstado().getCodigo());
		System.out.println("Sigla do Estado: " + cidade.getEstado().getSigla());
		System.out.println("Nome do  Estado: " + cidade.getEstado().getNome());
	}

}