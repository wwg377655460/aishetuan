package dao;

import entry.Article;
import util.DbFactory;

/**
 * Created by wsdevotion on 16/1/30.
 */
public class ArticleDao extends DbFactory<Article> {

    @Override
    public Article get(int id) {
        return getDao().fetch(Article.class, id);
    }
}
