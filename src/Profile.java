public class Profile {
    private String phoneNumber;
    private String address;
    private String emailAddress;

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void viewProfile() {
        System.out.println("Current phone number: " + getPhoneNumber());
        System.out.println("Current address: " + getAddress());
        System.out.println("Current email address: " + getEmailAddress());
    }
}
