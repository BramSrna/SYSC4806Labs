import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

import java.util.List;

import static org.junit.Assert.*;

public class BuddyInfoTest {
    private final String ORIG_NAME = "TEST_NAME";
    private final int ORIG_NUM = 0;
    private final String ORIG_ADDR = "TEST_ADDRESS";

    private BuddyInfo testBuddy;

    @Before
    public void setUp(){
        testBuddy = new BuddyInfo(ORIG_NAME, ORIG_NUM, ORIG_ADDR);
    }

    @After
    public void teardown(){
        testBuddy = null;
    }

    @Test
    public void testName(){
        String testName = "NEW NAME";

        assertEquals(testBuddy.getName(), ORIG_NAME);

        testBuddy.setName(testName);
        assertEquals(testBuddy.getName(), testName);
    }

    @Test
    public void testNum(){
        int testNum = 2;

        assertEquals(testBuddy.getPhoneNumber(), ORIG_NUM);

        testBuddy.setPhoneNumber(testNum);
        assertEquals(testBuddy.getPhoneNumber(), testNum);
    }

    @Test
    public void testAddr(){
        String testAddr = "MAIN STREET";

        assertEquals(testBuddy.getAddress(), ORIG_ADDR);

        testBuddy.setAddress(testAddr);
        assertEquals(testBuddy.getAddress(), testAddr);
    }

    @Test
    public void testPersistence() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-test");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        em.persist(testBuddy);

        tx.commit();

        Query q = em.createQuery("SELECT buddy FROM BuddyInfo buddy");

        List<BuddyInfo> results = q.getResultList();

        System.out.println("List of Buddy Infos\n-------------");

        for (BuddyInfo buddy : results) {
            System.out.println(buddy.toString());
        }

        em.close();

        emf.close();
    }

}