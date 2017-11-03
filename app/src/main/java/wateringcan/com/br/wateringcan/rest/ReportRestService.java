package wateringcan.com.br.wateringcan.rest;

import android.os.AsyncTask;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.annotations.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import wateringcan.com.br.wateringcan.model.Report;

/**
 * Created by Bruno on 03/11/2017.
 */

@EBean
public class ReportRestService {

    @RestService
    protected IReportRestService reportRestService;

    public List<Report> findAllReports() {
        AsyncTask<Object, Object, List<Report>> async = new AsyncTask<Object, Object, List<Report>>() {

            @Override
            protected List<Report> doInBackground(Object... params) {
                ResponseEntity<List<Report>> response = reportRestService.findAllReports();
                if (!response.getStatusCode().equals(HttpStatus.OK)) {
                    return null;
                }
                return response.getBody();
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
}
