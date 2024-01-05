package content_writer.entity;

import java.io.Serializable;

public class Content_Writer implements Serializable, Cloneable{

    private int id;
    private String userName, email, passwd, competenze;

    public Content_Writer(){

    }

    public Content_Writer(int id, String userName, String email, String passwd, String competenze){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.passwd = passwd;
        this.competenze = competenze;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getCompetenze() {
        return competenze;
    }

    public void setCompetenze(String competenze) {
        this.competenze = competenze;
    }

    @Override
    public String toString(){
        return getClass().getName() + "[ID= " + id + ", userName= " + userName + ", email= " + email + ", password= " + passwd
                + ", competenze= " + competenze + "]";
    }

    @Override
    public int hashCode(){
        int res = 17;
        res = 31 * res + id;
        res = 31 * res + userName.hashCode();
        res = 31 * res + email.hashCode();
        res = 31 * res + passwd.hashCode();
        res = 31 * res + competenze.hashCode();

        return res;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;

        if(getClass() != o.getClass())
            return false;

        Content_Writer altro = (Content_Writer) o;
        return( (id == altro.getId()) && (userName.equalsIgnoreCase(altro.getUserName())) && (email.equalsIgnoreCase(altro.getEmail()))
                && (passwd.equalsIgnoreCase(altro.getPasswd())) && (competenze.equalsIgnoreCase(altro.getCompetenze())) );
    }


    @Override
    public Content_Writer clone(){
        try{
            return (Content_Writer) super.clone();
        } catch(CloneNotSupportedException e){
            utils.UtilityClass.print(e);
            return null;
        }
    }

}
