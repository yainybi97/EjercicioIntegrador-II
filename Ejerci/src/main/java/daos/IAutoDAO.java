package daos;

import model.Auto;

import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public interface IAutoDAO {

    public void insert(Auto auto);
    public void update(Auto auto);
    public void delete(Integer id);
    public Auto getQuery(Integer id);
    public List<Auto> imprimirAutos(List<Auto> autos);
    public List<Auto> getAutos();

}
