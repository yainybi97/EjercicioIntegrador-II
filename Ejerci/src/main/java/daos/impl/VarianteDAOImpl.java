package daos.impl;
import daos.IVariante;
import model.AccesoConexion;
import model.Variante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public class VarianteDAOImpl implements IVariante {

    private Connection conn = AccesoConexion.getConnection();


    /**
     * @param variante
     */
    @Override
    public void insert(Variante variante) {
        PreparedStatement sentencia = null;
        String consulta = "INSERT INTO Variante  VALUES (null, ?, ?)";
        try {


            sentencia = this.conn.prepareStatement(consulta , PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, variante.getDescripcion());
            sentencia.setInt(2, variante.getPrecio());


            int resultado = sentencia.executeUpdate();
            System.out.println(isInsert(resultado));

            ResultSet rs = sentencia.getGeneratedKeys();
            while (rs.next()) {
                int claveAsignada = rs.getInt(1);
                variante.setId(claveAsignada);
            }

            rs.close();
            sentencia.close();

        } catch (Exception e) {
            System.out.println("Error: Clase VariateDaoImpl, método insert");
            e.printStackTrace();
        }
    }


    /**
     * @param variante
     */
    @Override
    public void update(Variante variante) {
        PreparedStatement sentencia = null;
        String consulta = "UPDATE Variante SET Descripcion = ?, Precio = ? WHERE Id = ?";

        try {
            sentencia = this.conn.prepareStatement(consulta);
            sentencia.setString(1, variante.getDescripcion());
            sentencia.setInt(2, variante.getPrecio());
            sentencia.setInt(3, variante.getId());

            int resultado = sentencia.executeUpdate();
            System.out.println(isUpdate(resultado));

            sentencia.close();
        } catch (Exception ex) {
            System.out.println("Error: Clase VariateDaoImpl, método update");
            ex.printStackTrace();
        }
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String consulta = "DELETE FROM Variante  WHERE Id =" + id;

        try {
            PreparedStatement sentencia = this.conn.prepareStatement(consulta);

            int resultado = sentencia.executeUpdate(consulta);
            System.out.println(isDelete(resultado));
            sentencia.close();

        } catch (Exception ex) {
            System.out.println("Error: Clase VariateDaoImpl, método delete");
            ex.printStackTrace();
        }
    }

    /**
     * @param id
     * @return Variante
     */
    @Override
    public Variante getQuery(Integer id) {
        Variante variante = null;
        String consulta = "SELECT * FROM Variante WHERE Id = " + id;
        try {

            PreparedStatement sentencia = this.conn.prepareStatement(consulta);
            ResultSet rs = sentencia.executeQuery(consulta);

            if (rs.next()) {
                variante = new Variante();
                variante.setId(rs.getInt("Id"));
                variante.setDescripcion(rs.getString("Descripcion"));
                variante.setPrecio(rs.getInt("Precio"));

            }

        } catch (Exception ex) {
            System.out.println("Error: Clase VariateDaoImpl, método getQuery");
            ex.printStackTrace();
        }
        return variante;
    }

    /**
     *
     * @return List<Variante></>
     */
    @Override
    public List<Variante> getVariantes() {
        String consultar = "SELECT * FROM Variante ORDER BY ID";
        List<Variante> variantes = new ArrayList<Variante>();

        try {
            Statement sentencia = this.conn.createStatement();
            ResultSet rs = sentencia.executeQuery(consultar);

            while (rs.next()) {
                Variante variante = new Variante();
                variante.setId(rs.getInt("Id"));
                variante.setDescripcion(rs.getString("Descripcion"));
                variante.setPrecio(rs.getInt("Precio"));
                variantes.add(variante);
            }
            rs.close();
            sentencia.close();

        }catch (Exception ex) {
            System.out.println("Error: Clase VariateDaoImpl, método getVarinates");
            ex.printStackTrace();
        }
        return variantes;
    }


    private String isDelete(int resultado) {
        return (resultado > 0)? "ELIMINADO" : "NO ELIMINADO";
    }

    private String isInsert(int resultado) {
        return (resultado > 0)? "INSERTADO" : "NO INSERTADO";
    }

    private String isUpdate(int resultado) {
        return (resultado > 0)? "MODIFICADO" : "NO MODIFICADO";
    }


    public void imprimirVariantes(List<Variante> variantes) {
        for (Variante variante: variantes) {
            System.out.println("Id \t" + variante.getId() + " Descrip \t" + variante.getDescripcion() + " $\t" + variante.getPrecio());
        }
    }

    public void imprimirVariante(Variante variante) {
        System.out.println("Id \t" + variante.getId() + " Descrip \t" + variante.getDescripcion() + " $\t" + variante.getPrecio());
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
