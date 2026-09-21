import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String args[]) {
        //Crear objeto Alumno
        Alumno alu1=new Alumno("Pedro",25);
        alu1.calificar("Matemáticas",50);
        alu1.calificar("Inglés",75);
        alu1.calificar("Informática",95);
        alu1.calificar("Lengua",60);

        //Abrir fichero para escritura
        FileOutputStream file;
        ObjectOutputStream buffer;
        try {
            file = new FileOutputStream("alumno.dat");
            buffer = new ObjectOutputStream(file);
        } catch (IOException e) {
            System.out.println("No se ha podido abrir el fichero");
            System.out.println(e.getMessage());
            return;
        }

        //Guarda objeto en el fichero alumno.dat
        try{
            buffer.writeObject(alu1);
            System.out.println("El objeto se ha grabado con éxito");
        }catch(IOException e){
            System.out.println("Error al escribir en el fichero");
            System.out.println(e.getMessage());
        }

        //Cerrar el fichero
        try{
            buffer.close();
            file.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el fichero");
            System.out.println(e.getMessage());
        }

    }
}
