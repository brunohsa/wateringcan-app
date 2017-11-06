package wateringcan.com.br.wateringcan.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.holders.ConfigurationViewHolder;
import wateringcan.com.br.wateringcan.model.Configuration;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ConfigurationAdapter extends RecyclerView.Adapter<ConfigurationViewHolder> {

    private Context context;

    private List<Configuration> configurationList;

    public ConfigurationAdapter(List<Configuration> configurationList, Context context) {
        this.configurationList = configurationList;
        this.context = context;
    }

    @Override
    public ConfigurationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.configuration_card, parent, false);
        ConfigurationViewHolder pvh = new ConfigurationViewHolder(v, configurationList, context);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ConfigurationViewHolder holder, int position) {
        String name = configurationList.get(position).getName();
        String description = configurationList.get(position).getDescription();
        Integer value = configurationList.get(position).getValue();

        holder.getLblConfigName().setText(name);
        holder.getLblDescription().setText(description);
        holder.getLblValue().setText(value.toString());
    }

    @Override
    public int getItemCount() {
        return configurationList.size();
    }
}
