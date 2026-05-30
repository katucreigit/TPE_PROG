public class Paquete{
    private int idP;
    private String codigoId;
    private double peso;
    private boolean tieneAlimentos;
    private int valorUrgencia;

    public Paquete(int idP, String codigoId, double peso, boolean tieneAlimentos, int valorUrgencia){
        this.id= idP;
        this.codigoId= codigoId;
        this.peso= peso;
        this.tieneAlimentos= tieneAlimentos;
        this.valorUrgencia= valorUrgencia;
    }

    public int getId(){
        return this.idP;
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
