package pl.lublin.wsei.java.cwiczenia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDB {

    private String Host;
    private Number Port;
    private String DBName;
    private String User;
    private String Password;
    private Connection connection = null;

    public MyDB(String host, Number port, String dbName)
    {
        this.Host=host;
        this.Port=port;
        this.DBName=dbName;
    }
    public void setUser(String user) {
        User = user;
    }
    public void setPassword(String password) {
        Password = password;
    }

    private void connect() {
        Properties connectionProps = new Properties();
        connectionProps.put("user", User);
        connectionProps.put("password", Password);
        connectionProps.put("serverTimezone", "Europe/Warsaw");

        String jdbcString = "jdbc:mysql://" + Host + ":" + Port + "/" + DBName;

        try {
            connection = DriverManager.getConnection(jdbcString, connectionProps);
        }
        catch (SQLException exception) {
            System.out.println("Błąd podłączenie do bazy: " + jdbcString);
            System.out.println("Komunikat błędu: " + exception.getMessage());
            connection = null;
        }
        System.out.println("Connected to database " + DBName);
    }

    public Connection getConnection() {
        if (connection == null)
            connect();
        return connection;
    }

    public void closeConnection() {
        if (connection != null)
            try {
                connection.close();
            } catch (SQLException exception) {
                System.out.println("Błąd przy zamykaniu połączenia bazodanowego: " + exception.getMessage());
            }
        connection = null;
    }
}
