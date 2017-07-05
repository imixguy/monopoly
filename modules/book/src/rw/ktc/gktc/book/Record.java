package rw.ktc.gktc.book;

import rw.gcktc.cms.nodedata.HistoryNode;

import javax.persistence.*;


/**
 * Created by miha on 20.11.2014.
 * Класс для объекта запись
 */
@Entity
@Table(name = "record")
//@PrimaryKeyJoinColumn(name = "id_node", referencedColumnName = "id")

@AttributeOverrides({
        @AttributeOverride(name="id_usercreator", column=@Column(name="id_usercreator")),
        @AttributeOverride(name="id_usercorrector", column=@Column(name="id_usercorrector")),
        @AttributeOverride(name="datecreate", column=@Column(name="datecreate")),
        @AttributeOverride(name="datecorrect", column=@Column(name="datecorrect")),
        @AttributeOverride(name="id_hystPremParent", column=@Column(name="id_hystPremParent")),
        @AttributeOverride(name="id_hystParent", column=@Column(name="id_hystParent")),
        @AttributeOverride(name="hystory", column=@Column(name="hystory"))
})
public class Record extends HistoryNode {
    @Column(name = "title",length = 100, nullable = false)
    private String title;
    @Column(name = "content",nullable = false,length=2000)
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
