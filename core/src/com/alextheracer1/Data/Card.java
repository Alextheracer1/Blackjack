package com.alextheracer1.Data;


import com.badlogic.gdx.graphics.Texture;

public class Card {

  private String name;
  private int cardPoints;
  private Texture cardTexture;

  public Card(String name, int cardPoints, Texture cardTexture){
    this.name = name;
    this.cardPoints = cardPoints;
    this.cardTexture = cardTexture;
  }

  public String getName() {
    return name;
  }

  public int getCardPoints() {
    return cardPoints;
  }

  public Texture getCardImage() {
    return cardTexture;
  }
}
