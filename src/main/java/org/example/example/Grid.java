package org.example;

import java.util.*;
import java.lang.*;

public class Grid {
    private Map<CoordinateKey, String> grid = new LinkedHashMap<>();
    private int xCord;
    private int yCord;
    private int startX;
    private int startY;
    private int endX;
    private int endY;

    private boolean foundPath;

    private int helper;
    public Grid(int x, int y, int startX, int startY, int endX, int endY) {
        xCord = x;
        yCord = y;
        for (int i = 0; i < x; i++) {
            for (int b = 0; b < y; b++) {
                CoordinateKey coordinate = new CoordinateKey(i, b);
                grid.put(coordinate, "-");
            }
        }
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        this.setStart(startX, startY);
        this.setEnd(endX, endY);
    }

    public int getX(){
        return startX;
    }
    public int getY(){
        return startY;
    }

    public int getEndX(){
        return endX;
    }
    public int getEndY(){
        return endY;
    }

    public void setFound(){
        foundPath = false;
    }

    public void setObstacle(int x, int y){
        grid.replace(new CoordinateKey(x,y), "L");
    }

    public void printGrid() {
        for (int b = 0; b < yCord; b++) {
            for (int i = 0; i < xCord; i++) {
                CoordinateKey coordinate = new CoordinateKey(i, b);
                System.out.print(grid.get(coordinate) + " ");
            }
            System.out.println();
        }
    }

    public void printGridAsMap() {
        System.out.println(grid);
    }

    private void setStart(int x, int y) {
        CoordinateKey coordinate = new CoordinateKey(x, y);
        grid.replace(coordinate, "O");
    }

    private void setEnd(int x, int y) {
        CoordinateKey coordinate = new CoordinateKey(x, y);
        grid.replace(coordinate, "X");
    }

    public void printHelp(){
        System.out.println(helper);
    }
//PathFinding

    public double findDistance(int x1, int y1, int x2, int y2) {
        double x = x2 - x1;
        double y = y2 - y1;
        double result = Math.sqrt((Math.pow(x, 2) + (Math.pow(y, 2))));

        return result;
    }

    public boolean getShortestPath(int passedX, int passedY, int targetX, int targetY, int passedCount) {

        if (passedX == targetX && passedY == targetY) {
            if (targetX!=endX&&targetY!=endY){
                grid.replace(new CoordinateKey(passedX, passedY), "P");
            }
            foundPath = true;
            return true;
        } else if (passedCount >= xCord*yCord) {
            return false;
        }

        int futureX = passedX - 1;
        int futureY = passedY - 1;
        int counter = 0;

        double shortestPath = findDistance(futureX, futureY, targetX, targetY);
        int shortestX = 0;
        int shortestY = 0;


        for (int i = 0; i < 9; i++) {

            if (counter > 2) {
                futureX = passedX - 1;
                futureY++;
                counter = 0;
            }
            if (futureX < 0) {
                futureX++;
                counter++;
                continue;

            } else if (futureX >= xCord) {
                futureX--;
                counter++;
                continue;
            } else if (futureY < 0) {
                counter++;
                continue;
            } else if (futureY >= yCord) {
                counter++;
                continue;
            }
            double tempPath = findDistance(futureX, futureY, targetX, targetY);
            if (tempPath < shortestPath) {
                shortestPath = tempPath;
                shortestX = futureX;
                shortestY = futureY;
            }
            futureX++;
            counter++;
        }

        String closeCord = shortestX + "." + shortestY;

        if (grid.get(new CoordinateKey(shortestX,shortestY)).equals("L")){
            return false;
        } else  if (!(shortestX == targetX && shortestY == targetY)) {
            grid.replace(new CoordinateKey(shortestX, shortestY), "P");
            helper++;
        }

        passedCount++;
        return getShortestPath(shortestX, shortestY, targetX, targetY, passedCount);

    }

    public boolean getShortestPathWithout(int passedX, int passedY, int targetX, int targetY, int passedCount) {

        if (passedX == targetX && passedY == targetY) {
            foundPath = true;
            return true;
        } else if (passedCount >= xCord*yCord) {
            return false;
        }

        int futureX = passedX - 1;
        int futureY = passedY - 1;
        int counter = 0;

        double shortestPath = findDistance(futureX, futureY, targetX, targetY);
        int shortestX = 0;
        int shortestY = 0;


        for (int i = 0; i < 9; i++) {

            if (counter > 2) {
                futureX = passedX - 1;
                futureY++;
                counter = 0;
            }
            if (futureX < 0) {
                futureX++;
                counter++;
                continue;

            } else if (futureX >= xCord) {
                futureX--;
                counter++;
                continue;
            } else if (futureY < 0) {
                counter++;
                continue;
            } else if (futureY >= yCord) {
                counter++;
                continue;
            }
            double tempPath = findDistance(futureX, futureY, targetX, targetY);
            if (tempPath < shortestPath) {
                shortestPath = tempPath;
                shortestX = futureX;
                shortestY = futureY;
            }
            futureX++;
            counter++;
        }

        String closeCord = shortestX + "." + shortestY;

        if (grid.get(new CoordinateKey(shortestX,shortestY)).equals("L")){
            return false;
        }

        passedCount++;
        return getShortestPathWithout(shortestX, shortestY, targetX, targetY, passedCount);

    }


    public CoordinateKey getShortestWithO( Map<CoordinateKey, Double> closedList, Map<CoordinateKey, Double> priorityList, CoordinateKey bestNode, int x, int y, int passedCount, int checkCount) {
        if (passedCount >= xCord*yCord) {
            if (checkCount>0){
                startX = bestNode.getX();
                startY = bestNode.getY();
                System.out.println(priorityList);
                return bestNode;

            } else {

                Map.Entry<CoordinateKey, Double> firstEntry = closedList.entrySet().iterator().next();
                startX = firstEntry.getKey().getX();
                startY = firstEntry.getKey().getY();
            //
                CoordinateKey send = new CoordinateKey(startX,startY);
                System.out.println(send.getX() + " " + send.getY());
                System.out.println(closedList);
                return send;
            }

        }

        int futureX = x - 1;
        int futureY = y - 1;
        int counter = 0;
        System.out.println("here1");
        for (int i = 0; i < 9; i++) {
            if (counter > 2) {
                futureX = x - 1;
                futureY++;
                counter = 0;
            }
            if (futureX < 0) {
                futureX++;
                counter++;
                continue;

            } else if (futureX >= xCord) {
                futureX--;
                counter++;
                continue;
            } else if (futureY < 0) {
                counter++;
                continue;
            } else if (futureY >= yCord) {
                counter++;
                continue;
            } else if (closedList.containsKey(new CoordinateKey(futureX,futureY))){
                futureX++;
                counter++;
                continue;
            } else if (futureX==x && futureY==y){
                futureX++;
                counter++;
                continue;
            }

            double tempPath = findDistance(futureX, futureY, endX, endY);
            if (!grid.get(new CoordinateKey(futureX,futureY)).equals("L")){
               if (getShortestPathWithout(startX,startY,futureX,futureY,0)){
                   priorityList.put(new CoordinateKey(futureX, futureY), tempPath);
                   System.out.println("GOAL");
                   System.out.println(futureX);
                   System.out.println(futureY);
               }

            }
            futureX++;
            counter++;
        }
        System.out.println("here2");
        ArrayList<CoordinateKey> temp = new ArrayList<>();
        if (trace(traceCollect(startX,startY,x,y,0,temp),x,y)) {
            if (findDistance(x, y, endX, endY) < findDistance(bestNode.getX(), bestNode.getY(), endX, endY)) {
                bestNode = new CoordinateKey(x, y);
                checkCount++;
                System.out.println("I HATE THIS");
            }
        }

        System.out.println("here3");
        Map<CoordinateKey, Double> sortedMap = sortedMapUtils(priorityList);

        closedList.put(new CoordinateKey(x, y), findDistance(x, y, endX, endY));

        Map<CoordinateKey, Double> sortedClosedMap = sortedMapUtils(closedList);
        Map.Entry<CoordinateKey, Double> firstEntry = null;
        if (!sortedMap.isEmpty() && sortedMap.size()>1){
            sortedMap.remove(new CoordinateKey(x, y));
            firstEntry = sortedMap.entrySet().iterator().next();
        } else {
            Map.Entry<CoordinateKey, Double> lastEntry = null;
            Iterator<Map.Entry<CoordinateKey, Double>> iterator = closedList.entrySet().iterator();
            while (iterator.hasNext()) {
                lastEntry = iterator.next();
            }
            firstEntry = lastEntry;
        }




        passedCount++;

        System.out.println("here4");
        System.out.println(passedCount);
        printGrid();
        return getShortestWithO(sortedClosedMap, sortedMap, bestNode, firstEntry.getKey().getX(), firstEntry.getKey().getY(), passedCount, checkCount);


    }

    public void printEndCoords() {
        System.out.println(endX + "," + endY);
    }

    public void printStartCoords() {
        System.out.println(startX + "," + startY);
    }


    public static Map<CoordinateKey, Double> sortedMapUtils(Map<CoordinateKey, Double> map) {
        List<Map.Entry<CoordinateKey, Double>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        Map<CoordinateKey, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<CoordinateKey, Double> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    public ArrayList<CoordinateKey> traceCollect(int passedX, int passedY, int targetX, int targetY, int passedCount, ArrayList<CoordinateKey> path) {

        if (passedX == targetX && passedY == targetY) {
                return path;
        } else if (passedCount >= xCord*yCord) {
            return null;
        }

        int futureX = passedX - 1;
        int futureY = passedY - 1;
        int counter = 0;

        double shortestPath = findDistance(futureX, futureY, targetX, targetY);
        int shortestX = 0;
        int shortestY = 0;


        for (int i = 0; i < 9; i++) {

            if (counter > 2) {
                futureX = passedX - 1;
                futureY++;
                counter = 0;
            }
            if (futureX < 0) {
                futureX++;
                counter++;
                continue;

            } else if (futureX >= xCord) {
                futureX--;
                counter++;
                continue;
            } else if (futureY < 0) {
                counter++;
                continue;
            } else if (futureY >= yCord) {
                counter++;
                continue;
            }
            double tempPath = findDistance(futureX, futureY, targetX, targetY);
            if (tempPath < shortestPath) {
                shortestPath = tempPath;
                shortestX = futureX;
                shortestY = futureY;
            }
            futureX++;
            counter++;
        }

        String closeCord = shortestX + "." + shortestY;

        if (grid.get(new CoordinateKey(shortestX,shortestY)).equals("L")){
            ArrayList<CoordinateKey> empty = new ArrayList<>();
            return empty;
        }
        path.add(new CoordinateKey(shortestX-passedX, shortestY-passedY));
        passedCount++;
        return traceCollect(shortestX, shortestY, targetX, targetY, passedCount, path);

    }

    public boolean trace(ArrayList<CoordinateKey> path, int passedX, int passedY){
        if (!path.isEmpty()){
            for (int i = 0; i < xCord; i++){
                for (CoordinateKey slope : path){
                    if (passedX>=0 && passedX<xCord && passedY>=0 && passedY<yCord){
                        passedX+=slope.getX();
                        passedY+=slope.getY();
                        //check if can make straight line to target
                        System.out.println("HURRAY");
                        System.out.println(passedX);
                        System.out.println(passedY);
                        if (passedX>=0 && passedX<xCord && passedY>=0 && passedY<yCord){
                            if (grid.get(new CoordinateKey(passedX,passedY)).equals("L")){
                                return false;
                            }
                        }

                    }
                }

            }
            for (CoordinateKey slope : path){
            }
        }

        return true;
    }


    public boolean getFoundPath(){
        return foundPath;
    }

}
/*/
  public boolean tracePath(int x, int y) {
        int distanceX;
        int distanceY;
        if (startX == x && startY == y){
            return true;
        } else {
            distanceX = Math.abs(startX-x);
            distanceY = Math.abs(startY-y);
        }

       System.out.println(distanceX + "WHAT");
        System.out.println(distanceY+ "WHAT");

        try {
            int smallest = Math.min(distanceX, distanceY);
            System.out.println(smallest + "WORKNOW" );
            int slopeX = 0;
            int slopeY = 0;
            System.out.println(slopeX + "herething");
            System.out.println(slopeY + "herething");
            if (smallest != 0){
                slopeX = Math.round(distanceX / smallest);
                slopeY = Math.round(distanceY / smallest);
                System.out.println(slopeX + "SHOULDWORK");
                System.out.println(slopeY + "SHOULDWORK");
            } else if (smallest == distanceX){
                slopeX = 0;
                System.out.println("vasdf");
            } else if (smallest == distanceY){
                slopeY = 0;
                System.out.println("vasdf");
            }
            int futureX = startX;
            int futureY = startY;
            while (futureX >= 0 && futureX <= xCord - 1 && futureY >= 0 && futureY <= yCord - 1) {

                if (x > startX) {
                    for (int i = 0; i < slopeX; i++) {
                        futureX++;

                        String check = grid.get(new CoordinateKey(futureX, futureY));
                        if (check!=null){
                            if (check.equals("L")) {
                                System.out.println(futureX+ "AHH");
                                System.out.println(futureY+ "AHH");
                                return false;
                            }
                        }

                    }
                } else if (x < startX) {
                    for (int i = 0; i < slopeX; i++) {
                        futureX--;
                        String check = grid.get(new CoordinateKey(futureX, futureY));
                        if (check.equals("L")) {
                            System.out.println(futureX+ "AHH");
                            System.out.println(futureY+ "AHH");

                            return false;
                        }
                    }

                    if (y > startY) {
                        for (int i = 0; i < slopeY; i++) {
                            futureY++;
                            String check = grid.get(new CoordinateKey(futureX, futureY));
                            if (check.equals("L")) {
                                System.out.println(futureX+ "AHH");
                                System.out.println(futureY+ "AHH");

                                return false;
                            }
                        }
                    } else if (y < startY) {
                        for (int i = 0; i < slopeY; i++) {
                            futureY--;
                            String check = grid.get(new CoordinateKey(futureX, futureY));
                            if (check.equals("L")) {
                                System.out.println(futureX + "AHH");
                                System.out.println(futureY+ "AHH");

                                return false;
                            }
                        }

                    }



                }
            }
        } catch (ArithmeticException e){
            System.out.println("Division by zero: " + e.getMessage());
        }

        return true;
    }
 */