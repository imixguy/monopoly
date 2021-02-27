package by.imix.cms.nodedata.test;

import by.imix.cms.nodedata.Node;
import by.imix.cms.nodedata.NodeProperty;
import by.imix.cms.nodedata.service.hib.usermanager.TestService;
import by.imix.cms.usermanager.Role;
import by.imix.cms.usermanager.User;
import by.imix.cms.usermanager.service.UserManagerNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 24.10.13
 * Time: 16:17
 * To change this template use File | Settings | File Templates.
 */
public class TestClass {

    public static void main(String str[]){
        new ClassPathXmlApplicationContext("testConnect.xml");
//        String h=new TestClass().sobrat();
//        System.out.print("jklj");
    }

    private int countPr=8;
    public String sobrat(){


        StringBuilder sql=new StringBuilder();
        sql.append("with hh as (select sta_no_2 st, NUM_PLF_STA from \"NSIVIEW\".\"PLF_STA_BCH\" where sta_no = 'ksoTr' and sta_no_1 = 'snpTr' and \"PLF_S_BCH#END\" > current timestamp) ");
        sql.append(",tp as (select sta_no, sta_no_1, sta_no_2, sta_no_3  from \"NSIVIEW\".\"PLF_COURSE\" where \"COU#END\" > current timestamp and \"COU_PRECISE\" = '0' and sta_no_3 in ((select hh.st from hh)) ");
        sql.append("union ");
        sql.append("select sta_no, sta_no_1, sta_no_2, sta_no_3  from \"NSIVIEW\".\"PLF_GROUP\" where \"PLF_GR#END\" > current timestamp and sta_no_4 in (select hh.st from hh)) ");
        sql.append("select mm.gr, ");
        sql.append("case ");
        sql.append("when mm.sp = 'sspTr' then 'snpR' ");
        for(int i=1;i<countPr+1;i++){
            sql.append("when (mm.sp <> 'sspTr' or mm.sp is null) and mm.p"+i+" is not null and mm.p"+(i+1)+" is null then mm.p"+i+" ");
        }
        sql.append("when (mm.sp <> 'sspTr' or mm.sp is null) and mm.p"+(countPr+1)+" is not null then mm.p"+(countPr+1)+" ");
        sql.append("else (select hh.st from hh where NUM_PLF_STA = 1) end otc ");
        sql.append("from ( ");
        sql.append("select distinct tt.sta_no gr, bn.sta_no_4 sp, ");
        for(int i=1;i<countPr+2;i++){
            sql.append("p"+i+".sta_no_3 p"+i+", ");
        }
        sql.delete(sql.length()-1,sql.length());
        sql.append(" from (select sta_no from \"NSI\".\"STA\" where sta_no in ('List')) tt ");
        sql.append("left join \"NSIVIEW\".\"STATION_BOUNDARY\" bn on bn.\"BOU#END\" > current timestamp and bn.sta_no_1 = tt.sta_no and bn.sta_no_4 = 'sspTr' ");
        sql.append("left join tp p1 on p1.sta_no = 'ksoTr' and tt.sta_no between p1.sta_no_1 and p1.sta_no_2 ");
        String pd="";
        for(int i=1;i<countPr+1;i++){
            pd+="p"+i+".sta_no_3";
            sql.append("left join tp p"+(i+1)+" on p"+(i+1)+".sta_no = p"+i+".sta_no_3 and tt.sta_no between p"+(i+1)+".sta_no_1 and p"+(i+1)+".sta_no_2 and p"+(i+1)+".sta_no_3 not in ("+pd+") ");
            pd+=", ";
        }
        sql.append(") mm");
        return sql.toString();
    }


    //    @Autowired
//    TestService ts;
    @Autowired
    private UserManagerNodeService userManagerService;

    public UserManagerNodeService getUserManagerService() {
        return userManagerService;
    }

    public void setUserManagerService() {
        this.userManagerService = userManagerService;
    }

    public TestService testService;

    public void setStartTest(TestService testService) {
        this.testService=testService;
        testServer();
        startNewServer();

//        Node us234 = testService.getNodeById(User.class, new Long(4));
//        System.out.println(us234.getId());
//        System.out.println(us234.getId());
//        System.out.println(javax.persistence.OneToMany.class.getProtectionDomain().getCodeSource().getLocation());
//        Role r=new Role(null,"administrator");

        //r.getNodeProperties().add(new NodeProperty());
//        np.setNode(r);
//        testService.saveNode(r);

//        Role r2=new Role(null,"unregisteruser");
//        testService.saveNode(r2);
//        Role r2=new Role("noregisteruser");
//        testService.saveNode(r2);
////        testService.saveNode(np);
//        User us=new User("miha","nuha");
//        us.getRoles().add(r);
//        testService.saveNode(us);

//        User us2=new User("admin","admin");
//        us2.getRoles().add(r);
//        testService.saveNode(us2);

//        User k=new User("3","3");
//        k.getRoles().add(r2);
//        NodeProperty np2=new NodeProperty();
//        np.setValue("222");
//        np.setKeyt("333");
//        k.getNodeProperties().add(np2);

//        testService.saveNode(k);
//
//        State state = new StateSimple();
//        state.setSimpleDescription("view");
//        testService.saveState(state);
//
//        State state1 = new StateSimple();
//        state1.setSimpleDescription("correct");
//        testService.saveState(state1);
//
//        State state2 = new StateSimple();
//        state2.setSimpleDescription("delete");
//        testService.saveState(state2);
//
//        State state3 = new StateNoSimple();
//        state3.setSimpleDescription("viewNews");
//        testService.saveState(state3);
//        State state4 = new StateCreateUser();
//        state4.setSimpleDescription("createUser");
//        testService.saveState(state4);
//
//        List<StateDefault> ls=testService.getStates();
//
//        List<Node> lND=testService.getNodes2();
//
//        List<Node> nodes=testService.getNodes(50,0,"id",true);
//
//
//
////        User us=new User();
//        List<Node> nodes2=testService.getNodes(User.class);
//
////        for(Node n:nodes2){
////            NodeState ns=new NodeState();
////            for(State s:ls){
////                ns.addState(s,r);
////            }
////            ns.addState(ls.get(0),r2);
////            n.getNodeStates().add(ns);
////            testService.saveNode(n);
////        }
//
//
//
//        Rule r3=new Rule();
//        r3.setId(111);
//        r3.setDiscription("dsfsdf");
//        NodeStateDouble nsd=new NodeStateDouble(ls.get(0),r);
//        NodeStateDouble nsd2=new NodeStateDouble(ls.get(1),r);
//        NodeStateDouble nsd3=new NodeStateDouble(ls.get(4),r);
//        r3.addAddedState(nsd);
//        r3.addRemovedState(nsd2);
//        r3.addAddedState(nsd3);
//
//        r3.rulePerform(nodes2.get(0));

//        nodes2.get(0).
//        testService.getById(new Long(3),false);
//        JButton jb=new JButton("sdf");
//        jb.addActionListener();
//        jb.removeActionListener();
//        System.out.println(nodes2.toArray().toString());
    }

    private void testServer() {
        List<Node> listNode=testService.getNodes2();
        System.out.println(listNode.size());

    }

    public void startNewServer(){
        Role r=new Role("administrator");
        r.getNodeProperties().add(new NodeProperty("credential","ROLE_ADMIN_SETTINGS"));
        testService.saveNode(r);

        Role r3=new Role("registeruser");
        r3.getNodeProperties().add(new NodeProperty("credential","ROLE_AUTH_DATA"));
        testService.saveNode(r3);

        User us2=new User("admin","admin");
        us2.getRoles().add(r);
        us2.getRoles().add(r3);
        testService.saveNode(us2);


//        UserASKBD userASKBD=new UserASKBD();
//        userASKBD.getRoles().add(r);
//        userASKBD.setName("miha");
//        userASKBD.setPassword("123");
//        userASKBD.setAskbd("asdf");
//        testService.saveNode(userASKBD);


    }
}
