/*
 * Copyright (C) 2016 CaMnter yuanyu.camnter@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package personcom.yl.contract.activity.base;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.camnter.easyrecyclerviewsidebar.helper.EasyRecyclerSectionIndexer;
import com.camnter.easyrecyclerviewsidebar.sections.EasyImageSection;
import com.camnter.easyrecyclerviewsidebar.sections.EasySection;

import java.util.ArrayList;
import java.util.List;

import personcom.yl.contract.R;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.activity.utils.GlideUtils;
import personcom.yl.contract.widget.recycleview.adapter.EasyRecyclerViewAdapter;
import personcom.yl.contract.widget.recycleview.holder.EasyRecyclerViewHolder;

/**
 * Description：SectionAdapter
 * Created by：CaMnter
 * Time：2016-04-10 20:41
 */
public class SectionAdapter extends EasyRecyclerViewAdapter
        implements EasyRecyclerSectionIndexer<EasySection> {

    private SparseIntArray positionOfSection;
    private SparseIntArray sectionOfPosition;
    private List<EasySection> easySections;


    /**
     * Please return RecyclerView loading layout Id array
     * 请返回RecyclerView加载的布局Id数组
     *
     * @return 布局Id数组
     */
    @Override public int[] getItemLayouts() {
        return new int[] { R.layout.item_image_section };
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
    @Override public void onBindRecycleViewHolder(EasyRecyclerViewHolder viewHolder, int position) {
        Contacts contacts = this.getItem(position);
        if (contacts == null) return;
        TextView headerTv = viewHolder.findViewById(R.id.section_header_tv);
        ImageView sectionIv = viewHolder.findViewById(R.id.section_iv);
        TextView nameTv = viewHolder.findViewById(R.id.section_name_tv);

        if (!TextUtils.isEmpty(contacts.name)) {
            nameTv.setText(contacts.name);
        } else {
            nameTv.setText("");
        }
        if (contacts.getAvatot() != null) {
            GlideUtils.displayNetWort(sectionIv,contacts.getAvatot());
        } else {
            GlideUtils.displayNative(sectionIv, R.mipmap.ic_launcher);
        }

        this.setHeaderLogic(contacts, headerTv, viewHolder, position);
    }


    /**
     * Set header logic
     *
     * @param contacts contacts
     * @param headerTv headerTv
     * @param viewHolder viewHolder
     * @param position position
     */
    public void setHeaderLogic(Contacts contacts, TextView headerTv, EasyRecyclerViewHolder viewHolder, int position) {
        // No implement
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
    @Override public int getRecycleViewItemType(int position) {
        return 0;
    }


    public void setHeader(boolean visible, TextView headerTv, String header) {
        if (visible) {
            headerTv.setText(header);
            headerTv.setVisibility(View.VISIBLE);
        } else {
            headerTv.setVisibility(View.GONE);
        }
    }


    @Override public List<EasySection> getSections() {
        this.resetSectionCache();

        int itemCount = getItemCount();
        if (itemCount < 1) return this.easySections;

        String letter;

        for (int i = 0; i < itemCount; i++) {
            Contacts contacts = this.getItem(i);
            letter = contacts.getHeader();
            int section = this.easySections.size() == 0 ? 0 : this.easySections.size() - 1;
            if (contacts.top) {
                if (i != 0) section++;
                this.positionOfSection.put(section, i);
                this.easySections.add(
                        new EasyImageSection(contacts.resId, getEasyImageSection(), i));
            } else {
                // A B C D E F ...
                if (section < this.easySections.size()) {
                    EasySection easySection = this.easySections.get(section);
                    if (easySection instanceof EasyImageSection) {
                        // last section = image section
                        this.easySections.add(new EasySection(letter));
                        section++;
                        this.positionOfSection.put(section, i);
                    } else {
                        // last section = letter section
                        if (!this.easySections.get(section).letter.equals(letter)) {
                            this.easySections.add(new EasySection(letter));
                            section++;
                            this.positionOfSection.put(section, i);
                        }
                    }
                } else if (section == 0) {
                    this.easySections.add(new EasySection(letter));
                    this.positionOfSection.put(section, i);
                }
            }
            this.sectionOfPosition.put(i, section);
        }
        return this.easySections;
    }


    private void resetSectionCache() {
        if (this.easySections == null) this.easySections = new ArrayList<>();
        if (this.easySections.size() > 0) this.easySections.clear();
        if (sectionOfPosition == null) this.sectionOfPosition = new SparseIntArray();
        if (this.sectionOfPosition.size() > 0) this.sectionOfPosition.clear();
        if (this.positionOfSection == null) this.positionOfSection = new SparseIntArray();
        if (this.positionOfSection.size() > 0) this.positionOfSection.clear();
    }


    public @EasyImageSection.ImageType int getEasyImageSection() {
        return EasyImageSection.CIRCLE;
    }


    @Override public int getPositionForSection(int sectionIndex) {
        return positionOfSection.get(sectionIndex);
    }


    @Override public int getSectionForPosition(int position) {
        return sectionOfPosition.get(position);
    }
}
