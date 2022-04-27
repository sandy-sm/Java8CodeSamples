package sm.sandy.java8inaction.ch10;

import javax.jws.soap.SOAPBinding;
import java.util.Optional;

public class Optional1 {

    public static void main(String[] args) {
       User user = new User();
       user.setAddress(Optional.empty());
       user.setDob(Optional.empty());

        System.out.println("User address " + user.getAddress().isPresent());


        System.out.println(findPinCode(Optional.of(user)));
        System.out.println(getVehicleNumber(Optional.of(user)));

        user = null;

        System.out.println(findPinCode(Optional.ofNullable(user)));
        System.out.println(getVehicleNumber(Optional.ofNullable(user)));
    }

    static Optional<Short> findPinCode(Optional<User> user) {
        return user.flatMap(u -> u.getAddress().map(a -> a.getPinCode()));
    }

    static Optional<String> getVehicleNumber(Optional<User> user) {
        return user.map(u -> Optional.ofNullable(u.vehicleNumber).orElse(""));
    }
}
