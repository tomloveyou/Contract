package personcom.yl.contract;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.activity.utils.GlideUtils;
import personcom.yl.contract.runtimepermissions.PermissionsManager;
import personcom.yl.contract.runtimepermissions.PermissionsResultAction;

public class ContractDetalActivity extends BaseActivity {


    @Bind(R.id.contract_detail_img_background)
    ImageView contractDetailImgBackground;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.appbar)
    AppBarLayout appbar;

    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.contract_detail_tellphone)
    TextView contractDetailTellphone;

    private Contacts contract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract_detal);
        ButterKnife.bind(this);
        contract = (Contacts) getIntent().getSerializableExtra("data");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setTitle(contract.getName());
        contractDetailTellphone.setText(contract.getPhone()==null?"":contract.getPhone());
        GlideUtils.displayNetWort(contractDetailImgBackground,contract.getAvatot());



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }


    @TargetApi(23)
    public void requestPermissions(final int type, String[] requestPermissions) {
        if (!PermissionsManager.getInstance().hasAllPermissions(this, requestPermissions)) {
            PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, requestPermissions, new PermissionsResultAction() {
                @Override
                public void onGranted() {
                    if (type == 1) {
                        startActivity(new Intent().setAction(Intent.ACTION_CALL).setData(Uri.parse("tel:" + contract.getPhone())));
                    } else {
                        Uri smsToUri = Uri.parse("smsto://" + contract.getPhone());
                        Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
                        startActivity(mIntent);
                    }


                }

                @Override
                public void onDenied(String permission) {

                }
            });
        } else {
            if (type == 1) {
                startActivity(new Intent().setAction(Intent.ACTION_CALL).setData(Uri.parse("tel:" + contract.getPhone())));
            } else {
                Uri smsToUri = Uri.parse("smsto://" + contract.getPhone());
                Intent mIntent = new Intent(android.content.Intent.ACTION_SENDTO, smsToUri);
                startActivity(mIntent);
            }

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contract_detal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @OnClick({R.id.contract_detail_sms, R.id.contract_detail_fab,R.id.contract_detail_share})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contract_detail_sms:
                requestPermissions(2, new String[]{Manifest.permission.SEND_SMS});
                break;
            case R.id.contract_detail_fab:
                requestPermissions(1, new String[]{Manifest.permission.CALL_PHONE});
                break;
            case R.id.contract_detail_share:
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
                intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, getTitle()));
                break;
        }
    }
}
