/**
* TravelingSalesmanRunner instantiates the TravelingSalesman object and provides graphical model of the shortest route
* @author Elizabeth Song and Shreyaa Raghavan
* @version 12.21.18
*/

import java.util.*;
import java.io.*;
import java.awt.*;
import javax.swing.*;

public class TravelingSalesmanRunner extends JPanel{

    private static int points;
    final private static int SIZE = 1000;
    
    //main method initializes JFrame with fixed dimensions
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new TravelingSalesmanRunner());
        frame.setSize(SIZE, SIZE);
        frame.setVisible(true);    
    }
    
    //paint method(automatically called) plots coordinates and the shortest route
    public void paint(Graphics g) {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("How many points do you have?: ");
        points = keyboard.nextInt();
        int[] x = new int[points] ;
        int[] y = new int[points] ;
        
        for (int i = 0; i < points; i++){       //asks user for x and y coordinates
            System.out.print("Print out x-coordinate number " + (i + 1) + ": ");
            x[i] = keyboard.nextInt();
            System.out.print("Print out y-coordinate number " + (i + 1) + ": ");
            y[i] = keyboard.nextInt();
        }
        
        TravelingSalesman sir = new TravelingSalesman(x, y, points);
        System.out.println(Arrays.deepToString(sir.setDistance()));
        sir.permute(0);
        System.out.println(sir.getMin());       //retrieves value of shortest path
        
        Integer[] path = sir.getShortestPath();
        
        for (int i = 0; i < points; i++){       //plotting each of the points 
            int xTemp = x[i];
            int yTemp = y[i];
            g.fillOval(SIZE/2 + xTemp, SIZE/2 - yTemp, 6, 6);
        }
        
        for (int i = 0; i < points - 1; i++){       //drawing a line between consecutive points on the route    
            g.drawLine(3 + SIZE/2 + x[path[i]], 3 + SIZE/2 - y[path[i]],3 + SIZE/2 + x[path[i + 1]], 3 + SIZE/2 - y[path[i + 1]]);      //shifts origin to center of JFrame
        }
        
        
    }
}