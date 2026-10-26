package com.ejemplo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fiesta")
public class Fiesta {

    @Value("Cumpleaños de Ana")
    private String nombre;

    private final Local local;

    @Autowired
    public Fiesta(Local local) {
        this.local = local;
    }

    public void celebrar() {
        System.out.println("¡Comienza la fiesta \"" + nombre + "\" en " + local + "!");
    }

    public void terminar() {
        System.out.println("La fiesta \"" + nombre + "\" ha terminado en " + local.getNombre() + ". ¡Hasta la próxima!");
    }
}