package com.alextheracer1;

import com.alextheracer1.Data.Deck;
import com.alextheracer1.Data.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameController extends ApplicationAdapter {


  private Sprite cardImage;
  private Sprite cardBackImage;

  private Music backgroundMusic;

  Deck deck = new Deck();

  Player player;
  Player dealer;

  private Rectangle card;
  private OrthographicCamera camera;
  private SpriteBatch batch;

  private Array<Rectangle> cards;

  private TextureAtlas atlas;

  private Button button;

  ImageButton drawCardButton;

/*
  private void spawnCards() {
    cards = new Array<>();
    for (int i = 0; i < 20; i++) {
      Rectangle card = new Rectangle();
      card.setWidth(MathUtils.random(100, 500));

    }
  }

 */

  @Override
  public void create() {


    AssetManager manager = new AssetManager();
    manager.load("assets/Atlas/cards.atlas", TextureAtlas.class);
    manager.finishLoading();

    atlas = manager.get("assets/Atlas/cards.atlas", TextureAtlas.class);

    //spawnCards();

    deck.fillDeck(atlas);

    player = new Player("Player1", 900, deck);
    dealer = new Player("Dealer", 999999999, deck);

    Drawable drawable = new TextureRegionDrawable(new TextureRegion(atlas.createSprite("CardCover")));
    drawCardButton = new ImageButton(drawable);


    cardImage = deck.getCard(2).getCardSprite();


    cardBackImage = atlas.createSprite("CardCover");



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
    card.width = 100;
    card.height = 150;
  }

  @Override
  public void render() {
    ScreenUtils.clear(0, 0, 0.2f, 1);

    camera.update();


    batch.setProjectionMatrix(camera.combined);



    batch.begin();
    batch.draw(cardImage, card.x, card.y, card.width, card.height);
    batch.draw(cardImage, card.x, 600, card.width, card.height);
    batch.draw(cardBackImage, 1000, card.y, card.width, card.height);
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
