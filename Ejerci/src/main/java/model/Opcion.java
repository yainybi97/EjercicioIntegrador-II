package model;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public class Opcion {

    private Integer id;
    private String descripcion;
    private Integer precio;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
