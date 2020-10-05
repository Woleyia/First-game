package Cyq;


import java.util.LinkedList;

/*
 创建角色，每个角色由多个节点组成
 */
public class Ren {
    //角色右
    private LinkedList<Node> body;
    private static final int body_num = 73;
    private LinkedList<Node> body_blood;
    private int blood = 1;
    //角色左
    private LinkedList<Node> body2;
    private static final int body2_num = 53;
    private LinkedList<Node> body2_blood;
    //默认不运动
    private Direction direction = Direction.z;
    //角色是否活着
    private boolean isLiving1 = true;//右
    private boolean isLiving2 = true;//左


    public Ren() {
        //初始化人物
        initRen();
    }
    //初始化人物
    private void initRen() {
        body = new LinkedList<>();//创建集合
        body.add(new Node(78,42));//创建节点添加到集合中
        addc();
        body_blood = new LinkedList<>();
        for(int i = 1;i <= 15;i++)
        {body_blood.add(new Node(83 - i,1));}


        body2 = new LinkedList<>();
        body2.add(new Node(10,43));
        adds();
        //初始化血条
        body2_blood = new LinkedList<>();
        for(int i = 1;i <= 15;i++)
        {body2_blood.add(new Node(5 + i,1));}

    }

    //人会移动
    public void move()
    {
        if(isLiving1)
        {

            switch (direction) {
                case LEFT:

                    Delete(body,body_num);
                    move_0(body,1);
                    addc();//在原点周围画出火柴人
                    direction = Direction.z;
                    break;
                case RIGHT:
                    Delete(body,body_num);
                    move_0(body,-1);
                    addc();// 在原点周围画出火柴人
                    direction = Direction.z;
                    break;
                case Up:
                    Delete(body,9);
                    addc_weapon();
                    direction = Direction.z;
                    break;
                case DOWN:

                    direction = Direction.z;
                    break;
                case z:
                    Delete(body,body_num);
                    addc();// 在原点周围画出火柴人
                    break;

            }

        }

    }
    public void move2() {
        if (isLiving2) {

            switch (direction) {

                case A:
                    Delete(body2,body2_num);
                    move_0(body2,1);
                    adds();// 在原点周围画出火柴人
                    direction = Direction.z;
                    break;
                case D:
                    Delete(body2,body2_num);
                    move_0(body2,-1);
                    adds();
                    direction = Direction.z;
                    break;
                case S:
                    direction = Direction.z;
                    break;
                case W:
                    Delete(body2,9);
                    adds_weapon();
                    direction = Direction.z;
                    break;
                case z:
                    Delete(body2,body2_num);
                    adds();
                    direction = Direction.z;
                    break;

            }
        }
    }

    //生成角色和武器
    public void adds() {
        // 在原点周围画出火柴人
        Node sLeft0 = body2.get(0);//找出新原点
        //body2.add(new Node(sLeft2.getX(), sLeft2.getY() - 1));
        for(int i = 1;i <= 4;i++)
        {body2.add(new Node(sLeft0.getX() + (i - 4), sLeft0.getY() - 3));}
        for(int i = 1;i <= 6;i++)
        {body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() - 2));}
        for(int i = 1;i <= 6;i++)
        {
            if(i ==  3 || i == 5)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() - 1));
        }
        for(int i = 1;i <= 6;i++)
        {
            if(i ==  2 || i == 4 || i== 5)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY()));
        }
        for(int i = 1;i <= 6;i++)
        {
            if(i ==  3 || i == 4 || i == 5)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() + 1));
        }
        for(int i = 1;i <= 4;i++)
        {body2.add(new Node(sLeft0.getX() + (i - 4), sLeft0.getY() + 2));}
        for(int i = 1;i <= 7;i++)
        {
            if(i == 4)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() + 3));
        }
        for(int i = 1;i <= 6;i++)
        {
            if(i ==  3 || i == 4)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() + 4));
        }
        for(int i = 1;i <= 4;i++)
        {body2.add(new Node(sLeft0.getX() + (i - 4), sLeft0.getY() + 5));}
        body2.add(new Node(sLeft0.getX() - 2, sLeft0.getY() + 6));body2.add(new Node(sLeft0.getX(), sLeft0.getY() + 6));
        for(int i = 2;i <= 6;i++)
        {
            if(i ==  4)
            {continue;}
            body2.add(new Node(sLeft0.getX() + (i - 5), sLeft0.getY() + 7));
        }
        //武器
        for(int i = 0;i <= 6;i++)
        {body2.add(new Node(sLeft0.getX() + 3, sLeft0.getY() + (4 - i)));}
        body2.add(new Node(sLeft0.getX() + 2, sLeft0.getY() + 2));
        body2.add(new Node(sLeft0.getX() + 4, sLeft0.getY() + 2));


    }
    public void adds_weapon() {
        Node weapon2 = body2.get(0);//找出新原点
        for(int i = 0;i <= 6;i++)
        {body2.add(new Node(weapon2.getX() + 3 + i, weapon2.getY() + 3));}
        body2.add(new Node(weapon2.getX() + 4, weapon2.getY() + 2));
        body2.add(new Node(weapon2.getX() + 4, weapon2.getY() + 4));

    }
    public void addc() {
        // 在原点周围画出火柴人
        Node sLeft2 = body.get(0);//找出新原点
        body.add(new Node(sLeft2.getX() + 2, sLeft2.getY() - 8));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() - 8));
        body.add(new Node(sLeft2.getX() + 2, sLeft2.getY() - 7));
        body.add(new Node(sLeft2.getX() + 1, sLeft2.getY() - 6));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() - 6));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() - 5));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() - 5));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() - 4));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() - 4));
        for (int i = 1; i <= 10; i++) {
            body.add(new Node(sLeft2.getX() + (i - 4), sLeft2.getY() - 3));
        }
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() - 2));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() - 2));
        body.add(new Node(sLeft2.getX() + 2, sLeft2.getY() - 2));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() - 2));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() - 2));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() - 1));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() - 1));
        body.add(new Node(sLeft2.getX() + 5, sLeft2.getY() - 1));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY()));
        body.add(new Node(sLeft2.getX() + 2, sLeft2.getY()));
        body.add(new Node(sLeft2.getX() + 5, sLeft2.getY()));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() + 1));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 1));
        for (int i = 0; i < 5; i++) {
            body.add(new Node(sLeft2.getX() + i, sLeft2.getY() + 2));
        }
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() + 3));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() + 3));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 3));
        body.add(new Node(sLeft2.getX() + 5, sLeft2.getY() + 3));
        body.add(new Node(sLeft2.getX() - 2, sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX() + 5, sLeft2.getY() + 4));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() + 5));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 5));
        body.add(new Node(sLeft2.getX() - 1, sLeft2.getY() + 6));
        body.add(new Node(sLeft2.getX(), sLeft2.getY() + 6));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 6));
        body.add(new Node(sLeft2.getX() + 5, sLeft2.getY() + 6));
        for (int i = 0; i < 7; i++) {
            body.add(new Node(sLeft2.getX() + (i - 1), sLeft2.getY() + 7));
        }
        body.add(new Node(sLeft2.getX(), sLeft2.getY() + 8));
        body.add(new Node(sLeft2.getX() + 1, sLeft2.getY() + 8));
        body.add(new Node(sLeft2.getX() + 3, sLeft2.getY() + 8));
        body.add(new Node(sLeft2.getX() + 4, sLeft2.getY() + 8));
        //武器
        body.add(new Node(sLeft2.getX() -4, sLeft2.getY() -1));body.add(new Node(sLeft2.getX() -3, sLeft2.getY() -1));
        body.add(new Node(sLeft2.getX() -4, sLeft2.getY()));body.add(new Node(sLeft2.getX() -3, sLeft2.getY()));
        for(int i = 1;i <= 5;i++)
        { body.add(new Node(sLeft2.getX() -3, sLeft2.getY() +i));}
    }
    public void addc_weapon() {
        Node weapon1 = body.get(0);//找出新原点
        for (int i = 2; i <= 8; i++)
        {
            body.add(new Node(weapon1.getX() - i, weapon1.getY() + 4));
            if(i >= 7)
            {
                body.add(new Node(weapon1.getX() - i, weapon1.getY() + 5));
            }
        }
    }
    //删除原本除原节点外的其他节点，方便移动后重新生成
    public void Delete(LinkedList<Node> body,int l) {
            for (int i = 1; i <= l; i++)
            {
                body.removeLast();
            }
    }
    //角色移动
    public void move_0(LinkedList<Node> body,int l) {
        Node sLeft2 = body.get(0); //将body里的原点找出

        if( (!(sLeft2.getX() < 7) && !(sLeft2.getX() > 83)) || sLeft2.getX() + l == 5 ||sLeft2.getX() - l == 83)
        {
            if(!(sLeft2.getX() - l < 6 || sLeft2.getX() - l > 83)) {
                body.add(new Node(sLeft2.getX() - l, sLeft2.getY()));//移动原点
                body.remove(0);//删除原原点
            }
        }
    }
    public void Delete_blood(LinkedList<Node> body)
    {
        body.removeLast();
    }
    public LinkedList<Node> getBody() {
        return body;
    }
    public LinkedList<Node> getBody2() {
        return body2;
    }
    public LinkedList<Node> getBody_blood() {
        return body_blood;
    }
    public LinkedList<Node> getBody2_blood() {
        return body2_blood;
    }
    public void Die(int i)
    {
        if(i == 1)
        {
            isLiving1 = false;
            Delete(body,body_num);
        }
        else if(i == 2)
        {
            isLiving2 = false;
            Delete(body2,body2_num);
        }

    }
    public void settBody(LinkedList<Node> body) {
        this.body = body;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    //吃食物
    public void eat(Node food) {

    }

}
