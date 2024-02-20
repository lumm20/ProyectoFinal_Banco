/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author luiis
 */
public class Transferencia extends Transaccion {

    private String numCuentaDestino;

    public Transferencia() {
    }

    public Transferencia(int idTransaccion, BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {

        super(idTransaccion, monto, fechaHoraCreacion, numCuentaOrigen, "transferencia");
        this.numCuentaDestino = numCuentaDestino;
    }

    public Transferencia(BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {

        super(monto, fechaHoraCreacion, numCuentaDestino, "transferencia");
        this.numCuentaDestino = numCuentaDestino;
    }

    public String getNumCuentaDestino() {
        return numCuentaDestino;
    }

    public void setNumCuentaDestino(String numCuentaDestino) {
        this.numCuentaDestino = numCuentaDestino;
    }

    @Override
    public String toString() {
        return "Transferencia{" + "idTransaccion=" + idTransaccion + ", monto=" + monto + ", fechaHoraCreacion=" + fechaHoraCreacion + ", numCuentaOrigen=" + numCuentaOrigen + ", numCuentaDestino=" + numCuentaDestino + '}';
    }

}
