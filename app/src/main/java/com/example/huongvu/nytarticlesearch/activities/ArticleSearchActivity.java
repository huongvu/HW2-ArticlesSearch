package com.example.huongvu.nytarticlesearch.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.example.huongvu.nytarticlesearch.R;
import com.example.huongvu.nytarticlesearch.adapters.ArticleAdapter;
import com.example.huongvu.nytarticlesearch.fragments.AdvanceSearchSettingFragment;
import com.example.huongvu.nytarticlesearch.interfaces.AdvanceSettingListener;
import com.example.huongvu.nytarticlesearch.models.Article;
import com.example.huongvu.nytarticlesearch.models.ArticleList;
import com.example.huongvu.nytarticlesearch.models.ArticleSearch;
import com.example.huongvu.nytarticlesearch.network.NYTClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class ArticleSearchActivity extends AppCompatActivity implements AdvanceSettingListener{
    private NYTClient client;
    private ArticleAdapter adapter;
    public List<Article> articlesResponse;
    private ArticleSearch dataSetting;
    private String newsDeskValue1;
    private String newsDeskValue2;
    private String newsDeskValue3;


    @Override
    public void onFinishSetting(ArticleSearch data){
        dataSetting.setNewDeskValues(data.getNewDeskValues());
        dataSetting.setSortOder(data.getSortOder());
        dataSetting.setNewDeskValues(newsDeskValue1 + newsDeskValue2 + newsDeskValue3);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataSetting = new ArticleSearch();

        // Lookup the recyclerview in activity layout
        //ListView rvArticles = (ListView) findViewById(R.id.rvArticles);
        RecyclerView rvArticles = (RecyclerView) findViewById(R.id.rvArticles);

        rvArticles.setHasFixedSize(true);

        articlesResponse = new ArrayList<>();

        adapter = new ArticleAdapter(articlesResponse);
        // Attach the adapter to the recyclerview to populate items
        rvArticles.setAdapter(adapter);


        // Set layout manager to position the items
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        //StaggeredGridLayoutManager gridLayoutManager =
        // new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
       // rvArticles.setLayoutManager(gridLayoutManager);

        rvArticles.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_article_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.onActionViewExpanded();

        searchView.requestFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                fetchArticles(query);

                searchView.clearFocus();

                return true;

            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;

            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //Log.d("DEBUG", "onOptionsItemSelected: Select ");
            showAdvanceSettingDialog();
            //return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showAdvanceSettingDialog() {

        FragmentManager fm = getSupportFragmentManager();
        AdvanceSearchSettingFragment editNameDiaglog = AdvanceSearchSettingFragment.newInstance("Advance Setting");
        editNameDiaglog.show(fm, "Hello word");

    }
    // Executes an API call to the OpenLibrary search endpoint, parses the results
    // Converts them into an array of book objects and adds them to the adapter
    private void fetchArticles(String query) {
        client = new NYTClient();

        client.getArticles(query, dataSetting, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONObject docs;
                    if (response != null) {
                        // Get the docs json array
                        docs = response.getJSONObject("response");
                        String test = docs.toString();
                        Log.d("DEBUG", "onSuccess: " + test);

                        GsonBuilder gsonBuilder = new GsonBuilder();
                        //gsonBuilder.registerTypeAdapter(Article.class, new ArticleDeserializer());
                        Gson gson = gsonBuilder.create();

                        ArticleList articles = gson.fromJson(test, ArticleList.class);

                        //articlesResponse.clear();

                        List<Article> articleRespone = articles.articlesList;
                        //articlesResponse.addAll(articles.articlesList);

                        for (Article article : articleRespone) {

                            articlesResponse.add(article);
                        }
                        //adapter.notifyDataSetChanged();

                        //Log.d("RUN", "onSuccess: " + articlesResponse);

                        adapter.notifyItemRangeInserted(adapter.getItemCount(), articlesResponse.size());

                        //adapter.notifyDataSetChanged();

                    }
                } catch (JSONException e) {
                    // Invalid JSON format, show appropriate error.
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    public  void onCheckboxClicked(View view){
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkBox_sport:
                if (checked)
                // Put some meat on the sandwich
                    newsDeskValue1 = "\"Sports\"";
                else
                // Remove the meat
                break;
            case R.id.checkBox_art:
                if (checked)
                // Cheese me
                    newsDeskValue2 = "\"Arts\"";
                else
                // I'm lactose intolerant
                break;
            // TODO: Veggie sandwich
            case R.id.checkBox_fashion_style:
                if (checked)
                // Cheese me
                    newsDeskValue3 = "\"Fashion & Style\"";
                else
                // I'm lactose intolerant
                break;
        }


    }

}
