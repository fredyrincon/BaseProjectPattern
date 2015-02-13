package healthconnex.com.au.baseprojectpattern.repository.userdata;

import android.content.Context;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.datamodel.User;
import healthconnex.com.au.baseprojectpattern.repository.dao.DatabaseManager;

import static healthconnex.com.au.baseprojectpattern.util.LogUtils.makeLogTag;

/**
 * Created by frincon on 9/02/2015.
 */
public class LocalUserDataStore implements IUserDataStore {

    private static final String TAG = makeLogTag(LocalUserDataStore.class);
    private Context context;

    public LocalUserDataStore(Context context){
        this.context = context;
    }

    //Get the information from the actual database
    public User getUserById(int userID) {
        User userDummy = null;
        try {
            userDummy = DatabaseManager.getInstance().getHelper().getUserDao().queryForId(userID);
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.e(TAG, e.getMessage());
        }
        return userDummy;
    }

    public boolean addUser(User user) {
        try {
            boolean userExist = false;
            userExist = DatabaseManager.getInstance().getHelper().getUserDao().idExists(user.getId());
            if (!userExist) {
                // Add the new user
                DatabaseManager.getInstance().getHelper().getUserDao().create(user);
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Log.e(TAG, e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<User> getUserByOrganisation(String organisationName) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Jhon", "Abbort", "HealthConnex");
        User userDummy2 = new User(2, "Charlie", "Wales", "HealthConnex");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        return listByOrganization;
    }

    @Override
    public void getUserByIdCallBack(int userID, UserDetailsCallback userDetailsCallback) {
        User userDummy = new User(userID, "DummyName", "DummyLastName", "HealthConnex");
        userDetailsCallback.onUserEntityLoaded(userDummy);
    }

    @Override
    public void getUserByOrganisationCallBack(String organisationName, UserListCallback userListCallback) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Jhon", "Abbort", "HealthConnex");
        User userDummy2 = new User(2, "Charlie", "Wales", "HealthConnex");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        userListCallback.onUserListLoaded(listByOrganization);
    }
}
