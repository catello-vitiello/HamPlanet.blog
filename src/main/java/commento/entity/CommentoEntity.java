package commento.entity;

import java.io.Serializable;

public class CommentoEntity implements Serializable, Cloneable{

    private int id, idPost, idUtente;
    private String contenutoCommento;


    public CommentoEntity(){

    }

    public CommentoEntity(int id, int idPost, int idUtente, String contenutoCommento){

        this.id = id;
        this.idPost = idPost;
        this.contenutoCommento = contenutoCommento;
        this.idUtente = idUtente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPost() {
        return idPost;
    }

    public void setIdPost(int idPost) {
        this.idPost = idPost;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }


    public String getContenutoCommento() {
        return contenutoCommento;
    }

    public void setContenutoCommento(String contenutoCommento) {
        this.contenutoCommento = contenutoCommento;
    }

    @Override
    public String toString(){

        String s = "";

            s = getClass().getName() + "[ID= " + id + ", idPost= " + idPost + ", idUser= " + idUtente + ", commento= " + contenutoCommento + "]";

        return s;
    }

    @Override
    public boolean equals(Object o){


        if (this == o) return true; // Controllo se si tratta dello stesso riferimento
        if (o == null || getClass() != o.getClass()) return false;

        CommentoEntity altro = (CommentoEntity) o;

        return id == altro.id &&
                idPost == altro.idPost &&
                idUtente == altro.idUtente &&
                (contenutoCommento != null ? contenutoCommento.equals(altro.contenutoCommento) : altro.contenutoCommento == null);

    }

    @Override
    public int hashCode() {
        int res = 17;
        res = 31 * res + id;
        res = 31 * res + idPost;
        res = 31 * res + idUtente;
        res = 31 * res + (contenutoCommento != null ? contenutoCommento.hashCode() : 0);
        return res;
    }


    @Override
    public CommentoEntity clone(){

        try{
            return (CommentoEntity) super.clone();
        }catch(CloneNotSupportedException e){
            utils.UtilityClass.print(e);
            return null;
        }

    }

}
