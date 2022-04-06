package com.townsend.class1.class1;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Networking extends Application
{
    int currentRow;
    int currentColumn;
    int xs=0;
    int os=0;
    int boardWidth =3;
    Button board[][];
    Button clear = new Button("Clear");
    public int getStringCount(String s)
    {
    int count = 0;
        for(int i =0; i<9;i++) {
            currentColumn = i / boardWidth;
            currentRow = i % boardWidth;

            if (board[currentColumn][currentRow].getText().equalsIgnoreCase(s)) {
                count++;
            }
        }
        return count;
    }
    @Override
    public void start(Stage stage) throws IOException
    { GridPane clientTab = new GridPane();
        GridPane ServerTab = new GridPane();
        TabPane tabs = new TabPane();
        Scene scene = new Scene(tabs, 320, 240);
        EventHandler<ActionEvent> GameEvent = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                Object Control = event.getSource();

                xs = getStringCount("X");
                os = getStringCount("O");

                for(int i =0; i<9;i++)
                {
                    currentColumn = i/boardWidth;
                    currentRow  =  i%boardWidth;

                    if(Control == board[currentColumn][currentRow] && board[currentColumn][currentRow].getText().equalsIgnoreCase(""))
                    {

                        if(xs>os)
                        {
                            board[currentColumn][currentRow].setText("O");
                            System.out.println(" ("+currentColumn+", "+currentRow+")");
                        }else if(xs ==0)
                        {
                            board[currentColumn][currentRow].setText("X");
                            System.out.println(" ("+currentColumn+","+currentRow+")");
                        }
                        else {
                            board[currentColumn][currentRow].setText("X");
                            System.out.println(" ("+currentColumn+", "+currentRow+")");
                        }
                    }


                }
                xs =0;
                os=0;
                if(Control == clear)
                {

                    for(int i =0; i<9;i++)
                    {
                        currentColumn = i/boardWidth;
                        currentRow  =  i%boardWidth;

                        board[currentColumn][currentRow].setText("");

                    }
                    xs =0;
                    os=0;

                }

            }
        };
        board = new Button[][]
                {
                        {new Button(),new Button(),new Button()},
                        {new Button(),new Button(),new Button()},
                        {new Button(),new Button(),new Button()},
                };
        clear.setOnAction(GameEvent);
        for(int i = 0; i<boardWidth*boardWidth; i++)
        {
            int column = i%3;
            int row = i/3;
            board[column][row].setText("");
            clientTab.add(board[column][row],column,row);
            board[column][row].setOnAction(GameEvent);
        }

        for (int i = 0; i<5; i++)
        {
            clientTab.getRowConstraints().add(new RowConstraints(100));
            clientTab.getColumnConstraints().add(new ColumnConstraints(100));
        }
        clientTab.add(clear, 3,0);



        //Using TCP


        Tab clientSide = new Tab("Client", clientTab);
        Tab serverSide = new Tab("Server", ServerTab);
        tabs.getTabs().addAll(clientSide,serverSide);

       // ServerSocket serverSocket= new ServerSocket(8000);
       // Socket socket = serverSocket.accept();

        /* Client Side
          *  Socket socket = new Socket(71.40.176.167, 8000);
          * serverHost is Host IP
         */
        //Now Linked


       // System.out.println(socket.toString());

        stage.setWidth(600);
        stage.setHeight(600);
        stage.setTitle("Networking App");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
/*
class Main implements ActionListener
{
    int currentRow;
    int currentColumn;
    int xs=0;
    int os=0;
    int boardWidth =3;
    Frame main = new Frame("ButtRock");
    Panel mPanel = (Panel)main.getContentPane();
    Panel clearPanel;
    Button clear = new Button("Clear");
    Button[][] board;

    //Start new non-static context
    public static void main(String[] args){new Main();}

    Main()
    {
        mPanel.setLayout(new GridLayout(4,4));
        clearPanel = new JPanel(new GridLayout(3,1));
        clearPanel.add(new JLabel(""));
        clearPanel.add(clear);
        clear.addActionListener(this);
        clearPanel.add(new JLabel(""));
        board = new JButton[][]
                {
                        {new JButton(),new JButton(),new JButton()},
                        {new JButton(),new JButton(),new JButton()},
                        {new JButton(),new JButton(),new JButton()},
                };
//Init
        mPanel.add(new JLabel(" "));
        mPanel.add(new JLabel("Tic-Tac-Toe"));
        mPanel.add(new JLabel(" "));
        mPanel.add(new JLabel(" "));
//Init
        for(int i =0;i<9; i++)
        {
            currentColumn = i/boardWidth;
            currentRow  =  i%boardWidth;
            if(i==0)
            {
                mPanel.add(clearPanel);
            }
            else if(i%3==0)
            {
                mPanel.add(new JLabel(" "));
            }
            mPanel.add(board[currentColumn][currentRow]);
            System.out.println();

            board[currentColumn][currentRow].addActionListener(this);
        }

        main.setSize(960,720);
        main.setVisible(true);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public int getXs()
    {

        for(int i =0; i<9;i++) {
            currentColumn = i / boardWidth;
            currentRow = i % boardWidth;

            if (board[currentColumn][currentRow].getText().equalsIgnoreCase("X")) {
                xs++;
            }
        }
        return xs;
    };

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        Object Control = actionEvent.getSource();

        for(int i =0; i<9;i++)
        {
            currentColumn = i/boardWidth;
            currentRow  =  i%boardWidth;

            if(Control == board[currentColumn][currentRow] && board[currentColumn][currentRow].getText().equalsIgnoreCase(""))
            {

                if(xs>os)
                {
                    board[currentColumn][currentRow].setText("O");
                    System.out.println(" ("+currentColumn+", "+currentRow+")");
                }else if(xs ==0)
                {
                    board[currentColumn][currentRow].setText("X");
                    System.out.println(" ("+currentColumn+","+currentRow+")");
                }
                else {
                    board[currentColumn][currentRow].setText("X");
                    System.out.println(" ("+currentColumn+", "+currentRow+")");
                }
            }

        }
        xs =0;
        os=0;

        if(Control == clear)
        {

            for(int i =0; i<9;i++)
            {
                currentColumn = i/boardWidth;
                currentRow  =  i%boardWidth;

                board[currentColumn][currentRow].setText("");

            }
            xs =0;
            os=0;

        }




    }
}
*/