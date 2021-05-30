/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistemaLara.capa4_persistencia;

import SistemaLara.capa1_presentacion.FormGestionarFactura;
import SistemaLara.capa3_dominio.ClienteEntrante;
import SistemaLara.capa3_dominio.EmpresaRutaImagen;
import SistemaLara.capa3_dominio.Factura;
import SistemaLara.capa3_dominio.IniciarSesion;
import SistemaLara.capa3_dominio.ProveedorServicio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JComponent.getDefaultLocale;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author XGamer
 */
public class AdelantoReporteDAOMySQL {

    GestorJDBC gestorJDBC;

    public AdelantoReporteDAOMySQL(GestorJDBC gestorJDBC) {
        this.gestorJDBC = gestorJDBC;

    }

    public JasperPrint reporteAdelantoCliente(ClienteEntrante clienteEntrante, JLabel lblsoles, JLabel lblDolares) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();
        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptAdelantoPorCliente.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            //  Map<String, String> parametros = new HashMap<String, String>();

            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", clienteEntrante.getCodigo());
            parametros.put("totalNoPagadoSoles", lblsoles.getText());
            parametros.put("totalNoPagadoDolares", lblDolares.getText());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreruta = "ADELANTO - CLIENTE " + clienteEntrante.getApellidos() + " - CODIGO " + clienteEntrante.getCodigo();
            String url = IniciarSesion.getUsuario().getRutaReporte().getAdelantoCliente() + nombreruta + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Adelanto cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

    public JasperPrint reporteAdelantoValorizacionDetalle(Integer codigo, JLabel lblsoles, JLabel lblDolares) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();
        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptAdelantoPorClienteValorizacion.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            //  Map<String, String> parametros = new HashMap<String, String>();

            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerRuta()));
            parametros.put("codigo", codigo);
            parametros.put("totalNoPagadoSoles", lblsoles.getText());
            parametros.put("totalNoPagadoDolares", lblDolares.getText());
            parametros.put("estado", "PAGADO");

            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String nombreruta = "ADELANTO - CLIENTE " + codigo + " - CODIGO " + codigo;
            String url = IniciarSesion.getUsuario().getRutaReporte().getAdelantoCliente() + nombreruta + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Adelanto cliente.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

    public JasperPrint reporteAdelantoProveedor(ProveedorServicio proveedorServicio, JLabel lblsoles, JLabel lblDolares) throws SQLException {
        JasperPrint jprint = null;
        Map parametros = new HashMap();

        String master = System.getProperty("user.dir") + "/src/SistemaLara/capa7_reportes/jasper/rptAdelantoPorProveedor.jasper";
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(master);
            //  Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("logo", this.getClass().getResourceAsStream(EmpresaRutaImagen.obtenerGeneral()));
            parametros.put("codigo", proveedorServicio.getCodigo());
            parametros.put("totalNoPagadoSoles", lblsoles.getText());
            parametros.put("totalNoPagadoDolares", lblDolares.getText());
            jprint = JasperFillManager.fillReport(reporte, parametros, gestorJDBC.getConnection());
            //JasperViewer.viewReport(jprint, false); 
            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jprint);
            String rutaUrl = "ADELANTO PROVEEDOR - " + proveedorServicio.getRazonSocial() + " - CODIGO - " + proveedorServicio.getCodigo();
            String url = IniciarSesion.getUsuario().getRutaReporte().getAdelantoProveedor() + rutaUrl + ".pdf";
            //   JOptionPane.showMessageDialog(null,url );
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(url));
            exporter.exportReport();
            ProcessBuilder p = new ProcessBuilder();
            p.command("cmd.exe", "/c", url);
            p.start();
        } catch (Exception ex) {
            throw new SQLException("No se ha podido Mostrar el Reporte de Adelanto Por Proveedor.\n"
                    + "Intente de nuevo o consulte con el Administrador.");

        }
        return jprint;
    }

}
