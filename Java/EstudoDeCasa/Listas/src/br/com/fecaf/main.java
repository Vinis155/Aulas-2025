package br.com.fecaf;

import java.util.*;

public class main {

    public static void main(String[] args) {




        /*Lista que permite duplicata*/

        List <String> list = new ArrayList<>();
        /*Adicionando itens na lista*/

        list.add("Pão doce");
        list.add("Açucar");
        list.add("Pão Frances");
        list.add("Pão de batata");
        list.add("cenoura");

    /*Pegando o objeto pelo index*/
    list.get(0);

        System.out.println("list: " + list);

    /*Aponta o erro no index 0 e 2 pois eles são repetidos*/
    Set<String> setString = new HashSet <>();
    setString.add("Pão doce");
    setString.add("Açucar");
    setString.add("Pão Frances");
    setString.add("Pão de batata");
    setString.add("cenoura");

        System.out.println("Set: " + setString);

        /*Verificar se tem algum valor atribuido para essa chave*/
        System.out.println("Set: " + setString.contains("Testeee"));

    /*Nesta lista precisa ser informada a chave e o valor. As duas estão como string mas não é
    * obrigatorio*/

        Map <String,String> map = new HashMap<>();
        map.put("1","Vinicius");

        System.out.println(map.get("1"));



        /*Lista queue*/
        Queue <String> queue = new LinkedList<>();
        queue.add("Vini");
        queue.add("Silva");

        System.out.println("Queue: "+ queue);/*Printa toda a lista*/
        System.out.println("Queue: "+ queue.poll()); /*Verifica e remove o primeiro valor*/
        System.out.println("Queue: "+ queue);


        System.out.println("Queue: " +queue.peek());/*Apenas espia e não remove o valor*/
        System.out.println("Queue: "+ queue);
        System.out.println("Queue: " + queue.remove()); /*Recupera e remove a primeira pessoa/ da erra caso não
        tenha ninguem na lista*/


        /****************************************************************************************
         *   Usando stream para filtrar os dados
         * ****************************************************************************************
         */

        /*Crio uma lista nova chamada paes para armazenar os itens. Após transformo em strem para verificar os dados
        * eu retorno novamente com o toList para transformar em uma lista*/
        List <String> Paes = list.stream().filter(nome -> nome.startsWith("Pão")).toList();
        System.out.println("Paes: " + Paes);
    }




    /********************************************************************************
     *                  Aprendendo sobre garbage Collector                          *
     ********************************************************************************/

    /*SERIAL GC usa uma Thered e pausa o programa para ser realizada/ cenario ideal seria uma aplicação leve, CLI, ambiente embarcado*/


    /*PARALLEL GC*     varias Thereds para a coleta e não pausa o programa
    / o melhor ambiente seria com multiplos processadores como processamento em batch.
    /Não é o ideial para ambientes que precisam de baixa latencia*/


    /*G1*   Divide a memoria e limpa a que esta mais cheia. GC padrão do java atualmente/
    Por conta da divisão não precisa vasculhar a heap completa a pausa pode ser gerenciada
    /Recomendado para aplicações de medio e grande porte
    */

    /*ZGC*   Coleta em Thered separada e não pausa a aplicação/ ideal para aplicações que precisam de baixa latencia
    /heap muito grande (50Gb) ou mais, Sistema de jogos, sistemas financeiros,Aviões etc
    /Maior consumo de CPU e memória
     */

    /*SHENONDOAH*  Compactibilidade com versões do Java especificas
    * /Mantem a ultra-baixa latencia Heaps distribuidas onde cada nó tem sua heap*/

    /*********************************************************************************************************
     *                                      FIM Garbage Collector                                            *
     *********************************************************************************************************/

    /******************************************************************************************************
     *                      Aprendo sobre Spring                                                         */



}
