/**
 * Created by 1345 on 28.01.2016.
 */
public class Numbers {
    String number;
    String holder;
    int balance;

    String NumberToString() {
        String string = number + " " + holder + " " + balance + " ";

        if (string.length() < 100){
            while(string.length()!=99){
                string = string + "*";
            }
            string = string + "0";
        }

        return string;
    }
    Numbers() {
        this.number = number;
        this.holder = holder;
        this.balance = balance;

    }
}
