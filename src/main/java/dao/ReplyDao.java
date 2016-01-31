package dao;

import entry.Comment;
import entry.Project;
import entry.Reply;
import org.nutz.dao.Cnd;
import util.DbFactory;

import java.util.List;

/**
 * Created by wsdevotion on 16/1/31.
 */
public class ReplyDao extends DbFactory<Reply> {


    @Override
    public Reply get(int id) {
        return getDao().fetch(Reply.class, id);
    }

    public List<Reply> getReplysByCommentId(int comment_id) {
        return getDao().query(Reply.class, Cnd.wrap("reply_comment_id = " + comment_id));
    }

    public void insert(Reply reply) {
        getDao().insert(reply);
    }
}
