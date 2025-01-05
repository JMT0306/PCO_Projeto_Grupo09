package model;
import java.util.ArrayList;
import java.util.List;

public class Percurso {
    private boolean ativo;
    private List<String> coordenadas;

    public Percurso(String localizacaoAtual) {
        this.ativo = false;
        this.coordenadas = new ArrayList<>();
        this.coordenadas.add(localizacaoAtual);
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<String> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(List<String> coordenadas) {
        this.coordenadas = coordenadas;
    }
}
