package com.example.climatehero.View;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.climatehero.ViewModel.CloudVisionViewModel;
import com.example.climatehero.R;
import com.example.climatehero.databinding.FragmentLoadingBinding;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoadingFragment extends Fragment {

    private FragmentLoadingBinding binding;
    private CloudVisionViewModel cloudVisionViewModel;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoadingBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cloudVisionViewModel = new ViewModelProvider(requireActivity()).get(CloudVisionViewModel.class);

        final Executor executor = Executors.newSingleThreadExecutor();
        final Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            cloudVisionViewModel.recognizeImage();
            //when thread execution complete, navigate to suggestion fragment
            handler.post(() ->
                    NavHostFragment.findNavController(LoadingFragment.this)
                            .navigate(R.id.action_loading_to_suggestion));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}