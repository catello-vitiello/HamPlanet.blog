package post.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import javax.sql.DataSource;
import post.entity.Post;
import databaseServices.GenericCrudOp;

public class PostDAO implements GenericCrudOp<Post, Integer, Object> {
	
	private DataSource ds = null;

    /********************************************************/
    /*	            COSTRUTTORE CON DATASOURCE		        */
    /********************************************************/
    public PostDAO(DataSource ds){
        this.ds = ds;
    }

    /********************************************************/
    /*	                    SELECT ALL              		*/
    /********************************************************/
	@Override
	public Collection<Post> getAll(Object filter) throws SQLException {
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM Post";
        Collection<Post> posts = new LinkedList<Post>();
		
        try {
        	
        	connection = ds.getConnection();
        	preparedStatement = connection.prepareStatement(sql);
        	utils.UtilityClass.print(">.SELECT ALL SU Post: " + preparedStatement.toString());
        	
        	ResultSet rs = preparedStatement.executeQuery();
        	
        	while(rs.next()) {
        		Post p = new Post();
        		p.setId(rs.getInt("id"));
        		p.setNomePost(rs.getString("nomePost"));
        		p.setTesto(rs.getString("testo"));
        		p.setIdContent_Writer(rs.getInt("idcontent_writer"));
        		
        		posts.add(p);
        	}
        	
        } finally {
        	if(preparedStatement != null)
        		preparedStatement.close();
			if(connection != null)
				connection.close();
		}
        
		return posts;
	}

	/********************************************************/
    /*	                  RICERCA PER ID            		*/
    /********************************************************/
	@Override
	public Post getByID(Integer id) throws SQLException {
		
		Post p = new Post();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM Post p WHERE p.id = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            utils.UtilityClass.print(">.RECUPERO Post PER ID: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
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
	public boolean insert(Post entity) throws SQLException {
		
		int res=0;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "insert into Post(nomepost, testo, idcontent_writer) values "
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
	public boolean delete(Post entity) throws SQLException {
		
		int res = 0;
		
		Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Post WHERE id = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, entity.getId());

            utils.UtilityClass.print(">.Eliminazionde Post: " + preparedStatement.toString());
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
	public boolean update(Post entity) throws SQLException {
		
		utils.UtilityClass.print("Sorry!!!\nThe Post modification flow will be add soon!!!");
		
		return false;
	}
	
	/********************************************************/
    /*	               	   IS LIKED		 	               	*/
    /********************************************************/
//	public boolean isLiked(String nomePost, String userName) throws SQLException{
//		
//		Connection connection = null;
//		PreparedStatement preparedStatement = null;
//		String sql = "";
//		
//	}

}
