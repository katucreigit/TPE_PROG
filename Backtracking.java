import java.util.ArrayList;

public class Backtracking {

    private double mejorPesoNoAsignado = Integer.MAX_VALUE;

    private double estadosGenerados = 0;

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
}