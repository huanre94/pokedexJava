package com.example.pokedexjava.ui.activities;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pokedexjava.R;
import com.example.pokedexjava.ui.viewmodels.PokemonDetailViewModel;

public class PokemonDetailActivity extends AppCompatActivity {
    PokemonDetailViewModel pokemonDetailViewModel;
    ImageView pokemonImage;
    TextView pokemonName;
    TextView pokemonNumber;
    TextView pokemonWeight;
    TextView pokemonType;
    TextView pokemonAbility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        int pokemonId = getIntent().getIntExtra("pokemonId", 0);

        pokemonImage = findViewById(R.id.pokemonImage);
        pokemonName = findViewById(R.id.pokemonName);
        pokemonNumber = findViewById(R.id.pokemonNumber);
        pokemonWeight = findViewById(R.id.pokemonWeight);
        pokemonType = findViewById(R.id.pokemonTypes);
        pokemonAbility = findViewById(R.id.pokemonAbilities);


        pokemonDetailViewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);
        pokemonDetailViewModel.getPokemonDetail(pokemonId);

        pokemonDetailViewModel.getPokemon().observe(this, pokemon -> {
            pokemonName.setText(pokemon.getName());
            pokemonNumber.setText(String.format("# " + pokemon.getId()));
            pokemonWeight.setText(String.format("Weight: " + pokemon.getWeight()));
            pokemonType.setText(String.format("Type: " + pokemon.getTypes()));
            pokemonAbility.setText(String.format("Ability: " + pokemon.getAbilities()));

            Glide.with(this).load(pokemon.getImageUrl()).into(pokemonImage);

        });
    }
}