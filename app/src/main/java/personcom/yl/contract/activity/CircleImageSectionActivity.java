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
package personcom.yl.contract.activity;


import java.util.List;

import personcom.yl.contract.activity.adapter.CircleImageSectionAdapter;
import personcom.yl.contract.activity.base.SectionActivity;
import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.activity.constant.Constant;

/**
 * Description：CircleImageSectionActivity
 * Created by：CaMnter
 * Time：2016-04-12 00:01
 */
public class CircleImageSectionActivity extends SectionActivity {

    @Override public void setActivityTitle() {
        this.setTitle("CircleImageSectionActivity");
    }


    @Override public void initAdapter() {
        this.adapter = new CircleImageSectionAdapter();
    }


    @Override public List<Contacts> getData() {
        return Constant.circleImageSectionList;
    }
}
