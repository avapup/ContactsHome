package net.homecredit.trainee.oberfrancova.entity;

import java.util.*;

public class Contact {

    private long contactId;
    private Person person;
    private PhoneNumber phoneNumber;

// =====================================================================================================================

    public Contact() {

    }

    public Contact(Person person, PhoneNumber phoneNumber) {
        this.person = person;
        this.phoneNumber = phoneNumber;
    }

// =====================================================================================================================

    public long getContactId() {

        return contactId;
    }

    public void setContactId(long contactId) {

        this.contactId = contactId;
    }
    public Person getPerson() {

        return person;
    }

    public PhoneNumber getPhoneNumber() {

        return phoneNumber;
    }

    public void setPerson(Person person) {

        this.person = person;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {

        this.phoneNumber = phoneNumber;
    }

// =====================================================================================================================

    @Override
    public String toString() {

        return "Contact{" +
                "contactId=" + contactId +
                ", person=" + person +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return contactId == contact.contactId &&
                Objects.equals(person, contact.person) &&
                Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(contactId, person, phoneNumber);
    }

// =====================================================================================================================

    public static class ContactsOrderedByPersonLastNameComparator implements Comparator<Contact> {

        private Comparator<Person> personComparator = new Person.LastNameComparator();

        @Override
        public int compare(Contact o1, Contact o2) {

            return personComparator.compare(o1.getPerson(), o2.getPerson());
        }
    }
/*
    public static class ContactsOrderedByPersonFirstNameComparator implements Comparator<Contact> {

        private Comparator<Person> personComparator = new Person.FirstNameComparator();

        @Override
        public int compare(Contact o1, Contact o2) {

            return personComparator.compare(o1.getPerson(), o2.getPerson());
        }
    }
*/
}
