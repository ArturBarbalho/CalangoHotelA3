import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        // Caminho da página inicial (pagina1.html)
        File paginaInicial = new File("Main.html");
        webEngine.load(paginaInicial.toURI().toString());

        BorderPane root = new BorderPane(webView);
        Scene scene = new Scene(root,1920,1080);

        stage.setTitle("Exemplo de Navegação HTML com WebView");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}