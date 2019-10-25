package net.homecredit.trainee.oberfrancova.repository;

import net.homecredit.trainee.oberfrancova.entity.Contact;
import net.homecredit.trainee.oberfrancova.entity.Person;
import net.homecredit.trainee.oberfrancova.entity.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class InMemoryContactRepository implements ContactRepository {

    private static final int MAX_SIZE_OF_IDS = 10_000;
    private static final String CSV_RESOURCE_NAME = "contactList.csv";

    private List<Contact> contactList;

// CONSTRUCTOR WITH RESOURCE ===========================================================================================

    public InMemoryContactRepository() {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            contactList = loadContactsFromCsv(classLoader.getResourceAsStream(CSV_RESOURCE_NAME));
        } catch (IOException e) {
            // TODO: spravne spracovat vynimku
            throw new RuntimeException(e);
        }

        initIdsForPreparedData();
    }

    public void initIdsForPreparedData() {
        for (int i = 1, contactListSize = contactList.size(); i < contactListSize + 1; i++) {
            contactList.get(i - 1).setContactId(i);
        }
    }

// RESOURCE PROCESSING =================================================================================================

    private List<Contact> loadContactsFromCsv(InputStream fileName) throws IOException {
        final List<Contact> csvLines = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileName, StandardCharsets.UTF_8))) {
            String line = bufferedReader.readLine();

            while (line != null) {
                csvLines.add(parseLine(line));
                line = bufferedReader.readLine();
            }
        }

        return csvLines;
    }

    private Contact parseLine(String line) {

        String[] splitString = StringUtils.split(line, ";");

        String firstName = splitString[0];
        String lastName = splitString[1];
        String number = splitString[2];

        //Person person = new Person(firstName, lastName);
        //PhoneNumber phoneNumber = new PhoneNumber(number);
        return new Contact(new Person(firstName, lastName), new PhoneNumber(number));
    }

// GENERATE ID =========================================================================================================

    private long generateId() {
        long newId = Math.round(Math.random() * InMemoryContactRepository.MAX_SIZE_OF_IDS);

        while (isIdUsed(newId)) {
            newId = Math.round(Math.random() * InMemoryContactRepository.MAX_SIZE_OF_IDS);
        }

        return newId;
    }

    private boolean isIdUsed(long id) {

        return contactList.stream()
                .map(Contact::getContactId)
                .anyMatch(listId -> listId == id);
    }

// ALL =================================================================================================================

    // TODO: to implement this method for a lot a of contacts => paging
    @Override
    public List<Contact> findAll() {

        return contactList;
    }

    @Override
    public List<Contact> findAll(Comparator<Contact> comparator) {
        final List<Contact> sortedContactList = findAll();
        sortedContactList.sort(comparator);
        return sortedContactList;
    }

// FIND ONE ============================================================================================================

    @Override
    public Contact findContact(long id) {

        for (Contact contact : contactList) {
            if (contact.getContactId() == id) {
                return contact;
            }
        }
        return null;
    }

// ADD =================================================================================================================

    @Override
    public Contact addContact(Contact contact) {
        long newId = generateId();
        contact.setContactId(newId);
        contactList.add(contact);

        return contact;
    }

// EDIT ================================================================================================================

    @Override
    public Contact updateContact(Contact contact) {

        final Contact contactToEdit = findContact(contact.getContactId());
        /*
        final Contact contactToEdit = contactList.stream()
                .filter(c -> c.getContactId() == contact.getContactId())
                .findFirst()
                .orElse(null);
        */
        if (contactToEdit == null) {
            System.out.println("Warning: no contact with ID = " + contact.getContactId() + " to edit found.");
        }

        contactToEdit.getPerson().setFirstName(contact.getPerson().getFirstName());
        contactToEdit.getPerson().setLastName(contact.getPerson().getLastName());
        contactToEdit.getPhoneNumber().setPrefix(contact.getPhoneNumber().getPrefix());
        contactToEdit.getPhoneNumber().setNumber(contact.getPhoneNumber().getNumber());
        contactToEdit.getPhoneNumber().setPhoneNumber(contactToEdit.getPhoneNumber().getPrefix().toString(), contactToEdit.getPhoneNumber().getNumber());

        return contactToEdit;
    }

// DELETE ==============================================================================================================

    @Override
    public Contact deleteContact(Contact contact) {

        final Contact contactToDelete = findContact(contact.getContactId());

        if (contactToDelete == null) {
            System.out.println("Warning no contact with ID = " + contact.getContactId() + " to remove found");
        }

        contactList.remove(contactToDelete);

        return contactToDelete;

    }

// CONSTRUCTOR WITHOUT RESOURCE ========================================================================================
    /*
    public InMemoryContactRepository() {
        contactList.add(new Contact(1, new Person("Karel", "Dvorak"), new PhoneNumber("+420603112233")));
        contactList.add(new Contact(11, new Person("Frantisek", "Vesely"), new PhoneNumber("+420608222233")));
        contactList.add(new Contact(21, new Person("Stanislav", "Zeleny"), new PhoneNumber("608113333")));
        contactList.add(new Contact(31, new Person("Petr", "Chlumsky"), new PhoneNumber("+420608112245")));
        contactList.add(new Contact(41, new Person("Slavomir", "Labsky"), new PhoneNumber("777112233")));
        contactList.add(new Contact(51, new Person("Ferdinand", "Este"), new PhoneNumber("+420777123456")));
        contactList.add(new Contact(61, new Person("Daniel", "Nekonecny"), new PhoneNumber("+420604112233")));
        contactList.add(new Contact(71, new Person("Jan", "Ledecky"), new PhoneNumber("00420605112233")));
        contactList.add(new Contact(81, new Person("Cyril", "Svoboda"), new PhoneNumber("+420776556677")));
        contactList.add(new Contact(91, new Person("Bozena", "Nemcova"), new PhoneNumber("+420602112233")));
        contactList.add(new Contact(10, new Person("Marta", "Kropackova"), new PhoneNumber("00420603111111")));
    }
    */
// SINGLETON ===========================================================================================================
    /*
    private static InMemoryContactRepository instance = new InMemoryContactRepository();

    public static ContactRepository getInstance() {
        return instance;
    }
    */
}

