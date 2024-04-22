package org.apollo.template.Service.Alert;

public interface Alertable <T>{

    void getAlertArea();
    void clearAlertArea();
    void addAlert(AlertComp alertComp);
    void removeAlert(AlertComp alertComp);

}
