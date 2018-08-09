package com.example.android.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news) {

        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the news at the given position in the list of news.
        News currentNews = getItem(position);

        // Find the TextView with view ID title.
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        // Display the title of the current news in that TextView
        titleView.setText(currentNews.getTitle());

        // Find the TextView with view ID author.
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        // Display the author name of the current news in that TextView
        if (currentNews.getAuthor() != ""){
            authorView.setText(currentNews.getAuthor());

            //Set author name view as visible
            authorView.setVisibility(View.VISIBLE);
        }else {
            //Set author view as gone.
            authorView.setVisibility(View.GONE);
        }


        // Find the TextView with view ID section_name.
        TextView sectionNameView = (TextView) listItemView.findViewById(R.id.section_name);
        // Display the description of the current news in that TextView
        sectionNameView.setText(currentNews.getSectionName());


        // Find the TextView with the ID date.
        TextView dateView = null;
        TextView timeView = null;
        if (currentNews.getPublicationDate() != null) {
            dateView = listItemView.findViewById(R.id.date);
            // Format the date string (i.e "July 6, 2018")
            String formattedDate = formatDate(currentNews.getPublicationDate()).concat(",");
            // Display the date of the current news in that TextView
            dateView.setText(formattedDate);
            // Find the TextView with view ID time
            timeView = listItemView.findViewById(R.id.time);
            // Format the time string (i.e. "3:00 PM")
            String formattedTime = formatTime(currentNews.getPublicationDate());
            // Display the time of the current news in that TextView.
            timeView.setText(formattedTime);

            // Set date and time views as visible
            dateView.setVisibility(View.VISIBLE);
            timeView.setVisibility(View.VISIBLE);
        } else {
            // Set date and time views as gone.
            dateView.setVisibility(View.GONE);
            timeView.setVisibility(View.GONE);
        }

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

}


