/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject3;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author John Justine
 */
public class Mavenproject3 {

       public static void main(String[] args) {
        
        try {
            Class.forName("com.mycompany.mavenproject3.Prabu");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Mavenproject3.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
class Prabu{
    static{
        System.out.println("Static Block");
    }
    {
        System.out.println("Instance Block");
    }
}