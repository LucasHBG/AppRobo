package com.carro.robo.mobile.ufj.approbo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class CriacaoQuestoes extends AppCompatActivity{

    private ProjetoRoboticaLogica novaLogica = new ProjetoRoboticaLogica();

    private int a,b;

    Random r = new Random();
    private TextView totalDePontos;
    private TextView textoDaQuestao;
    private Button escolha1;
    private Button escolha2;
    private Button escolha3;
    private int escolhaCorreta;

    private int somaScore = 0;
    //    private int numeroDaQuestao = 0;      com os calculos alterados e a nova logica usada, esse inteiro fica inutil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_questoes);


        totalDePontos = (TextView) findViewById(R.id.score);
        textoDaQuestao = (TextView) findViewById(R.id.perguntas);
        escolha1 = (Button) findViewById(R.id.escolha1);
        escolha2 = (Button) findViewById(R.id.escolha2);
        escolha3 = (Button) findViewById(R.id.escolha3);

        novaLogica.acionaQuestao(true);
        mudarQuestao(false);


        escolha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaCorreta== 1){
                    novaLogica.botaoCorreto(true);
                    Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                            .show();
                    somaScore = somaScore + 1;
                    updateScore(somaScore);
                    mudarQuestao(true);

                }
                else
                {
                    novaLogica.botaoCorreto(false);
                    Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                        .show();
                        mudarQuestao(true);
                }
            }

        });

        escolha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaCorreta == 2) {
                    novaLogica.botaoCorreto(true);
                        Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                                .show();
                        somaScore = somaScore + 1;
                        updateScore(somaScore);
                        mudarQuestao(true);
                    } else {
                   novaLogica.botaoCorreto(false);
                        Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                                .show();
                        mudarQuestao(true);
                    }
                }

        });

        escolha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (escolhaCorreta == 3) {

                    novaLogica.botaoCorreto(true);
                        Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                                .show();
                        somaScore = somaScore + 1;
                        updateScore(somaScore);
                        mudarQuestao(true);
                } else {
                    novaLogica.botaoCorreto(false);
                    Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                                .show();
                        mudarQuestao(true);
                    }
                }

        });

    }

    private void mudarQuestao(boolean anda){
        novaLogica.logica(anda);
        textoDaQuestao.setText(novaLogica.printaConta());
        escolhaCorreta = getEscolhaButton();
        String r1,r2,r3;
        r1 = novaLogica.printaBotao();
        r2 = Integer.toString(Integer.parseInt(novaLogica.printaBotao())+r.nextInt(4+1+4)-4);
        r3 = Integer.toString(Integer.parseInt(novaLogica.printaBotao())+r.nextInt(4+1+4)-4);
        if(r2 == r3){r3 = Integer.toString(Integer.parseInt(r3)+1);}
        if(r1 == r3){r3 = Integer.toString(Integer.parseInt(r3)+1);}
        if(r1 == r2){r2 = Integer.toString(Integer.parseInt(r2)+1);}

        if(escolhaCorreta == 1){
            escolha1.setText(r1);
            escolha2.setText(r2);
            escolha3.setText(r3);
        }
        else if(escolhaCorreta == 2){
            escolha1.setText(r2);
            escolha2.setText(r1);
            escolha3.setText(r3);
        }
        else{
            escolha1.setText(r3);
            escolha2.setText(r2);
            escolha3.setText(r1);
        }
    }

    private void updateScore(int recebePontos){
        totalDePontos.setText("" +somaScore);
    }

    public int getEscolhaButton(){
        int sorteadorQuestoes = (int) (r.nextInt(3)+1);
        return sorteadorQuestoes;
    }

}