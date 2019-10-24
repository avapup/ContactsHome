package net.homecredit.trainee.oberfrancova.repository;

import net.homecredit.trainee.oberfrancova.entity.Contact;

import java.util.Comparator;
import java.util.List;

public interface ContactRepository {

    List<Contact> findAll();

    List<Contact> findAll(Comparator<Contact> comparator);

    Contact findContact(long id);

    //Contact findContactById(Contact contact);
    //Contact findContactByFirstName(Contact contact);
    //Contact findContactByLastName(Contact contact);

    Contact addContact(Contact contact);

    Contact updateContact(Contact contact);

    Contact deleteContact(Contact contact);

}
