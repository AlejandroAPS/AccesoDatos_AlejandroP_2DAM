package repository;

import database.ConexionDB;
import model.Categoria;
import model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProductoDAO {


    public boolean add(Producto data) {
        String sql = "INSERT INTO PRODUCTO VALUES (?, ?)";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, data.getIdproducto());
            ps.setString(2, data.getMedida());
            ps.setString(3, data.getNombre());
            ps.setInt(4, data.getPrecio());
            ps.setInt(5, data.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Integer id) {
        String sql = "DELETE FROM PRODUCTO WHERE idProducto = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Producto findById(Integer id) {
        String sql = "SELECT * FROM PRODUCTO WHERE idProducto = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();
                    p.setIdcategoria(rs.getInt("idProducto"));
                    p.setCategoria(rs.getString("Producto"));
                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Producto data) {
        String sql = "UPDATE PRODUCTO SET Producto = ? WHERE idProducto = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, data.setIdproducto());
            ps.setString(2, data.setMedida());
            ps.setString(3, data.setNombre());
            ps.setInt(4, data.setPrecio());
            ps.setInt(5, data.setStock());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Producto> getList() {
        String sql = "SELECT * FROM Producto";
        List<Producto> productos = new ArrayList<>();

        try (
                Statement statement = ConexionDB.getConexion().createStatement();
                ResultSet rs = statement.executeQuery(sql)
        ) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdproducto(rs.getInt("IdProducto"));
                p.setProducto(rs.getString("Producto"));
                productos.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }


}
