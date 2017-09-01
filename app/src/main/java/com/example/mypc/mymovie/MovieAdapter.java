package com.example.mypc.mymovie;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Catherine Almira on 8/29/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder>{

    private ArrayList<Movie> mMovieData;

    private final MovieAdapterOnClickHandler mClickHandler;

    //public Context context;

    public interface MovieAdapterOnClickHandler {
        void onClick(String movieForRow);
    }

    public MovieAdapter(MovieAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mMovieTextView;

        public final ImageView mPosterImage;

        public MovieAdapterViewHolder(View view) {
            super(view);
            mMovieTextView = (TextView) view.findViewById(R.id.list_title);
            mPosterImage = (ImageView) view.findViewById(R.id.list_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            String movieForRow = mMovieData.get(adapterPosition).getTitle()
                    + "\n" + mMovieData.get(adapterPosition).getBackDropSource()
                    + "\n" + mMovieData.get(adapterPosition).getSynopsis()
                    + "\n" + mMovieData.get(adapterPosition).getReleaseDate()
                    + "\n" + mMovieData.get(adapterPosition).getRating()
                    + "\n" + mMovieData.get(adapterPosition).getMovieId()
                    + "\n" + mMovieData.get(adapterPosition).getImdbId()
                    + "\n" + mMovieData.get(adapterPosition).getYoutubeKey();
            mClickHandler.onClick(movieForRow);
        }
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        return new MovieAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        String movieForThisRow = mMovieData.get(position).getTitle();
        holder.mMovieTextView.setText(movieForThisRow);
        Picasso.with(holder.mPosterImage.getContext()).load
                ("https://image.tmdb.org/t/p/w185" + mMovieData.get(position).getImageSource())
                .placeholder(R.drawable.logo_item)
                .into(holder.mPosterImage);

        /*String url = "https://image.tmdb.org/t/p/w185" + mMovieData.get(position).getBackDropSource();
        Log.d(MovieAdapter.class.getName(), url);
        holder.mPosterImage.setImageBitmap(getBitmapFromUrl(url));*/


    }

    public static Bitmap getBitmapFromUrl(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getItemCount() {
        if (null == mMovieData) {
            return 0;
        }
        return mMovieData.size();
    }

    public void setmMovieData(ArrayList<Movie> movieData) {
        mMovieData = movieData;
        notifyDataSetChanged();
    }



    //    public MovieAdapter(Context context, List<Movie> movies) {
//        super(context, 0, movies);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        View listItemView = convertView;
//        if (listItemView == null) {
//            listItemView = LayoutInflater.from(getContext()).inflate(
//                    R.layout.movie_list_item, parent, false);
//        }
//
//        Movie currentMovie = getItem(position);
//
//        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
//        titleView.setText(currentMovie.getTitle());
//
//        TextView imageSourceView = (TextView) listItemView.findViewById(R.id.image_src);
//        imageSourceView.setText(currentMovie.getImageSource());
//
//        return listItemView;
//    }
}
