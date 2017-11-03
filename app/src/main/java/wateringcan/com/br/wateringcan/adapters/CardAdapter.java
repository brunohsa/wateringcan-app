package wateringcan.com.br.wateringcan.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.model.Report;
import wateringcan.com.br.wateringcan.util.DateUtil;

/**
 * Created by Bruno on 30/10/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    private List<Report> reports;
    private Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView temperature;
        TextView humidity;
        TextView dayOfWeek;
        TextView hours;
        CardView cardView;
        ImageView imgWeather;


        public ViewHolder(View v) {
            super(v);
            this.temperature = v.findViewById(R.id.lblTemperature);
            this.humidity = v.findViewById(R.id.lblHumidity);
            this.dayOfWeek = v.findViewById(R.id.lblDayOfWeek);
            this.hours = v.findViewById(R.id.lblHours);
            this.cardView = v.findViewById(R.id.cardViewLayout);
            this.imgWeather = v.findViewById(R.id.imgWeather);
        }
    }

    public CardAdapter(List<Report> reportList, Context context) {
        this.reports = reportList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card, parent, false);
        ViewHolder pvh = new ViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Double temperature = reports.get(position).getTemperature();
        Double humidity = reports.get(position).getHumidity();
        Date dateRelease = reports.get(position).getDateRelease();

        holder.temperature.setText(temperature.toString());
        holder.humidity.setText(humidity.toString());
        holder.dayOfWeek.setText(DateUtil.getDayOfWeek(dateRelease));
        holder.hours.setText(DateUtil.getHours(dateRelease));

        setImageAndColorWeather(holder, temperature);
    }

    private void setImageAndColorWeather(ViewHolder holder, Double temperature) {
        if (temperature > 0 && temperature <= 20) {
            holder.imgWeather.setImageResource(R.mipmap.cold);
            holder.cardView.setCardBackgroundColor(getColor(R.color.cold));
        } else if (temperature <= 0) {
            holder.imgWeather.setImageResource(R.mipmap.snow);
            holder.cardView.setCardBackgroundColor(getColor(R.color.snow));
        } else {
            holder.imgWeather.setImageResource(R.mipmap.sun);
            holder.cardView.setCardBackgroundColor(getColor(R.color.summer));
        }
    }

    private int getColor(Integer color) {
        return ContextCompat.getColor(context, color);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}