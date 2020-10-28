package daos.impl;

import daos.IOpcion;
import model.AccesoConexion;
import model.Opcion;

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
public class OpcionDAOImpl implements IOpcion {

    private Connection conn = AccesoConexion.getConnection();


    /**
     * @param opcion
     */
    @Override
    public void insert(Opcion opcion) {
        PreparedStatement sentencia = null;
        String consulta = "INSERT INTO Opcion  VALUES (null, ?, ?)";
        try {

            sentencia = this.conn.prepareStatement(consulta , PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, opcion.getDescripcion());
            sentencia.setInt(2, opcion.getPrecio());


            int resultado = sentencia.executeUpdate();
            System.out.println(isInsert(resultado));

            ResultSet rs = sentencia.getGeneratedKeys();
            while (rs.next()) {
                int claveAsignada = rs.getInt(1);
                opcion.setId(claveAsignada);
            }

            rs.close();
            sentencia.close();

        } catch (Exception e) {
            System.out.println("Error: Clase OpcionDaoImpl, método insert");
            e.printStackTrace();
        }
    }


    /**
     * @param opcion
     */
    @Override
    public void update(Opcion opcion) {
        PreparedStatement sentencia = null;
        String consulta = "UPDATE Opcion SET Descripcion = ?, Precio = ? WHERE Id = ?";

        try {
            sentencia = this.conn.prepareStatement(consulta);
            sentencia.setString(1, opcion.getDescripcion());
            sentencia.setInt(2, opcion.getPrecio());
            sentencia.setInt(3, opcion.getId());

            int resultado = sentencia.executeUpdate();
            System.out.println(isUpdate(resultado));

            sentencia.close();
        } catch (Exception ex) {
            System.out.println("Error: Clase OpcionDaoImpl, método update");
            ex.printStackTrace();
        }
    }

    /**
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String consulta = "DELETE FROM Opcion  WHERE Id =" + id;

        try {
            PreparedStatement sentencia = this.conn.prepareStatement(consulta);

            int resultado = sentencia.executeUpdate(consulta);
            System.out.println(isDelete(resultado));
            sentencia.close();

        } catch (Exception ex) {
            System.out.println("Error: Clase OpcionDaoImpl, método delete");
            ex.printStackTrace();
        }
    }

    /**
     * @param id
     * @return Opcion
     */
    @Override
    public Opcion getQuery(Integer id) {
        Opcion opcion = null;
        String consulta = "SELECT * FROM Opcion WHERE Id = " + id;
        try {

            PreparedStatement sentencia = this.conn.prepareStatement(consulta);
            ResultSet rs = sentencia.executeQuery(consulta);

            if (rs.next()) {
                opcion = new Opcion();
                opcion.setId(rs.getInt("Id"));
                opcion.setDescripcion(rs.getString("Descripcion"));
                opcion.setPrecio(rs.getInt("Precio"));

            }

        } catch (Exception ex) {
            System.out.println("Error: Clase OpcionteDaoImpl, método getQuery");
            ex.printStackTrace();
        }
        return opcion;
    }

    /**
     *
     * @return List<Opcion></>
     */
    @Override
    public List<Opcion> getOpcions() {
        String consultar = "SELECT * FROM Opcion ORDER BY ID";
        List<Opcion> opcions = new ArrayList<Opcion>();

        try {
            Statement sentencia = this.conn.createStatement();
            ResultSet rs = sentencia.executeQuery(consultar);

            while (rs.next()) {
                //Opcion opcion = new Opcion(rs.getString("Descripcion"), rs.getInt("Precio"), rs.getInt("Id"));
                Opcion opcion = new Opcion();
                opcion.setId(rs.getInt("Id"));
                opcion.setDescripcion(rs.getString("Descripcion"));
                opcion.setPrecio(rs.getInt("Precio"));

                opcions.add(opcion);
            }
            rs.close();
            sentencia.close();

        }catch (Exception ex) {
            System.out.println("Error: Clase OpcionDaoImpl, método getOpcions");
            ex.printStackTrace();
        }
        return opcions;
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

    public void imprimirOpcions(List<Opcion> opcions) {
        for (Opcion opcion: opcions) {
            System.out.println("Id \t" + opcion.getId() + " Descrip \t" + opcion.getDescripcion() + " $\t" + opcion.getPrecio());
        }
    }

    public void imprimirOpcion(Opcion opcion) {
        System.out.println("Id \t" + opcion.getId() + " Descrip \t" + opcion.getDescripcion() + " $\t" + opcion.getPrecio());
    }
}
