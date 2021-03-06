/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa1_presentacion;

import SistemaLara.capa1_presentacion.util.Animacion;
import SistemaLara.capa1_presentacion.util.Mensaje;
import SistemaLara.capa1_presentacion.util.Verificador;

import SistemaLara.capa2_aplicacion.GestionarNotaDebitoServicio;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.NotaDebito;
import javax.swing.table.TableColumnModel;
import mastersoft.modelo.ModeloTabla;
import mastersoft.tabladatos.Columna;
import mastersoft.tabladatos.Tabla;
import necesario.RSScrollBar;

//import motorepuestos.capa1_presentacion.util.Mensaje;
//import motorepuestos.capa1_presentacion.util.Verificador;
//import motorepuestos.capa2_aplicacion.Almacen.GestionarFacturaServicio;
//import motorepuestos.capa3_dominio.Factura;
/**
 *
 * @author FiveCod Software
 */
public class FormGestionarNotaDebito extends javax.swing.JDialog {

    private GestionarNotaDebitoServicio gestionarNotaDebitoServicio;
    public static NotaDebito notaDebitoSeleccionado;
    public static int TIPO_ADMINISTRADOR = 1;
    public static int TIPO_LIQUIDACION = 2;
    private int TIPO_USUARIO;

    public FormGestionarNotaDebito(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();
        BarraDesplzamiento();
        Animacion.moverParaIzquierda(this);
        this.setLocationRelativeTo(null);
//            this.TIPO_USUARIO = tipo;
        notaDebitoSeleccionado = new NotaDebito();
        gestionarNotaDebitoServicio = new GestionarNotaDebitoServicio();
        inicializarTablaColumnas();
        inicializarTabla();
        inabilitarBotones(true);
        btnEliminar.setEnabled(false);
        popMenu.add(pnlMenu);
        modificarTama├▒oComponentes();

    }

    public void modificarTama├▒oComponentes() {

//        JOptionPane.showMessageDialog(null, FormMenu.posicion_x_form_menu_axuliar);
//        this.setBounds(FormMenu.posicion_x_form_menu_axuliar, this.getY(), FormMenu.ancho_para_formularios, this.getHeight());
    }

    private void inicializarTablaColumnas() {
        Tabla tabla = new Tabla();
        tabla.agregarColumna(new Columna("CODIGO", "java.lang.String"));
        tabla.agregarColumna(new Columna("NRO NOTA DEBIDO", "java.lang.String"));
        tabla.agregarColumna(new Columna("NRO FACTURA", "java.lang.String"));
        tabla.agregarColumna(new Columna("PROVEEDOR", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA EMISI├ôN", "java.lang.String"));
        tabla.agregarColumna(new Columna("FECHA EM. COMPROBANTE", "java.lang.String"));

        tabla.agregarColumna(new Columna("VALOR VENTA", "java.lang.String"));
        tabla.agregarColumna(new Columna("IGV", "java.lang.String"));
        tabla.agregarColumna(new Columna("TOTAL", "java.lang.String"));
        tabla.agregarColumna(new Columna("MONEDA", "java.lang.String"));

        ModeloTabla modeloTabla = new ModeloTabla(tabla);
        tableNotaDebito.setModel(modeloTabla);
        TableColumnModel columnModel = tableNotaDebito.getColumnModel();
//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(0)).setWidth(0);
//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(0)).setMinWidth(0);
//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(0)).setMaxWidth(0);

        columnModel.getColumn(1).setPreferredWidth(50);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(120);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);
        columnModel.getColumn(6).setPreferredWidth(50);
        columnModel.getColumn(7).setPreferredWidth(50);
        columnModel.getColumn(8).setPreferredWidth(20);

//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(10)).setWidth(0);
//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(10)).setMinWidth(0);
//        tableNotaDebito.getColumn(tableNotaDebito.getColumnName(10)).setMaxWidth(0);
        tableNotaDebito.setColumnModel(columnModel);
    }

    public final void BarraDesplzamiento() {
        jScrollPane3.getVerticalScrollBar().setUI(new RSScrollBar());
        jScrollPane3.getHorizontalScrollBar().setUI(new RSScrollBar());
    }

    public void limpiarTabla() {
        ModeloTabla modelo = (ModeloTabla) tableNotaDebito.getModel();
        modelo.eliminarTotalFilas();
    }

    private void inicializarTabla() {
        try {
            limpiarTabla();
            gestionarNotaDebitoServicio.mostrarNotaDebitos(1, tableNotaDebito);
        } catch (Exception e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();
        btnModificar = new rojeru_san.RSButton();
        btnEliminar = new rojeru_san.RSButton();
        popMenu = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        rSButton2 = new rojeru_san.RSButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableNotaDebito = new rojerusan.RSTableMetro();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar = new org.jdesktop.swingx.JXSearchField();
        btnAgregar = new rojeru_san.RSButton();
        btnCancelar = new rojeru_san.RSButton();

        btnModificar.setBackground(new java.awt.Color(65, 94, 255));
        btnModificar.setBorder(null);
        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Editar.png"))); // NOI18N
        btnModificar.setText("MODIFICAR");
        btnModificar.setColorHover(new java.awt.Color(255, 82, 54));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnModificar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModificar.setIconTextGap(2);
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(65, 94, 255));
        btnEliminar.setBorder(null);
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("ELIMINAR");
        btnEliminar.setColorHover(new java.awt.Color(255, 82, 54));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnEliminar.setIconTextGap(2);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlMenuLayout.setVerticalGroup(
            pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLayout.createSequentialGroup()
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        popMenu.setBackground(new java.awt.Color(65, 94, 255));
        popMenu.setForeground(new java.awt.Color(65, 94, 255));
        popMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(65, 94, 255), 2));

        jPanel2.setBackground(new java.awt.Color(65, 94, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIONAR DATOS DE NOTA DE DEBITO ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Factura.png"))); // NOI18N

        rSButton2.setBackground(new java.awt.Color(65, 94, 255));
        rSButton2.setBorder(null);
        rSButton2.setText("X");
        rSButton2.setColorHover(new java.awt.Color(255, 68, 68));
        rSButton2.setFont(new java.awt.Font("Roboto Bold", 0, 20)); // NOI18N
        rSButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rSButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 300, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addComponent(rSButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder1 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder1.setShowLeftShadow(true);
        dropShadowBorder1.setShowTopShadow(true);
        jPanel3.setBorder(dropShadowBorder1);

        tableNotaDebito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableNotaDebito.setAltoHead(30);
        tableNotaDebito.setColorBackgoundHead(new java.awt.Color(65, 94, 255));
        tableNotaDebito.setColorFilasForeground1(new java.awt.Color(65, 94, 255));
        tableNotaDebito.setColorFilasForeground2(new java.awt.Color(65, 94, 255));
        tableNotaDebito.setColorSelBackgound(new java.awt.Color(253, 173, 1));
        tableNotaDebito.setComponentPopupMenu(popMenu);
        tableNotaDebito.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tableNotaDebito.setFuenteHead(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tableNotaDebito.setGrosorBordeFilas(0);
        tableNotaDebito.setGrosorBordeHead(0);
        tableNotaDebito.setRowHeight(20);
        tableNotaDebito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tableNotaDebitoMousePressed(evt);
            }
        });
        tableNotaDebito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableNotaDebitoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tableNotaDebitoKeyTyped(evt);
            }
        });
        jScrollPane3.setViewportView(tableNotaDebito);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        org.jdesktop.swingx.border.DropShadowBorder dropShadowBorder2 = new org.jdesktop.swingx.border.DropShadowBorder();
        dropShadowBorder2.setShowLeftShadow(true);
        dropShadowBorder2.setShowTopShadow(true);
        jPanel4.setBorder(dropShadowBorder2);

        txtBuscar.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(65, 94, 255)));
        txtBuscar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBuscar.setPrompt("BUSCAR POR PROVEEDOR, N┬░ FACTURA, N┬░ RUC PROVEEDOR");
        txtBuscar.setPromptBackround(null);
        txtBuscar.setPromptFontStyle(new java.lang.Integer(4));
        txtBuscar.setPromptForeground(new java.awt.Color(65, 94, 255));
        txtBuscar.setSelectionEnd(1);
        txtBuscar.setSelectionStart(2);
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(65, 94, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/Nuevo.png"))); // NOI18N
        btnAgregar.setText("CREAR");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(65, 94, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/SistemaLara/capa5_imagenes/CancelarNuevo.png"))); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.setColorHover(new java.awt.Color(253, 173, 1));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCancelar.setIconTextGap(0);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

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


    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        inabilitarBotones(true);
    }//GEN-LAST:event_btnCancelarActionPerformed
    int fila;
    private void tableNotaDebitoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableNotaDebitoMousePressed
        fila = tableNotaDebito.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableNotaDebitoMousePressed

    private void tableNotaDebitoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNotaDebitoKeyReleased
        fila = tableNotaDebito.getSelectedRow();
        inabilitarBotones(false);
    }//GEN-LAST:event_tableNotaDebitoKeyReleased

    private void tableNotaDebitoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableNotaDebitoKeyTyped
//        try {
//            if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
//                if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//
//                    obtenerFactura();
//                }
//            }
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }


    }//GEN-LAST:event_tableNotaDebitoKeyTyped

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            FormRegistroNotaDeDebito datosTipoPersonal = new FormRegistroNotaDeDebito(null, true);
            datosTipoPersonal.setVisible(true);
            inicializarTabla();
            notaDebitoSeleccionado = null;
            inabilitarBotones(true);

        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        try {
            txtBuscar.setText(txtBuscar.getText().toUpperCase());
            if (!txtBuscar.getText().equals("")) {
                limpiarTabla();
                gestionarNotaDebitoServicio.mostrarNotaDebitos(1, tableNotaDebito);
            } else {
                limpiarTabla();
                gestionarNotaDebitoServicio.mostrarNotaDebitos(1, tableNotaDebito, txtBuscar.getText());
            }

        } catch (Exception ex) {
            Mensaje.mostrarErrorDeConsulta(this);
        }

    }//GEN-LAST:event_txtBuscarKeyPressed

    private void rSButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_rSButton2ActionPerformed

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        Verificador.MousePressed(evt);
    }//GEN-LAST:event_formMousePressed

    private void formMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseDragged
        Verificador.MouseDragged(evt, this);
    }//GEN-LAST:event_formMouseDragged

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        try {
            obtenerFacturaSeleccionado();
            FormRegistroNotaDeDebito factura = new FormRegistroNotaDeDebito(null, true, notaDebitoSeleccionado);
            factura.setVisible(true);
            inicializarTabla();
            notaDebitoSeleccionado = null;
            inabilitarBotones(true);
        } catch (Exception e) {
            Mensaje.mostrarFilaNoSeleccionada(this);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            obtenerFacturaSeleccionado();
            if (notaDebitoSeleccionado != null) {
                if (!Mensaje.mostrarPreguntaDeEliminacion(this)) {
                    return;
                }

                try {
                    int registros_afectados = gestionarNotaDebitoServicio.eliminarNotaDebitoElectronica(notaDebitoSeleccionado);
                    if (registros_afectados == 1) {
                        Mensaje.mostrarAfirmacionDeEliminacion(this);
                        inicializarTabla();
                        inabilitarBotones(true);
                    } else {
                        Mensaje.mostrarAdvertenciaDeEliminacion(this);
                    }

                } catch (Exception e) {
                    Mensaje.mostrarErrorDeEliminacion(this);
                }
            } else {
                Mensaje.mostrarErrorSistema(this);
            }
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        try {
            txtBuscar.setText(txtBuscar.getText().toUpperCase());
            String texto = txtBuscar.getText().toString();
            if (texto.equals("")) {
                inicializarTabla();
            } else {
                limpiarTabla();
                gestionarNotaDebitoServicio.mostrarNotaDebitos(1, tableNotaDebito, texto);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void obtenerFacturaSeleccionado() {
        try {
            String codigo = tableNotaDebito.getValueAt(fila, 0).toString();
            notaDebitoSeleccionado = gestionarNotaDebitoServicio.buscarNotaDebitoPorCodigo(Integer.parseInt(codigo));
        } catch (Exception e) {
            Mensaje.mostrarErrorSistema(this);
        }

    }

    private void inabilitarBotones(Boolean v) {

        btnAgregar.setEnabled(v);
//        btnEliminar.setEnabled(!v);
        btnModificar.setEnabled(!v);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.RSButton btnAgregar;
    private rojeru_san.RSButton btnCancelar;
    private rojeru_san.RSButton btnEliminar;
    private rojeru_san.RSButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel pnlMenu;
    private javax.swing.JPopupMenu popMenu;
    private rojeru_san.RSButton rSButton2;
    private rojerusan.RSTableMetro tableNotaDebito;
    private org.jdesktop.swingx.JXSearchField txtBuscar;
    // End of variables declaration//GEN-END:variables

//    private void obtenerFactura() {
//        String codigo = tableFactura.getValueAt(fila, 0).toString();
//
//        try {
//            facturaSeleccionado = gestionarFacturaServicio.buscarFacturaPorCodigo(Integer.parseInt(codigo));
//            if (TIPO_USUARIO == TIPO_LIQUIDACION) {
//                FormGestionarLiquidacion.facturaSeleccionado = facturaSeleccionado;
//                this.dispose();
//            } else {
//                inabilitarBotones(false);
//            }
//
//        } catch (Exception e) {
//            Mensaje.mostrarErrorSistema(this);
//        }
//
//    }
}
