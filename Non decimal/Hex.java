import java.util.ArrayList;
public class Hex {
    private ArrayList<Character> hex;
    public Hex(int input){
        hex = new ArrayList<>();
        var temp = new ArrayList<Character>();
        while(input > 0){
            int remainder = input%16;
            input = input/16;
            char insert;
            if(remainder < 10){
                insert = Integer.toString(remainder).charAt(0);
            }
            else{
                insert = (char)(remainder - 10 + 'a');
            }
            temp.add(insert);
        }
        for(int i = temp.size()-1; i >= 0; i--){
            hex.add(temp.get(i));
        }
    }
    public String toString(){
        String s = "";
        for(int i = 0; i < hex.size(); i++){
            s += (hex.get(i));
        }
        return s;
    }

    public static void main(String[] args) {
        Hex h = new Hex(31);
        System.out.println(h);
    }
}
