import java.util.ArrayList;
import java.util.List;

/**
 * Creado por @author: YainyBi
 * el 16/10/20
 **/
public class Auto {
    private int precioFinal;
    private Variante variante;
    private ArrayList<Opcion> opcionales;

    public Auto(Variante variante) {
        this.precioFinal = 0;
        this.variante = variante;
        this.opcionales = new ArrayList<Opcion>();
    }

    public void agregarOpcion(Opcion opcion) {
        this.opcionales.add(opcion);
    }

    public int getPrecioFinal() {

        return precioFinal;
    }

    public void setPrecioFinal(int precioFinal) {

        this.precioFinal = precioFinal;
    }

    public Variante getVariante() {

        return variante;
    }

    public void setVariante(Variante variante) {

        this.variante = variante;
    }

    public ArrayList<Opcion> getOpcionales() {
        return opcionales;
    }

    public void setOpcionales(ArrayList<Opcion> opcionales) {
        this.opcionales = opcionales;
    }

    private int calcularPrecioTotalDeOpcionales() {
        int precioTotal = 0;
        if(!this.opcionales.isEmpty()) {
            for (int i = 0; i < this.opcionales.size(); i++) {
                precioTotal = precioTotal + this.opcionales.get(i).getPrecio();
            }
        }
        return precioTotal;
    }

    public void calcularPrecioFinal() {
        int precioOpcionales = calcularPrecioTotalDeOpcionales();
        int precioVariante = this.variante.getPrecio();
        this.precioFinal = precioOpcionales + precioVariante;
        System.out.println("Auto de Variante " + this.variante.getNombre() + " con "+ this.opcionales.size() + " opcionales" + " Precio Final " + this.precioFinal);
    }

}
