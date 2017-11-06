package wateringcan.com.br.wateringcan.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.holders.ReportViewHolder;
import wateringcan.com.br.wateringcan.model.Report;
import wateringcan.com.br.wateringcan.util.DateUtil;

/**
 * Created by Bruno on 30/10/2017.
 */

public class ReportCardAdapter extends RecyclerView.Adapter<ReportViewHolder> {

    private List<Report> reports;
    private Context context;

    public ReportCardAdapter(List<Report> reportList, Context context) {
        this.reports = reportList;
        this.context = context;
    }

    @Override
    public ReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card, parent, false);
        ReportViewHolder pvh = new ReportViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ReportViewHolder holder, int position) {
        Double temperature = reports.get(position).getTemperature();
        Double humidity = reports.get(position).getHumidity();
        Date dateRelease = reports.get(position).getDateRelease();

        holder.getTemperature().setText(temperature.toString());
        holder.getHumidity().setText(humidity.toString());
        holder.getDayOfWeek().setText(DateUtil.getDayOfWeek(dateRelease));
        holder.getHours().setText(DateUtil.getHours(dateRelease));

        setImageAndColorWeather(holder, temperature);
    }

    private void setImageAndColorWeather(ReportViewHolder holder, Double temperature) {
        int image = 0;
        int color = 0;
        if (temperature > 0 && temperature <= 20) {
            image = R.mipmap.cold;
            color = getColor(R.color.cold);
        } else if (temperature <= 0) {
            image = R.mipmap.snow;
            color = getColor(R.color.snow);
        } else {
            image = R.mipmap.sun;
            color = getColor(R.color.summer);
        }
        holder.getImgWeather().setImageResource(image);
        holder.getCardView().setCardBackgroundColor(color);
    }

    private int getColor(Integer color) {
        return ContextCompat.getColor(context, color);
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}
