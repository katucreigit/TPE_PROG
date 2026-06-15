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
    
    //Expresar la complejidad temporal del constructor
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

                // lee una línea completa
                String linea = scanner.nextLine();
                // separa por ;
                String[] datos = linea.split(";");

                // convierte tipos
                int idP = Integer.parseInt(datos[0]);
                String codigoId = datos[1];
                double peso = Double.parseDouble(datos[2]);
                boolean tieneAlimentos = datos[3].equals("1");
                int valorUrgencia = Integer.parseInt(datos[4]);

                // crea objeto
                Paquete p = new Paquete(idP, codigoId, peso, tieneAlimentos, valorUrgencia);

                // guarda objeto
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
            // saltea la primera línea (cantidad total)
            scanner.nextLine();
            // mientras haya líneas
            while(scanner.hasNextLine()) {

                // lee una línea completa
                String linea = scanner.nextLine();
                // separa por ;
                String[] datos = linea.split(";");

                // convierte tipos
                int idC = Integer.parseInt(datos[0]);
                String patente = datos[1];
                boolean refrigerado = datos[2].equals("1");
                double capacidadKg = Double.parseDouble(datos[3]);

                // crea objeto
                Camion c = new Camion(idC, patente, refrigerado, capacidadKg);

                // guarda objeto
                camiones.add(c);
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("No se encontró el archivo de camión");
        }
    }
    
    //Expresar la complejidad temporal del servicio 1.
    public Paquete servicio1(String codigoPaquete) {
        return paquetesPorCodigo.get(codigoPaquete);
    }
    
    //Expresar la complejidad temporal del servicio 2.
    public List<Paquete> servicio2(boolean contieneAlimentos) {
        return paquetesPorAlimentos.get(contieneAlimentos);
    }
    
    //Expresar la complejidad temporal del servicio 3.
    public List<Paquete> servicio3(int urgenciaMinima, int urgenciaMaxima) {
        List<Paquete> resultado = new ArrayList<>();

        for (Paquete p : paquetes) {
            if (p.valorUrgencia() >= urgenciaMinima && p.valorUrgencia() <= urgenciaMaxima) {
                resultado.add(p);
            }
        }
        return resultado;
    }
}