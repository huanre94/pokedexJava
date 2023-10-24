package com.example.pokedexjava.Models;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Ability abilities;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ArrayList<Moves> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Moves> moves) {
        this.moves = moves;
    }

    public ArrayList<Types> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Types> types) {
        this.types = types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    private int weight;
    private ArrayList<Moves> moves;
    private ArrayList<Types> types;
    private Sprites sprites;

    private class Moves {
        private Move move;

        public Move getMove() {
            return move;
        }

        public void setMove(Move move) {
            this.move = move;
        }

        private class Move {
            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }

    private class Types {
        private Type type;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        private class Type {
            private String name;
            private String url;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }

    private class Sprites {
        private String front_default;
        private String back_default;

        public String getBack_default() {
            return back_default;
        }

        public void setBack_default(String back_default) {
            this.back_default = back_default;
        }

        public String getFront_default() {
            return front_default;
        }

        public void setFront_default(String front_default) {
            this.front_default = front_default;
        }
    }

    public Ability getAbilities() {
        return abilities;
    }

    private String imageUrl;

    public String getImageUrl() {
        return sprites.getFront_default();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setAbilities(Ability abilities) {
        this.abilities = abilities;
    }

    private class Ability {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
