package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import healthconnex.com.au.baseprojectpattern.base.Config;
import healthconnex.com.au.baseprojectpattern.datamodel.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.datamodel.User;
import healthconnex.com.au.baseprojectpattern.R;
import healthconnex.com.au.volley.ErrorListenerVolley;
import healthconnex.com.au.volley.GsonRequest;
import healthconnex.com.au.volley.MyVolleySingleton;

import static healthconnex.com.au.baseprojectpattern.util.LogUtils.makeLogTag;

/**
 * Created by frincon on 11/02/2015.
 */
public class CloudWebAPIService implements IServiceData {

    private static final String TAG = makeLogTag(CloudWebAPIService.class);

    private Context context;
    private String urlWebService;

    public CloudWebAPIService(Context context){
        this.context = context;
        urlWebService = context.getString(R.string.data_web_service_url);
    }

    //Build request header if auth required
    public static Map<String, String> getHeaderAuth(String tokenType, String tokenAuth) {
        Map<String, String> paramsHeader = new HashMap<String, String>();
        paramsHeader.put(Config.AUTHORISATION_TOKEN_HEADER,tokenType + " " + tokenAuth);
        return paramsHeader;
    }

    //GET api/TestWeb/UserInformation?userName={userName}
    @Override
    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback) {

        Map<String, String> hashParameters = new HashMap<String, String>();
        hashParameters.put("userName",userName);

        String apiServiceURL = generateAPIServiceURL("TestWeb", "UserInformation" , hashParameters);
        try {
            GsonRequest <User> myReq = new GsonRequest<User>(Request.Method.GET, apiServiceURL, User.class, null, requestSuccessListenerGetUserInformation(userServiceCallback), new ErrorListenerVolley(userServiceCallback, context)); //need to solve the error and make it generic
            MyVolleySingleton.getInstance(context).addToRequestQueue(myReq, TAG);
        } catch (Exception e ) {
            userServiceCallback.onErrorService(e);
        }
    }

    //Function to receive the data from the getUserInformation
    private Response.Listener<User> requestSuccessListenerGetUserInformation(final UserWebServiceCallback userServiceCallback) {
        return new Response.Listener<User>() {
            @Override
            public void onResponse(User response) {
                try {
                    userServiceCallback.onUserServiceDataLoaded(response);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    userServiceCallback.onErrorService(e);
                }
            }
        };
    }

    //GET api/TestWeb/ReleaseNotes?appName={appName}&organizationName={organizationName}&versionCode={versionCode}
    @Override
    public void getReleaseNote(String appName, String organization, String versionCode, UserWebServiceCallback userServiceCallback) {

        Map<String, String> hashParameters = new HashMap<String, String>();
        hashParameters.put("appName",appName);
        hashParameters.put("organizationName",organization);
        hashParameters.put("versionCode",versionCode);

        String apiServiceURL = generateAPIServiceURL("TestWeb", "ReleaseNotes" , hashParameters);

        try {
            GsonRequest <ReleaseNoteItem[]> myReq = new GsonRequest<ReleaseNoteItem[]>(Request.Method.GET, apiServiceURL, ReleaseNoteItem[].class, null, requestSuccessListenerGetReleaseNote(userServiceCallback), new ErrorListenerVolley(userServiceCallback, context)); //need to solve the error and make it generic
            MyVolleySingleton.getInstance(context).addToRequestQueue(myReq, TAG);
        } catch (Exception e ) {
            userServiceCallback.onErrorService(e);
        }
    }

    //Function to receive the data from the getUserInformation
    private Response.Listener<ReleaseNoteItem[]> requestSuccessListenerGetReleaseNote(final UserWebServiceCallback userServiceCallback) {
        return new Response.Listener<ReleaseNoteItem[]>() {
            @Override
            public void onResponse(ReleaseNoteItem[] response) {
                try {
                    ArrayList<ReleaseNoteItem> listResponse = new ArrayList<ReleaseNoteItem>(Arrays.asList(response));
                    userServiceCallback.onReleaseNoteLoaded(listResponse);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                    userServiceCallback.onErrorService(e);
                }
            }
        };
    }

    //function to generate the url for the web services
    public String generateAPIServiceURL (String controller, String function, Map <String, String> parameters ) {

        String urlService = urlWebService + "api/" + controller + "/" + function + "/?";

        //Generate the get paramaters from a HashMap to make it generic
        String param = "";
        for(Map.Entry<String, String> e: parameters.entrySet()){
            System.out.println("Key " + e.getKey());
            System.out.println("Value " + e.getValue());
            param = param + e.getKey() + "=" + Uri.encode(e.getValue()) + "&";
        }

        //Add the parameters to the web api url
        urlService = urlService +param;

        return urlService;
    }

    //Get information of the version
    /*public void getVersionApplicationInformation() {
        GsonRequest<Vehicle[]> myReq = new GsonRequest<Vehicle[]>(Request.Method.GET, urlRequest, Vehicle[].class, paramsHeader, createReqSuccessListenerRetrieveVehicles(), new ErrorListenerVolley(this, this));
        MyVolleySingleton.getInstance(this).addToRequestQueue(myReq, TAG);
    }

    //Function to receive the processing data of the vehicles
    private Response.Listener<Vehicle[]> createReqSuccessListenerRetrieveVehicles() {
        return new Response.Listener<Vehicle[]>() {
            @Override
            public void onResponse(Vehicle[] response) {
                try {
                    ArrayList<Vehicle> singleResult = new ArrayList<Vehicle>(Arrays.asList(response));

                    if (singleResult != null && singleResult.size() > 0) {
                        carSelected = singleResult.get(0);

                        //Validate if is on favorite
                        if (db.isVehicleFavorite(carSelected.getNVIC())) {
                            carSelected.setFavoriteVehicle(true);
                        }

                        mApplication.setCarSelected(carSelected);

                        //Function to get the extra information req.
                        Map<String, String> paramsHeader = WebDAO.getHeaderAuth(mApplication.getUserInfo().getAuthorizationToken());
                        getExtraVehicleInformation(paramsHeader);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }*/
}
