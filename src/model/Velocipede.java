package model;

public class Velocipede {
    private int id;
    private String tipo;
    private String estado;
    private int bateria;
    private String localizacaoPonto;  // localização definida pelo gestor ou técnico como ponto de entrega/recolha
    private String localizacao;  // localização atual do velocípede

    public Velocipede(String tipo, String estado, int bateria, String localizacaoPonto, String localizacao) {
        this.tipo = tipo;
        this.estado = estado;
        this.bateria = bateria;
        this.localizacaoPonto = localizacaoPonto;
        this.localizacao = localizacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public String getLocalizacaoPonto() {
        return localizacaoPonto;
    }

    public void setLocalizacaoPonto(String localizacaoPonto) {
        this.localizacaoPonto = localizacaoPonto;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void carregarBateria() {
        this.bateria = 100;
    }
}
