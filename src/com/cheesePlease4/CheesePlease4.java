/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cheesePlease4;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;            
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Array;

/**
 *
 * @author t-roy
 */
public class CheesePlease4 extends Game{
    public Stage mainStage;
    private AnimatedActor mousey;
    private BaseActor cheese;
    private BaseActor floor;
    private BaseActor winText;
    private boolean win;
  
    
    
    public void create(){
        win = false;
          
        mainStage = new Stage();
        
        floor = new BaseActor();
        floor.setTexture(new Texture(Gdx.files.internal("assets/tiles.jpg")));
        floor.setPosition(0, 0);
        mainStage.addActor(floor);
        
        cheese = new BaseActor();
        cheese.setTexture(new Texture(Gdx.files.internal("assets/cheese.png")));
        cheese.setOrigin(cheese.getWidth()/2, cheese.getHeight()/2);
        cheese.setPosition(400,300);
        mainStage.addActor(cheese);
        
        mousey = new AnimatedActor();
        
        TextureRegion[] frames = new TextureRegion[4];
        for (int n = 0; n < 4; n++)
        {   
            String fileName = "assets/mouse" + n + ".png";
            Texture tex = new Texture(Gdx.files.internal(fileName));
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            frames[n] = new TextureRegion( tex );
        }
        Array<TextureRegion> framesArray = new Array<TextureRegion>(frames);
        
        Animation anim = new Animation(0.1f, framesArray, Animation.PlayMode.LOOP_PINGPONG);

        mousey.setAnimation( anim );
        mousey.setOrigin( mousey.getWidth()/2, mousey.getHeight()/2 );
        mousey.setPosition( 20, 20 );
        mainStage.addActor(mousey);
        
        winText = new BaseActor();
        winText.setTexture(new Texture(Gdx.files.internal("assets/you-win.png")));
        winText.setPosition(170,60);
        winText.setVisible(false);
        mainStage.addActor(winText);
        
        
    }
    
    public void render(){
        
        //process input
        
        mousey.velocityX = 0;
        mousey.velocityY = 0;
        
        if (Gdx.input.isKeyPressed(Keys.LEFT))
            mousey.velocityX -= 100;
        if (Gdx.input.isKeyPressed(Keys.RIGHT))
            mousey.velocityX += 100;
        if (Gdx.input.isKeyPressed(Keys.UP))
            mousey.velocityY +=100;
        if (Gdx.input.isKeyPressed(Keys.DOWN))
            mousey.velocityY -=100;
        
        //update
        float dt = Gdx.graphics.getDeltaTime();
        mainStage.act(dt);
        
        //check win condition
        Rectangle cheeseRectangle = cheese.getBoundingRectangle();
        Rectangle mouseyRectangle = mousey.getBoundingRectangle();
        
        if (!win &&cheeseRectangle.contains(mouseyRectangle))
        {
            win = true;
            Action spinShrinkFadeOut = Actions.parallel(
                Actions.alpha(1),           //set transparency value
                Actions.rotateBy(360, 1),   //rotation amount, duration
                Actions.scaleTo(0,0,1),     //x amount, y amount, duration
                Actions.fadeOut(1)          //duration of fade out                 
            );
            cheese.addAction(spinShrinkFadeOut);
            Action fadeInColorCycleForever  = Actions.sequence(
                Actions.alpha(0),
                Actions.show(),
                Actions.fadeIn(2),
                Actions.forever(Actions.sequence(
                                //color shade to approach, duration
                                Actions.color(new Color(1,0,0,1),1),
                                Actions.color(new Color(0,0,1,1),1)
                                )
                                )
                        
                );
            winText.addAction(fadeInColorCycleForever);
        }
        
        //draw graphics
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        mainStage.draw();
        
    }
}
