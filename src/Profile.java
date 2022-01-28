
public class Profile {
    private String phoneNumber;
    private String address;
    private String emailAddress;

    private void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private void setAddress(String address) {
        this.address = address;
    }

    private void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void editPhoneNumber(String number){
        // System.out.print("Current phone number: " + this.phoneNumber);
        // System.out.print("New phone number:");
        setPhoneNumber(number);
        System.out.println("Phone number is modified.");
        System.out.print("Current phone number: " + this.phoneNumber);
    }

    public void editAddress(String address){
        // System.out.print("Current address: " + this.address);
        setAddress(address);
        System.out.println("Address is modified.");
        System.out.print("Current address: " + this.address);
    }

    public void editEmailAddress(String emailAddress){
        // System.out.print("Current email address: " + this.emailAddress);
        setEmailAddress(emailAddress);
        System.out.println("Email is modified.");
        System.out.print("Current email address: " + this.emailAddress);
    }
}
