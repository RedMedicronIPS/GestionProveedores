package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralPais {

    // Método para obtener la lista de países en formato de arreglo JavaScript
    public static String getListaEnArregloJs() {
        String lista = "[";
        List<String> paises = obtenerPaisesDesdeBaseDeDatos();

        // Convertimos la lista de países en un formato adecuado para JavaScript
        for (int i = 0; i < paises.size(); i++) {
            if (i > 0) lista += ", ";
            lista += "'" + paises.get(i) + "'";  // Se agrega cada país entre comillas simples
        }

        lista += "]";
        return lista;
    }

    // Método auxiliar para obtener los países desde la base de datos
    private static List<String> obtenerPaisesDesdeBaseDeDatos() {
        List<String> paises = new ArrayList<>();
        String sql = "SELECT nombre FROM generalPais ORDER BY nombre";  // La consulta SQL para obtener los países

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

    // Método para obtener la lista de nombres en formato para opciones HTML
    public static String getListaEnOption(String nombreSeleccionado) {
        StringBuilder options = new StringBuilder();
        String sql = "SELECT nombre FROM generalPais ORDER BY nombre";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String selected = nombre.equals(nombreSeleccionado) ? "selected" : "";
                options.append("<option value=\"").append(nombre).append("\" ").append(selected).append(">").append(nombre).append("</option>");
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo básico de excepciones
        } finally {
            ConectorBD.cerrarConexion();  // Cerramos la conexión después de usarla
        }

        return options.toString();
    }

    // Método para obtener la lista de nombres de los países, como un arreglo de texto (sin HTML)
    public static String getListaNombres() {
        StringBuilder nombres = new StringBuilder();
        String sql = "SELECT nombre FROM generalPais ORDER BY nombre";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                nombres.append("\"").append(nombre).append("\",");  // Añadimos comillas y coma al final
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Manejo básico de excepciones
        } finally {
            ConectorBD.cerrarConexion();  // Cerramos la conexión después de usarla
        }

        // Eliminamos la última coma si existe
        if (nombres.length() > 0) {
            nombres.setLength(nombres.length() - 1);
        }

        return nombres.toString();  // Retornamos la lista de países en formato adecuado para JavaScript
    }
}
