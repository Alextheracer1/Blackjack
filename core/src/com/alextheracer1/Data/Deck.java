package com.alextheracer1.Data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.ArrayList;

public class Deck {

  private ArrayList<Card> deck = new ArrayList<>();

  public void fillDeck() {

    for (int i = 0, j = 1; i < 2; i++) {

      Card card = new Card("Card" + j, j, new Texture(Gdx.files.internal("Cards/card" + j + ".png")));
      deck.add(card);
      j++;
    }

    for (int i = 0; i <= deck.size() - 1; i++) {
      System.out.println(deck.get(i).getCardImage().getTextureData());
    }

  }

}
