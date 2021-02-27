package by.imix.webcms.security;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: miha
 * Date: 13.12.13
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public class CredentialBox {
    private Set<String> listCredential=new HashSet<String>();

    public CredentialBox(Set<String>... list) {
        this.addListCredential(list);
    }

    public Set<String> getListCredential() {
        return listCredential;
    }

    public void setListCredential(Set<String> listCredential) {
        this.listCredential = listCredential;
    }

    public void addListCredential(Set<String>... listCredential) {
        for(Set<String> cre:listCredential) {
            this.listCredential.addAll(cre);
        }
    }
}
