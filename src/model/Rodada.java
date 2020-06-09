package model;

import java.util.List;

public class Rodada {

    private List<Letra> palavra;
    private boolean passouRodada;

    public Rodada(List<Letra> palavra, boolean passouRodada) {
        this.palavra = palavra;
        this.passouRodada = passouRodada;
    }

    public Rodada() {
    }

    public List<Letra> getPalavra() {
        return palavra;
    }

    public void setPalavra(List<Letra> palavra) {
        this.palavra = palavra;
    }

    public boolean isPassouRodada() {
        return passouRodada;
    }

    public void setPassouRodada(boolean passouRodada) {
        this.passouRodada = passouRodada;
    }
}
