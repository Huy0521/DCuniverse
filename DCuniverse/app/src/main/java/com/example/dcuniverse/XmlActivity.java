package com.example.dcuniverse;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class XmlActivity extends AppCompatActivity {
    ListView lvNews;
    TextView txtthum;
    ListXMLAdapter listViewAdapter;
    ArrayList<XMLitem> listNews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        addViews();
        addEvents();

    }

    private void addEvents() {
        lvNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(XmlActivity.this, listNews.get(position).getLink(), Toast.LENGTH_LONG).show();
                Intent webViewIntent = new Intent(getBaseContext(), WebActivity.class);
                webViewIntent.putExtra("newsUrl", listNews.get(position).getLink());
                startActivity(webViewIntent);
            }
        });
    }

    private void addViews() {
        txtthum=findViewById(R.id.txtThum);
        txtthum.setText("Tin");
        lvNews = findViewById(R.id.ListCharacter);
        loadData();
    }

    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadData() {
        new ProcessInBackground().execute();
    }

    public class ProcessInBackground extends AsyncTask<Integer, Void, String> {
        //execute(Integer... integers); publishProgress(Integer... integers); onPostExecute(Void aVoid)

        ProgressDialog progressDialog = new ProgressDialog(XmlActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Loading rss data...");
            progressDialog.show();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Integer... integers) {

            try {
                URL url = new URL("\t\n" +
                        "https://www.kodoani.com/rss/category/tin-tuc-manga");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(getInputStream(url), "UTF-8");

                boolean insideItem = false;
                int eventType = xpp.getEventType();
                XMLitem news = new XMLitem();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            insideItem = true;
                            news = new XMLitem();
                        }
                        else if (xpp.getName().equalsIgnoreCase("title")) {
                            if (insideItem) {
                                news.setTitle(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("pubDate")) {
                            if (insideItem) {
                                news.setDate(xpp.nextText());
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("enclosure")) {
                            if (insideItem) {
                                news.setEnclosure(xpp.getAttributeValue(0));
                            }
                        }
                        else if (xpp.getName().equalsIgnoreCase("link")) {
                            if (insideItem) {
                                news.setLink(xpp.nextText());
                            }
                        }
                    }
                    else if (eventType == XmlPullParser.END_TAG &&
                            xpp.getName().equalsIgnoreCase("item")) {
                        insideItem = false;
                        listNews.add(news);
                    }
                    eventType = xpp.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            listViewAdapter = new ListXMLAdapter(listNews);
            lvNews.setAdapter(listViewAdapter);

            progressDialog.dismiss();
        }
    }
}