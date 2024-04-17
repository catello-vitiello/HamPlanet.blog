package commento.entity;

import java.io.Serializable;

public class Commento implements Serializable, Cloneable{

    private int id, idPost, idUtente, idContent_Writer;
    private String contenutoCommento;

    private void setCorrectID(int Utente, int Content_Writer){

        if(Utente == 0){
            this.idContent_Writer = Content_Writer;
            this.idUtente = 0;
        }

        if(Content_Writer == 0){
            this.idUtente = Utente;
            idContent_Writer = 0;
        }

    }

    public Commento(){

    }

    public Commento(int id, int idPost, int idUtente, int idContent_Writer, String contenutoCommento){

        this.id = id;
        this.idPost = idPost;

        setCorrectID(idUtente, idContent_Writer);
        this.contenutoCommento = contenutoCommento;

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

    public int getIdContent_Writer() {
        return idContent_Writer;
    }

    public void setIdContent_Writer(int idContent_Writer) {
        this.idContent_Writer = idContent_Writer;
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

        if(this.idContent_Writer == 0)
            s = getClass().getName() + "[ID= " + id + ", idPost= " + idPost + ", idUtente= " + idUtente + ", commento= " + contenutoCommento + "]";

        if(this.idUtente == 0)
            s = getClass().getName() + "[ID= " + id + ", idPost= " + idPost + ", idContent_Writer= " + idContent_Writer + ", commento= " + contenutoCommento + "]";

        return s;
    }

    @Override
    public boolean equals(Object o){

        if(o == null)
            return false;
        if(getClass() != o.getClass())
            return false;

        Commento altro = (Commento) o;

        return( (id == altro.getId()) && (idPost == altro.getIdPost()) && (idUtente == altro.getIdUtente())
                && (idContent_Writer == altro.getIdContent_Writer()) && (contenutoCommento.equals(altro.getContenutoCommento())) );

    }

    @Override
    public int hashCode(){

        int res = 17;
        res = 31 * res + id;
        res = 31 * res + idPost;
        res = 31 * res + idUtente;
        res = 31 * res + idContent_Writer;
        res = 31 * res + contenutoCommento.hashCode();

        return res;

    }

    @Override
    public Commento clone(){

        try{
            return (Commento) super.clone();
        }catch(CloneNotSupportedException e){
            utils.UtilityClass.print(e);
            return null;
        }

    }

}
