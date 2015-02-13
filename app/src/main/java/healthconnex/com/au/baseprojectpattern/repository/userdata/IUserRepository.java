package healthconnex.com.au.baseprojectpattern.repository.userdata;

import java.util.ArrayList;
import java.util.Collection;

import healthconnex.com.au.baseprojectpattern.commun.ErrorBundle;
import healthconnex.com.au.baseprojectpattern.datamodel.User;

/**
 * Created by frincon on 9/02/2015.
 */
public interface IUserRepository {

    interface UserListCallback {
        void onUserListLoaded(Collection<User> usersCollection);
        void onError(ErrorBundle errorBundle);
    }

    interface UserDetailsCallback {
        void onUserLoaded(User user);
        void onError(ErrorBundle errorBundle);
    }

    public ArrayList <User> getUserByOrganisation(String organisationName);

    public User getUserById(int userId);

    public boolean addUser(User user);

    public void getUserByIdCallBack(final int userId, final UserDetailsCallback userCallback);

    public void getUserByOrganisationCallBack(final String organisationName, final UserListCallback userListCallback);

}
