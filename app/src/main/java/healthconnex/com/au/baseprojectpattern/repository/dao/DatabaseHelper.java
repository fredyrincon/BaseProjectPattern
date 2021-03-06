package healthconnex.com.au.baseprojectpattern.repository.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import healthconnex.com.au.baseprojectpattern.model.User;

/**
 * Created by frincon on 13/02/2015.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // name of the database file for your application
    private static final String DATABASE_NAME = "MCMVideoDB.sqlite";

    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    //private Dao<User, Integer> wishListDao = null;
    private Dao <User, Integer> userDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database,ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db,ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            List <String> allSql = new ArrayList<String>();
            switch(oldVersion)
            {
                case 1:
                    //allSql.add("alter table AdData add column `new_col` VARCHAR");
                    //allSql.add("alter table AdData add column `new_col2` VARCHAR");
            }

            /*
            if (oldVersion < 2) {
                // we added the age column in version 2
                dao.executeRaw("ALTER TABLE `account` ADD COLUMN age INTEGER;");
            }
            if (oldVersion < 3) {
                // we added the weight column in version 3
                dao.executeRaw("ALTER TABLE `account` ADD COLUMN weight INTEGER;");
            }

            */
            for (String sql : allSql) {
                db.execSQL(sql);
            }
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }

    }

    public Dao <User, Integer> getUserDao() {
        if (null == userDao) {
            try {
                userDao = getDao(User.class);
            }catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return userDao;
    }

}
