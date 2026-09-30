
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
        private static final String DIRECTORIO_DATOS = "datos";
        private static final String FICHERO_CUENTA = DIRECTORIO_DATOS + File.separator + "cuenta.dat";
        private static final String FICHERO_EXPORT_CSV = DIRECTORIO_DATOS + File.separator + "movimientos.csv";

        // Se define una sola vez y se reutiliza tanto al consultar por pantalla
        // como al exportar, para no duplicar el patrón de fecha en dos sitios.
        private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Cuenta cuenta;

        try {
            cuenta = inicializarCuenta(scanner);
        } catch (IOException e) {
            // Si ni siquiera podemos cargar o crear la cuenta, no tiene sentido
            // seguir ejecutando el programa.
            System.out.println("Error al inicializar los datos de la cuenta: " + e.getMessage());
            scanner.close();
            return;
        }

        boolean salir = false;
        while (!salir) {
            mostrarMenu();
            int opcion = leerOpcion(scanner);

            switch (opcion) {
                case 1 -> realizarIngreso(scanner, cuenta);
                case 2 -> realizarRetirada(scanner, cuenta);
                case 3 -> consultarSaldoYMovimientos(cuenta);
                case 4 -> exportarMovimientos(cuenta);
                case 5 -> salir = true;
                default -> System.out.println("Opción no válida. Elige un número entre 1 y 5.");
            }
        }

        try {
            guardarCuenta(cuenta);
            System.out.println("Cuenta guardada correctamente. ¡Hasta pronto!");
        } catch (IOException e) {
            System.out.println("No se pudieron guardar los datos antes de salir: " + e.getMessage());
        }

        scanner.close();
    }

    // ---------- Inicialización y persistencia ----------

    /**
     * Comprueba si existen el directorio "datos" y el fichero "cuenta.dat".
     * Crea el directorio si falta, carga la cuenta si el fichero existe,
     * o solicita los datos de una cuenta nueva en caso contrario.
     */
    private static Cuenta inicializarCuenta(Scanner scanner) throws IOException {
        File directorio = new File(DIRECTORIO_DATOS);
        if (!directorio.exists()) {
            directorio.mkdir();
            System.out.println("Directorio '" + DIRECTORIO_DATOS + "' creado.");
        }

        File fichero = new File(FICHERO_CUENTA);
        if (fichero.exists()) {
            System.out.println("Cargando cuenta existente...");
            return cargarCuenta();
        } else {
            System.out.println("No se encontraron datos previos. Vamos a crear una cuenta nueva.");
            return crearNuevaCuenta(scanner);
        }
    }

    private static Cuenta cargarCuenta() throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO_CUENTA))) {
            return (Cuenta) ois.readObject();
        } catch (ClassNotFoundException e) {
            // Solo pasaría si el fichero estuviera corrupto
            throw new IOException("El fichero de datos está corrupto o no es compatible.", e);
        }
    }

    private static void guardarCuenta(Cuenta cuenta) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_CUENTA))) {
            oos.writeObject(cuenta);
        }
    }


    private static Cuenta crearNuevaCuenta(Scanner scanner) {
        System.out.println("--- Datos del titular ---");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Apellidos: ");
        String apellidos = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        Cliente cliente = new Cliente(nombre, apellidos, dni, telefono, email, direccion);

        System.out.println("--- Datos de la cuenta ---");
        System.out.print("Número de cuenta: ");
        String numeroCuenta = scanner.nextLine();

        return new Cuenta(numeroCuenta, cliente);
    }

    // ---------- Menú ----------

    private static void mostrarMenu() {
        System.out.println("\n===== MENÚ =====");
        System.out.println("1. Ingresar dinero");
        System.out.println("2. Retirar dinero");
        System.out.println("3. Consultar saldo y movimientos");
        System.out.println("4. Exportar movimientos");
        System.out.println("5. Finalizar aplicación");
        System.out.print("Elige una opción: ");
    }

    /**
     * Lee la opción elegida por el usuario. Si lo introducido no es un
     * número válido, devuelve -1 para que el switch lo trate igual que
     * cualquier otra opción fuera de rango (1-5), unificando ambos casos
     * de error en un único mensaje.
     */
    private static int leerOpcion(Scanner scanner) {
        String entrada = scanner.nextLine();
        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Pide un importe por consola. Devuelve Double.NaN si lo introducido
     * no es un número válido (ya se informa al usuario en ese caso).
     */
    private static double leerImporte(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        String entrada = scanner.nextLine();
        try {
            return Double.parseDouble(entrada.trim());
        } catch (NumberFormatException e) {
            System.out.println("Eso no es un número válido.");
            return Double.NaN;
        }
    }

    // ---------- Operaciones del menú ----------

    private static void realizarIngreso(Scanner scanner, Cuenta cuenta) {
        double importe = leerImporte(scanner, "Importe a ingresar: ");
        if (Double.isNaN(importe)) {
            return; // Ya se avisó del error de formato en leerImporte()
        }

        System.out.print("Concepto (opcional): ");
        String concepto = scanner.nextLine();

        try {
            cuenta.ingresar(importe, concepto);
            System.out.printf("Ingreso realizado. Nuevo saldo: %.2f €%n", cuenta.getSaldo());
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo realizar el ingreso: " + e.getMessage());
        }
    }

    private static void realizarRetirada(Scanner scanner, Cuenta cuenta) {
        double importe = leerImporte(scanner, "Importe a retirar: ");
        if (Double.isNaN(importe)) {
            return;
        }

        System.out.print("Concepto (opcional): ");
        String concepto = scanner.nextLine();

        try {
            cuenta.retirar(importe, concepto);
            System.out.printf("Retirada realizada. Nuevo saldo: %.2f €%n", cuenta.getSaldo());
        } catch (SaldoInsuficienteException | IllegalArgumentException e) {
            System.out.println("No se pudo realizar la retirada: " + e.getMessage());
        }
    }

    private static void consultarSaldoYMovimientos(Cuenta cuenta) {
        System.out.printf("%nSaldo actual: %.2f €%n", cuenta.getSaldo());

        if (cuenta.getMovimientos().isEmpty()) {
            System.out.println("No hay movimientos registrados todavía.");
            return;
        }

        System.out.println("--- Movimientos ---");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (Movimiento m : cuenta.getMovimientos()) {
            System.out.printf("[%s] %-8s | %-25s | %8.2f € | Saldo: %8.2f €%n",
                    m.getFecha().format(formato),
                    m.getTipo(),
                    m.getConcepto(),
                    m.getImporte(),
                    m.getSaldoResultante());
        }
    }

    /**
     * Exporta el listado completo de movimientos a un fichero CSV legible,
     * en datos/movimientos.csv. Se sobrescribe en cada exportación con el
     * estado actual de la lista de movimientos.
     */
    private static void exportarMovimientos(Cuenta cuenta) {
        if (cuenta.getMovimientos().isEmpty()) {
            System.out.println("No hay movimientos que exportar todavía.");
            return;
        }

        File ficheroCsv = new File(FICHERO_EXPORT_CSV);

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(ficheroCsv, StandardCharsets.UTF_8))) {

            writer.write("Fecha;Tipo;Concepto;Importe;Saldo resultante");
            writer.newLine();

            for (Movimiento m : cuenta.getMovimientos()) {
                writer.write(String.join(";",
                        m.getFecha().format(FORMATO_FECHA),
                        m.getTipo().toString(),
                        exportarCsv(m.getConcepto()),
                        String.format(Locale.of("es", "ES"), "%.2f", m.getImporte()),
                        String.format(Locale.of("es","ES"), "%.2f", m.getSaldoResultante())));
                writer.newLine();
            }

            System.out.println("Movimientos exportados correctamente a " + ficheroCsv.getPath());
        } catch (IOException e) {
            System.out.println("No se pudo exportar los movimientos: " + e.getMessage());
        }
    }

    /**
     * Prepara un valor de texto para insertarlo con seguridad en una celda
     * CSV: si contiene el delimitador (;), comillas o saltos de línea, lo
     * envuelve entre comillas dobles y duplica las comillas internas, tal
     * como exige el formato CSV estándar (RFC 4180).
     */
    private static String exportarCsv(String valor) {
        if (valor.contains(";") || valor.contains("\"") || valor.contains("\n")) {
            return "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }
}
