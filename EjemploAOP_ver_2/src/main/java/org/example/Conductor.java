import org.example.Coche;

public class Conductor {
    public void antesArrancar(Coche co) {
        System.out.println("Colocamos los espejos al coche "+co.getMatricula());
        System.out.println("Regulamos el asiento del "+co.getMatricula());
        System.out.println("Nos ponemos el cinturón de seguridad en el coche "+co.getModelo());
    }


    public void despuesArrancar(Coche co)
    {
        System.out.println("Conducimos con cuidado el coche "+co.getModelo());
    }
}
