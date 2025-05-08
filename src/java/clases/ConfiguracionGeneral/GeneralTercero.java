package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

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
    private boolean tercero_facturar = false;
    private boolean tercero_empleado = false;
    private boolean tercero_proveedor = false;
    private boolean tercero_accionista_asociado = false;

    private String tercero_tipo;
    private String tercero_estado = "Activo";

    public GeneralTercero() {
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
                this.tercero_facturar = rs.getBoolean("tercero_facturar");
                this.tercero_empleado = rs.getBoolean("tercero_empleado");
                this.tercero_proveedor = rs.getBoolean("tercero_proveedor");
                this.tercero_accionista_asociado = rs.getBoolean("tercero_accionista_asociado");
                this.tercero_tipo = rs.getString("tercero_tipo");
                this.tercero_estado = rs.getString("tercero_estado");
            }
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    // Getters
    public String getId() {
        return id != null ? id : "";
    }

    public void setId(String id) {
        this.id = id != null ? id : "";
    }

    public String getTerceroCodigo() {
        return tercero_codigo != null ? tercero_codigo : "";
    }

    public void setTerceroCodigo(String tercero_codigo) {
        this.tercero_codigo = tercero_codigo != null ? tercero_codigo : "";
    }

    public String getTerceroIdTipoIdentificacion() {
        return tercero_id_tipo_identificacion != null ? tercero_id_tipo_identificacion : "";
    }

    public void setTerceroIdTipoIdentificacion(String tercero_id_tipo_identificacion) {
        this.tercero_id_tipo_identificacion = tercero_id_tipo_identificacion != null ? tercero_id_tipo_identificacion : "";
    }

    public String getTerceroRazonNombres() {
        return tercero_razon_nombres != null ? tercero_razon_nombres : "";
    }

    public void setTerceroRazonNombres(String tercero_razon_nombres) {
        this.tercero_razon_nombres = tercero_razon_nombres != null ? tercero_razon_nombres : "";
    }

    public String getTerceroFechaNacimiento() {
        return tercero_fecha_nacimiento != null ? tercero_fecha_nacimiento : "";
    }

    public void setTerceroFechaNacimiento(String tercero_fecha_nacimiento) {
        this.tercero_fecha_nacimiento = tercero_fecha_nacimiento != null ? tercero_fecha_nacimiento : "";
    }

    public String getTerceroDireccion() {
        return tercero_direccion != null ? tercero_direccion : "";
    }

    public void setTerceroDireccion(String tercero_direccion) {
        this.tercero_direccion = tercero_direccion != null ? tercero_direccion : "";
    }

    public String getTerceroTelefono() {
        return tercero_telefono != null ? tercero_telefono : "";
    }

    public void setTerceroTelefono(String tercero_telefono) {
        this.tercero_telefono = tercero_telefono != null ? tercero_telefono : "";
    }

    public String getTerceroCorreo() {
        return tercero_correo != null ? tercero_correo : "";
    }

    public void setTerceroCorreo(String tercero_correo) {
        this.tercero_correo = tercero_correo != null ? tercero_correo : "";
    }

    public String getTerceroPais() {
        return tercero_pais != null ? tercero_pais : "";
    }

    public void setTerceroPais(String tercero_pais) {
        this.tercero_pais = tercero_pais != null ? tercero_pais : "";
    }

    public String getTerceroDepartamento() {
        return tercero_departamento != null ? tercero_departamento : "";
    }

    public void setTerceroDepartamento(String tercero_departamento) {
        this.tercero_departamento = tercero_departamento != null ? tercero_departamento : "";
    }

    public String getTerceroCiudad() {
        return tercero_ciudad != null ? tercero_ciudad : "";
    }

    public void setTerceroCiudad(String tercero_ciudad) {
        this.tercero_ciudad = tercero_ciudad != null ? tercero_ciudad : "";
    }

    public String getTerceroCiiu() {
        return tercero_ciiu != null ? tercero_ciiu : "";
    }

    public void setTerceroCiiu(String tercero_ciiu) {
        this.tercero_ciiu = tercero_ciiu != null ? tercero_ciiu : "";
    }

    public boolean isTerceroFacturar() {
        return tercero_facturar;
    }

    public void setTerceroFacturar(boolean tercero_facturar) {
        this.tercero_facturar = tercero_facturar;
    }

    public boolean isTerceroEmpleado() {
        return tercero_empleado;
    }

    public void setTerceroEmpleado(boolean tercero_empleado) {
        this.tercero_empleado = tercero_empleado;
    }

    public boolean isTerceroProveedor() {
        return tercero_proveedor;
    }

    public void setTerceroProveedor(boolean tercero_proveedor) {
        this.tercero_proveedor = tercero_proveedor;
    }

    public boolean isTerceroAccionistaAsociado() {
        return tercero_accionista_asociado;
    }

    public void setTerceroAccionistaAsociado(boolean tercero_accionista_asociado) {
        this.tercero_accionista_asociado = tercero_accionista_asociado;
    }

    public String getTerceroTipo() {
        return tercero_tipo != null ? tercero_tipo : "";
    }

    public void setTerceroTipo(String tercero_tipo) {
        this.tercero_tipo = tercero_tipo != null ? tercero_tipo : "";
    }

    public String getTerceroEstado() {
        return tercero_estado != null ? tercero_estado : "Activo";
    }

    public void setTerceroEstado(String tercero_estado) {
        this.tercero_estado = tercero_estado != null ? tercero_estado : "Activo";
    }

    // Checked para tipos
    public String isCheckedTT(String tipo) {
        return tipo != null && tipo.equals(tercero_tipo) ? "checked" : "";
    }

    public boolean create() {
        String sql = "INSERT INTO dbo.generalTercero (tercero_codigo, tercero_id_tipo_identificacion, tercero_razon_nombres, "
                + "tercero_fecha_nacimiento, tercero_direccion, tercero_telefono, tercero_correo, tercero_pais, "
                + "tercero_departamento, tercero_ciudad, tercero_ciiu, tercero_facturar, tercero_empleado, tercero_proveedor, "
                + "tercero_accionista_asociado, tercero_tipo, tercero_estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            stmt.setBoolean(12, tercero_facturar);
            stmt.setBoolean(13, tercero_empleado);
            stmt.setBoolean(14, tercero_proveedor);
            stmt.setBoolean(15, tercero_accionista_asociado);
            stmt.setString(16, tercero_tipo);
            stmt.setString(17, tercero_estado);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean update() {
        String sql = "UPDATE dbo.generalTercero SET tercero_codigo=?, tercero_id_tipo_identificacion=?, tercero_razon_nombres=?, "
                + "tercero_fecha_nacimiento=?, tercero_direccion=?, tercero_telefono=?, tercero_correo=?, tercero_pais=?, "
                + "tercero_departamento=?, tercero_ciudad=?, tercero_ciiu=?, tercero_facturar=?, tercero_empleado=?, "
                + "tercero_proveedor=?, tercero_accionista_asociado=?, tercero_tipo=?, tercero_estado=? WHERE id=?";

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
            stmt.setBoolean(12, tercero_facturar);
            stmt.setBoolean(13, tercero_empleado);
            stmt.setBoolean(14, tercero_proveedor);
            stmt.setBoolean(15, tercero_accionista_asociado);
            stmt.setString(16, tercero_tipo);
            stmt.setString(17, tercero_estado);
            stmt.setString(18, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(GeneralTercero.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public static List<GeneralTercero> listInObjects(String filtro, String orden) {
        List<GeneralTercero> list = new ArrayList<>();
        String sql = "SELECT * FROM dbo.generalTercero"
                + (filtro != null && !filtro.isBlank() ? " WHERE " + filtro : "")
                + (orden != null && !orden.isBlank() ? " ORDER BY " + orden : "");

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
                t.tercero_facturar = rs.getBoolean("tercero_facturar");
                t.tercero_empleado = rs.getBoolean("tercero_empleado");
                t.tercero_proveedor = rs.getBoolean("tercero_proveedor");
                t.tercero_accionista_asociado = rs.getBoolean("tercero_accionista_asociado");
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
