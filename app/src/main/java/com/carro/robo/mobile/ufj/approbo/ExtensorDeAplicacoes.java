package com.carro.robo.mobile.ufj.approbo;

import android.app.Application;

public class ExtensorDeAplicacoes extends Application {

    public static ConnectionThread connect = new ConnectionThread();

    public ConnectionThread getConnect(){
        return connect;
    }

}
