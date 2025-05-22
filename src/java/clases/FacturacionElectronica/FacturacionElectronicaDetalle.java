/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.FacturacionElectronica;

import clasesDataBase.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author IPS OBRERO
 */
public class FacturacionElectronicaDetalle {

    private String id;
    private String descripcionFactura;
    private String observacionesGestion;
    private String observacionesInconsistencias;
    private String observacionesConformidad;
    private String observacionesPago;
    private String observacionesContabilidad;
    private String observacionesRevision;
    private String observacionesImpuestos;
    private String observacionesContraloria;
    private String idFacturaElectronica;
    private Boolean estado;

    public FacturacionElectronicaDetalle() {
    }

    public FacturacionElectronicaDetalle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionFactura() {
      return descripcionFactura != null ? descripcionFactura : "";
    }

    public void setDescripcionFactura(String descripcionFactura) {
        this.descripcionFactura = descripcionFactura;
    }

    public String getObservacionesGestion() {
         return observacionesGestion != null ? observacionesGestion : "";
    }

    public void setObservacionesGestion(String observacionesGestion) {
        this.observacionesGestion = observacionesGestion;
    }

    public String getObservacionesInconsistencias() {
        return observacionesInconsistencias != null ? observacionesInconsistencias : "";
    }

    public void setObservacionesInconsistencias(String observacionesInconsistencias) {
        this.observacionesInconsistencias = observacionesInconsistencias;
    }

    public String getObservacionesConformidad() {
        return observacionesConformidad != null ? observacionesConformidad : "";
    }

    public void setObservacionesConformidad(String observacionesConformidad) {
        this.observacionesConformidad = observacionesConformidad;
    }

    public String getObservacionesPago() {
        return observacionesPago != null ? observacionesPago : "";
    }

    public void setObservacionesPago(String observacionesPago) {
        this.observacionesPago = observacionesPago;
    }

    public String getObservacionesContabilidad() {
   return observacionesContabilidad != null ? observacionesContabilidad : "";
    }

    public void setObservacionesContabilidad(String observacionesContabilidad) {
        this.observacionesContabilidad = observacionesContabilidad;
    }

    public String getObservacionesRevision() {
       return observacionesRevision != null ? observacionesRevision : "";
    }

    public void setObservacionesRevision(String observacionesRevision) {
        this.observacionesRevision = observacionesRevision;
    }

    public String getObservacionesImpuestos() {
      return observacionesImpuestos != null ? observacionesImpuestos : "";
    }

    public void setObservacionesImpuestos(String observacionesImpuestos) {
        this.observacionesImpuestos = observacionesImpuestos;
    }

    public String getObservacionesContraloria() {
       return observacionesContraloria != null ? observacionesContraloria : "";
    }

    public void setObservacionesContraloria(String observacionesContraloria) {
        this.observacionesContraloria = observacionesContraloria;
    }

    public String getIdFacturaElectronica() {
        return idFacturaElectronica;
    }

    public void setIdFacturaElectronica(String idFacturaElectronica) {
        this.idFacturaElectronica = idFacturaElectronica;
    }

    public Boolean getEstado() {
        return estado != null ? estado : true;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado != null ? estado : true;
    }

    @Override
    public String toString(){
        return descripcionFactura;
    }
    public boolean create() {
        String sql = "INSERT INTO dbo.facturacionElectronicaDetalle ("
                + "descripcionFactura, observacionesGestion, observacionesInconsistencias, "
                + "observacionesConformidad, observacionesPago, observacionesContabilidad, "
                + "observacionesRevision, observacionesImpuestos, observacionesContraloria, "
                + "idFacturaElectronica, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, descripcionFactura);
            stmt.setString(2, observacionesGestion);
            stmt.setString(3, observacionesInconsistencias);
            stmt.setString(4, observacionesConformidad);
            stmt.setString(5, observacionesPago);
            stmt.setString(6, observacionesContabilidad);
            stmt.setString(7, observacionesRevision);
            stmt.setString(8, observacionesImpuestos);
            stmt.setString(9, observacionesContraloria);
            stmt.setString(10, idFacturaElectronica);
            stmt.setBoolean(11, estado != null ? estado : true);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return false;
    }

    public boolean update() {
        String sql = "UPDATE dbo.facturacionElectronicaDetalle SET "
                + "descripcionFactura=?, "
                + "observacionesGestion=?, "
                + "observacionesInconsistencias=?, "
                + "observacionesConformidad=?, "
                + "observacionesPago=?, "
                + "observacionesContabilidad=?, "
                + "observacionesRevision=?, "
                + "observacionesImpuestos=?, "
                + "observacionesContraloria=?, "
                + "idFacturaElectronica=?, "
                + "estado=? "
                + "WHERE id=?";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, descripcionFactura);
            stmt.setString(2, observacionesGestion);
            stmt.setString(3, observacionesInconsistencias);
            stmt.setString(4, observacionesConformidad);
            stmt.setString(5, observacionesPago);
            stmt.setString(6, observacionesContabilidad);
            stmt.setString(7, observacionesRevision);
            stmt.setString(8, observacionesImpuestos);
            stmt.setString(9, observacionesContraloria);
            stmt.setString(10, idFacturaElectronica);
            stmt.setBoolean(11, estado != null ? estado : true);
            stmt.setString(12, id);

            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.INFO,
                    "Ejecutando UPDATE: " + stmt.toString());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.SEVERE,
                    "Error al actualizar detalle de factura electrónica: " + sql, e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean desactivar() {
        String sql = "UPDATE dbo.facturacionElectronicaDetalle SET estado = 0 WHERE id = ?";
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.SEVERE,
                    "Error al desactivar detalle de factura electrónica ID: " + id, e);
            return false;
        }
    }

    public static List<FacturacionElectronicaDetalle> listInObjects(String filtro, String orden) {
        List<FacturacionElectronicaDetalle> list = new ArrayList<>();
        String whereClause = "estado = 1"; // Filtro base para mostrar solo activos
        if (filtro != null && !filtro.trim().isEmpty()) {
            whereClause += " AND " + filtro;
        }
        String sql = "SELECT * FROM dbo.facturacionElectronicaDetalle WHERE " + whereClause
                + (orden != null && !orden.trim().isEmpty() ? " ORDER BY " + orden : "");
        
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FacturacionElectronicaDetalle detalle = new FacturacionElectronicaDetalle();
                detalle.id = rs.getString("id");
                detalle.descripcionFactura = rs.getString("descripcionFactura");
                detalle.observacionesGestion = rs.getString("observacionesGestion");
                detalle.observacionesInconsistencias = rs.getString("observacionesInconsistencias");
                detalle.observacionesConformidad = rs.getString("observacionesConformidad");
                detalle.observacionesPago = rs.getString("observacionesPago");
                detalle.observacionesContabilidad = rs.getString("observacionesContabilidad");
                detalle.observacionesRevision = rs.getString("observacionesRevision");
                detalle.observacionesImpuestos = rs.getString("observacionesImpuestos");
                detalle.observacionesContraloria = rs.getString("observacionesContraloria");
                detalle.idFacturaElectronica = rs.getString("idFacturaElectronica");
                detalle.estado = rs.getBoolean("estado");
                list.add(detalle);
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return list;
    }

    
    public static List<FacturacionElectronicaDetalle> listByFacturaElectronica(String idFactura) {
        List<FacturacionElectronicaDetalle> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.facturacionElectronicaDetalle WHERE idFacturaElectronica = ? AND estado = 1";
        
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, idFactura);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FacturacionElectronicaDetalle detalle = new FacturacionElectronicaDetalle();
                detalle.id = rs.getString("id");
                detalle.descripcionFactura = rs.getString("descripcionFactura");
                detalle.observacionesGestion = rs.getString("observacionesGestion");
                detalle.observacionesInconsistencias = rs.getString("observacionesInconsistencias");
                detalle.observacionesConformidad = rs.getString("observacionesConformidad");
                detalle.observacionesPago = rs.getString("observacionesPago");
                detalle.observacionesContabilidad = rs.getString("observacionesContabilidad");
                detalle.observacionesRevision = rs.getString("observacionesRevision");
                detalle.observacionesImpuestos = rs.getString("observacionesImpuestos");
                detalle.observacionesContraloria = rs.getString("observacionesContraloria");
                detalle.idFacturaElectronica = rs.getString("idFacturaElectronica");
                detalle.estado = rs.getBoolean("estado");
                list.add(detalle);
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronicaDetalle.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return list;
    }
}
