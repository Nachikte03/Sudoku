/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sudoku;

import java.util.ArrayList;

/**
 *
 * @author nachi
 */
public class CreateBoard {
    ArrayList []rows = new ArrayList[9];
    ArrayList []cols = new ArrayList[9];
    ArrayList [][] boxes = new ArrayList[3][3];
    int [][] solution = new int [9][9];
    int [][] board = new int [9][9];


    CreateBoard(){
        for(int i=0;i<9;i++){
            ArrayList temp = new ArrayList();
            rows[i]=temp;
            ArrayList temp1 = new ArrayList();
            cols[i]=temp1;
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ArrayList temp = new ArrayList();
                boxes[i][j]=temp;
            }

        }
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                board[i][j]=0;
            }
        }
        createGrid();
        generateSolution(0,0);
    }

    public boolean isValid(int i,int j,int num){
        if(rows[i].contains(num)) return false;
        if(cols[j].contains(num)) return false;
        else if(boxes[(int)(Math.floor((double)i/3))][(int)(Math.floor((double)j/3))].contains(num)) return false;
        return true;
    }
    public void addToGrid(int i,int j,int num){
        rows[i].add(num);
        cols[j].add(num);
        boxes[(int)(Math.floor((double)i/3))][(int)(Math.floor((double)j/3))].add(num);

    }
    public final void createGrid(){
        int count = 0;
        while(count!=20){
            int i = (int)(Math.random()*9);
            int j = (int)(Math.random()*9);
            int num = (int)(Math.random()*9)+1;
            if(board[i][j]==0 && isValid(i,j,num)){
                count++;
                addToGrid(i,j,num);
                board[i][j]=num;
                solution[i][j] = num;
            }
        }
    }

    public final boolean generateSolution(int i,int j){
        if(i==9) return true;
        if(solution[i][j]!=0){
            int x=i,y=j+1;
            if(y ==9){
                x=x+1;
                y=0;
            }
            return generateSolution(x,y);
        }
        boolean b = false;
        for(int num=1;num<=9;num++){
            if(isValid(i,j,num)){
                addToGrid(i,j,num);
                solution[i][j] =num;
                int x=i,y=j+1;
                if(y ==9){
                    x=i+1;
                    y=0;
                }
                b = b|| generateSolution(x,y);
                if(b) break;
                rows[i].remove(Integer.valueOf(num));
                cols[j].remove(Integer.valueOf(num));
                boxes[(int)(Math.floor((double)i/3))][(int)(Math.floor((double)j/3))].remove(Integer.valueOf(num));
                solution[i][j]=0;

            }
        }
        return b;
    }



    public static void main(String[] args) {
        CreateBoard b = new CreateBoard();
        b.createGrid();
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(b.board[i][j]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        b.generateSolution(0,0);
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                System.out.print(b.solution[i][j]+" ");
            }
            System.out.println("");
        }
    }
}
