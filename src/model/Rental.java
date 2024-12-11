package model;

import java.time.LocalDateTime;

public class Rental {
    private String id; // Identificador único do aluguer
    private String vehicleId; // ID do veículo alugado
    private String userId; // ID do utilizador que alugou
    private LocalDateTime startTime; // Início do aluguer
    private LocalDateTime endTime; // Fim do aluguer (opcional até ser concluído)
    private double distance; // Distância percorrida em quilômetros
    private double cost; // Custo total do aluguer
    private double penalty; // Penalizações aplicadas, se houver

    public Rental(String id, String vehicleId, String userId, LocalDateTime startTime) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = null; // Ainda não concluído
        this.distance = 0.0;
        this.cost = 0.0;
        this.penalty = 0.0;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    // Métodos auxiliares

    /**
     * Calcula o custo total com base na distância percorrida e nas taxas fixas.
     * @param baseRate Taxa base por quilômetro.
     * @param fixedFee Taxa fixa do aluguer.
     */
    public void calculateCost(double baseRate, double fixedFee) {
        this.cost = (distance * baseRate) + fixedFee + penalty;
    }

    /**
     * Finaliza o aluguer e calcula o custo.
     * @param endTime Tempo de fim do aluguer.
     * @param distance Distância total percorrida.
     * @param baseRate Taxa base por quilômetro.
     * @param fixedFee Taxa fixa do aluguer.
     */
    public void finishRental(LocalDateTime endTime, double distance, double baseRate, double fixedFee) {
        this.endTime = endTime;
        this.distance = distance;
        calculateCost(baseRate, fixedFee);
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id='" + id + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", userId='" + userId + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", distance=" + distance +
                ", cost=" + cost +
                ", penalty=" + penalty +
                '}';
    }
}
