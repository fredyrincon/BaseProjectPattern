package healthconnex.com.au.baseprojectpattern.busevent;

/**
 * Created by frincon on 16/02/2015.
 */
public class NetworkStateChanged {

    private boolean  mIsInternetConnected;

    public NetworkStateChanged(boolean isInternetConnected) {
        this.mIsInternetConnected = isInternetConnected;
    }

    public boolean isInternetConnected() {
        return this.mIsInternetConnected;
    }
}
