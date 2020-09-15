package Hashing;

public class HashCode {
    public static void main(String[] args) {
        String a = "200";
        String b = "200";

        if(a.equals(b)) {
            System.out.println("Equal variables.");
            System.out.println(a.hashCode() + " and " + b.hashCode());
        }

        String c = "50";
        String d = "2000";

        if(!c.equals(d)) {
            System.out.println("Unequal variables.");
            System.out.println(c.hashCode() + " and " + d.hashCode());
        }
    }
}
