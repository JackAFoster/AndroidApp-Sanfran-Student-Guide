package com.example.assignment_5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.webkit.WebViewClient;
import android.widget.PopupMenu;
import android.widget.TabHost;
import android.widget.Button;
import android.widget.EditText;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.webkit.WebView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    private EditText text;
    private WebView webView;
    private Button button, button2;
    private GoogleMap mMap;
    private static final LatLng University_of_Pacific = new LatLng(37.7826801839483, -122.40561381000833);

    private static final LatLng Heald_College = new LatLng(37.78198486735459, -122.40398302689978);
    private static final LatLng San_Francisco_State_University = new LatLng(37.78491871978695, -122.40657940516886);
    private static final LatLng City_College_of_San_Francisco = new LatLng(37.784855125994525, -122.40471392887149);
    private static final float zoom = 16.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabs = (TabHost) findViewById(R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec;


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync((OnMapReadyCallback) this);


        // Initialize a TabSpec for tab1 and add it to the TabHost
        spec = tabs.newTabSpec("tag1");    //create new tab specification
        spec.setContent(R.id.tab1);    //add tab view content
        spec.setIndicator("Map");    //put text on tab
        tabs.addTab(spec);             //put tab in TabHost containe


            // Initialize a TabSpec for tab2 and add it to the TabHost
            spec = tabs.newTabSpec("tag2");        //create new tab specification
            spec.setContent(R.id.tab2);            //add view tab content
            spec.setIndicator("Web");
            tabs.addTab(spec);                    //put tab in TabHost container

            button = (Button) findViewById(R.id.Button01);
            text = (EditText) findViewById(R.id.EditText01);
            webView = (WebView) findViewById(R.id.web);

            //intercept URL loading and load in widget

            webView.setWebViewClient(new WebViewClient() {

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

            });

            //set listeners for web tab
            button.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.loadUrl(text.getText().toString());

                }
            });

            text.setOnKeyListener(new OnKeyListener() {
                public boolean onKey(View view, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_ENTER) {
                        webView.getSettings().setJavaScriptEnabled(true);
                        webView.loadUrl(text.getText().toString());
                        return true;
                    }
                    return false;
                }
            });


            // Initialize a TabSpec for tab3 and add it to the TabHost
            spec = tabs.newTabSpec("tag3");        //create new tab specification
            spec.setContent(R.id.tab3);            //add tab view content
            spec.setIndicator("tab3");            //put text on tab
            tabs.addTab(spec);
            button2 = (Button) findViewById(R.id.Button02);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Initialize a new PopupMenu
                    PopupMenu popup = new PopupMenu(MainActivity.this, view);

                    // Inflate the menu resource
                    popup.getMenuInflater().inflate(R.menu.menu_main, popup.getMenu());

                    // Set a click listener for the menu items
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            // Handle menu item clicks
                            switch (item.getItemId()) {
                                case R.id.item1:
                                    tabs.setCurrentTabByTag("tag2");
                                    text.setText("https://sf.eater.com/maps/best-restaurants-san-francisco-38");
                                    webView.loadUrl(text.getText().toString());
                                    // Do something
                                    return true;
                                case R.id.item2:

                                    tabs.setCurrentTabByTag("tag2");
                                    text.setText("https://tourscanner.com/blog/best-tourist-attractions-in-san-francisco/");
                                    webView.loadUrl(text.getText().toString());

                                    return true;
                                case R.id.item3:

                                    tabs.setCurrentTabByTag("tag2");
                                    text.setText("https://www.cuddlynest.com/blog/bars-in-san-francisco/");
                                    webView.loadUrl(text.getText().toString());

                                    return true;
                                case R.id.item4:

                                    tabs.setCurrentTabByTag("tag2");
                                    text.setText("https://health.usnews.com/best-hospitals/area/san-francisco-ca");
                                    webView.loadUrl(text.getText().toString());

                                    return true;
                                case R.id.item5:

                                    tabs.setCurrentTabByTag("tag2");
                                    text.setText("https://www.yelp.com/search?find_desc=Gaming+Pc+Cafe&find_loc=San+Francisco%2C+CA");
                                    webView.loadUrl(text.getText().toString());

                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });

                    // Show the PopupMenu
                    popup.show();
                }

            });

        }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add markers at the schools, set title and snippet, and move the camera
        mMap.addMarker(new MarkerOptions().position(Heald_College).title("Heald College")
                .snippet("https://oag.ca.gov/consumers/general/corinthian-colleges/sf"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Heald_College, zoom));
        mMap.addMarker(new MarkerOptions().position(San_Francisco_State_University).title("San Francisco State University")
                .snippet("https://cpage.sfsu.edu/downtowncampus"));
        mMap.addMarker(new MarkerOptions().position(City_College_of_San_Francisco).title("City College of San Francisco")
                .snippet("https://www.ccsf.edu/about/our-locations/downtown-center"));
        mMap.addMarker(new MarkerOptions().position(University_of_Pacific).title("University of Pacific")
                .snippet("https://www.pacific.edu/san-francisco-campus"));
        //display a Toast when the marker is clicked
        mMap.setOnMarkerClickListener(
                new GoogleMap.OnMarkerClickListener() {
                    TabHost tabs = findViewById(R.id.tabhost);
                    public boolean onMarkerClick(Marker m) {
                        //get title and snippet to display on Toast, get the snip and search that in the webview

                        String title = m.getTitle();
                        String snip = m.getSnippet();
                        Toast.makeText(MainActivity.this, title,Toast.LENGTH_LONG).show();
                        tabs.setCurrentTabByTag("tag2");
                        text.setText(snip);
                        webView.loadUrl(text.getText().toString());
                        text.setText("");

                        return true;
                    }
                }
        );
    }
}