package AddressBookProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ThymeController {
    @Autowired
    private AddressBookRepository bookRepo;

    @GetMapping("viewAddressBook")
    public String viewAddressBook(@RequestParam(value = "id") int bookId,
                                  Model model) {
        if (bookId == -1) {
            for (AddressBook book : bookRepo.findAll()) {
                bookId = book.getId();
            }
        }

        Optional<AddressBook> checkBook = bookRepo.findById(bookId);
        if (checkBook.isPresent()){
            model.addAttribute("book", checkBook.get());
        } else {
            System.out.println(String.format("No AddressBook with id: %d", bookId));
        }

        return "AddressBook";
    }

}
