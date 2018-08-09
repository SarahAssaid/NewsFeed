package com.example.android.newsfeed;

import java.util.Date;

public class News {
    /**
     * Publication date of the news
     */
    private final Date mPublicationDate;
    /**
     * Title of the news
     */
    private final String mTitle;
    /**
     * Author of the news
     */
    private final String mAuthor;
    /**
     * Section name of the news
     */
    private final String mSectionName;

    /**
     * Website URL of the news
     */
    private final String mUrl;

    /**
     * Constructs a new {@link News} object.
     *
     * @param title       is the Title of the news
     * @param author      is the Author of the news
     * @param section      is the section name of the news
     * @param url         is the website URL to find more details about the news
     * @param publicationDate is the Publication date of the news
     */

    public News(String title, String author, String section, String url, Date publicationDate) {
        mTitle = title;
        mAuthor = author;
        mSectionName = section;
        mUrl = url;
        mPublicationDate = publicationDate;
    }

    /**
     * Returns the title of the news.
     */
    public String getTitle() {
        return mTitle;
    }
    /** Returns the author name of the news
     */

    public String getAuthor() {
        return mAuthor;
    }

    /**
     * Returns the section name of the news.
     */
    public String getSectionName() {
        return mSectionName;
    }


    /**
     * Returns the website URL to find more information about the news.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the Publication date of the news.
     */
    public Date getPublicationDate() {
        return mPublicationDate;
    }


}
