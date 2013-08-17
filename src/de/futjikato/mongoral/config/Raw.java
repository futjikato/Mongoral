package de.futjikato.mongoral.config;

import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.config
 */
public class Raw {

    public List<HashMap<String, String>> connections;

    protected static Raw create(File defineFile) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(defineFile));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, Raw.class);
    }

    protected Connection[] getConnections() {
        Connection[] list = new Connection[connections.size()];
        int index = 0;
        for(HashMap<String, String> data : connections) {
            list[index++] = new Connection(data);
        }

        return list;
    }

    public static void write(Connection[] connections, File defineFile) {
        // prepare object
        Raw raw = new Raw();
        raw.connections = new LinkedList<HashMap<String, String>>();
        for(Connection con : connections) {
            raw.connections.add(con.getOptions());
        }

        // write to file
        try {
            Gson gson = new Gson();
            String jsonString = gson.toJson(raw);
            FileWriter writer = new FileWriter(defineFile);
            writer.write(jsonString);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
