package com.lancewer.interestingQuestions;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * User: Lancewer
 * Date: 12-12-26
 * Time: 下午11:45
 * Version: 1
 * Description: This code is to solve the famous hanoiDisk question with recursive
 */

class Disk {
    public int size;

    public Disk(int size) {
        this.size = size;
    }
}

/**
 * a simple queue interface
 */
interface Queue {
    /**
     * pop out the outer disk
     *
     * @return the disk object
     */
    Disk pop();

    /**
     * push a disk into the pole
     *
     * @param disk  the disk to insert
     */
    void push(Disk disk);
}

class Pole implements Queue {
    final int poleId;
    int containSize;
    int index = 0;
    private Disk[] disks;

    public Pole(int poleId, int containSize) {
        this.containSize = containSize;
        disks = new Disk[containSize];
        this.poleId = poleId;
    }

    @Override
    public Disk pop() {
        Disk toReturn;
        if (index > 0) {
            toReturn = disks[index - 1];
            disks[index - 1] = null;
            index--;
        } else {
            return null;
        }

        return toReturn;
    }

    @Override
    public void push(Disk disk) {
        disks[index] = disk;
        index++;
    }

//    public int currentSize() {
//        return index;
//    }

    /**
     * Show all the disks on a pole
     */
    public void showDisks() {
        if (index == 0) {
            System.out.println("Pole " + poleId + " is empty");
        } else {
            StringBuilder diskInfo = new StringBuilder();
            for(int i = 0; i < index; i++){
                diskInfo.append(disks[i].size).append(" ");
            }
            System.out.println("Disks on pole " + poleId + ":" + diskInfo.toString());
        }
    }
}

public class HanoiDisk {
    public static Pole pole1, pole2, pole3;
    private static int numberOfDisks;
    //Total move to solve this problem;
    static int totalMove = 0;

    public static void main(String[] args) {
        System.out.print("The biggest disk size is:");
        Scanner scanner = new Scanner(System.in);
        numberOfDisks = scanner.nextInt();
        initGame();
        sovle(numberOfDisks, 1, 3);
        //Show the result
        System.out.println(String.format("Solve this question used %d moves in total.", totalMove));
        System.out.println("****************Result**************");
        pole1.showDisks();
        pole2.showDisks();
        pole3.showDisks();
    }

    private static void sovle(int diskNum, int from, int to) {
        if(diskNum == 2){
            moveDisk(getPoleById(from), getPoleById(6-from-to)); // move disk 1 to middle pole
            moveDisk(getPoleById(from), getPoleById(to)); //move disk 2 to destination pole
            moveDisk(getPoleById(6-from-to), getPoleById(to));  //move disk 1 to destination pole
        }else{
            sovle(diskNum - 1, from, 6 - (from + to));
            moveDisk(getPoleById(from), getPoleById(to));
            sovle(diskNum - 1, 6 - (from + to), to);
        }
    }

    private static void moveDisk(Queue from, Queue to){
        to.push(from.pop());
        totalMove++;
    }

    private static Pole getPoleById(int id){
        switch (id){
            case 1:
                return pole1;
            case 2:
                return pole2;
            default:
                return pole3;
        }
    }

    /**
     * init the game
     */
    private static void initGame() {
        pole1 = new Pole(1, numberOfDisks);
        pole2 = new Pole(2, numberOfDisks);
        pole3 = new Pole(3, numberOfDisks);
        initPole1();
        pole1.showDisks();
        pole2.showDisks();
        pole3.showDisks();
        System.out.println("\npoles initialized");
    }

    private static void initPole1() {
        for(int i = numberOfDisks; i > 0; i--){
            pole1.push(new Disk(i));
        }
    }
}
