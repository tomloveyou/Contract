package personcom.yl.contract.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import personcom.yl.contract.R;
import personcom.yl.contract.mvp.model.Contract;
import personcom.yl.contract.widget.EaseImageView;

/**
 * Created by changlianjiuzhou on 17/2/15.
 */

public class ContractAdapter extends BaseAdapter implements SectionIndexer {
    private List<Contract> Contractlist = null;
    private Context context;
    private SparseIntArray positionOfSection;
    private SparseIntArray sectionOfPosition;
    List<String> list;

    public ContractAdapter(List<Contract> contractlist, Context context) {
        Contractlist = contractlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Contractlist.size();
    }

    @Override
    public Object getItem(int position) {
        return Contractlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ContractViewHolder contractViewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contract_layout, parent, false);
            contractViewHolder = new ContractViewHolder(convertView);
            convertView.setTag(contractViewHolder);
        } else {
            contractViewHolder = (ContractViewHolder) convertView.getTag();
        }
       if (Contractlist.get(position).getAvatot().equals("1")){
           contractViewHolder.list_item_contract_avator.setImageResource(R.drawable.avaor1);
       }else  if (Contractlist.get(position).getAvatot().equals("2")){
           contractViewHolder.list_item_contract_avator.setImageResource(R.drawable.avaor5);
        }else  if (Contractlist.get(position).getAvatot().equals("3")){
           contractViewHolder.list_item_contract_avator.setImageResource(R.drawable.avaor3);
       }else  if (Contractlist.get(position).getAvatot().equals("4")){
           contractViewHolder.list_item_contract_avator.setImageResource(R.drawable.avaor4);
       }
        contractViewHolder.listItemContractName.setText(Contractlist.get(position).getName());
        String header = Contractlist.get(position).getInitialLetter();
        if (position == 0 || header != null && !header.equals(Contractlist.get(position-1).getInitialLetter())) {
            if (TextUtils.isEmpty(header)) {
                contractViewHolder.listItemHeadTask.setVisibility(View.GONE);
            } else {
                contractViewHolder.listItemHeadTask.setVisibility(View.VISIBLE);
                contractViewHolder.listItemHeadTask.setText(header);
            }
        } else {
            contractViewHolder.listItemHeadTask.setVisibility(View.GONE);
        }
        contractViewHolder.listItemCallImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context).setTitle("拨打点话").setMessage("确定拨打"+Contractlist.get(position).getPhone())
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        context.startActivity(new Intent().setAction(Intent.ACTION_CALL).setData(Uri.parse("tel:" + Contractlist.get(position).getPhone())));
                        dialog.dismiss();
                    }
                }).show();
            }
        });
        return convertView;
    }

    @Override
    public Object[] getSections() {
        positionOfSection = new SparseIntArray();
        sectionOfPosition = new SparseIntArray();
        int count = getCount();
        list = new ArrayList<String>();
        list.add(context.getString(R.string.search_new));
        positionOfSection.put(0, 0);
        sectionOfPosition.put(0, 0);
        for (int i = 1; i < count; i++) {
            String letter = Contractlist.get(i).getInitialLetter();
            int section = list.size() - 1;
            if (list.get(section) != null && !list.get(section).equals(letter)) {
                list.add(letter);
                section++;
                positionOfSection.put(section, i);
            }
            sectionOfPosition.put(i, section);
        }
        return list.toArray(new String[list.size()]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return positionOfSection.get(sectionIndex);
    }

    @Override
    public int getSectionForPosition(int position) {
        return sectionOfPosition.get(position);
    }

    static class ContractViewHolder {
        @Bind(R.id.list_item_contract_name)
        TextView listItemContractName;
        @Bind(R.id.list_item_call_img)
        ImageView listItemCallImg;
        @Bind(R.id.list_item_head_task)
        TextView listItemHeadTask;
        @Bind(R.id.list_item_contract_avator)
        EaseImageView list_item_contract_avator;
        ContractViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
