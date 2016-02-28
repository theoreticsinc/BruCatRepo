package com.theoreticsinc.brucat.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.theoreticsinc.brucat.R;

@TargetApi(11)
public class SettingsFragment extends Fragment {

	private LayoutInflater inflater;

	public SettingsFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		int i = getArguments().getInt("SettingsItem");
		Log.d("LEAGUE CHAT", "SettingsItem:" + i);
		View rootView = null;
		if (i == 1) {
			rootView = inflater.inflate(R.layout.add_new_post, container,
					false);
			getActivity().setTitle("New Post");
		}
		else if (i == 2) {
		rootView = inflater.inflate(R.layout.fragment_profile, container,
				false);
		getActivity().setTitle("Settings");
		}
		else {
			rootView = inflater.inflate(R.layout.fragment_planet, container,
					false);
		}
		

		return rootView;
	}
}
