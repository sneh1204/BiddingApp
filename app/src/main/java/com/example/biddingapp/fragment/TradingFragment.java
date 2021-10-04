package com.example.biddingapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.biddingapp.R;
import com.example.biddingapp.databinding.FragmentTradingBinding;
import com.example.biddingapp.models.User;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class TradingFragment extends Fragment {

    FragmentTradingBinding binding;
    NavController navController;
    User user;
    ITrading am;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ITrading) {
            am = (ITrading) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getActivity().setTitle("Ongoing Bids");

        user = am.getUser();

        binding = FragmentTradingBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        navController = Navigation.findNavController(getActivity(), R.id.fragmentContainerView);

        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_tradingFragment_to_postItemFragment);
            }
        });

        binding.bottomNavigation.setSelectedItemId(R.id.home);
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bids:
                        return true;
                    case R.id.history:
                        navController.navigate(R.id.action_tradingFragment_to_historyFragment);
                        return true;
                    case R.id.profileIcons:
                        navController.navigate(R.id.action_tradingFragment_to_userProfileFragment);
                        return true;
                    case R.id.logOutIcons:
                        FirebaseAuth.getInstance().signOut();
                        navController.navigate(R.id.action_tradingFragment_to_loginFragment);
                        return true;

                }
                return false;
            }
        });

        return view;
    }

    public interface ITrading{

        User getUser();

    }

}