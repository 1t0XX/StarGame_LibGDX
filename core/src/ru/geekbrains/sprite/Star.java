package ru.geekbrains.sprite;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class Star extends Sprite {


    private Vector2 v = new Vector2();
    private Rect worldBound;

    public Star(TextureAtlas atlas){
        super(atlas.findRegion("star"));

        v.set(Rnd.nextFloat(-0.0005f,0.0005f), Rnd.nextFloat(-0.5f, -0.1f));
        setHeightProportion(0.01f);
    }


    @Override
    public void resize(Rect worldBound) {
        this.worldBound = worldBound;

        float posX = Rnd.nextFloat(worldBound.getLeft(), worldBound.getRight());
        float posY = Rnd.nextFloat(worldBound.getBottom(), worldBound.getTop());
        pos.set(posX, posY);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v,delta);
        checkBounds();
    }


    private void checkBounds(){
        if(getRight() < worldBound.getLeft()) setLeft(worldBound.getRight());
        if(getLeft() > worldBound.getRight()) setRight(worldBound.getLeft());
        if(getTop() < worldBound.getBottom()) setBottom(worldBound.getTop());
    }
}
