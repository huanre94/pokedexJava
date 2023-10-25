package com.example.pokedexjava.Models;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private ArrayList<Ability> abilities;

    public void setAbilities(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

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

    public class Moves {
        private Move move;

        public Move getMove() {
            return move;
        }

        public void setMove(Move move) {
            this.move = move;
        }

        public class Move {
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

    public class Types {
        private Type type;

        public Type getType() {
            return type;
        }

        public void setType(Type type) {
            this.type = type;
        }

        public class Type {
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

    public class Sprites {
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


    private String imageUrl;

    public String getImageUrl() {
        return sprites.getFront_default();
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public class Ability {
        private boolean is_hidden;
        private int slot;
        private Item ability;

        public boolean isIs_hidden() {
            return is_hidden;
        }

        public void setIs_hidden(boolean is_hidden) {
            this.is_hidden = is_hidden;
        }

        public int getSlot() {
            return slot;
        }

        public void setSlot(int slot) {
            this.slot = slot;
        }

        public Item getAbility() {
            return ability;
        }

        public void setAbility(Item ability) {
            this.ability = ability;
        }

        public class Item {
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
}
