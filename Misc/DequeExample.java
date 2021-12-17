import java.util.ArrayDeque;
import java.util.Deque;

public class DequeExample {
    public static void main(String[] args) {
        Deque<Integer> e = new ArrayDeque<Integer>();
        e.push(5);
        e.push(7);
        e.push(9);
        e.push(3);
        e.push(4);
        e.push(11);
        e.push(14);
        e.add(11);
        e.add(4);
        e.add(3);
        e.add(9);
        e.add(7);
        e.add(5);

        for(int i = 0; i < 3; i++){
            e.pop();
            e.removeLast();
        }
        System.out.println(e);
    }
}
