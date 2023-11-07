package Panels;

import Classes.Cliente;
import Classes.JogadoresStatic;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Player02 {
    private JLabel lblAviso;
    private JLabel lblNomeJogador;
    private JButton button11;
    private JButton button12;
    private JButton button13;
    private JButton button21;
    private JButton button22;
    private JButton button23;
    private JButton button31;
    private JButton button32;
    private JButton button33;
    private JPanel panelP2;

    private String simbolo_str = "-1";

    JButton[] ListaDeBotoes = {
            button11, button12, button13,
            button21, button22, button23,
            button31, button32, button33
    };
    private Cliente Jogador2;

    private Map<String, String> inicial;

    public Player02(){

        button11.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "1"); pacote.put("coluna", "1");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button12.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "1"); pacote.put("coluna", "2");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button13.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "1"); pacote.put("coluna", "3");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button21.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "2"); pacote.put("coluna", "1");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button22.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "2"); pacote.put("coluna", "2");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button23.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "2"); pacote.put("coluna", "3");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button31.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "3"); pacote.put("coluna", "1");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button32.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "3"); pacote.put("coluna", "2");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});
        button33.addActionListener(ActionEvent -> {Map<String, String> pacote = new HashMap<>();pacote.put("linha", "3"); pacote.put("coluna", "3");pacote.put("aviso", "Sua Vez!");pacote.put("simbolo", simbolo_str);FazJogada(pacote);});

        new Thread(() -> {
            // Iniciando cliente recebendo pacote inicial do jogador
            try {
                Jogador2 = new Cliente(8888);
//                Map<String, String> pacoteJ1;
//                DesabilitaHabilitaBotoes(false);
//                while(true){
//
//                    pacoteJ1 = new HashMap<>(Jogador2.RecebeMapPacote());
//                    System.out.println("RECEBEU O PACOTE!");
//                    System.out.println(pacoteJ1.get("nome1") + " -> " + pacoteJ1.get("simbolo1"));
//                    System.out.println(pacoteJ1.get("nome2") + " -> " + pacoteJ1.get("simbolo2"));
//                    System.out.println(pacoteJ1.get("aviso"));
//                    break;
//                }
//
//                System.out.println("Saiu do While P1");
            }catch (Exception ex){
                System.out.println("Erro ao abrir cliente 1: " + ex.getMessage());
            }

            // Iniciando Jogador 2
            try{
                Map<String, String> pacoteJ2;
                DesabilitaHabilitaBotoes(false);

                while(true){
                    pacoteJ2 = new HashMap<>(Jogador2.RecebeMapPacote());
                    System.out.println("RECEBEU O PACOTE!");
                    simbolo_str = pacoteJ2.get("simbolo2");
                    String aviso = pacoteJ2.get("aviso");
                    lblNomeJogador.setText(pacoteJ2.get("nome2"));
                    lblAviso.setText(aviso);

                    if (Objects.equals(aviso, "Você Começa!") || Objects.equals(aviso, "Sua Vez!")) {

                        if (Objects.equals(aviso, "Sua Vez!")) {
                            TempPrintPlayer(pacoteJ2);
                            MarcaJogada(pacoteJ2);
                        }

                        DesabilitaHabilitaBotoes(true);

                    } else {
                        pacoteJ2.put("aviso", "Você Começa!");
                        Jogador2.EnviaMapPacote(pacoteJ2);
                        DesabilitaHabilitaBotoes(false);
                        mostraMensagem(pacoteJ2);
                    }
                }
            }catch (Exception ex){
                System.out.println("Erro no Jogador 2: " + ex.getMessage());
            }
        }).start();

    }

    private void DesabilitaHabilitaBotoes(boolean cod){

        for(JButton b : ListaDeBotoes){
            b.setEnabled(cod);
        }
    }

    private void MarcaJogada(Map<String, String> pacote) {
        String linha = pacote.get("linha");
        String coluna = pacote.get("coluna");
        String botao = linha + coluna;
        String simbolo = QualSimbolo(pacote);

        switch (botao) {
            case "11" -> button11.setText(simbolo);
            case "12" -> button12.setText(simbolo);
            case "13" -> button13.setText(simbolo);
            case "21" -> button21.setText(simbolo);
            case "22" -> button22.setText(simbolo);
            case "23" -> button23.setText(simbolo);
            case "31" -> button31.setText(simbolo);
            case "32" -> button32.setText(simbolo);
            case "33" -> button33.setText(simbolo);
        }
    }

    private boolean ValidaJogada(Map<String, String> pacote) {
        String linha = pacote.get("linha");
        String coluna = pacote.get("coluna");
        String botao = linha + coluna;

        return switch (botao) {
            case "11" -> Objects.equals(button11.getText(), "-");
            case "12" -> Objects.equals(button12.getText(), "-");
            case "13" -> Objects.equals(button13.getText(), "-");
            case "21" -> Objects.equals(button21.getText(), "-");
            case "22" -> Objects.equals(button22.getText(), "-");
            case "23" -> Objects.equals(button23.getText(), "-");
            case "31" -> Objects.equals(button31.getText(), "-");
            case "32" -> Objects.equals(button32.getText(), "-");
            case "33" -> Objects.equals(button33.getText(), "-");
            default -> false;
        };
    }

    private void FazJogada(Map<String, String> pacote) {
        if (ValidaJogada(pacote)) {
            try {
                Jogador2.EnviaMapPacote(pacote);
                MarcaJogada(pacote);
                DesabilitaHabilitaBotoes(false);
                lblAviso.setText("Espere sua vez novamente!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error:  A Jogada é inválida!");
        }
    }
    public void mostraMensagem(Map<String,String> pacote) {
        String aviso = pacote.get("aviso");
        if (Objects.equals(aviso, "Você Ganhou!")) {
            lblAviso.setText("Você Venceu!!!");
            lblAviso.setForeground(Color.GREEN);
        } else if (Objects.equals(aviso, "Você Perdeu!")) {
            lblAviso.setText("Você Perdeu!");
            lblAviso.setForeground(Color.RED);
        } else if (Objects.equals(aviso, "Deu Velha!")){
            lblAviso.setText("Ih, Deu Velha!");
            lblAviso.setForeground(Color.MAGENTA);
        } else if (Objects.equals(aviso, "ERRO")) {
            JOptionPane.showMessageDialog(null, "Vish, Deu algum Erro.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogador 2");
        frame.setContentPane(new Player02().panelP2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void TempPrintPlayer(Map<String, String> p){
        System.out.println(p.get("linha")+","+p.get("coluna") + " / " + QualSimbolo(p) + "\taviso: " + p.get("aviso"));
    }

    private String QualSimbolo(Map<String, String> p){
        if(Objects.equals(p.get("simbolo"), "1")){
            return "X";
        }else if(Objects.equals(p.get("simbolo"), "-1")){
            return "O";
        }
        return "-";
    }

}
