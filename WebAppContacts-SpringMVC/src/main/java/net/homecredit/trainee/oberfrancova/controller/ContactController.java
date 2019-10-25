
package net.homecredit.trainee.oberfrancova.controller;

import net.homecredit.trainee.oberfrancova.controller.DTO.ContactConverter;
import net.homecredit.trainee.oberfrancova.controller.DTO.ContactForm;
import net.homecredit.trainee.oberfrancova.entity.Contact;
import net.homecredit.trainee.oberfrancova.repository.ContactRepository;
import net.homecredit.trainee.oberfrancova.services.LocalizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;
    private final LocalizationService localizationService;
    //private Map<String, String> allLocalizationMap;
    //private Map<String, String> editLocalizationMap;

    public ContactController(ContactRepository contactRepository, ContactConverter contactConverter, LocalizationService localizationService) {
        this.contactRepository = contactRepository;
        this.contactConverter = contactConverter;
        this.localizationService = localizationService;
    }
    /*
    private Map<String, String> createAllLocalization(LocalizationService localizationService) {
        allLocalizationMap = new HashMap<>();

        return allLocalizationMap;

    }
    */

// ALL =================================================================================================================

    @RequestMapping(value = "/contacts/all.html", method = RequestMethod.GET)
    public ModelAndView showContactList() {

        List<Contact> contactList = contactRepository.findAll(new Contact.ContactsOrderedByPersonLastNameComparator());

        //Map<String, String> map = new HashMap<>();
        List<String> allLocalizedExpressions = Arrays.asList(
                localizationService.getLocalizedStringFromBundleByKey("all.h1"),
                localizationService.getLocalizedStringFromBundleByKey("all.a.button_add"),
                localizationService.getLocalizedStringFromBundleByKey("all.table.column_id"),
                localizationService.getLocalizedStringFromBundleByKey("all.table.column_first_name"),
                localizationService.getLocalizedStringFromBundleByKey("all.table.column_last_name"),
                localizationService.getLocalizedStringFromBundleByKey("all.table.column_phone_number"),
                localizationService.getLocalizedStringFromBundleByKey("all.table.column_action"),
                localizationService.getLocalizedStringFromBundleByKey("all.a.button_edit"),
                localizationService.getLocalizedStringFromBundleByKey("all.a.button_delete"));


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contactList", contactList);
        //modelAndView.addAllObjects(map);
        modelAndView.addObject("localizedExpressions", allLocalizedExpressions);
        /*
        modelAndView.addAllObjects(map).getModel().keySet().forEach(System.out::println);
        modelAndView.getModelMap().keySet().forEach(System.out::println);
        modelAndView.getModelMap().values().forEach(System.out::println);
        */
        modelAndView.setViewName("/WEB-INF/view/contacts/all.jsp");

        return modelAndView;
    }

// EDIT ================================================================================================================

    @RequestMapping(value = "/contacts/edit.html", method = RequestMethod.GET, params = "id")
    public ModelAndView showEditForm(@RequestParam long id) {

        Contact contact = contactRepository.findContact(id);

        List<String> editLocalizedExpressions = Arrays.asList(
                localizationService.getLocalizedStringFromBundleByKey("edit.h1"),
                localizationService.getLocalizedStringFromBundleByKey("edit.form.input_first_name"),
                localizationService.getLocalizedStringFromBundleByKey("edit.form.input_last_name"),
                localizationService.getLocalizedStringFromBundleByKey("edit.form.input_phone_number"),
                localizationService.getLocalizedStringFromBundleByKey("edit.form.button_add"),
                localizationService.getLocalizedStringFromBundleByKey("edit.form.button_update"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("contact", contact);
        modelAndView.addObject("localizedExpressions", editLocalizedExpressions);
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


