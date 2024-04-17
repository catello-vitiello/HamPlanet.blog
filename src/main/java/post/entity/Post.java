package post.entity;

import java.io.Serializable;

public class Post implements Serializable, Cloneable{
    
    private int id, idContent_Writer;
    private String nomePost, testo;
    private Boolean isLiked=false;

    public Post(){

    }

    public Post(int id, String nomePost, String testo, boolean isLiked, int idContent_Writer){
        this.id = id;
        this.nomePost = nomePost;
        this.testo = testo;
        this.isLiked = isLiked;
        this.idContent_Writer = idContent_Writer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContent_Writer() {
        return idContent_Writer;
    }

    public void setIdContent_Writer(int idContent_Writer) {
        this.idContent_Writer = idContent_Writer;
    }

    public String getNomePost() {
        return nomePost;
    }

    public void setNomePost(String nomePost) {
        this.nomePost = nomePost;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    @Override
    public String toString(){

        return getClass().getName() + "[ID= " + id + "ID Content_Writer= " + idContent_Writer + ", nomePost= " + nomePost + ", like=" 
        + isLiked + ", testo= " + testo + "]";

    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;
        if(getClass() != o.getClass())
            return false;

        Post altro = (Post) o;

        return( (id==altro.getId()) && (idContent_Writer==altro.getIdContent_Writer()) && (isLiked==altro.isLiked()))
        && (nomePost.equals(altro.getNomePost())) && (testo.equals(altro.getTesto()) );
    }

    @Override
    public int hashCode(){
        int res = 17;
        res = 31 * res + id;
        res = 31 * res + idContent_Writer;
        res = 31 * res + isLiked.hashCode();
        res = 31 * res + nomePost.hashCode();
        res = 31 * res + testo.hashCode();

        return res;
    }

    @Override
    public Post clone(){
        try {
            return (Post) super.clone();
        } catch (CloneNotSupportedException e) {
            utils.UtilityClass.print(e);
            return null;
        }
    }

}
