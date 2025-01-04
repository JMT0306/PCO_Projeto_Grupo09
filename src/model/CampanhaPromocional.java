package model;

public class CampanhaPromocional {
    private int id;
    private String nome;
    private String tipo;
    private String detalhes;

    public CampanhaPromocional(String nome, String tipo, String detalhes) {
        this.nome = nome;
        this.tipo = tipo;
        this.detalhes = detalhes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
