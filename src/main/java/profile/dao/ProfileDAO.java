package profile.dao;

import org.json.JSONObject;
import profile.entity.UtenteEntity;
import databaseServices.GenericCrudOp;
import utils.CifraPassword;
import javax.sql.DataSource;
import java.sql.*;
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
        String sql = "SELECT * FROM Ham_user WHERE ruolo = ?";
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
                cw.setRuolo(filter);
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
        String sql = "SELECT * FROM Ham_user c WHERE c.id = ?";

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
                cw.setCompetenze(rs.getString("competenze"));
                cw.setRuolo(rs.getString("ruolo"));
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



        boolean res = false;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT into Ham_user(userName, email, passwd, competenze, ruolo) VALUES (?,?,?,?,?)";

        try {

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            String pass = CifraPassword.toHash(entity.getPasswd());

            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, entity.getCompetenze());
            preparedStatement.setString(5, entity.getRuolo());

            utils.UtilityClass.print(">.Inserimento nuovo UtenteEntity: " + preparedStatement.toString());
            if(preparedStatement.executeUpdate() > 0)
                res = true;

        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return res;

    }


    public boolean insertSupervisor(UtenteEntity supervisor, String token) throws SQLException {

        boolean res = false;
        String user_sql = "INSERT into Ham_user(userName, email, passwd, competenze, ruolo) VALUES (?,?,?,?,?)";
        String idsql = "SELECT id FROM Ham_user WHERE email = ?";
        String sql = "UPDATE tokens SET overseer = ? WHERE token = ?";

        try (Connection connection = ds.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(user_sql);
                PreparedStatement tokenps = connection.prepareStatement(sql); PreparedStatement idps = connection.prepareStatement(idsql)) {

            preparedStatement.setString(1, supervisor.getUserName());
            preparedStatement.setString(2, supervisor.getEmail());
            preparedStatement.setString(3, supervisor.getPasswd());
            preparedStatement.setString(4, supervisor.getCompetenze());
            preparedStatement.setString(5, supervisor.getRuolo());

            if(preparedStatement.executeUpdate() > 0) {
                idps.setString(1, supervisor.getEmail());
                ResultSet rs = idps.executeQuery();
                if(rs.next()) {


                    tokenps.setInt(1, Integer.parseInt(rs.getString("id")));
                    tokenps.setString(2, token);


                    utils.UtilityClass.print(">.Inserimento nuovo supervisore in token: " + preparedStatement);
                    if (tokenps.executeUpdate() > 0)
                        res = true;
                }
            }
        }
        return res;

    }


    /********************************************************/
    /*	               	CHECK IF IS A VALID TOKEN	        */
    /********************************************************/
    public boolean isValidToken(String token) throws SQLException {


        String sql = "SELECT EXISTS ( SELECT 1 FROM tokens  WHERE token = ? AND overseer IS NULL ) AS valid_record";
        ResultSet rs = null;

        try(Connection connection = ds.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1, token);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                if (rs.getInt("valid_record") == 1)
                    return true;
            }

        }finally {
            if (rs != null)
                rs.close();
        }
        return false;
    }



    /********************************************************/
    /*	               	 DELETE HAM_USER	               	*/
    /********************************************************/
    @Override
    public boolean delete(UtenteEntity user) throws SQLException {

        int res = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Ham_user WHERE email = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());

            utils.UtilityClass.print(">.Eliminazionde dell'account: " + preparedStatement.toString());
            res = preparedStatement.executeUpdate();
            //utils.UtilityClass.resetAuto_increment("Ham_user", ds);

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

        String usersql = "UPDATE Ham_user SET userName = ? WHERE id = ?";
        String cwsql = "UPDATE Ham_user SET competenze = ? WHERE id = ?";
        String nopass = "UPDATE Ham_user SET passwd = ? WHERE id = ?";

        try(Connection connection = ds.getConnection();
            PreparedStatement userps = connection.prepareStatement(usersql);
            PreparedStatement cwps = connection.prepareStatement(cwsql);
            PreparedStatement nopassps = connection.prepareStatement(nopass)){

            userps.setString(1, user.getUserName());
            userps.setInt(2, user.getId());
            if( userps.executeUpdate() < 1){
                return false;
            }


            if (user.getPasswd() != null && user.getPasswd().length() > 8){
                nopassps.setString(1, utils.CifraPassword.toHash(user.getPasswd()));
                nopassps.setInt(2, user.getId());
                if (nopassps.executeUpdate() < 1) {
                    return false;
                }
            }

            if (user.getRuoloEnum().equals(UtenteEntity.Role.content_writer)){
                cwps.setString(1, user.getCompetenze());
                cwps.setInt(2, user.getId());
                if (cwps.executeUpdate() < 1) {
                    return false;
                }
            }


        }

        return true;
    }
    
    /********************************************************/
    /*	               	CERCA PER EMAIL           		   	*/
    /********************************************************/
    public boolean checkEmail(String email) throws SQLException {
    	
    	 ResultSet rs = null;
    	 int res = 0; 
         Connection connection = null;
         PreparedStatement preparedStatement = null;
         String sql = "SELECT 1 as R FROM Ham_user cw WHERE cw.email = ?";

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
        String sql = "SELECT 1 as R FROM Ham_user cw WHERE cw.userName = ?";

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



    	String sql = "SELECT passwd FROM Ham_user cw WHERE cw.email = ?";
    	
    	int res = 0;
    	
    	try {
    		
    		connection = ds.getConnection();

    		ps = connection.prepareStatement(sql);
    		
    		ps.setString(1, email);

    		
    		utils.UtilityClass.print(">.Try to login: " + ps);
            rs = ps.executeQuery();
    		
            if(rs.next()) {
                String pass = rs.getString(1);
                if (CifraPassword.checkPass(pass, password))
                    res = 1;
            }

            
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
        String sql = "SELECT * FROM Ham_user c WHERE c.email = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, email);

            utils.UtilityClass.print(">.RECUPERO UtenteEntity per email: " + preparedStatement.toString());
            
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                cw.setId(rs.getInt("id"));
                cw.setUserName(rs.getString("userName"));
                cw.setRuolo(rs.getString("ruolo"));
                cw.setEmail(rs.getString("email"));
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
