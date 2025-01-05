package model;

public class Aluguer {
    private int id;
    private int velocipedeId;
    private int idCliente;

    public Aluguer(int id, int velocipedeId, int utilizadorId) {
        this.id = id;
        this.velocipedeId = velocipedeId;
        this.idCliente = utilizadorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVelocipedeId() {
        return velocipedeId;
    }

    public void setVelocipedeId(int velocipedeId) {
        this.velocipedeId = velocipedeId;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
