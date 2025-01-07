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

    /**
     * Faz o processamento do pagamento utilizando os dados do cartão de crédito fornecidos.
     * Valida as informações do cartão e, se válidas, cria um novo registo de pagamento e o adiciona à lista de pagamentos.
     * @param nomeTitular o nome do titular do cartão.
     * @param numeroCartao o número do cartão de crédito.
     * @param validade a validade do cartão de crédito.
     * @param cvv o código de segurança do cartão de crédito.
     * @return true se o pagamento foi processado com sucesso, false caso um ou mais dos dados do cartão sejam inválidos.
     */
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

    /**
     * Faz a validação dos dados do cartão de crédito fornecido.
     * @param nomeTitular o nome do titular do cartão de crédito.
     * @param numeroCartao o número do de crédito.
     * @param validade a validade do cartão de crédito.
     * @param cvv o código de segurança do cartão de crédito.
     * @return true se todos os dados do cartão forem válidos, false caso contrário.
     */
    private boolean validarCartao(String nomeTitular, String numeroCartao, String validade, String cvv) {
        return nomeTitular != null && !nomeTitular.isEmpty() && numeroCartao.length() == 16 && validade.length() == 5 && cvv.length() == 3;
    }
}
