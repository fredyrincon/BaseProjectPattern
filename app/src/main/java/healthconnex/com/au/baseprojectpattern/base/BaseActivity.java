package healthconnex.com.au.baseprojectpattern.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import healthconnex.com.au.baseprojectpattern.repository.dao.DatabaseManager;
import healthconnex.com.au.versionmanager.WVersionManager;
import healthconnex.com.au.baseprojectpattern.R;
import healthconnex.com.au.baseprojectpattern.repository.UserDataRepository;
import healthconnex.com.au.baseprojectpattern.services.WebAPIService;

/**
 * Created by frincon on 10/02/2015.
 */
public class BaseActivity  extends ActionBarActivity {

    //Services locator for database and webAPI
    protected UserDataRepository userDataRepository;
    protected WebAPIService webAPIService;

    // Service URL To Check version update
    public String URL_VERSION_CHECK = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Start the database instance
        DatabaseManager.init(this);

        userDataRepository = UserDataRepository.getInstance(this, getString(R.string.data_source_key));
        webAPIService = WebAPIService.getInstance(this, getString(R.string.service_key));
        URL_VERSION_CHECK = getString(R.string.service_version_check) + "?appName=testname&organizationName=org";

    }

    protected void checkVersion() {
        WVersionManager versionManager = new WVersionManager(this);

        versionManager.setVersionContentUrl(URL_VERSION_CHECK);
        versionManager.setUpdateNowLabel("Update now");
        versionManager.setRemindMeLaterLabel("Later");
        versionManager.setIgnoreThisVersionLabel("Ignore this version");
        versionManager.setReminderTimer(Integer.valueOf(1));

        versionManager.checkVersion();
    }

}
