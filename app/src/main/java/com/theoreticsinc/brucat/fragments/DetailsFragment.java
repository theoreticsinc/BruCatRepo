package com.theoreticsinc.brucat.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.theoreticsinc.brucat.R;

@TargetApi(11)
public class DetailsFragment extends Fragment {

	private LayoutInflater inflater;

	public DetailsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = null;

		rootView = inflater.inflate(R.layout.fragment_details, container,
				false);

/*		ImageButton menuButton = (ImageButton) rootView.findViewById(R.id.menuButton2);
		menuButton.setVisibility(View.GONE);
		ImageView headerLogo = (ImageView) rootView.findViewById(R.id.headerLogo);
		headerLogo.setVisibility(View.GONE);
*/
		int i = getArguments().getInt("SettingsItem");
		byte[] byteArray = getArguments().getByteArray("byteArray");
		Log.d("LEAGUE CHAT", "SettingsItem:" + i);


			String name= getArguments().getString("NAME");
			System.out.println(name);
			String details= getArguments().getString("DETAILS");
			if (name!= null) {
				TextView title = (TextView) rootView.findViewById(R.id.detailstitleText);
				title.setText(name);
			}
			if (details!= null) {
				TextView detailstv = (TextView) rootView.findViewById(R.id.detailsText);
				detailstv.setText(details);
			}

            if(byteArray.length > 0) {
				ImageView previewThumbnail = (ImageView) rootView.findViewById(R.id.detailsImage);
				Bitmap b = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
				previewThumbnail.setImageBitmap(b);
			}

		return rootView;
	}
}
