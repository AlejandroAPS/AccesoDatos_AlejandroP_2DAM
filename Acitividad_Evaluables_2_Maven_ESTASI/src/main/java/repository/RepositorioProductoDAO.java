package repository;

import database.ConexionDB;
import model.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RepositorioProductoDAO implements RepositorioDAO<Producto, Integer> {

    private Producto mapear(ResultSet rs) throws SQLException {
        Producto p = new Producto();
        p.setIdproducto(rs.getInt("idproducto"));
        p.setNombre(rs.getString("nombre"));
        p.setIdcategoria(rs.getInt("idcategoria"));
        p.setMedida(rs.getString("medida"));
        p.setPrecio(rs.getInt("precio"));
        p.setStock(rs.getInt("stock"));
        return p;
    }

    @Override
    public boolean add(Producto data) {
        String sql = "INSERT INTO producto (idproducto, nombre, idcategoria, medida, precio, stock) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, data.getIdproducto());
            ps.setString(2, data.getNombre());
            ps.setInt(3, data.getIdcategoria());
            ps.setString(4, data.getMedida());
            ps.setInt(5, data.getPrecio());
            ps.setInt(6, data.getStock());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean remove(Integer id) {
        String sql = "DELETE FROM producto WHERE idproducto = ?";
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
        String sql = "SELECT * FROM producto WHERE idproducto = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapear(rs);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean update(Producto data) {
        String sql = "UPDATE producto SET nombre = ?, idcategoria = ?, medida = ?, "
                + "precio = ?, stock = ? WHERE idproducto = ?";
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, data.getNombre());
            ps.setInt(2, data.getIdcategoria());
            ps.setString(3, data.getMedida());
            ps.setInt(4, data.getPrecio());
            ps.setInt(5, data.getStock());
            ps.setInt(6, data.getIdproducto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public List<Producto> getList() {
        String sql = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();
        try (Statement st = ConexionDB.getConexion().createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) productos.add(mapear(rs));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    // ---------- Consultas avanzadas ----------

    public List<Producto> getProductosPorRangoPrecio(int min, int max) {
        String sql = "SELECT * FROM producto WHERE precio BETWEEN ? AND ? ORDER BY precio";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) productos.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public List<Producto> getProductosBajoStock(int limite) {
        String sql = "SELECT * FROM producto WHERE stock <= ? ORDER BY stock";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setInt(1, limite);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) productos.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }
    public List<Producto> getProductosPorCategorias(List<String> categorias) {
        List<Producto> productos = new ArrayList<>();
        if (categorias.isEmpty()) return productos;

        String marcadores = String.join(", ", java.util.Collections.nCopies(categorias.size(), "?"));
        String sql = "SELECT p.* FROM producto p "
                + "JOIN categoria c ON p.idcategoria = c.idcategoria "
                + "WHERE c.categoria IN (" + marcadores + ") ORDER BY p.nombre";

        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            for (int i = 0; i < categorias.size(); i++) {
                ps.setString(i + 1, categorias.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) productos.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public List<Producto> getProductosExcluyendoCategoria(String categoria) {
        String sql = "SELECT p.* FROM producto p "
                + "JOIN categoria c ON p.idcategoria = c.idcategoria "
                + "WHERE c.categoria <> ? ORDER BY p.nombre";
        List<Producto> productos = new ArrayList<>();
        try (PreparedStatement ps = ConexionDB.getConexion().prepareStatement(sql)) {
            ps.setString(1, categoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) productos.add(mapear(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }
}