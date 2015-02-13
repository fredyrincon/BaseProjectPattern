package healthconnex.com.au.baseprojectpattern.datamodel;

/**
 * Created by frincon on 12/02/2015.
 */
public class ReleaseNoteItem {
    public String AppName;
    public String VersionCode;
    public String Type;
    public String Description;

    //Default As well for Volley
    public ReleaseNoteItem(){

    }

    public ReleaseNoteItem(String appName, String versionCode, String type, String description) {
        AppName = appName;
        VersionCode = versionCode;
        Type = type;
        Description = description;
    }


    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public String getVersionCode() {
        return VersionCode;
    }

    public void setVersionCode(String versionCode) {
        VersionCode = versionCode;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }



}
