package com.example.pokedexjava.ui.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedexjava.Models.Pokemon;
import com.example.pokedexjava.repository.PokemonRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonDetailViewModel extends ViewModel {
    private final PokemonRepository repository;
    private final MutableLiveData<Pokemon> pokemon = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Pokemon> getPokemon() {
        return pokemon;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }


    public void setPokemon(Pokemon pokemon) {
        this.pokemon.setValue(pokemon);
    }

    public PokemonDetailViewModel() {
        repository = new PokemonRepository();
    }

    public void getPokemonDetail(int pokemonId) {
        isLoading.setValue(true);
        repository.getPokemonDetail(pokemonId).enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    Log.i("POKEDEX", "onResponse: " + response.body());
                    pokemon.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e("POKEDEX", "onFailure: " + t.getMessage());
            }
        });
    }

}
