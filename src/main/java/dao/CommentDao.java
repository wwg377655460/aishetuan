package dao;

import entry.Comment;
import entry.Reply;
import entry.User;
import org.nutz.dao.Cnd;
import org.nutz.trans.Atom;
import org.nutz.trans.Trans;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/31.
 */
public class CommentDao extends DbFactory<Comment> {

    @Override
    public Comment get(int id) {
        return getDao().fetch(Comment.class, id);
    }

    public List<Comment> getCommentsByProjectId(int project_id) {
        return getDao().query(Comment.class, Cnd.wrap("comment_to_project_id = " + project_id));
    }

    public void insertComment(Comment comment) {
        getDao().insert(comment);
    }

    //更新comment和reply
    public void addReplyByComment(Comment comment, Reply reply) {

        Trans.exec(new Atom() {
            @Override
            public void run() {
                getDao().update(comment);
                getDao().insert(reply);
            }
        });
    }

    //更新comment和user
    public void updateCommentAndUser(Comment comment, User user) {

        Trans.exec(new Atom() {
            @Override
            public void run() {
                getDao().update(comment);
                getDao().update(user);
            }
        });
    }

}
