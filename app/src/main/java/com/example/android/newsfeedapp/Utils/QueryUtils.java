package com.example.android.newsfeedapp.Utils;

import android.text.TextUtils;
import android.util.Log;

import com.example.android.newsfeedapp.Data.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class with methods to help perform the HTTP request and
 * parse the response.
 */
public class QueryUtils {

    /** Tag for the log messages */
    private static String LOG_TAG = QueryUtils.class.getSimpleName();

    //Creates an instance of the QueryUtils class
    private QueryUtils() {

    }

    /** Creates fetch news data which passes in the url and
     * Creates an http request, extracts data from the json response and parses them
     */
    public static List<NewsData> fetchNewsData(String requestUrl) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<NewsData> news = extractFromJson(jsonResponse);

        // Return the {@link List<NewsData>}
        return news;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.connect();

            // If the request was successful (response code == 200),
            // then read input stream and parse the connection.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader streamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link List<NewsData>} object by parsing out information
     * about the first earthquake from the input earthquakeJSON string.
     */
    private static List<NewsData> extractFromJson(String newsJson) {

        List<NewsData> newsArrayList = new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(newsJson)) {
            return null;
        }

        try {
            JSONObject baseJson = new JSONObject(newsJson);
            JSONArray news_array = baseJson.getJSONObject("response").getJSONArray("results");

            for (int i = 0; i < news_array.length(); i++) {
                JSONObject currentNews = news_array.getJSONObject(i);

                // Extract out the name of section, title of the story, the date and url values
                String nameOfSection = currentNews.getString("sectionName");
                String title = currentNews.getString("webTitle");
                String date = currentNews.getString("webPublicationDate");
                String url = currentNews.getString("webUrl");

                JSONObject fields = news_array.getJSONObject(i).getJSONObject("fields");

                // Extract out the thumbnail of the news and the story of the news values
                String thumbnail = fields.getString("thumbnail");
                String body = fields.getString("bodyText");
                List<String> contributor = new ArrayList<>();
                JSONArray contributor_array = currentNews.getJSONArray("tags");

                for (int j = 0; j < contributor_array.length(); j++) {

                    JSONObject currentContributor = contributor_array.getJSONObject(j);
                    String contributor_name;
                    contributor_name = currentContributor.getString("webTitle");
                    contributor.add(contributor_name);
                }

                NewsData news = new NewsData(title, thumbnail, url, date, nameOfSection, contributor,body);
                newsArrayList.add(news);

            }

        } catch (JSONException e) {

            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        return newsArrayList;
    }
}
