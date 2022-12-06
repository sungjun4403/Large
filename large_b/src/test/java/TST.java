import java.util.ArrayList;

public class TST {
    public static void main(String[] args) {
        String word = "s## s### s# s s sssss s s s s#sss #sssss## sss # ";
        String guess = "#";

        for (int index = word.indexOf(guess);
             index >= 0;
             index = word.indexOf(guess, index + 1))
        {
            System.out.println(index);
        }

    }

}
