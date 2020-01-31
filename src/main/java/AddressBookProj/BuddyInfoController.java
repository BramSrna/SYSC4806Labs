package AddressBookProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        BuddyInfo newBuddy = new BuddyInfo(name, phoneNumber, address);
        newBuddy.setId(Math.toIntExact(counter.incrementAndGet()));

        AddressBook book = bookRepo.findById(bookId);
        book.addBuddy(newBuddy);

        return newBuddy;
    }
}
