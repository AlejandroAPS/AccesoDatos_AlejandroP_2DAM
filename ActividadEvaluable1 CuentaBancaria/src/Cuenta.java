import java.io.Serializable;
import java.util.ArrayList;

public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numeroCuenta;
    private Cliente titular;
    private double saldo;
    private ArrayList<Movimiento> movimientos;

    public Cuenta() {
        this.movimientos = new ArrayList<>();
    }

    public Cuenta(String numeroCuenta, Cliente titular) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
    }

    public Cuenta(String numeroCuenta, Cliente titular, double saldoInicial) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.movimientos = new ArrayList<>();
    }

    // --- Getters y setters ---

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public Cliente getTitular() {
        return titular;
    }

    public void setTitular(Cliente titular) {
        this.titular = titular;
    }

    public double getSaldo() {
        return saldo;
    }

    // No exponemos un setSaldo() público a propósito: el saldo solo debe
    // cambiar como consecuencia de un movimiento (ingreso/retirada), nunca
    // asignándolo directamente desde fuera de la clase.
    protected void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    @Override
    public String toString() {
        return "Cuenta {" +
                "numeroCuenta='" + numeroCuenta + '\'' +
                ", titular=" + (titular != null ? titular.getNombreCompleto() : "sin asignar") +
                ", saldo=" + saldo +
                ", nº movimientos=" + movimientos.size() +
                '}';
    }
}
