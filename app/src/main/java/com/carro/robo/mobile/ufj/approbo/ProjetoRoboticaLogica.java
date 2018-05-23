package com.carro.robo.mobile.ufj.approbo;

import android.support.v7.app.AppCompatActivity;

public class ProjetoRoboticaLogica extends AppCompatActivity{

    private boolean isCorrect = false,acionarEquacao; // botoes para os metodos de passagem dos dados
    private int min, max, nivel1 = 0, nivel2 = 0, flag; // variaveis para determinar o floor e o teto dos numeros random, contagem para passar de nivel e sinalizador do tipo de conta;
    private int randomNumber1; // variavel do primeiro numero random
    private int randomNumber2; // variavel do segundo numero random
    private int randomNumberAdd; // variavel que recebe a soma dos randons
    private int randomNumberSub; // variavel que recebe a subtração dos randons
    private float dist, TAM; // variaveis para determinar e calcular a distancia da pista e que o carrinho anda, respectivamente

    public boolean botaoCorreto(boolean resultado) { // funcao que recebe se a pessoa apertou o botao certo
        isCorrect = resultado; // variavel recebe true/false do botao que apertou
        return isCorrect; // retorna a variavel
    }

    public boolean acionaQuestao(boolean acionar){
        acionarEquacao = acionar;
        return acionarEquacao;
    }

    public void andaCarrinho(float d) { // funcao que manda o carrinho andar
        String aux = Integer.toString((int) d) + " ";
        byte[] data = (aux).getBytes();
        ExtensorDeAplicacoes.connect.write(data);

//        sendMessage(Float.toString(dist));// distancia recebe o tamanho da pista dividido pela constante 11,8

    }




    public String printaConta() { // metodo para fazer printar a conta de acordo com o sinalizador

        if (flag == 1) { // se a flag for 1, sera conta de subtraçao
            return ("Selecione a resposta correta: \n     "+ Integer.toString(randomNumber1) + " - " + Integer.toString(randomNumber2));
        } else { // caso contrario sera conta de soma
            return ("Selecione a resposta correta: \n     " + Integer.toString(randomNumber1) + " + " + Integer.toString(randomNumber2));
        }

    }

    public String printaBotao() {// printa o resultado da conta de acordo com o sinalizador
        if (flag == 1) { // se flag for 1, printa o resultado da subtração
            return (Integer.toString(randomNumberSub));
        } else { // caso contrario printa o resultado da soma
            return (Integer.toString(randomNumberAdd));
        }
    }

    public void logica(boolean anda) { // logica principal
        TAM = 500; // tamanho da pista em cm
        dist = (float) (TAM / 23.6);
        if (acionarEquacao == true) { // se o botao de start for apertado a logica entra em ação
            if ((nivel1 >= 0 && nivel1 <= 4) && nivel2 == 0) {// se estiver no nivel 1 segue as instruções
                randomNumber1 = (int) (10 * Math.random()) + 1; // primeiro random recebe um numero random de 0 a 10
                randomNumber2 = (int) (10 * Math.random()) + 1; // segundo random recebe um numero random de 0 a 10
                if (randomNumber1 > randomNumber2) { // se o primeiro random for maior que o segundo..
                    randomNumberSub = randomNumber1 - randomNumber2; // a operação de menos é realizada
                    flag = 1; // sinalizador recebe 1
                    printaConta(); // printa os dois numeros randons que foram gerados
                    printaBotao(); // printa o resultado da subtração
                } else { // caso o segundo random seja maior que o primeiro
                    randomNumberAdd = randomNumber1 + randomNumber2; // a operação de adição é realizada
                    flag = 0; // sinalizador recebe 0
                    printaConta();
                    printaBotao(); // printa o resultado da adição
                }
                if (isCorrect == false) { // se o botao que a pessoa clicou for o errado
                    if(nivel1 > 0)
                    {
                        nivel1--; // nivel1 recebe -1 caso estiver na segunda questão do nivel 1
                    }

                } else { // caso ele tenha acertado o botao correto
                    nivel1++; // nivel1 recebe +1
                    if(anda == true){
                    andaCarrinho(dist); // chama a função que faz o carrinho andar
                    }
                }
                return;
            }// fim nivel 1

            if (nivel1 > 4 && (nivel2 >= 0 && nivel2 <= 4)) // se a pessoa acertou pelo menos 2 questões do nivel 1 seguidas
            {
                max = 50; // as questões passam a ser com números de 20 à 50
                min = 20;
                randomNumber1 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);// cria teto e floor do range das
                randomNumber2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);// variaveis de acordo com max e min
                if (randomNumber1 > randomNumber2) {// mesmas logicas do nível 1
                    randomNumberSub = randomNumber1 - randomNumber2;
                    flag = 1;
                    printaConta();
                    printaBotao();
                } else {
                    randomNumberAdd = randomNumber1 + randomNumber2;
                    flag = 0;
                    printaConta();
                    printaBotao();
                }
                if (isCorrect == false ) {
                    if(nivel2 > 0)
                    {
                        nivel2--; // nivel 2 recebe -1 caso esteja errado
                    }
                } else {
                    nivel2++; // nivel 2 recebe +1 caso esteja correto
                    if(anda == true) {
                        andaCarrinho(dist*2);
                    }
                }
                return;
            }// fim nivel 2

            if (nivel1 > 4 && nivel2 > 4)// se a pessoa acertou pelo menos 2 questões de nivell 1 e 2 de nível 2
            {
                max = 100; // range das questões passam a ser de 50 à 100
                min = 50;
                randomNumber1 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);
                randomNumber2 = (int) (Math.floor(Math.random() * (max - min + 1)) + min);

                if (randomNumber1 > randomNumber2) { // mesma lógica dos níveis anteriores
                    randomNumberSub = randomNumber1 - randomNumber2;
                    flag = 1;
                    printaConta();
                    printaBotao();
                } else {
                    randomNumberAdd = randomNumber1 + randomNumber2;
                    flag = 0;
                    printaConta();
                    printaBotao();
                }
                if (isCorrect == false) { // caso o participante errar uma questão
                    if(nivel2 > 0)
                    {
                        nivel2--; // de nível 3, ele volta para o nivel 2;
                    }
                } else { // não há variavel para nivel 3 pois a brincadeira esta prestes a acabar em 2 acertos no maximo
                    if(anda == true) {
                        andaCarrinho(dist*3);
                    }
                }
            }//nivel 3

        }

    }

}
