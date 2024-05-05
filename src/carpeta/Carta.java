package carpeta;

public class Carta {

    public String elemento;
    public String numero;

    public Carta() {
    }

    public Carta(String elemento, String numero) {
        this.elemento = elemento;
        this.numero = numero;
    }

    public String getElemento() {
        return elemento;
    }

    public void setElemento(String elemento) {
        this.elemento = elemento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return numero + " de " + elemento;
    }

}

//    public static String establecerValorCarta(String elemento, String numero) {
//        return numero + " de " + elemento;
//    }