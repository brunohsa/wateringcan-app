package wateringcan.com.br.wateringcan.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import wateringcan.com.br.wateringcan.ConfigurationDetailActivity_;
import wateringcan.com.br.wateringcan.R;
import wateringcan.com.br.wateringcan.model.Configuration;

/**
 * Created by Bruno on 04/11/2017.
 */

public class ConfigurationViewHolder extends RecyclerView.ViewHolder {

    private TextView lblConfigName;
    private TextView lblDescription;
    private TextView lblValue;
    private List<Configuration> configurationList;

    private View itemView;

    private Context context;

    public ConfigurationViewHolder(View itemView, List<Configuration> configurationList, Context context) {
        super(itemView);
        this.itemView = itemView;
        this.lblConfigName = itemView.findViewById(R.id.lblConfigName);
        this.lblDescription = itemView.findViewById(R.id.lblDescription);
        this.lblValue = itemView.findViewById(R.id.lblValue);
        this.configurationList = configurationList;
        this.context = context;

        onItemClickListener();
    }

    public TextView getLblConfigName() {
        return lblConfigName;
    }

    public TextView getLblDescription() {
        return lblDescription;
    }

    public TextView getLblValue() {
        return lblValue;
    }

    protected void onItemClickListener() {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configuration config = configurationList.get(getAdapterPosition());
                if (config == null) {
                    return;
                }
                Intent intent = new Intent(context, ConfigurationDetailActivity_.class);
                intent.putExtra("configuration", config);
                context.startActivity(intent);
            }
        });
    }
}
