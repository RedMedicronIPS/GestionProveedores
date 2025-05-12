/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases.ConfiguracionGeneral;

import clases.ConfiguracionGeneral.GeneralTipoUsuario;
import clasesDataBase.ConectorBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IPS OBRERO
 */
public class GeneralTipoUsuario {

    private String codigo;

    public GeneralTipoUsuario(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String obtenerMenuOpcionesRolUsuario(int idRol) {
        StringBuilder menuRoles = new StringBuilder();
        menuRoles.append("<ul class='menu'>");
        String sqlMenu = "SELECT gm.id, gm.menu_nombre "
                + "FROM generalMenu gm "
                + "INNER JOIN generalMenuOpcion gmo ON gmo.id_generales_menu = gm.id "
                + "WHERE (SELECT TOP 1 SUBSTRING(rol_opciones, opcion_consecutivo, 1) "
                + "FROM generalUsuarioRolOpcion WHERE id_rol = ? ) = 1 "
                + "GROUP BY gm.id, gm.menu_nombre "
                + "ORDER BY gm.id";

        String sqlSubMenu = "SELECT gmo.opcion_nombre, gmo.opcion_destino, gmo.opcion_icono "
                + "FROM generalMenuOpcion gmo "
                + "WHERE (SELECT TOP 1 SUBSTRING(rol_opciones, opcion_consecutivo, 1) "
                + "       FROM generalUsuarioRolOpcion WHERE id_rol = ? ) = 1 "
                + "AND id_generales_menu = ?";

        try (Connection conexion = ConectorBD.getConnection();
                PreparedStatement stmtMenu = conexion.prepareStatement(sqlMenu)) {

            stmtMenu.setInt(1, idRol);

            try (ResultSet rsMenu = stmtMenu.executeQuery()) {
                while (rsMenu.next()) {
                    int idMenu = rsMenu.getInt("id");
                    String nombreMenu = rsMenu.getString("menu_nombre");
                    menuRoles.append("<a href='#' class='toggle-menu submenu-title'><i class='bi bi-folder'></i>&nbsp;&nbsp;")
                            .append(nombreMenu)
                            .append(" <i class='bi bi-chevron-down float-end'></i></a>");
                    try (PreparedStatement stmtSubMenu = conexion.prepareStatement(sqlSubMenu)) {
                        stmtSubMenu.setInt(1, idRol);
                        stmtSubMenu.setInt(2, idMenu);

                        try (ResultSet rsSubMenu = stmtSubMenu.executeQuery()) {
                            menuRoles.append("<div class='submenu'>");

                            while (rsSubMenu.next()) {
                                String nombreOpcion = rsSubMenu.getString("opcion_nombre");
                                String opcionDestino = rsSubMenu.getString("opcion_destino");
                                String opcionIcono = rsSubMenu.getString("opcion_icono");
                                menuRoles.append("<li><a href='main.jsp?CONTENIDO=")
                                        .append(opcionDestino)
                                        .append("' class='dropdown-item'><i class='")
                                        .append(opcionIcono)
                                        .append("'></i>&nbsp;&nbsp;")
                                        .append(nombreOpcion)
                                        .append("</a></li>");
                            }
                            menuRoles.append("</div>");
                        }
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "<p>Error al generar el menú.</p>";
        }
        menuRoles.append("</ul>");
        return menuRoles.toString();
    }

}
