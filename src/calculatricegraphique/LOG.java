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
public class LOG extends Unary{
    public LOG(EXPR e) {
        right = e;
    }

    public double eval() {
        return Math.log(right.eval());
    }
}
