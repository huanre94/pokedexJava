package com.example.pokedexjava.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedexjava.Models.Pokemon;
import com.example.pokedexjava.repository.PokemonRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailViewModel extends ViewModel {
    private PokemonRepository repository;
    private MutableLiveData<Pokemon> pokemon = new MutableLiveData<>();

    public MutableLiveData<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon.setValue(pokemon);
    }

    public PokemonDetailViewModel() {
        repository = new PokemonRepository();
    }

    public void getPokemonDetail(int pokemonId) {
        repository.getPokemonDetail(pokemonId).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    pokemon.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }

}
