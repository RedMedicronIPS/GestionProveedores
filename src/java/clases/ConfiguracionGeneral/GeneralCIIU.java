package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralCIIU {

    private String id;

    private String codigo;
    private String nombre;

    public GeneralCIIU() {
    }

    public GeneralCIIU(String id) {
        GeneralCIIU data = getById(id);
        if (data != null) {
            this.id = data.id;
            this.codigo = data.codigo;
            this.nombre = data.nombre;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo != null ? codigo : "";
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre != null ? nombre : "";
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    public boolean create() {
        String sql = "INSERT INTO generalCIIU (Codigo, Nombre) "
                + "VALUES (?, ?)";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            Logger.getLogger(GeneralCIIU.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean update() {
        String sql = "UPDATE generalCIIU SET Codigo=?, Nombre=? WHERE ID=?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, id);

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
                    g.codigo = rs.getString("Codigo");
                    g.nombre = rs.getString("Nombre");
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
        String sql = "SELECT ID, Codigo, Nombre FROM generalCIIU"; // Asegúrate de usar el nombre correcto de la tabla

        if (filtro != null && !filtro.trim().isEmpty()) {
            sql += " WHERE " + filtro;
        }
        if (orden != null && !orden.trim().isEmpty()) {
            sql += " ORDER BY " + orden;
        }

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GeneralCIIU g = new GeneralCIIU();
                g.setId(rs.getString("ID"));
                g.setCodigo(rs.getString("Codigo"));
                g.setNombre(rs.getString("Nombre"));
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
        System.out.println("DEBUG - Iniciando getListaEnOption");
        StringBuilder lista = new StringBuilder();

        System.out.println("DEBUG - Obteniendo datos de CIIU...");
        List<GeneralCIIU> data = listInObjects(null, "Codigo ASC");
        System.out.println("DEBUG - Número de CIIUs encontrados: " + data.size());

        for (GeneralCIIU ciiu : data) {
            System.out.println("DEBUG - Procesando: " + ciiu.getCodigo() + " - " + ciiu.getNombre());
            String selected = (preseleccionado != null && preseleccionado.equals(ciiu.getId())) ? " selected" : "";
            lista.append("<option value='").append(ciiu.getId()).append("'").append(selected).append(">")
                    .append(ciiu.getCodigo()).append(" - ").append(ciiu.getNombre()).append("</option>");
        }

        System.out.println("DEBUG - HTML generado: " + lista.toString());
        return lista.toString();
    }
}
