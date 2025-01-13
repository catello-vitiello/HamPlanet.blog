package commento.entity;

import java.util.Objects;

public class CommentoDTO {

    private int id;
    private String commento;
    private String username;
    private int idUtente;



    public CommentoDTO(){

    }

    public CommentoDTO(int id, String username, String contenutoCommento, int idUtente){

        this.id = id;
        this.commento = contenutoCommento;
        this.username = username;
        this.idUtente = idUtente;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public  int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getCommento() {
        return commento;
    }

    public void setCommento(String contenutoCommento) {
        this.commento = contenutoCommento;
    }

    @Override
    public String toString(){


        return getClass().getName() + "[ID= " + id + ", id utente= " + idUtente + ", username= " + username + ", commento= " + commento + "]";
    }

    @Override
    public boolean equals(Object o){


        if (this == o) return true; // Controllo se si tratta dello stesso riferimento
        if (o == null || getClass() != o.getClass()) return false;

        CommentoDTO altro = (CommentoDTO) o;

        return id == altro.id &&
                username.equals(altro.username)&&
                (Objects.equals(commento, altro.commento));

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
