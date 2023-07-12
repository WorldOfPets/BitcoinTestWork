package com.example.bitcoinminertestwork.ui.main;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.bitcoinminertestwork.R;
import com.example.bitcoinminertestwork.data.BitcoinDataBase;
import com.example.bitcoinminertestwork.data.DataDao;
import com.example.bitcoinminertestwork.data.DataModel;
import com.example.bitcoinminertestwork.databinding.FragmentMainBinding;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;


public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private DataModel dataModel;
    private MainViewModel homeViewModel;
//    private int speedServer = 0;
//    private double percent = 0.25;
//    private int satoshi = 125;
//    private double btc = 0.00000125;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        DecimalFormat df = new DecimalFormat("#");
        df.setMaximumFractionDigits(10);
        homeViewModel.getData().observe(this, dataModels -> {
            if(!dataModels.isEmpty()){
                dataModel = dataModels.get(0);
                binding.balanceSatoshiTxt.setText(dataModel.satoshi + " Satoshi");
                binding.balanceBtcTxt.setText(df.format(dataModel.btc) + " BTC");
                binding.percentTxt.setText((int)(dataModel.percent * 100) + "%");
                setPercent(dataModel.percent);
                activateServerBoost(dataModel.server);
            }
        });

        binding = FragmentMainBinding.inflate(inflater, container, false);

        binding.btnStart.setOnClickListener(view -> {
            ((LinearLayout) view).startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.boost_btn_anima));
            if ((dataModel.satoshi - 15) >= 0){
                dataModel.satoshi -= 15;
                dataModel.btc -= 0.00000015;
                dataModel.percent += 0.05;
                homeViewModel.update(dataModel);
            }else {
                Toast.makeText(requireContext(), "Insufficient funds.", Toast.LENGTH_LONG).show();
            }
        });
        binding.btnBoost.setOnClickListener(view -> {
            ((LinearLayout) view).startAnimation(AnimationUtils.loadAnimation(requireContext(), R.anim.boost_btn_anima));
            if (dataModel.server < 3){
                dataModel.server++;
                homeViewModel.update(dataModel);
                activateServerBoost(dataModel.server);

            }else{
                dataModel.server=0;
                activateServerBoost(dataModel.server);
                activateServerBoost(dataModel.server);
            }
        });
        binding.btnOpenLink.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/WorldOfPets/BitcoinTestWork"));
            startActivity(browserIntent);
        });
        return binding.getRoot();
    }
    private void setPercent(double per){
        ViewGroup.LayoutParams layoutParams1 = binding.progressFront.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = binding.progressBack.getLayoutParams();
        layoutParams1.height = (int)(layoutParams2.height * per);
        binding.progressFront.setLayoutParams(layoutParams1);
    }
    private void activateServerBoost(int speedServer){
        LinearLayout linearLayout = (LinearLayout) binding.servers.getChildAt(speedServer);
        ImageView imgSever = (ImageView) linearLayout.getChildAt(0);
        //imgSever.setBackgroundResource(R.drawable.speed_green_img);
        imgSever.setImageResource(R.drawable.speed_green_img);
        linearLayout.setBackgroundResource(R.drawable.server_background_green);
        LinearLayout linearSpeed = (LinearLayout) linearLayout.getChildAt(1);
        TextView textPng = (TextView)linearSpeed.getChildAt(0);
        TextView textServer = (TextView)linearSpeed.getChildAt(1);
        TextView textMs = (TextView)linearSpeed.getChildAt(2);
        textPng.setTextColor(Color.parseColor("#000000"));
        textServer.setTextColor(Color.parseColor("#44CB46"));
        textMs.setTextColor(Color.parseColor("#000000"));
        homeViewModel.update(dataModel);
        deactivateServerBoost(speedServer);
    }
    private void deactivateServerBoost(int speedServer){
        for (int i = 0; i < 4; i++){
            if (speedServer != i){
                LinearLayout linearLayout = (LinearLayout) binding.servers.getChildAt(i);
                ImageView imgSever = (ImageView) linearLayout.getChildAt(0);
                //imgSever.setBackgroundResource(R.drawable.speed_green_img);
                imgSever.setImageResource(R.drawable.speed_gray_img);
                linearLayout.setBackgroundResource(R.drawable.server_background_gray);
                LinearLayout linearSpeed = (LinearLayout) linearLayout.getChildAt(1);
                TextView textPng = (TextView)linearSpeed.getChildAt(0);
                TextView textServer = (TextView)linearSpeed.getChildAt(1);
                TextView textMs = (TextView)linearSpeed.getChildAt(2);
                textPng.setTextColor(Color.parseColor("#959595"));
                textServer.setTextColor(Color.parseColor("#959595"));
                textMs.setTextColor(Color.parseColor("#959595"));
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}