import java.text.SimpleDateFormat;

public class Test {
    public static void main(String[] args) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
        System.out.println(format.parse("11-JAN-2019"));
    }
}
