package model;
import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public class Auto {
    private Integer id;
    private Integer precioFinal;
    private Variante variante;
    private List<Opcion> opcionales;
    private Integer nroOpciones;


    public Auto() {
        this.opcionales = new ArrayList<Opcion>();
        this.precioFinal = 0;
    }

    public void agregarOpcion(Opcion opcion) {
        this.opcionales.add(opcion);
    }

    public Integer getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(Integer precioFinal) {

        this.precioFinal = precioFinal;
    }

    public Variante getVariante() {

        return variante;
    }

    public void  setVariante(Variante variante) {
        this.variante = variante;
    }

    public Integer getId() {
        return id;
    }

    public Auto setId(Integer id) {
        this.id = id;
        return this;
    }

    public List<Opcion> getOpcionales() {
        return opcionales;
    }

    public void setOpcionales(List<Opcion> opcionales) {
        this.opcionales = opcionales;
    }

    public Integer getNroOpciones() {
        return nroOpciones;
    }

    public void setNroOpciones(Integer nroOpciones) {
        this.nroOpciones = nroOpciones;
    }

    private Integer calcularPrecioTotalDeOpcionales() {
        Integer precioTotal = 0;
        if(!this.opcionales.isEmpty()) {
            for (Integer i = 0; i < this.opcionales.size(); i++) {
                precioTotal = precioTotal + this.opcionales.get(i).getPrecio();
            }
        }
        return precioTotal;
    }


    public void calcularPrecioFinal() {
        Integer precioOpcionales = calcularPrecioTotalDeOpcionales();
        Integer precioVariante = this.variante.getPrecio();
        this.precioFinal = precioOpcionales + precioVariante;
    }
}
