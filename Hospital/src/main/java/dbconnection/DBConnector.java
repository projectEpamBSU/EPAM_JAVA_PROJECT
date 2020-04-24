package dbconnection;

import dbconnection.exceptions.WrongQueryException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class DBConnector {
    private String url = "jdbc:mysql://localhost:3306/hospital";
    private String user = "root";
    private String password = "mysql123";
    private Connection connection;

    static Logger logger = LogManager.getLogger();

    public DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
            this.connection.close();
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void connectToDataBase() {
        try {
            this.connection = DriverManager.getConnection(this.url, this.user, this.password);
        } catch (SQLException e) {
            logger.error("Cannot connect to database");
        }
    }

    public ResultSet getQueryResultAsResultSet(String query) throws WrongQueryException {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            logger.error("Cannot execute query");
        }
        throw new WrongQueryException();
    }

    public void executeUpdateOrDeleteQuery(String query) throws WrongQueryException {
        Statement statement = null;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Cannot execute query");
        }
        throw new WrongQueryException();
    }

    public boolean isConnected() {
        try {
            return !this.connection.isClosed();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    public void closeStream() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
