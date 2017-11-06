package wateringcan.com.br.wateringcan.holders;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import wateringcan.com.br.wateringcan.R;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ReportViewHolder extends RecyclerView.ViewHolder {

    private TextView temperature;
    private TextView humidity;
    private TextView dayOfWeek;
    private TextView hours;
    private CardView cardView;
    private ImageView imgWeather;


    public ReportViewHolder(View v) {
        super(v);
        this.temperature = v.findViewById(R.id.lblTemperature);
        this.humidity = v.findViewById(R.id.lblHumidity);
        this.dayOfWeek = v.findViewById(R.id.lblDayOfWeek);
        this.hours = v.findViewById(R.id.lblHours);
        this.cardView = v.findViewById(R.id.cardViewLayout);
        this.imgWeather = v.findViewById(R.id.imgWeather);
    }

    public TextView getTemperature() {
        return temperature;
    }

    public TextView getHumidity() {
        return humidity;
    }

    public TextView getDayOfWeek() {
        return dayOfWeek;
    }

    public TextView getHours() {
        return hours;
    }

    public CardView getCardView() {
        return cardView;
    }

    public ImageView getImgWeather() {
        return imgWeather;
    }
}
