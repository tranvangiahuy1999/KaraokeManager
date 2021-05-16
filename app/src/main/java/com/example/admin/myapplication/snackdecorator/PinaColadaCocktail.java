package com.example.admin.myapplication.snackdecorator;

public class PinaColadaCocktail implements Cocktail{
    Cocktail cocktail;
    String name;
    float price;

    public PinaColadaCocktail(Cocktail cocktail){
        this.cocktail = cocktail;
        this.name = "PinaColada";
        this.price = 100000;
    }

    public String getName() {
        return cocktail.getName() + name;
    }

    public float getPrice() {
        return cocktail.getPrice() + price;
    }

    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail() + mixWithCoconut();
    }

    private String mixWithCoconut(){
        return "...mixing with coconut";
    }
}
