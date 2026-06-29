import java.util.ArrayList;
import java.util.List;

public class Greedy{
    private double pesoNoAsignado;
    private int candidatosConsiderados;

    /*
    Estrategia: consiste en ordenar inicialmente el conjunto de paquetes de mayor a menor peso
    como criterio. DEspues se recorre secuencialmente la lista ya ordenada, seleccionando 
    en cada paso el paquete más pesado disponible y buscando asignarlo al primer camión que 
    cumpla con las restricciones de capacidad de carga y refrigeración. Una vez que se toma una
    decisión, no se modifica posteriormente. La idea de este criterio es priorizar la asignación 
    de los paquetes más pesados al principio del algoritmo, ya que son los más difíciles de ubicar
    y los que más impactan en el peso total no asignado en caso de quedar fuera de la solución.
    */

    public void asignarPaquetesGreedy(List<Camion> camiones, List<Paquete> paquetes) {
        this.pesoNoAsignado = 0;
        this.candidatosConsiderados = 0;
        List<Paquete> candidatos = new ArrayList<>(paquetes);

        candidatos.sort((p1, p2) -> Double.compare(p2.getPeso(), p1.getPeso()));

        for(Paquete p : candidatos) {

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
