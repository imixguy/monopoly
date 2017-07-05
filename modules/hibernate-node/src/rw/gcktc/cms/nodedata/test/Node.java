package rw.gcktc.cms.nodedata.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by miha on 12.11.2014.
 */
public class Node {
    @Id
    @GeneratedValue
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    Node parent;

    @OneToOne(cascade = CascadeType.ALL)
    Node leftChild;

    @OneToOne(cascade = CascadeType.ALL)
    Node rightChild;

    Node() {}

    public Node(String name) {
        this.name = name;
    }
    // omitted getters and setters for brevity


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Node.class)
                .setProperty("hibernate.connection.url",
                        "jdbc:h2:mem:foo;DB_CLOSE_DELAY=-1")
                .setProperty("hibernate.hbm2ddl.auto", "create")
                .buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        a.setLeftChild(b);
        b.setParent(a);
        a.setRightChild(c);
        c.setParent(a);
        b.setLeftChild(d);
        d.setParent(b);
        b.setRightChild(e);
        e.setParent(b);
        System.out.println("Before saving:");
        print(a, 1);
        Serializable rootNodeId = session.save(a);
        transaction.commit();
        session.close();

        session = sessionFactory.openSession();
        Node root = (Node) session.get(Node.class, rootNodeId);
        System.out.println("Freshly loaded:");
        print(root, 1);
        session.close();
    }

    private static void print(Node node, int depth) {
        if (node == null) { return; }
        System.out.format("%" + depth + "s\n", node);
        print(node.getLeftChild(), depth + 1);
        print(node.getRightChild(), depth + 1);
    }
}
