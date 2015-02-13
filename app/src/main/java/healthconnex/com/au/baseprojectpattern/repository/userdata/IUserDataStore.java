package healthconnex.com.au.baseprojectpattern.repository.userdata;

import java.util.ArrayList;
import java.util.Collection;

import healthconnex.com.au.baseprojectpattern.datamodel.User;

/**
 * Created by frincon on 9/02/2015.
 */
public interface IUserDataStore {

    interface UserListCallback {
        void onUserListLoaded(Collection <User> usersCollection);
        void onError(Exception exception);
    }

    interface UserDetailsCallback {
        void onUserEntityLoaded(User user);
        void onError(Exception exception);
    }

    public User getUserById(int userID);

    public boolean addUser(User user);

    public ArrayList<User> getUserByOrganisation(String organisationName);

    public void getUserByIdCallBack(int userID, UserDetailsCallback userDetailsCallback);

    public void getUserByOrganisationCallBack(String organisationName, UserListCallback userListCallback);
}
