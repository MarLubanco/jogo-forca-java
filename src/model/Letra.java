package model;

public class Letra {

    private String letra;
    private boolean acertaram;

    public Letra(String letra, boolean acertaram) {
        this.letra = letra;
        this.acertaram = acertaram;
    }

    public Letra() {
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public boolean isAcertaram() {
        return acertaram;
    }

    public void setAcertaram(boolean acertaram) {
        this.acertaram = acertaram;
    }
}
