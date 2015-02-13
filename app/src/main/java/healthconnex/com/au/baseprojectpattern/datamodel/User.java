package healthconnex.com.au.baseprojectpattern.datamodel;

import com.google.gson.Gson;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by frincon on 9/02/2015.
 */
@DatabaseTable(tableName = "users")
public class User {

    @DatabaseField(id = true, columnName = "id")
    public int Id;
    @DatabaseField (columnName = "firstName", canBeNull = false)
    public String FirstName;
    @DatabaseField (columnName = "lastName", canBeNull = false)
    public String LastName;
    @DatabaseField (columnName = "organization", canBeNull = false)
    public String Organization;
    @DatabaseField (columnName = "email", canBeNull = false)
    public String Email;

    public User(){
        // ORMLite needs a no-arg constructor
    }

    public User(int Id, String FirstName, String LastName, String Organization) {
        this.Id = Id;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Organization = Organization;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getOrganization() {
        return Organization;
    }

    public void setOrganization(String organization) {
        Organization = organization;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
