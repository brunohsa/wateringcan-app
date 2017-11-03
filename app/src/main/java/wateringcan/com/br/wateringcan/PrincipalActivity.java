package wateringcan.com.br.wateringcan;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import wateringcan.com.br.wateringcan.adapters.CardAdapter;
import wateringcan.com.br.wateringcan.model.Report;
import wateringcan.com.br.wateringcan.rest.ReportRestService;

@EActivity(R.layout.activity_principal)
public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @ViewById
    protected Toolbar toolbar;

    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;

    @ViewById(R.id.nav_view)
    protected NavigationView navView;

    @ViewById
    protected RecyclerView cardsView;

    @ViewById
    protected ProgressBar progressBar;

    @Bean
    protected ReportRestService reportRestService;

    @AfterViews
    protected void mountScreen(){
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);

        mountCardView();
    }

    @Background
    protected void mountCardView(){
        final List<Report> reports = reportRestService.findAllReports();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cardsView.setLayoutManager(new LinearLayoutManager(PrincipalActivity.this));
                CardAdapter adapter = new CardAdapter(reports, PrincipalActivity.this);
                cardsView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            cardsView.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);

            mountCardView();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_manage:
                Intent intent = new Intent(this, ConfigurationActivity.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
