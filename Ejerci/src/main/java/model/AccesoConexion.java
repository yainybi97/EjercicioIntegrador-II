package model;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public class AccesoConexion {

    private final static String url = "jdbc:mysql://localhost:3306/Fabrica_Autos";
    private final static String usuario = "yainybi";
    private final static String clave = "123456";


    public static Connection conexion = null;



    public static Connection getConnection() {
        try {
            if (conexion == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, clave);
                System.out.println("Conectado");
            }

        } catch (Exception e) {
            System.out.println("Conexion fallida");
            e.printStackTrace();
        }
        return conexion;
    }



    public static void closeConnection(Connection conexion){
        try {
            conexion.close();
            System.out.println("Desconectado");
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
