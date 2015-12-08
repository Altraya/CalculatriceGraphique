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
        System.out.println("Result dans read_e : "+result);
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
        System.out.println("Result dans read_e_m : "+result);
        if (result != null) {
            while (read_char('*') || read_char('/')) {
                op = lastChar;
                right = read_e_u();
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
            System.out.println("Read_e_u : read char passé !");
            op = lastChar;
            System.out.println("OP = "+op);
            right = read_e_u();
            System.out.println("Right = "+right+" dans read_e_u");
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
        System.out.println("Dans read_e_u : result = "+result);
        return result;
    }

    public static EXPR read_cst() {
        System.out.println("Read constante");
        EXPR right, result;
        int chiffre;
        if(read_char('('))
        {
            while(!read_char(')'))
            {
                right = read_e();
            }
        }
        while(read_char('0') ||
                read_char('1') ||
                read_char('2') ||
                read_char('3') ||
                read_char('4') ||
                read_char('5') ||
                read_char('6') ||
                read_char('7') ||
                read_char('8') ||
                read_char('9')
        ){
            System.out.println("Je passe dans le while o/");
            chiffre = lastChar;
        }
        result = new CST();
        return result;
    }

    public static boolean read_char(char c) {
        System.out.println("Lit le caractère : "+c);
        System.out.println("Last char : "+lastChar);
        System.out.println("Cur : "+cur);

        System.out.println("Str length"+str.length());
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
        System.out.println("Str = "+str);
        cur = 0;
        result = read_e();
        System.out.println("Dans on result de read_e = "+result);
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
