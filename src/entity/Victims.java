package entity;

import java.util.Date;

public class Victims {
    private int victimID;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String gender;
    private String contactInformation;

    // Constructor
    public Victims(){

    }

    public Victims(int victimID, String firstName, String lastName, Date dateOfBirth, String gender, String contactInformation) {
        this.victimID = victimID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.contactInformation = contactInformation;
    }



    // getters and setters
    public int getVictimID() {
        return victimID;
    }
    public void setVictimID(int victimID) {
        this.victimID = victimID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getContactInformation() {
        return contactInformation;
    }
    public void setContactInformation(String contactInformation) {
        this.contactInformation = contactInformation;
    }

    @Override
    public String toString() {
        return "Victims [victimId=" + victimID + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dateOfBirth
                + ", gender=" + gender + ", Contact Information" + contactInformation + "]";
    }
}