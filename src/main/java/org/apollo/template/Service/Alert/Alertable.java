package org.apollo.template.Service.Alert;

public interface Alertable <T>{

    T getAlertArea();
    void clearAlertArea();
    void addAlert(AlertComp alertComp);
    void removeAlert(AlertComp alertComp);

}
