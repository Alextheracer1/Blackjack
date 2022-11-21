package com.alextheracer1.Data;

import static com.badlogic.gdx.math.MathUtils.random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import java.util.ArrayList;

public class Deck {

  private final ArrayList<Card> deck = new ArrayList<>();

  public void fillDeck(TextureAtlas atlas) {

    Card ass = new Card("Ass", 1, atlas.createSprite("Card1"));
    deck.add(ass);

    for (int i = 0, j = 2; i < 8; i++) {

      Card card = new Card("Card" + j, j, atlas.createSprite("Card" + j));
      deck.add(card);
      j++;
    }

    Card king = new Card("King", 10, atlas.createSprite("Card13"));
    Card queen = new Card("Queen", 10, atlas.createSprite("Card2"));
    Card junior = new Card("Junior", 10, atlas.createSprite("Card11"));

    deck.add(king);
    deck.add(queen);
    deck.add(junior);

    for (int i = 0; i <= deck.size() - 1; i++) {
      System.out.println(deck.get(i).getName());
      System.out.print(" Points: " + deck.get(i).getCardPoints() + "\n");
    }

  }

  public void shuffleDeck(Deck deck) {

  }


  public Card getCard(int index) {
    return deck.get(index);
  }

}
