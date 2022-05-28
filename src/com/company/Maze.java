package com.company;


public class Maze {
    char[][] maze;
    int x;
    int y;
    int lastX;
    int lastY;
    int xFinish;
    int yFinish;
    char start;
    char finish;
    boolean viewMoves = false;


    public Maze(char[][] maze, int xStart, int yStart, int xFinish, int yFinish) {
        this.maze = maze;
        this.x = xStart;
        this.y = yStart;
        this.xFinish = xFinish;
        this.yFinish = yFinish;
    }




    public Maze(char[][] maze, char start, char finish) {
        this.maze = maze;
        this.start = start;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == start)setXY(i,j);
                if (maze[i][j] == finish) {
                    setXfinish(i);
                    setYfinish(j);
                }

            }
        }
        this.finish = finish;
    }


    public void viewMoves(boolean viewMoves) {
        this.viewMoves = viewMoves;
    }

    public char[][] getMaze() {
        return maze;
    }


    public int getxFinish() {
        return xFinish;
    }


    public int getyFinish() {
        return yFinish;
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

    public void  setXY(int x, int y){
        setX(x);
        setY(y);
    }
    public void setXfinish(int xFinish) {
        this.xFinish = xFinish;
    }

    public void setYfinish(int yFinish) {
        this.yFinish = yFinish;
    }

    public void print() {
        if (viewMoves) {
            for (char[] chars : maze) {
                for (char aChar : chars) {
                    System.out.print(aChar + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public boolean search(char c, char c1, int x, int y) {

        if (safe(x) && safe(y) && available(x, y, c)) {
            plotPos(x, y, c1);
            return true;
        }

        if (safe(y + 1) && available(x, y + 1, c)) {
            plotPos(x, y + 1, c1);
            return true;

        }

        if (safe(y - 1) && available(x, y - 1, c)) {
            plotPos(x, y - 1, c1);
            return true;

        }

        if (safe(x - 1) && available(x - 1, y, c)) {
            plotPos(x - 1, y, c1);

            return true;
        }


        if (safe(x + 1) && available(x + 1, y, c)) {
            plotPos(x + 1, y, c1);

            return true;

        }


        return false;
    }

    public void backTrack(char c, int x, int y) {

        if (safe(x) && safe(y) && available(x, y, c)) {
            setX(x);
            setY(y);
            return;
        }

        if (safe(y + 1) && available(x, y + 1, c)) {
            setX(x);
            setY(y + 1);
            return;

        }

        if (safe(y - 1) && available(x, y - 1, c)) {
            setX(x);
            setY(y - 1);
            return;

        }

        if (safe(x - 1) && available(x - 1, y, c)) {
            setX(x - 1);
            setY(y);

            return;
        }


        if (safe(x + 1) && available(x + 1, y, c)) {
            setX(x + 1);
            setY(y);

        }


    }

    public int movement(char c, int x, int y) {
        int counter = 0;
        if (safe(x) && safe(y) && available(x, y, c)) {
            counter++;
        }

        if (safe(y + 1) && available(x, y + 1, c)) {
            counter++;

        }
        if (safe(y - 1) && available(x, y - 1, c)) {
            counter++;

        }
        if (safe(x + 1) && available(x + 1, y, c)) {
            counter++;

        }
        if (safe(x - 1) && available(x - 1, y, c)) {
            counter++;
        }

        return counter;
    }


    public boolean safe(int pos) {
        return pos >= 0 && pos < getMaze().length;
    }

    public boolean available(int x, int y, char c) {
        return maze[x][y] == c || maze[x][y] == finish;
    }

    public void runMaze() {

            while (done() && movement('.', getX(), getY()) > 0) {
                if (movement('.', getX(), getY()) > 1) {
                    lastY = getY();
                    lastX = getX();
                }

                search('.', 'x', getX(), getY());
                print();
                while (done() && movement('.',  getX(), getY()) == 0 ) {
                    backTrack('x', getX(), getY());
                    if (!search('.', 'x', getX(), getY())) {
                        if (movement('x', getX(), getY()) > 0) {
                            search('x', 'o', getX(), getY());
                        } else {
                            setXY(lastX,lastY);
                        }
                        print();
                    }
                }
            }
            viewMoves = true;
            print();
}

    public void plotPos(int x, int y, char c) {
        maze[x][y] = c;
        setXY(x,y);
    }

    public void getPos() {
        System.out.println(getX() + "," + getY());
    }

    public void clearDeadEnds(){
        for (char[] chars :maze) {
            for (int i = 0; i < maze.length; i++) {
                if (chars[i] == 'o') chars[i] ='.';

            }
        }

    }

    public boolean done() {

        if (maze[getX()][getY()] == maze[getxFinish()][getyFinish()]) {
            maze[getxFinish()][getyFinish()]= finish;
            clearDeadEnds();
            return false;
        }

        for (char[] chars : maze) {
            for (int j = 0; j < maze.length; j++) {
                if (chars[j] == '.') return true;
            }
        }

        return false;
    }



}
