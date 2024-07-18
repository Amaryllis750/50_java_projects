package com.calculator;

public class EquationParser{
    public static int parseString(String s){
        // initialize all the variables
        int i = 0; // this is the index used to access the characters in the string
        int res = 0; // this always contains the present result of our calculation
        int cur = 0; // this presents the current number
        int prev = 0; // this presents the previous number
        char cur_operation = '+';

        while (i < s.length()) {
            char cur_char = s.charAt(i);

            if (Character.isDigit(cur_char)) {
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    cur = (cur * 10) + Integer.parseInt(Character.toString(s.charAt(i)));
                    i++;
                }
                i--; // reset the counter by taking it one step back

                if (cur_operation == '+') {
                    res += cur;
                    prev = cur;
                } else if (cur_operation == '-') {
                    res -= cur;
                    prev = -cur;
                }

                else if (cur_operation == '/') {
                    res -= prev;
                    res += (prev / cur);

                    prev = prev / cur;
                }

                else {
                    res -= prev;
                    res += prev * cur;
                    prev = prev * cur;
                }

                cur = 0;
            }

            else if (cur_char != ' ') {
                cur_operation = cur_char;
            }
            i++;
        }
        return res;
    }
}
