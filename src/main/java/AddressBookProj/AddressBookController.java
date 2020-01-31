package AddressBookProj;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AddressBookController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/addressBook")
    public AddressBook addressBook() {
        AddressBook newBook = new AddressBook();
        newBook.setId(Math.toIntExact(counter.incrementAndGet()));
        return newBook;
    }
}
