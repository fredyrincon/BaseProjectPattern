package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.commun.ErrorBundle;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import volley.IErrorVolleyCallBack;

/**
 * Created by frincon on 11/02/2015.
 */
public interface IWebAPIService {

    interface UserWebServiceCallback {
        void onUserServiceDataLoaded(User userInfo);
        void onErrorService(ErrorBundle errorBundle);
        void onReleaseNoteLoaded(ArrayList <ReleaseNoteItem> releaseNoteList);
    }

    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback, IErrorVolleyCallBack iErrorVolleyCallBac);

    public void getReleaseNote(String appName, String organization, String versionCode, UserWebServiceCallback userServiceCallback, IErrorVolleyCallBack iErrorVolleyCallBac);







    /*public ArrayList<User> getUserByOrganisation(String organisationName);

    public User getUserById(int userId);

    public void getUserByIdCallBack(final int userId, final UserDetailsCallback userCallback);

    public void getUserByOrganisationCallBack(final String organisationName, final UserListCallback userListCallback);*/
}
