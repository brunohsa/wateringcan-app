package wateringcan.com.br.wateringcan.rest;

import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import wateringcan.com.br.wateringcan.model.Report;

/**
 * Created by Bruno on 02/11/2017.
 */

@Rest(rootUrl = "http://192.168.0.17:8080/report",converters = {MappingJackson2HttpMessageConverter.class})
public interface IReportRestService {

    @Get("/findall")
    ResponseEntity<List<Report>> findAllReports();
}
