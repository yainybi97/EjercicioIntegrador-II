package daos;

import model.Variante;

import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public interface IVariante {

    public void insert(Variante variante);
    public void update(Variante variante);
    public void delete(Integer id);
    public Variante getQuery(Integer id);
    public List<Variante> getVariantes();
}
