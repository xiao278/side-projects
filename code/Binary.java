import java.util.ArrayList;

public class Binary {
    private ArrayList<Boolean> binary;
    public Binary(int in){
        binary = new ArrayList<>();
        while(in > 0){
            int remainder = in%2;
            in = in/2;
            binary.add(remainder == 1);
        }
    }

    public Binary(ArrayList<Boolean> b){
        binary = b;
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < binary.size(); i++){
            boolean temp = getDigit(i);
            if(temp) {
                s += "1";
            } else {
                s += "0";
            }
        }
        s = s.replaceFirst("^0", "");
        return s;
    }

    public static void main(String[] args) {
        var b = new Binary(155);
        var a = new Binary(235);
        var c = a.xor(b);
        System.out.println(b);
        System.out.println(a);
        System.out.println(c);
    }

    public boolean getDigit(int index){
        return binary.get(binary.size()-index-1);
    }

    private Binary operation(Binary b, I bridge){
        var result = new ArrayList<Boolean>();
        var tempA = this.getBinary();
        var tempB = b.getBinary();
        for(int i = 0; i < Math.min(tempA.size(),tempB.size()); i++){
            result.add(bridge.method(tempA.get(i),tempB.get(i)));
        }
        ArrayList<Boolean> bigger;
        if(tempA.size() > tempB.size()){
            bigger = tempA;
        }
        else bigger = tempB;
        for(int i = Math.min(tempA.size(),tempB.size()); i < Math.max(tempA.size(), tempB.size()); i++){
            result.add(bridge.method(bigger.get(i), false));
        }
        return new Binary(result);
    }

    public Binary and(Binary b){
        return operation(b, Binops::and);
    }

    public Binary or(Binary b){
        return operation(b, Binops::or);
    }

    public Binary xor(Binary b){
        return operation(b, Binops::xor);
    }

    public ArrayList<Boolean> getBinary() {
        return binary;
    }
}

interface I{
    public Boolean method(boolean a, boolean b);
}

class Binops{
    public static boolean and(boolean a,boolean b){
        return a && b;
    }

    public static boolean or(boolean a,boolean b){
        return a || b;
    }

    public static boolean xor(boolean a, boolean b){
        return a != b;
    }
}
