package commento.dao;

import commento.entity.CommentoEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.sql.DataSource;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class CommentoDAOTest {

    static CommentoDAO dao;
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        dao = new CommentoDAO(utils.MockDataSource.createDataSource());

    }

    @Test
    void getAll() throws Exception {
        Collection<CommentoEntity> collection = dao.getAll("");
        assertNotNull(collection);
    }

    @Test
    void getAllByPost() throws Exception{
        int id = 1;
        Collection<CommentoEntity> collection = dao.getAllByPost(id);
        assertNotNull(collection);
    }

    @Test
    void getByID() throws Exception{
        int id = 1;
        CommentoEntity commento = dao.getByID(id);
        assertNotNull(commento);
    }

    @Test
    void update() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setIdUtente(5);
        commento.setContenutoCommento("commento test update");
        commento.setIdPost(4);
        commento.setId(1);

        boolean result = dao.insert(commento);
        assertTrue(result);

    }

    @Test
    void delete() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setId(2);
        dao.delete(commento);

        boolean result = dao.delete(commento);
        assertTrue(result);

    }

    @Test
    void insert() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setIdUtente(5);
        commento.setContenutoCommento("commento test insert");
        commento.setIdPost(3);


        boolean result = dao.insert(commento);

        assertTrue(result);
    }
}