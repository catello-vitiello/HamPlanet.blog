package content_writer.dao;

import content_writer.entity.Content_Writer;
import databaseServices.GenericCrudOp;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class Content_WriterDao implements GenericCrudOp<Content_Writer> {

    private DataSource ds = null;

    /********************************************************/
    /*	            COSTRUTTORE CON DATASOURCE		        */
    /********************************************************/
    public Content_WriterDao(DataSource ds){
        this.ds = ds;
    }

    /********************************************************/
    /*	                    SELECT ALL              		*/
    /********************************************************/
    @Override
    public Collection<Content_Writer> getAll() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM Content_Writer";
        Collection<Content_Writer> creatori = new LinkedList<Content_Writer>();

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            utils.UtilityClass.print(">.SELECT ALL SU Content_Writer " + preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Content_Writer cw = new Content_Writer();
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
    public Content_Writer getByID(Content_Writer user) throws SQLException {

        Content_Writer cw = new Content_Writer();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM Content_Writer c WHERE c.id = ?";

        try{
            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());

            utils.UtilityClass.print(">.RECUPERO CONTENT_WRITER PER ID: " + preparedStatement);
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

    /********************************************************/
    /*	            INSERT NEW CONTENT WRITER            	*/
    /********************************************************/
    @Override
    public boolean insert(Content_Writer entity) throws SQLException {

        int res=0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "INSERT into Content_Writer(userName, email, passwd, competenze) VALUES (?,?,?,?)";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, entity.getUserName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, utils.CifraPassword.toHash(entity.getPasswd()));
            preparedStatement.setString(4, entity.getCompetenze());

            utils.UtilityClass.print(">.Inserimento nuovo utente: " + preparedStatement);
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
    /*	               DELETE CONTENT WRITER               	*/
    /********************************************************/
    @Override
    public boolean delete(Content_Writer user) throws SQLException {

        int res = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM Content_Writer WHERE id = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());

            utils.UtilityClass.print(">.Eliminazionde dell'account: " + preparedStatement);
            res = preparedStatement.executeUpdate();
            utils.UtilityClass.resetAuto_increment("Content_Writer", ds);

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }

        return res == 1;
    }

    /********************************************************/
    /*	               UPDATE CONTENT WRITER               	*/
    /********************************************************/
    @Override
    public boolean update(Content_Writer user) throws SQLException {

        int res = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE Content_Writer SET userName = ? , email = ? , passwd = ? , competenze = ? WHERE id = ?";

        try{

            connection = ds.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, utils.CifraPassword.toHash(user.getPasswd()));
            preparedStatement.setString(4, user.getCompetenze());
            preparedStatement.setInt(5, user.getId());

            utils.UtilityClass.print(">.Update su Content_Writer: " + preparedStatement);
            res = preparedStatement.executeUpdate();

        } finally {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
        }

        return res == 1;
    }
}
