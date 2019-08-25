package br.com.unifil.agenda;


public class Pessoa implements Comparator {

    private String nome;
    private String telefone;

    public Pessoa(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getNome() {
        return this.nome;
    }

    public String getTelefone() {
        return telefone;
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.telefone;
    }

    @Override
    public int compare(Pessoa p1, Pessoa p2) {
        return p1.nome.compareTo(p2.nome);
    }
}
