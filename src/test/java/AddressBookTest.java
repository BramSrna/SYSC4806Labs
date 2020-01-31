import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.List;

import static org.junit.Assert.*;

public class AddressBookTest {
    private AddressBook testBook;

    @Before
    public void setUp(){
        testBook = new AddressBook();
    }

    @After
    public void teardown(){
        testBook = null;
    }

    @Test
    public void testAddBuddy(){
        BuddyInfo newBuddy = new BuddyInfo("Paul", 123, "Main Street");

        testBook.addBuddy(newBuddy);

        assertEquals(testBook.getBuddy(0), newBuddy);
        assertEquals(testBook.getMyBuddies().size(), 1);
    }

    @Test
    public void testRemoveBuddy(){
        BuddyInfo newBuddy = new BuddyInfo("Paul", 123, "Main Street");

        testBook.addBuddy(newBuddy);
        testBook.removeBuddy(0);

        assertEquals(testBook.getMyBuddies().size(), 0);
    }

    @Test
    public void testClearBuddies(){
        BuddyInfo buddy1 = new BuddyInfo("Dan", 1, "Main Street");
        BuddyInfo buddy2 = new BuddyInfo("Paul", 2, "Main Street");
        BuddyInfo buddy3 = new BuddyInfo("George", 3, "Main Street");

        testBook.addBuddy(buddy1);
        testBook.addBuddy(buddy2);
        testBook.addBuddy(buddy3);
        testBook.clearBuddies();

        assertEquals(testBook.getMyBuddies().size(), 0);
    }

    @Test
    public void testPersistence() {
        BuddyInfo buddy1 = new BuddyInfo("Dan", 1, "Main Street");
        BuddyInfo buddy2 = new BuddyInfo("Paul", 2, "Main Street");
        BuddyInfo buddy3 = new BuddyInfo("George", 3, "Main Street");

        testBook.addBuddy(buddy1);
        testBook.addBuddy(buddy2);
        testBook.addBuddy(buddy3);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-test");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.persist(testBook);

        tx.commit();

        Query q = em.createQuery("SELECT book FROM AddressBook book");

        List<AddressBook> results = q.getResultList();

        System.out.println("List of Address Books\n-------------");

        for (AddressBook book : results) {
            System.out.println(book.toString());
        }

        em.close();

        emf.close();
    }

}