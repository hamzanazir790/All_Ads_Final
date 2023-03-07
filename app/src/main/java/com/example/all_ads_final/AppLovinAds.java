package com.example.all_ads_final;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.sdk.AppLovinSdkUtils;

public class AppLovinAds {

    public  static void BannerAd(Activity context, LinearLayout rootView){
        MaxAdView maxBannerAdView = new MaxAdView(context.getResources().getString(R.string.banner), context);
        final boolean isTablet = AppLovinSdkUtils.isTablet(context);
        final int heightPx = AppLovinSdkUtils.dpToPx(context, isTablet ? 100 : 80);
        maxBannerAdView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, heightPx));
        rootView.addView(maxBannerAdView);
        rootView.setVisibility(View.VISIBLE);
        maxBannerAdView.loadAd();
    }
    public  static MaxInterstitialAd loadAdsInterstitialAd(Context context, Activity activity) {
        final MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(context.getResources().getString(R.string.interstitial), activity);
        MaxAdListener maxAdListener = new MaxAdListener() {
            @Override
            public void onAdLoaded(MaxAd ad) {
            }
            @Override
            public void onAdDisplayed(MaxAd ad) {
            }
            @Override
            public void onAdHidden(MaxAd ad) {
                //saveImageInSdCard();
            }
            @Override
            public void onAdClicked(MaxAd ad) {
            }
            @Override
            public void onAdLoadFailed(String adUnitId, MaxError error) {
                maxInterstitialAd.loadAd();
            }
            @Override
            public void onAdDisplayFailed(MaxAd ad, MaxError error) {
            }
        };
        maxInterstitialAd.setListener(maxAdListener);
        maxInterstitialAd.loadAd();
        return maxInterstitialAd;
    }
    public static  boolean checkInternetConenction(Context context) {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() ==
                android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() ==
                        android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();

            return true;
        }else if (
                connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            return false;
        }
        return false;

    }
    public  static  void createNativeAd(Activity context, final LinearLayout nativeAdContainer)
    {

        MaxNativeAdLoader nativeAdLoader = new MaxNativeAdLoader(context.getResources().getString(R.string.native_small), context);
        nativeAdLoader.setNativeAdListener( new MaxNativeAdListener()
        {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad)
            {
                // Add ad view to view.
                nativeAdContainer.setVisibility(View.VISIBLE);
                nativeAdContainer.removeAllViews();
                nativeAdContainer.addView( nativeAdView );
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error)
            {
                // We recommend retrying with exponentially higher delays up to a maximum delay
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad)
            {
                // Optional click callback
            }
        } );

        nativeAdLoader.loadAd();
    }
}