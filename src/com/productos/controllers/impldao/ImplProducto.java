package com.productos.controllers.impldao;

import com.productos.controllers.intfdao.IProducto;
import com.productos.models.ModelProducto;
import com.productos.utils.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquí esta la implementación del producto según el patrón DAO,
 * se usa la interfaz IProducto.
 *
 * @author JamesTovar
 * @email u20172163883@usco.edu.co
 */
public class ImplProducto implements IProducto {

    @Override
    public boolean create(ModelProducto producto) {
        Connection connection = ConnectionDB.getConnection();
        try{
            String query = "INSERT INTO Productos (codigo,nombre,descripcion,fabricante,cantidadStock) VALUES (?,?,?,?,?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setString(3,producto.getDescripcion());
            ps.setString(4,producto.getFabricante());
            ps.setInt(5,producto.getCantidadStock());
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public ModelProducto getById(int id) {
        return null;
    }

    @Override
    public List<ModelProducto> getAll() {
        List<ModelProducto> listaProductos = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try{
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Productos;");
            while (rs.next()){
                ModelProducto producto = new ModelProducto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setCodigo(rs.getInt("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setFabricante(rs.getString("fabricante"));
                producto.setCantidadStock(rs.getInt("cantidadStock"));

                listaProductos.add(producto);
            }

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return listaProductos;
    }

    @Override
    public List<ModelProducto> getBySearch(String buscar) {
        List<ModelProducto> listaProductos = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        try{
            String query = "SELECT * FROM Productos WHERE codigo LIKE ? OR nombre LIKE ? OR fabricante LIKE ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1,buscar+"%");
            ps.setString(2,buscar+"%");
            ps.setString(3,buscar+"%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ModelProducto producto = new ModelProducto();

                producto.setIdProducto(rs.getInt("idProducto"));
                producto.setCodigo(rs.getInt("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setFabricante(rs.getString("fabricante"));
                producto.setCantidadStock(rs.getInt("cantidadStock"));

                listaProductos.add(producto);
            }
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return listaProductos;
    }

    @Override
    public boolean edit(ModelProducto producto) {
        Connection connection = ConnectionDB.getConnection();
        try{
            String query = "UPDATE Productos SET codigo=?,nombre=?,descripcion=?,fabricante=?,cantidadStock=? WHERE idProducto=?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,producto.getCodigo());
            ps.setString(2,producto.getNombre());
            ps.setString(3,producto.getDescripcion());
            ps.setString(4,producto.getFabricante());
            ps.setInt(5,producto.getCantidadStock());
            ps.setInt(6,producto.getIdProducto());
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        Connection connection = ConnectionDB.getConnection();

        try{
            String query = "DELETE FROM Productos WHERE idProducto = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            int rs = ps.executeUpdate();
            return rs > 0;
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return false;

    }
}