package controller;
import model.Pagamento;
import model.Percurso;
import java.util.ArrayList;
import java.util.List;

public class PercursoController {

    private final List<Percurso> percursos;

    public PercursoController() {
        this.percursos = new ArrayList<>();
    }

    // Método para iniciar o percurso e adicioná-lo à lista de percursos
    public Percurso iniciarPercurso() {
        Percurso percurso = new Percurso();
        percurso.setAtivo(true);
        percursos.add(percurso);
        System.out.println("Percurso iniciado!");
        return percurso;
    }

    // Método para listar todos os percursos
    public List<Percurso> listarPercursos() {
        return percursos;
    }
}


