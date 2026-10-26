package com.ejemplo;

public class Local {
    private String nombre;
    private String direccion;

    public Local() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String toString() {
        return nombre + " (" + direccion + ")";
    }
}