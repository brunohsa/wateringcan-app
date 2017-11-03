package wateringcan.com.br.wateringcan.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bruno on 30/10/2017.
 */

public class Report {

    private Long id;

    private Date dateRelease;

    private Double humidity;

    private Double temperature;

    public Report() {
    }

    public Report(Double humidity, Double temperature) {
        this.dateRelease = Calendar.getInstance().getTime();
        this.humidity = humidity;
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public Date getDateRelease() {
        return dateRelease;
    }

    public void setDateRelease(Date dateRelease) {
        this.dateRelease = dateRelease;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
}
