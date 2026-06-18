import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    private double menorPesoNoAsignado;
    private double estadosGenerados;
    private List<Camion> mejorSolucion;

    /*
    Estrategia: explorar todas las posibles asignaciones de paquetes a camiones.
    Para cada paquete se consideran todas las alternativas válidas: asignarlo a cualquiera de los 
    camiones que cumplan las restricciones de capacidad y refrigeración,o dejarlo sin asignar.
    El algoritmo recorre recursivamente todas las combinaciones posibles y se queda aquella que minimiza 
    el peso total de los paquetes no asignados. Además utilizamos una poda para evitar explorar ramas que 
    ya poseen un peso no asignado igual o mayor al de la mejor solución encontrada hasta ese momento.
    */

    public void asignarPaquetesBackTracking(List<Camion> camiones, List<Paquete> paquetes) {
        this.menorPesoNoAsignado = Double.MAX_VALUE;
        this.estadosGenerados = 0;
        this.mejorSolucion = new ArrayList<>();

        backtracking(0, (List<Paquete>) paquetes, (List<Camion>) camiones, 0);
    }

    private void backtracking(int i, List<Paquete> paquetes, List<Camion> camiones, double pesoNoAsignadoActual) {

        estadosGenerados++;

        if (pesoNoAsignadoActual >= menorPesoNoAsignado) {
            return;
        }

        if (i == paquetes.size()) {
            if (pesoNoAsignadoActual < menorPesoNoAsignado) {
                menorPesoNoAsignado = pesoNoAsignadoActual;
                guardarSolucion(camiones);
            }
            return;
        }

        Paquete p = paquetes.get(i);

        for (Camion c : camiones) {
            if (c.puedeCargar(p)) {
                c.agregarPaquete(p);
                backtracking(i + 1,paquetes, camiones,pesoNoAsignadoActual);
                c.eliminarPaquete(p);
            }
        }

        backtracking(i + 1, paquetes, camiones, pesoNoAsignadoActual + p.getPeso());
    }

    private void guardarSolucion(List<Camion> camiones) {
        mejorSolucion = new ArrayList<>();
    
        for (Camion c : camiones) {
            Camion copia = new Camion(c.getId(),c.getPatente(),c.isRefrigerado(),c.getCapacidadKg());
    
            for (Paquete p : c.getPaquetes()) {
                copia.agregarPaquete(p);
            }
            mejorSolucion.add(copia);
        }
    }
    public void mostrarSolucion() {
        System.out.println("Backtracking");
        System.out.println("Solución obtenida:");
        for (Camion c : mejorSolucion) {
            System.out.println("Camión ID: " + c.getId());
            System.out.print("Paquetes: ");
            for (Paquete p : c.getPaquetes()) {
                System.out.print("[" + p.getId() + ", " + p.getPeso() + " kg] ");
            }
            System.out.println();
        }
        System.out.println("Peso no asignado: " + menorPesoNoAsignado + " kg");
        System.out.println("Cantidad de estados generados: " + estadosGenerados);
    }
}