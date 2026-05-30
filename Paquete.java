public class Paquete{
    private int id;
    private String codigoId;
    private double peso;
    private boolean tieneAlimentos;
    private int valorUrgencia;

    public Paquete(int id, String codigoId, double peso, boolean tieneAlimentos, int valorUrgencia){
        this.id= id;
        this.codigoId= codigoId;
        this.peso= peso;
        this.tieneAlimentos= tieneAlimentos;
        this.valorUrgencia= valorUrgencia;
    }

    public int getId(){
        return this.id;
    }
    public String getCodigoId(){
        return this.codigoId;
    }
    public double getPeso(){
        return this.peso;
    }
    public boolean getTieneAlimentos(){
        return this.tieneAlimentos;
    }
    public int valorUrgencia(){
        return this.valorUrgencia;
    }
}
