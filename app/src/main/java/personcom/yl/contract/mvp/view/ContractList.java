package personcom.yl.contract.mvp.view;

import java.util.List;

import personcom.yl.contract.activity.bean.Contacts;
import personcom.yl.contract.mvp.BasePresenter;
import personcom.yl.contract.mvp.BaseView;

/**
 * Created by changlianjiuzhou on 17/2/17.
 */

public interface ContractList  {
    interface ContractView extends BaseView{
        String getTableName();
      void getContactsList(List<Contacts>contactses);
        void error();
    };
    interface ContractPresenter extends BasePresenter{
        void refresh();
    }
}
