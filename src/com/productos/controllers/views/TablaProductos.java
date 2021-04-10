package com.productos.controllers.views;

import com.productos.controllers.impldao.ImplProducto;
import com.productos.models.ModelProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Esta clase funciona como controlador de la vista TablaProductos
 *
 * @autor JamesTovar
 * @email u20172163883@usco.edu.co
 */
public class TablaProductos {

    @FXML
    private HBox header;
    @FXML
    private TableView<ModelProducto> tablaProductos;
    @FXML
    private TableColumn<ModelProducto, Integer> idColumna;
    @FXML
    private TableColumn<ModelProducto, Integer> codigoColumna;
    @FXML
    private TableColumn<ModelProducto, String> nombreColumna;
    @FXML
    private TableColumn<ModelProducto, String> descripcionColumna;
    @FXML
    private TableColumn<ModelProducto, String> fabricanteColumna;
    @FXML
    private TableColumn<ModelProducto, Integer> cantidadColumna;
    @FXML
    private TextField txtBuscar;

    private double xOffset = 0;
    private double yOffset = 0;

    ImplProducto productos;

    @FXML
    private void initialize(){
        productos = new ImplProducto();
        todosProductos();
        // Agregar eventos a la fila de la tabla
        tablaProductos.setRowFactory(tv -> {
            TableRow<ModelProducto> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    ModelProducto producto = row.getItem();
                    abrirVentanaProducto(producto);
                }
            });
            return row;
        });
    }

    @FXML
    private void closeWindow(){
        System.exit(0);
    }

    @FXML
    private void minimizeWindow(ActionEvent event){
        ((Stage)((Button)event.getSource()).getScene().getWindow()).setIconified(true);
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

    @FXML
    private void ventanaAgregar(){
        abrirVentanaProducto(null);
    }

    @FXML
    public void buscarProductos(){
        String busqueda = txtBuscar.getText();
        if(busqueda.equals(""))
            todosProductos();
        else{
            ObservableList<ModelProducto> listaProductos = FXCollections.observableArrayList();
            listaProductos.addAll(productos.getBySearch(busqueda));
            mostrarProductos(listaProductos);
        }
    }

    private void abrirVentanaProducto(ModelProducto producto){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../../views/vistaProducto.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 300, 360);
            VistaProductos controller = fxmlLoader.getController();
            controller.setTablaProductos(this);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setTitle("Producto");
            stage.setScene(scene);
            stage.show();

            if (producto != null)
                controller.setProducto(producto);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void todosProductos(){
        ObservableList<ModelProducto> listaProductos = FXCollections.observableArrayList();
        listaProductos.addAll(productos.getAll());
        mostrarProductos(listaProductos);
    }

    public void mostrarProductos(ObservableList<ModelProducto> lista) {

        idColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,Integer>("IdProducto"));
        codigoColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,Integer>("Codigo"));
        nombreColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,String>("Nombre"));
        descripcionColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,String>("Descripcion"));
        fabricanteColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,String>("Fabricante"));
        cantidadColumna.setCellValueFactory(new PropertyValueFactory<ModelProducto,Integer>("CantidadStock"));

        tablaProductos.setItems(lista);
    }

    public void agregarProducto(ModelProducto producto){
        productos.create(producto);
        todosProductos();
    }

    public void eliminarProducto(int id){
        productos.delete(id);
        todosProductos();
    }

    public void actualizarProducto(ModelProducto producto){
        productos.edit(producto);
        todosProductos();
    }

}
