public class Tarea {
    private int id;
    private String titulo;
    private int duracion;
    private boolean completada;   // true = Completada, false = Pendiente

    public Tarea(int id, String titulo, int duracion, boolean completada) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.completada = completada;
    }

    public boolean isCompletada() {
        return completada;
    }

    public boolean isPendiente() {
        return !completada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getduracion() {
        return duracion;
    }

    public void setduracion(int duracion) {
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
// Al leer del CSV:
    // boolean completada = "Completada".equalsIgnoreCase(campos[3].trim());

    // Al escribir (si hiciera falta el texto):
    // String estadoTexto = completada ? "Completada" : "Pendiente";
}