package controller;
import model.Pagamento;
import java.util.ArrayList;
import java.util.List;

public class PagamentoController {
    private static int pagamentoIdCounter = 1;
    private final List<Pagamento> pagamentos;

    public PagamentoController() {
        this.pagamentos = new ArrayList<>();
    }

    public boolean processarPagamento(String nomeTitular, String numeroCartao, String validade, String cvv) {
        if (!validarCartao(nomeTitular, numeroCartao, validade, cvv)) {
            System.out.println("Erro: Dados do cartão inválidos.");
            return false;
        }
        Pagamento pagamento = new Pagamento(1, nomeTitular, numeroCartao, validade, cvv, 5.00, "Pago", "CartãoCrédito");
        pagamento.setId(pagamentoIdCounter++);
        pagamentos.add(pagamento);
        return true;
    }

    // Valida os dados do cartão
    private boolean validarCartao(String nomeTitular, String numeroCartao, String validade, String cvv) {
        return nomeTitular != null && !nomeTitular.isEmpty() && numeroCartao.length() == 16 && validade.length() == 5 && cvv.length() == 3;
    }

    // Método para listar todos os pagamentos registados
    public List<Pagamento> listarPagamentos() {
        return pagamentos;
    }
}
