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
package personcom.yl.contract.activity.constant;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import personcom.yl.contract.R;
import personcom.yl.contract.activity.bean.Contacts;

/**
 * Description：Constant
 * Created by：CaMnter
 * Time：2016-04-10 21:28
 */
public class Constant {

    public static final Map<String, Integer> letter2ResId = new HashMap<>();
    public static final Map<String, String> letter2Name = new HashMap<>();
    public static final Map<String, String> letter2Pinyin = new HashMap<>();
    public static final String[] letterArray = { "C", "D", "F", "H", "K", "P", "Q", "R", "U", "X" };

    public static final List<Contacts> circleImageSectionList = new ArrayList<>();
    public static final List<Contacts> roundImageSectionList = new ArrayList<>();
    public static final List<Contacts> letterSectionList = new ArrayList<>();


    static {
        letter2ResId.put("C", R.drawable.avaor1);
        letter2ResId.put("D", R.drawable.avaor2);
        letter2ResId.put("F", R.drawable.avaor3);
        letter2ResId.put("H", R.drawable.avaor4);
        letter2ResId.put("K", R.drawable.avaor5);
        letter2ResId.put("P", R.drawable.avaor2);
        letter2ResId.put("Q", R.drawable.avaor4);
        letter2ResId.put("R", R.drawable.avaor1);
        letter2ResId.put("U", R.drawable.avaor1);
        letter2ResId.put("X", R.drawable.avaor3);

        letter2Name.put("C", "CaMnter");
        letter2Name.put("D", "drakeet");
        letter2Name.put("F", "Fython");
        letter2Name.put("H", "Harry Chen");
        letter2Name.put("K", "Kaede Akatsuki");
        letter2Name.put("P", "Peter Cai");
        letter2Name.put("Q", "Qixingchen");
        letter2Name.put("R", "Randy");
        letter2Name.put("U", "undownding");
        letter2Name.put("X", "xingrz");

        letter2Pinyin.put("C", "camnter");
        letter2Pinyin.put("D", "drakeet");
        letter2Pinyin.put("F", "fython");
        letter2Pinyin.put("H", "harrychen");
        letter2Pinyin.put("K", "kaedeakatsuki");
        letter2Pinyin.put("P", "petercai");
        letter2Pinyin.put("Q", "qixingchen");
        letter2Pinyin.put("R", "randy");
        letter2Pinyin.put("U", "undownding");
        letter2Pinyin.put("X", "xingrz");


        // Special
        Contacts camnter = new Contacts();
        camnter.name = letter2Name.get("C");
        camnter.pinyin = letter2Pinyin.get("C");
        camnter.resId = letter2ResId.get("C");
        camnter.top = true;
        Contacts drakeet = new Contacts();
        drakeet.name = letter2Name.get("D");
        drakeet.pinyin = letter2Pinyin.get("D");
        drakeet.resId = letter2ResId.get("D");
        drakeet.top = true;

        circleImageSectionList.add(camnter);
        circleImageSectionList.add(drakeet);

        roundImageSectionList.add(camnter);
        roundImageSectionList.add(drakeet);

        for (String letter : letterArray) {
            Contacts contacts = new Contacts();
            contacts.name = letter2Name.get(letter);
            contacts.pinyin = letter2Pinyin.get(letter);
            contacts.resId = letter2ResId.get(letter);
            Contacts contacts1 = new Contacts();
            contacts1.name = letter + "lingyi";
            contacts1.pinyin = letter.toLowerCase() + "lingyi";
            contacts1.resId = 0;
            Contacts contacts2 = new Contacts();
            contacts2.name = letter + "linger";
            contacts2.pinyin = letter.toLowerCase() + "linger";
            contacts2.resId = 0;
            circleImageSectionList.add(contacts);
            circleImageSectionList.add(contacts1);
            circleImageSectionList.add(contacts2);
            roundImageSectionList.add(contacts);
            roundImageSectionList.add(contacts1);
            roundImageSectionList.add(contacts2);
            letterSectionList.add(contacts);
            letterSectionList.add(contacts1);
            letterSectionList.add(contacts2);
        }
    }
}
