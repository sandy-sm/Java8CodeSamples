package sm.sandy.java8;

public class StringOps {
    public static void main(String[] args) {
        String s1 = "Java";
        String s2 = "Java";
        String s3 = new String("Java");

        System.out.println(s1 == s2); //true
        System.out.println(s1 == s3); //false
        System.out.println(s1.equals(s2)); //true
        System.out.println(s1.equals(s3)); //true


        Integer i1 = 1;
        Integer i2 = 1;
        Integer i3 = new Integer(1);

        System.out.println(i1 == i3);
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i3));
    }
}
