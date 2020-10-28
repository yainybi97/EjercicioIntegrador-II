package daos;

import model.Opcion;

import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public interface IOpcion {

    public void insert(Opcion opcion);
    public void update(Opcion opcion);
    public void delete(Integer id);
    public Opcion getQuery(Integer id);
    public List<Opcion> getOpcions();

}
