import java.util.ArrayList;

public class Backtracking {

    private double mejorPesoNoAsignado = Double.MAX_VALUE;

    private double estadosGenerados = 0;

    private ArrayList<Camion> mejorSolucion;

    public void asignarPaquetesBackTracking(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes) {

        backtracking(0, paquetes, camiones, 0);

        System.out.println("Mejor peso no asignado: " + mejorPesoNoAsignado);

        System.out.println("Estados generados: " + estadosGenerados);
    }

    private void backtracking(int i, ArrayList<Paquete> paquetes, ArrayList<Camion> camiones, double pesoNoAsignadoActual) {

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


    private void guardarSolucion(ArrayList<Camion> camiones) {

        mejorSolucion = new ArrayList<>();
    
        for (Camion c : camiones) {
    
            Camion copia = new Camion(c.getId(),c.getPatente(),c.isRefrigerado(),c.getCapacidadKg());
    
            for (Paquete p : c.getPaquetes()) {
                copia.agregarPaquete(p);
            }
    
            mejorSolucion.add(copia);
        }
    }
}