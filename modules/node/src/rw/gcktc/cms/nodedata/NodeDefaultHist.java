package rw.gcktc.cms.nodedata;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 02.12.13
 * Time: 10:48
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "nodehistory")
//@PrimaryKeyJoinColumn(name = "id_node", referencedColumnName = "id_node")
@AttributeOverrides({
        @AttributeOverride(name="id_usercreator", column=@Column(name="id_usercreator")),
        @AttributeOverride(name="id_usercorrector", column=@Column(name="id_usercorrector")),
        @AttributeOverride(name="datecreate", column=@Column(name="datecreate")),
        @AttributeOverride(name="datecorrect", column=@Column(name="datecorrect")),
        @AttributeOverride(name="id_hystPremParent", column=@Column(name="id_hystPremParent")),
        @AttributeOverride(name="id_hystParent", column=@Column(name="id_hystParent")),
        @AttributeOverride(name="hystory", column=@Column(name="hystory"))
})
public class NodeDefaultHist extends HistoryNode implements Serializable {

    public NodeDefaultHist(){}

    public NodeDefaultHist(Node nodechanger) {
        super(nodechanger);
    }

    public NodeDefaultHist(Node nodechanger,HistoryNode historyNode) {
        super(nodechanger,historyNode);
    }
}
