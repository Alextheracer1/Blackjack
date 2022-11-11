package com.alextheracer1;

import com.alextheracer1.Data.Deck;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.TextArea;
import java.util.Iterator;

public class GameController extends ApplicationAdapter {

  private TextArea textArea;

  private Sprite cardImage;

  private Music backgroundMusic;

  private Rectangle card;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Array<Rectangle> cards;

  private TextureAtlas atlas;

  @Override
  public void create() {
    // load the images for the droplet and the bucket, 64x64 pixels each
    //dropImage = new Texture(Gdx.files.internal("droplet.png"));
    //bucketImage = new Texture(Gdx.files.internal("bucket.png"));

    AssetManager manager = new AssetManager();
    manager.load("assets/Atlas/cards.atlas", TextureAtlas.class);
    manager.finishLoading();

    atlas = manager.get("assets/Atlas/cards.atlas", TextureAtlas.class);


    Deck deck = new Deck();
    deck.fillDeck(atlas);



    cardImage = atlas.createSprite("Card2");



    // load the drop sound effect and the rain background "music"
   // dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
   // rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

    // start the playback of the background music immediately
    //rainMusic.setLooping(true);
   // rainMusic.play();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 1280, 720);

    batch = new SpriteBatch();


    card = new Rectangle();
    card.x = 1280 / 2 - 64 / 2;
    card.y = 20;
    card.width = 250;
    card.height = 250;
  }

  @Override
  public void render() {
    ScreenUtils.clear(0, 0, 0.2f, 1);

    camera.update();


    batch.setProjectionMatrix(camera.combined);



    batch.begin();
    batch.draw(cardImage, card.x, card.y, card.width, card.height);
    batch.end();



/*
    batch.begin();
    batch.draw(bucketImage, bucket.x, bucket.y);
    for(Rectangle raindrop: raindrops) {
      batch.draw(dropImage, raindrop.x, raindrop.y);

    }
    batch.end();




    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.x = (int) (touchPos.x - 64 / 2);
    }

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      bucket.x -= 200 * Gdx.graphics.getDeltaTime();
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      bucket.x += 200 * Gdx.graphics.getDeltaTime();
    }

    if (bucket.x < 0) {
      bucket.x = 0;
    }
    if (bucket.x > 800 - 64) {
      bucket.x = 800 - 64;
    }


    if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();


    for (Iterator<Rectangle> iter = raindrops.iterator(); iter.hasNext(); ) {
      Rectangle raindrop = iter.next();
      raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
      if(raindrop.y + 64 < 0) iter.remove();

      if(raindrop.overlaps(bucket)) {
        dropSound.play();
        iter.remove();
      }
    }

*/

  }

  @Override
  public void dispose() {
    batch.dispose();
    atlas.dispose();
  }
}
