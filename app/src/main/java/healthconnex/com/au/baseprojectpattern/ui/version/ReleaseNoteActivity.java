package healthconnex.com.au.baseprojectpattern.ui.version;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import healthconnex.com.au.baseprojectpattern.base.BaseActivity;
import healthconnex.com.au.baseprojectpattern.commun.ErrorBundle;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.R;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IWebAPIService;
import healthconnex.com.au.baseprojectpattern.adaptor.ReleaseNoteAdapter;

public class ReleaseNoteActivity extends BaseActivity implements IWebAPIService.UserWebServiceCallback {

    private ListView releaseNotesList;
    private TextView titleRelease;

    private ReleaseNoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.version_activity_release_note);

        //Get ui references
        releaseNotesList = (ListView) findViewById(R.id.releaseNoteList);
        titleRelease = (TextView) findViewById(R.id.titleRelease);

        titleRelease.setText("What's new on 2.0.0?");

        //Show spinner
        getReleaseNoteData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.version_menu_release_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //Function to get the data from the server
    public void getReleaseNoteData() {
        //Release note WebService
        webAPIService.getReleaseNote("MCM","HealthConnex", "1.1.1",this);
    }


    @Override
    public void onUserServiceDataLoaded(User userInfo) {

    }

    @Override
    public void onErrorService(ErrorBundle errorBundle) {

    }

    @Override
    public void volleyReponseError(String message) {

    }

    @Override
    public void onReleaseNoteLoaded(ArrayList<ReleaseNoteItem> releaseNoteList) {

        adapter = new ReleaseNoteAdapter(this, releaseNoteList);
        releaseNotesList.setAdapter(adapter);

    }
}
