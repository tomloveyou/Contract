package personcom.yl.contract.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import personcom.yl.contract.BaseFragment;
import personcom.yl.contract.R;
import personcom.yl.contract.activity.CircleImageSectionActivity;
import personcom.yl.contract.activity.RoundImageSectionActivity;
import personcom.yl.contract.activity.adapter.MainAdapter;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.mvp.model.Contract;
import personcom.yl.contract.widget.recycleview.holder.EasyRecyclerViewHolder;
import personcom.yl.contract.widget.recycleview.widget.EasyRecyclerView;
import personcom.yl.contract.widget.recycleview.widget.decorator.EasyDividerItemDecoration;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContractFragment extends BaseFragment implements EasyRecyclerViewHolder.OnItemClickListener {

    @Bind(R.id.main_rv)
    EasyRecyclerView mainRv;
    @Bind(R.id.activity_main)
    RelativeLayout activityMain;
    private MainAdapter mainAdapter;
    private ArrayList<Class> classes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        ButterKnife.bind(this, view);
        this.initViews();
        this.initData();
        this.initListeners();
        return view;
    }


    private void initViews() {
        EasyDividerItemDecoration decoration = new EasyDividerItemDecoration(getActivity(),
                EasyDividerItemDecoration.VERTICAL_LIST);
        decoration.bottomDivider = true;
        this.mainRv.addItemDecoration(decoration);
    }


    private void initData() {
        this.classes = new ArrayList<>();

        this.classes.add(CircleImageSectionActivity.class);
        this.classes.add(RoundImageSectionActivity.class);

        this.mainAdapter = new MainAdapter();
        this.mainAdapter.setList(this.classes);
        this.mainRv.setAdapter(this.mainAdapter);
    }


    private void initListeners() {
        this.mainAdapter.setOnItemClickListener(new EasyRecyclerViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {

                startActivity(new Intent(getActivity(), classes.get(i)));
            }
        });
    }

    private List<Contacts> init() {
        List<Contacts>contactsList=new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Contacts contract = new Contacts();
            if (i % 2 == 0) {
                contract.setAvatot("1");
                contract.setName("张三" + i);
                contract.setPinyin(("张三" + i).toLowerCase());
                contract.setTop(false);
                contract.setResId(R.drawable.avaor1);
                contract.setPhone("1568088754" + i % 3);

            } else if (i % 3 == 0) {
                contract.setAvatot("2");
                contract.setName("李四" + i);
                contract.setPinyin(("李四" + i).toLowerCase());
                contract.setTop(false);
                contract.setResId(R.drawable.avaor1);
                contract.setPhone("1568088754" + i % 5);
            } else if (i % 5 == 0) {
                contract.setAvatot("3");
                contract.setName("王五" + i);
                contract.setPinyin(("王五" + i).toLowerCase());
                contract.setTop(false);
                contract.setResId(R.drawable.avaor1);
                contract.setPhone("1568088754" + i % 7);
            } else {
                contract.setAvatot("4");
                contract.setName("诸葛无" + i);
                contract.setPinyin(("诸葛无" + i).toLowerCase());
                contract.setTop(false);
                contract.setResId(R.drawable.avaor1);
                contract.setPhone("1568088754" + i % 2);
            }
            contactsList.add(contract);


        }
        return contactsList;


    }

    public void addContract(Contract contract) {
        //EaseCommonUtils.setUserInitialLetter(contract);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View convertView, int position) {

    }
}
