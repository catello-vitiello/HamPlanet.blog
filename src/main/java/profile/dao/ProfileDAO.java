package profile.dao;

import profile.entity.UtenteEntity;
import profile.entity.UtenteEntity.Role;
import databaseServices.GenericCrudOp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProfileDAO implements GenericCrudOp<UtenteEntity, Integer, String> {

    private DataSource ds = null;

    /********************************************************/
    /*	            COSTRUTTORE CON DATASOURCE		        */
    /********************************************************/
    public ProfileDAO(DataSource ds){
        this.ds = ds;
    }

    /********************************************************/
    /*	                    SELECT ALL              		*/
    /********************************************************/
    @Override
    public Collection<UtenteEntity> getAll(String filter) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM UtenteEntity WHERE ruolo = ?";
        Collection<UtenteEntity> creatori = new LinkedList<UtenteEntity>();

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, filter);
            utils.UtilityClass.print(">.SELECT ALL SU " + filter + " --> " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                UtenteEntity cw = new UtenteEntity();
                cw.setId(rs.getInt("id"));
                cw.setUserName(rs.getString("userName"));
                cw.setEmail(rs.getString("email"));
                cw.setPasswd(rs.getString("passwd"));
                cw.setCompetenze(rs.getString("competenze"));
                creatori.add(cw);
            }

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return creatori;
    }

    /********************************************************/
    /*	                  RICERCA PER ID            		*/
    /********************************************************/
    @Override
    public UtenteEntity getByID(Integer id) throws SQLException {

    	UtenteEntity cw = new UtenteEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM UtenteEntity c WHERE c.id = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            utils.UtilityClass.print(">.RECUPERO PER ID: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                cw.setId(rs.getInt("id"));
                cw.setUserName(rs.getString("userName"));
                cw.setEmail(rs.getString("email"));
                cw.setPasswd(rs.getString("passwd"));
                cw.setCompetenze(rs.getString("competenze"));
                cw.setRuolo((Role) rs.getObject("Ruolo"));
            }

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return cw;
    }

    /********************************************************/
    /*	           		  INSERT HAM_USER	            	*/
    /********************************************************/
    @Override
    public boolean insert(UtenteEntity entity) throws SQLException {

        int res=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT into UtenteEntity(userName, email, passwd, competenze, ruolo) VALUES (?,?,?,?,?)";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPasswd());
            preparedStatement.setString(4, entity.getCompetenze());
            preparedStatement.setString(5, entity.getRuolo());

            utils.UtilityClass.print(">.Inserimento nuovo UtenteEntity: " + preparedStatement.toString());
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
    /*	               	 DELETE HAM_USER	               	*/
    /********************************************************/
    @Override
    public boolean delete(UtenteEntity user) throws SQLException {

        int res = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM UtenteEntity WHERE email = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());

            utils.UtilityClass.print(">.Eliminazionde dell'account: " + preparedStatement.toString());
            res = preparedStatement.executeUpdate();
            utils.UtilityClass.resetAuto_increment("UtenteEntity", ds);

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }

        return res == 1;
    }

    /********************************************************/
    /*	                 UPDATE HAM_USER	               	*/
    /********************************************************/
    @Override
    public boolean update(UtenteEntity user) throws SQLException {

        int res = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE UtenteEntity SET userName = ? , email = ? , passwd = ? , competenze = ? WHERE id = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, utils.CifraPassword.toHash(user.getPasswd()));
            preparedStatement.setString(4, user.getCompetenze());
            preparedStatement.setInt(5, user.getId());

            utils.UtilityClass.print(">.Update su Content_Writer: " + preparedStatement.toString());
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
    /*	               	CERCA PER EMAIL           		   	*/
    /********************************************************/
    public boolean checkEmail(String email) throws SQLException {
    	
    	 ResultSet rs = null;
    	 int res = 0; 
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         String sql = "SELECT 1 as R FROM UtenteEntity cw WHERE cw.email = ?";

         try{

             connection = ds.getConnection();
             preparedStatement = connection.prepareStatement(sql);

             preparedStatement.setString(1, email);

             utils.UtilityClass.print(">.Ricerca riscontro email: " + preparedStatement.toString());
             rs = preparedStatement.executeQuery();
             
             while(rs.next())
            	 res = rs.getInt("R");

         } finally {
             if(preparedStatement != null)
                 preparedStatement.close();
             if(connection != null)
                 connection.close();
         }

         return res == 1;
    	
    }
    
    /********************************************************/
    /*	               	CERCA PER USERNAME           		*/
    /********************************************************/
    public boolean checkUserName(String userName) throws SQLException {
    	
   	 ResultSet rs = null;
   	 int res = 0; 
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT 1 as R FROM UtenteEntity cw WHERE cw.userName = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, userName);

            utils.UtilityClass.print(">.Ricerca confronto userName: " + preparedStatement.toString());
            rs = preparedStatement.executeQuery();
            
            while(rs.next())
           	 res = rs.getInt("R");

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }

        return res == 1;
   	
   }
    
    /********************************************************/
    /*	               		 LOGIN			          		*/
    /********************************************************/
    public boolean login(String email, String password) throws SQLException{
    	
    	ResultSet rs = null;
    	PreparedStatement ps = null;
    	Connection connection = null;
    	String sql = "SELECT 1 AS L FROM UtenteEntity cw WHERE cw.email = ? AND cw.passwd = ?";
    	
    	int res = 0;
    	
    	try {
    		
    		connection = ds.getConnection();
    		ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, email);
    		ps.setString(2, password);
    		
    		utils.UtilityClass.print(">.Try to login: " + ps.toString());
            rs = ps.executeQuery();
    		
            while(rs.next())
            	res = rs.getInt("L");
            
    	} finally {
    		if(ps != null)
                ps.close();
            if(connection != null)
                connection.close();	
		}
    	
    	return res == 1;
    	
    }
    
    /********************************************************/
    /*	                  RICERCA PER EMAIL            		*/
    /********************************************************/
    public UtenteEntity getByEmail(String email) throws SQLException {

        UtenteEntity cw = new UtenteEntity();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM UtenteEntity c WHERE c.email = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            utils.UtilityClass.print(">.RECUPERO UtenteEntity per email: " + preparedStatement.toString());
            
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                cw.setId(rs.getInt("id"));
                cw.setUserName(rs.getString("userName"));
                cw.setEmail(rs.getString("email"));
                cw.setPasswd(rs.getString("passwd"));
                cw.setCompetenze(rs.getString("competenze"));
            }

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }
        return cw;
    }
    
    
    
}