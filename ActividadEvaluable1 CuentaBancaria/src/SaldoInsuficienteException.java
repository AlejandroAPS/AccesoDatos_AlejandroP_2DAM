
/**
 * Excepción que indica que se ha intentado retirar más dinero del
 * disponible en la cuenta.
 *
 * Es una excepción "checked" (extiende de Exception, no de RuntimeException)
 * a propósito: queremos obligar a quien llame a retirar() a gestionarla
 * explícitamente con un try/catch, ya que es un caso de negocio esperado
 * y no un error de programación.
 */
public class SaldoInsuficienteException extends Exception {

    private static final long serialVersionUID = 1L;

    private final double saldoDisponible;
    private final double importeSolicitado;

    public SaldoInsuficienteException(double saldoDisponible, double importeSolicitado) {
        super(String.format(
                "Saldo insuficiente: disponible %.2f €, solicitado %.2f €",
                saldoDisponible, importeSolicitado));
        this.saldoDisponible = saldoDisponible;
        this.importeSolicitado = importeSolicitado;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public double getImporteSolicitado() {
        return importeSolicitado;
    }
}