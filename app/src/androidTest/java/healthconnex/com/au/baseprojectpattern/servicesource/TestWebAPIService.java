package healthconnex.com.au.baseprojectpattern.servicesource;

import android.content.Context;

import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IServiceData;

/**
 * Created by frincon on 12/02/2015.
 */
public class TestWebAPIService implements IServiceData {

    private Context context;

    public TestWebAPIService(Context context){
        this.context = context;
    }

    @Override
    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback) {
        //Create a dummy user
        try {
            User userDummy = new User(1, "TestLocalServiceName", "TestLocalServiceLastName", "Telstra");
            userServiceCallback.onUserServiceDataLoaded(userDummy);
        } catch (Exception e ) {
            userServiceCallback.onErrorService(e);
        }
    }
}