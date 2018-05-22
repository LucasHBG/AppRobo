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

    private TextView totalDePontos;
    private TextView textoDaQuestao;
    private Button escolha1;
    private Button escolha2;
    private Button escolha3;

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

        mudarQuestao();


        escolha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEscolhaButton()== 1){

                    novaLogica.andaCarrinho();
                   // Integer.parseInt(novaLogica.printaBotao());
                    novaLogica.botaoCorreto(true);
                    Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                            .show();
                    somaScore = somaScore + 1;
                    //novaLogica.andaCarrinho();
                    updateScore(somaScore);
                    mudarQuestao();
                }
                else
                {
                  // Integer.parseInt(novaLogica.printaBotao());
                    novaLogica.botaoCorreto(false);
                    Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                        .show();
                        mudarQuestao();
                }
            }

        });

        escolha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEscolhaButton() == 2) {
                   // Integer.parseInt(novaLogica.printaBotao());
                    novaLogica.botaoCorreto(true);
                        Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                                .show();
                        somaScore = somaScore + 1;
                     //   novaLogica.andaCarrinho();
                        updateScore(somaScore);
                        mudarQuestao();
                    } else {
                   novaLogica.botaoCorreto(false);
                        Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                                .show();
                        mudarQuestao();
                    }
                }

        });

        escolha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getEscolhaButton() == 3) {
                    //Integer.parseInt(novaLogica.printaBotao())

                    novaLogica.botaoCorreto(true);
                        Toast.makeText(CriacaoQuestoes.this, "Acertou!", Toast.LENGTH_SHORT)
                                .show();
                        somaScore = somaScore + 1;
                        updateScore(somaScore);
                        mudarQuestao();
                } else {
                    novaLogica.botaoCorreto(false);
                    Toast.makeText(CriacaoQuestoes.this, "Resposta errada!", Toast.LENGTH_SHORT)
                                .show();
                        mudarQuestao();
                    }
                }

        });

    }

    private void mudarQuestao(){
        textoDaQuestao.setText(novaLogica.printaConta());

        if(getEscolhaButton() == 1){
        escolha1.setText(Integer.toString(getEscolhaButton()));
        }
        else {escolha1.setText(Integer.toString(Integer.parseInt((novaLogica.printaBotao())+1)));}

        if(getEscolhaButton()==2){
            escolha2.setText(novaLogica.printaBotao());
        }
        else {
            escolha2.setText(Integer.toString(Integer.parseInt((novaLogica.printaBotao()) + 2)));
        }
        if(getEscolhaButton()==3){
            escolha3.setText(novaLogica.printaBotao());
        }
        else {
            escolha3.setText(Integer.toString(Integer.parseInt((novaLogica.printaBotao()) + 3)));
        }

    }

    private void updateScore(int recebePontos){
        totalDePontos.setText("" +somaScore);
    }

    public int getEscolhaButton(){
        Random r = new Random();
        int sorteadorQuestoes = (int) (r.nextInt(3)+1);
        return sorteadorQuestoes;
    }

}