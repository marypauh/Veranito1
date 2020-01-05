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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarEstudiante.setText("Agregar Estudiante");

        btnCerrar.setText("Cerrar");

        btnAgregarSala.setText("Agregar Sala");

        btnConsultarE.setText("Consultar Estudiante");

        btnModificarSala.setText("Modificar Sala");

        btnReservarSala.setText("Reservar Sala");

        btnRegistrarIncidente.setText("Registrar Incidente");

        btnConsultarSala.setText("Consultar Sala");
        btnConsultarSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarSalaActionPerformed(evt);
            }
        });

        btnCancelarReserva.setText("Cancelar Reserva");

        btnCalificar.setText("Calificar Sala");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnReservarSala)
                            .addComponent(btnAgregarEstudiante)
                            .addComponent(btnAgregarSala))
                        .addGap(81, 81, 81)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnConsultarSala)
                            .addComponent(btnRegistrarIncidente)
                            .addComponent(btnModificarSala)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(btnCalificar))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConsultarE)
                        .addGap(75, 75, 75)
                        .addComponent(btnCancelarReserva)))
                .addContainerGap(92, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReservarSala)
                    .addComponent(btnRegistrarIncidente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarEstudiante)
                    .addComponent(btnModificarSala))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarSala)
                    .addComponent(btnConsultarSala))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConsultarE)
                    .addComponent(btnCancelarReserva))
                .addGap(18, 18, 18)
                .addComponent(btnCalificar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

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
    public javax.swing.JButton btnReservarSala;
    // End of variables declaration//GEN-END:variables
}
