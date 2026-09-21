import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class GestorTareas {

    public static void main(String[] args) {
        ArrayList<Tarea> listaTareas = new ArrayList<>();
        int totalLeidas = 0;
        int totalPendientes = 0;

        // 1. Leer el fichero tareas.csv con Scanner
        try (Scanner scanner = new Scanner(new File("CESEUVE.csv"))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine().trim();
                if (linea.isEmpty()) {
                    continue;
                }

                String[] campos = linea.split(";");
                if (campos.length != 4) {
                    System.err.println("Línea con formato incorrecto: " + linea);
                    continue;
                }

                try {
                    int id = Integer.parseInt(campos[0].trim());
                    String titulo = campos[1].trim();
                    int duracion = Integer.parseInt(campos[2].trim());
                    boolean estado = Boolean.parseBoolean(campos[3].trim());

                    Tarea tarea = new Tarea(id, titulo, duracion, estado);
                    listaTareas.add(tarea);
                    totalLeidas++;
                } catch (NumberFormatException e) {
                    System.err.println("Error al parsear números en la línea: " + linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encontró el fichero tareas.csv");
            return;
        }

        // 2. Filtrar tareas pendientes y clasificarlas
        ArrayList<Tarea> tareasPendientes = new ArrayList<>();
        for (Tarea tarea : listaTareas) {
            if (!tarea.isEstado()) {
                tareasPendientes.add(tarea);
            }
        }

        // 3. Guardar las tareas pendientes en el archivo nuevo tareas_pendientes.csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("tareas_pendientes.csv"))) {
            for (Tarea tarea : tareasPendientes) {
                String linea = tarea.getId() + ";"
                        + tarea.getTitulo() + ";"
                        + tarea.getduracion() + ";"
                        + tarea.getEstado();
                writer.write(linea);
                writer.newLine();
                totalPendientes++;
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el fichero tareas_pendientes.csv: " + e.getMessage());
            return;
        }

        // 4. Mostrar resumen por consola
        System.out.println("Tareas leídas del fichero: " + totalLeidas);
        System.out.println("Tareas pendientes guardadas: " + totalPendientes);
    }
}

