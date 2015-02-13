package healthconnex.com.au.baseprojectpattern.services.ServiceData;

import android.content.Context;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.base.Config;
import healthconnex.com.au.baseprojectpattern.datamodel.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.datamodel.User;

/**
 * Created by frincon on 11/02/2015.
 */
public class LocalWebAPIService implements IServiceData {

    private Context context;

    public LocalWebAPIService(Context context){
        this.context = context;
    }

    @Override
    public void getUserInformation(String userName, UserWebServiceCallback userServiceCallback) {
        //Create a dummy user
        try {
            User userDummy = new User(1, "DummyLocalServiceName", "DummyLocalServiceLastName", "Telstra");
            userServiceCallback.onUserServiceDataLoaded(userDummy);
        } catch (Exception e ) {
            userServiceCallback.onErrorService(e);
        }
    }

    @Override
    public void getReleaseNote(String appName, String organization, String versionCode, UserWebServiceCallback userServiceCallback) {
        try {
            ArrayList <ReleaseNoteItem> listRelease = new ArrayList<ReleaseNoteItem>();
            listRelease.add(new ReleaseNoteItem("MCM_Video", "2.0.0", Config.RELEASE_ITEM_FIX, "Release 1 fix"));
            listRelease.add(new ReleaseNoteItem("MCM_Video", "2.0.0", Config.RELEASE_ITEM_FIX, "Release 1 df ds fsd dsf g"));
            listRelease.add(new ReleaseNoteItem("MCM_Video", "2.0.0", Config.RELEASE_ITEM_IMPR, "Release 1 dfg dsf gsdf gds fgdsf dsfg dsfg "));
            listRelease.add(new ReleaseNoteItem("MCM_Video", "2.0.0", Config.RELEASE_ITEM_NEW, "Release 1dsf gds dsf gsdf gdsf gdsf gdsf gdsf gsdf gdsf "));
            userServiceCallback.onReleaseNoteLoaded(listRelease);
        } catch (Exception e ) {
            userServiceCallback.onErrorService(e);
        }
    }
}