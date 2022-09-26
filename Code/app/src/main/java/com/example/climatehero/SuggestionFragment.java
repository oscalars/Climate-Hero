package com.example.climatehero;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.climatehero.databinding.FragmentSecondBinding;
import com.example.climatehero.databinding.FragmentSuggestionBinding;


public class SuggestionFragment extends Fragment {

    private FragmentSuggestionBinding binding;
    private itemViewModel viewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSuggestionBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(itemViewModel.class);

        binding.buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SuggestionFragment.this)
                        .navigate(R.id.action_Suggestion_to_FirstFragment);

            }
        });

        String item = viewModel.getItem();
        String defaultBin = "organic waste bin";
        binding.suggestionText.setText(new StringBuilder().append("Recycle the ").append(item).append(" in the ").append(defaultBin).toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}