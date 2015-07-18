package com.android3project.dev.mycal.View;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android3project.dev.mycal.Model.DataDomain;
import com.android3project.dev.mycal.Presenter.MainPresenter.MainPresenter;
import com.android3project.dev.mycal.Presenter.MainPresenter.OperationPresenter;
import com.android3project.dev.mycal.Presenter.MainPresenter.OperationPresenterImpl;
import com.android3project.dev.mycal.R;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity implements MainPresenter{

    @Bind(R.id.edtFirstNumber)
    EditText edtFirstNum;

    @Bind(R.id.edtSecondNumber)
    EditText edtSecondNum;

    OperationPresenter operationPresenter;
    DataDomain dataDomain;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        operationPresenter = new OperationPresenterImpl();
        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidation.setColor(Color.RED);
    }

    @OnClick({R.id.btnAdd, R.id.btnMinus, R.id.btnDivide, R.id.btnMultiply})
    public void computeOperation(Button view) {
        String fNum = this.edtFirstNum.getText().toString();
        String sNum = this.edtSecondNum.getText().toString();
        String optr = view.getText().toString();
        if(!operationPresenter.isFieldEmpty(fNum) && !operationPresenter.isFieldEmpty(sNum)){
            operationPresenter.onOperatorClick(fNum, sNum, optr);
            startResultActivity(operationPresenter.getDataDomain());
        }else{
            if(operationPresenter.isFieldEmpty(fNum)){
                setFirstFieldError();
            }
            if (operationPresenter.isFieldEmpty(sNum)) {
                setSecondFieldError();
            }
        }
    }

    @OnClick(R.id.btnCancel)
    public void clearEditText() {
        this.edtFirstNum.setText("");
        this.edtSecondNum.setText("");
        this.edtFirstNum.setError(null);
        this.edtSecondNum.setError(null);
        this.edtFirstNum.requestFocus();
    }

    @OnClick(R.id.btnExit)
    public void exitApp() {
        this.finish();
    }

    @Override
    public void setFirstFieldError() {
        awesomeValidation.addValidation(edtFirstNum, "regex", "Ayaw ko kalimti");
        awesomeValidation.validate();
    }

    @Override
    public void setSecondFieldError() {
        awesomeValidation.addValidation(edtSecondNum, "regex", "Ayaw ko kalimti");
        awesomeValidation.validate();
    }

    public void startResultActivity(DataDomain dataDomain) {
        Intent intent = new Intent(getBaseContext(),ResultActivity.class);
        intent.putExtra(KEY_DATA,dataDomain);
        startActivity(intent);
    }
}
