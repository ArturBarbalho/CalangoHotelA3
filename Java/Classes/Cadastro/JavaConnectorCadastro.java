package Classes.Cadastro;
import Classes.Check.Check;
import Classes.Pagamentos.Pagamentos;

public class JavaConnectorCadastro {
        Cadastro Cadastro = new Cadastro();
        Check reserva = new Check();
        Pagamentos Pagamento = new Pagamentos();


        public String listarHospedes() {
            return Cadastro.listarHospedes();
        }

        public void cadastrarHospede(String nome, String email, int telefone, int quarto, String dataEntrada, String dataSaida, int valor, String formaPagamento) {
          
             Cadastro.cadastrarHospede(nome, email, telefone, quarto, dataEntrada, dataSaida, valor, formaPagamento);
             reserva.NovaReserva(nome, quarto, dataEntrada, dataSaida);
             Pagamento.salvarPagamento(valor, Integer.parseInt(dataEntrada), formaPagamento); 
        }

        public void removerHospede(int id) {
             Cadastro.removerHospede(id);
        }
    }