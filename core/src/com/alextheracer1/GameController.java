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
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameController extends ApplicationAdapter {

  private Texture dropImage;
  private Texture bucketImage;
  private Sound dropSound;
  private Music rainMusic;

  private Rectangle bucket;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Array<Rectangle> raindrops;

  private long lastDropTime;
  private TextureAtlas atlas;

  private void spawnRaindrop() {

    Rectangle raindrop = new Rectangle();
    raindrop.x = MathUtils.random(0, 800 - 64);
    raindrop.y = 480;
    raindrop.width = 64;
    raindrop.height = 64;
    raindrops.add(raindrop);
    lastDropTime = TimeUtils.nanoTime();

  }

  @Override
  public void create() {
    // load the images for the droplet and the bucket, 64x64 pixels each
    dropImage = new Texture(Gdx.files.internal("droplet.png"));
    bucketImage = new Texture(Gdx.files.internal("bucket.png"));

    AssetManager manager = new AssetManager();
    manager.load("assets/Atlas/cards.atlas", TextureAtlas.class);

    manager.finishLoading();

    atlas = manager.get("assets/Atlas/cards.atlas", TextureAtlas.class);


    // load the drop sound effect and the rain background "music"
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
    rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

    // start the playback of the background music immediately
    rainMusic.setLooping(true);
    rainMusic.play();

    camera = new OrthographicCamera();
    camera.setToOrtho(false, 800, 480);

    batch = new SpriteBatch();

    bucket = new Rectangle();
    bucket.x = 800 / 2 - 64 / 2;
    bucket.y = 20;
    bucket.width = 64;
    bucket.height = 64;

    raindrops = new Array<Rectangle>();
    spawnRaindrop();

    Deck deck = new Deck();
    deck.fillDeck();

  }

  @Override
  public void render() {
    ScreenUtils.clear(0, 0, 0.2f, 1);

    camera.update();


    batch.setProjectionMatrix(camera.combined);
    batch.begin();
    batch.draw(atlas.findRegion("card2"), 23, 45);
    batch.end();




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



  }

  @Override
  public void dispose() {
    dropImage.dispose();
    bucketImage.dispose();
    dropSound.dispose();
    rainMusic.dispose();
    batch.dispose();
  }
}
