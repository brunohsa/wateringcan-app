package wateringcan.com.br.wateringcan.rest;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.model.Report;

/**
 * Created by Bruno on 03/11/2017.
 */

@EBean
public class ReportRestService {

    @RestService
    protected IReportRestService reportRestService;

    @RootContext
    Context context;

    public List<Report> findAllReports() {
        AsyncTask<Object, Object, List<Report>> async = new AsyncTask<Object, Object, List<Report>>() {

            @Override
            protected void onPostExecute(List<Report> reports) {
                RelativeLayout layout = (RelativeLayout) getView(R.id.progressLayout);
                layout.setVisibility(View.GONE);

                RecyclerView recyclerView = (RecyclerView) getView(R.id.cardsView);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<Report> doInBackground(Object... params) {
                ResponseEntity<List<Report>> response = reportRestService.findAllReports();
                if (!response.getStatusCode().equals(HttpStatus.OK)) {
                    return null;
                }
                List<Report> reports = response.getBody();
                sortReportList(reports);
                return reports;
            }
        }.execute();

        List<Report> reports = new ArrayList<>();
        try {
            reports = async.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return reports;
    }

    private void sortReportList(List<Report> reports) {
        Collections.sort(reports, new Comparator<Report>() {

            @Override
            public int compare(Report o1, Report o2) {
                if (o1.getDateRelease().before(o2.getDateRelease())) {
                    return 1;
                } else if (o1.getDateRelease().after(o2.getDateRelease())) {
                    return -1;
                }
                return 0;
            }
        });
    }

    private View getView(int id) {
        return ((Activity) context).findViewById(id);
    }
}
