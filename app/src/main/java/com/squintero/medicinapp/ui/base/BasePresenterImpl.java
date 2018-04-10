package com.squintero.medicinapp.ui.base;

/**
 * Base class that implements the BasePresenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenterImpl<T extends BaseView> implements BasePresenter<T> {

    private T baseView;

    @Override
    public void attachView(T baseView) {
        this.baseView = baseView;
    }

    @Override
    public void detachView() {
        this.baseView = null;
    }

    public boolean isViewAttached() {
        return baseView != null;
    }

    public T getBaseView() {
        return baseView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new BaseViewNotAttachedException();
    }

    public static class BaseViewNotAttachedException extends RuntimeException {
        public BaseViewNotAttachedException() {
            super("Please call BasePresenter.attachView(BaseView) before" +
                    " requesting data to the BasePresenter");
        }
    }
}
