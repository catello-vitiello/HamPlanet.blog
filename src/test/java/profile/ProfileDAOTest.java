package profile;

import org.junit.jupiter.api.*;
import profile.dao.ProfileDAO;
import profile.entity.UtenteEntity;
import java.sql.SQLException;
import java.util.Collection;

public class ProfileDAOTest {

    static ProfileDAO profileDAO;
    @BeforeAll
    static void setUp() {
        profileDAO = new ProfileDAO(utils.MockDataSource.createDataSource());
    }

    //Test retrive all  user with content writer role
    @Test
    public void getAllCW() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("content_writer");

        Assertions.assertNotNull(list);

    }

    //Test retrive all  user with utente role
    @Test
    public void getAllUsers() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("utente");

        Assertions.assertNotNull(list);

    }

    //Test retrive all  user with supervisore role
    @Test
    public void getAllSupervisors() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("supervisore");

        Assertions.assertNotNull(list);

    }

    //Test search by ID
    @Test
    public void getByID() throws SQLException{

        UtenteEntity u = profileDAO.getByID(1);

        Assertions.assertNotNull(u);

    }

    //Test Insert new utente
    @Test
    public void insertUtente() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("utente"); //insert utente

        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Test Insert new content writer
    @Test
    public void insertContentWriter() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("content_writer"); //insert content writer
        u.setCompetenze("competenze");

        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Test Insert new supervisore
    @Test
    public void insertSupervisore() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("supervisore"); //insert supervisore
        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Test Insert new utente ID
    @Test
    public void insertUtenteID() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setId(1000);
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("utente"); //insert utente

        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Test Insert new content writer ID
    @Test
    public void insertContentWriterID() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setId(1000);
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("content_writer"); //insert content writer
        u.setCompetenze("competenze");

        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Test Insert new supervisore ID
    @Test
    public void insertSupervisoreID() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setId(1000);
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("supervisore"); //insert supervisore
        Assertions.assertTrue(profileDAO.insert(u));

    }

    //Delete Ham_user
    @Test
    public void deleteHamUser() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setEmail("?");

        Assertions.assertTrue(profileDAO.delete(u));

    }

    //update Ham_user
    @Test
    public void updateHamUser() throws SQLException{
        UtenteEntity u = new UtenteEntity();
        u.setId(100);
        u.setUserName("Uname");
        u.setEmail("email");
        u.setPasswd("passwd");
        u.setRuolo("content_writer");
        u.setCompetenze("competenze");

        Assertions.assertTrue(profileDAO.update(u));
    }

    //Check is available username
    @Test
    public void userNameIsAvailable() throws SQLException{

        Assertions.assertFalse(profileDAO.checkUserName("USERNAME_TEST"));

    }

    //Check is not available username
    @Test
    public void userNameIsNotAvailable() throws SQLException{

        Assertions.assertTrue(profileDAO.checkUserName("Nicola_Nappi"));

    }

    //Check is available email
    @Test
    public void emailIsAvailable() throws SQLException{

        Assertions.assertFalse(profileDAO.checkEmail("EMAIL4TEST@TEST.EMAIL.TEST"));

    }

    //Check is not available email
    @Test
    public void emailIsNotAvailable() throws SQLException{

        Assertions.assertTrue(profileDAO.checkEmail("m.rossi@live.it"));

    }

    //Test Login
    @Test
    public void checkCredentials() throws SQLException{

        Assertions.assertTrue(profileDAO.login("m.rossi@live.it1", "a"));

    }

    //retrive by email
    @Test
    public void getUserByEmail() throws SQLException{

        Assertions.assertNotNull(profileDAO.getByEmail("m.rossi@live.it1"));

    }

    //check status Content Writer
    @Test
    public void checkStatus() throws SQLException{

        UtenteEntity u = new UtenteEntity();
        u.setEmail("m.rossi@live.it1");

        Assertions.assertNotNull(profileDAO.status(u));

    }

}
