package healthconnex.com.au.baseprojectpattern.base;

/**
 * Created by frincon on 11/02/2015.
 */
public class Config {
    // General configuration

    // Is this an internal dogfood build?
    public static final boolean IS_DOGFOOD_BUILD = false;

    // Key defined for AUTH header
    public static final String AUTHORISATION_TOKEN_HEADER  = "Authorization";

    public static int MY_SOCKET_TIMEOUT_MS = 3000;
    public static int MY_SOCKET_RETRIES_MS = 2;
    public static int MY_SOCKET_BACKOFF_MULT_MS = 2;

    public static String RELEASE_ITEM_FIX = "FIX";
    public static String RELEASE_ITEM_NEW = "NEW";
    public static String RELEASE_ITEM_IMPR = "IMPR";

}
