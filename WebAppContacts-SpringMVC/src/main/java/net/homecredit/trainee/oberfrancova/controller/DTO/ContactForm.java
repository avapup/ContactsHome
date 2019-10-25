package net.homecredit.trainee.oberfrancova.controller.DTO;

public class ContactForm {

    private long contactId;
    private String firstName;
    private String lastName;
    private String prefix;
    private String number;
    //private String phoneNumber;

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    /*
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String prefix, String number) {
        this.phoneNumber = prefix + number;
    }
    */
}
