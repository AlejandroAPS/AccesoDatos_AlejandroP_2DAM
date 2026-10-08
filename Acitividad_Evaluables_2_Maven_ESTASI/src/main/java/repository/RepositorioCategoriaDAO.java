package repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConexionDB;
import model.*;

public class RepositorioCategoriaDAO implements RepositorioDAO<Categoria, Integer>{

    @Override
    public boolean add(Categoria data) {
        String sql = "INSERT INTO CATEGORIA VALUES (?, ?)";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, data.getIdcategoria());
            ps.setString(2, data.getCategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Integer id) {
        String sql = "DELETE FROM CATEGORIA WHERE idCategoria = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Categoria findById(Integer id) {
        String sql = "SELECT * FROM CATEGORIA WHERE idCategoria = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria c = new Categoria();
                    c.setIdcategoria(rs.getInt("idCategoria"));
                    c.setCategoria(rs.getString("Categoria"));
                    return c;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public boolean update(Categoria data) {
        String sql = "UPDATE CATEGORIA SET Categoria = ? WHERE idCategoria = ?";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, data.getCategoria());
            ps.setInt(2, data.getIdcategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Categoria> getList() {
        String sql = "SELECT * FROM Categoria";
        List<Categoria> categorias = new ArrayList<>();

        try (
                Statement statement = ConexionDB.getConexion().createStatement();
                ResultSet rs = statement.executeQuery(sql)
        ) {
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdcategoria(rs.getInt("IdCategoria"));
                c.setCategoria(rs.getString("Categoria"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return categorias;
    }

    public List<Producto> getProductosPorCategoria(Integer id) {
        String sql = "SELECT * FROM PRODUCTO WHERE idCategoria = ?";
        List<Producto> productos = new ArrayList<>();

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto p = new Producto();
                    Producto p = new Producto();
                    p.setIdproducto(rs.getInt("idproducto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setIdcategoria(rs.getInt("idcategoria"));
                    p.setMedida(rs.getString("medida"));
                    p.setPrecio(rs.getInt("precio"));
                    p.setStock(rs.getInt("stock"));
                    productos.add(p);
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productos;
    }
}