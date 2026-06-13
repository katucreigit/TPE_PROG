import java.util.*;

public class Backtracking {

    private int mejorPesoNoAsignado = Integer.MAX_VALUE;

    private int estadosGenerados = 0;

    public void resolver(ArrayList<Camion> camiones, ArrayList<Paquete> paquetes) {

        backtracking(0, paquetes, camiones, 0);

        System.out.println("Mejor peso no asignado: " + mejorPesoNoAsignado);

        System.out.println("Estados generados: " + estadosGenerados);
    }

    private void backtracking(int index, ArrayList<Paquete> paquetes, ArrayList<Camion> camiones, int pesoNoAsignadoActual) {

        estadosGenerados++;

        if (index == paquetes.size()) {

            if (pesoNoAsignadoActual < mejorPesoNoAsignado) {
                mejorPesoNoAsignado = pesoNoAsignadoActual;
            }

            return;
        }

        Paquete p = paquetes.get(index);

        for (Camion c : camiones) {

            if (c.puedeCargar(p)) {

                c.agregarPaquete(p);

                backtracking(index + 1,paquetes, camiones,pesoNoAsignadoActual);

                c.quitarPaquete(p);
            }
        }

        backtracking(index + 1, paquetes, camiones, pesoNoAsignadoActual + p.getPeso());
    }
}