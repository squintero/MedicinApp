package com.squintero.medicinapp.ui.base;

/**
 * Every presenter in the app must either implement this interface or extend BasePresenterImpl
 * indicating the MvpView type that wants to be attached with.
 */
public interface BasePresenter<V extends BaseView> {

    void attachView(V baseView);

    void detachView();
}
