package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralTipoIdentificacion {

    private String id;
    private String general_tip_ident_codigo;
    private String general_tip_ident_nombre;
    private String general_tip_ident_estado;

    public GeneralTipoIdentificacion() {
    }

    public GeneralTipoIdentificacion(String id) {
        GeneralTipoIdentificacion data = getById(id);
        if (data != null) {
            this.id = data.id;
            this.general_tip_ident_codigo = data.general_tip_ident_codigo;
            this.general_tip_ident_nombre = data.general_tip_ident_nombre;
            this.general_tip_ident_estado = data.general_tip_ident_estado;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeneral_tip_ident_codigo() {
        return general_tip_ident_codigo != null ? general_tip_ident_codigo : "";
    }

    public void setGeneral_tip_ident_codigo(String general_tip_ident_codigo) {
        this.general_tip_ident_codigo = general_tip_ident_codigo;
    }

    public String getGeneral_tip_ident_nombre() {
        return general_tip_ident_nombre != null ? general_tip_ident_nombre : "";
    }

    public void setGeneral_tip_ident_nombre(String general_tip_ident_nombre) {
        this.general_tip_ident_nombre = general_tip_ident_nombre;
    }

    public String getGeneral_tip_ident_estado() {
        return general_tip_ident_estado;
    }

    public void setGeneral_tip_ident_estado(String general_tip_ident_estado) {
        this.general_tip_ident_estado = general_tip_ident_estado;
    }

    @Override
    public String toString() {
        return general_tip_ident_nombre;
    }

    public boolean create() {
        String sql = "INSERT INTO dbo.generalTipoIdentificacion "
                + "(general_tip_ident_codigo, general_tip_ident_nombre, general_tip_ident_estado) "
                + "VALUES (?, ?, ?)";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, general_tip_ident_codigo);
            ps.setString(2, general_tip_ident_nombre);
            ps.setString(3, general_tip_ident_estado);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralTipoIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean update() {
        String sql = "UPDATE dbo.generalTipoIdentificacion "
                + "SET general_tip_ident_codigo = ?, general_tip_ident_nombre = ?, general_tip_ident_estado = ? "
                + "WHERE id = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, general_tip_ident_codigo);
            ps.setString(2, general_tip_ident_nombre);
            ps.setString(3, general_tip_ident_estado);
            ps.setString(4, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralTipoIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM dbo.generalTipoIdentificacion WHERE id = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralTipoIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public static GeneralTipoIdentificacion getById(String id) {
        String sql = "SELECT id, general_tip_ident_codigo, general_tip_ident_nombre, general_tip_ident_estado "
                + "FROM dbo.generalTipoIdentificacion WHERE id = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GeneralTipoIdentificacion tipoIdent = new GeneralTipoIdentificacion();
                    tipoIdent.setId(rs.getString("id"));
                    tipoIdent.setGeneral_tip_ident_codigo(rs.getString("general_tip_ident_codigo"));
                    tipoIdent.setGeneral_tip_ident_nombre(rs.getString("general_tip_ident_nombre"));
                    tipoIdent.setGeneral_tip_ident_estado(rs.getString("general_tip_ident_estado"));
                    return tipoIdent;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralTipoIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return null;
    }

    public static List<GeneralTipoIdentificacion> listInObjects(String filtro, String orden) {
        List<GeneralTipoIdentificacion> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT id, general_tip_ident_codigo, general_tip_ident_nombre, general_tip_ident_estado FROM dbo.generalTipoIdentificacion");

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
                GeneralTipoIdentificacion tipoIdent = new GeneralTipoIdentificacion();
                tipoIdent.setId(rs.getString("id"));
                tipoIdent.setGeneral_tip_ident_codigo(rs.getString("general_tip_ident_codigo"));
                tipoIdent.setGeneral_tip_ident_nombre(rs.getString("general_tip_ident_nombre"));
                tipoIdent.setGeneral_tip_ident_estado(rs.getString("general_tip_ident_estado"));
                list.add(tipoIdent);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralTipoIdentificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return list;
    }

    public static String getListaEnOption(String preseleccionado) {
    StringBuilder lista = new StringBuilder();

    List<GeneralTipoIdentificacion> data = listInObjects(null, "general_tip_ident_nombre");

    for (GeneralTipoIdentificacion ti : data) {
        String selected = preseleccionado != null && preseleccionado.equals(ti.getId()) ? " selected" : "";
        lista.append("<option value='").append(ti.getId()).append("'").append(selected).append(">")
             .append(ti.getGeneral_tip_ident_codigo()).append(" - ")
             .append(ti.getGeneral_tip_ident_nombre()).append("</option>");
    }

    return lista.toString();
}


}
