package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.volley.IErrorVolleyCallBack;

/**
 * Created by frincon on 11/02/2015.
 */
public interface IServiceData {

    interface UserWebServiceCallback {
        void onUserServiceDataLoaded(User userInfo);
        void onErrorService(Exception exception);

        void onReleaseNoteLoaded(ArrayList <ReleaseNoteItem> releaseNoteList);
    }

    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback, IErrorVolleyCallBack iErrorVolleyCallBack);

    public void getReleaseNote(String appName, String organization, String versionCode, UserWebServiceCallback userServiceCallback, IErrorVolleyCallBack iErrorVolleyCallBack);
}
