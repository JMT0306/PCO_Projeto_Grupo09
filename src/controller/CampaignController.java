package controller;

import model.Campaign;

import java.util.ArrayList;
import java.util.List;

public class CampaignController {
    private List<Campaign> campaigns;

    public CampaignController() {
        this.campaigns = new ArrayList<>();
    }

    // Método para adicionar uma nova campanha
    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
        System.out.println("Campanha adicionada com sucesso: " + campaign.getName());
    }

    // Método para atualizar uma campanha existente
    public boolean updateCampaign(String id, Campaign updatedCampaign) {
        for (int i = 0; i < campaigns.size(); i++) {
            if (campaigns.get(i).getId().equals(id)) {
                campaigns.set(i, updatedCampaign);
                System.out.println("Campanha atualizada com sucesso: " + updatedCampaign.getName());
                return true;
            }
        }
        System.out.println("Campanha com ID " + id + " não encontrada.");
        return false;
    }

    // Método para remover uma campanha
    public boolean removeCampaign(String id) {
        for (int i = 0; i < campaigns.size(); i++) {
            if (campaigns.get(i).getId().equals(id)) {
                System.out.println("Campanha removida com sucesso: " + campaigns.get(i).getName());
                campaigns.remove(i);
                return true;
            }
        }
        System.out.println("Campanha com ID " + id + " não encontrada.");
        return false;
    }

    // Método para listar todas as campanhas
    public List<Campaign> listCampaigns() {
        return campaigns;
    }
}
