package com.alextheracer1.Data;

public class Player {

  private String name;
  private int money;
  private Deck deck;

  public Player(String name, int money, Deck deck) {
    this.name = name;
    this.money = money;
    this.deck = deck;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public String getName() {
    return name;
  }

  public int getMoney() {
    return money;
  }

  public Deck getDeck() {
    return deck;
  }
}
