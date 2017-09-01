package com.example.mypc.mymovie;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Catherine Almira on 8/29/2017.
 */

public final class QueryUtils {

    private QueryUtils() {

    }

    public static ArrayList<Movie> extractMovies(String movieJson) {
        // Empty array list to start adding movies
        ArrayList<Movie> movies = new ArrayList<>();
        if (TextUtils.isEmpty(movieJson)) {
            return movies;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(movieJson);
            JSONArray movieArray = baseJsonResponse.getJSONArray("results");
            for (int i = 0; i < movieArray.length(); i++) {
                JSONObject currentMovie = movieArray.getJSONObject(i);
                String title = currentMovie.getString("title");
                String imageSource = currentMovie.getString("poster_path");
                String synopsis = currentMovie.getString("overview");
                String backdropSource = currentMovie.getString("backdrop_path");
                String rating = currentMovie.getString("vote_average");
                String releaseDate = currentMovie.getString("release_date");
                String id = currentMovie.getString("id");
                Movie movie = new Movie(title, imageSource, synopsis, backdropSource, rating, releaseDate, id);
                movies.add(movie);
            }


        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }

        return movies;
    }

    public static String[] extractReviewTrailer(String reviewJson) {
        String[] reviewId = new String[2];
        if (TextUtils.isEmpty(reviewJson)) {
            return reviewId;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(reviewJson);
            reviewId[0] = baseJsonResponse.getString("imdb_id");
            JSONObject video = baseJsonResponse.getJSONObject("videos");
            JSONArray videoArray = video.getJSONArray("results");
            JSONObject firstTrailer = videoArray.getJSONObject(0);
            reviewId[1] = firstTrailer.getString("key");
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the movie JSON results", e);
        }

        return reviewId;
    }

    public static int categoryChoice(Context context) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        String keyForCategory = "category";
        String defaultCategory = "popular";
        String preferredCategory = prefs.getString(keyForCategory, defaultCategory);
        String popular = "popular";
        String upcoming = "upcoming";
        if (popular.equals(preferredCategory)) {
            return 1;
        } else if (upcoming.equals(preferredCategory)) {
            return 3;
        } else {
            return 2;
        }
    }
}
