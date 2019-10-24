
package net.homecredit.trainee.oberfrancova.entity;

import java.util.Objects;

public class PhoneNumber {

    //private final String prefixCz = "+420";
    //private final String prefixSk = "+421";

    private String phoneNumber;

// =====================================================================================================================

    // TODO: skusit odstranit tento konstruktor a vyskusat funkcionalitu
    public PhoneNumber() {

    }

    public PhoneNumber(String phoneNumber) {

        if (phoneNumber.startsWith("00") && (phoneNumber.length() == 14)) {
           this.phoneNumber = "+" + phoneNumber.substring(2);
        } else if (phoneNumber.startsWith("09") && (phoneNumber.length() == 10)) {
            this.phoneNumber = PhoneNumberPrefix.SK.getPrefix() + phoneNumber.substring(1);
        } else if (!phoneNumber.startsWith(PhoneNumberPrefix.CZ.getPrefix()) && !phoneNumber.startsWith(PhoneNumberPrefix.SK.getPrefix()) && (phoneNumber.length() == 9)) {
            this.phoneNumber = PhoneNumberPrefix.CZ.getPrefix() + phoneNumber;
        } else if  (phoneNumber.length() == 13){
            this.phoneNumber = phoneNumber;
        }
    }

// =====================================================================================================================

    public String getPhoneNumber() {

        return phoneNumber;
    }


    public void setPhoneNumber(String number) {

        this.phoneNumber = number;
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



