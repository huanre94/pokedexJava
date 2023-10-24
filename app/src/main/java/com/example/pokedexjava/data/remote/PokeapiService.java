package com.example.pokedexjava.data.remote;

import com.example.pokedexjava.Models.Pokemon;
import com.example.pokedexjava.Models.PokemonList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {
    @GET("pokemon")
    Call<PokemonList> getPokemonList(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemonDetail(@Path("id") int id);
}
