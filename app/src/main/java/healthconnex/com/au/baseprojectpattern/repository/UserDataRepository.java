package healthconnex.com.au.baseprojectpattern.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collection;

import healthconnex.com.au.baseprojectpattern.commun.RepositoryErrorBundle;
import healthconnex.com.au.baseprojectpattern.datamodel.User;
import healthconnex.com.au.baseprojectpattern.repository.userdata.IUserDataStore;
import healthconnex.com.au.baseprojectpattern.repository.userdata.IUserRepository;
import healthconnex.com.au.baseprojectpattern.repository.userdata.UserDataStoreFactory;

/**
 * Created by frincon on 9/02/2015.
 */
public class UserDataRepository implements IUserRepository {

    private static UserDataRepository INSTANCE;

    private Context context;
    private String keyContext;
    private IUserDataStore iUserDataStore;

    public static synchronized UserDataRepository getInstance(Context context, String keyStore) {
        if (INSTANCE == null) {
            INSTANCE = new UserDataRepository(context, keyStore);
        }
        return INSTANCE;
    }

    public static synchronized UserDataRepository getInstance(Context context, IUserDataStore iUserDataStore) {
        if (INSTANCE == null) {
            INSTANCE = new UserDataRepository(context, iUserDataStore);
        }
        return INSTANCE;
    }

    public static synchronized  void resetInstance() {
        INSTANCE = null;
    }

    //Constructor using String
    protected UserDataRepository(Context context, String keyStore) {
        this.context = context;
        UserDataStoreFactory factory = new UserDataStoreFactory(context, keyStore);
        iUserDataStore = factory.create(keyStore);
    }

    //Constructor sending the actual datastore
    protected UserDataRepository(Context context, IUserDataStore iUserDataStore) {
        this.context = context;
        this.iUserDataStore = iUserDataStore;
    }


    //-----------------------------------------------------------------------------------------------------------------

    //Implementation of the methods
    @Override
    public User getUserById(int userID) {
        return iUserDataStore.getUserById(userID);
    }

    @Override
    public boolean addUser(User user) {
        return iUserDataStore.addUser(user);
    }

    @Override
    public void getUserByIdCallBack(final int userId, final UserDetailsCallback userCallback) {
        iUserDataStore.getUserByIdCallBack(userId, new IUserDataStore.UserDetailsCallback() {
            @Override
            public void onUserEntityLoaded(User user) {
                userCallback.onUserLoaded(user);
            }

            @Override
            public void onError(Exception exception) {
                userCallback.onError(new RepositoryErrorBundle(exception));
            }
        });
    }

    @Override
    public ArrayList<User> getUserByOrganisation(String organisationName) {
        return iUserDataStore.getUserByOrganisation(organisationName);
    }

    @Override
    public void getUserByOrganisationCallBack(final String organisationName, final UserListCallback userListCallback) {
        //return iUserDataStore.getUserByOrganisation(organisationName);
        iUserDataStore.getUserByOrganisationCallBack(organisationName, new IUserDataStore.UserListCallback() {
            @Override
            public void onUserListLoaded(Collection<User> usersCollection) {
                userListCallback.onUserListLoaded(usersCollection);
            }

            @Override
            public void onError(Exception exception) {
                userListCallback.onError(new RepositoryErrorBundle(exception));
            }
        });
    }

}
