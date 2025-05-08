package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la obtención de ciudades
 * según el departamento.
 */
public class GeneralCiudad {

    /**
     * Método que obtiene las ciudades de un departamento específico.
     *
     * @param idDepartamento El ID del departamento.
     * @return Lista de nombres de ciudades.
     */
   public static List<String> getCiudadesPorDepartamentoNombre(String nombreDepartamento) {
    List<String> ciudades = new ArrayList<>();
    // Cambiar el nombre de la columna para que coincida con la estructura de la tabla
    String sql = "SELECT ciudad.nombre FROM generalCiudades ciudad " +
                 "JOIN generalDepartamentos dep ON ciudad.idDepartamento = dep.id " + // idDepartamento en lugar de id_departamento
                 "WHERE dep.nombre = ? ORDER BY ciudad.nombre";

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
 // Método para obtener la lista de países en formato de arreglo JavaScript
    public static String getListaEnArregloJs() {
        String lista = "[";
        List<String> paises = obtenerCiuDesdeBaseDeDatos();

        // Convertimos la lista de países en un formato adecuado para JavaScript
        for (int i = 0; i < paises.size(); i++) {
            if (i > 0) lista += ", ";
            lista += "'" + paises.get(i) + "'";  // Se agrega cada país entre comillas simples
        }

        lista += "]";
        return lista;
    }

    // Método auxiliar para obtener los países desde la base de datos
    private static List<String> obtenerCiuDesdeBaseDeDatos() {
        List<String> paises = new ArrayList<>();
        String sql = "SELECT nombre FROM generalCiudades ORDER BY nombre";  // La consulta SQL para obtener los países

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                paises.add(rs.getString("nombre"));  // Añadimos el nombre de cada país a la lista
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo básico de excepciones
        } finally {
            ConectorBD.cerrarConexion();  // Cerramos la conexión después de usarla
        }

        return paises;
    }

}
