package profile.entity;

import java.io.Serializable;

public class UtenteEntity implements Serializable, Cloneable{
    private static final long serialVersionUID = 1L;
    
    public enum Role{
    	utente,
    	content_writer,
    	supervisore
    }
    
	private int id;
    private String userName, email, passwd, competenze;
    private Role ruolo;

    public UtenteEntity(){
    	
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

    public String getRuolo() {
		return String.valueOf(ruolo);
	}

	public void setRuolo(String ruolo) {
		this.ruolo = Role.valueOf(ruolo);
	}

    public void setRuolo(Role ruolo) {
        this.ruolo = ruolo;
    }

	@Override
    public String toString(){
        return getClass().getName() + "[ID= " + id + ", userName= " + userName + ", email= " + email + ", password= " + passwd
                + ", competenze= " + competenze + ", ruolo= " + ruolo + "]";
    }

    @Override
    public int hashCode(){
        int res = 17;
        res = 31 * res + id;
        res = 31 * res + userName.hashCode();
        res = 31 * res + email.hashCode();
        res = 31 * res + passwd.hashCode();
        res = 31 * res + competenze.hashCode();
        res = 31 * res + ruolo.hashCode();
        
        return res;
    }

    @Override
    public boolean equals(Object o){
        if(o == null)
            return false;

        if(getClass() != o.getClass())
            return false;

        UtenteEntity altro = (UtenteEntity) o;
        return( (id == altro.getId()) && (userName.equalsIgnoreCase(altro.getUserName())) && (email.equalsIgnoreCase(altro.getEmail()))
                && (passwd.equalsIgnoreCase(altro.getPasswd())) && (competenze.equalsIgnoreCase(altro.getCompetenze())) 
                && (ruolo.equals(altro.getRuolo())) );
    }


    @Override
    public UtenteEntity clone(){
        try{
            return (UtenteEntity) super.clone();
        } catch(CloneNotSupportedException e){
            utils.UtilityClass.print(e);
            return null;
        }
    }

}
