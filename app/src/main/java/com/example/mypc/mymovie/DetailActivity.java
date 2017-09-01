package com.example.mypc.mymovie;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String MOVIE_SHARE_HASHTAG = " #MyMovieApp";

    private String mMovie;
    private String mMovieTitle;
    private String mMovieBackDrop;
    private String mMovieRating;
    private String mMovieSynopsis;
    private String mMovieReleaseDate;
    private String mMovieId;
    private String mImdbId;
    private String mYoutubeKey;
    private TextView mMovieTitleDisplay;
    private TextView mMovieSynopsisDisplay;
    private TextView mMovieRatingDisplay;
    private TextView mMovieReleaseDateDisplay;
    private ImageView mMovieBackdropDisplay;
    private ImageView mMovieVideoDisplay;
    private TextView mMovieReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMovieVideoDisplay = (ImageView) findViewById(R.id.display_movie_video);
        mMovieTitleDisplay = (TextView) findViewById(R.id.display_movie_title);
        mMovieSynopsisDisplay = (TextView) findViewById(R.id.display_movie_synopsis);
        mMovieRatingDisplay = (TextView) findViewById(R.id.display_movie_rating);
        mMovieReleaseDateDisplay = (TextView) findViewById(R.id.display_movie_release);
        mMovieBackdropDisplay = (ImageView) findViewById(R.id.display_movie_backdrop);
        mMovieReview = (TextView) findViewById(R.id.display_movie_review);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mMovie = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                String[] lines = mMovie.split(System.getProperty("line.separator"));
                mMovieTitle = lines[0];
                mMovieBackDrop = lines[1];
                mMovieSynopsis = lines[2];
                mMovieReleaseDate = lines[3];
                mMovieRating = lines[4];
                mMovieId = lines[5];
                mImdbId = lines[6];
                mYoutubeKey = lines[7];
                mMovieTitleDisplay.setText(mMovieTitle);
                mMovieSynopsisDisplay.setText(mMovieSynopsis);
                mMovieRatingDisplay.setText("RATING " + mMovieRating + " / 10");
                mMovieReleaseDateDisplay.setText("RELEASE DATE : " + mMovieReleaseDate);
                Picasso.with(DetailActivity.this).load
                        ("https://image.tmdb.org/t/p/w600" + mMovieBackDrop)
                        .placeholder(R.drawable.logo_item)
                        .into(mMovieBackdropDisplay);
                Picasso.with(DetailActivity.this).load
                        ("http://img.youtube.com/vi/" +mYoutubeKey+ "/0.jpg")
                        .placeholder(R.drawable.logo_item)
                        .into(mMovieVideoDisplay);
                //mMovieVideoDisplay.setText(mMovieId);
            }
        }

        mMovieReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("http://www.imdb.com/title/" + mImdbId + "/reviews"));
                startActivity(viewIntent);
            }
        });

        mMovieVideoDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.youtube.com/watch?v=" + mYoutubeKey));
                startActivity(viewIntent);
            }
        });
    }

    private Intent createShareMovieIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText("Title : " + mMovieTitle + "\n" +
                        "Synopsis : " + mMovieSynopsis + "\n" +
                        "Release date : " + mMovieReleaseDate + "\n" +
                        "Rating : " + mMovieRating +"/10\n"
                        + MOVIE_SHARE_HASHTAG + " by Catherine Almira")
                .getIntent();
        return shareIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        menuItem.setIntent(createShareMovieIntent());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }

        if (id == R.id.action_about) {
            Toast.makeText(getApplicationContext().getApplicationContext(),
                    "Developed by Catherine Almira", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
