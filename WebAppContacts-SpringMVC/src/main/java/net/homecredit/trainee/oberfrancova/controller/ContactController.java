
package net.homecredit.trainee.oberfrancova.controller;

import net.homecredit.trainee.oberfrancova.controller.DTO.ContactConverter;
import net.homecredit.trainee.oberfrancova.controller.DTO.ContactForm;
import net.homecredit.trainee.oberfrancova.entity.Contact;
import net.homecredit.trainee.oberfrancova.repository.ContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;

    public ContactController(ContactRepository contactRepository, ContactConverter contactConverter) {
        this.contactRepository = contactRepository;
        this.contactConverter = contactConverter;
    }

// ALL =================================================================================================================

    @RequestMapping("/contacts/all.html")
    public ModelAndView showContactList() {

        List<Contact> contactList = contactRepository.findAll(new Contact.ContactsOrderedByPersonLastNameComparator());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contactList", contactList);
        modelAndView.setViewName("/WEB-INF/view/contacts/all.jsp");

        return modelAndView;
    }

// EDIT ================================================================================================================

    @RequestMapping(value = "/contacts/edit.html", method = RequestMethod.GET, params = "id")
    public ModelAndView showEditForm(@RequestParam long id) {

        Contact contact = contactRepository.findContact(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("/WEB-INF/view/contacts/edit.jsp");

        return modelAndView;
    }

    @RequestMapping(value = "/contacts/edit.html", method = RequestMethod.POST)
    public ModelAndView editSelectedContact(ContactForm contactForm) {

        Contact contact = contactConverter.convert(contactForm);

        contactRepository.updateContact(contact);

        ModelAndView modelAndView = new ModelAndView("redirect:/contacts/all.html");

        return modelAndView;
    }

// ADD =================================================================================================================

    @RequestMapping(value = "/contacts/new.html", method = RequestMethod.GET)
    public ModelAndView showAddForm() {

        Contact contact = new Contact();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", contact);
        modelAndView.setViewName("/WEB-INF/view/contacts/edit.jsp");

        return modelAndView;
    }

    @RequestMapping(value = "/contacts/new.html", method = RequestMethod.POST)
    public ModelAndView addNewContact(ContactForm contactForm) {

        Contact contact = contactConverter.convert(contactForm);

        contactRepository.addContact(contact);

        ModelAndView modelAndView = new ModelAndView("redirect:/contacts/all.html");

        return modelAndView;
    }

// DELETE ==============================================================================================================

    @RequestMapping(value = "/contacts/delete.html", method = RequestMethod.POST, params = "id")
    public ModelAndView deleteSelectedContact(@RequestParam("id") long id) {

        Contact contact = contactRepository.findContact(id);

        contactRepository.deleteContact(contact);

        ModelAndView modelAndView = new ModelAndView("redirect:/contacts/all.html");

        return modelAndView;
    }

}


