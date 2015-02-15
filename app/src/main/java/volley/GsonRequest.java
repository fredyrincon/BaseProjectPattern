package volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import healthconnex.com.au.baseprojectpattern.base.Config;

/**
 * Created by frincon on 11/02/2015.
 */
public class GsonRequest<T> extends Request<T> {
    private final Gson gson = new Gson();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private final Map<String, String> params;
    private final String mBody;
    private final String mContentType;
    private final Response.Listener<T> listener;

    /**
     * Make a GET request and return a parsed object from JSON. Assumes
     * {@link Method#GET}.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(String url, Class<T> clazz, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = null;
        this.mBody = null;
        this.mContentType = null;
        setRetryPolicy(new DefaultRetryPolicy(Config.MY_SOCKET_TIMEOUT_MS, Config.MY_SOCKET_RETRIES_MS, Config.MY_SOCKET_BACKOFF_MULT_MS));
    }

    /**
     * Like the other, but allows you to specify which {@link Method} you want.
     *
     * @param method
     * @param url
     * @param clazz
     * @param headers
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = null;
        this.mBody = null;
        this.mContentType = null;
        //setRetryPolicy(new DefaultRetryPolicy(CommunKey.MY_SOCKET_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setRetryPolicy(new DefaultRetryPolicy(Config.MY_SOCKET_TIMEOUT_MS, Config.MY_SOCKET_RETRIES_MS, Config.MY_SOCKET_BACKOFF_MULT_MS));
    }

    /**
     * Like the other, but allows you to specify which {@link Method} you want.
     *
     * @param method
     * @param url
     * @param clazz
     * @param headers
     * @param params
     * @param listener
     * @param errorListener
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params, String jsonBody, String mContentType, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.params = params;
        this.mBody = jsonBody;
        this.mContentType = mContentType;
        this.listener = listener;
        setRetryPolicy(new DefaultRetryPolicy(Config.MY_SOCKET_TIMEOUT_MS, Config.MY_SOCKET_RETRIES_MS, Config.MY_SOCKET_BACKOFF_MULT_MS));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers != null ? headers : super.getHeaders();
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return params != null ? params : super.getParams();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return mBody != null ? mBody.getBytes() : super.getBody();
    }

    @Override
    public String getBodyContentType() {
        return mContentType != null ? mContentType : super.getBodyContentType();
    }

    public String getContentType() {
        return mContentType != null ? mContentType : super.getBodyContentType();
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

}
