/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculatricegraphique;

/**
 *
 * @author Elise
 */
public class COS extends Unary{
    public COS(EXPR e) {
        right = e;
    }

    public double eval() {
        return Math.cos(right.eval());
    }
}
