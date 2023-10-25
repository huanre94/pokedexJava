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
    private final PokemonRepository repository;
    private final MutableLiveData<ArrayList<PokemonListEntry>> pokemonList = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public LiveData<ArrayList<PokemonListEntry>> getPokemonList() {
        return pokemonList;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void setPokemonList(int limit, int offset) {
        isLoading.setValue(true);
        errorMessage.setValue(null); // Clear previous error messages

        repository.getPokemonList(limit, offset).enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                isLoading.setValue(false);
                if (response.isSuccessful()) {
                    pokemonList.setValue(response.body().getResults());
                } else {
                    errorMessage.setValue("Error cargando data. Por favor volver a intentar.");
                }

            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                isLoading.setValue(false);
                errorMessage.setValue("Error en la red. Por favor revisa tu conexión a internet.");
            }
        });
    }


    public PokemonListViewModel() {
        repository = new PokemonRepository();
    }
}