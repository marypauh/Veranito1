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
public class MenuReportesForm extends javax.swing.JFrame {

    /**
     * Creates new form MenuForm
     */
    public MenuReportesForm() {
        initComponents();
    }

   
  /**
   * Metodo para abrir ventana anterior
   * @param ventanaAnterior 
   */
  public void abrirVentanaAnterior(MenuReportesForm ventanaAnterior){
    ventanaAnterior.setVisible(true);
    ventanaAnterior.setLocationRelativeTo(null);
  }
 
  
  /**
   * Metodo para cancelar inicio de sesion
   */
  public void cerrarMenu(){
    this.setVisible(false);
  }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnHorariosUtilizados = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnCarreras = new javax.swing.JButton();
        btnSalasPuntuacion = new javax.swing.JButton();
        btnSalasUtilizadas = new javax.swing.JButton();
        btnBitacoraReservas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnHorariosUtilizados.setText("Horarios más utilizados");
        getContentPane().add(btnHorariosUtilizados, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 90, -1, -1));

        btnCerrar.setText("Cerrar");
        getContentPane().add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        btnCarreras.setText("Carreras que más usan salas");
        getContentPane().add(btnCarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, -1, -1));

        btnSalasPuntuacion.setText("Salas con mayor puntuación");
        getContentPane().add(btnSalasPuntuacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, -1, -1));

        btnSalasUtilizadas.setText("Salas más utilizadas");
        getContentPane().add(btnSalasUtilizadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        btnBitacoraReservas.setText("Bitacora de Reservas");
        getContentPane().add(btnBitacoraReservas, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 170, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBitacoraReservas;
    public javax.swing.JButton btnCarreras;
    public javax.swing.JButton btnCerrar;
    public javax.swing.JButton btnHorariosUtilizados;
    public javax.swing.JButton btnSalasPuntuacion;
    public javax.swing.JButton btnSalasUtilizadas;
    // End of variables declaration//GEN-END:variables
}
