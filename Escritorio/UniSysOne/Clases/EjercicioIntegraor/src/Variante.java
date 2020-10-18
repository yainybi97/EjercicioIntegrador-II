/**
 * Creado por @author: YainyBi
 * el 16/10/20
 **/
public class Variante {
    private String nombre;
    private int precio;

    public Variante(String nombre, int precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
