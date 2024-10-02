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
    	this.ruolo = Role.utente;
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
    public Role getRuoloEnum(){
        return ruolo;
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
    public boolean equals(Object o) {
        if (this == o) return true; // Se è lo stesso oggetto, restituisce true
        if (o == null || getClass() != o.getClass()) return false; // Verifica se l'oggetto è null o di un'altra classe

        UtenteEntity altro = (UtenteEntity) o;

        // Controllo di uguaglianza per i campi, gestendo i casi null in modo sicuro
        return id == altro.getId() &&
                (userName != null ? userName.equalsIgnoreCase(altro.getUserName()) : altro.getUserName() == null) &&
                (email != null ? email.equalsIgnoreCase(altro.getEmail()) : altro.getEmail() == null) &&
                (passwd != null ? passwd.equalsIgnoreCase(altro.getPasswd()) : altro.getPasswd() == null) &&
                (competenze != null ? competenze.equalsIgnoreCase(altro.getCompetenze()) : altro.getCompetenze() == null) &&
                ruolo == altro.getRuoloEnum(); // Confronto diretto sugli enum
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
