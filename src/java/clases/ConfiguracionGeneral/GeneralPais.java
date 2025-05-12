package clases.ConfiguracionGeneral;

import clasesDataBase.ConectorBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GeneralPais {

    public static String getListaEnArregloJs() {
        String lista = "[";
        List<String> paises = obtenerPaisesDesdeBaseDeDatos();
        for (int i = 0; i < paises.size(); i++) {
            if (i > 0) {
                lista += ", ";
            }
            lista += "'" + paises.get(i) + "'";
        }
        lista += "]";
        return lista;
    }

    private static List<String> obtenerPaisesDesdeBaseDeDatos() {
        List<String> paises = new ArrayList<>();
        String sql = "SELECT nombre FROM generalPais ORDER BY nombre";

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
            e.printStackTrace();
        } finally {
            ConectorBD.cerrarConexion();
        }
        return options.toString();
    }

    public static String getListaNombres() {
        StringBuilder nombres = new StringBuilder();
        String sql = "SELECT nombre FROM generalPais ORDER BY nombre";

        try (PreparedStatement stmt = ConectorBD.getConnection().prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                nombres.append("\"").append(nombre).append("\",");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConectorBD.cerrarConexion();
        }

        if (nombres.length() > 0) {
            nombres.setLength(nombres.length() - 1);
        }

        return nombres.toString();
    }
}
