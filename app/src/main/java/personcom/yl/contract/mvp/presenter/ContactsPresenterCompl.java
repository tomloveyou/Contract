package personcom.yl.contract.mvp.presenter;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.mvp.view.ContractList;
import personcom.yl.contract.utils.EaseCommonUtils;

/**
 * Created by changlianjiuzhou on 17/2/17.
 */

public class ContactsPresenterCompl implements ContractList.ContractPresenter {
    private ContractList.ContractView contractView;
    private List<Contacts> contactsList = new ArrayList<>();

    public ContactsPresenterCompl(ContractList.ContractView contractView) {
        this.contractView = contractView;
    }

    @Override
    public void refresh() {
        contactsList.clear();
        AVQuery<AVObject> avQuery = new AVQuery<>(contractView.getTableName());
        avQuery.orderByDescending("createdAt");
        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e==null){
                    for (int i = 0; i < list.size(); i++) {
                        Contacts contacts = new Contacts();
                        try {
                            contacts.setAvatot(list.get(i).getJSONObject("avatot").getString("url"));
                            contacts.setName(list.get(i).getString("name"));
                            contacts.setPhone(list.get(i).getString("phone"));
                            contacts.setAddress(list.get(i).getString("address"));
                            contacts.setTop(list.get(i).getBoolean("top"));
                            contacts.setJob(list.get(i).getString("job"));
                            contacts.setCompany(list.get(i).getString("company"));
                            contacts.setPinyin(list.get(i).getString("pinyin"));
                            EaseCommonUtils.setUserInitialLetter(contacts);
                            contactsList.add(contacts);
                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }

                    }
                    contractView.getContactsList(contactsList);
                }else {
                    contractView.error();
                }

            }
        });
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }
}
