package de.futjikato.mongoral.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import de.futjikato.mongoral.MongoralException;
import de.futjikato.mongoral.database.Collection;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.config
 */
public final class Connection {

    private String name = "name";
    private static final String OPTION_KEY_NAME = "name";

    private String host = "localhost";
    private static final String OPTION_KEY_HOST = "host";

    private int port = 27017;
    private static final String OPTION_KEY_PORT = "port";

    private String username = "root";
    private static final String OPTION_KEY_USER = "username";

    private String password = "my secret password";
    private static final String OPTION_KEY_PASSWORD = "password";

    private String database = "database";
    private static final String OPTION_KEY_DATABASE = "db";

    protected MongoClient client;

    protected DB db;

    /**
     * This constructor is used to create connections from the config file
     *
     * @param options
     */
    public Connection(HashMap<String, String> options) {
        if(options.containsKey(OPTION_KEY_NAME)) {
            name = options.get(OPTION_KEY_NAME);
        }

        if(options.containsKey(OPTION_KEY_HOST)) {
            host = options.get(OPTION_KEY_HOST);
        }

        if(options.containsKey(OPTION_KEY_PORT)) {
            port = Integer.valueOf(options.get(OPTION_KEY_PORT));
        }

        if(options.containsKey(OPTION_KEY_USER)) {
            username = options.get(OPTION_KEY_USER);
        }

        if(options.containsKey(OPTION_KEY_PASSWORD)) {
            password = options.get(OPTION_KEY_PASSWORD);
        }

        if(options.containsKey(OPTION_KEY_DATABASE)) {
            database = options.get(OPTION_KEY_DATABASE);
        }
    }

    /**
     * The empty constructor is used to create blank connections to fill with user input
     */
    public Connection() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    protected MongoClient getClient() throws MongoralException {
        if(client == null) {
            try {
                client = new MongoClient(host, port);
            } catch (UnknownHostException e) {
                throw new MongoralException(e);
            }
        }

        return client;
    }

    public DB connect() throws MongoralException {
        if(database == null) {
            throw new MongoralException("Database name is not defined");
        }

        MongoClient client = getClient();
        return client.getDB(database);
    }

    public boolean test() {
        try {
            connect();
            return true;
        } catch (MongoralException e) {
            System.out.println(String.format("Connection::test failed -> %s", e.getMessage()));
            return false;
        }
    }

    public String toString() {
        return name;
    }

    public HashMap<String,String> getOptions() {
        HashMap<String, String> options = new HashMap<String, String>();
        options.put(OPTION_KEY_NAME, name);
        options.put(OPTION_KEY_HOST, host);
        options.put(OPTION_KEY_PORT, String.valueOf(port));
        options.put(OPTION_KEY_USER, username);
        options.put(OPTION_KEY_PASSWORD, password);
        options.put(OPTION_KEY_DATABASE, database);

        return options;
    }
}
