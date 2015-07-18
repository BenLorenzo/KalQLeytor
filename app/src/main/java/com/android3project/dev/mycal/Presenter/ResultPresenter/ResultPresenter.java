package com.android3project.dev.mycal.Presenter.ResultPresenter;

import java.text.ParseException;

/**
 * Created by Dev on 7/17/2015.
 */
public interface ResultPresenter {
    static final String KEY_DATA = "data_domain_object";
    public void setSuccessColor();
    public void setErrorColor();
    public void validateResult(String result) throws ParseException;
}
