package healthconnex.com.au.baseprojectpattern.services;

import android.content.Context;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.commun.RepositoryErrorBundle;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IServiceData;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IWebAPIService;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.ServiceDataFactory;
import healthconnex.com.au.volley.IErrorVolleyCallBack;

/**
 * Created by frincon on 11/02/2015.
 */
public class WebAPIService implements IWebAPIService {

    private static WebAPIService INSTANCE;

    private Context context;
    private String keyContext;
    private IServiceData iServiceData;

    public static synchronized WebAPIService getInstance(Context context, String keyStore) {
        if (INSTANCE == null) {
            INSTANCE = new WebAPIService(context, keyStore);
        }
        return INSTANCE;
    }

    public static synchronized WebAPIService getInstance(Context context, IServiceData iServiceData) {
        if (INSTANCE == null) {
            INSTANCE = new WebAPIService(context, iServiceData);
        }
        return INSTANCE;
    }

    public static synchronized  void resetInstance() {
        INSTANCE = null;
    }

    //Constructor using String
    protected WebAPIService(Context context, String keyService) {
        this.context = context;
        ServiceDataFactory factory = new ServiceDataFactory(context, keyService);
        iServiceData = factory.create(keyService);
    }

    //Constructor sending the actual datastore
    protected WebAPIService(Context context, IServiceData iServiceData) {
        this.context = context;
        this.iServiceData = iServiceData;
    }

    //-----------------------------------------------------------------------------------------------------------------

    @Override
     public void getUserInformation(String userName, final UserWebServiceCallback userServiceCallback, final IErrorVolleyCallBack iErrorVolleyCallBack) {
        iServiceData.getUserInformation(userName, new IServiceData.UserWebServiceCallback() {
            @Override
            public void onUserServiceDataLoaded(User userInfo) {
                userServiceCallback.onUserServiceDataLoaded(userInfo);
            }

            @Override
            public void onErrorService(Exception exception) {
                userServiceCallback.onErrorService(new RepositoryErrorBundle(exception));
            }

            @Override
            public void onReleaseNoteLoaded(ArrayList<ReleaseNoteItem> releaseNoteList) {

            }
        }, iErrorVolleyCallBack);
    }

    //Function to get the release  note list
    @Override
    public void getReleaseNote(String appName, String organization, String versionCode, final UserWebServiceCallback userServiceCallback, final IErrorVolleyCallBack iErrorVolleyCallBack) {
        iServiceData.getReleaseNote(appName, organization, versionCode, new  IServiceData.UserWebServiceCallback() {

            @Override
            public void onUserServiceDataLoaded(User userInfo) {

            }

            @Override
            public void onErrorService(Exception exception) {

            }


            @Override
            public void onReleaseNoteLoaded(ArrayList<ReleaseNoteItem> releaseNoteList) {
                userServiceCallback.onReleaseNoteLoaded(releaseNoteList);
            }
        }, iErrorVolleyCallBack);
    }



}
