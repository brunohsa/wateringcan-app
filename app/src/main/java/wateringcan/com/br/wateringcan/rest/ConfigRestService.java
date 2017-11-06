package wateringcan.com.br.wateringcan.rest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.model.Configuration;
import wateringcan.com.br.wateringcan.model.Report;

/**
 * Created by Bruno on 04/11/2017.
 */
@EBean
public class ConfigRestService {

    @RestService
    protected IConfigRestService configRestService;

    @RootContext
    Context context;

    public List<Configuration> findAll() {
        AsyncTask<Object, Object, List<Configuration>> async = new AsyncTask<Object, Object, List<Configuration>>() {

            @Override
            protected void onPostExecute(List<Configuration> configurations) {
                ProgressBar progressBar = (ProgressBar) getView(R.id.progressBarConfigs);
                progressBar.setVisibility(View.GONE);

                RecyclerView recyclerView = (RecyclerView) getView(R.id.configRecyclerView);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<Configuration> doInBackground(Object... params) {
                ResponseEntity<List<Configuration>> configs = configRestService.findAll();
                if (!configs.getStatusCode().equals(HttpStatus.OK)) {
                    return null;
                }
                List<Configuration> configurations = configs.getBody();
                return configurations;
            }
        }.execute();

        List<Configuration> configurations = new ArrayList<>();
        try {
            configurations = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return configurations;
    }

    public void update(final Configuration configuration) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                configRestService.update(configuration);
                return null;
            }
        }.execute();
    }

    private View getView(int id) {
        return ((Activity) context).findViewById(id);
    }
}
