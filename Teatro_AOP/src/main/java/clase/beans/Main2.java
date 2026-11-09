import org.example.Obra;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public static void main (String args[]){
    try{
        ApplicationContext context = new ClassPathXmlApplicationContext
                ("configBeans.xml");
        org.example.Obra ob = (Obra) (context.getBean("obra"));
        ob.comenzarObra ();
        ob.descanso();
        ob.reanudarObra();
        ob.finObra();
    }catch (Exception e){
    }
}
