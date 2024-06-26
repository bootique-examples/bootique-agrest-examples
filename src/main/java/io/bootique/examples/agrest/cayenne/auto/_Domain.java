package io.bootique.examples.agrest.cayenne.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.ListProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.StringProperty;

import io.bootique.examples.agrest.cayenne.Article;

/**
 * Class _Domain was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Domain extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final String ID_PK_COLUMN = "id";

    public static final StringProperty<String> NAME = PropertyFactory.createString("name", String.class);
    public static final StringProperty<String> VHOST = PropertyFactory.createString("vhost", String.class);
    public static final ListProperty<Article> ARTICLES = PropertyFactory.createList("articles", Article.class);

    protected String name;
    protected String vhost;

    protected Object articles;

    public void setName(String name) {
        beforePropertyWrite("name", this.name, name);
        this.name = name;
    }

    public String getName() {
        beforePropertyRead("name");
        return this.name;
    }

    public void setVhost(String vhost) {
        beforePropertyWrite("vhost", this.vhost, vhost);
        this.vhost = vhost;
    }

    public String getVhost() {
        beforePropertyRead("vhost");
        return this.vhost;
    }

    public void addToArticles(Article obj) {
        addToManyTarget("articles", obj, true);
    }

    public void removeFromArticles(Article obj) {
        removeToManyTarget("articles", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Article> getArticles() {
        return (List<Article>)readProperty("articles");
    }

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "name":
                return this.name;
            case "vhost":
                return this.vhost;
            case "articles":
                return this.articles;
            default:
                return super.readPropertyDirectly(propName);
        }
    }

    @Override
    public void writePropertyDirectly(String propName, Object val) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch (propName) {
            case "name":
                this.name = (String)val;
                break;
            case "vhost":
                this.vhost = (String)val;
                break;
            case "articles":
                this.articles = val;
                break;
            default:
                super.writePropertyDirectly(propName, val);
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        writeSerialized(out);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readSerialized(in);
    }

    @Override
    protected void writeState(ObjectOutputStream out) throws IOException {
        super.writeState(out);
        out.writeObject(this.name);
        out.writeObject(this.vhost);
        out.writeObject(this.articles);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.name = (String)in.readObject();
        this.vhost = (String)in.readObject();
        this.articles = in.readObject();
    }

}
