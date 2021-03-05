package com.smitdesai16.androidhtmlcallwithjsoup;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;

public class ContentService extends AsyncTask<Void, Void, ResponseModel> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected ResponseModel doInBackground(Void... voids) {
        try {
            Document document = Jsoup.connect("https://firebase.google.com/").get();
            Element img = document.select("img").first();
            String imgSrc = img.absUrl("src");

            InputStream inputStream = new java.net.URL(imgSrc).openStream();

            ResponseModel responseModel = new ResponseModel();
            responseModel.bitmap = BitmapFactory.decodeStream(inputStream);
            responseModel.title = document.title();

            return responseModel;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ResponseModel responseModel) {
        super.onPostExecute(responseModel);
    }
}
