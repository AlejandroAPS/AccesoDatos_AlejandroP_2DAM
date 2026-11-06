package com.ejemplo;

public class Fiesta {
    private String nombre;
    private Local local;   // dependencia a inyectar

    public Fiesta() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Local getLocal() { return local; }
    public void setLocal(Local local) { this.local = local; }

    public void celebrar() {
        System.out.println("¡Comienza la fiesta \"" + nombre + "\" en " + local + "!");
    }

    public void terminar() {
        System.out.println("La fiesta \"" + nombre + "\" ha terminado en " + local.getNombre() + ". ¡Hasta la próxima!");
    }
}
