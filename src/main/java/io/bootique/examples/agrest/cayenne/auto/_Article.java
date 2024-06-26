package io.bootique.examples.agrest.cayenne.auto;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.List;

import org.apache.cayenne.BaseDataObject;
import org.apache.cayenne.exp.property.DateProperty;
import org.apache.cayenne.exp.property.EntityProperty;
import org.apache.cayenne.exp.property.ListProperty;
import org.apache.cayenne.exp.property.PropertyFactory;
import org.apache.cayenne.exp.property.StringProperty;

import io.bootique.examples.agrest.cayenne.Domain;
import io.bootique.examples.agrest.cayenne.Tag;

/**
 * Class _Article was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Article extends BaseDataObject {

    private static final long serialVersionUID = 1L;

    public static final String ID_PK_COLUMN = "id";

    public static final StringProperty<String> BODY = PropertyFactory.createString("body", String.class);
    public static final DateProperty<Date> PUBLISHED_ON = PropertyFactory.createDate("publishedOn", Date.class);
    public static final StringProperty<String> TITLE = PropertyFactory.createString("title", String.class);
    public static final EntityProperty<Domain> DOMAIN = PropertyFactory.createEntity("domain", Domain.class);
    public static final ListProperty<Tag> TAGS = PropertyFactory.createList("tags", Tag.class);

    protected String body;
    protected Date publishedOn;
    protected String title;

    protected Object domain;
    protected Object tags;

    public void setBody(String body) {
        beforePropertyWrite("body", this.body, body);
        this.body = body;
    }

    public String getBody() {
        beforePropertyRead("body");
        return this.body;
    }

    public void setPublishedOn(Date publishedOn) {
        beforePropertyWrite("publishedOn", this.publishedOn, publishedOn);
        this.publishedOn = publishedOn;
    }

    public Date getPublishedOn() {
        beforePropertyRead("publishedOn");
        return this.publishedOn;
    }

    public void setTitle(String title) {
        beforePropertyWrite("title", this.title, title);
        this.title = title;
    }

    public String getTitle() {
        beforePropertyRead("title");
        return this.title;
    }

    public void setDomain(Domain domain) {
        setToOneTarget("domain", domain, true);
    }

    public Domain getDomain() {
        return (Domain)readProperty("domain");
    }

    public void addToTags(Tag obj) {
        addToManyTarget("tags", obj, true);
    }

    public void removeFromTags(Tag obj) {
        removeToManyTarget("tags", obj, true);
    }

    @SuppressWarnings("unchecked")
    public List<Tag> getTags() {
        return (List<Tag>)readProperty("tags");
    }

    protected abstract void onPostAdd();

    @Override
    public Object readPropertyDirectly(String propName) {
        if(propName == null) {
            throw new IllegalArgumentException();
        }

        switch(propName) {
            case "body":
                return this.body;
            case "publishedOn":
                return this.publishedOn;
            case "title":
                return this.title;
            case "domain":
                return this.domain;
            case "tags":
                return this.tags;
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
            case "body":
                this.body = (String)val;
                break;
            case "publishedOn":
                this.publishedOn = (Date)val;
                break;
            case "title":
                this.title = (String)val;
                break;
            case "domain":
                this.domain = val;
                break;
            case "tags":
                this.tags = val;
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
        out.writeObject(this.body);
        out.writeObject(this.publishedOn);
        out.writeObject(this.title);
        out.writeObject(this.domain);
        out.writeObject(this.tags);
    }

    @Override
    protected void readState(ObjectInputStream in) throws IOException, ClassNotFoundException {
        super.readState(in);
        this.body = (String)in.readObject();
        this.publishedOn = (Date)in.readObject();
        this.title = (String)in.readObject();
        this.domain = in.readObject();
        this.tags = in.readObject();
    }

}
