package com.carro.robo.mobile.ufj.approbo;

import android.app.Application;

public class ExtensorDeAplicacoes extends Application {

    private ConnectionThread connect;

    public ConnectionThread getConnect(){
        return connect;
    }

    public void setConnect(ConnectionThread A ) {
        this.connect = A;
    }

}
