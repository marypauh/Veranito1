/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

/**
 *
 * @author María Paula
 */
public class MenuForm extends javax.swing.JFrame {

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
        initComponents();
    }

   
  /**
   * Metodo para abrir ventana anterior
   * @param ventanaAnterior 
   */
  public void abrirVentanaAnterior(MenuForm ventanaAnterior){
    ventanaAnterior.setVisible(true);
    ventanaAnterior.setLocationRelativeTo(null);
  }
 
  
  /**
   * Metodo para cancelar inicio de sesion
   */
  public void cerrarMenu(){
    System.exit(0);
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarEstudiante = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnAgregarSala = new javax.swing.JButton();
        btnConsultarE = new javax.swing.JButton();
        btnModificarSala = new javax.swing.JButton();
        btnReservarSala = new javax.swing.JButton();
        btnRegistrarIncidente = new javax.swing.JButton();
        btnConsultarSala = new javax.swing.JButton();
        btnCancelarReserva = new javax.swing.JButton();
        btnCalificar = new javax.swing.JButton();
        btnReportes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAgregarEstudiante.setText("Agregar Estudiante");
        getContentPane().add(btnAgregarEstudiante, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        btnCerrar.setText("Cerrar");
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, -1, -1));

        btnAgregarSala.setText("Agregar Sala");
        getContentPane().add(btnAgregarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        btnConsultarE.setText("Consultar Estudiante");
        getContentPane().add(btnConsultarE, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));

        btnModificarSala.setText("Modificar Sala");
        getContentPane().add(btnModificarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

        btnReservarSala.setText("Reservar Sala");
        getContentPane().add(btnReservarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));

        btnRegistrarIncidente.setText("Registrar Incidente");
        getContentPane().add(btnRegistrarIncidente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        btnConsultarSala.setText("Consultar Sala");
        btnConsultarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSalaActionPerformed(evt);
            }
        });
        getContentPane().add(btnConsultarSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        btnCancelarReserva.setText("Cancelar Reserva");
        getContentPane().add(btnCancelarReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        btnCalificar.setText("Calificar Sala");
        getContentPane().add(btnCalificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 200, -1, -1));

        btnReportes.setText("Reportes");
        getContentPane().add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultarSalaActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarEstudiante;
    public javax.swing.JButton btnAgregarSala;
    public javax.swing.JButton btnCalificar;
    public javax.swing.JButton btnCancelarReserva;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnConsultarE;
    public javax.swing.JButton btnConsultarSala;
    public javax.swing.JButton btnModificarSala;
    public javax.swing.JButton btnRegistrarIncidente;
    public javax.swing.JButton btnReportes;
    public javax.swing.JButton btnReservarSala;
    // End of variables declaration//GEN-END:variables
}
