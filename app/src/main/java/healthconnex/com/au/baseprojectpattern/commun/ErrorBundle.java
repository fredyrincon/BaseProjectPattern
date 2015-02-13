package healthconnex.com.au.baseprojectpattern.commun;

/**
 * Created by frincon on 9/02/2015.
 */
public interface ErrorBundle {
    Exception getException();

    String getErrorMessage();
}