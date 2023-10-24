package com.example.pokedexjava.repository;

import com.example.pokedexjava.Models.Pokemon;
import com.example.pokedexjava.Models.PokemonList;
import com.example.pokedexjava.data.remote.PokeapiService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonRepository implements PokeapiService {
    private PokeapiService pokeapiService;

    public PokemonRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        pokeapiService = retrofit.create(PokeapiService.class);
    }

    @Override
    public Call<PokemonList> getPokemonList(int limit, int offset) {
        return pokeapiService.getPokemonList(limit, offset);
    }

    @Override
    public Call<Pokemon> getPokemonDetail(int id) {
        return pokeapiService.getPokemonDetail(id);
    }
}
