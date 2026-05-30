public class Camion {
    private int idC;
    private String patente;
    private boolean refrigerado;
    private double capacidadKg;

    public Camion(int idC, String patente, boolean refrigerado, double capacidadKg) {
        this.idC = idC;
        this.patente = patente;
        this.refrigerado = refrigerado;
        this.capacidadKg = capacidadKg;
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
}
