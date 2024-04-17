package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import javax.sql.DataSource;

public class FindFreeID {
	
	/*******************************************************/
	/* FUNZIONE AD HOC PER INSERIMENTO NEL PRIMO ID LIBERO */
	/*******************************************************/
	public static int findFreeID(String tabella, DataSource ds) throws SQLException{

		HashSet<Integer> list = new HashSet<>();

		
		Connection con = null;
		PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id FROM " + tabella;
        
        try {
        	
        	con = ds.getConnection();
        	ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next())
                list.add(rs.getInt("id"));

        } finally {
        	if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(con != null)
				con.close();  	
		}

		
        int idx=0;
        int[] a = new int[list.size()];

        for(Integer i : list){
            a[idx++] = i;
        }

        for(int i=0, j=1; i<a.length; i++, j++){
            if(j != a[i]){
                System.out.println("index: " + j + " value: " + a[i]);
                return j;
            }
        }

        return -1;
    } 
	
}
