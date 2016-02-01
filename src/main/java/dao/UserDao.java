package dao;

import entry.Label;
import entry.Project;
import entry.User;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import util.DbFactory;

/**
 * Created by wsdevotion on 16/1/24.
 */
public class UserDao extends DbFactory<User> {


    @Override
    public User get(int id) {
        return getDao().fetch(User.class, id);
    }

    public User getByUserPhone(String user_phone){
        return getDao().fetch(User.class, user_phone);
    }

    public int insert(User user){
        getDao().insert(user);
        return user.getUser_id();
    }

    //更新user和label
    public void updateUserAndLabel(int user_id, int label_id, String follow_ids, int  follow_num) {
        User user1 = getDao().fetch(User.class, user_id);
        Label label1 = getDao().fetch(Label.class, label_id);

        user1.setUser_liked_project_ids(follow_ids);
        label1.setLable_follow_number(follow_num);

        //设置事务
        Trans.exec(new Atom() {
            @Override
            public void run() {
                getDao().update(user1);
                getDao().update(label1);
            }
        });
    }

    //更新user和project
    public void updateUserAndProject(int user_id, int project_id, String follow_ids, int  follow_num) {
        User user1 = getDao().fetch(User.class, user_id);
        Project project1 = getDao().fetch(Project.class, project_id);

        user1.setUser_liked_project_ids(follow_ids);
        project1.setProject_like_number(follow_num);

        //设置事务
        Trans.exec(new Atom() {
            @Override
            public void run() {
                getDao().update(user1);
                getDao().update(project1);
            }
        });

    }

    //更新user
    public void updateUser(User user) {
        getDao().update(user);
    }
}
