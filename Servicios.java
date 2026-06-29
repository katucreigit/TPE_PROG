import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Servicios {
    private List<Paquete> paquetes;
    private List<Camion> camiones;
    private HashMap<Boolean, List<Paquete>> paquetesPorAlimentos;
    private HashMap<String, Paquete> paquetesPorCodigo;
    
    // Complejidad temporal: O(P+C)
    // es lineal respecto de la cantidad de paquetes (P) y camiones (C) cargados desde los archivos.
    public Servicios(String pathCamiones, String pathPaquetes){
        paquetes = new ArrayList<>();
        camiones = new ArrayList<>();
        paquetesPorCodigo = new HashMap<>();
        paquetesPorAlimentos = new HashMap<>();

        paquetesPorAlimentos.put(true, new ArrayList<>());
        paquetesPorAlimentos.put(false, new ArrayList<>());

        cargarPaquetes(pathPaquetes);
        cargarCamiones(pathCamiones);
    }

    private void cargarPaquetes(String pathPaquetes){
        try {
            Scanner scanner = new Scanner(new File(pathPaquetes));
            scanner.nextLine();

            while(scanner.hasNextLine()) {

                String linea = scanner.nextLine();

                String[] datos = linea.split(";");

                int idP = Integer.parseInt(datos[0].trim());
                String codigoId = datos[1];
                double peso = Double.parseDouble(datos[2].trim());
                boolean tieneAlimentos = datos[3].equals("1");
                int valorUrgencia = Integer.parseInt(datos[4].trim());

                Paquete p = new Paquete(idP, codigoId, peso, tieneAlimentos, valorUrgencia);

                paquetes.add(p);
                paquetesPorCodigo.put(codigoId, p);
                paquetesPorAlimentos.get(tieneAlimentos).add(p);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de paquete");
        }
    }
    private void cargarCamiones(String pathCamiones){
        try {
            Scanner scanner = new Scanner(new File(pathCamiones));
            scanner.nextLine();
            
            while(scanner.hasNextLine()) {

                String linea = scanner.nextLine();

                String[] datos = linea.split(";");

                int idC = Integer.parseInt(datos[0].trim());
                String patente = datos[1];
                boolean refrigerado = datos[2].equals("1");
                double capacidadKg = Double.parseDouble(datos[3].trim());

                Camion c = new Camion(idC, patente, refrigerado, capacidadKg);

                camiones.add(c);
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo de camión");
        }
    }

    public ArrayList<Camion> copiaCamiones(){
        return new ArrayList<>(camiones);
    }

    public ArrayList<Paquete> copiaPaquetes(){
        return new ArrayList<>(paquetes);
    }
    
    // La complejidad temporal del SERVICIO 1 es O(n) debido a las posibles colisiones
    public Paquete servicio1(String codigoPaquete) {
        return paquetesPorCodigo.get(codigoPaquete);
    }
    
    //La complejidad temporal del SERVICIO 2 es O(n) debido a las posibles colisiones
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return paquetesPorAlimentos.get(contieneAlimentos);
    }
    
    //La complejidad temporal del SERVICIO 3 es O(P) donde P es la cantidad total de paquetes.
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();

        for (Paquete p : paquetes) {
            if (p.getValorUrgencia() >= urgenciaMinima && p.getValorUrgencia() <= urgenciaMaxima) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}