package healthconnex.com.au.baseprojectpattern.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;

import de.greenrobot.event.EventBus;
import healthconnex.com.au.baseprojectpattern.base.BaseActivity;
import healthconnex.com.au.baseprojectpattern.busevent.NetworkStateChanged;
import healthconnex.com.au.baseprojectpattern.commun.ErrorBundle;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.R;
import healthconnex.com.au.baseprojectpattern.repository.userdata.IUserRepository;
import healthconnex.com.au.baseprojectpattern.repository.UserDataRepository;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IWebAPIService;
import healthconnex.com.au.baseprojectpattern.ui.version.ReleaseNoteActivity;


public class MainActivity extends BaseActivity implements IUserRepository.UserDetailsCallback, IUserRepository.UserListCallback , IWebAPIService.UserWebServiceCallback{

    public TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EventBus.getDefault().register(this); // register EventBus

        //Get UI reference
        mResult = (TextView) findViewById(R.id.result);


       //Check for apk updates
        checkVersion ();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this); // unregister EventBus
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    // method that will be called when someone posts an event NetworkStateChanged
    public void onEventMainThread(NetworkStateChanged event) {
        if (!event.isInternetConnected()) {
            Toast.makeText(this, "EventBus - No Internet connection!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "EventBus - Internet connection is up!", Toast.LENGTH_SHORT).show();
        }
    }

    //print the information of the datasource local
    public void testLocalData(View v) {
        UserDataRepository.resetInstance();

        boolean resultAddUser = userDataRepository.addUser(new User(1,"Marcos","Toro","HealthConnex"));

        User userLocal = userDataRepository.getUserById(1);
        String resultTest = "User info from " + getString(R.string.data_source_key)+ " : " + userLocal.toString();
        Log.e("", resultTest);
        mResult.setText(resultTest);
    }

    //Function to test get user with CallBack (Database locally wont need callback.)
    public void getTestUserCallBack() {
        //Using Call back
        userDataRepository.getUserByIdCallBack(1, this);
    }

    //Services always req call back or async process
    //Print the information of the datasource cloud
    public void testCloudData(View v) {

        //Test the webservice
        webAPIService.getUserInformation("Marcos Toro",this);

        /*UserDataRepository.resetInstance();
        CloudUserDataStore cloudUserDataStore = new CloudUserDataStore(this);
        UserDataRepository userDataRepository = UserDataRepository.getInstance(this, cloudUserDataStore);
        User userLocal = userDataRepository.getUserById(1);
        String resultTest = "User info: " + userLocal.toString();
        Log.e("", resultTest);
        mResult.setText(resultTest);*/
    }

    //Show release note activity
    public void clickShowReleaseNote(View v) {
        Intent intent = new Intent(this, ReleaseNoteActivity.class);
        startActivity(intent);
    }

    @Override
    public void onUserLoaded(User user) {
        String resultTest = "User info call back from " + getString(R.string.data_source_key)+ " : " + user.toString();
        Log.e("", resultTest);
        mResult.setText(resultTest);
    }

    @Override
    public void onUserListLoaded(Collection<User> usersCollection) {

    }

    @Override
    public void onError(ErrorBundle errorBundle) {

    }

    @Override
    public void onUserServiceDataLoaded(User userInfo) {
        String resultTest = "onUserServiceDataLoaded from " + getString(R.string.service_key)+ " : " + userInfo.toString();
        Log.e("", resultTest);
        mResult.setText(resultTest);
    }

    @Override
    public void onErrorService(ErrorBundle errorBundle) {

    }

    @Override
    public void volleyReponseError(String message) {
        String resultTest = "error from " + getString(R.string.service_key)+ " : " + message;
        Log.e("", resultTest);
        mResult.setText(resultTest);
    }

    @Override
    public void onReleaseNoteLoaded(ArrayList<ReleaseNoteItem> releaseNoteList) {
        //Show the information of the release note


    }
}
