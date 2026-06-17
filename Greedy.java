import java.util.ArrayList;
import java.util.List;

public class Greedy{
    private double pesoNoAsignado;
    private int candidatosConsiderados;

    public void asignarPaquetesGreedy(List<Camion> camiones, List<Paquete> paquetes) {
        // SOLUCIÓN: Reiniciamos acá para asegurar que cada ejecución arranque de cero
        this.pesoNoAsignado = 0;
        this.candidatosConsiderados = 0;
        List<Paquete> candidatos = new ArrayList<>(paquetes);

        while (!candidatos.isEmpty()) {
            
            Paquete p = seleccionar(candidatos);
            candidatos.remove(p);

            boolean asignado = false;

            for (Camion c : camiones) {
                candidatosConsiderados++;

                if (c.puedeCargar(p)) {
                    c.agregarPaquete(p);
                    asignado = true;
                    break;
                }
            
                
            }
            if (!asignado) {
                pesoNoAsignado += p.getPeso();
            }
        }
    }

    private Paquete seleccionar(List<Paquete> candidatos) {

        if (candidatos.isEmpty()) {
            return null;
        }

        Paquete mejorPaquete = candidatos.get(0);

        for (Paquete p : candidatos) {
            if (p.getPeso() > mejorPaquete.getPeso()) {
                mejorPaquete = p;
            }
        }

        return mejorPaquete;
    }


    public void mostrarSolucion(List<Camion> camiones) {

        System.out.println("Greedy");
        System.out.println("Solución obtenida:");
        for (Camion c : camiones) {
            System.out.println("Camión ID: " + c.getId());
            System.out.print("Paquetes: ");
            for (Paquete p : c.getPaquetes()) {
                System.out.print("[" + p.getId() + ", " + p.getPeso() + " kg] ");
            }
            System.out.println();
        }

        System.out.println("Peso no asignado: " + pesoNoAsignado);
        System.out.println("Cantidad de candidatos considerados: " + candidatosConsiderados);
    }
}
