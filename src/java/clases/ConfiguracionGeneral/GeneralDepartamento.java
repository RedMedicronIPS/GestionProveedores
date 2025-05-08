/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IPS OBRERO
 */
public class GeneralDepartamento {
   public static String getDepartamentosPorPais(String nombrePais) {
    StringBuilder lista = new StringBuilder();
    String sql = "SELECT gd.nombre FROM generalDepartamentos gd " +
                 "JOIN generalPais gp ON gd.idPais = gp.id " +
                 "WHERE gp.nombre = ? ORDER BY gd.nombre";
    try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql)) {
        stmt.setString(1, nombrePais);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String nombre = rs.getString("nombre");
            lista.append("<option value=\"").append(nombre).append("\">").append(nombre).append("</option>");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        ConectorBD.cerrarConexion();
    }
    return lista.toString();
}
 // Método para obtener la lista de países en formato de arreglo JavaScript
    public static String getListaEnArregloJs() {
        String lista = "[";
        List<String> paises = obtenerDeptosDesdeBaseDeDatos();

        // Convertimos la lista de países en un formato adecuado para JavaScript
        for (int i = 0; i < paises.size(); i++) {
            if (i > 0) lista += ", ";
            lista += "'" + paises.get(i) + "'";  // Se agrega cada país entre comillas simples
        }

        lista += "]";
        return lista;
    }

    // Método auxiliar para obtener los países desde la base de datos
    private static List<String> obtenerDeptosDesdeBaseDeDatos() {
        List<String> paises = new ArrayList<>();
        String sql = "SELECT nombre FROM generalDepartamentos ORDER BY nombre";  // La consulta SQL para obtener los países

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
