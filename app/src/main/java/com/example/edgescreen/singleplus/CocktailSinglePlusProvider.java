
package com.example.edgescreen.singleplus;

import com.example.edgescreen.R;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailManager;
import com.samsung.android.sdk.look.cocktailbar.SlookCocktailProvider;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.RemoteViews;

public class CocktailSinglePlusProvider extends SlookCocktailProvider {

    @Override
    public void onVisibilityChanged(Context context, int i, int x){
        int layoutId = R.layout.sample_edge_single_plus;
        RemoteViews rv = new RemoteViews(context.getPackageName(), layoutId);
        try {
            GetWebRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        //rv.setViewVisibility(R.id.main_background, View.VISIBLE);
        //rv.setImageViewResource(R.id.main_background, R.drawable.apps_edge);
        //if (cocktailIds != null) {
          //  for (int id : cocktailIds) {
            //    manager.updateCocktail(id, rv);
            //}

    }

    @Override
    public void onUpdate(Context context, SlookCocktailManager cocktailManager, int[] cocktailIds) {
        panelUpdate(context, cocktailManager, cocktailIds);
    }

    public void panelUpdate(Context context, SlookCocktailManager manager, int[] cocktailIds) {
        int layoutId = R.layout.sample_edge_single_plus;
        RemoteViews rv = new RemoteViews(context.getPackageName(), layoutId);
                       rv.setViewVisibility(R.id.main_background, View.VISIBLE);
            rv.setImageViewResource(R.id.main_background, R.drawable.apps_edge);
        if (cocktailIds != null) {
            for (int id : cocktailIds) {
                manager.updateCocktail(id, rv);
            }
        }
    }


    private class GetHttpAsync extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... url) {
            String result = "here";
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url[0].openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader r = new BufferedReader(new InputStreamReader(in));
                StringBuilder total = new StringBuilder();
                String line;
                while ((line = r.readLine()) != null) {
                    total.append(line);
                }
                result = total.toString();
                System.out.println(total.toString());
            }
            catch(Exception ex){
                System.out.println(ex);
            }
            finally{
                urlConnection.disconnect();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            String x = result;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }

    private void GetWebRequest() throws IOException, ExecutionException, InterruptedException {
        URL url = null;
        try {
            url = new URL("http://www.android.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        AsyncTask task = new GetHttpAsync().execute(url);
        String result = (String) task.get();




    }

}
