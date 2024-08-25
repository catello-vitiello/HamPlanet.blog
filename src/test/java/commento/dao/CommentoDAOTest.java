package commento.dao;

import commento.entity.CommentoEntity;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;


public class CommentoDAOTest {

    @AfterClass
    public static void end(){
        System.out.println("\nEnd of the Functional Test on Commento!\n\n");
    }

    static CommentoDAO dao;
    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("Start of the Functional Test on Commento!\n");
        dao = new CommentoDAO(utils.MockDataSource.createDataSource());

    }

    @Test
    public void getAll() throws Exception {
        Collection<CommentoEntity> collection = dao.getAll("");
        assertNotNull(collection);
    }

    @Test
    public void getAllByPost() throws Exception{
        int id = 1;
        Collection<CommentoEntity> collection = dao.getAllByPost(id);
        assertNotNull(collection);
    }

    @Test
    public void getByID() throws Exception{
        int id = 1;
        CommentoEntity commento = dao.getByID(id);
        assertNotNull(commento);
    }

    @Test
    public void update() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setIdUtente(5);
        commento.setContenutoCommento("commento test update");
        commento.setIdPost(4);
        commento.setId(1);

        boolean result = dao.insert(commento);
        assertTrue(result);

    }

    @Test
    public void isOwnComment()throws Exception{


        boolean result = dao.isOwnComment(1, 5);

        assertTrue(result);
    }

    @Test
    public void delete() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setId(2);
        dao.delete(commento);

        boolean result = dao.delete(commento);
        assertTrue(result);

    }

    @Test
    public void insert() throws Exception{
        CommentoEntity commento = new CommentoEntity();
        commento.setIdUtente(5);
        commento.setContenutoCommento("commento test insert");
        commento.setIdPost(3);


        boolean result = dao.insert(commento);

        assertTrue(result);
    }
}