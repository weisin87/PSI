package com.sp.sppsi;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.ui.IconGenerator;
import com.sp.sppsi.model.ItemModel;
import com.sp.sppsi.model.PsiModel;
import com.sp.sppsi.model.PsiResponse;
import com.sp.sppsi.model.ReadingModel;
import com.sp.sppsi.model.RegionModel;
import com.sp.sppsi.rest.ApiClient;
import com.sp.sppsi.rest.ApiInterface;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ImageView refreshImg;

    IconGenerator iconFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        iconFactory = new IconGenerator(this);

        iconFactory.setColor(Color.parseColor("#E04006"));
        iconFactory.setTextAppearance(R.style.iconGenText);


        refreshImg = (ImageView) findViewById(R.id.refreshImg);
        refreshImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPsi();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMapToolbarEnabled(false);
        getPsi();
    }

    public void getPsi(){

        refreshImg.clearAnimation();
        refreshImg.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<PsiResponse> call = apiService.getPSI();

        call.enqueue(new Callback<PsiResponse>() {
            @Override
            public void onResponse(Call<PsiResponse> call, Response<PsiResponse> response) {
                PsiResponse results = response.body();

                ReadingModel psiReading = results.getItems().get(0).getReadings().get("psi_twenty_four_hourly");

                List<PsiModel> psiModelList = new ArrayList<PsiModel>();

                DecimalFormat format = new DecimalFormat();
                format.setDecimalSeparatorAlwaysShown(false);


                for(RegionModel region : results.getRegion_metadata()){

                    double psiNumber = 0;

                    try {
                        Field field = psiReading.getClass().getDeclaredField(region.getName());

                        field.setAccessible(true);

                        Class clazzType = field.getType();
                        if (clazzType.toString().equals("double")) {
                            psiNumber = field.getDouble(psiReading);
                        }

                    }catch (Exception e){
                        Log.e("Exception", e.getMessage());
                    }


                    psiModelList.add(new PsiModel(region.getName(), format.format(psiNumber), region.getRegionLocation()));
                }

                mMap.clear();


                for(PsiModel psi: psiModelList){
                    LatLng latlng = new LatLng(psi.getLocation().getLatitude(), psi.getLocation().getLongitude());



                    addIcon(iconFactory, psi.getRegionName().toUpperCase() + " : " + psi.getPsiNumber(), latlng);
                   // mMap.addMarker(new MarkerOptions().position(latlng)
                    //        .title(psi.getRegionName().toUpperCase())).setSnippet("PSI: " + Double.toString(psi.getPsiNumber()));

                    if("central".equals(psi.getRegionName())) {
                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 10.5f));
                    }

                }


                refreshImg.clearAnimation();

            }

            @Override
            public void onFailure(Call<PsiResponse> call, Throwable t) {
                Log.e("response", t.getMessage());
            }
        });
    }

    private void addIcon(IconGenerator iconFactory, CharSequence text, LatLng position) {
        MarkerOptions markerOptions = new MarkerOptions().
                icon(BitmapDescriptorFactory.fromBitmap(iconFactory.makeIcon(text))).
                position(position).
                anchor(iconFactory.getAnchorU(), iconFactory.getAnchorV());

        mMap.addMarker(markerOptions);
    }
}
