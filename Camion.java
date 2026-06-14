import java.util.ArrayList;

public class Camion {
    private int idC;
    private String patente;
    private boolean refrigerado;
    private double capacidadKg;
    private double cargaActual;
    private ArrayList<Paquete> paquetes;

    public Camion(int idC, String patente, boolean refrigerado, double capacidadKg) {
        this.idC = idC;
        this.patente = patente;
        this.refrigerado = refrigerado;
        this.capacidadKg = capacidadKg;
        this.cargaActual= 0;
        this.paquetes= new ArrayList<>();
    }

    public boolean puedeCargar(Paquete p){

        if (p.getTieneAlimentos() && !isRefrigerado()) {
            return false;
        }
        return (cargaActual + p.getPeso()) <= capacidadKg;
    }

    public void agregarPaquete(Paquete p) {
        paquetes.add(p);
        cargaActual += p.getPeso();
    }

    public void eliminarPaquete(Paquete p) {
        paquetes.remove(p);
        cargaActual -= p.getPeso();
    }

    public int getId() {
        return idC;
    }

    public String getPatente() {
        return patente;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public double getCapacidadKg() {
        return capacidadKg;
    }

    public ArrayList<Paquete> getPaquetes() {
        return paquetes;
    }
    
    public double getCargaActual() {
        return cargaActual;
    }
}
