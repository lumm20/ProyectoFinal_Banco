/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package banco.bancopresentaciones;

import banco.banconegocio.controlador.ControlNegocio;
import banco.banconegocio.controlador.IControlNegocio;
import banco.banconegocio.excepciones.NegocioException;
import banco.bancopresentacion.control.ControlPresentacion;
import banco.bancopresentacion.utilities.FondoImagen;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author luiis
 */
public class DlgUsuario extends javax.swing.JDialog {
    private int idCliente;
    IControlNegocio control=new ControlNegocio();
    ControlPresentacion controlP = new ControlPresentacion();
    /**
     * Creates new form DlgUsuario
     */
    public DlgUsuario(java.awt.Frame parent, boolean modal, int idCliente) {
        super(parent, modal);
        initComponents();
        setFondo();
        this.idCliente=idCliente;
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

        psw_1 = new javax.swing.JPasswordField();
        psw_2 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txt_usuario = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_aceptar = new javax.swing.JButton();
        fondoUsuario = new javax.swing.JLabel();
        ButtonRegreso = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(psw_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 180, 30));
        getContentPane().add(psw_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 180, 30));

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel1.setText("Nombre de usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 180, 35));

        txt_usuario.setFont(new java.awt.Font("Nirmala UI", 0, 14)); // NOI18N
        getContentPane().add(txt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 180, 30));

        jLabel2.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jLabel3.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        jLabel3.setText("Repite la contraseña:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        btn_aceptar.setBackground(new java.awt.Color(204, 204, 255));
        btn_aceptar.setFont(new java.awt.Font("Nirmala UI", 1, 18)); // NOI18N
        btn_aceptar.setForeground(new java.awt.Color(0, 0, 51));
        btn_aceptar.setText("aceptar");
        btn_aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_aceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btn_aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, -1, -1));
        getContentPane().add(fondoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 350));

        ButtonRegreso.setBackground(new java.awt.Color(14, 33, 110));
        ButtonRegreso.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ButtonRegreso.setForeground(new java.awt.Color(255, 255, 255));
        ButtonRegreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRegresoActionPerformed(evt);
            }
        });
        getContentPane().add(ButtonRegreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_aceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_aceptarActionPerformed
        // TODO add your handling code here:
        String usuario=txt_usuario.getText();
        String contra1=String.copyValueOf(psw_1.getPassword());
        String contra2=String.copyValueOf(psw_1.getPassword());
        try{
            this.guardarUsuario(usuario, contra1, contra2);
            this.dispose();
        }catch(NegocioException e){
            System.out.println(e.getCause());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btn_aceptarActionPerformed

    private void ButtonRegresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRegresoActionPerformed
        // TODO add your handling code here:
        controlP.despliegaInicio();
        this.dispose();
    }//GEN-LAST:event_ButtonRegresoActionPerformed

    public void setFondo() {
        try {
            File file = new File("Imagenes/fondo2.png");
            BufferedImage imagen = ImageIO.read(file);
            FondoImagen fondo = new FondoImagen(imagen);
            fondoUsuario.setBorder(fondo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * guarda el usuario en la base de datos
     * @param user a guardar, si no existe
     * @param contra1 a guardar
     * @param contra2 confirmacion de la contrasena a establecer
     * @throws NegocioException en caso de que ocurra un erro al guardar el usuario
     */
    private void guardarUsuario(String user, String contra1, String contra2)throws NegocioException{
        if(!user.isBlank() && !contra1.isBlank() && !contra2.isBlank()){
            if(contra1.equals(contra2)){
                control.guardarUsuario(idCliente, user, contra1);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonRegreso;
    private javax.swing.JButton btn_aceptar;
    private javax.swing.JLabel fondoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField psw_1;
    private javax.swing.JPasswordField psw_2;
    private javax.swing.JTextField txt_usuario;
    // End of variables declaration//GEN-END:variables
}
