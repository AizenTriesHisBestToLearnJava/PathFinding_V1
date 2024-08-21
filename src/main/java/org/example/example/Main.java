package org.example;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("Hello World");

      Grid map = new Grid(25,25,0,12,24,12);
        map.setObstacle(12,12);
        map.setObstacle(11,12);
        map.setObstacle(10,12);
        map.setObstacle(9,12);
        map.setObstacle(8,12);
        map.setObstacle(7,12);
        map.setObstacle(6,12);
        map.setObstacle(5,12);
        map.setObstacle(4,12);
        map.setObstacle(3,12);
        map.setObstacle(2,12);
        map.setObstacle(12,12);
        map.setObstacle(12,7);
        map.setObstacle(12,7);
        map.setObstacle(12,7);
        map.setObstacle(11,7);
        map.setObstacle(8,7);
        map.setObstacle(7,7);
        map.setObstacle(6,7);
        map.setObstacle(5,7);
        map.setObstacle(4,7);
        map.setObstacle(3,7);
        map.setObstacle(12,12);
        map.setObstacle(12,13);
        map.setObstacle(12,11);
        map.setObstacle(12,14);
        map.setObstacle(12,10);
        map.setObstacle(12,15);
        map.setObstacle(12,16);
        map.setObstacle(13,16);
        map.setObstacle(14,16);
        map.setObstacle(15,16);
        map.setObstacle(16,16);
        map.setObstacle(17,16);
        map.setObstacle(19,16);
        map.setObstacle(20,16);
        map.setObstacle(21,16);
        map.setObstacle(22,16);
        map.setObstacle(23,16);
        map.setObstacle(24,16);
        map.setObstacle(12,17);
        map.setObstacle(12,18);
        map.setObstacle(12,19);
        map.setObstacle(12,21);
        map.setObstacle(12,23);
        map.setObstacle(12,20);
        map.setObstacle(12,21);
        map.setObstacle(12,22);
        map.setObstacle(12,23);
        map.setObstacle(12,16);
        map.setObstacle(12,15);
        map.setObstacle(12,14);
        map.setObstacle(12,13);
        map.setObstacle(12,12);
        map.setObstacle(12,11);
        map.setObstacle(12,10);
        map.setObstacle(12,9);
        map.setObstacle(12,8);
        map.setObstacle(12,7);
        map.setObstacle(12,6);
        map.setObstacle(12,5);
        map.setObstacle(12,4);
        map.setObstacle(12,3);
        map.setObstacle(12,2);
        map.setObstacle(11,10);
        map.setObstacle(10,10);
        map.setObstacle(9,10);
        map.setObstacle(8,10);
        map.setObstacle(7,10);
        map.setObstacle(6,10);
        map.setObstacle(5,10);
        map.setObstacle(11,14);
        map.setObstacle(10,14);
        map.setObstacle(9,14);
       // map.setObstacle(8,14);


        map.printGrid();
        System.out.println();

        while (!map.getShortestPathWithout(map.getX(), map.getY(), map.getEndX(),map.getEndY(),0)){
            Map<CoordinateKey, Double> closed = new LinkedHashMap<>();
            Map<CoordinateKey, Double> priorityList = new LinkedHashMap<>();
            CoordinateKey node = new CoordinateKey(map.getX(), map.getY());
            boolean found = false;
            System.out.println("now");
            System.out.println(map.getX());
            System.out.println(map.getY());
            map.printGrid();
            while (!found){
                map.printGrid();
                int previousX = map.getX();
                int previousY = map.getY();
                CoordinateKey subTarget = map.getShortestWithO(closed, priorityList, node, map.getX(), map.getY(), 0,0);
                System.out.println(subTarget.getX() + " " + subTarget.getY());
                map.printGrid();
                found = map.getShortestPath(previousX,previousY,subTarget.getX(),subTarget.getY(),0);
                System.out.println(map.getX());
                System.out.println(map.getY());
                System.out.println(subTarget.getX());
                System.out.println(subTarget.getY());
                System.out.println("here69");


            }



            map.setFound();

        }
       boolean lastPath = map.getShortestPath(map.getX(), map.getY(), map.getEndX(),map.getEndY(),0);
        System.out.println();
        map.printGrid();
        map.printEndCoords();
        map.printStartCoords();
        map.printHelp();
    }
     }

