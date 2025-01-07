package model;

public class Pagamento {
    private int id;
    private String nomeTitular;
    private String numeroCartao;
    private String validade;
    private String cvv;
    private double valor;
    private String status;
    private String metodoPagamento;

    public Pagamento(int id, String nomeTitular, String numeroCartao, String validade, String cvv, double valor,
                     String status, String metodoPagamento) {
        this.id = id;
        this.nomeTitular = nomeTitular;
        this.numeroCartao = numeroCartao;
        this.validade = validade;
        this.cvv = cvv;
        this.valor = valor;
        this.status = status;
        this.metodoPagamento = metodoPagamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(String metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
