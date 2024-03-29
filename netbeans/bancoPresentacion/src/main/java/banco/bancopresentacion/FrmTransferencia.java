/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package banco.bancopresentacion;

import banco.bancodominio.Transaccion;
import banco.banconegocio.controlador.ControlNegocio;
import banco.banconegocio.controlador.IControlNegocio;
import banco.banconegocio.excepciones.NegocioException;
import banco.bancopresentacion.control.ControlPresentacion;
import java.math.BigDecimal;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author luiis
 */
public class FrmTransferencia extends javax.swing.JFrame {
    ControlPresentacion control=new ControlPresentacion();
    IControlNegocio controlN=new ControlNegocio();
    private int idCliente, idDireccion;
    /**
     * Creates new form Transferencia
     */
    public FrmTransferencia() {
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_numCuentaDestino = new javax.swing.JTextField();
        lbl_atras = new javax.swing.JLabel();
        lbl_confirmar = new javax.swing.JLabel();
        txt_monto = new javax.swing.JTextField();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(360, 630));
        setMinimumSize(new java.awt.Dimension(360, 630));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_numCuentaDestino.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txt_numCuentaDestino, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 240, 30));

        lbl_atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_atrasMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 40, 20));

        lbl_confirmar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_confirmarMouseClicked(evt);
            }
        });
        getContentPane().add(lbl_confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 120, 40));

        txt_monto.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(txt_monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 342, 240, 30));

        fondo.setIcon(new javax.swing.ImageIcon("C:\\Users\\luiis\\Dropbox\\PC\\Documents\\proyectoFinal-Banco-BDA\\netbeans\\bancoPresentacion\\src\\main\\java\\banco\\bancopresentacion\\Imagenes\\fondo-transferencia.png")); // NOI18N
        getContentPane().add(fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 630));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lbl_confirmarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_confirmarMouseClicked
        // TODO add your handling code here:
        String monto=txt_monto.getText();
        String numCuentaDestino=txt_numCuentaDestino.getText();
        try {
            this.confirmarTransferencia(monto, control.obtenNumCuentaActual(), numCuentaDestino);
            control.despliegaPrincipal();
            this.dispose();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_lbl_confirmarMouseClicked

    private void lbl_atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_atrasMouseClicked
        // TODO add your handling code here:
        control.despliegaPrincipal();
        this.dispose();
    }//GEN-LAST:event_lbl_atrasMouseClicked

    private void confirmarTransferencia(String monto, 
            String numCuentaOrigen, String numCuentaDestino)throws NegocioException{
        int idTransaccion;
        Date fechaHora=new Date(System.currentTimeMillis());
        BigDecimal montoBD=new BigDecimal(monto);
        Transaccion transaccion=new Transaccion(montoBD, fechaHora, numCuentaOrigen, "transferencia");
        idTransaccion = this.controlN.procesarTransaccion(transaccion);
        this.controlN.procesarTransferencia(idTransaccion, numCuentaDestino);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel lbl_atras;
    private javax.swing.JLabel lbl_confirmar;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_numCuentaDestino;
    // End of variables declaration//GEN-END:variables
}
