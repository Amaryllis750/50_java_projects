package com.digitalclock;

import java.util.*;

import com.digitalclock.gui.*;

import javax.swing.*;
import java.awt.*;

public class TimePanel extends JPanel {
    private Calendar calendar;
    // private Map<Integer, String> days = Map.of(1, "Sunday",
    //         2, "Monday",
    //         3, "Tuesday",
    //         4, "Wednesday",
    //         5, "Thursday",
    //         6, "Friday",
    //         7, "Saturday");
    private DigitLabel hourLabel, minuteLabel, secondLabel;
    private Font textFont = new Font("Dubai", Font.BOLD, 50);

    public TimePanel() {
        super();
        calendar = Calendar.getInstance();
        setupGUI();
    }

    private void setupGUI() {

        hourLabel = new DigitLabel();
        JLabel colon1 = new JLabel(":");
        minuteLabel = new DigitLabel();
        JLabel colon2 = new JLabel(":");
        secondLabel = new DigitLabel();

        colon1.setFont(textFont);
        colon2.setFont(textFont);

        add(hourLabel);
        add(colon1);
        add(minuteLabel);
        add(colon2);
        add(secondLabel);
    }

    public void start(){
        while(true){
            try {
                setTime();
                Thread.sleep(1000);
                // this is a nasty nested list but I couldn't figure out a way to refactor it
                if(calendar.get(Calendar.SECOND) == calendar.getMaximum(Calendar.SECOND)){
                    // if the SECOND value is at it's maximum, set the value of second to zero and then check if it the MINUTE value is also at its maximum
                    calendar.set(Calendar.SECOND, 0);

                    if(calendar.get(Calendar.MINUTE) == calendar.getMaximum(Calendar.MINUTE)){
                        // if the MINUTE value is at its maximum, set the value of the minute to zero and then check if the HOUR value is also its maximum
                        calendar.set(Calendar.MINUTE, 0);

                        if(calendar.get(Calendar.HOUR_OF_DAY) == calendar.getMaximum(Calendar.HOUR_OF_DAY)){
                            // if the HOUR value is at its maximum, set it to zero.
                            calendar.set(Calendar.HOUR_OF_DAY, 0);
                        }
                        else{
                            // if the HOUR value is not at its maximum, roll it up by one
                            calendar.roll(Calendar.HOUR_OF_DAY, true);
                        }
                    }
                    else{
                        // if the minute value is not up to the maximum, roll it up by one
                        calendar.roll(Calendar.MINUTE, true);
                    }
                }
                else{
                    // if the SECOND value is not up to maximum roll it up by one
                    calendar.roll(Calendar.SECOND, true);
                }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void setTime() {
        hourLabel.setText(formatInteger(calendar.get(Calendar.HOUR_OF_DAY)));
        minuteLabel.setText(formatInteger(calendar.get(Calendar.MINUTE)));
        secondLabel.setText(formatInteger(calendar.get(Calendar.SECOND)));
    }

    private String formatInteger(int i){
        String stringValue = Integer.toString(i);
        if(stringValue.length() == 1){
            return String.format("0%d", i);
        }
        return stringValue;
    }
}
