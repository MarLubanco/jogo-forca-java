package model;

public class Jogador {

    private Integer id;
    private String nome;
    private Integer pontos;

    public Jogador(Integer id, String nome, Integer pontos) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
    }

    public Jogador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }
}
