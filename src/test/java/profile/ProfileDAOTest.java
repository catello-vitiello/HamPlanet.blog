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

    @Test
    public void getAllCW() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("content_writer");

        Assertions.assertNotNull(list);

    }

    @Test
    public void getAllUsers() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("utente");

        Assertions.assertNotNull(list);

    }

    @Test
    public void getAllSupervisors() throws SQLException {

        Collection<UtenteEntity> list = profileDAO.getAll("supervisore");

        Assertions.assertNotNull(list);

    }

}
