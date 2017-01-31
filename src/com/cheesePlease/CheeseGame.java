/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheesePlease;

/**
 *
 * @author t-roy
 */
import com.badlogic.gdx.Game;

public class CheeseGame extends Game{
    public void create(){
        CheeseMenu cl = new CheeseMenu(this);
        setScreen(cl);
    }
    
}
