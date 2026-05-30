public class Camion {
    private int id;
    private String patente;
    private boolean refrigerado;
    private double capacidadKg;

    public Camion(int id, String patente, boolean refrigerado, double capacidadKg) {
        this.id = id;
        this.patente = patente;
        this.refrigerado = refrigerado;
        this.capacidadKg = capacidadKg;
    }

    public int getId() {
        return id;
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
