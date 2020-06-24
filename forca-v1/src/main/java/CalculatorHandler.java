import java.util.HashMap;

public class CalculatorHandler {

    private HashMap<Integer,String> log;

    public CalculatorHandler() {
        log = new HashMap<Integer, String>();
    }

    public void ping() {
        System.out.println("ping()");
    }

    public int add(int n1, int n2) {
        System.out.println("add(" + n1 + "," + n2 + ")");
        return n1 + n2;
    }

    public int calculate() {
        return 1;
    }

    public String getStruct() {
        return "oi";
    }

    public void zip() {
        System.out.println("zip()");
    }

}