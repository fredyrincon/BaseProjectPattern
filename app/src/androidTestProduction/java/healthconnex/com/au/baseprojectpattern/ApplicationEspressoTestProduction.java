package healthconnex.com.au.baseprojectpattern;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

import healthconnex.com.au.baseprojectpattern.model.User;
import healthconnex.com.au.baseprojectpattern.datasource.TestProductionUserDataStore;
import healthconnex.com.au.baseprojectpattern.datasource.TestUserDataStore;
import healthconnex.com.au.baseprojectpattern.repository.UserDataRepository;
import healthconnex.com.au.baseprojectpattern.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by frincon on 10/02/2015.
 */
public class ApplicationEspressoTestProduction extends ActivityInstrumentationTestCase2<MainActivity> {

    //Good link
    //http://junit.sourceforge.net/javadoc/org/junit/Assert.html
    private MainActivity activity;

    public ApplicationEspressoTestProduction() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        activity = getActivity();
    }

    //Test if hello world test is available
    public void testHelloWorldVisibleProduction() {
        ViewInteraction helloWorldText;
        helloWorldText = onView(withText("Hello world!")); // Find the text

        helloWorldText.check(ViewAssertions.matches(isDisplayed())); // Assert text is displayed
    }

    //Test DataSource test component locator
    public void testDataSourceTestProduction() {
        UserDataRepository.resetInstance();
        TestProductionUserDataStore testProductionUserDataStore = new TestProductionUserDataStore(activity);
        UserDataRepository userDataRepository = UserDataRepository.getInstance(activity, testProductionUserDataStore);
        User userLocal = userDataRepository.getUserById(1);
        String resultTest = "User info from TestUserDataStore "+ " : " + userLocal.toString();
        Log.e("", resultTest);
        assertEquals("Information is correct", "DummyProductionName",userLocal.getFirstName());
    }


}

