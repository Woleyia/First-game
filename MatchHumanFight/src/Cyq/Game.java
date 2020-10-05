package Cyq;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class Game extends JFrame
{
    private Ren ren;//人物
    private Ren ren2;//人物
    private JPanel jPanel;//游戏界面
    private Timer timer;//定时器，指定时间执行指定任务
    private Node food;//食物
    private Direction direction = Direction.z;

    public Game() throws HeadlessException
    {
        //初始化窗体参数
        initFrame();
        //初始化游戏界面
        initGamePanel();
        //初始化人
        initRen();
        //初始化食物
        initFood();
        //初始化定时器
        initTimer();
        //设置键盘监听
        setKeyListener();
    }

    //初始化窗体参数
    private void initFrame()
    {
        setTitle("小蓝你听我解释");
        setSize(905,635);//宽高
        setLocation(350,140);//窗口位置，距离边框
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);//窗口大小不可变


    }
    //初始化游戏界面
    private void initGamePanel()
    {
        jPanel = new JPanel()
        {
            //绘制界面
            @Override
            public void paint(Graphics g)
            {
                g.clearRect(0,0,900,600);
               /* //绘制横线
                for(int i = 0;i < 60 ;i++)
                {
                    g.drawLine(0,i * 10,900,i * 10);
                }
                //绘制竖线
                for(int i = 0;i < 90 ;i++)
                {
                    g.drawLine(i * 10,0,i * 10,600);
                }*/
                //绘制基本地图
                for(int i = 0; i < 90;i++)
                {
                    g.fillRect(i * 10, 0, 10, 10);
                }
                for(int i = 1; i <= 5;i++)
                {
                    for(int j = 0;j <= 90;j++) {
                        if (j <= 5) {
                            g.setColor(Color.green);
                            g.fillRect(j * 10, i * 10, 10, 10);
                        }
                        if (j >= 83) {
                            g.setColor(Color.blue);
                            g.fillRect(j * 10, i * 10, 10, 10);
                        }
                        g.setColor(Color.red);
                        if(j >= 40 && j<=49 && (i == 1||i == 3))
                        {
                            if(j !=41 && j != 42 && j != 43 && j != 45)
                            {g.fillRect(j * 10, i * 10, 10, 10);}
                        }
                        else if(i == 2)
                        {
                            if(j ==40 || j == 44 || j == 46)
                            {g.fillRect(j * 10, i * 10, 10, 10);}
                        }
                        else if(i == 4)
                        {
                            if(j ==41 || j == 43 || j == 49)
                            {g.fillRect(j * 10, i * 10, 10, 10);}
                        }
                        else if(i == 5)
                        {
                            if(j ==42 || (j >= 46 && j <= 49))
                            {g.fillRect(j * 10, i * 10, 10, 10);}
                        }
                    }
                }
                g.setColor(Color.black);//绘制颜色变黑
                for(int i = 1; i <= 9;i++)
                {
                    for(int j = 0;j <= 90;j++)
                    {g.fillRect(i * 10 * j,  (50 + i)* 10, 10, 10);}

                }
                //绘制人
                LinkedList<Node> body = ren.getBody();
                for(Node node : body)
                {
                    g.setColor(Color.blue);
                    g.fillRect(node.getX() * 10,node.getY() * 10,10,10);
                }
                LinkedList<Node> body2 = ren2.getBody2();
                for(Node node : body2)
                {
                    g.setColor(Color.green);
                    g.fillRect(node.getX() * 10,node.getY() * 10,10,10);
                }
                g.setColor(Color.red);//绘制颜色变红
                //绘制血条
                LinkedList<Node> blood = ren.getBody_blood();
                for(Node node : blood)
                {
                    g.fillRect(node.getX() * 10,node.getY() * 10,10,30);
                }
                LinkedList<Node> blood2 = ren2.getBody2_blood();
                for(Node node : blood2)
                {
                    g.fillRect(node.getX() * 10,node.getY() * 10,10,30);
                }


                //绘制蓝点
                //g.fillRect(food.getX() * 15, food.getY() * 15, 15,15);
            }

        };

        //把界面添加进窗体
        add(jPanel);
    }

    //初始化人
    private void initRen()
    {
        ren = new Ren();
        ren2 = new Ren();

    }
    //初始化食物
    private void initFood()
    {
        food = new Node();
        food.random();
    }
    //初始化定时器
    private void initTimer()
    {
        timer = new Timer();
        //初始化定时任务
        TimerTask timerTask = new TimerTask()
        {
            @Override
            public void run() {
                ren.move();
                ren2.move2();
              /*  //判断蛇头是否和食物重合
                Node head = ren.getBody().getFirst();
                if(head.getX() == food.getX() && head.getY() == food.getY())
                {
                    ren.eat(food);
                    food.random();
                }*/
                Node x = ren.getBody().getFirst();
                Node y = ren2.getBody2().getFirst();
                if(x.getX() - y.getX() <= 11 && direction == Direction.Up) {
                    attack2(ren2,53,6);
                }
                if(x.getX() - y.getX() <= 11 && direction == Direction.W) {
                    attack(ren,73,-6);
                }
                if(ren2.getBody2_blood().size() == 0 )
                {
                    ren2.Die(2);
                    JOptionPane.showMessageDialog(null, "解释就是掩饰，去死吧", "小蓝胜利",
                            JOptionPane.PLAIN_MESSAGE);
                    timer.cancel();
                }
                else  if(ren.getBody_blood().size() == 0)
                {
                    ren.Die(1);
                    JOptionPane.showMessageDialog(null, "让你听我解释，非得我动手", "小绿胜利",
                            JOptionPane.PLAIN_MESSAGE);
                    timer.cancel();
                }
                direction = Direction.z;
                //重绘游戏界面
                jPanel.repaint();
            }
        };

        //每100毫秒执行一次定时任务
        timer.scheduleAtFixedRate(timerTask,0,100);

    }
    //设置键盘监听
    private void setKeyListener()
    {
        addKeyListener(new KeyAdapter()
        {
            //键盘按下时自动处理
            @Override
            public void keyPressed(KeyEvent e)
            {
                switch (e.getExtendedKeyCode())
                {
                    case KeyEvent.VK_UP:
                        //修改蛇的运动方向
                        direction = Direction.Up;
                        ren.setDirection(Direction.Up);
                        break;
                    case KeyEvent.VK_LEFT:
                        ren.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_DOWN:
                        ren.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                        ren.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_A:
                        ren2.setDirection(Direction.A);
                        break;
                    case KeyEvent.VK_D:
                        ren2.setDirection(Direction.D);
                        break;
                    case KeyEvent.VK_W:
                        direction = Direction.W;
                        ren2.setDirection(Direction.W);
                        break;
                }
            }
        });
    }
    private void attack(Ren x,int i,int z)
    {
        x.Delete_blood(x.getBody_blood());//x血量减少
        x.Delete(x.getBody(),i);
        x.move_0(x.getBody(),z);
        x.addc();
        direction = Direction.z;
    }
    private void attack2(Ren x,int i,int z)
    {
        x.Delete_blood(x.getBody2_blood());//x血量减少
        x.Delete(x.getBody2(),i);
        x.move_0(x.getBody2(),z);
        x.adds();
        direction = Direction.z;
    }
    public static void main(String[] args)
    {
        //创建窗体对象并显示
        new Game();

    }

}
