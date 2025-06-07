package Classes.Login;

public class JavaConnectorLogin {
        public void receberLogin(String usuario, String senha) {
            System.out.println("Recebido do HTML → Usuário: " + usuario + ", Senha: " + senha);

            boolean resultado = Login.verificarLogin(usuario, senha);

            if (resultado) {
                System.out.println("Login bem-sucedido.");
                // Aqui você pode adicionar qualquer ação, como mudar a cena do JavaFX
            } else {
                System.out.println("Login falhou.");
            }
        }
}