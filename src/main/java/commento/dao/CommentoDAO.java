package commento.dao;

import commento.entity.CommentoEntity;
import databaseServices.GenericCrudOp;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class CommentoDAO implements GenericCrudOp<CommentoEntity, Integer, Object> {

    private static final String TABLE_NAME = "CommentoEntity";
    private DataSource ds;

    public CommentoDAO(DataSource dataSource){
        ds = dataSource;
    }


    /********************************************************/
    /*	               	  GET ALL COMMENTO		               	*/
    /********************************************************/
    @Override
    public Collection<CommentoEntity> getAll(Object filter) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Commento";
        Collection<CommentoEntity> commentoCollection = new LinkedList<>();



        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);
            utils.UtilityClass.print(">.GET ALL SU Commento: " + ps.toString());


            rs = ps.executeQuery();
            while (rs.next()){
                CommentoEntity commento = new CommentoEntity();
                commento.setId(rs.getInt(1));
                commento.setIdPost(rs.getInt(2));
                commento.setIdUtente(rs.getInt(3));
                commento.setContenutoCommento(rs.getString(4));
                commentoCollection.add(commento);
            }

        }finally {
            if (ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return commentoCollection;
    }

    /********************************************************/
    /*	               	  GET ALL COMMENTO BY POST		               	*/
    /********************************************************/
    public Collection<CommentoEntity> getAllByPost(int postId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Commento WHERE idpost = ?";
        Collection<CommentoEntity> commentoCollection = new LinkedList<>();



        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, postId);

            utils.UtilityClass.print(">.GET ALL By POST SU CommentoEntity: " + ps.toString());

            rs = ps.executeQuery();
            while (rs.next()){
                CommentoEntity commento = new CommentoEntity();
                commento.setId(rs.getInt(1));
                commento.setIdPost(rs.getInt(2));
                commento.setIdUtente(rs.getInt(3));
                commento.setContenutoCommento(rs.getString(4));
                commentoCollection.add(commento);
            }

        }finally {
            if (ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return commentoCollection;
    }

    /********************************************************/
    /*	               	  CHECK ITS OWN COMMENT		               	*/
    /********************************************************/
    public boolean isOwnComment(int commentId, int userId) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Commento WHERE id = ? AND iduser = ?";
        boolean isOwn = false;


        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setInt(1, commentId);
            ps.setInt(2, userId);

            utils.UtilityClass.print(">. CHECK ITS OWN COMMENT SU CommentoEntity: " + ps.toString());

            rs = ps.executeQuery();
           if (rs.next()){
               isOwn = true;
           }

        }finally {
            if (ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return isOwn;
    }

    /********************************************************/
    /*	               	  GET COMMENTO	BY ID	               	*/
    /********************************************************/
    @Override
    public CommentoEntity getByID(Integer id) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM Commento WHERE id = ?";
        CommentoEntity commento = null;

        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            utils.UtilityClass.print(">.GET BY ID SU CommentoEntity: " + ps.toString());

            rs = ps.executeQuery();
            if (rs.next()){
                commento = new CommentoEntity();
                commento.setId(id);
                commento.setIdPost(rs.getInt(1));
                commento.setIdUtente(rs.getInt(2));
                commento.setContenutoCommento(rs.getString(3));
            }

        }finally {
            if (ps != null)
                ps.close();
            if(rs != null)
                rs.close();
            if (connection != null)
                connection.close();
        }

        return commento;
    }


    /********************************************************/
    /*	               	  UPDATE COMMENTO	               	*/
    /********************************************************/
    @Override
    public boolean update(CommentoEntity entity) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "UPDATE Commento SET contenutocommento = ? WHERE id = ?";
        boolean result = false;

        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, entity.getContenutoCommento());
            ps.setInt(2, entity.getId());

            utils.UtilityClass.print(">.UPDATE SU CommentoEntity: " + ps.toString());

            if(ps.executeUpdate() > 0)
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
    /*	               	  DELETE COMMENTO	               	*/
    /********************************************************/
    @Override
    public boolean delete(CommentoEntity entity) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM commento WHERE id = ?";
        boolean result = false;

        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getId());
            utils.UtilityClass.print(">.DELETE SU CommentoEntity: " + ps.toString());


            if(ps.executeUpdate() > 0)
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
    /*	               	  INSERT COMMENTO	               	*/
    /********************************************************/
    @Override
    public boolean insert(CommentoEntity entity) throws SQLException {
        Connection connection = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO Commento(idpost, iduser, contenutocommento) VALUE (?, ?, ?)";
        boolean result = false;

        try {
            connection = ds.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1, entity.getIdPost());
            ps.setInt(2, entity.getIdUtente());
            ps.setString(3, entity.getContenutoCommento());
            utils.UtilityClass.print(">.INSERT INTO CommentoEntity: " + ps.toString());

            if(ps.executeUpdate() > 0)
                result = true;


        }finally {
            if (ps != null)
                ps.close();
            if (connection != null)
                connection.close();
        }

        return result;
    }
}
