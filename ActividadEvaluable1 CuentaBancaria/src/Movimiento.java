import java.io.Serializable;
import java.time.LocalDateTime;

public class Movimiento implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum TipoMovimiento {
        INGRESO,
        RETIRADA
    }

    private LocalDateTime fecha;
    private TipoMovimiento tipo;
    private String concepto;
    private double importe;
    private double saldoResultante;


    public Movimiento(LocalDateTime fecha, TipoMovimiento tipo, String concepto, double importe, double saldoResultante) {
        this.fecha = fecha;
        this.tipo = tipo;
        this.concepto = concepto;
        this.importe = importe;
        this.saldoResultante = saldoResultante;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getImporte() {
        return importe;
    }

    public double getSaldoResultante() {
        return saldoResultante;
    }

    @Override
    public String toString() {
        return "Movimiento {" +
                "fecha=" + fecha +
                ", tipo=" + tipo +
                ", concepto='" + concepto + '\'' +
                ", importe=" + importe +
                ", saldoResultante=" + saldoResultante +
                '}';
    }
}