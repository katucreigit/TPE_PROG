import java.util.ArrayList;
import java.util.List;

public class Greedy{
    private double pesoNoAsignado;
    private int candidatosConsiderados;

    /*
    Estrategia: consiste en seleccionar en cada paso el paquete de mayor peso entre los 
    que aún no fueron procesados. DEspues se intenta asignarlo al primer camión que cumpla 
    las restricciones de capacidad y refrigeración. Una vez que se toma una decisión, no se 
    modifica posteriormente. La idea de este criterio es priorizar la asignación de los 
    paquetes más pesados, ya que son los más difíciles de ubicar y los que más impactan en el 
    peso total no asignado en caso de quedar fuera de la solución.
    */

    public void asignarPaquetesGreedy(List<Camion> camiones, List<Paquete> paquetes) {
        this.pesoNoAsignado = 0;
        this.candidatosConsiderados = 0;
        List<Paquete> candidatos = new ArrayList<>(paquetes);

        while (!candidatos.isEmpty()) {
            
            Paquete p = seleccionarPaqueteMasPesado(candidatos);
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

    private Paquete seleccionarPaqueteMasPesado(List<Paquete> candidatos) {

        if (candidatos.isEmpty()) {
            return null;
        }

        Paquete mayorPaquete = null;
        double mayorPeso= 0;

        for (Paquete p : candidatos) {
            if (p.getPeso() > mayorPeso) {
                mayorPaquete = p;
                mayorPeso= p.getPeso();
            }
        }

        return mayorPaquete;
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
