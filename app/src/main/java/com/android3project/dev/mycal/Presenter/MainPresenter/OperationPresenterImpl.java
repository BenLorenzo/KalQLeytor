package com.android3project.dev.mycal.Presenter.MainPresenter;

import android.text.TextUtils;

import com.android3project.dev.mycal.Model.DataDomain;

/**
 * Created by Dev on 7/17/2015.
 */
public class OperationPresenterImpl implements OperationPresenter {
    DataDomain dataDomain;
    @Override
    public boolean isFieldEmpty(String field) {
        if(TextUtils.isEmpty(field)){
            return true;
        }
        return false;
    }

    @Override
    public void onOperatorClick(String fNum, String sNum, String operator) {
        this.dataDomain = new DataDomain(fNum,sNum,operator);
    }

    @Override
    public DataDomain getDataDomain() {
        return dataDomain;
    }
}
