package clase.beans;

public class Obra {
    public void comenzarObra (){
        System.out.println ("Comienza el espectáculo: En un lugar de la Mancha de cuyo nombre no puedo acordarme...");
    }
    public void descanso (){
        System.out.println ("Estamos en el descanso. Pueden pasar por el bar.");
    }
    public void reanudarObra (){
        System.out.println ("Reanudamos: no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor");
    }
    public void finObra () throws Exception{
        System.out.println ("Hemos terminado el espectáculo. Esperamos que les haya gustado");
        java.util.Random rand = new java.util.Random();
        int x = rand.nextInt(100);
        if (x>50){
            throw new Exception ("La obra ha salido mal");
        }
    }
}
