public class Tester {
    public static void main(String[] args) {
        Term t = new Term("x + 5");
        t = t.pow(2).sortPower();
        System.out.println(t);
    }
}
