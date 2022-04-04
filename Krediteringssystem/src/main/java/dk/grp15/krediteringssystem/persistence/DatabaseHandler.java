package dk.grp15.krediteringssystem.persistence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DatabaseHandler {

    private static final DatabaseHandler INSTANCE = new DatabaseHandler();
    private Connection conn;
    private Vector<String> currentHeaders;
    private Vector<Vector<String>> currentData;

    private DatabaseHandler() {
        currentHeaders = new Vector<>();
        currentData = new Vector<>();
    }

    public void connect() {
        conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/CreditsManagementSystem", "credits_manager", "Credits123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (conn != null) {
            System.out.println("Connected to database!");
        }
        else {
            System.out.println("Connection could not be established!");
        }
    }

    public void disconnect() {
        try {
            conn.close();
            System.out.println("Disconnected from database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleStatement(String statement) {
        connect();
        Statement stmt;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(statement);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        disconnect();
    }

    private ResultSet handleQuery(String query) {
        ResultSet rs;
        Statement stmt;
        connect();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            stmt.closeOnCompletion();
        } catch (SQLException e) {
            return null;
        }
        disconnect();
        return rs;
    }

    public void processQuery(String query) {
        ResultSet rs = handleQuery(query);
        assert rs != null;
        currentHeaders = getColumns(rs);
        currentData = getRows(rs);
    }

    private Vector<String> getColumns(ResultSet rs) {
        Vector<String> headers = new Vector<>();
        try {
            ResultSetMetaData tableInfo = rs.getMetaData();
            for (int i = 1; i < tableInfo.getColumnCount() + 1; i++) {
                headers.add(tableInfo.getCatalogName(i));
            }
        } catch (SQLException e) {
            return null;
        }
        return headers;
    }

    private Vector<Vector<String>> getRows(ResultSet rs) {
        Vector<Vector<String>> results = new Vector<>();
        try {
            while(rs.next()) {
                Vector<String> row = new Vector<>();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    row.add("" + rs.getString(i + 1));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return results;
    }

    public Vector<String> getCurrentHeaders() {
        return currentHeaders;
    }

    public Vector<Vector<String>> getCurrentData() {
        return currentData;
    }

    public static DatabaseHandler getInstance() {
        return INSTANCE;
    }

}