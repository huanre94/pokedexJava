package com.example.pokedexjava.ui.activities;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexjava.R;
import com.example.pokedexjava.ui.adapters.PokemonListAdapter;
import com.example.pokedexjava.ui.viewmodels.PokemonListViewModel;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;
    private int offset;
    private boolean isFinal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PokemonListViewModel model = new ViewModelProvider(this).get(PokemonListViewModel.class);

        model.getPokemonList().observe(this, pokemonListEntries -> {
            pokemonListAdapter.addPokemonList(pokemonListEntries);
            isFinal = true;

        });


        recyclerView = findViewById(R.id.rv_pokemon_list);
        pokemonListAdapter = new PokemonListAdapter(this);
        recyclerView.setAdapter(pokemonListAdapter);
        recyclerView.hasFixedSize();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //call the viewmodel to update the list
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (isFinal) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            isFinal = false;
                            offset += 40;
                            model.setPokemonList(40, offset);
                        }
                    }
                }
            }
        });

        //call model to update the list
        model.setPokemonList(40, 0);
    }

}