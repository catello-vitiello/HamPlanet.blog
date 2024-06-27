package post.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import post.entity.PostEntity;
import java.sql.SQLException;

public class PostDAOTest {

    @AfterClass
    public static void end(){
        System.out.println("\nEnd of the Functional Test on Post!\n\n");
    }

    static PostDAO postDAO;

    @BeforeClass
    public static void setUp() {
        System.out.println("Start of the Functional Test on Post!\n");
        postDAO = new PostDAO(utils.MockDataSource.createDataSource());
    }

    //retrive all post by Content Writer
    @Test
    public void retriveByCW() throws SQLException{

        Assertions.assertNotNull(postDAO.getAllByContentWriter(1));

    }

    //retrive all Posts
    @Test
    public void retrvieAll() throws SQLException{

        Assertions.assertNotNull(postDAO.getAll(null));

    }

    //retrive by ID
    @Test
    public void retrivePostByID() throws SQLException{

        Assertions.assertNotNull(postDAO.getByID(1));

    }

    //insert new Post
    @Test
    public void insertPost() throws SQLException{

        PostEntity p = new PostEntity();
        p.setNomePost("POST_TEST");
        p.setTesto("POST_TESTO_TEST");
        p.setIdContent_Writer(10);

        Assertions.assertTrue(postDAO.insert(p));

    }

    //delete post
    @Test
    public void deletePost() throws SQLException{

        PostEntity p = new PostEntity();
        p.setId(1);

        Assertions.assertTrue(postDAO.delete(p));

    }

    //like to post
    @Test
    public void likeToPost() throws SQLException{

        Assertions.assertTrue(postDAO.like(1, 6));

    }

    //unlike to post
    @Test
    public void unLikeToPost() throws SQLException{

        Assertions.assertTrue(postDAO.unlike(1, 5));

    }

    //check exsists like
    @Test
    public void isLikedCheck() throws SQLException{

        Assertions.assertTrue(postDAO.isLiked(5, 1));

    }

    //check don't exsists like
    @Test
    public void isNotLikedCheck() throws SQLException{

        Assertions.assertFalse(postDAO.isLiked(16, 1));

    }

}