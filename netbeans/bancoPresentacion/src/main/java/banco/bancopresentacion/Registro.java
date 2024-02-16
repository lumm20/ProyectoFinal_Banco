/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package banco.bancopresentacion;

//import banco.bancodominio.Cliente;
//import banco.banconegocio.clienteRegistro;
//import banco.bancopersistencia.conexion.Conexion;
//import banco.bancopersistencia.conexion.IConexion;
//import banco.bancopersistencia.daos.ClienteDAO;
//import banco.bancopersistencia.excepciones.PersistenciaException;
import banco.banconegocio.controlador.ControlNegocio;
import banco.banconegocio.controlador.IControlNegocio;
import banco.banconegocio.excepciones.NegocioException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author molin
 */
public class Registro extends javax.swing.JFrame {

    private IControlNegocio control=new ControlNegocio();
    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtApellidoP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtApellidoM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtFechaNacimiento = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_calle = new javax.swing.JTextField();
        ButtonAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_colonia = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_cp = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_numeroDir = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Nombre:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 250, -1));

        jLabel3.setText("Apellido Paterno:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, -1, -1));
        jPanel1.add(txtApellidoP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 250, -1));

        jLabel4.setText("Apellido Materno:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));
        jPanel1.add(txtApellidoM, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 250, -1));

        jLabel5.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        txtFechaNacimiento.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.add(txtFechaNacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 250, -1));

        jLabel6.setText("Dirección");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, -1, -1));

        txt_calle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_calleActionPerformed(evt);
            }
        });
        jPanel1.add(txt_calle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 120, -1));

        ButtonAceptar.setBackground(new java.awt.Color(14, 33, 110));
        ButtonAceptar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ButtonAceptar.setForeground(new java.awt.Color(255, 255, 255));
        ButtonAceptar.setText("Aceptar");
        ButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonAceptarActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 540, 130, 40));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 600));

        jLabel7.setText("Calle:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 430, -1, -1));

        jLabel8.setText("Colonia:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, -1, -1));
        jPanel1.add(txt_colonia, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 450, 110, -1));

        jLabel9.setText("Codigo postal:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 480, -1, -1));

        txt_cp.setPreferredSize(new java.awt.Dimension(80, 22));
        jPanel1.add(txt_cp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 110, -1));

        jLabel10.setText("Numero:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 480, -1, -1));

        txt_numeroDir.setPreferredSize(new java.awt.Dimension(80, 22));
        jPanel1.add(txt_numeroDir, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 510, 110, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonAceptarActionPerformed
        // TODO add your handling code here:
        // Obtener los datos ingresados por el usuario
        String[] datosCliente={txtNombre.getText(),
        txtApellidoP.getText(),
        txtApellidoM.getText(),
        txtFechaNacimiento.getText()};
        
        String[] datosDireccion={txt_calle.getText(),
        txt_colonia.getText(),
        txt_cp.getText(),
        txt_numeroDir.getText()
        };
        try {
            this.agregarCliente(datosCliente,datosDireccion);
            JOptionPane.showMessageDialog(this, "se agrego correctamente al cliente");
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_ButtonAceptarActionPerformed

    private boolean validarCamposVacios()throws NegocioException{
        JTextField[] componentes={txtNombre,txtApellidoP,txt_calle,txt_colonia,txt_cp,txt_numeroDir};
        for(JTextField jtxt:componentes){
            if(jtxt.getText().isBlank())
                return false;
        }
        return true;
    }
    
    private boolean validarNombres()throws NegocioException{
        JTextField[] componentes={txtNombre,txtApellidoP,txtApellidoM,txt_calle,txt_colonia};
        Pattern patron=Pattern.compile("([a-zA-Z])(\\s*)");
        Matcher matcher;
        boolean flag=true;
        for(JTextField txt:componentes){
            matcher=patron.matcher(txt.getText());
            flag=matcher.matches();
            if(!flag)
                throw new NegocioException(""); 
        }
        return flag;
    }
    
    private void txt_calleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_calleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_calleActionPerformed

    private void agregarCliente(String[]datos_cliente, String[] datos_direccion) 
    throws NegocioException{
        int codigo_direccion=this.control.agregarDireccionCliente(datos_direccion[0], datos_direccion[1], datos_direccion[2], datos_direccion[3]);
        this.control.insertarCliente(datos_cliente[0], datos_cliente[1], datos_cliente[2], datos_cliente[3], codigo_direccion);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtApellidoM;
    private javax.swing.JTextField txtApellidoP;
    private javax.swing.JTextField txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txt_calle;
    private javax.swing.JTextField txt_colonia;
    private javax.swing.JTextField txt_cp;
    private javax.swing.JTextField txt_numeroDir;
    // End of variables declaration//GEN-END:variables
}
