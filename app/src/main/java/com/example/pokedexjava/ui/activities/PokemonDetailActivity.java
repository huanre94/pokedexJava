package com.example.pokedexjava.ui.activities;

import static java.lang.Integer.valueOf;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.pokedexjava.R;
import com.example.pokedexjava.ui.viewmodels.PokemonDetailViewModel;
import com.example.pokedexjava.utils.Common;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class PokemonDetailActivity extends AppCompatActivity {
    ImageView pokemonImage, pokemonFrontImage, pokemonBackImage;
    TextView pokemonName, pokemonNumber, pokemonWeight;
    ChipGroup pokemonAbility, pokemonMoves, pokemonTypes;
    ProgressBar progressBar;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        int pokemonId = getIntent().getIntExtra("pokemonId", 0);

        pokemonImage = findViewById(R.id.pokemonImage);
        pokemonName = findViewById(R.id.pokemonName);
        pokemonNumber = findViewById(R.id.pokemonNumber);
        pokemonWeight = findViewById(R.id.pokemonWeight);
        pokemonTypes = findViewById(R.id.pokemonTypes);
        pokemonFrontImage = findViewById(R.id.pokemonFrontSprite);
        pokemonBackImage = findViewById(R.id.pokemonBackSprite);
        pokemonAbility = findViewById(R.id.pokemonAbilities);
        pokemonMoves = findViewById(R.id.pokemonMoves);
        scrollView = findViewById(R.id.scrollView);

        PokemonDetailViewModel pokemonDetailViewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);

        pokemonDetailViewModel.getPokemon().observe(this, pokemon -> {
            pokemonNumber.setText(String.format("# " + pokemon.getId()));
            pokemonName.setText(pokemon.getName());
            pokemonWeight.setText(String.format("Peso: " + pokemon.getWeight() + " kg"));

            // Set background color get first type
            scrollView.setBackgroundColor(valueOf(Common.getColorByType(pokemon.getTypes().get(0).getType().getName().toLowerCase())));

            pokemonTypes.removeAllViews();
            for (int i = 0; i < pokemon.getTypes().size(); i++) {
                Chip textView = new Chip(this);
                String type = pokemon.getTypes().get(i).getType().getName();
                textView.setText(type);
                textView.setChipBackgroundColor(ColorStateList.valueOf(Common.getColorByType(type.toLowerCase())));
                pokemonTypes.addView(textView);
            }

            pokemonAbility.removeAllViews();
            for (int i = 0; i < pokemon.getAbilities().size(); i++) {
                Chip textView = new Chip(this);
                textView.setText(pokemon.getAbilities().get(i).getAbility().getName().replace("-", " "));
                pokemonAbility.addView(textView);
            }

            pokemonMoves.removeAllViews();
            for (int i = 0; i < pokemon.getMoves().size(); i++) {
                Chip textView = new Chip(this);
                textView.setText(pokemon.getMoves().get(i).getMove().getName().replace("-", " "));
                pokemonMoves.addView(textView);
            }

            Glide.with(this)
                    .load(pokemon.getImageUrl())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pokemonImage);

            Glide.with(this)
                    .load(pokemon.getSprites().getFront_default())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(pokemonFrontImage);

            Glide.with(this)
                    .load(pokemon.getSprites().getBack_default())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)

                    .into(pokemonBackImage);

        });

        pokemonDetailViewModel.getPokemonDetail(pokemonId);
    }
}