package com.songzx;

import com.songzx.utils.Command;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.math.BigDecimal;

public class Controller {
    @FXML
    ImageView screen;
    @FXML
    Button refreshBtn;
    double startX = 0d, startY = 0d, endX = 0d, endY = 0d;
    double factor = 4.12;

    public void updateImg() {
        Command.capture();
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        File imgFile = new File(Command.IMAGE_URL);
        InputStream is = null;
        try {
            is = new FileInputStream(imgFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Image image = new Image(is);
        screen.setImage(image);

        screen.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                MouseButton mb = e.getButton();
                if(mb.toString().equals("PRIMARY")) {
                    startX = e.getSceneX();
                    startY = e.getSceneY();
                    System.out.println(mb + ":" + startX + ":" + startY + ":" + endX + ":" + endY);
                } else if (mb.toString().equals("SECONDARY")){
                    endX = e.getSceneX();
                    endY = e.getSceneY();
                    System.out.println(mb + ":" + startX + ":" + startY + ":" + endX + ":" + endY);

                    double distance = calcDistance(startX, startY, endX, endY);
                    System.out.println(distance);
                    jump(distance);
                } else {

                }
            }
        });

    }

    public double calcDistance(double startX, double startY, double endX, double endY) {
        BigDecimal bStartX = new BigDecimal(startX);
        BigDecimal bStartY = new BigDecimal(startY);
        BigDecimal bEndX = new BigDecimal(endX);
        BigDecimal bEndY = new BigDecimal(endY);
        double result = Math.sqrt(bEndX.subtract(bStartX).pow(2).add(bEndY.subtract(bStartY).pow(2)).doubleValue());
        return result;
    }

    public void jump(double distance) {
        double duration = distance * factor;
        Command.jump(duration);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        updateImg();
    }

}
