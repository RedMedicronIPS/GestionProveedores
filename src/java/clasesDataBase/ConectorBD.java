package clasesDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConectorBD {

    private String servidor;
    private String puerto;
    private String usuario;
    private String clave;
    private String baseDatos;

    private static Connection conexion;

    public ConectorBD() {

        servidor = "192.168.59.207";
        puerto = "1433";
        usuario = "DbDesarrollo";
        clave = "TI.Desarrollo%*2024";
        baseDatos = "AppRedMedicronIPS_Dev";

    }

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Controlador cargado correctamente.");

            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                cerrarConexion();
                System.out.println("Aplicación cerrada. Conexión a la base de datos terminada de forma segura.");
            }));

        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el controlador de la base de datos: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if (conexion == null || conexion.isClosed()) {
                ConectorBD conector = new ConectorBD();
                conexion = conector.conectarBD();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener la conexión: " + ex.getMessage());
        }

        return conexion;
    }

    public Connection conectarBD() {
        Connection nuevaConexion = null;
        try {
            String cadenaConexion = "jdbc:sqlserver://" + servidor + ";databaseName=" + baseDatos
                    + ";user=" + usuario + ";password=" + clave + ";encrypt=false;trustServerCertificate=true;";

            nuevaConexion = DriverManager.getConnection(cadenaConexion);
            System.out.println("Conexión exitosa a la base de datos: " + baseDatos);

        } catch (SQLException ex) {
            System.out.println("Error al conectarse a la base de datos: " + ex.getMessage());
        }

        return nuevaConexion;
    }

    public static void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
                System.out.println("Se ha desconectado de la base de datos correctamente.");
            } else {
                System.out.println("La conexion esta cerrada.");
            }
        } catch (SQLException ex) {
            System.out.println("Error al desconectarse de la base de datos: " + ex.getMessage());
        }
    }

    public static void consultar(String cadenaSQL) {
        try (PreparedStatement sentencia = getConnection().prepareStatement(cadenaSQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                System.out.println("Columna1: " + resultado.getString(1));
            }

        } catch (SQLException ex) {
            System.out.println("Error en la cadena SQL " + cadenaSQL + ": " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static boolean ejecutarQuery(String cadenaSQL, Object... params) {
        boolean resultado = false;

        try (PreparedStatement sentencia = getConnection().prepareStatement(cadenaSQL)) {

            for (int i = 0; i < params.length; i++) {
                sentencia.setObject(i + 1, params[i]);
            }
            sentencia.execute();
            resultado = true;
            System.out.println("Consulta ejecutada correctamente.");

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta SQL: " + cadenaSQL + ": " + ex.getMessage());
        } finally {
            cerrarConexion();
        }

        return resultado;
    }

    public static void ejecutarConsultaConParametros(String cadenaSQL, Object... params) {
        try (PreparedStatement sentencia = getConnection().prepareStatement(cadenaSQL)) {

            for (int i = 0; i < params.length; i++) {
                sentencia.setObject(i + 1, params[i]);
            }

            try (ResultSet resultado = sentencia.executeQuery()) {
                while (resultado.next()) {
                    System.out.println("Columna1: " + resultado.getString(1));
                }
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar la consulta SQL con parámetros: " + cadenaSQL + ": " + ex.getMessage());
        } finally {
            cerrarConexion();
        }
    }

    public static void guardarRegistro(String nombreArchivo, String ruta) {
        String sql = "INSERT INTO Archivos (nombre, ruta) VALUES (?, ?)";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, nombreArchivo);
            ps.setString(2, ruta);
            ps.executeUpdate();
            System.out.println("Registro guardado en la base de datos.");
        } catch (SQLException e) {
            System.out.println("Error al guardar en la base de datos: " + e.getMessage());
        }
    }
}
