package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import post.entity.PostEntity;
import databaseServices.GenericCrudOp;
import utils.UtilityClass;

public class PostDAO implements GenericCrudOp<PostEntity, Integer, Object> {

    private static final String POST_TABLE = "Post";
    private static final String LIKE_TABLE = "Like_";

	private DataSource ds = null;



    /********************************************************/
    /*	            COSTRUTTORE CON DATASOURCE		        */
    /********************************************************/
    public PostDAO(DataSource ds){
        this.ds = ds;
    }


    public int getByName(String nomePost)throws SQLException{

        String sql = "SELECT id FROM Post WHERE nomePost = ?";
        ResultSet rs = null;

        try(Connection connection = ds.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            preparedStatement.setString(1, nomePost);

            rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }

        }finally {
            if (rs != null)
                rs.close();
        }
        return -1;
    }

    /********************************************************/
    /*	               	  LIKE POST		               	*/
    /********************************************************/

    public Collection<PostEntity> getAllByContentWriter(int writerId) throws  SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Collection<PostEntity> posts = new LinkedList<>();
        String sql = "SELECT * FROM " + POST_TABLE + " WHERE idcontent_writer = ?";

        try {
            connection = ds.getConnection();
            //INSERT INTO Like_(id_post, id_user) VALUE (?, ?)
            ps = connection.prepareStatement(sql);
            ps.setInt(1, writerId);

            UtilityClass.print(">.retrieve contentWriter's posts : " + ps);

            rs = ps.executeQuery();
            while (rs.next()){
                PostEntity p = new PostEntity();
                p.setId(rs.getInt("id"));
                p.setNomePost(rs.getString("nomePost"));
                p.setTesto(rs.getString("testo"));
                p.setIdContent_Writer(writerId);

                posts.add(p);
            }



        }finally {
            if (ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return posts;
    }

    /********************************************************/
    /*	                    SELECT ALL              		*/
    /********************************************************/
	@Override
	public Collection<PostEntity> getAll(Object filter) throws SQLException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + POST_TABLE ;
        Collection<PostEntity> postEntities = new LinkedList<PostEntity>();
		
        try {
        	
        	connection = ds.getConnection();
        	preparedStatement = connection.prepareStatement(sql);
        	utils.UtilityClass.print(">.SELECT ALL SU PostEntity: " + preparedStatement.toString());
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	
        	while(rs.next()) {
        		PostEntity p = new PostEntity();
        		p.setId(rs.getInt("id"));
        		p.setNomePost(rs.getString("nomePost"));
        		p.setTesto(rs.getString("testo"));
        		p.setIdContent_Writer(rs.getInt("idcontent_writer"));
        		
        		postEntities.add(p);
        	}
        	
        } finally {
        	if(preparedStatement != null)
        		preparedStatement.close();
			if(connection != null)
				connection.close();
		}
        
		return postEntities;
	}

	/********************************************************/
    /*	                  RICERCA PER ID            		*/
    /********************************************************/
	@Override
	public PostEntity getByID(Integer id) throws SQLException {
		
		PostEntity p = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM " + POST_TABLE + " p WHERE p.id = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            utils.UtilityClass.print(">.RECUPERO PostEntity PER ID: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                p = new PostEntity();
                p.setId(rs.getInt("id"));
        		p.setNomePost(rs.getString("nomePost"));
        		p.setTesto(rs.getString("testo"));
        		p.setIdContent_Writer(rs.getInt("idcontent_writer"));
            }

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return p;
		
	}

	/********************************************************/
    /*	            	 CREATE NEW POST	            	*/
    /********************************************************/
	@Override
	public boolean insert(PostEntity entity) throws SQLException {
		
		int res=0;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into " + POST_TABLE + " (nomepost, testo, idcontent_writer) values "
        		+ "(?, ?, ?)";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getNomePost());
            preparedStatement.setString(2, entity.getTesto());
            preparedStatement.setInt(3, entity.getIdContent_Writer());
            
            utils.UtilityClass.print(">.CREAZIONE POST: " + preparedStatement.toString());
            res = preparedStatement.executeUpdate();

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
		
		return res == 1;
	}

	/********************************************************/
    /*		               DELETE POST		               	*/
    /********************************************************/
	@Override
	public boolean delete(PostEntity entity) throws SQLException {
		
		int res = 0;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM " + POST_TABLE + " WHERE id = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());

            utils.UtilityClass.print(">.Eliminazionde PostEntity: " + preparedStatement.toString());
            res = preparedStatement.executeUpdate();
            
        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
		
		return res == 1;
	}

	/********************************************************/
    /*	               	  UPDATE POST		               	*/
    /********************************************************/
	@Override
	public boolean update(PostEntity entity) throws SQLException {
		
		utils.UtilityClass.print("Sorry!!!\nThe PostEntity modification flow will be add soon!!!");
		
		return false;
	}

    public boolean isCwOwnPost(int postId, int CwId)throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean result = false;

        String sql = "SELECT * FROM " + POST_TABLE + " WHERE id = ? AND idcontent_writer = ?";

        try{
            connection = ds.getConnection();

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, postId);
            preparedStatement.setInt(2, CwId);
            rs = preparedStatement.executeQuery();

            UtilityClass.print(">.Check se il post appartiena al creator : " + preparedStatement);
            if(rs.next()){
                result = true;
                rs.close();
            }
        }
        finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (rs != null)
                rs.close();
        }

        return result;
    }


    /********************************************************/
    /*	               	  LIKE POST		               	*/
    /********************************************************/

    public boolean like(int postId, int userId) throws  SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;
        String sql = "INSERT INTO " + LIKE_TABLE + " (id_post, id_user) VALUE (?, ?)";

        try {
            connection = ds.getConnection();
            //INSERT INTO Like_(id_post, id_user) VALUE (?, ?)
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, userId);

            UtilityClass.print(">.Aggiunta Like a LIKE_ : " + ps);
            if (ps.executeUpdate() > 0)
                result = true;


        }finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();
        }

        return result;
    }


    /********************************************************/
    /*	               	  UNLIKE POST		               	*/
    /********************************************************/

    public boolean unlike(int postId, int userId) throws  SQLException{
        Connection connection = null;
        PreparedStatement ps = null;
        boolean result = false;
        String sql = "DELETE FROM " + LIKE_TABLE + " l WHERE l.id_post = ? AND l.id_user = ?";

        try {
            connection = ds.getConnection();
            //INSERT INTO Like_(id_post, id_user) VALUE (?, ?)
            ps = connection.prepareStatement(sql);
            ps.setInt(1, postId);
            ps.setInt(2, userId);

            UtilityClass.print(">.Rimozione Like a LIKE_ " + ps);
            if (ps.executeUpdate() > 0)
                result = true;


        }finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();
        }

        return result;
    }
	
	/********************************************************/
    /*	               	   IS LIKED		 	               	*/
    /********************************************************/
	public boolean isLiked(int userId, int postId) throws SQLException{

		Connection connection = null;
		PreparedStatement preparedStatement = null;
        ResultSet rs = null;
		String sql = "SELECT * FROM " + LIKE_TABLE + " WHERE id_user = ? AND id_post = ?";
        boolean result = false;

        try {
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, postId);

            rs = preparedStatement.executeQuery();

            if (rs.next())
                result = true;


        }finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return result;
	}

}
