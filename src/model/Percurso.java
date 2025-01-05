package model;
import java.util.ArrayList;
import java.util.List;

public class Percurso {
    private boolean ativo;
    private List<String> coordenadas;

    public Percurso() {
        this.ativo = false;
        this.coordenadas = new ArrayList<>();
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
