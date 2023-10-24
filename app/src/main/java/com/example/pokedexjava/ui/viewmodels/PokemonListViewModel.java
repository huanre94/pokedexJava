package com.example.pokedexjava.ui.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pokedexjava.Models.PokemonList;
import com.example.pokedexjava.Models.PokemonListEntry;
import com.example.pokedexjava.repository.PokemonRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonListViewModel extends ViewModel {
    private PokemonRepository repository;
    private MutableLiveData<ArrayList<PokemonListEntry>> pokemonList = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setPokemonList(int limit, int offset) {
        isLoading.setValue(true);
        repository.getPokemonList(limit, offset).enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    pokemonList.setValue(response.body().getResults());
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                isLoading.setValue(false);
            }
        });
    }

    public LiveData<ArrayList<PokemonListEntry>> getPokemonList() {
        return pokemonList;
    }

    public PokemonListViewModel() {
        repository = new PokemonRepository();
    }
}