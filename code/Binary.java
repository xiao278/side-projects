import java.util.ArrayList;

public class Binary {
    private ArrayList<Boolean> binary;
    public Binary(int in){
        binary = new ArrayList<>();
        while(in > 0){
            int remainder = in%2;
            in = in/2;
            binary.add(0,remainder == 1);
        }
    }

    public Binary(ArrayList<Boolean> b){
        binary = b;
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < binary.size(); i++){
            boolean temp = binary.get(i);
            if(temp) {
                s += "1";
            } else {
                s += "0";
            }
        }
        return s;
    }

    public static void main(String[] args) {
        var b = new Binary(69);
        var a = new Binary(80);
        var c = b.or(a);
        System.out.println(b);
        System.out.println(a);
        System.out.println(c);
    }

    public boolean getDigit(int index){
        return binary.get(binary.size()-index-1);
    }

    public Binary and(Binary b){
        var tempA = this.getBinary();
        var tempB = b.getBinary();
        var result = new ArrayList<Boolean>();
        for(int i = 0; i < Math.min(tempA.size(),tempB.size()); i++){
            result.add(0,this.getDigit(i) && b.getDigit(i));
        }
        return new Binary(result);
    }

    public Binary or(Binary b){
        var result = new ArrayList<Boolean>();
        var aLength = this.getBinary().size();
        var bLength = b.getBinary().size();
        for(int i = 0; i < Math.min(bLength,aLength); i++){
            result.add(0,this.getDigit(i) || b.getDigit(i));
        }
        Binary bigger;
        if(aLength > bLength){
            bigger = this;
        }
        else bigger = b;
        for(int i = Math.min(bLength,aLength); i < Math.max(bLength,aLength); i++){
            result.add(0,bigger.getDigit(i));
        }
        return new Binary(result);
    }

    public ArrayList<Boolean> getBinary() {
        return binary;
    }
}
