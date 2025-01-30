package aula_03_sockets_multicliente;

import java.io.Serializable;

public class Mensagem implements Serializable {

    private String nome;
    private String texto;

    public Mensagem(String nome, String texto) {
        super();
        this.nome = nome;
        this.texto = texto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return nome + ": " + texto;
    }

}
