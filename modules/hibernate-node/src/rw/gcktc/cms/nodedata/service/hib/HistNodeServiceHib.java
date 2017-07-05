package rw.gcktc.cms.nodedata.service.hib;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rw.gcktc.cms.nodedata.HistoryNode;
import rw.gcktc.cms.nodedata.Node;
import rw.gcktc.cms.nodedata.service.HistoryNodeService;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 10.12.13
 * Time: 11:53
 * To change this template use File | Settings | File Templates.
 */
@Service("nodedefaulthistservice")
@Transactional(readOnly = true)
public class HistNodeServiceHib extends NodeServiceHib implements HistoryNodeService {
    private static Logger log=Logger.getLogger(HistNodeServiceHib.class);

    @Autowired
    public HistNodeServiceHib(SessionFactory sessionFactory){
        super(sessionFactory);

    }

    //Метод возвращает все исторические узлы
    @Override
    public List<HistoryNode> getAllNDH(){
        return getSession().createCriteria(HistoryNode.class).list();
    }

    //Метод возвращает все исторические узлы конкретного узла
    //todo дописать
    @Override
    public List<HistoryNode> getAllNDHForConcreteNode(HistoryNode node){
        return getSession().createCriteria(HistoryNode.class).list();
    }


    @Override
    @Transactional
    public void removeNode(Node node) throws IllegalArgumentException {
        throw new IllegalArgumentException("Object removed in HistoryNode without generated object not possible");
        //removeNode((HistoryNode)node,getNodeById(new Long(2)));
    }

    @Override
    @Transactional
    public void removeNode(HistoryNode node, Node generatingNode) {
        //todo выставить состаяния удаления для узла.
        node.setRemoved(true);
        saveNode(node,generatingNode);
    }

    @Override
    @Transactional
    public Node saveNode(Node node,  Node generatingNode) {
        HistoryNode hnode=(HistoryNode) node;
        //если узел новый, то сохраняем его и устанавливаем его рут парент в него же самого
        if(hnode.getId()==null){
            hnode= (HistoryNode) super.saveNode(hnode,generatingNode);
            hnode.setId_hystPremParent(hnode.getId());
            return hnode;
        }

        //если узел уже существовал, то загружаем копию узла из БД - та что была до этого
        HistoryNode oldNode= (HistoryNode) getNodeById(HistoryNode.class,hnode.getId());
        loadFullObject(oldNode);

        hnode.setId(null);

        //если узел не менялся, то и смысла его пересохранять нету - просто возвращаем старый узел
        if(hnode.equals(oldNode)){
            return oldNode;
        }
        //устанавливаем в старый узел свойство исторический
        oldNode.setHystory(true);
        saveOrUpdate(oldNode);
        //в новый узел прописываем идентификаторы первичного узла и узла который ушел в историю
        hnode.setId_hystParent(oldNode.getId_hystPremParent());
        hnode.setId_hystParent(oldNode.getId());

        //передаем на сохранение суперклассу
        return super.saveNode(hnode,generatingNode);
    }
}