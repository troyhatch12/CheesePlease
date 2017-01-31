/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheesePlease4;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;

/**
 *
 * @author t-roy
 */
public class Launcher4 {
    public static void main (String [] args){
        
    CheesePlease4 myProgram = new CheesePlease4();
    LwjglApplication launcher = new LwjglApplication(myProgram);
    
    }
}
