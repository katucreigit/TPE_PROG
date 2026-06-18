import java.util.ArrayList;

public class Main {
        public static void main(String[] args) {
            
            Servicios servicios = new Servicios("./Camiones.csv","./Paquetes.csv");

            System.out.println("SERVICIO 1");
            Paquete p = servicios.servicio1("P002");
    
            if (p != null) {
                System.out.println("Paquete encontrado:");
                System.out.println(p.getId() + " - " + p.getCodigoId() + " - " + p.getPeso() + "kg");
            }

            System.out.println("SERVICIO 2");
            for (Paquete paq : servicios.servicio2(true)) {
                System.out.println(paq.getCodigoId() +" - alimentos: " +paq.getTieneAlimentos());
            }

            System.out.println("SERVICIO 3");

            for (Paquete paq : servicios.servicio3(12, 100)) {
                System.out.println(paq.getCodigoId() +" - urgencia: " +paq.getValorUrgencia());
            }

            Backtracking bt = new Backtracking();
            bt.asignarPaquetesBackTracking(new ArrayList<>(servicios.copiaCamiones()),new ArrayList<>(servicios.copiaPaquetes()));
            bt.mostrarSolucion();

            Greedy greedy = new Greedy();
            ArrayList<Camion> camionesGreedy = servicios.copiaCamiones();
    
            greedy.asignarPaquetesGreedy(camionesGreedy,servicios.copiaPaquetes());
    
            greedy.mostrarSolucion(camionesGreedy);
        }
}
