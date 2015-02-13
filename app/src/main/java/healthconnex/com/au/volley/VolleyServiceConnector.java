package healthconnex.com.au.volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import static healthconnex.com.au.baseprojectpattern.util.LogUtils.makeLogTag;

/**
 * Created by frincon on 11/02/2015.
 */
public class VolleyServiceConnector {

    private static final String TAG = makeLogTag(VolleyServiceConnector.class);

    public static void requestWithHeader(Activity parentActivity, String urlRequest, String TAG_Request, final String authoToken) {

        final ProgressDialog pDialog = new ProgressDialog(parentActivity);
        pDialog.setMessage("Loading example data cards...");
        pDialog.show();

        JsonArrayRequest req = new JsonArrayRequest(urlRequest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        pDialog.hide();
                        //barProgressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                VolleyLog.d(TAG, "Error: " + arg0.getMessage());
                pDialog.hide();

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("AUTHORISATION_TOKEN_HEADER", authoToken);
                return params;
            }
        };

        // Adding request to request queue
        MyVolleySingleton.getInstance(parentActivity).addToRequestQueue(req, TAG_Request);

    }

    public static void requestVolleyJsonArray(String parentTAG, Context contextParent, final IVolleyCallBack volleyCallBack, final String tagReturn, String urlRequest, final String tokenAuth) {

        JsonArrayRequest reqObject = new JsonArrayRequest(urlRequest,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
                // TODO Auto-generated method stub
                VolleyLog.d(TAG, "Error: " + arg0.getMessage());
                //pDialog.hide();
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("AUTHORISATION_TOKEN_HEADER", tokenAuth);
                return params;
            }
        };

        MyVolleySingleton.getInstance(contextParent).addToRequestQueue(reqObject, parentTAG);

    }
}
