package rw.gcktc.cms.userknow;

import rw.gcktc.cms.material.parsetonode.NodeExt;
import rw.gcktc.cms.nodedata.Node;

/**
 * Created by miha on 09.09.2014.
 */
public class PersonalKnow extends NodeExt{
    //Имя
    private String firstName;
    //Фамилия
    private String lastName;
    //Должность
    private String prof;
    //Телефон
    private String tel;
    private String email;
    //Дата рождения
    private String dayBez;
    private String mounthBez;
    private String yerBez;
    //
    private String java;
    private String hib;
    private String spring;
    private String libjava;
    private String maven;
    private String ant;
    private String othersborsh;


    private String csh;
    private String libcsh;

    private String cpl;
    private String libcpl;
    private String vc;
    private String libvc;

    private String delphi;
    private String libdelphi;

    private String assembler;
    private String libassembler;

    private String php;
    private String libphp;

    private String javascript;
    private String jquery;
    private String ajax;
    private String libjs;

    private String otherLangPr;

    private String html;
    private String xml;

    private String tomcat;
    private String jboss;
    private String websphere;
    private String resin;
    private String nameApplicationServer;

    private String mssql;
    private String mysql;
    private String db2sql;
    private String oraclesql;
    private String otherSQL;


    private String wordPress;
    private String joomla;
    private String drupal;
    private String opencms;
    private String magnolia;
    private String dotCMS;
    private String otherCMS;

    private String ide;

    private String photoshop;
    private String corelDraw;
    private String autocad;
    private String s3dmax;
    private String primerePro;
    private String msOffice;
    private String openOffice;
    private String otherProgramm;
    private String design;

    private String hobbi;

    private String sport;
    private String desire;

    private PersonalKnow personalKnow;

    public PersonalKnow() {
        super();
    }

    public PersonalKnow(Node node) {
        super(node);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDayBez() {
        return dayBez;
    }

    public void setDayBez(String dayBez) {
        this.dayBez = dayBez;
    }

    public String getMounthBez() {
        return mounthBez;
    }

    public void setMounthBez(String mounthBez) {
        this.mounthBez = mounthBez;
    }

    public String getYerBez() {
        return yerBez;
    }

    public void setYerBez(String yerBez) {
        this.yerBez = yerBez;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getHib() {
        return hib;
    }

    public void setHib(String hib) {
        this.hib = hib;
    }

    public String getSpring() {
        return spring;
    }

    public void setSpring(String spring) {
        this.spring = spring;
    }

    public String getLibjava() {
        return libjava;
    }

    public void setLibjava(String libjava) {
        this.libjava = libjava;
    }

    public String getMaven() {
        return maven;
    }

    public void setMaven(String maven) {
        this.maven = maven;
    }

    public String getAnt() {
        return ant;
    }

    public void setAnt(String ant) {
        this.ant = ant;
    }

    public String getOthersborsh() {
        return othersborsh;
    }

    public void setOthersborsh(String othersborsh) {
        this.othersborsh = othersborsh;
    }

    public String getCsh() {
        return csh;
    }

    public void setCsh(String csh) {
        this.csh = csh;
    }

    public String getLibcsh() {
        return libcsh;
    }

    public void setLibcsh(String libcsh) {
        this.libcsh = libcsh;
    }

    public String getCpl() {
        return cpl;
    }

    public void setCpl(String cpl) {
        this.cpl = cpl;
    }

    public String getLibcpl() {
        return libcpl;
    }

    public void setLibcpl(String libcpl) {
        this.libcpl = libcpl;
    }

    public String getVc() {
        return vc;
    }

    public void setVc(String vc) {
        this.vc = vc;
    }

    public String getLibvc() {
        return libvc;
    }

    public void setLibvc(String libvc) {
        this.libvc = libvc;
    }

    public String getDelphi() {
        return delphi;
    }

    public void setDelphi(String delphi) {
        this.delphi = delphi;
    }

    public String getLibdelphi() {
        return libdelphi;
    }

    public void setLibdelphi(String libdelphi) {
        this.libdelphi = libdelphi;
    }

    public String getAssembler() {
        return assembler;
    }

    public void setAssembler(String assembler) {
        this.assembler = assembler;
    }

    public String getLibassembler() {
        return libassembler;
    }

    public void setLibassembler(String libassembler) {
        this.libassembler = libassembler;
    }

    public String getPhp() {
        return php;
    }

    public void setPhp(String php) {
        this.php = php;
    }

    public String getLibphp() {
        return libphp;
    }

    public void setLibphp(String libphp) {
        this.libphp = libphp;
    }

    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public String getJquery() {
        return jquery;
    }

    public void setJquery(String jquery) {
        this.jquery = jquery;
    }

    public String getAjax() {
        return ajax;
    }

    public void setAjax(String ajax) {
        this.ajax = ajax;
    }

    public String getLibjs() {
        return libjs;
    }

    public void setLibjs(String libjs) {
        this.libjs = libjs;
    }

    public String getOtherLangPr() {
        return otherLangPr;
    }

    public void setOtherLangPr(String otherLangPr) {
        this.otherLangPr = otherLangPr;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getTomcat() {
        return tomcat;
    }

    public void setTomcat(String tomcat) {
        this.tomcat = tomcat;
    }

    public String getJboss() {
        return jboss;
    }

    public void setJboss(String jboss) {
        this.jboss = jboss;
    }

    public String getWebsphere() {
        return websphere;
    }

    public void setWebsphere(String websphere) {
        this.websphere = websphere;
    }

    public String getResin() {
        return resin;
    }

    public void setResin(String resin) {
        this.resin = resin;
    }

    public String getNameApplicationServer() {
        return nameApplicationServer;
    }

    public void setNameApplicationServer(String nameApplicationServer) {
        this.nameApplicationServer = nameApplicationServer;
    }

    public String getMssql() {
        return mssql;
    }

    public void setMssql(String mssql) {
        this.mssql = mssql;
    }

    public String getMysql() {
        return mysql;
    }

    public void setMysql(String mysql) {
        this.mysql = mysql;
    }

    public String getDb2sql() {
        return db2sql;
    }

    public void setDb2sql(String db2sql) {
        this.db2sql = db2sql;
    }

    public String getOraclesql() {
        return oraclesql;
    }

    public void setOraclesql(String oraclesql) {
        this.oraclesql = oraclesql;
    }

    public String getOtherSQL() {
        return otherSQL;
    }

    public void setOtherSQL(String otherSQL) {
        this.otherSQL = otherSQL;
    }

    public String getWordPress() {
        return wordPress;
    }

    public void setWordPress(String wordPress) {
        this.wordPress = wordPress;
    }

    public String getJoomla() {
        return joomla;
    }

    public void setJoomla(String joomla) {
        this.joomla = joomla;
    }

    public String getDrupal() {
        return drupal;
    }

    public void setDrupal(String drupal) {
        this.drupal = drupal;
    }

    public String getOpencms() {
        return opencms;
    }

    public void setOpencms(String opencms) {
        this.opencms = opencms;
    }

    public String getMagnolia() {
        return magnolia;
    }

    public void setMagnolia(String magnolia) {
        this.magnolia = magnolia;
    }

    public String getDotCMS() {
        return dotCMS;
    }

    public void setDotCMS(String dotCMS) {
        this.dotCMS = dotCMS;
    }

    public String getOtherCMS() {
        return otherCMS;
    }

    public void setOtherCMS(String otherCMS) {
        this.otherCMS = otherCMS;
    }

    public String getIde() {
        return ide;
    }

    public void setIde(String ide) {
        this.ide = ide;
    }

    public String getPhotoshop() {
        return photoshop;
    }

    public void setPhotoshop(String photoshop) {
        this.photoshop = photoshop;
    }

    public String getCorelDraw() {
        return corelDraw;
    }

    public void setCorelDraw(String corelDraw) {
        this.corelDraw = corelDraw;
    }

    public String getAutocad() {
        return autocad;
    }

    public void setAutocad(String autocad) {
        this.autocad = autocad;
    }

    public String getS3dmax() {
        return s3dmax;
    }

    public void setS3dmax(String s3dmax) {
        this.s3dmax = s3dmax;
    }

    public String getPrimerePro() {
        return primerePro;
    }

    public void setPrimerePro(String primerePro) {
        this.primerePro = primerePro;
    }

    public String getMsOffice() {
        return msOffice;
    }

    public void setMsOffice(String msOffice) {
        this.msOffice = msOffice;
    }

    public String getOpenOffice() {
        return openOffice;
    }

    public void setOpenOffice(String openOffice) {
        this.openOffice = openOffice;
    }

    public String getOtherProgramm() {
        return otherProgramm;
    }

    public void setOtherProgramm(String otherProgramm) {
        this.otherProgramm = otherProgramm;
    }

    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getHobbi() {
        return hobbi;
    }

    public void setHobbi(String hobbi) {
        this.hobbi = hobbi;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDesire() {
        return desire;
    }

    public void setDesire(String desire) {
        this.desire = desire;
    }

    public void setPersonalKnow(PersonalKnow personalKnow) {
        setCopyAllData(this,personalKnow);
    }

    private String type="personalKnow";

    @Override
    public String getType() {
        return "personalKnow";
    }
}