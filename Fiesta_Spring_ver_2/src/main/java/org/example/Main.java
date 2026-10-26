package org.example;

import com.ejemplo.Fiesta;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext contexto =
                new ClassPathXmlApplicationContext("context.xml");

        Fiesta fiesta = contexto.getBean("fiesta", Fiesta.class);

        fiesta.celebrar();
        fiesta.terminar();

        contexto.close();
    }
}
