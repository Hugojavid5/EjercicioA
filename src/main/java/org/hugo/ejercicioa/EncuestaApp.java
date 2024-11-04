package org.hugo.ejercicioa;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * La clase HelloApplication es una aplicación JavaFX que carga una interfaz gráfica
 * de usuario (GUI) definida en un archivo FXML y la muestra en una ventana.
 * Extiende la clase {@link javafx.application.Application}, que es el punto de entrada
 * para las aplicaciones JavaFX.
 */
public class EncuestaApp extends Application {

    /**
     * El método start es invocado automáticamente cuando la aplicación JavaFX es iniciada.
     * Carga el archivo FXML que define la interfaz gráfica de la aplicación y establece la escena.
     * @param stage El escenario principal donde se mostrará la interfaz gráfica.
     * @throws IOException Si hay un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML que contiene la definición de la interfaz
        FXMLLoader fxmlLoader = new FXMLLoader(EncuestaApp.class.getResource("ejercicioA.fxml"));
        // Configura la escena con el contenido cargado del archivo FXML
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);

        // Establece el tamaño mínimo de la ventana
        stage.setMinHeight(500);
        stage.setMinWidth(600);
        stage.setResizable(false);
        // Establece el título de la ventana
        stage.setTitle("Ejercicio A");

        // Establece la escena en el escenario y la muestra
        stage.setScene(scene);
        stage.show();
    }

    /**
     * El método main es el punto de entrada de la aplicación.
     * @param args Los argumentos de la línea de comandos (no se usan en esta aplicación).
     */
    public static void main(String[] args) {
        launch();
    }
}
