package com.productos.controllers.views;

import com.productos.models.ModelProducto;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Esta clase funciona como controlador de la vista VistaProductos
 *
 * @autor JamesTovar
 * @email u20172163883@usco.edu.co
 */
public class VistaProductos {

    @FXML
    private Button btnClose;
    @FXML
    private HBox header;
    @FXML
    private Label title;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtFabricante;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextArea txtDescripcion;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnGuardar;

    private double xOffset = 0;
    private double yOffset = 0;

    private ModelProducto producto;
    private TablaProductos tablaProductos;

    @FXML
    private void initialize(){
        // Hacer que el campo código solo acepte números
        txtCodigo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtCodigo.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        // Hacer que el campo cantidad solo acepte números
        txtCantidad.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtCantidad.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void closeWindow() {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void movePreset(MouseEvent evento){
        xOffset = evento.getSceneX();
        yOffset = evento.getSceneY();
    }

    @FXML
    private void moveDragged(MouseEvent evt){
        Stage stage = (Stage)header.getScene().getWindow();
        stage.setX(evt.getScreenX() - xOffset);
        stage.setY(evt.getScreenY() - yOffset);
    }

    public void setProducto(ModelProducto producto){
        this.producto = producto;
        mostrarDatos();
    }

    public void setTablaProductos(TablaProductos tablaProductos){
        this.tablaProductos = tablaProductos;
    }

    private void mostrarDatos(){
        title.setText("Producto #" + producto.getIdProducto());
        txtCodigo.setText(Integer.toString(producto.getCodigo()));
        txtNombre.setText(producto.getNombre());
        txtFabricante.setText(producto.getFabricante());
        txtCantidad.setText(Integer.toString(producto.getCantidadStock()));
        txtDescripcion.setText(producto.getDescripcion());
        btnAgregar.setVisible(false);
        btnEliminar.setVisible(true);
        btnGuardar.setVisible(true);
    }

    @FXML
    private void guardarProducto(){
        producto.setCodigo(Integer.parseInt(txtCodigo.getText()));
        producto.setNombre(txtNombre.getText());
        producto.setFabricante(txtFabricante.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setCantidadStock(Integer.parseInt(txtCantidad.getText()));
        tablaProductos.actualizarProducto(producto);
        closeWindow();
    }

    @FXML
    private void eliminarProducto(){
        tablaProductos.eliminarProducto(producto.getIdProducto());
        closeWindow();
    }

    @FXML
    private void agregarProducto(){
        producto = new ModelProducto();
        producto.setCodigo(Integer.parseInt(txtCodigo.getText()));
        producto.setNombre(txtNombre.getText());
        producto.setFabricante(txtFabricante.getText());
        producto.setDescripcion(txtDescripcion.getText());
        producto.setCantidadStock(Integer.parseInt(txtCantidad.getText()));
        tablaProductos.agregarProducto(producto);
        resetForm();
        validar();
    }

    private void resetForm(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtFabricante.setText("");
        txtCantidad.setText("");
        txtDescripcion.setText("");
    }

    @FXML
    private void validar(){
        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        String cantidad = txtCantidad.getText();
        if (codigo.isEmpty() || nombre.isEmpty() || cantidad.isEmpty()){
            btnAgregar.setDisable(true);
            btnGuardar.setDisable(true);
        } else{
            btnAgregar.setDisable(false);
            btnGuardar.setDisable(false);
        }
    }

}
