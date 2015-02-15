package healthconnex.com.au.baseprojectpattern.repository.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by frincon on 13/02/2015.
 */
public class DatabaseManager {

    static private DatabaseManager instance;

    static public void init(Context ctx) {
        if (null==instance) {
            instance = new DatabaseManager(ctx);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }

    private DatabaseHelper helper;
    private DatabaseManager(Context ctx) {
        helper = new DatabaseHelper(ctx);
        SQLiteDatabase database = helper.getWritableDatabase();
    }

    public DatabaseHelper getHelper() {
        return helper;
    }
}
