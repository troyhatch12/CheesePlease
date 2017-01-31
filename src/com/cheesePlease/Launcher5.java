/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheesePlease;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 *
 * @author t-roy
 */
public class Launcher5 {
    public static void main (String [] args){
        
    CheesePlease5 myProgram = new CheesePlease5();
    LwjglApplication launcher = new LwjglApplication(myProgram);
    
    }
}
