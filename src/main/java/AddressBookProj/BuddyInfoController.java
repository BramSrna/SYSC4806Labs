package AddressBookProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Null;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BuddyInfoController {
    @Autowired
    private AddressBookRepository bookRepo;

    @Autowired
    private BuddyInfoRepository buddyRepo;

    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/addBuddy")
    public BuddyInfo addBuddy(@RequestParam(value = "name") String name,
                              @RequestParam(value = "phoneNumber") int phoneNumber,
                              @RequestParam(value = "address") String address,
                              @RequestParam(value = "bookId") int bookId) {
        BuddyInfo newBuddy = null;

        Optional<AddressBook> checkBook = bookRepo.findById(bookId);
        if (checkBook.isPresent()){
            AddressBook book = checkBook.get();

            newBuddy = new BuddyInfo(name, phoneNumber, address);
            newBuddy.setId(Math.toIntExact(counter.incrementAndGet()));

            book.addBuddy(newBuddy);

            bookRepo.save(book);
        } else {
            System.out.println(String.format("No AddressBook with id: %d", bookId));

            for (AddressBook tempBook : bookRepo.findAll()){
                System.out.println(tempBook.toString());
            }
        }

        return newBuddy;
    }

    @GetMapping("/removeBuddy")
    public Boolean removeBuddy(@RequestParam(value = "buddyId") int buddyId,
                                 @RequestParam(value = "bookId") int bookId) {
        Boolean retVal = false;

        BuddyInfo newBuddy = null;

        Optional<AddressBook> checkBook = bookRepo.findById(bookId);
        if (checkBook.isPresent()){
            AddressBook book = checkBook.get();

            book.removeBuddyWithId(buddyId);

            bookRepo.save(book);
            buddyRepo.deleteById(buddyId);

            retVal = true;
        } else {
            System.out.println(String.format("No AddressBook with id: %d", bookId));

            for (AddressBook tempBook : bookRepo.findAll()){
                System.out.println(tempBook.toString());
            }
        }

        return retVal;
    }
}
