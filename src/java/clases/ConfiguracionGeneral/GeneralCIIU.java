package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralCIIU {

    private String id;
    private String tabla;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String habilitado;
    private String aplicacion;
    private String isStandardGEL;
    private String isStandardMSPS;
    private String extra_I;
    private String extra_II;
    private String extra_III;
    private String extra_IV;
    private String extra_V;
    private String extra_VI;
    private String extra_VII;
    private String extra_VIII;
    private String extra_IX;
    private String extra_X;
    private String valorRegistro;
    private String usuarioResponsable;
    private String fechaActualizacion;
    private String isPublicPrivate;

    public GeneralCIIU() {
    }

    public GeneralCIIU(String id) {
        GeneralCIIU data = getById(id);
        if (data != null) {
            this.id = data.id;
            this.tabla = data.tabla;
            this.codigo = data.codigo;
            this.nombre = data.nombre;
            this.descripcion = data.descripcion;
            this.habilitado = data.habilitado;
            this.aplicacion = data.aplicacion;
            this.isStandardGEL = data.isStandardGEL;
            this.isStandardMSPS = data.isStandardMSPS;
            this.extra_I = data.extra_I;
            this.extra_II = data.extra_II;
            this.extra_III = data.extra_III;
            this.extra_IV = data.extra_IV;
            this.extra_V = data.extra_V;
            this.extra_VI = data.extra_VI;
            this.extra_VII = data.extra_VII;
            this.extra_VIII = data.extra_VIII;
            this.extra_IX = data.extra_IX;
            this.extra_X = data.extra_X;
            this.valorRegistro = data.valorRegistro;
            this.usuarioResponsable = data.usuarioResponsable;
            this.fechaActualizacion = data.fechaActualizacion;
            this.isPublicPrivate = data.isPublicPrivate;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public String getIsStandardGEL() {
        return isStandardGEL;
    }

    public void setIsStandardGEL(String isStandardGEL) {
        this.isStandardGEL = isStandardGEL;
    }

    public String getIsStandardMSPS() {
        return isStandardMSPS;
    }

    public void setIsStandardMSPS(String isStandardMSPS) {
        this.isStandardMSPS = isStandardMSPS;
    }

    public String getExtra_I() {
        return extra_I;
    }

    public void setExtra_I(String extra_I) {
        this.extra_I = extra_I;
    }

    public String getExtra_II() {
        return extra_II;
    }

    public void setExtra_II(String extra_II) {
        this.extra_II = extra_II;
    }

    public String getExtra_III() {
        return extra_III;
    }

    public void setExtra_III(String extra_III) {
        this.extra_III = extra_III;
    }

    public String getExtra_IV() {
        return extra_IV;
    }

    public void setExtra_IV(String extra_IV) {
        this.extra_IV = extra_IV;
    }

    public String getExtra_V() {
        return extra_V;
    }

    public void setExtra_V(String extra_V) {
        this.extra_V = extra_V;
    }

    public String getExtra_VI() {
        return extra_VI;
    }

    public void setExtra_VI(String extra_VI) {
        this.extra_VI = extra_VI;
    }

    public String getExtra_VII() {
        return extra_VII;
    }

    public void setExtra_VII(String extra_VII) {
        this.extra_VII = extra_VII;
    }

    public String getExtra_VIII() {
        return extra_VIII;
    }

    public void setExtra_VIII(String extra_VIII) {
        this.extra_VIII = extra_VIII;
    }

    public String getExtra_IX() {
        return extra_IX;
    }

    public void setExtra_IX(String extra_IX) {
        this.extra_IX = extra_IX;
    }

    public String getExtra_X() {
        return extra_X;
    }

    public void setExtra_X(String extra_X) {
        this.extra_X = extra_X;
    }

    public String getValorRegistro() {
        return valorRegistro;
    }

    public void setValorRegistro(String valorRegistro) {
        this.valorRegistro = valorRegistro;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getIsPublicPrivate() {
        return isPublicPrivate;
    }

    public void setIsPublicPrivate(String isPublicPrivate) {
        this.isPublicPrivate = isPublicPrivate;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    public boolean create() {
        String sql = "INSERT INTO generalCIIU (Tabla, Codigo, Nombre, Descripcion, Habilitado, Aplicacion, IsStandardGEL, IsStandardMSPS, "
                + "Extra_I, Extra_II, Extra_III, Extra_IV, Extra_V, Extra_VI, Extra_VII, Extra_VIII, Extra_IX, Extra_X, "
                + "ValorRegistro, UsuarioResponsable, Fecha_Actualizacion, IsPublicPrivate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tabla);
            ps.setString(2, codigo);
            ps.setString(3, nombre);
            ps.setString(4, descripcion);
            ps.setBoolean(5, Boolean.parseBoolean(habilitado));
            ps.setString(6, aplicacion);
            ps.setBoolean(7, Boolean.parseBoolean(isStandardGEL));
            ps.setBoolean(8, Boolean.parseBoolean(isStandardMSPS));
            ps.setString(9, extra_I);
            ps.setString(10, extra_II);
            ps.setString(11, extra_III);
            ps.setString(12, extra_IV);
            ps.setString(13, extra_V);
            ps.setString(14, extra_VI);
            ps.setString(15, extra_VII);
            ps.setString(16, extra_VIII);
            ps.setString(17, extra_IX);
            ps.setString(18, extra_X);
            ps.setString(19, valorRegistro);
            ps.setString(20, usuarioResponsable);
            ps.setString(21, fechaActualizacion);
            ps.setString(22, isPublicPrivate);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean update() {
        String sql = "UPDATE generalCIIU SET Tabla=?, Codigo=?, Nombre=?, Descripcion=?, Habilitado=?, Aplicacion=?, "
                + "IsStandardGEL=?, IsStandardMSPS=?, Extra_I=?, Extra_II=?, Extra_III=?, Extra_IV=?, Extra_V=?, "
                + "Extra_VI=?, Extra_VII=?, Extra_VIII=?, Extra_IX=?, Extra_X=?, ValorRegistro=?, UsuarioResponsable=?, "
                + "Fecha_Actualizacion=?, IsPublicPrivate=? WHERE ID=?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tabla);
            ps.setString(2, codigo);
            ps.setString(3, nombre);
            ps.setString(4, descripcion);
            ps.setBoolean(5, Boolean.parseBoolean(habilitado));
            ps.setString(6, aplicacion);
            ps.setBoolean(7, Boolean.parseBoolean(isStandardGEL));
            ps.setBoolean(8, Boolean.parseBoolean(isStandardMSPS));
            ps.setString(9, extra_I);
            ps.setString(10, extra_II);
            ps.setString(11, extra_III);
            ps.setString(12, extra_IV);
            ps.setString(13, extra_V);
            ps.setString(14, extra_VI);
            ps.setString(15, extra_VII);
            ps.setString(16, extra_VIII);
            ps.setString(17, extra_IX);
            ps.setString(18, extra_X);
            ps.setString(19, valorRegistro);
            ps.setString(20, usuarioResponsable);
            ps.setString(21, fechaActualizacion);
            ps.setString(22, isPublicPrivate);
            ps.setString(23, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM generalCIIU WHERE ID = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public static GeneralCIIU getById(String id) {
        String sql = "SELECT * FROM generalCIIU WHERE ID = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GeneralCIIU g = new GeneralCIIU();
                    g.id = rs.getString("ID");
                    g.tabla = rs.getString("Tabla");
                    g.codigo = rs.getString("Codigo");
                    g.nombre = rs.getString("Nombre");
                    g.descripcion = rs.getString("Descripcion");
                    g.habilitado = String.valueOf(rs.getBoolean("Habilitado"));
                    g.aplicacion = rs.getString("Aplicacion");
                    g.isStandardGEL = String.valueOf(rs.getBoolean("IsStandardGEL"));
                    g.isStandardMSPS = String.valueOf(rs.getBoolean("IsStandardMSPS"));
                    g.extra_I = rs.getString("Extra_I");
                    g.extra_II = rs.getString("Extra_II");
                    g.extra_III = rs.getString("Extra_III");
                    g.extra_IV = rs.getString("Extra_IV");
                    g.extra_V = rs.getString("Extra_V");
                    g.extra_VI = rs.getString("Extra_VI");
                    g.extra_VII = rs.getString("Extra_VII");
                    g.extra_VIII = rs.getString("Extra_VIII");
                    g.extra_IX = rs.getString("Extra_IX");
                    g.extra_X = rs.getString("Extra_X");
                    g.valorRegistro = rs.getString("ValorRegistro");
                    g.usuarioResponsable = rs.getString("UsuarioResponsable");
                    g.fechaActualizacion = rs.getString("Fecha_Actualizacion");
                    g.isPublicPrivate = rs.getString("IsPublicPrivate");
                    return g;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return null;
    }

    public static List<GeneralCIIU> listInObjects(String filtro, String orden) {
        List<GeneralCIIU> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM generalCIIU");

        if (filtro != null && !filtro.trim().isEmpty()) {
            sql.append(" WHERE ").append(filtro);
        }
        if (orden != null && !orden.trim().isEmpty()) {
            sql.append(" ORDER BY ").append(orden);
        }

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GeneralCIIU g = new GeneralCIIU();
                g.id = rs.getString("ID");
                g.tabla = rs.getString("Tabla");
                g.codigo = rs.getString("Codigo");
                g.nombre = rs.getString("Nombre");
                g.descripcion = rs.getString("Descripcion");
                g.habilitado = String.valueOf(rs.getBoolean("Habilitado"));
                g.aplicacion = rs.getString("Aplicacion");
                g.isStandardGEL = String.valueOf(rs.getBoolean("IsStandardGEL"));
                g.isStandardMSPS = String.valueOf(rs.getBoolean("IsStandardMSPS"));
                g.extra_I = rs.getString("Extra_I");
                g.extra_II = rs.getString("Extra_II");
                g.extra_III = rs.getString("Extra_III");
                g.extra_IV = rs.getString("Extra_IV");
                g.extra_V = rs.getString("Extra_V");
                g.extra_VI = rs.getString("Extra_VI");
                g.extra_VII = rs.getString("Extra_VII");
                g.extra_VIII = rs.getString("Extra_VIII");
                g.extra_IX = rs.getString("Extra_IX");
                g.extra_X = rs.getString("Extra_X");
                g.valorRegistro = rs.getString("ValorRegistro");
                g.usuarioResponsable = rs.getString("UsuarioResponsable");
                g.fechaActualizacion = rs.getString("Fecha_Actualizacion");
                g.isPublicPrivate = rs.getString("IsPublicPrivate");
                list.add(g);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return list;
    }

    public static String getListaEnOption(String preseleccionado) {
        StringBuilder lista = new StringBuilder();

        List<GeneralCIIU> data = listInObjects(null, "codigo, nombre");

        for (GeneralCIIU ti : data) {
            String selected = preseleccionado != null && preseleccionado.equals(ti.getId()) ? " selected" : "";
            lista.append("<option value='").append(ti.getId()).append("'").append(selected).append(">")
                    .append(ti.getCodigo()).append(" - ").append(ti.getNombre()).append("</option>");
        }

        return lista.toString();
    }
}
