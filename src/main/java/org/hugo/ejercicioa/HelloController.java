package org.hugo.ejercicioa;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

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
    public void initialize() {
        ObservableList<String> edades = FXCollections.observableArrayList(
                "Menores de 18", "Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70"
        );
        combo_edad.setItems(edades);

        ObservableList<String> deportes = FXCollections.observableArrayList(
                "Tenis", "Fútbol", "Baloncesto", "Natación", "Ciclismo", "Otro"
        );
        lst_deportes.setItems(deportes);

        lst_deportes.setDisable(true);

        combo_edad.setTooltip(new Tooltip("rango de edad al que pertenece"));
        lst_deportes.setTooltip(new Tooltip("deporte preferido si practica alguno de los siguientes"));
    }
    @FXML
    private void salir() {
        Stage stage = (Stage) btt_cancelar.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void habilitarLista(ActionEvent event) {
        lst_deportes.setDisable(!check_deportes.isSelected());
    }

    @FXML
    private void mostrar() {
        StringBuilder errores = new StringBuilder();

        String profesion = txt_Prof.getText().trim();
        if (profesion.isEmpty()) {
            errores.append("Profesion no puede estar vacío.\n");
        }

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

        if (check_deportes.isSelected() && lst_deportes.getSelectionModel().isEmpty()) {
            errores.append("Debido que marcó que practica algun deporte, marque tambien su deporte favorito.\n");
        }

        if (errores.length() > 0) {
            mostrarError(errores.toString());
            return;
        }

        String edadSeleccionada = combo_edad.getSelectionModel().getSelectedItem();
        String deporteSeleccionado = check_deportes.isSelected() ? lst_deportes.getSelectionModel().getSelectedItem() : "No practica deportes";
        double compras = slider_com.getValue();
        double television = slider_tv.getValue();
        double cine = sldr_cine.getValue();

        String mensaje = String.format("Profesión: %s\nNúmero de hermanos: %d\nEdad: %s\nDeporte: %s\nCompras: %.1f\nVerTelevisión: %.1f\nIr al cine: %.1f",
                profesion, numHermanos, edadSeleccionada, deporteSeleccionado, compras, television, cine);

        mostrarInformacion("Datos ingresados correctamente", mensaje);
    }
    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error en los datos");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
