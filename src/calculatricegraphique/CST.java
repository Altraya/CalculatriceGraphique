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
public class CST extends EXPR {

    public double value;

    public CST(double value) {
        this.value = value;
    }

    public double eval() {
        return value;
    }
}
