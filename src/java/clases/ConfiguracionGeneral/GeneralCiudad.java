package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralCiudad {

    public static List<String> getCiudadesPorDepartamentoNombre(String nombreDepartamento) {
        List<String> ciudades = new ArrayList<>();
        String sql = "SELECT ciudad.nombre FROM generalCiudades ciudad "
                + "JOIN generalDepartamentos dep ON ciudad.idDepartamento = dep.id "
                + "WHERE dep.nombre = ? ORDER BY ciudad.nombre";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
            stmt.setString(1, nombreDepartamento);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ciudades.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.cerrarConexion();
        }

        return ciudades;
    }

    public static String getListaEnArregloJs() {
        String lista = "[";
        List<String> paises = obtenerCiuDesdeBaseDeDatos();
        for (int i = 0; i < paises.size(); i++) {
            if (i > 0) {
                lista += ", ";
            }
            lista += "'" + paises.get(i) + "'";
        }
        lista += "]";
        return lista;
    }

    private static List<String> obtenerCiuDesdeBaseDeDatos() {
        List<String> paises = new ArrayList<>();
        String sql = "SELECT nombre FROM generalCiudades ORDER BY nombre";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                paises.add(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.cerrarConexion();
        }
        return paises;
    }
}
