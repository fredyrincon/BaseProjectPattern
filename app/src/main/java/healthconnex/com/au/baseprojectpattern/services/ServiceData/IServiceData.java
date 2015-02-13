package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.datamodel.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.datamodel.User;

/**
 * Created by frincon on 11/02/2015.
 */
public interface IServiceData {

    interface UserWebServiceCallback {
        void onUserServiceDataLoaded(User userInfo);
        void onErrorService(Exception exception);
        void volleyReponseError(String message);

        void onReleaseNoteLoaded(ArrayList <ReleaseNoteItem> releaseNoteList);
    }

    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback);

    public void getReleaseNote(String appName, String organization, String versionCode, UserWebServiceCallback userServiceCallback);
}
