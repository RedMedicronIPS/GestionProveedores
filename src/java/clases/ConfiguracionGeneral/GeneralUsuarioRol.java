/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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

/**
 *
 * @author IPS OBRERO
 */
public class GeneralUsuarioRol {

    private String id;
    private String rol_nombre;
    private String rol_anulacion;
    

    public GeneralUsuarioRol() { 
    }

      public GeneralUsuarioRol(String id) {
        GeneralUsuarioRol data = getById(id);
        if (data != null) {
            this.id = data.id;
            this.rol_nombre = data.rol_nombre;
            this.rol_anulacion = data.rol_anulacion;
        }
      }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRol_nombre() {
        return rol_nombre;
    }

    public void setRol_nombre(String rol_nombre) {
        this.rol_nombre = rol_nombre;
    }

    public String getRol_anulacion() {
        return rol_anulacion;
    }

    public void setRol_anulacion(String rol_anulacion) {
        this.rol_anulacion = rol_anulacion;
    }
    


 public static GeneralUsuarioRol getById(String id) {
        String sql = "SELECT id, rol_nombre, rol_anulacion "
                + "FROM dbo.generalUsuarioRol WHERE id = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GeneralUsuarioRol rol = new GeneralUsuarioRol();
                    rol.setId(rs.getString("id"));
                    rol.setRol_nombre(rs.getString("rol_nombre"));
                    rol.setRol_anulacion(rs.getString("rol_anulacion"));                   
                    return rol;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralUsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }
        return null;
    }
    
     public static String getListaEnOption(String preseleccionado) {
        StringBuilder lista = new StringBuilder();

        List<GeneralUsuarioRol> data = listInObjects(null, "rol_nombre");

        for (GeneralUsuarioRol ti : data) {
            String selected = preseleccionado != null && preseleccionado.equals(ti.getId()) ? " selected" : "";
            lista.append("<option value='").append(ti.getId()).append("'").append(selected).append(">")
                    .append(ti.getRol_nombre()).append("</option>");
        }

        return lista.toString();
    }

   public static List<GeneralUsuarioRol> listInObjects(String filtro, String orden) {
        List<GeneralUsuarioRol> list = new ArrayList<>();

        StringBuilder sql = new StringBuilder("SELECT id, rol_nombre, rol_anulacion FROM dbo.generalUsuarioRol");

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
                GeneralUsuarioRol rol = new GeneralUsuarioRol();
                rol.setId(rs.getString("id"));
                rol.setRol_nombre(rs.getString("rol_nombre"));
                rol.setRol_anulacion(rs.getString("rol_anulacion"));               
                list.add(rol);
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralUsuarioRol.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return list;
    }

    @Override
    public String toString() {
        return getRol_nombre();
    }
}
