package dao;

import config.ConstMes;
import entry.Project;
import org.nutz.dao.Cnd;
import org.nutz.dao.QueryResult;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Criteria;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class ProjectDao extends DbFactory<Project> {

    @Override
    public Project get(int id) {
        return getDao().fetch(Project.class, id);
    }

    //分页获取信息
    public List<Project> getProjectByPage(int page) {

        Pager pager = getDao().createPager(page, ConstMes.PAGE);
        //按喜欢的人数排序
        Criteria cri = Cnd.cri();
        cri.getOrderBy().desc("project_like_number");
        List<Project> list = getDao().query(Project.class, cri, pager);
        pager.setRecordCount(getDao().count(Project.class));
        return new QueryResult(list, pager).getList(Project.class);
    }

    //根据标签id查询项目
    public List<Project> getProjectByLabelId(int label_id) {
        List<Project> crowd = getDao().query(Project.class, Cnd.wrap("project_lable_id = " + label_id), null);
        return crowd;
    }
}
