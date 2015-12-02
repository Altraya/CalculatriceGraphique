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
public class POS extends Unary {

    public POS(EXPR e) {
        right = e;
    }

    public int eval() {
        int e = right.eval();
        if (e >= 0) {
            return e;
        } else {
            return -e;
        }
    }
}
