import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Representa una cuenta bancaria: tiene un cliente propietario, un número
 * de cuenta, un saldo y el histórico de movimientos realizados.
 */
public class Cuenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private String numeroCuenta;
    private Cliente titular;
    private double saldo;
    private ArrayList<Movimiento> movimientos;

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

    /**
     * Ingresa dinero en la cuenta y registra el movimiento correspondiente.
     * La validación del formato del importe (que sea un número) es
     * responsabilidad de quien llama a este método (el menú de consola);
     * aquí solo se valida la regla de negocio: el importe debe ser positivo.
     *
     * @param importe  cantidad a ingresar, debe ser mayor que 0
     * @param concepto descripción del ingreso; si llega vacío o null se
     *                 sustituye por un texto por defecto
     * @throws IllegalArgumentException si el importe es negativo o cero
     */
    public void ingresar(double importe, String concepto) {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe a ingresar debe ser mayor que 0");
        }

        String conceptoFinal = (concepto == null || concepto.isBlank()) ? "Sin concepto especificado" : concepto;

        saldo += importe;
        movimientos.add(new Movimiento(
                LocalDateTime.now(),
                Movimiento.TipoMovimiento.INGRESO,
                conceptoFinal,
                importe,
                saldo));
    }

    /**
     * Retira dinero de la cuenta, siempre que haya saldo suficiente, y
     * registra el movimiento correspondiente.
     *
     * @param importe  cantidad a retirar, debe ser mayor que 0
     * @param concepto descripción de la retirada; si llega vacío o null se
     *                 sustituye por un texto por defecto
     * @throws IllegalArgumentException     si el importe es negativo o cero
     * @throws SaldoInsuficienteException si el saldo actual es menor que el importe solicitado
     */
    public void retirar(double importe, String concepto) throws SaldoInsuficienteException {
        if (importe <= 0) {
            throw new IllegalArgumentException("El importe a retirar debe ser mayor que 0");
        }
        if (importe > saldo) {
            // No modificamos el saldo ni creamos el movimiento: si no se
            // puede completar la operación, la cuenta debe quedar exactamente
            // como estaba antes de intentarla.
            throw new SaldoInsuficienteException(saldo, importe);
        }

        String conceptoFinal = (concepto == null || concepto.isBlank()) ? "Sin concepto especificado" : concepto;

        saldo -= importe;
        movimientos.add(new Movimiento(
                LocalDateTime.now(),
                Movimiento.TipoMovimiento.RETIRADA,
                conceptoFinal,
                importe,
                saldo));
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