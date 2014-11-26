package logica;

import PessoaDao.PessoaDao;
import modelo.Pessoa;

/**
 * Created by Ton on 22/10/2014.
 */
public class PessoaBusinnes {

	private PessoaDao dao = new PessoaDao();

	public void addNovaPessoa(String codigo, String nome, String email) {

		// executar validações, testes de nulidade
		Pessoa pessoa = new Pessoa();
		pessoa.setCodigo(codigo);
		pessoa.setNome(nome);
		pessoa.setEmail(email);

		dao.salvar(pessoa);

	}

	public Pessoa pesquisaPorCodigo(String chave) throws Exception {
		String nova = chave.trim();

		if (nova == null || nova.equals("")) {
			throw new Exception("Campo de pesquisa nulo, inválido");
		} else {
			return dao.pesquisaPorCodigo(chave);
		}

	}
}
