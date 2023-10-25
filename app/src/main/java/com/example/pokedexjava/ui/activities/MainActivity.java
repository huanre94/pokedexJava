package com.example.pokedexjava.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokedexjava.R;
import com.example.pokedexjava.ui.adapters.PokemonListAdapter;
import com.example.pokedexjava.ui.viewmodels.PokemonListViewModel;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "POKEDEX";
    private RecyclerView recyclerView;
    private PokemonListAdapter pokemonListAdapter;
    TextView tverrorMessage;
    Button btnListRetry;
    ProgressBar progressBar;
    private int offset = 0;
    private static final int limit = 40;
    private boolean isFinal = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tverrorMessage = findViewById(R.id.item_error_text);
        btnListRetry = findViewById(R.id.item_error_button);

        PokemonListViewModel model = new ViewModelProvider(this).get(PokemonListViewModel.class);

        model.getPokemonList().observe(this, pokemonListEntries -> {
            pokemonListAdapter.addPokemonList(pokemonListEntries);
            isFinal = true;
        });

        btnListRetry.setOnClickListener(v -> {
            model.setPokemonList(limit, offset);
        }); // Retry loading when error occurs

        progressBar = findViewById(R.id.item_progress_bar);
        model.getIsLoading().observe(this, isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        }); // Show progress bar when loading


        model.getErrorMessage().observe(this, errorMessage -> {
            if (errorMessage != null) {
                tverrorMessage.setVisibility(View.VISIBLE);
                tverrorMessage.setText(errorMessage);
                recyclerView.setVisibility(View.GONE);
                btnListRetry.setVisibility(View.VISIBLE);
            } else {
                tverrorMessage.setVisibility(View.GONE);
                btnListRetry.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }); // Show error message when error occurs

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

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (isFinal) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            isFinal = false;
                            offset += 40;
                            model.setPokemonList(limit, offset);
                        }
                    }
                }
            }
        });

        model.setPokemonList(limit, offset);
    }

}