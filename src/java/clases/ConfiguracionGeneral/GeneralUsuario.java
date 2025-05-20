package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import static clasesDataBase.ConectorBD.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneralUsuario {

    private String id;
    private String general_usu_num_identificacion;
    private String general_usu_nombre;
    private String general_usu_apellido;
    private String general_usu_correo_electronico;
    private String general_usu_num_celular;
    private String general_usu_clave;
    private int general_usu_rol;
    private int general_usu_estado;
    private String general_usu_id_tipo_identificacion;
    private int general_usu_rol_anulacion;

    public GeneralUsuario() {
    }

    public GeneralUsuario(String id) {
        GeneralUsuario data = getById(id);
        if (data != null) {
            this.id = data.id;
            this.general_usu_num_identificacion = data.general_usu_num_identificacion;
            this.general_usu_nombre = data.general_usu_nombre;
            this.general_usu_apellido = data.general_usu_apellido;
            this.general_usu_correo_electronico = data.general_usu_correo_electronico;
            this.general_usu_num_celular = data.general_usu_num_celular;
            this.general_usu_clave = data.general_usu_clave;
            this.general_usu_rol = data.general_usu_rol;
            this.general_usu_estado = data.general_usu_estado;
            this.general_usu_id_tipo_identificacion = data.general_usu_id_tipo_identificacion;
            this.general_usu_rol_anulacion = data.general_usu_rol_anulacion;
        }
    }

    public static GeneralUsuario getById(String id) {
        String sql = "SELECT gu.id, general_usu_num_identificacion, general_usu_nombre, general_usu_apellido, \n"
                + "general_usu_correo_electronico, general_usu_num_celular, general_usu_clave, \n"
                + "general_usu_rol, general_usu_estado, general_usu_id_tipo_identificacion,gur.rol_anulacion\n"
                + "FROM dbo.generalUsuario gu\n"
                + "inner join dbo.generalUsuarioRol gur on gu.general_usu_rol=gur.id\n"
                + "WHERE gu.id= ? ";

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    GeneralUsuario user = new GeneralUsuario();
                    user.setId(rs.getString("id"));
                    user.setGeneral_usu_num_identificacion(rs.getString("general_usu_num_identificacion"));
                    user.setGeneral_usu_nombre(rs.getString("general_usu_nombre"));
                    user.setGeneral_usu_apellido(rs.getString("general_usu_apellido"));
                    user.setGeneral_usu_correo_electronico(rs.getString("general_usu_correo_electronico"));
                    user.setGeneral_usu_num_celular(rs.getString("general_usu_num_celular"));
                    user.setGeneral_usu_clave(rs.getString("general_usu_clave"));
                    user.setGeneral_usu_rol(rs.getInt("general_usu_rol"));
                    user.setGeneral_usu_estado(rs.getInt("general_usu_estado"));
                    user.setGeneral_usu_id_tipo_identificacion(rs.getString("general_usu_id_tipo_identificacion"));
                    user.setGeneral_usu_rol_anulacion(rs.getInt("rol_anulacion"));
                    return user;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeneral_usu_num_identificacion() {
        return general_usu_num_identificacion != null ? general_usu_num_identificacion : "";
    }

    public void setGeneral_usu_num_identificacion(String general_usu_num_identificacion) {
        this.general_usu_num_identificacion = general_usu_num_identificacion;
    }

    public String getGeneral_usu_nombre() {
        return general_usu_nombre != null ? general_usu_nombre : "";
    }

    public void setGeneral_usu_nombre(String general_usu_nombre) {
        this.general_usu_nombre = general_usu_nombre;
    }

    public String getGeneral_usu_apellido() {
        return general_usu_apellido != null ? general_usu_apellido : "";
    }

    public void setGeneral_usu_apellido(String general_usu_apellido) {
        this.general_usu_apellido = general_usu_apellido;
    }

    public String getGeneral_usu_correo_electronico() {
        return general_usu_correo_electronico != null ? general_usu_correo_electronico : "";
    }

    public void setGeneral_usu_correo_electronico(String general_usu_correo_electronico) {
        this.general_usu_correo_electronico = general_usu_correo_electronico;
    }

    public String getGeneral_usu_num_celular() {
        return general_usu_num_celular != null ? general_usu_num_celular : "";
    }

    public void setGeneral_usu_num_celular(String general_usu_num_celular) {
        this.general_usu_num_celular = general_usu_num_celular;
    }

    public String getGeneral_usu_clave() {
        return general_usu_clave != null ? general_usu_clave : "";
    }

    public void setGeneral_usu_clave(String general_usu_clave) {
        if (general_usu_clave == null || general_usu_clave.trim().isEmpty()) {
            this.general_usu_clave = id;
        } else {
            this.general_usu_clave = general_usu_clave;
        }
    }

    public int getGeneral_usu_rol() {
        return general_usu_rol;
    }

    public void setGeneral_usu_rol(int general_usu_rol) {
        this.general_usu_rol = general_usu_rol;
    }

    public int getGeneral_usu_rol_anulacion() {
        return general_usu_rol_anulacion;
    }

    public void setGeneral_usu_rol_anulacion(int general_usu_rol_anulacion) {
        this.general_usu_rol_anulacion = general_usu_rol_anulacion;
    }

    public int getGeneral_usu_estado() {
        return general_usu_estado;
    }

    public void setGeneral_usu_estado(int general_usu_estado) {
        this.general_usu_estado = general_usu_estado;
    }

    public String getGeneral_usu_id_tipo_identificacion() {
        return general_usu_id_tipo_identificacion != null ? general_usu_id_tipo_identificacion : "";
    }

    public void setGeneral_usu_id_tipo_identificacion(String general_usu_id_tipo_identificacion) {
        this.general_usu_id_tipo_identificacion = general_usu_id_tipo_identificacion;
    }

    @Override
    public String toString() {
        return general_usu_num_identificacion != null
                ? general_usu_num_identificacion + " - " + general_usu_nombre + " " + general_usu_apellido
                : "";
    }

    public GeneralTipoUsuario typeObject() {
        return new GeneralTipoUsuario(String.valueOf(general_usu_rol));
    }

    public int getIdRol() {
        return general_usu_rol;
    }

    public GeneralUsuarioRol getTipoRol() {
        return new GeneralUsuarioRol(String.valueOf(general_usu_rol));
    }

    public clases.ConfiguracionGeneral.GeneralTipoIdentificacion getTI() {
        return new clases.ConfiguracionGeneral.GeneralTipoIdentificacion(general_usu_id_tipo_identificacion);
    }

    public static GeneralUsuario validar(String identificacion, String clave) {
        GeneralUsuario user = null;

        String sql = "SELECT * FROM dbo.generalUsuario "
                + "WHERE general_usu_num_identificacion = ? "
                + "AND general_usu_clave = CONVERT(VARCHAR(32), HASHBYTES('MD5', CAST(? AS VARCHAR(8000))), 2)";

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, identificacion);
            stmt.setString(2, clave.trim());

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new GeneralUsuario(rs.getString("id"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(GeneralUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConectorBD.cerrarConexion();
        }

        return user;
    }

    public boolean create() {
        String sql = "INSERT INTO dbo.generalUsuario (general_usu_num_identificacion, general_usu_nombre, general_usu_apellido, "
                + "general_usu_correo_electronico, general_usu_num_celular, general_usu_clave, general_usu_rol, general_usu_estado, general_usu_id_tipo_identificacion) "
                + "VALUES (?, ?, ?, ?, ?, CONVERT(VARCHAR(32), HASHBYTES('MD5', ?), 2), ?, ?, ?)";

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, general_usu_num_identificacion);
            stmt.setString(2, general_usu_nombre);
            stmt.setString(3, general_usu_apellido);
            stmt.setString(4, general_usu_correo_electronico);
            stmt.setString(5, general_usu_num_celular);
            stmt.setString(6, general_usu_clave.trim());
            stmt.setInt(7, general_usu_rol);
            stmt.setInt(8, general_usu_estado);
            stmt.setString(9, general_usu_id_tipo_identificacion);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean actualizarPerfil() {
        StringBuilder sql = new StringBuilder("UPDATE dbo.generalUsuario SET ")
                .append("general_usu_num_identificacion = ?, ")
                .append("general_usu_nombre = ?, ")
                .append("general_usu_apellido = ?, ")
                .append("general_usu_correo_electronico = ?, ")
                .append("general_usu_num_celular = ?, ");
        boolean actualizarClave = general_usu_clave != null && !general_usu_clave.isEmpty();
        if (actualizarClave) {
            sql.append("general_usu_clave = CONVERT(VARCHAR(32), HASHBYTES('MD5', CAST(? AS VARCHAR(8000))), 2) ");
        }
        sql.append(" WHERE id = ?");

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql.toString())) {

            int i = 1;
            stmt.setString(i++, general_usu_num_identificacion);
            stmt.setString(i++, general_usu_nombre);
            stmt.setString(i++, general_usu_apellido);
            stmt.setString(i++, general_usu_correo_electronico);
            stmt.setString(i++, general_usu_num_celular);
            if (actualizarClave) {
                stmt.setString(i++, general_usu_clave.trim());
            }
            stmt.setInt(i++, Integer.parseInt(id));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean update() {
        StringBuilder sql = new StringBuilder("UPDATE dbo.generalUsuario SET ")
                .append("general_usu_num_identificacion = ?, ")
                .append("general_usu_nombre = ?, ")
                .append("general_usu_apellido = ?, ")
                .append("general_usu_correo_electronico = ?, ")
                .append("general_usu_num_celular = ?, ")
                .append("general_usu_rol = ?, ")
                .append("general_usu_estado = ?, ")
                .append("general_usu_id_tipo_identificacion = ? ")
                .append(" WHERE id = ?");

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql.toString())) {
            int i = 1;
            stmt.setString(i++, general_usu_num_identificacion);
            stmt.setString(i++, general_usu_nombre);
            stmt.setString(i++, general_usu_apellido);
            stmt.setString(i++, general_usu_correo_electronico);
            stmt.setString(i++, general_usu_num_celular);
            stmt.setInt(i++, general_usu_rol);
            stmt.setInt(i++, general_usu_estado);
            stmt.setString(i++, general_usu_id_tipo_identificacion);
            stmt.setInt(i++, Integer.parseInt(id));
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean restablecerClave(String nuevaClave) {
        String sql = "UPDATE dbo.generalUsuario SET general_usu_clave = CONVERT(VARCHAR(32), HASHBYTES('MD5', CAST(? AS VARCHAR(8000))), 2) WHERE id = ?";

        try (Connection connection = ConectorBD.getConnection(); PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nuevaClave.trim());
            stmt.setInt(2, Integer.parseInt(id));
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public static void consultar(String cadenaSQL) {
        try (Connection conexion = getConnection(); PreparedStatement sentencia = conexion.prepareStatement(cadenaSQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet resultado = sentencia.executeQuery()) {

            ResultSetMetaData metaData = resultado.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultado.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(metaData.getColumnLabel(i) + ": " + resultado.getString(i) + " | ");
                }
                System.out.println();
            }
        } catch (SQLException ex) {
            System.out.println("Error en la cadena SQL " + cadenaSQL + ": " + ex.getMessage());
        } finally {
            ConectorBD.cerrarConexion();
        }
    }

    public boolean delete() {
        String sql = "DELETE FROM dbo.generalUsuario WHERE id='" + id + "'";
        return ConectorBD.ejecutarQuery(sql);
    }

    public static List<Map<String, Object>> consultarLista(String cadenaSQL) {
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection conexion = getConnection(); PreparedStatement sentencia = conexion.prepareStatement(cadenaSQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY); ResultSet resultado = sentencia.executeQuery()) {

            ResultSetMetaData metaData = resultado.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultado.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    fila.put(metaData.getColumnLabel(i), resultado.getObject(i));
                }
                lista.add(fila);
            }

        } catch (SQLException ex) {
            System.out.println("Error en la cadena SQL " + cadenaSQL + ": " + ex.getMessage());
        } finally {
            ConectorBD.cerrarConexion();
        }

        return lista;
    }

    public static List<GeneralUsuario> getListaEnObjetos(String filtro, String orden) {
        List<GeneralUsuario> lista = new ArrayList<>();
        String rowSql = "SELECT * FROM dbo.generalUsuario";

        if (filtro != null && !filtro.isEmpty()) {
            rowSql += " WHERE " + filtro;
        }
        if (orden != null && !orden.isEmpty()) {
            rowSql += " ORDER BY " + orden;
        }

        List<Map<String, Object>> resultados = consultarLista(rowSql);

        for (Map<String, Object> map : resultados) {
            GeneralUsuario usuario = new GeneralUsuario();

            usuario.setId(map.get("id") != null ? map.get("id").toString() : "");
            usuario.setGeneral_usu_num_identificacion(map.get("general_usu_num_identificacion") != null ? map.get("general_usu_num_identificacion").toString() : "");
            usuario.setGeneral_usu_nombre(map.get("general_usu_nombre") != null ? map.get("general_usu_nombre").toString() : "");
            usuario.setGeneral_usu_apellido(map.get("general_usu_apellido") != null ? map.get("general_usu_apellido").toString() : "");
            usuario.setGeneral_usu_correo_electronico(map.get("general_usu_correo_electronico") != null ? map.get("general_usu_correo_electronico").toString() : "");
            usuario.setGeneral_usu_num_celular(map.get("general_usu_num_celular") != null ? map.get("general_usu_num_celular").toString() : "");
            usuario.setGeneral_usu_clave(map.get("general_usu_clave") != null ? map.get("general_usu_clave").toString() : "");
            usuario.setGeneral_usu_id_tipo_identificacion(map.get("general_usu_id_tipo_identificacion") != null ? map.get("general_usu_id_tipo_identificacion").toString() : "");
            usuario.setGeneral_usu_rol(map.get("general_usu_rol") != null ? Integer.parseInt(map.get("general_usu_rol").toString()) : 0);
            usuario.setGeneral_usu_estado(map.get("general_usu_estado") != null ? Integer.parseInt(map.get("general_usu_estado").toString()) : 0);

            lista.add(usuario);
        }

        return lista;
    }

    public static String getListaEnOption(String preseleccionado) {
        StringBuilder lista = new StringBuilder();
        List<GeneralUsuario> data = getListaEnObjetos(null, "general_usu_nombre");

        for (GeneralUsuario ti : data) {
            String auxiliar = preseleccionado.equals(ti.getId()) ? " selected" : "";
            lista.append("<option value='").append(ti.getId()).append("'").append(auxiliar).append(">")
                    .append(ti.getGeneral_usu_nombre()).append(" ").append(ti.getGeneral_usu_apellido())
                    .append("</option>");
        }
        return lista.toString();
    }

}
