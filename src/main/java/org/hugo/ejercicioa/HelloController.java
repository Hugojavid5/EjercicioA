package org.hugo.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * La clase HelloController gestiona la lógica de la interfaz de usuario de una aplicación JavaFX
 * que permite al usuario ingresar información sobre su profesión, número de hermanos, edad,
 * deportes, y preferencias de entretenimiento.
 */
public class HelloController {

    @FXML
    private Button btt_aceptar;

    @FXML
    private Button btt_cancelar;

    @FXML
    private CheckBox check_deportes;

    @FXML
    private ComboBox<String> combo_edad;

    @FXML
    private ListView<String> lst_deportes;

    @FXML
    private RadioButton radioButton_hombre;

    @FXML
    private RadioButton radioButton_mujer;

    @FXML
    private RadioButton radioButton_otro;

    @FXML
    private Slider sldr_cine;

    @FXML
    private Label slider_cine;

    @FXML
    private Slider slider_com;

    @FXML
    private Slider slider_tv;

    @FXML
    private TextField txt_Prof;

    @FXML
    private TextField txt_hermanos;

    /**
     * El método initialize es ejecutado automáticamente después de que los elementos FXML hayan sido cargados.
     * Establece las opciones disponibles en los ComboBox y ListView, y deshabilita ciertos elementos.
     */
    public void initialize() {
        ObservableList<String> edades = FXCollections.observableArrayList(
                "Menores de 18", "Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70"
        );
        combo_edad.setItems(edades);

        ObservableList<String> deportes = FXCollections.observableArrayList(
                "Tenis", "Fútbol", "Baloncesto", "Natación", "Ciclismo", "Otro"
        );
        lst_deportes.setItems(deportes);

        // Permite la selección múltiple en la lista de deportes
        lst_deportes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Deshabilita la lista de deportes inicialmente
        lst_deportes.setDisable(true);

        // Configura los tooltips para la selección de edad y deportes
        combo_edad.setTooltip(new Tooltip("rango de edad al que pertenece"));
        lst_deportes.setTooltip(new Tooltip("deporte preferido si practica alguno de los siguientes"));
    }


    /**
     * Cierra la ventana de la aplicación cuando el botón de cancelar es presionado.
     */
    @FXML
    private void salir() {
        Stage stage = (Stage) btt_cancelar.getScene().getWindow();
        stage.close();
    }

    /**
     * Habilita o deshabilita la lista de deportes según si el checkbox "Practica deportes" está seleccionado.
     * @param event El evento que se dispara al seleccionar o deseleccionar el checkbox.
     */
    @FXML
    private void habilitarLista(ActionEvent event) {
        lst_deportes.setDisable(!check_deportes.isSelected());
    }

    /**
     * Valida la entrada del usuario y muestra un mensaje de error si los datos no son correctos.
     * Si todos los datos son válidos, muestra un resumen de la información ingresada.
     */
    @FXML
    private void mostrar() {
        StringBuilder errores = new StringBuilder();

        // Validación del campo de profesión
        String profesion = txt_Prof.getText().trim();
        if (profesion.isEmpty()) {
            errores.append("Profesion no puede estar vacío.\n");
        }

        // Validación del campo de número de hermanos
        String hermanosText = txt_hermanos.getText().trim();
        int numHermanos = -1;
        try {
            numHermanos = Integer.parseInt(hermanosText);
            if (numHermanos < 0) {
                errores.append("El número de hermanos no puede ser negativo.\n");
            }
        } catch (NumberFormatException e) {
            errores.append("numero de hermanos no es un numero.\n");
        }

        // Validación de la selección de deportes
        if (check_deportes.isSelected() && lst_deportes.getSelectionModel().isEmpty()) {
            errores.append("Debido que marcó que practica algun deporte, marque tambien su deporte favorito.\n");
        }

        // Si hay errores, se muestran en una alerta
        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return;
        }
        String deporte = "";
        if (check_deportes.isSelected()) {
            deporte += "Deportes que practicas : \n";
            for (String deportes : lst_deportes.getSelectionModel().getSelectedItems()) {
                deporte += "\t" + deporte + "\n";
            }
        } else {
            deporte += "No practicas ningun deporte \n";
        }
        String edadSeleccionada = combo_edad.getSelectionModel().getSelectedItem();
        double compras = slider_com.getValue();
        double television = slider_tv.getValue();
        double cine = sldr_cine.getValue();

        // Muestra un mensaje con los datos ingresados
        String mensaje = String.format("Profesión: %s\nNúmero de hermanos: %d\nEdad: %s\nDeporte: %s\nCompras: %.1f\nVerTelevisión: %.1f\nIr al cine: %.1f",
                profesion, numHermanos, edadSeleccionada, deporte, compras, television, cine);

        mostrarInformacion("Datos ingresados correctamente", mensaje);
    }
    /**
     * Muestra un cuadro de diálogo de error con el mensaje proporcionado.
     * @param mensaje El mensaje de error que se mostrará.
     */
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un cuadro de diálogo de información con el título y mensaje proporcionados.
     * @param titulo El título de la ventana del cuadro de diálogo.
     * @param mensaje El contenido que se mostrará en el cuadro de diálogo.
     */
    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
