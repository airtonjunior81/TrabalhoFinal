package PessoaDao;

import basededados.BancoDados;
import modelo.Pessoa;

import java.util.List;

public class PessoaDao {

    private BancoDados banco = BancoDados.getInstance();

    public void salvar(Pessoa pessoa) {

        banco.tbPessoas.add(pessoa);

        System.out.println("salvando pessoa: " + pessoa);
    }

    public Pessoa pesquisaPorCodigo(String chave) {

        List<Pessoa> pessoas = banco.tbPessoas;

        for (Pessoa pessoa : pessoas) {
            if (pessoa.getCodigo().equals(chave)) {
                return pessoa;
            }
        }

        return null;
    }
}
