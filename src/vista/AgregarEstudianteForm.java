package vista;

/**
 *
 * @author María Paula
 */
public class AgregarEstudianteForm extends javax.swing.JFrame {

  /**
   * Creates new form AgregarEstudianteForm
   */
  public AgregarEstudianteForm() {
    initComponents();
  }
    
  /**
  * 
  * @return si los campos están vacíos
  */
  public boolean EstudianteDatosCorrectos(){
    return txtCarnet != null && txtNombre!=null && txtCarrera!=null || 
    txtEmail!=null && txtCalificacion!=null && txtTelefono!=null;
  }
  
  
  /**
   * Metodo para abrir la ventana anterior
   * @param ventanaAnterior 
   */
  public void abrirVentanaAnterior(AgregarEstudianteForm ventanaAnterior){
    ventanaAnterior.setVisible(true);
    ventanaAnterior.setLocationRelativeTo(null);
  }
  
  /**
   * Metodo para regresar al inicio de sesion
   */
  public void volverMenu(){
    this.setVisible(false);
  }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblCarnet = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCarrera = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblCalificacion = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCarrera = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCalificacion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTitulo.setText("Agregar Estudiante");
        getContentPane().add(lblTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        lblCarnet.setText("Carnet: ");
        getContentPane().add(lblCarnet, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        lblNombre.setText("Nombre: ");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        lblCarrera.setText("Carrera: ");
        getContentPane().add(lblCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        lblEmail.setText("Email: ");
        getContentPane().add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, -1));

        lblCalificacion.setText("Calificacion: ");
        getContentPane().add(lblCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 280, -1, -1));

        lblTelefono.setText("Telefono: ");
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 330, -1, -1));
        getContentPane().add(txtCarnet, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 150, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 150, -1));

        txtCarrera.setToolTipText("");
        getContentPane().add(txtCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 150, -1));
        getContentPane().add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 170, -1));
        getContentPane().add(txtCalificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 140, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 330, 160, -1));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 380, -1, -1));

        btnMenu.setText("Menu");
        getContentPane().add(btnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnMenu;
    private javax.swing.JLabel lblCalificacion;
    private javax.swing.JLabel lblCarnet;
    private javax.swing.JLabel lblCarrera;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    public javax.swing.JTextField txtCalificacion;
    public javax.swing.JTextField txtCarnet;
    public javax.swing.JTextField txtCarrera;
    public javax.swing.JTextField txtEmail;
    public javax.swing.JTextField txtNombre;
    public javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
