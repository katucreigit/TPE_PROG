import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
    
            Servicios servicios = new Servicios("/Camiones.csv","Camiones.csv");

            System.out.println("SERVICIO 1");

            Paquete p = servicios.servicio1("PKG001");
    
            if (p != null) {
                System.out.println("Paquete encontrado:");
                System.out.println(p.getId() + " - " + p.getCodigoId() + " - " + p.getPeso() + "kg");
            }

            System.out.println("SERVICIO 2");

            for (Paquete paq : servicios.servicio2(true)) {
                System.out.println(paq.getCodigoId() +" - alimentos: " +paq.getTieneAlimentos());
            }

            System.out.println("SERVICIO 3");

            for (Paquete paq : servicios.servicio3(5, 10)) {
                System.out.println(paq.getCodigoId() +" - urgencia: " +paq.getValorUrgencia());
            }

            System.out.println("BACKTRACKING");

            Backtracking bt = new Backtracking();

            bt.asignarPaquetesBackTracking(
                    new ArrayList<>(servicios.copiaCamiones()),
                    new ArrayList<>(servicios.copiaPaquetes())
            );

            bt.mostrarSolucion();

            System.out.println("GREEDY");

            Greedy greedy = new Greedy();
    
            ArrayList<Camion> camionesGreedy =
                    new ArrayList<>(servicios.copiaCamiones());
    
            greedy.asignarPaquetesGreedy(
                    camionesGreedy,
                    new ArrayList<>(servicios.copiaPaquetes())
            );
    
            greedy.mostrarSolucion(camionesGreedy);
        }
}
