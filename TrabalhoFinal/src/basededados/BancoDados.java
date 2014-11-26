package basededados;

import modelo.Pessoa;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ton on 22/10/2014.
 *
 * Padrão Singleton --> padrão de projeto
 */
public class BancoDados {

    // tabela pessoas
    public List<Pessoa> tbPessoas = new ArrayList<Pessoa>();

    private static BancoDados bancoDados;

    //construtor privado não roda o comando new
    private BancoDados() {
        tbPessoas.add( new Pessoa("01","Ze","ze@gmail.com"));
        tbPessoas.add( new Pessoa("02","Joselito","joselito@mail.com"));
        tbPessoas.add( new Pessoa("03","Meeee","me@mail.com"));

    }

    //padrão singleton
    public static BancoDados getInstance() {

        if (bancoDados == null) {
            bancoDados = new BancoDados();
        }

        return bancoDados;
    }

}
