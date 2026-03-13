package br.com.munif.comum.persistencia;

import org.hibernate.envers.RevisionListener;

public class MRevisionEntityListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        MRevisionEntity revisao = (MRevisionEntity) revisionEntity;
        revisao.setIp("127.0.0.1");
        revisao.setUsuario("sistema");
        revisao.setOi("default");
    }
}