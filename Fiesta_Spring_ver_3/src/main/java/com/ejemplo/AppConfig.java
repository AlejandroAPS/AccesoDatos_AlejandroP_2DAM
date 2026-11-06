package com.ejemplo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Local local() {
        Local local = new Local();
        local.setNombre("Sala Aurora");
        local.setDireccion("Calle Mayor 12, Getafe");
        return local;
    }

    @Bean
    public Fiesta fiesta() {
        Fiesta fiesta = new Fiesta();
        fiesta.setNombre("Cumpleaños de Ana");
        fiesta.setLocal(local());   // inyección mediante llamada al método @Bean
        return fiesta;
    }
}