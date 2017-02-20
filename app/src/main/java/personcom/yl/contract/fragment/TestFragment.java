package personcom.yl.contract.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.camnter.easyrecyclerviewsidebar.EasyFloatingImageView;
import com.camnter.easyrecyclerviewsidebar.EasyRecyclerViewSidebar;
import com.camnter.easyrecyclerviewsidebar.sections.EasyImageSection;
import com.camnter.easyrecyclerviewsidebar.sections.EasySection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import personcom.yl.contract.BaseFragment;
import personcom.yl.contract.ContractDetalActivity;
import personcom.yl.contract.MainActivity;
import personcom.yl.contract.R;
import personcom.yl.contract.activity.adapter.CircleImageSectionAdapter;
import personcom.yl.contract.activity.base.SectionAdapter;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.activity.utils.GlideUtils;
import personcom.yl.contract.mvp.presenter.ContactsPresenterCompl;
import personcom.yl.contract.mvp.view.ContractList;
import personcom.yl.contract.widget.recycleview.holder.EasyRecyclerViewHolder;
import personcom.yl.contract.widget.recycleview.widget.EasyRecyclerView;

/**
 * Created by changlianjiuzhou on 17/2/17.
 */

public class TestFragment extends BaseFragment implements EasyRecyclerViewSidebar.OnTouchSectionListener, EasyRecyclerViewHolder.OnItemClickListener, ContractList.ContractView,EasyRecyclerViewHolder.OnItemLongClickListener,EasyRecyclerViewHolder.OnItemTouchListener{
    @Bind(R.id.section_rv)
    EasyRecyclerView imageSectionRv;
    @Bind(R.id.section_sidebar)
    EasyRecyclerViewSidebar imageSidebar;
    @Bind(R.id.section_floating_iv)
    EasyFloatingImageView imageFloatingIv;
    @Bind(R.id.section_floating_tv)
    TextView imageFloatingTv;
    private List<Contacts> contactsList = new ArrayList<>();
    public SectionAdapter adapter;
    @Bind(R.id.section_floating_rl)
    RelativeLayout sectionFloatingRl;
    private List<Contacts> result = new ArrayList<>();
    private ContractList.ContractPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactsPresenterCompl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_section, container, false);
        ButterKnife.bind(this, view);
        this.initViews();
        this.initData();
        return view;
    }


    private void initViews() {
        this.initAdapter();
        if (this.imageSectionRv != null) {
            this.imageSectionRv.setAdapter(this.adapter);
        }

        this.imageSidebar.setFloatView(sectionFloatingRl);
        this.imageSidebar.setOnTouchSectionListener(this);
        adapter.setOnItemLongClickListener(this);
        adapter.setOnItemClickListener(this);
        adapter.setOnItemTouchListener(this);
    }


    private void initData() {
        presenter.refresh();
        this.imageSidebar.setSections(this.adapter.getSections());
    }


    public void refreshui() {
        //EaseCommonUtils.setUserInitialLetter(contract);
        presenter.refresh();
        this.imageSidebar.setSections(this.adapter.getSections());

    }

    public void initAdapter() {
        this.adapter = new CircleImageSectionAdapter();
    }



    /**
     * On touch image section
     *
     * @param sectionIndex sectionIndex
     * @param imageSection imageSection
     */
    @Override
    public void onTouchImageSection(int sectionIndex, EasyImageSection imageSection) {
        this.imageFloatingTv.setVisibility(View.INVISIBLE);
        this.imageFloatingIv.setVisibility(View.VISIBLE);
        switch (imageSection.imageType) {
            case EasyImageSection.CIRCLE:
                this.imageFloatingIv.setImageType(EasyFloatingImageView.CIRCLE);
                break;
            case EasyImageSection.ROUND:
                this.imageFloatingIv.setImageType(EasyFloatingImageView.ROUND);
                break;
        }
        GlideUtils.displayNative(this.imageFloatingIv, imageSection.resId);
        this.scrollToPosition(this.adapter.getPositionForSection(sectionIndex));
    }


    /**
     * On touch letter section
     *
     * @param sectionIndex  sectionIndex
     * @param letterSection letterSection
     */
    @Override
    public void onTouchLetterSection(int sectionIndex, EasySection letterSection) {
        this.imageFloatingTv.setVisibility(View.VISIBLE);
        this.imageFloatingIv.setVisibility(View.INVISIBLE);
        this.imageFloatingTv.setText(letterSection.letter);
        this.scrollToPosition(this.adapter.getPositionForSection(sectionIndex));
    }


    private void scrollToPosition(int position) {
        this.imageSectionRv.getLinearLayoutManager().scrollToPositionWithOffset(position, 0);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onItemClick(View convertView, int position) {
        Log.v("234","点击了");
        startActivity(new Intent(getActivity(), ContractDetalActivity.class).putExtra("data", result.get(position)));
    }

    @Override
    public String getTableName() {
        return "ContractUser";
    }

    @Override
    public void getContactsList(List<Contacts> contactses) {
        result.clear();
        result.addAll(contactses);
        adapter.setList(result);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void error() {

    }


    @Override
    public boolean onItemLongClick(View convertView, int position) {
        Log.v("234","长按了");
        ((MainActivity)getActivity()).changeicon();
        return true;
    }





    @Override
    public boolean onItemTouchClick(View convertView, int position, MotionEvent event) {
        Log.v("234","离开");
        if (event.getAction()==MotionEvent.ACTION_UP){
            ((MainActivity)getActivity()).switchicon();
            Log.v("234","离开");
        }
        return true;
    }
}
