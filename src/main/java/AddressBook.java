import java.util.ArrayList; // import the ArrayList class


public class AddressBook {
    private ArrayList<BuddyInfo> myBuddies;

    public AddressBook() {
        clearBuddies();
    }

    public void addBuddy(BuddyInfo newBuddy) {
        myBuddies.add(newBuddy);
    }

    public ArrayList<BuddyInfo> getBuddies(){
        return(myBuddies);
    }

    public void clearBuddies(){
        myBuddies = new ArrayList<BuddyInfo>();
    }

    public BuddyInfo getBuddy(int ind) {
        BuddyInfo toRet = null;

        if (ind < myBuddies.size()) {
            toRet = myBuddies.get(ind);
        }

        return(toRet);
    }

    public void removeBuddy(int ind){
        if (ind < myBuddies.size()) {
            myBuddies.remove(ind);
        }
    }

    @Override
    public String toString() {
        String toRet = "";
        toRet += "Address Book: \n";

        for (BuddyInfo buddy : myBuddies) {
            toRet += "\t" + buddy.toString() + "\n";

        }

        return(toRet);
    }

    public static void main(String args[]) {
        AddressBook myBook = new AddressBook();

        BuddyInfo buddy1 = new BuddyInfo("Dan", 1, "1 Main Street");
        BuddyInfo buddy2 = new BuddyInfo("Paul", 2, "2 Main Street");
        BuddyInfo buddy3 = new BuddyInfo("George", 3, "3 Main Street");

        myBook.addBuddy(buddy1);
        myBook.addBuddy(buddy2);
        myBook.addBuddy(buddy3);

        System.out.println(myBook);
    }
}
