package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralTercero {

    private String id;
    private String tercero_codigo;
    private String tercero_id_tipo_identificacion;
    private String tercero_razon_nombres;
    private String tercero_fecha_nacimiento;
    private String tercero_direccion;
    private String tercero_telefono;
    private String tercero_correo;
    private String tercero_pais;
    private String tercero_departamento;
    private String tercero_ciudad;
    private String tercero_ciiu;
    private Boolean tercero_facturar;
    private Boolean tercero_empleado;
    private Boolean tercero_proveedor;
    private Boolean tercero_accionista_asociado;

    private String tercero_tipo;
    private String tercero_estado;
 public GeneralTercero() {
        // Inicializar como Boolean, no como String
        this.tercero_facturar = false;
        this.tercero_empleado = false;
        this.tercero_proveedor = false;
        this.tercero_accionista_asociado = false;

    }
    public GeneralTercero(String id) {
        String sql = "SELECT * FROM dbo.generalTercero WHERE id = ?";
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = id;
                this.tercero_codigo = rs.getString("tercero_codigo");
                this.tercero_id_tipo_identificacion = rs.getString("tercero_id_tipo_identificacion");
                this.tercero_razon_nombres = rs.getString("tercero_razon_nombres");
                this.tercero_fecha_nacimiento = rs.getString("tercero_fecha_nacimiento");
                this.tercero_direccion = rs.getString("tercero_direccion");
                this.tercero_telefono = rs.getString("tercero_telefono");
                this.tercero_correo = rs.getString("tercero_correo");
                this.tercero_pais = rs.getString("tercero_pais");
                this.tercero_departamento = rs.getString("tercero_departamento");
                this.tercero_ciudad = rs.getString("tercero_ciudad");
                this.tercero_ciiu = rs.getString("tercero_ciiu");
                this.tercero_facturar = rs.getString("tercero_facturar");
                this.tercero_empleado = rs.getString("tercero_empleado");
                this.tercero_proveedor = rs.getString("tercero_proveedor");
                this.tercero_accionista_asociado = rs.getString("tercero_accionista_asociado");
                this.tercero_tipo = rs.getString("tercero_tipo");
                this.tercero_estado = rs.getString("tercero_estado");
            }
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public String isCheckedTT(String tipo) {
        return this.tercero_tipo != null && this.tercero_tipo.equals(tipo) ? "checked" : "";
    }


    public String getId() {
        return id != null ? id : "";
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTercero_codigo() {
        return tercero_codigo != null ? tercero_codigo : "";
    }

    public void setTercero_codigo(String tercero_codigo) {
        this.tercero_codigo = tercero_codigo;
    }

    public String getTercero_id_tipo_identificacion() {
        return tercero_id_tipo_identificacion != null ? tercero_id_tipo_identificacion : "";
    }

    public void setTercero_id_tipo_identificacion(String tercero_id_tipo_identificacion) {
        this.tercero_id_tipo_identificacion = tercero_id_tipo_identificacion;
    }

    public String getTercero_razon_nombres() {
        return tercero_razon_nombres != null ? tercero_razon_nombres : "";
    }

    public void setTercero_razon_nombres(String tercero_razon_nombres) {
        this.tercero_razon_nombres = tercero_razon_nombres;
    }

    public String getTercero_fecha_nacimiento() {
        return tercero_fecha_nacimiento != null ? tercero_fecha_nacimiento : "";
    }

    public void setTercero_fecha_nacimiento(String tercero_fecha_nacimiento) {
        this.tercero_fecha_nacimiento = tercero_fecha_nacimiento;
    }

    public String getTercero_direccion() {
        return tercero_direccion != null ? tercero_direccion : "";
    }

    public void setTercero_direccion(String tercero_direccion) {
        this.tercero_direccion = tercero_direccion;
    }

    public String getTercero_telefono() {
        return tercero_telefono != null ? tercero_telefono : "";
    }

    public void setTercero_telefono(String tercero_telefono) {
        this.tercero_telefono = tercero_telefono;
    }

    public String getTercero_correo() {
        return tercero_correo != null ? tercero_correo : "";
    }

    public void setTercero_correo(String tercero_correo) {
        this.tercero_correo = tercero_correo;
    }

    public String getTercero_pais() {
        return tercero_pais != null ? tercero_pais : "";
    }

    public void setTercero_pais(String tercero_pais) {
        this.tercero_pais = tercero_pais;
    }

    public String getTercero_departamento() {
        return tercero_departamento != null ? tercero_departamento : "";
    }

    public void setTercero_departamento(String tercero_departamento) {
        this.tercero_departamento = tercero_departamento;
    }

    public String getTercero_ciudad() {
        return tercero_ciudad != null ? tercero_ciudad : "";
    }

    public void setTercero_ciudad(String tercero_ciudad) {
        this.tercero_ciudad = tercero_ciudad;
    }

    public String getTercero_ciiu() {
        return tercero_ciiu != null ? tercero_ciiu : "";
    }

    public void setTercero_ciiu(String tercero_ciiu) {
        this.tercero_ciiu = tercero_ciiu;
    }

    public String getTercero_tipo() {
        return tercero_tipo != null ? tercero_tipo : "";
    }

    public void setTercero_tipo(String tercero_tipo) {
        this.tercero_tipo = tercero_tipo;
    }    

    public String getTerceroEmpleado() {
        return Boolean.TRUE.equals(this.tercero_empleado) ? "Sí" : "No";
    }

    public void setTercero_empleado(String tercero_empleado) {
        this.tercero_empleado = "Sí".equals(tercero_empleado) || "true".equalsIgnoreCase(tercero_empleado);
    }

    public String isCheckedEmpleado(String valor) {
        if (this.tercero_empleado == null) {
            return "";
        }
        boolean isSi = "Sí".equals(valor);
        return (isSi && this.tercero_empleado) || (!isSi && !this.tercero_empleado) ? "checked" : "";
    }

    public String getTerceroProveedor() {
        return Boolean.TRUE.equals(this.tercero_proveedor) ? "Sí" : "No";
    }

    public String getTerceroAccionistaAsociado() {
        return Boolean.TRUE.equals(this.tercero_accionista_asociado) ? "Sí" : "No";

    }
// En la clase GeneralTercero, modifica los setters para los campos booleanos:

    public String getTerceroFacturar() {
        return Boolean.TRUE.equals(this.tercero_facturar) ? "Sí" : "No";
    }

    public void setTercero_proveedor(String tercero_proveedor) {
        this.tercero_proveedor = "Sí".equals(tercero_proveedor) || "true".equalsIgnoreCase(tercero_proveedor);
    }

    public void setTercero_accionista_asociado(String tercero_accionista_asociado) {
        this.tercero_accionista_asociado = "Sí".equals(tercero_accionista_asociado) || "true".equalsIgnoreCase(tercero_accionista_asociado);
    }

    public void setTercero_facturar(String tercero_facturar) {
        this.tercero_facturar = "Sí".equals(tercero_facturar) || "true".equalsIgnoreCase(tercero_facturar);
    }

    public String isCheckedProveedor(String valor) {
        if (this.tercero_proveedor == null) {
            return "";
        }
        boolean isSi = "Sí".equals(valor);
        return (isSi && this.tercero_proveedor) || (!isSi && !this.tercero_proveedor) ? "checked" : "";
    }

    public String isCheckedAccionista(String valor) {
        if (this.tercero_accionista_asociado == null) {
            return "";
        }
        boolean isSi = "Sí".equals(valor);
        return (isSi && this.tercero_accionista_asociado) || (!isSi && !this.tercero_accionista_asociado) ? "checked" : "";
    }

    public String isCheckedFacturar(String valor) {
        if (this.tercero_facturar == null) {
            return "";
        }
        boolean isSi = "Sí".equals(valor);
        return (isSi && this.tercero_facturar) || (!isSi && !this.tercero_facturar) ? "checked" : "";
    }

    public void setTercero_estado(String tercero_estado) {
        this.tercero_estado = (tercero_estado == null || tercero_estado.trim().isEmpty()) ? "Activo" : tercero_estado;
    }

    public String getTercero_estado() {
        return tercero_estado != null ? tercero_estado : "";
    }


    @Override
    public String toString() {
        return "GeneralTercero{"
                + "id='" + id + '\''
                + ", empleado='" + tercero_empleado + '\''
                + ", proveedor='" + tercero_proveedor + '\''
                + ", accionista='" + tercero_accionista_asociado + '\''
                + ", facturar='" + tercero_facturar + '\''
                + '}';
    }

    public boolean create() {
        String sql = "INSERT INTO dbo.generalTercero (tercero_codigo, tercero_id_tipo_identificacion, tercero_razon_nombres, "
                + "tercero_fecha_nacimiento, tercero_direccion, tercero_telefono, tercero_correo, tercero_pais, "
                + "tercero_departamento, tercero_ciudad, tercero_ciiu, tercero_facturar, tercero_empleado, tercero_proveedor, "
                + "tercero_accionista_asociado, tercero_tipo, tercero_estado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, tercero_codigo);
            stmt.setString(2, tercero_id_tipo_identificacion);
            stmt.setString(3, tercero_razon_nombres);
            stmt.setString(4, tercero_fecha_nacimiento);
            stmt.setString(5, tercero_direccion);
            stmt.setString(6, tercero_telefono);
            stmt.setString(7, tercero_correo);
            stmt.setString(8, tercero_pais);
            stmt.setString(9, tercero_departamento);
            stmt.setString(10, tercero_ciudad);
            stmt.setString(11, tercero_ciiu);
            stmt.setString(12, tercero_facturar);
            stmt.setString(13, tercero_empleado);
            stmt.setString(14, tercero_proveedor);
            stmt.setString(15, tercero_accionista_asociado);
            stmt.setString(16, tercero_tipo);
            stmt.setString(17, tercero_estado);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return false;
    }

    public boolean update() {
        String sql = "UPDATE dbo.generalTercero SET "
                + "tercero_codigo=?, "
                + "tercero_id_tipo_identificacion=?, "
                + "tercero_razon_nombres=?, "
                + "tercero_fecha_nacimiento=?, "
                + "tercero_direccion=?, "
                + "tercero_telefono=?, "
                + "tercero_correo=?, "
                + "tercero_pais=?, "
                + "tercero_departamento=?, "
                + "tercero_ciudad=?, "
                + "tercero_ciiu=?, "
                + "tercero_facturar=?, "
                + "tercero_empleado=?, "
                + "tercero_proveedor=?, "
                + "tercero_accionista_asociado=?, "
                + "tercero_tipo=?, "
                + "tercero_estado=? "
                + "WHERE id=?";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {          
            stmt.setString(1, this.tercero_codigo);
            stmt.setString(2, this.tercero_id_tipo_identificacion);
            stmt.setString(3, this.tercero_razon_nombres);
            stmt.setString(4, this.tercero_fecha_nacimiento);
            stmt.setString(5, this.tercero_direccion);
            stmt.setString(6, this.tercero_telefono);
            stmt.setString(7, this.tercero_correo);
            stmt.setString(8, this.tercero_pais);
            stmt.setString(9, this.tercero_departamento);
            stmt.setString(10, this.tercero_ciudad);
            stmt.setString(11, this.tercero_ciiu);
            stmt.setBoolean(12, this.tercero_facturar);
            stmt.setBoolean(13, this.tercero_empleado);
            stmt.setBoolean(14, this.tercero_proveedor);
            stmt.setBoolean(15, this.tercero_accionista_asociado);
            stmt.setString(16, this.tercero_tipo);
            stmt.setString(17, this.tercero_estado);
            stmt.setString(18, this.id);

            Logger.getLogger(GeneralTercero.class.getName()).log(Level.INFO,
                    "Ejecutando UPDATE: " + stmt.toString());


            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE,
                    "Error al actualizar tercero: " + sql, e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM dbo.generalTercero WHERE id = ?";
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, "Error al eliminar tercero", e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public static List<GeneralTercero> listInObjects(String filtro, String orden) {
        List<GeneralTercero> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.generalTercero"
                + (filtro != null && !filtro.trim().isEmpty() ? " WHERE " + filtro : "")
                + (orden != null && !orden.trim().isEmpty() ? " ORDER BY " + orden : "");
        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                GeneralTercero t = new GeneralTercero();
                t.id = rs.getString("id");
                t.tercero_codigo = rs.getString("tercero_codigo");
                t.tercero_id_tipo_identificacion = rs.getString("tercero_id_tipo_identificacion");
                t.tercero_razon_nombres = rs.getString("tercero_razon_nombres");
                t.tercero_fecha_nacimiento = rs.getString("tercero_fecha_nacimiento");
                t.tercero_direccion = rs.getString("tercero_direccion");
                t.tercero_telefono = rs.getString("tercero_telefono");
                t.tercero_correo = rs.getString("tercero_correo");
                t.tercero_pais = rs.getString("tercero_pais");
                t.tercero_departamento = rs.getString("tercero_departamento");
                t.tercero_ciudad = rs.getString("tercero_ciudad");
                t.tercero_ciiu = rs.getString("tercero_ciiu");
                t.tercero_facturar = rs.getString("tercero_facturar");
                t.tercero_empleado = rs.getString("tercero_empleado");
                t.tercero_proveedor = rs.getString("tercero_proveedor");
                t.tercero_accionista_asociado = rs.getString("tercero_accionista_asociado");
                t.tercero_tipo = rs.getString("tercero_tipo");
                t.tercero_estado = rs.getString("tercero_estado");
                list.add(t);
            }
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return list;
    }
}

