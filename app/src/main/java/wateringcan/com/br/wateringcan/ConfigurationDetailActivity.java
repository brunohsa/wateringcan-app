package wateringcan.com.br.wateringcan;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.Serializable;

import wateringcan.com.br.wateringcan.model.Configuration;
import wateringcan.com.br.wateringcan.rest.ConfigRestService;

@EActivity(R.layout.activity_configuration_detail)
public class ConfigurationDetailActivity extends AppCompatActivity {

    @ViewById
    protected EditText lblEditName;

    @ViewById
    protected EditText lblEditDescription;

    @ViewById
    protected EditText lblEditValue;

    @Bean
    protected ConfigRestService configRestService;

    private Menu pageMenu;

    private Configuration config;

    @AfterViews
    protected void mountScreen() {
        createHomeButton();
        setScreenValues();

        addLblEditValueTextChance();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.pageMenu = menu;

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);

        setSaveMenuItemVisible(false);
        return true;
    }

    private void createHomeButton() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setScreenValues() {
        Intent intent = getIntent();
        config = (Configuration) intent.getSerializableExtra("configuration");

        lblEditName.setText(config.getName());
        lblEditDescription.setText(config.getDescription());
        lblEditValue.setText(config.getValue().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.save_menu:
                updateData();
                Toast.makeText(this, "Configuração Alterada Com Sucesso", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Background
    protected void updateData(){
        Integer newValue = Integer.valueOf(lblEditValue.getText().toString());
        config.setValue(newValue);

        configRestService.update(config);
        finish();
    }

    private void addLblEditValueTextChance() {
        lblEditValue.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setSaveMenuItemVisible(true);
            }
        });
    }

    private void setSaveMenuItemVisible(boolean visible) {
        MenuItem item = pageMenu.findItem(R.id.save_menu);
        item.setVisible(visible);
    }
}
