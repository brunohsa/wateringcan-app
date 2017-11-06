package wateringcan.com.br.wateringcan;


import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import wateringcan.com.br.wateringcan.adapters.ConfigurationAdapter;
import wateringcan.com.br.wateringcan.configuration.WateringCanConfig;
import wateringcan.com.br.wateringcan.model.Configuration;
import wateringcan.com.br.wateringcan.rest.ConfigRestService;

@EActivity(R.layout.activity_configuration)
public class ConfigurationActivity extends AppCompatActivity {

    @Bean
    protected ConfigRestService configRestService;

    @ViewById
    protected RecyclerView configRecyclerView;

    @ViewById
    protected Switch swtAutomaticRefresh;

    @ViewById
    protected ProgressBar progressBarConfigs;

    @AfterViews
    protected void mountScreen() {
        setHomeButton();
        mountConfigCards();
        setSwitchStatus();
        addOnCheckChangeListener();
    }

    private void setSwitchStatus() {
        swtAutomaticRefresh.setChecked(WateringCanConfig.isAutomaticRefresh());
    }

    private void setHomeButton() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Background
    protected void mountConfigCards() {
        final List<Configuration> configs = configRestService.findAll();
        if (configs == null) {
            return;
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                configRecyclerView.setLayoutManager(new LinearLayoutManager(ConfigurationActivity.this));
                ConfigurationAdapter adapter = new ConfigurationAdapter(configs, ConfigurationActivity.this);
                configRecyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        configRecyclerView.setVisibility(View.GONE);
        progressBarConfigs.setVisibility(View.VISIBLE);
        mountConfigCards();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    private void addOnCheckChangeListener() {
        swtAutomaticRefresh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                WateringCanConfig.setAutomaticRefresh(isChecked);
            }
        });
    }
}
