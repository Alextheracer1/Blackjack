package com.alextheracer1.Data;


import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Card {

  private String name;
  private int cardPoints;
  private Sprite cardSprite;

  public Card(String name, int cardPoints, Sprite cardSprite){
    this.name = name;
    this.cardPoints = cardPoints;
    this.cardSprite = cardSprite;
  }

  public String getName() {
    return name;
  }

  public int getCardPoints() {
    return cardPoints;
  }

  public Sprite getCardSprite() {
    return cardSprite;
  }
}
