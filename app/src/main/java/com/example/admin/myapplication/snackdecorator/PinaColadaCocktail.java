package com.example.admin.myapplication.snackdecorator;

public class PinaColadaCocktail implements Cocktail{
    Cocktail cocktail;
    String name;
    float price;

    public PinaColadaCocktail(Cocktail cocktail){
        this.cocktail = cocktail;
        this.name = "PinaColadaCocktail";
        this.price = 100000;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail() + mixWithCoconut();
    }

    private String mixWithCoconut(){
        return "...mixing with coconut";
    }
}
