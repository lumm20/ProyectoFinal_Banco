/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco.bancodominio;

<<<<<<< HEAD
=======
import java.math.BigDecimal;
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
import java.sql.Date;

/**
 *
 * @author luiis
 */
public class Transferencia extends Transaccion{
    private String numCuentaDestino;

    public Transferencia() {
    }

<<<<<<< HEAD
    public Transferencia(int idTransaccion, float monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {
=======
    public Transferencia(int idTransaccion, BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
        super(idTransaccion,monto,fechaHoraCreacion,numCuentaOrigen,"transferencia");
        this.numCuentaDestino=numCuentaDestino;
    }

<<<<<<< HEAD
    public Transferencia(float monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {
=======
    public Transferencia(BigDecimal monto, Date fechaHoraCreacion, String numCuentaOrigen, String numCuentaDestino) {
>>>>>>> 208485a08e5a867a928b4baf0b034cb91e1b81ae
        super(monto,fechaHoraCreacion,numCuentaDestino,"transferencia");
        this.numCuentaDestino=numCuentaDestino;
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
