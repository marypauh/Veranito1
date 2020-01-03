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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarEstudiante.setText("Agregar Estudiante");

        btnCerrar.setText("Cerrar");

        btnAgregarSala.setText("Agregar Sala");

        btnConsultarE.setText("Consultar Estudiante");

        btnModificarSala.setText("Modificar Sala");

        btnReservarSala.setText("Reservar Sala");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarSala)
                            .addComponent(btnAgregarEstudiante)
                            .addComponent(btnConsultarE)
                            .addComponent(btnReservarSala)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addComponent(btnModificarSala))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(184, 184, 184)
                        .addComponent(btnCerrar)))
                .addContainerGap(109, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(btnReservarSala)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarEstudiante)
                .addGap(29, 29, 29)
                .addComponent(btnAgregarSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnConsultarE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(btnModificarSala)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarEstudiante;
    public javax.swing.JButton btnAgregarSala;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnConsultarE;
    public javax.swing.JButton btnModificarSala;
    public javax.swing.JButton btnReservarSala;
    // End of variables declaration//GEN-END:variables
}
