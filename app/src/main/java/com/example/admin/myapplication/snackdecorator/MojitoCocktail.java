package com.example.admin.myapplication.snackdecorator;

public class MojitoCocktail implements Cocktail{
    Cocktail cocktail;
    String name;
    float price;

    public MojitoCocktail(Cocktail cocktail){
        this.cocktail = cocktail;
        this.name = "MojitoCocktail";
        this.price = 120000;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String mixCocktail() {
        return cocktail.mixCocktail() + mixWithMint() + mixWithRum() + mixWithLemonade();
    }

    private String mixWithMint(){
        return "...mixing with Mint";
    }

    private String mixWithRum(){
        return "...mixing with Rum";
    }

    private String mixWithLemonade(){
        return "...mixing with Lemonade";
    }
}
