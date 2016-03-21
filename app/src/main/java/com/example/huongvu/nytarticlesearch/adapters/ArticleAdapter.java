package com.example.huongvu.nytarticlesearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.huongvu.nytarticlesearch.R;
import com.example.huongvu.nytarticlesearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HUONGVU on 3/20/2016.
 */
public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    // Store a member variable for the articles
    private List<Article> mArticles;


    // Pass in the contact array into the constructor
    public ArticleAdapter(List<Article> articles) {
        mArticles = articles;
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView articleHeadline;
        public ImageView articleThumbnail;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            articleHeadline = (TextView) itemView.findViewById(R.id.tvItemGridText);
            articleThumbnail = (ImageView) itemView.findViewById(R.id.ivItemGridImage);
        }
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View articleView = inflater.inflate(R.layout.article_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(articleView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        //Article article = mArticles.get(position);


        //Log.d("DEGUB", "onBindViewHolder: " + article.getArticleHeadline().getHeadline());
        TextView textView = viewHolder.articleHeadline;
        textView.setText(mArticles.get(position).getArticleHeadline().getHeadline());

        ImageView imageView = viewHolder.articleThumbnail;

        String imageUrl = mArticles.get(position).getThumbnailUrl();
        //Log.d("DEBUG", "onBindViewHolder: " + imageUrl);
        //imageView.setImageIcon(@);
        Picasso.with(viewHolder.articleThumbnail.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
                .into(imageView);

    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        //return mArticles.size();
        return mArticles.size();
    }

}

