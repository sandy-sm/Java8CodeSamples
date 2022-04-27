package sm.sandy.java8inaction.ch10;

import java.util.Date;
import java.util.Optional;

public class User {

    private Optional<Address> address;
    private Optional<Date> dob;
    String vehicleNumber;

    public Optional<Address> getAddress() {
        return address;
    }

    public void setAddress(Optional<Address> address) {
        this.address = address;
    }

    public Optional<Date> getDob() {
        return dob;
    }

    public void setDob(Optional<Date> dob) {
        this.dob = dob;
    }
}
