package net.homecredit.trainee.oberfrancova.entity;

import java.util.Comparator;
import java.util.Objects;

public class Person {

    private String firstName;
    private String lastName;

// =====================================================================================================================

    public Person() {

    }

    public Person(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

// =====================================================================================================================

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

// =====================================================================================================================

    @Override
    public String toString() {

        return firstName + ' ' + lastName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName);
    }

// =====================================================================================================================

    public static class LastNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {

            int result = o1.getLastName().compareTo(o2.getLastName());

            if (result != 0) return result;

            return o1.getFirstName().compareTo(o2.getFirstName());
        }
    }
/*
    public static class FirstNameComparator implements Comparator<Person> {

        @Override
        public int compare(Person o1, Person o2) {

            int result = o1.getFirstName().compareTo(o2.getFirstName());

            if (result != 0) return result;

            return o1.getLastName().compareTo(o2.getLastName());
        }
    }
*/
}

