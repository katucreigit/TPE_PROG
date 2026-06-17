import java.util.ArrayList;
import java.util.List;

public class Backtracking {

    private double mejorPesoNoAsignado;
    private double estadosGenerados;
    private List<Camion> mejorSolucion;

    public void asignarPaquetesBackTracking(List<Camion> camiones, List<Paquete> paquetes) {
        // Reiniciamos los estados de las variables globales antes de empezar la recursión
        this.mejorPesoNoAsignado = Double.MAX_VALUE;
        this.estadosGenerados = 0;
        this.mejorSolucion = new ArrayList<>();

        // Lanzamos el algoritmo recursivo
        backtracking(0, (List<Paquete>) paquetes, (List<Camion>) camiones, 0);
    }

    private void backtracking(int i, List<Paquete> paquetes, List<Camion> camiones, double pesoNoAsignadoActual) {

        estadosGenerados++;

        if (pesoNoAsignadoActual >= mejorPesoNoAsignado) {
            return;
        }

        if (i == paquetes.size()) {
            if (pesoNoAsignadoActual < mejorPesoNoAsignado) {
                mejorPesoNoAsignado = pesoNoAsignadoActual;
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
            System.out.println("Camión ID: " + c.getId() + ", Patente: " + c.getPatente());
            System.out.print("Paquetes: ");
            for (Paquete p : c.getPaquetes()) {
                System.out.print("[" + p.getId() + ", " + p.getPeso() + " kg] ");
            }
            System.out.println();
        }
        System.out.println("Peso no asignado: " + mejorPesoNoAsignado + " kg");
        System.out.println("Cantidad de estados generados: " + estadosGenerados);
    }
}