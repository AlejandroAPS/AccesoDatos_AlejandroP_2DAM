package org.example;

import com.ejemplo.AppConfig;
import com.ejemplo.Fiesta;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext contexto =
                new AnnotationConfigApplicationContext(AppConfig.class);

        Fiesta fiesta = contexto.getBean("fiesta", Fiesta.class);

        fiesta.celebrar();
        fiesta.terminar();

        contexto.close();
    }
}