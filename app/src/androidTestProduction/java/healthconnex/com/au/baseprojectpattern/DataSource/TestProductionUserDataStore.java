package healthconnex.com.au.baseprojectpattern.datasource;

import android.content.Context;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.repository.userdata.IUserDataStore;

/**
 * Created by frincon on 10/02/2015.
 */
public class TestProductionUserDataStore implements IUserDataStore {

    private Context context;

    public TestProductionUserDataStore(Context context){
        this.context = context;
    }

    //Get the information from the actual database
    public User getUserById(int userID) {
        User userDummy = new User(userID, "DummyProductionName", "DummyProductionLastName", "HDNS");
        return userDummy;
    }

    public ArrayList<User> getUserByOrganisation(String organisationName) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Production", "Abbort", "HDNS");
        User userDummy2 = new User(2, "Production", "Wales", "HDNS");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        return listByOrganization;
    }

    @Override
    public void getUserByIdCallBack(int userID, UserDetailsCallback userDetailsCallback) {
        User userDummy = new User(userID, "DummyProductionName", "DummyProductionLastName", "HDNS");
        userDetailsCallback.onUserEntityLoaded(userDummy);
    }

    @Override
    public void getUserByOrganisationCallBack(String organisationName, UserListCallback userListCallback) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Production", "Abbort", "HDNS");
        User userDummy2 = new User(2, "Production", "Wales", "HDNS");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        userListCallback.onUserListLoaded(listByOrganization);
    }
}