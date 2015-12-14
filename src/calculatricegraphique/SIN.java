/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

/**
 *
 * @author Diane
 */
public class SIN extends Unary{
    public SIN(EXPR e) {
        right = e;
    }

    public double eval() {
        return Math.sin(right.eval());
    }
}
