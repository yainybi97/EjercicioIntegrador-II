import daos.impl.*;

/**
 * Creado por @author: YainyBi
 * el 28/10/20
 **/
public class Main {
    public static void main(String[] args) {
        VarianteDAOImpl variImpl = new VarianteDAOImpl();
        OpcionDAOImpl opcImpl = new OpcionDAOImpl();
        AutoDAOImpl autoImpl = new AutoDAOImpl();


        //autoImpl.imprimirAutos(autoImpl.getAutos());
        //opcImpl.imprimirOpcions(opcImpl.getOpcions());
        //variImpl.imprimirVariantes(variImpl.getVariantes());
    }
}
