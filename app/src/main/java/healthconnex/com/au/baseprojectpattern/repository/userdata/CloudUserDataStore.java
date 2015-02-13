package healthconnex.com.au.baseprojectpattern.repository.userdata;

import android.content.Context;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.datamodel.User;

/**
 * Created by frincon on 9/02/2015.
 */
public class CloudUserDataStore  implements IUserDataStore {

    private Context context;

    public CloudUserDataStore(Context context){
        this.context = context;
    }

    //Get the information from the actual database
    public User getUserById(int userID) {
        User userDummy = new User(userID, "DummyCloudName", "DummyCloudLastName", "Telstra");
        return userDummy;
    }

    public boolean addUser(User user) {
        return true;
    }

    public ArrayList<User> getUserByOrganisation(String organisationName) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Cloud", "Abbort", "Telstra");
        User userDummy2 = new User(2, "Cloud", "Wales", "Telstra");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        return listByOrganization;
    }

    @Override
    public void getUserByIdCallBack(int userID, UserDetailsCallback userDetailsCallback) {
        User userDummy = new User(userID, "DummyCloudName", "DummyCloudLastName", "Telstra");
        userDetailsCallback.onUserEntityLoaded(userDummy);
    }

    @Override
    public void getUserByOrganisationCallBack(String organisationName, UserListCallback userListCallback) {
        ArrayList <User> listByOrganization = new ArrayList<User>();
        User userDummy1 = new User(1, "Cloud", "Abbort", "Telstra");
        User userDummy2 = new User(2, "Cloud", "Wales", "Telstra");

        listByOrganization.add(userDummy1);
        listByOrganization.add(userDummy2);

        userListCallback.onUserListLoaded(listByOrganization);
    }
}