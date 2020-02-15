public class Arco {
    int inicio;
    int fin;
    String tipo;

    Arco(int primero, int segundo){
        this.inicio = primero;
        this.fin = segundo;
        tipo = "";
    }

    Arco(int primero, int segundo, int modo){
        this.inicio = primero;
        this.fin = segundo;
        if (modo==0){
            this.tipo = "Bosque";
        } else if(modo==1){
            this.tipo = "Ida";
        } else if(modo==2){
            this.tipo = "Vuelta";
        } else if(modo==3){
            this.tipo = "Cruzado";
        }
    }


    public int get_inicio(){
        return inicio;
    }

    public int get_fin(){
        return fin;
    }

    public void bosque(){
        this.tipo = "Bosque";
    }
    public void ida(){
        this.tipo = "Ida";
    }
    public void vuelta(){
        this.tipo = "Vuelta";
    }
    public void cruzado(){
        this.tipo = "Cruzado";
    }
    public Boolean is_bosque(){
        return tipo.equals("Bosque");
    }
    public Boolean is_cruzado(){
        return tipo.equals("Cruzado");
    }
    public Boolean is_ida(){
        return tipo.equals("Ida");
    }
    public Boolean is_vuelta(){
        return tipo.equals("Vuelta");
    }

}