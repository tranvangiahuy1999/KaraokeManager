package com.example.admin.myapplication.command;

public class Remote {
    public InterfaceCommand ICommand1;
    public InterfaceCommand ICommand2;
    public InterfaceCommand ICommand3;

    public void setICommand1(InterfaceCommand ICommand){
        this.ICommand1 = ICommand;
    }

    public void setICommand2(InterfaceCommand ICommand){
        this.ICommand2 = ICommand;
    }

    public void setICommand3(InterfaceCommand ICommand){
        this.ICommand3 = ICommand;
    }

    public void button1_onClickHandler(){
        ICommand1.execute();
    }

    public void button2_onClickHandler(){
        ICommand2.execute();
    }

    public void button3_onClickHandler(){
        ICommand3.execute();
    }
}
