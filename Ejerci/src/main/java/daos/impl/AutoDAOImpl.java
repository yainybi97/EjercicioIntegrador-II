package daos.impl;

import daos.IAutoDAO;
import model.AccesoConexion;
import model.Auto;

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
public class AutoDAOImpl implements IAutoDAO {

    Connection conn = AccesoConexion.getConnection();

    /**
     *
     * @param auto
     */
    @Override
    public void insert(Auto auto) {
        PreparedStatement sentencia = null;
        String consulta = "INSERT INTO Auto VALUES (null, ?, ?, ?)";
        try {
            sentencia = this.conn.prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setInt(1, auto.getPrecioFinal());
            sentencia.setInt(2, auto.getOpcionales().size());
            sentencia.setInt(3, auto.getVariante().getId());


            int resultado = sentencia.executeUpdate();
            System.out.println(isInsert(resultado));

            ResultSet rs = sentencia.getGeneratedKeys();
            while (rs.next()) {
                int claveAsignada = rs.getInt(1);
                auto.setId(claveAsignada);
            }

            rs.close();
            sentencia.close();

        } catch (Exception e) {
            System.out.println("Error: Clase AutoDaoImpl, método insert");
            e.printStackTrace();
        }
    }


    /**
     *
     * @param auto
     */
    @Override
    public void update(Auto auto) {
        PreparedStatement sentencia = null;
        String consulta = "UPDATE Auto SET Precio = ?, NroOpciones = ? , IdVariante = ? WHERE Id = ?";

        try {
            sentencia = this.conn.prepareStatement(consulta);
            sentencia.setInt(1, auto.getPrecioFinal());
            sentencia.setInt(2, auto.getOpcionales().size());
            sentencia.setInt(3, auto.getVariante().getId());
            sentencia.setInt(4, auto.getId());


            int resultado = sentencia.executeUpdate();
            System.out.println(isUpdate(resultado));

            sentencia.close();
        } catch (Exception ex) {
            System.out.println("Error: Clase AutoDaoImple, método update");
            ex.printStackTrace();
        }

    }

    /**
     *
     * @param id
     */
    @Override
    public void delete(Integer id) {
        String consulta = "DELETE FROM Auto  WHERE Id =" + id;

        try {
            PreparedStatement sentencia = this.conn.prepareStatement(consulta);

            int resultado = sentencia.executeUpdate(consulta);
            System.out.println(isDelete(resultado));
            sentencia.close();

        } catch (Exception ex) {
            System.out.println("Error: Clase AutoDaoImple, método delete");
            ex.printStackTrace();
        }
    }


    /**
     *
     * @param id
     * @return
     */
    @Override
    public Auto getQuery(Integer id) {
        Auto auto = null;
        String consulta = "SELECT * FROM Auto WHERE Id = " + id;
        VarianteDAOImpl variante = new VarianteDAOImpl();

        try {

            PreparedStatement sentencia = this.conn.prepareStatement(consulta);
            ResultSet rs = sentencia.executeQuery(consulta);

            if (rs.next()) {
                auto = new Auto();
                auto.setId(rs.getInt("Id"));
                auto.setVariante(variante.getQuery(rs.getInt("IdVariante")));
                auto.setPrecioFinal(rs.getInt("Precio"));

            }

        } catch (Exception ex) {
            System.out.println("Error: Clase AutoDaoImple, método getQuery");
            ex.printStackTrace();
        }
        return auto;
    }


    /**
     *
     * @return
     */

    @Override
    public List<Auto> getAutos() {
        String consultar = "SELECT * FROM Auto";
        List<Auto> autos = new ArrayList<Auto>();
        VarianteDAOImpl variante = new VarianteDAOImpl();


        try {
            Statement sentencia = this.conn.createStatement();
            ResultSet rs = sentencia.executeQuery(consultar);

            while (rs.next()) {

                Auto auto = new Auto();
                auto.setId(rs.getInt("Id"));
                auto.setVariante(variante.getQuery(rs.getInt("IdVariante")));
                auto.setNroOpciones(rs.getInt("NroOpciones"));
                auto.setPrecioFinal(rs.getInt("Precio"));
                autos.add(auto);
            }
            rs.close();
            sentencia.close();

        }catch (Exception ex) {
            System.out.println("Error: Clase AutoDaoImple, método getAutos");
            ex.printStackTrace();
        }
        return autos;
    }


    /**
     *
     * @param autos
     * @return
     */

    @Override
    public List<Auto> imprimirAutos(List<Auto> autos) {
        for (Auto auto: autos) {
            System.out.println("Id " + auto.getId() + " \t IdVar " + auto.getVariante().getId() + " \t N°Opci " + auto.getNroOpciones() + " \t $ " + auto.getPrecioFinal());
        }
        return autos;
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

}
