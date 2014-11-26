package modelo;

import java.io.Serializable;

public class Pessoa implements Serializable {

	private String codigo;

	private String nome;

	private String email;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Pessoa pessoa = (Pessoa) o;

		if (!codigo.equals(pessoa.codigo))
			return false;
		if (!email.equals(pessoa.email))
			return false;
		if (!nome.equals(pessoa.nome))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = codigo.hashCode();
		result = 31 * result + nome.hashCode();
		result = 31 * result + email.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Pessoa{" + "codigo='" + codigo + '\'' + ", nome='" + nome + '\'' + ", email='" + email + '\'' + '}';
	}

	public Pessoa(String codigo, String nome, String email) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
	}

	public Pessoa() {

	}
}
