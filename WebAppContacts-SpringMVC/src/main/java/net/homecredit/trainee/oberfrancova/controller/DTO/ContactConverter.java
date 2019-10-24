package net.homecredit.trainee.oberfrancova.controller.DTO;

import net.homecredit.trainee.oberfrancova.entity.Contact;
import net.homecredit.trainee.oberfrancova.entity.Person;
import net.homecredit.trainee.oberfrancova.entity.PhoneNumber;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    public Contact convert(ContactForm contactForm) {

        Contact contact = new Contact();

        contact.setContactId(contactForm.getContactId());
        contact.setPerson(new Person(contactForm.getFirstName(), contactForm.getLastName()));
        contact.setPhoneNumber(new PhoneNumber(contactForm.getPhoneNumber()));

        return contact;

    }

    public ContactForm convert(Contact entity) {

        ContactForm contactForm = new ContactForm();

        contactForm.setContactId(entity.getContactId());
        contactForm.setFirstName(entity.getPerson().getFirstName());
        contactForm.setLastName(entity.getPerson().getLastName());
        contactForm.setPhoneNumber(entity.getPhoneNumber().getPhoneNumber());

        return contactForm;

    }

    /*
    public List<ContactForm> convert(List<Contact> entities) {
        List<ContactForm> contactForms = new ArrayList<>(entities.size());
        for (Contact entity : entities) {
            contactForms.add(convert(entity));
        }
        return contactForms;
    }
    */

}
