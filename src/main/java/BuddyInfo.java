public class BuddyInfo {
    private String name;
    private int phoneNumber;
    private String address;


    public BuddyInfo(String name, int phoneNumber, String address) {
        setName(name);
        setPhoneNumber(phoneNumber);
        setAddress(address);
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getName(){
        return(this.name);
    }

    public void setPhoneNumber(int newNum){
        this.phoneNumber = newNum;
    }

    public int getPhoneNumber(){
        return(this.phoneNumber);
    }

    public void setAddress(String newAddr) {
        this.address = newAddr;
    }

    public String getAddress() {
        return(this.address);
    }

    @Override
    public String toString() {
        String toRet = String.format("Buddy: Name: %s, Phone Number: %d, Address: %s",
                                     this.name,
                                     this.phoneNumber,
                                     this.address);

        return(toRet);
    }
}
