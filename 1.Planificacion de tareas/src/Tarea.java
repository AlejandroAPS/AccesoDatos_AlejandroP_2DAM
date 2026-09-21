public class Tarea {
    private int id;
    private String titulo;
    private int duracion;
    private boolean estado;   // true = Completada, false = Pendiente

    public Tarea(int id, String titulo, int duracion, boolean estado) {
        this.id = id;
        this.titulo = titulo;
        this.duracion = duracion;
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }
    public int getId() {
        return id;
    }

    public int getduracion() {
        return duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public boolean getEstado() {
        return estado;
    }
}