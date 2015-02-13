package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import android.content.Context;

import healthconnex.com.au.baseprojectpattern.commun.DataServiceLocatorKey;

/**
 * Created by frincon on 11/02/2015.
 */
public class ServiceDataFactory {

    private Context context;
    private String serviceDataKey;

    public ServiceDataFactory(Context context, String serviceDataKey) {
        this.context = context;
        this.serviceDataKey = serviceDataKey;
    }

    public IServiceData create(String serviceDataKey) {

        IServiceData serviceData = null;

        if (serviceDataKey.equals(DataServiceLocatorKey.SERVICE_LOCAL)) {
            serviceData = new LocalWebAPIService(context);
        } else {
            if (serviceDataKey.equals(DataServiceLocatorKey.SERVICE_CLOUD)) {
                serviceData = new CloudWebAPIService(context);
            } else {
                //userDataStore = new LocalUserDataStore();
            }
        }

        return serviceData;
    }
}
