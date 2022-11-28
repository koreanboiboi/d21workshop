package workshop1.workshop21.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {

    private Integer id;
    private String company;
    private String lastName;
    private String firstName;
    private String email;
    private String jobTitle;
    private String businessPhone;
    private String homePhone;
    private String mobilePhone;
    private String address;
    private String city;
    private String stateProvince;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getBusinessPhone() {
        return businessPhone;
    }
    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getStateProvince() {
        return stateProvince;
    }
    public void setStateProvince(String stateProvince) {
        this.stateProvince = stateProvince;
    }
    
    public static Customer create(SqlRowSet rs){

        Customer c = new Customer();
        c.setId(rs.getInt("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setCompany(rs.getString("company"));
        c.setAddress(rs.getString("address"));
        c.setBusinessPhone(rs.getString("business_phone"));
        c.setCity(rs.getString("city"));
        c.setEmail(rs.getString("email"));
        c.setHomePhone(rs.getString("home_phone"));
        c.setMobilePhone(rs.getString("mobile_phone"));
        c.setJobTitle(rs.getString("job_title"));
        c.setStateProvince(rs.getString("state_province"));
        
        return c;
        
    }

    public JsonObject toJson(){

        return Json.createObjectBuilder().add("id", getId())
                                         .add("company", getCompany())
                                         .add("last_name", getLastName())
                                         .add("first_name", getFirstName())
                                         .build();

    }
    
}
