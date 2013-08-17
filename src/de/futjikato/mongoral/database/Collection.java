package de.futjikato.mongoral.database;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.Set;

/**
 * @author moritzspindelhirn
 * @todo Documentation
 * @category de.futjikato.mongoral.database
 */
public class Collection {

    DBCollection dbCollection;

    public Collection(DBCollection dbCollection) {
        this.dbCollection = dbCollection;
    }

    public static Collection[] getAllFromDatabase(DB database) {
        Set<String> names = database.getCollectionNames();
        int size = names.size();
        Collection[] list = new Collection[size];

        int index = 0;
        for(String name : names) {
            list[index++] = new Collection(database.getCollection(name));
        }

        return list;
    }
}
