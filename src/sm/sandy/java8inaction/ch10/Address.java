package sm.sandy.java8inaction.ch10;

public class Address {

    private String blockNumber;
    private String street;
    private short pinCode;

    public String getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public short getPinCode() {
        return pinCode;
    }

    public void setPinCode(short pinCode) {
        this.pinCode = pinCode;
    }
}
