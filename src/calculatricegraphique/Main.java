/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

import java.util.Scanner;

/**
 *
 * @author Karakayn
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EXPR e;
        System.out.println("Ecrire une expression :");
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        System.out.println("Expression : "+expression);
        e = Parser.on(expression);
        System.out.println("Result = " + e.eval());
    }

}
