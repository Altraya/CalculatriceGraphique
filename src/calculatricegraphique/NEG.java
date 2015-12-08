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
public class NEG extends Unary {

    public NEG(EXPR e) {
        right = e;
    }

    public double eval() {
        return (-right.eval());
    }
}
