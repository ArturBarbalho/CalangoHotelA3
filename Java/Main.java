import static spark.Spark.*;
import com.google.gson.Gson;
import Classes.Check.JavaConnectorCheck;
import Classes.Login.JavaConnectorLogin;
import Classes.Cadastro.JavaConnectorCadastro;
import Classes.Pagamentos.Pagamentos;
import Classes.Database.ConexaoSQL;

public class Main {
    static class MessageResponse {
        String message;
        MessageResponse(String message) { this.message = message; }
    }
    public static void main(String[] args) {
        port(8081);

        JavaConnectorCheck javaCheck = new JavaConnectorCheck();
        JavaConnectorLogin javaLogin = new JavaConnectorLogin();
        JavaConnectorCadastro javaCadastro = new JavaConnectorCadastro();
        Pagamentos javaPagamentos = new Pagamentos();

        Gson gson = new Gson();

        // CORS básico
        options("/*", (req, res) -> {
            String acrh = req.headers("Access-Control-Request-Headers");
            if (acrh != null) res.header("Access-Control-Allow-Headers", acrh);
            String acrm = req.headers("Access-Control-Request-Method");
            if (acrm != null) res.header("Access-Control-Allow-Methods", acrm);
            return "OK";
        });
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        

        // --- Endpoints Check (reservas, checkin, checkout, nova reserva) ---
        get("/api/check/reservas", (req, res) -> {
            res.type("application/json");
            String lista = javaCheck.listarReservas();
            return gson.toJson(new MessageResponse(lista));
        });

        post("/api/check/checkin", (req, res) -> {
            res.type("application/json");
            IdRequest idReq = gson.fromJson(req.body(), IdRequest.class);
            boolean sucesso = javaCheck.realizarCheckIn(idReq.id);
            if (sucesso) {
                res.status(200);
                return gson.toJson(new MessageResponse("Check-in realizado com sucesso!"));
            } else {
                res.status(400);
                return gson.toJson(new MessageResponse("Falha ao realizar check-in."));
            }
        });

        post("/api/check/checkout", (req, res) -> {
            res.type("application/json");
            IdRequest idReq = gson.fromJson(req.body(), IdRequest.class);
            boolean sucesso = javaCheck.realizarCheckOut(idReq.id);
            if (sucesso) {
                res.status(200);
                return gson.toJson(new MessageResponse("Check-out realizado com sucesso!"));
            } else {
                res.status(400);
                return gson.toJson(new MessageResponse("Falha ao realizar check-out."));
            }
        });



       // Endpoint de login
        post("/api/login", (req, res) -> {
            res.type("application/json");
            try {
                LoginRequest login = gson.fromJson(req.body(), LoginRequest.class);
                javaLogin.receberLogin(login.username, login.password);
                return gson.toJson(new MessageResponse("✅ Login processado com sucesso"));
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return gson.toJson(new MessageResponse("❌ Erro ao processar login: " + e.getMessage()));
            }
});


        // Endpoint de cadastro
        post("/api/cadastro", (req, res) -> {
            res.type("application/json");
            try {
                CadastroRequest cadastro = gson.fromJson(req.body(), CadastroRequest.class);
                javaCadastro.cadastrarHospede(
                    cadastro.nome,
                    cadastro.email,
                    cadastro.telefone,
                    cadastro.quarto,
                    cadastro.dataEntrada,
                    cadastro.dataSaida,
                    cadastro.valor,
                    cadastro.formaPagamento
                );
                return gson.toJson(new MessageResponse("✅ Cadastro processado com sucesso"));
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return gson.toJson(new MessageResponse("❌ Erro ao processar cadastro: " + e.getMessage()));
            }
        });

        // Listar pagamentos
        get("/api/pagamentos", (req, res) -> {
            res.type("application/json");
            try {
                String pagamentos = gson.toJson(javaPagamentos.listarPagamentos());
                return new MessageResponse(pagamentos);
            } catch (Exception e) {
                e.printStackTrace();
                res.status(500);
                return gson.toJson(new MessageResponse("❌ Erro ao listar pagamentos: " + e.getMessage()));
            }
        });
        
        get("/", (req, res) -> {
            res.type("text/html");
            return "<h1>Bem-vindo à API do Hotel!</h1>" +
                   "<p>Use os endpoints para interagir com o sistema.</p>" +
                   "<p>Exemplo: <a href='/api/check/reservas'>/api/check/reservas</a></p>";
        });
    }

    // Classes auxiliares para desserializar JSON requests:
    static class IdRequest {
        int id;
    }

    static class PagamentoRequest {
        int valor;
        int data; // Pode ser uma data em formato timestamp ou outro formato
        String metodo;
    }
    static class LoginRequest {
        String username;
        String password;
    }

    static class CadastroRequest {
        String nome;
        String email;
        int telefone;
        int quarto;
        String dataEntrada;
        String dataSaida;
        int valor;
        String formaPagamento;
    }}




 /*public class Main {
    static class MessageResponse {
        String message;
        MessageResponse(String message) { this.message = message; }
    }
    public static void main(String[] args) {
        // Inicia o servidor na porta 8081
        port(8081);
        
       

        Gson gson = new Gson();

        // CORS básico
        options("/*", (req, res) -> {
            String acrh = req.headers("Access-Control-Request-Headers");
            if (acrh != null) res.header("Access-Control-Allow-Headers", acrh);
            String acrm = req.headers("Access-Control-Request-Method");
            if (acrm != null) res.header("Access-Control-Allow-Methods", acrm);
            return "OK";
        });
        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        
        // --- Endpoints Check (reservas, checkin, checkout, nova reserva) ---
        get("/api", (req, res) -> {
            res.type("application/json");
            ConexaoSQL db = new ConexaoSQL();
            db.conectar();
            
            return gson.toJson(new MessageResponse("test"));
        });

    }
}
     */