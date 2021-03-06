package com.aracssoftware.osmandroidmap;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.ResourceProxy;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import org.osmdroid.views.overlay.MyLocationOverlay;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.MapController;
 
public class OSMap extends Activity {
  
 MIL mIL = null;
 MyLocationOverlay mLO = null;
 MapController myMapController = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
         
        Drawable marker=getResources().getDrawable(R.drawable.pin_red);
        int markerWidth = marker.getIntrinsicWidth();
        int markerHeight = marker.getIntrinsicHeight();
        marker.setBounds(0, markerHeight, markerWidth, 0);
         
        ResourceProxy resourceProxy = new DefaultResourceProxyImpl(getApplicationContext());
         
        mIL = new MIL(marker, resourceProxy);
        mapView.getOverlays().add(mIL);
         
        GeoPoint myPoint1 = new GeoPoint(40.37044, 49.84139);
        mIL.addItem(myPoint1, "myPoint1", "myPoint1");
       // GeoPoint myPoint2 = new GeoPoint(50*1000000, 50*1000000);
       // mIL.addItem(myPoint2, "myPoint2", "myPoint2");
        
        mLO = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(mLO);
        mLO.enableMyLocation();
        myMapController = mapView.getController();
        myMapController.setZoom(3);
         
        ScaleBarOverlay myScaleBarOverlay = new ScaleBarOverlay(this);
        mapView.getOverlays().add(myScaleBarOverlay);
         
    } 
    
    @Override
    protected void onResume() {
     // TODO Auto-generated method stub
     super.onResume();
     mLO.enableMyLocation();
     mLO.enableCompass();
     mLO.enableFollowLocation();
    } 
     
    @Override
    protected void onPause() {
     // TODO Auto-generated method stub
     super.onPause();
     mLO.disableMyLocation();
     mLO.disableCompass();
     mLO.disableFollowLocation();
    }
     
}