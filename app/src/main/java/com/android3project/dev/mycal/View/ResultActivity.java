package com.android3project.dev.mycal.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android3project.dev.mycal.Model.DataDomain;
import com.android3project.dev.mycal.Presenter.ResultPresenter.ResultPresenter;
import com.android3project.dev.mycal.R;

import java.text.ParseException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultActivity extends AppCompatActivity implements ResultPresenter{

    @Bind(R.id.tvFirstNumber)
    TextView tvFirstNumber;
    @Bind(R.id.tvOperator)
    TextView tvOperator;
    @Bind(R.id.tvSecondNumber)
    TextView tvSecondNumber;
    @Bind(R.id.tvResult)
    TextView tvResult;
    DataDomain dataDomain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        dataDomain = getIntent().getParcelableExtra(KEY_DATA);
        if(dataDomain != null){
            tvFirstNumber.setText(dataDomain.getFirstNumber());
            tvOperator.setText(dataDomain.getOperator());
            tvSecondNumber.setText(dataDomain.getSecondNumber());
            String result = dataDomain.getResult();
            try {
                validateResult(result);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void setSuccessColor() {
        tvResult.setBackgroundColor(getResources().getColor(R.color.successColor));
    }

    @Override
    public void setErrorColor() {
        tvResult.setBackgroundColor(getResources().getColor(R.color.errorColor));
    }

    @Override
    public void validateResult(String result) throws ParseException {
        if(dataDomain.getNumFormat(result).doubleValue() == -1D){
            tvResult.setText("ERROR");
            setErrorColor();
        }else {
            tvResult.setText(result);
            setSuccessColor();
        }

        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }
}
