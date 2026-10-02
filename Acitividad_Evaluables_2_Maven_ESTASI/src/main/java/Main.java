void main() {
    Scanner scanner = new Scanner(System.in);
    boolean salir = false;
    while (salir == false) {
        mostrarMenu();
        int opcion = leerOpcion(scanner);

        switch (opcion) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
            case 3 -> System.out.println("3");
            case 4 -> System.out.println("4");
            case 5 -> salir = true;
            default -> System.out.println("Opción no válida. Elige un número entre 1 y 5.");
        }
    }
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
    System.out.println("1. Ingresar dinero");
    System.out.println("2. Retirar dinero");
    System.out.println("3. Consultar saldo y movimientos");
    System.out.println("4. Exportar movimientos");
    System.out.println("5. Finalizar aplicación");
    System.out.print("Elige una opción: ");
}