package br.com.hackaton.up.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.util.Pair;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import br.com.hackaton.up.R;
import br.com.hackaton.up.model.Results;
import br.com.hackaton.up.recyclerview.FeedRecyclerViewAdapter;

public class AcompanharActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String gcm_key;
    private GoogleCloudMessaging gcm;
    String PROJECT_NUMBER = "NUMERO_DO_PROJETO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_acompanhar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.feed_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        try {
            mAdapter = new FeedRecyclerViewAdapter(getDataSet(), AcompanharActivity.this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        mRecyclerView.setAdapter(mAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getRegId();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.acompanhar, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Results getDataSet() throws IOException {

        try {
            return new GetFeedList().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void getRegId(){
        new AsyncTask<Void, Void, String>(){

            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if (gcm == null){
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }

                    gcm_key = gcm.register(PROJECT_NUMBER);

                    URL url = new URL("http://10.0.0.103/up/save_regid.php?gcm_key=" + gcm_key);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("GET");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

                    Log.i("HTTP_URL", conn.getURL().getPath());
                    writer.flush();
                    writer.close();
                    os.close();
                    Log.i("HTTP_RESPONSE", conn.getResponseCode() + ": " + conn.getResponseMessage());

                    Log.i("GCM", gcm_key);
                } catch (IOException e){
                    msg = "Error :" + e.getMessage();
                    e.printStackTrace();
                }

                return msg;
            }
        }.execute();
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }
}

class GetFeedList extends AsyncTask<Void, Void, Results> {

    @Override
    protected Results doInBackground(Void... params) {

        ObjectMapper mapper = new ObjectMapper();

        Results feedList;
        try {
            feedList = mapper.readValue(new URL("http://10.0.0.103/up"), Results.class);
            return feedList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
