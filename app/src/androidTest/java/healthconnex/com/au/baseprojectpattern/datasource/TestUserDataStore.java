package healthconnex.com.au.baseprojectpattern.datasource;

import android.content.Context;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.datamodel.User;
import healthconnex.com.au.baseprojectpattern.repository.userdata.IUserDataStore;

/**
 * Created by frincon on 9/02/2015.
 */
public class TestUserDataStore  implements IUserDataStore {

    private Context context;

    public TestUserDataStore(Context context){
        this.context = context;
    }

    //Get the information from the actual database
    public User getUserById(int userID) {
        User userDummy = new User(userID, "DummyTestName", "DummyTestLastName", "HDNS");
        return userDummy;
    }

    public ArrayList<User> getUserByOrganisation(String organisationName) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Test", "Abbort", "HDNS");
        User userDummy2 = new User(2, "Test", "Wales", "HDNS");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        return listByOrganization;
    }

    @Override
    public void getUserByIdCallBack(int userID, UserDetailsCallback userDetailsCallback) {
        User userDummy = new User(userID, "DummyTestName", "DummyTestLastName", "HDNS");
        userDetailsCallback.onUserEntityLoaded(userDummy);
    }

    @Override
    public void getUserByOrganisationCallBack(String organisationName, UserListCallback userListCallback) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Test", "Abbort", "HDNS");
        User userDummy2 = new User(2, "Test", "Wales", "HDNS");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        userListCallback.onUserListLoaded(listByOrganization);
    }
}