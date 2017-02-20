package personcom.yl.contract.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import personcom.yl.contract.R;
import personcom.yl.contract.mvp.model.Contract;
import personcom.yl.contract.widget.EaseImageView;
import personcom.yl.contract.widget.recycleview.adapter.EasyRecyclerViewAdapter;
import personcom.yl.contract.widget.recycleview.holder.EasyRecyclerViewHolder;


/**
 * Created by changlianjiuzhou on 17/2/17.
 */

public class TestAdapter extends EasyRecyclerViewAdapter {

    private static final int MULTIPLE_ITEM_TYPE = 0;
    private static final int SINGLE_ITEM_TYPE = 1;


    /**
     * Please return RecyclerView loading layout Id array
     * 请返回RecyclerView加载的布局Id数组
     *
     * @return 布局Id数组
     */
    @Override
    public int[] getItemLayouts() {
        return new int[]{
                R.layout.item_contract_layout, R.layout.item_contract_layout};
    }

    /**
     * butt joint the onBindViewHolder and
     * If you want to write logic in onBindViewHolder, you can write here
     * 对接了onBindViewHolder
     * onBindViewHolder里的逻辑写在这
     *
     * @param viewHolder viewHolder
     * @param position position
     */
    @Override
    public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        int itemType = this.getRecycleViewItemType(position);
        Contract data = this.getItem(position);
        Contract redata=this.getItem(position-1);
        switch (itemType) {
            case MULTIPLE_ITEM_TYPE: {
                TextView listItemContractName=viewHolder.findViewById(R.id.list_item_contract_name);
                ImageView listItemCallImg=viewHolder.findViewById(R.id.list_item_call_img);
                TextView list_item_head_task=viewHolder.findViewById(R.id.list_item_head_task);
                EaseImageView list_item_contract_avator=viewHolder.findViewById(R.id.list_item_contract_avator);
                if (data.getAvatot().equals("1")){
                    list_item_contract_avator.setImageResource(R.drawable.avaor1);
                }else  if (data.getAvatot().equals("2")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor5);
                }else  if (data.getAvatot().equals("3")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor3);
                }else  if (data.getAvatot().equals("4")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor4);
                }
                listItemContractName.setText(data.getName());
                String header = data.getInitialLetter();
                if (position == 0 || header != null && !header.equals(redata.getInitialLetter())) {
                    if (TextUtils.isEmpty(header)) {
                        list_item_head_task.setVisibility(View.GONE);
                    } else {
                        list_item_head_task.setVisibility(View.VISIBLE);
                        list_item_head_task.setText(header);
                    }
                } else {
                    list_item_head_task.setVisibility(View.GONE);
                }
                break;
            }
            case SINGLE_ITEM_TYPE: {
                TextView listItemContractName=viewHolder.findViewById(R.id.list_item_contract_name);
                ImageView listItemCallImg=viewHolder.findViewById(R.id.list_item_call_img);
                TextView list_item_head_task=viewHolder.findViewById(R.id.list_item_head_task);
                EaseImageView list_item_contract_avator=viewHolder.findViewById(R.id.list_item_contract_avator);
                if (data.getAvatot().equals("1")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor1);
                }else  if (data.getAvatot().equals("2")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor5);
                }else  if (data.getAvatot().equals("3")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor3);
                }else  if (data.getAvatot().equals("4")){
                   list_item_contract_avator.setImageResource(R.drawable.avaor4);
                }
                listItemContractName.setText(data.getName());
                String header = data.getInitialLetter();
                if (position == 0 || header != null && !header.equals(redata.getInitialLetter())) {
                    if (TextUtils.isEmpty(header)) {
                        list_item_head_task.setVisibility(View.GONE);
                    } else {
                        list_item_head_task.setVisibility(View.VISIBLE);
                        list_item_head_task.setText(header);
                    }
                } else {
                    list_item_head_task.setVisibility(View.GONE);
                }
                break;
            }
        }
    }

    /**
     * Please write judgment logic when more layout
     * and not write when single layout
     * 如果是多布局的话，请写判断逻辑
     * 单布局可以不写
     *
     * @param position Item position
     * @return 布局Id数组中的index
     */
    @Override
    public int getRecycleViewItemType(int position) {
        if (position % 2 == 0) {
            return SINGLE_ITEM_TYPE;
        } else {
            return MULTIPLE_ITEM_TYPE;
        }
    }

}