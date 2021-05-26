package com.myassistant.manager.ui.PrivacyPolicy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.myassistant.manager.R;

public class PrivacyPolicy extends Fragment {


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.privacy_policy_fragment, container, false);
        WebView browser = (WebView) root.findViewById(R.id.webview);
        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);

        browser.loadUrl("https://myassistant.jaml46.net/PrivacyAndPolicy.php");
        return root;
    }



}