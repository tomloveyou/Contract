package personcom.yl.contract;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVFile;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import personcom.yl.contract.activity.AddContractActivity;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.adapter.FragmentAdapter;
import personcom.yl.contract.fragment.TestFragment;
import personcom.yl.contract.utils.StatusBarUtils;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, TabLayout.OnTabSelectedListener, View.OnClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tabs)
    TabLayout tabs;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private FragmentAdapter adapter;
    private TestFragment contractFragment;
    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("通讯录");
        StatusBarUtils.setWindowStatusBarColor(this, R.color.colorPrimary);
        viewpager.addOnPageChangeListener(this);
        tabs.addOnTabSelectedListener(this);
        fab.setOnClickListener(this);
        adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        viewpager.setAdapter(adapter);
        prepae();

    }

    public void changeicon() {
        fab.setImageResource(R.mipmap.ic_delete_dowun_icon);
    }
    public void switchicon() {
        fab.setImageResource(R.mipmap.ic_add_contract_icon);
    }
    private void prepae() {
        contractFragment = new TestFragment();
        fragments.add(contractFragment);
        fragments.add(new TestFragment());
        fragments.add(new TestFragment());
        adapter.notifyDataSetChanged();


    }

    private byte[] image2Bytes(String imgSrc) throws Exception {
        FileInputStream fin = new FileInputStream(new File(imgSrc));
        //可能溢出,简单起见就不考虑太多,如果太大就要另外想办法，比如一次传入固定长度byte[]
        byte[] bytes = new byte[fin.available()];
        //将文件内容写入字节数组，提供测试的case
        fin.read(bytes);

        fin.close();
        return bytes;
    }

    private byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            Contacts contract = (Contacts) data.getSerializableExtra("data");
            // 测试 SDK 是否正常工作的代码
            AVObject testObject = new AVObject("ContractUser");
            try {
                testObject.put("name", contract.getName());
                testObject.put("pinyin", contract.getPinyin());
                testObject.put("header", contract.getHeader());
                testObject.put("top", contract.isTop());
                if (contract.getAvatot() != null && !contract.getAvatot().equals("1")) {
                    testObject.put("avatot", new AVFile(contract.getAvatot(), image2Bytes(contract.getAvatot())));
                } else {
                    testObject.put("avatot", new AVFile(contract.getAvatot(), bitmap2Bytes(BitmapFactory.decodeResource(getResources(), R.drawable.img_default_head))));
                }

                testObject.put("phone", contract.getPhone());
                testObject.put("job", contract.getJob());
                testObject.put("address", contract.getAddress());
                testObject.put("company", contract.getCompany());
                testObject.put("department", contract.getDepartment());
            } catch (Exception e) {
                e.printStackTrace();
            }
            testObject.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        contractFragment.refreshui();
                    }
                }
            });
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        tabs.getTabAt(position).select();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivityForResult(new Intent(MainActivity.this, AddContractActivity.class), 1);
                break;
        }
    }
}
