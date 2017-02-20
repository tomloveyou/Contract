package personcom.yl.contract.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import personcom.yl.contract.R;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.utils.StatusBarUtils;
import personcom.yl.contract.widget.ClearEditText;
import personcom.yl.contract.widget.EaseImageView;
import personcom.yl.contract.widget.multi_image_selector.MultiImageSelectorActivity;

public class AddContractActivity extends AppCompatActivity {

    @Bind(R.id.activity_add_contract_name)
    ClearEditText activityAddContractName;
    @Bind(R.id.activity_add_contract_conpaney)
    ClearEditText activityAddContractConpaney;
    @Bind(R.id.activity_add_contract_job)
    ClearEditText activityAddContractJob;
    @Bind(R.id.activity_add_contract_bumen)
    ClearEditText activityAddContractBumen;
    @Bind(R.id.activity_add_contract_phone)
    ClearEditText activityAddContractPhone;
    @Bind(R.id.activity_add_contract_address)
    ClearEditText activityAddContractAddress;
    @Bind(R.id.activity_add_contract)
    LinearLayout activityAddContract;
    @Bind(R.id.activity_add_contract_avator)
    EaseImageView activityAddContractAvator;
    private ArrayList<String> mSelectPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contract);
        ButterKnife.bind(this);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.colorPrimary);

    }

    @OnClick({R.id.activity_add_contract_cancel, R.id.activity_add_contract_comfirm, R.id.activity_add_contract_avator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_add_contract_cancel:
                finish();
                break;
            case R.id.activity_add_contract_comfirm:
                setdata();
                break;
            case R.id.activity_add_contract_avator:
                Intent intent = new Intent(AddContractActivity.this, MultiImageSelectorActivity.class);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                if (mSelectPath != null && mSelectPath.size() > 0) {
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);
                }
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                mSelectPath = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                Bitmap bitmap=BitmapFactory.decodeFile(mSelectPath.get(0));
                activityAddContractAvator.setImageBitmap(bitmap);
            }
        }
    }

    private void setdata() {
        Contacts contract = new Contacts();
        contract.setName(activityAddContractName.getText().toString());
        contract.setCompany(activityAddContractConpaney.getText().toString());
        contract.setJob(activityAddContractJob.getText().toString());
        contract.setDepartment(activityAddContractBumen.getText().toString());
        contract.setPhone(activityAddContractPhone.getText().toString());
        contract.setAddress(activityAddContractAddress.getText().toString());
        contract.setAvatot(mSelectPath==null?"1":mSelectPath.get(0));
        Intent intent = new Intent();
        intent.putExtra("data", contract);
        setResult(2, intent);
        finish();
    }
}
