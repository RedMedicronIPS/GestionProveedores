package clases.FacturacionElectronica;

import clasesDataBase.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacturacionElectronica {

    private String id;
    private String deptoCentroOperaciones;
    private String etapa;
    private String fechaFactura;
    private String estadoFactura;
    private String numFacturaAutorizada;
    private String concepto;
    private String razonSocialProveedor;
    private String razonSocialAdquiriente;
    private double valorFactura;
    private String causalDevolucionAnulacion;
    private String causalDevolucionContabilidad;
    private String centroCosto;
    private String causalDevolucionRevision;
    private String causalDevolucionImpuestos;
    private Boolean estado;

    public FacturacionElectronica() {
    }

    public FacturacionElectronica(String id) {
        String sql = "SELECT * FROM dbo.facturacionElectronica WHERE id = ?";
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = id;
                this.deptoCentroOperaciones = rs.getString("deptoCentroOperaciones");
                this.etapa = rs.getString("etapa");
                this.fechaFactura = rs.getString("fechaFactura");
                this.estadoFactura = rs.getString("estadoFactura");
                this.numFacturaAutorizada = rs.getString("numFacturaAutorizada");
                this.concepto = rs.getString("concepto");
                this.razonSocialProveedor = rs.getString("razonSocialProveedor");
                this.razonSocialAdquiriente = rs.getString("razonSocialAdquiriente");
                this.valorFactura = rs.getDouble("valorFactura");
                this.causalDevolucionAnulacion = rs.getString("causalDevolucionAnulacion");
                this.causalDevolucionContabilidad = rs.getString("causalDevolucionContabilidad");
                this.centroCosto = rs.getString("centroCosto");
                this.causalDevolucionRevision = rs.getString("causalDevolucionRevision");
                this.causalDevolucionImpuestos = rs.getString("causalDevolucionImpuestos");
                this.estado = rs.getBoolean("estado");
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptoCentroOperaciones() {
        return deptoCentroOperaciones != null ? deptoCentroOperaciones : "";
    }

    public void setDeptoCentroOperaciones(String deptoCentroOperaciones) {
        this.deptoCentroOperaciones = deptoCentroOperaciones;
    }

    public String getEtapa() {
        return etapa != null ? etapa : "";
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public String getFechaFactura() {
        return fechaFactura != null ? fechaFactura : "";
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getEstadoFactura() {
        return estadoFactura != null ? estadoFactura : "";
    }

    public void setEstadoFactura(String estadoFactura) {
        this.estadoFactura = estadoFactura;
    }

    public String getNumFacturaAutorizada() {
        return numFacturaAutorizada != null ? numFacturaAutorizada : "";
    }

    public void setNumFacturaAutorizada(String numFacturaAutorizada) {
        this.numFacturaAutorizada = numFacturaAutorizada;
    }

    public String getConcepto() {
        return concepto != null ? concepto : "";
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getRazonSocialProveedor() {
        return razonSocialProveedor != null ? razonSocialProveedor : "";
    }

    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    public String getRazonSocialAdquiriente() {
        return razonSocialAdquiriente != null ? razonSocialAdquiriente : "";
    }

    public void setRazonSocialAdquiriente(String razonSocialAdquiriente) {
        this.razonSocialAdquiriente = razonSocialAdquiriente;
    }

    public double getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(double valorFactura) {
        this.valorFactura = valorFactura;
    }

    public String getCausalDevolucionAnulacion() {
        return causalDevolucionAnulacion != null ? causalDevolucionAnulacion : "";
    }

    public void setCausalDevolucionAnulacion(String causalDevolucionAnulacion) {
        this.causalDevolucionAnulacion = causalDevolucionAnulacion;
    }

    public String getCausalDevolucionContabilidad() {
        return causalDevolucionContabilidad != null ? causalDevolucionContabilidad : "";
    }

    public void setCausalDevolucionContabilidad(String causalDevolucionContabilidad) {
        this.causalDevolucionContabilidad = causalDevolucionContabilidad;
    }

    public String getCentroCosto() {
        return centroCosto != null ? centroCosto : "";
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getCausalDevolucionRevision() {
        return causalDevolucionRevision != null ? causalDevolucionRevision : "";
    }

    public void setCausalDevolucionRevision(String causalDevolucionRevision) {
        this.causalDevolucionRevision = causalDevolucionRevision;
    }

    public String getCausalDevolucionImpuestos() {
        return causalDevolucionImpuestos != null ? causalDevolucionImpuestos : "";
    }

    public void setCausalDevolucionImpuestos(String causalDevolucionImpuestos) {
        this.causalDevolucionImpuestos = causalDevolucionImpuestos;
    }

    public Boolean getEstado() {
        return estado != null ? estado : true;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado != null ? estado : true;
    }

    public static List<FacturacionElectronica> listByIds(List<String> ids) {
        List<FacturacionElectronica> lista = new ArrayList<>();
        if (ids == null || ids.isEmpty()) return lista;

        String idsString = String.join(",", ids); // ej: "1,2,3"

        String sql = "SELECT * FROM facturacionElectronica WHERE id IN (" + idsString + ")";

        try (Connection con = ConectorBD.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                FacturacionElectronica fe = new FacturacionElectronica();             
                fe.setId(rs.getString("id"));
                fe.setDeptoCentroOperaciones(rs.getString("deptoCentroOperaciones"));
                fe.setEtapa(rs.getString("etapa"));
                fe.setFechaFactura(rs.getString("fechaFactura"));
                fe.setEstadoFactura(rs.getString("estadoFactura"));
                fe.setNumFacturaAutorizada(rs.getString("numFacturaAutorizada"));
                fe.setConcepto(rs.getString("concepto"));
                fe.setRazonSocialProveedor(rs.getString("razonSocialProveedor"));
                fe.setRazonSocialAdquiriente(rs.getString("razonSocialAdquiriente"));
           
                lista.add(fe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    public boolean create() {
        String sql = "INSERT INTO dbo.facturacionElectronica ("
                + "deptoCentroOperaciones, etapa, fechaFactura, estadoFactura, numFacturaAutorizada, "
                + "concepto, razonSocialProveedor, razonSocialAdquiriente, valorFactura, "
                + "causalDevolucionAnulacion, causalDevolucionContabilidad, centroCosto, "
                + "causalDevolucionRevision, causalDevolucionImpuestos, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, deptoCentroOperaciones);
            stmt.setString(2, etapa);
            stmt.setString(3, fechaFactura);
            stmt.setString(4, estadoFactura);
            stmt.setString(5, numFacturaAutorizada);
            stmt.setString(6, concepto);
            stmt.setString(7, razonSocialProveedor);
            stmt.setString(8, razonSocialAdquiriente);
            stmt.setDouble(9, valorFactura);
            stmt.setString(10, causalDevolucionAnulacion);
            stmt.setString(11, causalDevolucionContabilidad);
            stmt.setString(12, centroCosto);
            stmt.setString(13, causalDevolucionRevision);
            stmt.setString(14, causalDevolucionImpuestos);
            stmt.setBoolean(15, estado != null ? estado : true);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return false;
    }

    public boolean update() {
        String sql = "UPDATE dbo.facturacionElectronica SET "
                + "deptoCentroOperaciones=?, "
                + "etapa=?, "
                + "fechaFactura=?, "
                + "estadoFactura=?, "
                + "numFacturaAutorizada=?, "
                + "concepto=?, "
                + "razonSocialProveedor=?, "
                + "razonSocialAdquiriente=?, "
                + "valorFactura=?, "
                + "causalDevolucionAnulacion=?, "
                + "causalDevolucionContabilidad=?, "
                + "centroCosto=?, "
                + "causalDevolucionRevision=?, "
                + "causalDevolucionImpuestos=?, "
                + "estado=? "
                + "WHERE id=?";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, this.deptoCentroOperaciones);
            stmt.setString(2, this.etapa);
            stmt.setString(3, this.fechaFactura);
            stmt.setString(4, this.estadoFactura);
            stmt.setString(5, this.numFacturaAutorizada);
            stmt.setString(6, this.concepto);
            stmt.setString(7, this.razonSocialProveedor);
            stmt.setString(8, this.razonSocialAdquiriente);
            stmt.setDouble(9, this.valorFactura);
            stmt.setString(10, this.causalDevolucionAnulacion);
            stmt.setString(11, this.causalDevolucionContabilidad);
            stmt.setString(12, this.centroCosto);
            stmt.setString(13, this.causalDevolucionRevision);
            stmt.setString(14, this.causalDevolucionImpuestos);
            stmt.setBoolean(15, this.estado != null ? this.estado : true);
            stmt.setString(16, this.id);

            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.INFO,
                    "Ejecutando UPDATE: " + stmt.toString());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.SEVERE,
                    "Error al actualizar factura electrónica: " + sql, e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean desactivar() {
        String sql = "UPDATE dbo.facturacionElectronica SET estado = 0 WHERE id = ?";
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.SEVERE,
                    "Error al desactivar factura electrónica ID: " + id, e);
            return false;
        }
    }

    public static List<FacturacionElectronica> listInObjects(String filtro, String orden) {
        List<FacturacionElectronica> list = new ArrayList<>();
        String whereClause = "estado = 1"; 
        if (filtro != null && !filtro.trim().isEmpty()) {
            whereClause += " AND " + filtro;
        }
        String sql = "SELECT * FROM dbo.facturacionElectronica WHERE " + whereClause
                + (orden != null && !orden.trim().isEmpty() ? " ORDER BY " + orden : "");
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FacturacionElectronica fe = new FacturacionElectronica();
                fe.id = rs.getString("id");
                fe.deptoCentroOperaciones = rs.getString("deptoCentroOperaciones");
                fe.etapa = rs.getString("etapa");
                fe.fechaFactura = rs.getString("fechaFactura");
                fe.estadoFactura = rs.getString("estadoFactura");
                fe.numFacturaAutorizada = rs.getString("numFacturaAutorizada");
                fe.concepto = rs.getString("concepto");
                fe.razonSocialProveedor = rs.getString("razonSocialProveedor");
                fe.razonSocialAdquiriente = rs.getString("razonSocialAdquiriente");
                fe.valorFactura = rs.getDouble("valorFactura");
                fe.causalDevolucionAnulacion = rs.getString("causalDevolucionAnulacion");
                fe.causalDevolucionContabilidad = rs.getString("causalDevolucionContabilidad");
                fe.centroCosto = rs.getString("centroCosto");
                fe.causalDevolucionRevision = rs.getString("causalDevolucionRevision");
                fe.causalDevolucionImpuestos = rs.getString("causalDevolucionImpuestos");
                fe.estado = rs.getBoolean("estado");
                list.add(fe);
            }
        } catch (SQLException e) {
            Logger.getLogger(FacturacionElectronica.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return list;
    }

    @Override
    public String toString() {
        return deptoCentroOperaciones + " - " + etapa;
    }
}
