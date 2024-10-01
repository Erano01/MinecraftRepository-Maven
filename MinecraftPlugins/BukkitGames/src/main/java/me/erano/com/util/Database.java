package me.erano.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import me.erano.com.Plugin;


public class Database {//hashlemeyi burada yap sadece !
	
	public static void setCoin(UUID uuid,long count) {
		try (Connection connection = DriverManager.getConnection(Database.getConnectionString(),Database.getUsername(), Database.getPassword())) {
		    String query = "UPDATE Players SET coin = ? WHERE uuid = ?;";
		    try (PreparedStatement statement = connection.prepareStatement(query)) {
		        statement.setLong(1, count);
		        statement.setString(2, Hasher.hash(uuid.toString()));
		        statement.executeUpdate();
		    }finally {
		    	connection.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static long getCoin(UUID uuid) {
		try(Connection connection = DriverManager.getConnection(getConnectionString(),getUsername(),getPassword())){
			String query = "Select coin From Players Where uuid = ?";
			try (PreparedStatement statement = connection.prepareStatement(query)) {
	            statement.setString(1, Hasher.hash(uuid.toString()));

	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    long coin = resultSet.getLong("coin");
	                    return coin;
	                }
	            }
	        }
			finally {
				connection.close();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
	
	public static String getConnectionString() {
        String host = Plugin.getInstance().getConfig().getString("database.host");
        String port = Plugin.getInstance().getConfig().getString("database.port");
        String database = Plugin.getInstance().getConfig().getString("database.name");
        return "jdbc:mysql://" + host + ":" + port + "/" + database;
    }

    public static String getUsername() {
        return Plugin.getInstance().getConfig().getString("database.username");
    }

    public static String getPassword() {
        return Plugin.getInstance().getConfig().getString("database.password");
    }
    public static void createTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(getConnectionString(), getUsername(), getPassword())) {
            String query = "CREATE TABLE IF NOT EXISTS Players (" +
                    "uuid VARCHAR(255) NOT NULL, " +
                    "username VARCHAR(255) NOT NULL, " +
                    "password VARCHAR(255) NOT NULL, " +
                    "coin BIGINT, " +
                    "session bool default false,"+
                    "last_login DATETIME, " +
                    "PRIMARY KEY (uuid)" +
                    ")";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.executeUpdate();
            }
            finally {
    			connection.close();
    		}
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata durumunda gerekli işlemler yapılabilir
        }
    }

}
