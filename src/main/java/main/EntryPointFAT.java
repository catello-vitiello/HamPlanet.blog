package main;

import utils.CifraPassword;

import java.sql.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class EntryPointFAT {

        public static int findFreeID(Set<Integer> list){

            int idx = 0;
            int[] a = new int[list.size()];
            LinkedList<Integer> outOfScope = new LinkedList<>();

            for(Integer i : list){
                //System.out.println(i);
                if(i > idx+1){
                    //System.out.println("Scarto: " + i);
                    outOfScope.addLast(i);
                    continue;
                }
                a[idx++] = i;
            }

            for(Integer i : outOfScope)
                a[idx++] = i;

            System.out.println();
            for(int i=0, j=1; i<a.length; i++, j++){
                if(j != a[i]){
                    //System.out.println("index: " + j + " value: " + a[i]);
                    return j;
                }else{
                    //System.out.println("EQUALS: index: " + j + " value: " + a[i]);
                }
            }

            return -1;
        }

    public static void mainFreeID(){

        HashSet<Integer> set = new HashSet<>();

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/freedb_HPBlog_db";

            Connection con = DriverManager.getConnection(url,"root","Niky-1904");
            System.out.println("Connesione OK\n");

            PreparedStatement ps = null;
            ResultSet rs = null;
            String sql = "SELECT id FROM Ham_user";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
                set.add(rs.getInt("id"));

            con.close();
            System.out.println("\nConnessione chiusa");

        } catch(ClassNotFoundException e){

        } catch (SQLException e){
            System.out.println("Launch SQLException!");
        }

        System.out.println(findFreeID(set));

    }

    public static String toHahPswd(String pass){
            return CifraPassword.toHash(pass);
    }

    /****************************************************************************/

    public static void main(String[] args){

        System.out.println(toHahPswd("test"));
//        mainFreeID();

    }


}
