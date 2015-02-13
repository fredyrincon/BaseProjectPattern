package healthconnex.com.au.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import healthconnex.com.au.baseprojectpattern.R;

/**
 * Created by frincon on 11/02/2015.
 */
public class ErrorVolleyMessageHandler /* implements Response.ErrorListener  */{

    /*private Context context;

    public ErrorVolleyMessageHandler(IVolleyCallBack iVolleyCallBack, Context context) {
        this.iVolleyCallBack = iVolleyCallBack;
        this.context = context;
    }

    @Override
    public void onErrorResponse(VolleyError arg0) {
        String messageError = getMessage(arg0);
        iVolleyCallBack.volleyReponseError(messageError);
    }*/

    //Function to get the appropiate message
    public static String getMessage(Context context, VolleyError error) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_down);
        } else if (isAuthenticationProblem(error)) {
            return context.getResources().getString(R.string.generic_auth_error);
        } else if (isServerProblem(error)) {
            return handleServerError(context, error);
        } else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_internet);
        }
        return context.getResources().getString(R.string.generic_error);
    }

    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError);
    }

    private static boolean isAuthenticationProblem(Object error) {
        return (error instanceof AuthFailureError);
    }

    private static String handleServerError(Context context, VolleyError error) {

        NetworkResponse response = error.networkResponse;

        if (response != null) {

            int statusCode = response.statusCode;
            Log.d("testerror", "" + statusCode + " " + response.data);

            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:
                    try {
                        // server might return error like this { "error": "Some error occured" }
                        // Use "Gson" to parse the result
                        HashMap<String, String> result = new Gson().fromJson(new String(response.data),
                                new TypeToken<Map<String, String>>() {
                                }.getType());

                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // invalid request
                    return error.getMessage();

                default:
                    return context.getResources().getString(R.string.generic_error);
            }
        }
        return context.getResources().getString(R.string.generic_error);
    }
}
