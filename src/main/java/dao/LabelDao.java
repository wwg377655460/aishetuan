package dao;

import entry.Label;
import org.nutz.dao.Cnd;
import org.nutz.dao.Condition;
import org.nutz.dao.pager.Pager;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class LabelDao extends DbFactory<Label> {

    @Override
    public Label get(int id) {
        return getDao().fetch(Label.class, id);
    }

    //获取按热度排名的标签
    public List<Label> getLabelsByFollowNum(int start_num, int num){

        return getDao().query(Label.class, Cnd.wrap("ORDER BY lable_follow_number DESC LIMIT " + start_num + "," + num));

    }
}
