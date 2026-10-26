package com.ejemplo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("local")
public class Local {

    @Value("Sala Aurora")
    private String nombre;

    @Value("Calle Mayor 12, Getafe")
    private String direccion;

    public Local() {}

    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }

    @Override
    public String toString() {
        return nombre + " (" + direccion + ")";
    }
}