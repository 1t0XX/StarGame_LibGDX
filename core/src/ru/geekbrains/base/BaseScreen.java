package ru.geekbrains.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.MatrixUtils;
import ru.geekbrains.math.Rect;

public abstract class BaseScreen implements Screen, InputProcessor {


    protected SpriteBatch batch;
    private Rect screeenBounds;
    private Rect worldBounds;
    private Rect glBounds;

    private Matrix4 worldToGl;
    private Matrix3 screenToWorld;

    private Vector2 touch;



    @Override
    public void show() {

        System.out.println("Show");
        Gdx.input.setInputProcessor(this);
        batch = new SpriteBatch();
        screeenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0,0,1f,1f);
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        touch = new Vector2();

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        screeenBounds.setSize(width,height);
        screeenBounds.setLeft(0);
        screeenBounds.setBottom(0);

        float aspect = width / (float) height;
        worldBounds.setHeight(1f);
        worldBounds.setWidth(1f* aspect);
        MatrixUtils.calcTransitionMatrix(worldToGl,worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);
        resize(worldBounds);
        MatrixUtils.calcTransitionMatrix(screenToWorld,screeenBounds,worldBounds);


    }

    public void resize(Rect worldBounds){
        System.out.println("resize worldBounds.width = " + worldBounds.getWidth() + "resize worldBounds.height = " + worldBounds.getHalfHeight());
    }

    @Override
    public void pause() {
        System.out.println("Pause");

    }

    @Override
    public void resume() {

        System.out.println("Resume");

    }

    @Override
    public void hide() {
        System.out.println("Hide");
        dispose();

    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        touch.set(screenX, screeenBounds.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch,pointer,button);
        return false;
    }

    public boolean touchDown( Vector2 touch, int pointer, int button) {
        System.out.println("touchDown touch.x = " + touch.x + "touchDown touch.y = " + touch.y);
        return false;
    }




    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        touch.set(touch.x, screeenBounds.getHeight() - touch.y).mul(screenToWorld);
        touchUp(touch,pointer,button);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer, int button) {
        System.out.println("touchUp touch.x = " + touch.x + "touchUp touch.y = " + touch.y);
        return false;
    }




    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        touch.set(touch.x, screeenBounds.getHeight() - touch.y).mul(screenToWorld);
        touchDragged(touch,pointer);
        return false;
    }

    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touch.x = " + touch.x + "touchDragged touch.y = " + touch.y);
        return false;
    }




    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("scrolled amount = " + amount);
        return false;
    }
}
