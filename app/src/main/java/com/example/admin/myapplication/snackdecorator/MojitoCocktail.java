package com.example.admin.myapplication.snackdecorator;

public class MojitoCocktail implements Cocktail{
    Cocktail cocktail;

    public MojitoCocktail(Cocktail cocktail){
        this.cocktail = cocktail;
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
