package com.example.pokedexjava.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.pokedexjava.Models.PokemonListEntry;
import com.example.pokedexjava.R;
import com.example.pokedexjava.ui.activities.PokemonDetailActivity;

import java.util.ArrayList;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.PokemonViewHolder> {
    private ArrayList<PokemonListEntry> pokemonList;
    private Context context;

    public PokemonListAdapter(Context context) {
        this.context = context;
        this.pokemonList = new ArrayList<>();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_item, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokemonListEntry pokemon = pokemonList.get(position);
        holder.pokemonName.setText(pokemon.getName());
        holder.pokemonNumber.setText(String.format("# " + pokemon.getNumber()));

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/" + pokemon.getNumber() + ".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {

                        Palette.from(((BitmapDrawable) resource).getBitmap()).generate((Palette.PaletteAsyncListener) palette -> {
                            Palette.Swatch vibrant = palette.getVibrantSwatch();
                            if (vibrant != null) {
                                ///make it a gradient from top to bottom
                                GradientDrawable gd = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{vibrant.getRgb(), 0x00000000});
                                holder.linearLayout.setBackground(gd);
                            }
                        });

                        return false;

                    }
                })
                .into(holder.pokemonImage);

        //add click listener here to cardview and show a toast with name
        holder.cardView.setOnClickListener(v -> {
            int pokemonId = pokemonList.get(position).getNumber();

            Intent intent = new Intent(context, PokemonDetailActivity.class);
            intent.putExtra("pokemonId", pokemonId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public void addPokemonList(ArrayList<PokemonListEntry> pokemonList) {
        this.pokemonList.addAll(pokemonList);
        notifyDataSetChanged();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ImageView pokemonImage;
        private final TextView pokemonName;
        private final TextView pokemonNumber;
        private final CardView cardView;
        private final LinearLayout linearLayout;

        public PokemonViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout2);
            pokemonImage = itemView.findViewById(R.id.ivPokemon);
            pokemonNumber = itemView.findViewById(R.id.tvPokemonNumber);
            pokemonName = itemView.findViewById(R.id.tvPokemonName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
