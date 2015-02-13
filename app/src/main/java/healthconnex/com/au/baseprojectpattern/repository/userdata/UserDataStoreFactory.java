package healthconnex.com.au.baseprojectpattern.repository.userdata;

import android.content.Context;

import healthconnex.com.au.baseprojectpattern.commun.DataServiceLocatorKey;

/**
 * Created by frincon on 9/02/2015.
 */
public class UserDataStoreFactory {

    private Context context;
    private String dataSourceKey;

    public UserDataStoreFactory(Context context, String dataSourceKey) {
        this.context = context;
        this.dataSourceKey = dataSourceKey;
    }

    public IUserDataStore create(String keyContext) {

        IUserDataStore userDataStore = null;

        if (keyContext.equals(DataServiceLocatorKey.DATA_SOURCE_LOCAL)) {
            userDataStore = new LocalUserDataStore(context);
        } else {
            if (keyContext.equals(DataServiceLocatorKey.DATA_SOURCE_CLOUD)) {
                userDataStore = new CloudUserDataStore(context);
            } else {
                //userDataStore = new LocalUserDataStore();
            }
        }

        return userDataStore;
    }
}
