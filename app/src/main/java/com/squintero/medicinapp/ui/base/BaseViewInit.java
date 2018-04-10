package com.squintero.medicinapp.ui.base;

import android.view.View;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View BasePresenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an ActivityEdit or Home.
 */
public interface BaseViewInit {

    void init(View view);

    void initCustomerFonts();
}
