package com.company;

import java.util.ArrayList;


public class Maze {
    char[][] maze;
    int x;
    int y;
    int initialX = getX();
    int initialY = getY();
    int lastX = 0;
    int lastY = 0;
    int counter = 0;
    public Maze(char[][] maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;

    }

    public char[][] getMaze() {
        return maze;
    }

    public void setMaze(char[][] maze) {
        this.maze = maze;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void print() {
        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println();

    }

    public boolean search(char c, char c1, char[][] maze, int x, int y) {

        if (safe(x) && safe(y) && available(x, y, c, maze)) {
            plotPos(x, y, c1, maze);

            return true;
        }

        if (safe(y + 1) && available(x, y + 1, c, maze)) {
            plotPos(x, y + 1, c1, maze);

            return true;

        }

        if (safe(y - 1) && available(x, y - 1, c, maze)) {
            plotPos(x, y - 1, c1, maze);

            return true;

        }

        if (safe(x - 1) && available(x - 1, y, c, maze)) {
            plotPos(x - 1, y, c1, maze);

            return true;
        }


        if (safe(x + 1) && available(x + 1, y, c, maze)) {
            plotPos(x + 1, y, c1, maze);

            return true;

        }


        return false;
    }

    public boolean backTrack(char c, char[][] maze, int x, int y) {

        if (safe(x) && safe(y) && available(x, y, c, maze)) {
            setX(x);
            setY(y);
            return true;
        }

        if (safe(y + 1) && available(x, y + 1, c, maze)) {
            setX(x);
            setY(y + 1);
            return true;

        }

        if (safe(y - 1) && available(x, y - 1, c, maze)) {
            setX(x);
            setY(y - 1);
            return true;

        }

        if (safe(x - 1) && available(x - 1, y, c, maze)) {
            setX(x - 1);
            setY(y);

            return true;
        }


        if (safe(x + 1) && available(x + 1, y, c, maze)) {
            setX(x + 1);
            setY(y);
            return true;

        }


        return false;
    }

    public int movement(char c, char c1, char[][] maze, int x, int y) {
        int counter = 0;
        if (safe(x) && safe(y) && available(x, y, c, maze)) {
            counter++;
        }

        if (safe(y + 1) && available(x, y + 1, c, maze)) {
            counter++;

        }
        if (safe(y - 1) && available(x, y - 1, c, maze)) {
            counter++;

        }
        if (safe(x + 1) && available(x + 1, y, c, maze)) {
            counter++;

        }
        if (safe(x - 1) && available(x - 1, y, c, maze)) {
            counter++;
        }

        return counter;
    }

    public void print(char[][] maze) {
        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    public boolean safe(int pos) {
        return pos >= 0 && pos < getMaze().length;
    }

    public boolean available(int x, int y, char c, char[][] maze) {

        return maze[x][y] == c;
    }

    public void runMaze() {
        initialX = getX();
        initialY = getY();
        int tempX;
        int tempY;




            while (movement('.', 'x', maze, getX(), getY()) > 0) {
                if (movement('.', 'x', maze, getX(), getY()) > 1) {
                    lastY = getY();
                    lastX = getX();
                }

                search('.', 'x', maze, getX(), getY());
                print();

                while (!done(initialX,initialY) && movement('.', 'x', maze, getX(), getY()) == 0 ) {

                    backTrack('x', maze, getX(), getY());
                    if (!search('.', 'x', maze, getX(), getY())) {
                        if (movement('x', 'o', maze, getX(), getY()) > 0) {
                            search('x', 'o', maze, getX(), getY());
                        } else {
                            setX(lastX);
                            setY(lastY);
                        }
                        print();
                    }
                }
            }

}

    public void plotPos(int x, int y, char c, char[][] maze) {
        maze[x][y] = c;
        setX(x);
        setY(y);
    }


    public void getPos() {
        System.out.println(getX() + "," + getY());
    }

    public boolean done(int initialX, int initialY) {

        if (getX() == initialX && getY() == initialY) return  true;

        if (getX() == 0 || getX() == maze.length - 1
                || getY() == 0 || getY() == maze.length - 1) return true;


        for (char[] chars : maze) {
            for (int j = 0; j < maze.length; j++) {
                if (chars[j] == '.') return false;
            }
        }




        return  true;
    }


}
