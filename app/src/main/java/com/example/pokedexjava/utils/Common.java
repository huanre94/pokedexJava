package com.example.pokedexjava.utils;

import android.graphics.Color;

public class Common {
    public static int getColorByType(String type) {
        return switch (type) {
            case "normal" -> Color.parseColor("#A4A27A");
            case "dragon" -> Color.parseColor("#743BFB");
            case "psychic" -> Color.parseColor("#F15B85");
            case "electric" -> Color.parseColor("#E9CA3C");
            case "ground" -> Color.parseColor("#D9BF6C");
            case "grass" -> Color.parseColor("#81C85B");
            case "poison" -> Color.parseColor("#A441A3");
            case "steel" -> Color.parseColor("#BAB7D2");
            case "fairy" -> Color.parseColor("#DDA2DF");
            case "fire" -> Color.parseColor("#F48130");
            case "fight" -> Color.parseColor("#BE3027");
            case "bug" -> Color.parseColor("#A8B822");
            case "ghost" -> Color.parseColor("#705693");
            case "dark" -> Color.parseColor("#745945");
            case "ice" -> Color.parseColor("#9BD8D8");
            case "water" -> Color.parseColor("#658FF1");
            default -> Color.parseColor("#658FA0");
        };
    }
}
