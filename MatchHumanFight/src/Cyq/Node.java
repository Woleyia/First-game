package Cyq;

import java.util.Random;

/*
节点类  每个角色是由若干个节点组成  每个节点有横纵坐标确定位置
*/
public class Node {
    private int x,y;
    public Node()
    {

    }

    public Node(int x,int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    //随机生成位置
    public void random()
    {
        //创建一个random对象
        Random r = new Random();
        //随机生成横,纵坐标
        this.x = r.nextInt(40);
        this.y = r.nextInt(40);

    }
}

