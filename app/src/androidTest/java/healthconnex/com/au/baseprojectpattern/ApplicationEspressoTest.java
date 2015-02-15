package healthconnex.com.au.baseprojectpattern;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import healthconnex.com.au.baseprojectpattern.commun.ErrorBundle;
import healthconnex.com.au.baseprojectpattern.model.ReleaseNoteItem;
import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.datasource.TestUserDataStore;
import healthconnex.com.au.baseprojectpattern.repository.UserDataRepository;
import healthconnex.com.au.baseprojectpattern.servicesource.TestWebAPIService;
import healthconnex.com.au.baseprojectpattern.services.ServiceData.IWebAPIService;
import healthconnex.com.au.baseprojectpattern.services.WebAPIService;
import healthconnex.com.au.baseprojectpattern.ui.main.MainActivity;
import volley.IErrorVolleyCallBack;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by frincon on 10/02/2015.
 */
//http://www.vogella.com/tutorials/AndroidTesting/article.html
//http://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
//Test Asych task but with UI
public class ApplicationEspressoTest extends ActivityInstrumentationTestCase2<MainActivity> {

    //Good link
    //http://junit.sourceforge.net/javadoc/org/junit/Assert.html
    private MainActivity activity;

    public ApplicationEspressoTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    //Test if hello world test is available
    public void testHelloWorldVisible() {
        ViewInteraction helloWorldText;
        helloWorldText = onView(withText("Hello world!")); // Find the text
        helloWorldText.check(ViewAssertions.matches(isDisplayed())); // Assert text is displayed
    }

    //Unit Test: Test data source
    public void testDataSourceTest() {
        UserDataRepository.resetInstance(); //reset UserDataRepository to be able to inject our own DataRepository
        TestUserDataStore testUserDataStore = new TestUserDataStore(activity);
        UserDataRepository userDataRepository = UserDataRepository.getInstance(activity, testUserDataStore);
        User userLocal = userDataRepository.getUserById(1);
        String resultTest = "User info from TestUserDataStore "+ " : " + userLocal.toString();
        Log.e("", resultTest);
        assertEquals("Information is correct", "DummyTestName",userLocal.getFirstName());
    }

    //Unit Test: Test webService cloud for dev: Cloud
    public void testWebServiceCloudTest() throws Exception {

        //Define the result expected (It is depends of the actual service locator - dev run cloud qa run local - prod run server)
        String resultExpectedCompleteName =  activity.getString(R.string.testWebServiceCloudTest_expected); //

        WebAPIService.resetInstance(); //reset WebAPI to be able to inject our own WebService

        // create CountDownLatch for which the test can wait.
        final CountDownLatch latch = new CountDownLatch(1);

        //Create Call back to be able to manage call back pattern
        IWebAPIService.UserWebServiceCallback callBackTest = new CallBackTest(latch,resultExpectedCompleteName);
        IErrorVolleyCallBack volleyErrorCallBack = new CallBackTest(latch, resultExpectedCompleteName);

        WebAPIService webAPIService = WebAPIService.getInstance(activity, activity.getString(R.string.service_key));
        webAPIService.getUserInformation("MARCOS",callBackTest,volleyErrorCallBack);

    }

    //Unit Test: Test webService local : Asyc Task
    public void testWebServiceLocalTest() throws Exception {

        //Define the result expected //Because we overrride the service we know what is expected
        //String resultExpectedCompleteName =  "{\"FirstName\":\"TestLocalServiceName\",\"Organization\":\"Telstra\",\"LastName\":\"TestLocalServiceLastName\",\"Id\":1}";
        String resultExpectedCompleteName =  "TestLocalServiceName" + " " + "TestLocalServiceLastName";

        WebAPIService.resetInstance(); //reset WebAPI to be able to inject our own WebService

        // create CountDownLatch for which the test can wait.
        final CountDownLatch latch = new CountDownLatch(1);

        //Create Call back to be able to manage call back pattern
        IWebAPIService.UserWebServiceCallback callBackTest = new CallBackTest(latch, resultExpectedCompleteName);
        IErrorVolleyCallBack volleyErrorCallBack = new CallBackTest(latch, resultExpectedCompleteName);

        //Execute Aych test that call the webService
        TestWebAPIService testWebAPIService = new TestWebAPIService(activity);
        WebAPIService webAPIService = WebAPIService.getInstance(activity, testWebAPIService);
        webAPIService.getUserInformation("MARCOS",callBackTest, volleyErrorCallBack);

        latch.await();// wait for callback

    }

    class CallBackTest implements  IWebAPIService.UserWebServiceCallback, IErrorVolleyCallBack {

        private CountDownLatch latch;
        private String resultExpected;

        public CallBackTest(CountDownLatch latch, String resultExpected) {
            this.latch = latch;
            this.resultExpected = resultExpected;
        }

        @Override
        public void onUserServiceDataLoaded(User userInfo) {
            String resultTest = "User info from TestServiceWeb "+ " : " + userInfo.toString();
            Log.e("", resultTest);
            String completeName = userInfo.getFirstName() + " " + userInfo.getLastName();

            assertEquals("webService Result correct",resultExpected, completeName);

            latch.countDown();// notify the count down latch
        }

        @Override
        public void onErrorService(ErrorBundle errorBundle) {
            assertEquals(true,false);//Launch error
            latch.countDown();// notify the count down latch
        }

        @Override
        public void onReleaseNoteLoaded(ArrayList<ReleaseNoteItem> releaseNoteList) {

        }

        @Override
        public void volleyResponseError(String messageError) {
            assertEquals(true,false);//Launch error
            latch.countDown();// notify the count down latch
        }
    }




}
