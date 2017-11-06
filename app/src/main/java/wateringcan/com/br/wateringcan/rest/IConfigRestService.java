package wateringcan.com.br.wateringcan.rest;

import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

import wateringcan.com.br.wateringcan.model.Configuration;

/**
 * Created by Bruno on 04/11/2017.
 */
@Rest(rootUrl = "http://192.168.0.17:8080/configuration",converters = {MappingJackson2HttpMessageConverter.class})
public interface IConfigRestService {

    @Get("/findall")
    ResponseEntity<List<Configuration>> findAll();

    @Post("/update")
    void update(@Body Configuration configuration);
}
