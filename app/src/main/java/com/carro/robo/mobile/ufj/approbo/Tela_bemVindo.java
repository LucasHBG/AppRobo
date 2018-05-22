package com.carro.robo.mobile.ufj.approbo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tela_bemVindo extends AppCompatActivity {

    private Button iniciarBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_bem_vindo);

        //Aqui recebo o click do usuario e o sincronizo
        iniciarBT = (Button) findViewById(R.id.iniciarBT);

        Intent telaPareia = new Intent(Tela_bemVindo.this, MainBluetoothActivity.class);
        startActivity(telaPareia);

        //Inicio do listener para o botao de inicio (iniciarBT) que chama outra tela (tela_questoes.xml)
        iniciarBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent chamaTelaQuestoes = new Intent(Tela_bemVindo.this, CriacaoQuestoes.class);
                    startActivity(chamaTelaQuestoes);
            }
        });

    }

}
