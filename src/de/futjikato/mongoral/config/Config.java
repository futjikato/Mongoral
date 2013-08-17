package de.futjikato.mongoral.config;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.config
 */
public class Config {

    protected static Config instance;

    protected Connection[] connections;

    private Config() {
        // initialize raw config
        Raw raw;
        try {
            File rawFile = new File("mongoral.json");
            raw = Raw.create(rawFile);
        } catch (IOException e) {
            // @todo show user error and provide fallback to empty config or whatever
            e.printStackTrace();
            return;
        }

        connections = raw.getConnections();
    }

    /**
     * Singleton for config
     *
     * @return config
     */
    public static Config getInstance() {
        if(!(instance instanceof Config)) {
            instance = new Config();
        }

        return instance;
    }

    public Connection[] getConnections() {
        return connections;
    }

    public void addConnection(Connection connection) {
        connections = Arrays.copyOf(connections, connections.length + 1);
        connections[connections.length - 1] = connection;
    }

    public void write() {
        File rawFile = new File("mongoral.json");
        Raw.write(connections, rawFile);
    }
}
