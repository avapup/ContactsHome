
package net.homecredit.trainee.oberfrancova.entity;

import java.util.Objects;

public class PhoneNumber {

    private String phoneNumber;
    private PhoneNumberPrefix prefix;
    private String number;

// =====================================================================================================================

    // TODO: skusit odstranit tento konstruktor a vyskusat funkcionalitu
    public PhoneNumber() {

    }

    public PhoneNumber(String phoneNumber) {
        /*
        if (phoneNumber.startsWith("00") && (phoneNumber.length() == 14)) {
           this.phoneNumber = "+" + phoneNumber.substring(2);
        } else if (phoneNumber.startsWith("09") && (phoneNumber.length() == 10)) {
            this.phoneNumber = PhoneNumberPrefix.SK.getPrefix() + phoneNumber.substring(1);
        } else if (!phoneNumber.startsWith(PhoneNumberPrefix.CZ.getPrefix()) && !phoneNumber.startsWith(PhoneNumberPrefix.SK.getPrefix()) && (phoneNumber.length() == 9)) {
            this.phoneNumber = PhoneNumberPrefix.CZ.getPrefix() + phoneNumber;
        } else if  (phoneNumber.length() == 13){
            this.phoneNumber = phoneNumber;
        }
        */
        int stringLength = phoneNumber.length();

        if (stringLength == 9) {
            this.prefix = PhoneNumberPrefix.CZ;
            this.number = phoneNumber;

        } else if (stringLength == 10) {
            this.prefix = PhoneNumberPrefix.SK;
            this.number = phoneNumber.substring(1);

        } else if (stringLength == 13) {
            if (phoneNumber.substring(0, 3).contains(PhoneNumberPrefix.CZ.getPrefix())) {
                this.prefix = PhoneNumberPrefix.CZ;
            } else {
                this.prefix = PhoneNumberPrefix.SK;
            }
            this.number = phoneNumber.substring(4);

        } else if (stringLength == 14) {
            if (phoneNumber.substring(2, 4).contains(PhoneNumberPrefix.CZ.getPrefix().substring(1))) {
                this.prefix = PhoneNumberPrefix.CZ;
            } else {
                this.prefix = PhoneNumberPrefix.SK;
            }
            this.number = phoneNumber.substring(5);

        } else {
            throw new IllegalArgumentException("No valid input phone number.");
        }

        this.phoneNumber = prefix.getPrefix() + number;

    }

    // =====================================================================================================================

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String prefix, String number) {

        this.phoneNumber = prefix + number;
    }

    public PhoneNumberPrefix getPrefix() {
        return prefix;
    }

    public void setPrefix(PhoneNumberPrefix prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    // =====================================================================================================================

    @Override
    public String toString() {

        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        PhoneNumber that = (PhoneNumber) o;

        return phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(phoneNumber);
    }
}



