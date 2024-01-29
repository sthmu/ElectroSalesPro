package com.eeServiceCenter.desktop.util;

public class ViewFactory {

    private static ViewFactory viewFactory;

    public static ViewFactory getInstance(){
        return viewFactory!=null?viewFactory:(viewFactory=new ViewFactory());
    }

    public String getView(int lvl){
        switch(lvl){

            case 1:return "/view/retail.fxml";
            case 3:return "/view/accounting.fxml";
            case 4: return "/view/inventory.fxml";
            case 8:return "/view/dashboard.fxml";
            case 9:return "/view/dashboard.fxml";
            case 10:return "/view/dashboard.fxml";
            default:return null;

        }
    }
}
