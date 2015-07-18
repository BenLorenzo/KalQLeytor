package com.android3project.dev.mycal.Presenter.MainPresenter;

import com.android3project.dev.mycal.Model.DataDomain;

/**
 * Created by Dev on 7/17/2015.
 */
public interface OperationPresenter {
    public boolean isFieldEmpty(String field);
    public void onOperatorClick(String fNum, String sNum, String operator);
    public DataDomain getDataDomain();
}
