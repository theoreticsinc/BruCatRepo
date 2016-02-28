package com.theoreticsinc.brucat.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.theoreticsinc.brucat.R;

@TargetApi(11)
public class WebsiteFragment extends Fragment {

	private LayoutInflater inflater;
	private WebView webView;

	public WebsiteFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = null;

		rootView = inflater.inflate(R.layout.websitelink, container,
				false);

		ImageButton menuButton = (ImageButton) rootView.findViewById(R.id.menuButton2);
		menuButton.setVisibility(View.GONE);
		ImageView headerLogo = (ImageView) rootView.findViewById(R.id.headerLogo);
		headerLogo.setVisibility(View.GONE);

		webView = (WebView) rootView.findViewById(R.id.webView);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("https://www.facebook.com/pages/Church-of-Our-Lady-of-the-Assumption/190646080973138");

		return rootView;
	}

}
