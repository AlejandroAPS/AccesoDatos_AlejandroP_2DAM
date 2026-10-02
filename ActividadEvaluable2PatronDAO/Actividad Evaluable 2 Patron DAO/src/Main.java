import dao.database.ConexionDB;
import dao.model.Categoria;
import dao.model.Producto;
import dao.repository.RepositorioCategoriaDAO;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        RepositorioCategoriaDAO repoCategoria = new RepositorioCategoriaDAO();

        List<Categoria> lista = repoCategoria.getList();

        for (Categoria c : lista) {
            System.out.println(c);
            List<Producto> listProductos = repoCategoria.getProductosPorCategoria(c.getIdcategoria());
            for (Producto p : listProductos) {
                System.out.println("     " + p.getNombre() + " - " + p.getPrecio());
            }
        }

        ConexionDB.cerrar();
    }
}