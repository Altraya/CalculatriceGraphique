/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

/**
 *
 * @author Karakayn
 */
public class Parser {

    static String str;
    static int cur;
    static char lastChar;
    static int lastInt;

    /**
     * 
     * @return 
     */
    public static EXPR read_e() {
        EXPR right, result;
        char op;
        result = read_e_m();
        if (result != null) {
            while (read_char('+') || read_char('-')) {
                op = lastChar;
                right = read_e_m();
                if (right == null) {
                    error();
                }
                if (op == '+') {
                    result = new ADD(result, right);
                } else {
                    result = new SUB(result, right);
                }
            }
        }
        return result;
    }

    /**
     * 
     * @return 
     */
    public static EXPR read_e_m() {
        EXPR right, result;
        char op;
        result = read_e_u();
        if (result != null) {
            while (read_char('*') || read_char('/')) {
                op = lastChar;
                right = read_e_m();
                if (right == null) {
                    error();
                }
                if (op == '*') {
                    result = new MUL(result, right);
                } else {
                    result = new DIV(result, right);
                }
            }
        }
        return result;
    }

    public static EXPR read_e_u() {
        EXPR result, right;
        char op;
        System.out.println("Last char : "+lastChar);
        System.out.println("Cur : "+cur);
        if (read_char('+') || read_char('-')) {
            op = lastChar;
            right = read_e_u();
            if (right == null) {
                System.out.println("La partie droite dans read_e_u est null");
                error();
            }

            System.out.println("Op = "+op);
            if (op == '+') {
                result = new POS(right);
            } else {
                result = new NEG(right);
            }
        } else {
            result = read_cst();
        }
        return result;
    }

    public static EXPR read_cst() {
        System.out.println("Read constante");
        return null;
    }

    public static boolean read_char(char c) {
        System.out.println("Lit le caractère : "+c);
        System.out.println("Last char : "+lastChar);
        System.out.println("Cur : "+cur);

        if ((cur <= str.length()) && (str.charAt(cur) == c)) {
            lastChar = c;
            cur++;
            System.out.println("Last char : "+lastChar);
            System.out.println("Cur : "+cur);
            return true;
        } else {
            return false;
        }
    }

    public static EXPR on(String s) {
        EXPR result;
        str = s;
        cur = 0;
        result = read_e();
        if (result == null || cur != str.length()) {
            error();
        }
        return result;
    }

    public static void error() {
        System.out.println(str);
        for (int j = 0; j < cur; j++) {
            System.out.print(" ");
        }
        System.out.println("^");
        System.exit(1);
    }
}
