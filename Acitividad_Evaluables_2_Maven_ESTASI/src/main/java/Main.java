import database.ConexionDB;
import model.Producto;
import repository.RepositorioProductoDAO;


public static void main(String[] args){
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;
    while (!salir) {
        mostrarMenu();
        int opcion = leerOpcion(scanner);

        switch (opcion) {
            case 1 -> menosde20();
            case 2 -> productosVeg();
            case 3 -> urgenteReponer();
            case 4 -> mostrarAlergicosPescado();
            case 5 -> salir = true;
            default -> System.out.println("Opción no válida. Elige un número entre 1 y 5.");
        }
    }

    cerrarUtilidades(scanner);
}

//----------- FUNCIONES---------------
private static int leerOpcion (Scanner scanner){
    String entrada = scanner.nextLine();
    try {
        return Integer.parseInt(entrada.trim());
    } catch (NumberFormatException e) {
        return -1;
    }
}

private static void mostrarMenu () {
    System.out.println("\n===== MENÚ =====");
    System.out.println("1. Ver Productos de menos de 20 euros o menos");
    System.out.println("2. Ver productos vegetarianos"); //Productos de categorias:bebidas, repostería, Granos/Cereales
    System.out.println("3. Ver productos urgentes de reponer"); //Productos con 10 en stock o menos
    System.out.println("4. Ver productos para personas alergicas al pescado"); //productos de todas categorias menos la de pescado
    System.out.println("5. Finalizar aplicación");
    System.out.print("Elige una opción: ");
}

private static void mostrarLista(String titulo, List<Producto> productos) {
    System.out.println("\n--- " + titulo + " ---");
    if (productos.isEmpty()) {
        System.out.println("No se han encontrado productos.");
        return;
    }
    productos.forEach(System.out::println);
    System.out.println("Total: " + productos.size());
}
private static void menosde20() {
    mostrarLista("Productos de 20 euros o menos", productoDAO.getProductosPorRangoPrecio(0, 20));
}


private static void productosVeg(){
    List<String> vegetarianas = List.of("Bebidas", "Granos/Cereales", "Frutas/Verduras", "Condimentos");
    mostrarLista("Productos veganos", productoDAO.getProductosPorCategorias(vegetarianas));
}


private static void urgenteReponer(){
    mostrarLista("Productos urgentes de reponer (stock <= 10)", productoDAO.getProductosBajoStock(10));
}

private static void mostrarAlergicosPescado(){
    mostrarLista("Productos aptos para alérgicos al pescado",
            productoDAO.getProductosExcluyendoCategoria("Pescado/Marisco"));
}

private static void cerrarUtilidades(Scanner scanner){
    ConexionDB.cerrar();
    scanner.close();
}
private static final RepositorioProductoDAO productoDAO = new RepositorioProductoDAO();