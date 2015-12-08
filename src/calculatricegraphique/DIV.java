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
public class DIV extends Binary {

    public DIV(EXPR e1, EXPR e2) {
        left = e1;
        right = e2;
    }

    public double eval() {
        return left.eval() / right.eval();
    }
}
