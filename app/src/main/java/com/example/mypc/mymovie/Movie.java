package com.example.mypc.mymovie;

/**
 * Created by Catherine Almira on 8/29/2017.
 */

public class Movie {

    private String mTitle;

    private String mImageSource;

    private String mSynopsis;

    private String mBackDropSource;

    private String mRating;

    private String mReleaseDate;

    private String mMovieId;

    private String mImdbId;

    private String mYoutubeKey;

    /**
     * Konstruktor objek movie.
     * @param title
     * @param imagesource
     * @param synopsis
     * @param backdropSource
     * @param rating
     * @param releaseDate
     */
    public Movie(String title, String imagesource, String synopsis, String backdropSource,
                 String rating, String releaseDate, String id) {
        mTitle = title;
        mImageSource = imagesource;
        mSynopsis = synopsis;
        mBackDropSource = backdropSource;
        mRating = rating;
        mReleaseDate = releaseDate;
        mMovieId = id;
    }

    /**
     * @return judul film.
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * @return sumber gambar sampul.
     */
    public String getImageSource() {
        return mImageSource;
    }

    /**
     *
     * @return ringkasan cerita.
     */
    public String getSynopsis() {
        return mSynopsis;
    }

    /**
     *
     * @return sumber gambar backdrop.
     */
    public String getBackDropSource() {
        return mBackDropSource;
    }

    /**
     *
     * @return rating film.
     */
    public String getRating() {
        return mRating;
    }

    /**
     *
     * @return tanggal rilis.
     */
    public String getReleaseDate() {
        return mReleaseDate;
    }

    /**
     *
     * @return movie id.
     */
    public String getMovieId() {
        return mMovieId;
    }

    /**
     *
     * @return imdb id.
     */
    public String getImdbId() {
        return mImdbId;
    }

    /**
     *
     * @return youtube key.
     */
    public String getYoutubeKey() {
        return mYoutubeKey;
    }

    public void setImdbId(String id) {
        mImdbId = id;
    }

    public void setYoutubeKey(String key) {
        mYoutubeKey = key;
    }
}
