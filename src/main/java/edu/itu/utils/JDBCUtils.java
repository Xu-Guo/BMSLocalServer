package edu.itu.utils;

import java.sql.*;

/**
 * Created by xuxu on 10/16/16.
 */
public class JDBCUtils {

    private static Connection conn = null;

    static {
        try {
            //load jdbc driver class
            Class.forName("com.mysql.jdbc.Driver");
            //get connection
            conn = DriverManager.getConnection("jdbc:mysql://10.1.4.23:3306/bms", "root", "root");
            //System.out.println("conn");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized Connection getConn() {
        return conn;
    }

    public static void close(ResultSet rs, Statement ps, Connection conn) {
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Statement ps, Connection conn){
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            if(conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Statement ps){
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs){
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
