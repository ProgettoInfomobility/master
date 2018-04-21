/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.progettoinfomobility;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartMouseEvent;
import org.jfree.chart.ChartMouseListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleEdge;


public class Grafica extends JApplet {
    
    private static final int JFXPANEL_WIDTH_INT = 300;
    private static final int JFXPANEL_HEIGHT_INT = 250;
    private static JFXPanel fxContainer;
    
    //Grafico 1
    static XYSeries ts1 = new XYSeries("data");
    static XYSeries c1 = new XYSeries("cursor");
    static Coda<DataFrame> coda1 = new Coda<DataFrame>();
    static boolean freeze1 = false;
    static boolean cursor1 = false;
    static boolean export1 = false;
    static double freq1 = 0;
    static int curpos1 = 300;
    
    static XYSeries ts2 = new XYSeries("data");
    static XYSeries c2 = new XYSeries("cursor");
    static Coda<DataFrame> coda2 = new Coda<DataFrame>();
    static boolean freeze2 = false;
    static boolean cursor2 = false;
    static boolean export2 = false;
    static double freq2 = 0;
    static int curpos2 = 1;
    
    static XYSeries ts3 = new XYSeries("data");
    static XYSeries c3 = new XYSeries("cursor");
    static Coda<DataFrame> coda3 = new Coda<DataFrame>();
    static boolean freeze3 = false;
    static boolean cursor3 = false;
    static boolean export3 = false;
    static double freq3 = 0;
    static int length3 = 1;
    static int curpos3 = 1;
    
    static XYSeries ts4 = new XYSeries("data");
    static XYSeries c4 = new XYSeries("cursor");
    static Coda<DataFrame> coda4 = new Coda<DataFrame>();
    static boolean freeze4 = false;
    static boolean cursor4 = false;
    static boolean export4 = false;
    static double freq4 = 0;
    static int length4 = 1;
    static int curpos4 = 1;

    @Override
    public void init() {
        
        fxContainer = new JFXPanel();
        fxContainer.setPreferredSize(new Dimension(JFXPANEL_WIDTH_INT, JFXPANEL_HEIGHT_INT));
        add(fxContainer, BorderLayout.CENTER);
        // create JavaFX scene
        Platform.runLater(new Runnable() {
            
            @Override
            public void run() {
                createScene();
            }
        });
    }
    
     //Grafico 1
    static class VisGraf1 extends Thread implements Runnable {
        
        public void run() {
            boolean first = true;
            while(true) {
                DataFrame df = coda1.pop();
                freq1 = df.getFreq();
                if(!freeze1){
                    if(!first)
                        ts1.clear();
                    for(double i = 0; i < df.getArray().length; i++){
                        ts1.add(i / freq1 * 1000, df.getArray()[(int)i]);
                    }
                    first = false;
                }
                if(cursor1){
                    if(!c1.isEmpty())c1.clear();
                    c1.add(ts1.getDataItem(curpos1).getX(), ts1.getDataItem(curpos1).getY());
                    c1.add(ts1.getDataItem(curpos1).getX().doubleValue(), ts1.getDataItem(curpos1).getY().doubleValue());
                }
            }
        }
    }
     //Fine Grafico 1
    static class VisGraf2 extends Thread implements Runnable {
        
        public void run() {
            boolean first = true;
            while(true) {
                DataFrame df = coda2.pop();
                freq2 = df.getFreq();
                if(!freeze2){
                    if(!first)
                        ts2.clear();
                    for(double i = 0; i < df.getArray().length; i++){
                        ts2.add(i / df.getFreq() * 1000, df.getArray()[(int)i]);
                    }
                    first = false;
                }
                if(cursor2){
                    if(!c2.isEmpty())c2.clear();
                    c2.add(ts2.getDataItem(curpos2).getX(), ts2.getDataItem(curpos2).getY());
                    c2.add(ts2.getDataItem(curpos2).getX().doubleValue(), ts2.getDataItem(curpos2).getY().doubleValue());
                }
            }
        }
    }
    //
    static class VisGraf3 extends Thread implements Runnable {
        
        public void run() {
            boolean first = true;
            while(true) {
                DataFrame df = coda3.pop();
                freq3 = df.getFreq();
                length3 = df.getArray().length;
                if(!freeze3){
                    if(!first)
                        ts3.clear();
                    for(double i = 0; i < df.getArray().length; i++){
                        ts3.add(i * df.getFreq() / (2 * df.getArray().length * 1000), df.getArray()[(int)i]);
                    }
                    first = false;
                }
                if(cursor3){
                    if(!c3.isEmpty())c3.clear();
                    c3.add(ts3.getDataItem(curpos3).getX(), ts3.getDataItem(curpos3).getY());
                    c3.add(ts3.getDataItem(curpos3).getX().doubleValue(), ts3.getDataItem(curpos3).getY().doubleValue());
                }
            }
        }
    }
    //
    static class VisGraf4 extends Thread implements Runnable {
        
        public void run() {
            boolean first = true;
            while(true) {
                DataFrame df = coda4.pop();
                freq4 = df.getFreq();
                length4 = df.getArray().length;
                if(!freeze4){
                    if(!first)
                        ts4.clear();
                    for(double i = 0; i < df.getArray().length; i++){
                        ts4.add(i * df.getFreq() / (2 * df.getArray().length * 1000), df.getArray()[(int)i]);
                    }
                    first = false;
                    
                }
                if(cursor4){
                    if(!c4.isEmpty())c4.clear();
                    c4.add(ts4.getDataItem(curpos4).getX(), ts4.getDataItem(curpos4).getY());
                    c4.add(ts4.getDataItem(curpos4).getX().doubleValue(), ts4.getDataItem(curpos4).getY().doubleValue());
                }
            }
        }
    }
    //
    private void createScene() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Radar Monitor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,2));

        //Grafico 1
        XYSeriesCollection dataset1 = new XYSeriesCollection(c1);
        dataset1.addSeries(ts1);
        JFreeChart chart1 = ChartFactory.createXYLineChart(
            "TIME PLOT UP",
            "Time (ms)",
            "Amplitude",
            dataset1,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        ValueAxis a;
        final XYPlot plot1 = chart1.getXYPlot();
        
        plot1.getRenderer().setSeriesPaint(1, Color.green);
        plot1.getRenderer().setSeriesPaint(0, Color.red);
        plot1.getRenderer().setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
        plot1.getRenderer().setSeriesStroke( 0 , new BasicStroke( 10.0f ) );
        
        try{
            XYPlot tmpplot = (XYPlot) new StampaOggetto("plot1.conf").read();
            ValueAxis tmpa = tmpplot.getDomainAxis();

            plot1.setBackgroundPaint(tmpplot.getBackgroundPaint());
            a = plot1.getDomainAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
            
            tmpa = tmpplot.getRangeAxis();
            a = plot1.getRangeAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
        }catch(Exception e){
            a = plot1.getDomainAxis();
            plot1.setBackgroundPaint(Color.black);
            a.setAutoRange(false);
            a.setRange(0, 13);
            a = plot1.getRangeAxis();
            a.setAutoRange(false);
            a.setRange(0, 1);
        }
        
        VisGraf1 visGraf1 = new VisGraf1();
        visGraf1.start();
        //Fine Grafico 1
        
        XYSeriesCollection dataset2 = new XYSeriesCollection(c2);
        dataset2.addSeries(ts2);
        JFreeChart chart2 = ChartFactory.createXYLineChart(
            "TIME PLOT DOWN",
            "Time (ms)",
            "Amplitude",
            dataset2,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        XYPlot plot2 = chart2.getXYPlot();
        
        plot2.getRenderer().setSeriesPaint(1, Color.red);
        plot2.getRenderer().setSeriesPaint(0, Color.green);
        plot2.getRenderer().setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
        plot2.getRenderer().setSeriesStroke( 0 , new BasicStroke( 10.0f ) );

        try{
            XYPlot tmpplot = (XYPlot) new StampaOggetto("plot2.conf").read();
            ValueAxis tmpa = tmpplot.getDomainAxis();
            
            plot2.setBackgroundPaint(tmpplot.getBackgroundPaint());
            a = plot2.getDomainAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
            
            tmpa = tmpplot.getRangeAxis();
            a = plot2.getRangeAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
        }catch(Exception e){
            plot2.setBackgroundPaint(Color.black);
            a = plot2.getDomainAxis();
            a.setAutoRange(false);
            a.setRange(0, 13);
            a = plot2.getRangeAxis();
            a.setAutoRange(false);
            a.setRange(0, 1);
        }
        
        VisGraf2 visGraf2 = new VisGraf2();
        visGraf2.start();
        //2fine
        XYSeriesCollection dataset3 = new XYSeriesCollection(c3);
        dataset3.addSeries(ts3);
        JFreeChart chart3 = ChartFactory.createXYLineChart(
            "FFT PLOT UP",
            "Frequency (kHz)",
            "Mag",
            dataset3,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        
        final XYPlot plot3 = chart3.getXYPlot();
        
        plot3.getRenderer().setSeriesPaint(0, Color.red);
        plot3.getRenderer().setSeriesPaint(1, Color.green);
        plot3.getRenderer().setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
        plot3.getRenderer().setSeriesStroke( 0 , new BasicStroke( 10.0f ) );

        try{
            XYPlot tmpplot = (XYPlot) new StampaOggetto("plot3.conf").read();
            ValueAxis tmpa = tmpplot.getDomainAxis();
            
            plot3.setBackgroundPaint(tmpplot.getBackgroundPaint());
            a = plot3.getDomainAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
            
            tmpa = tmpplot.getRangeAxis();
            a = plot3.getRangeAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
        }catch(Exception e){     
            plot3.setBackgroundPaint(Color.black);
            plot3.getRenderer().setSeriesPaint(0, Color.green);
            a = plot3.getDomainAxis();
            a.setAutoRange(false);
            a.setRange(0, 25);
            a = plot3.getRangeAxis();
            a.setAutoRange(false);
            a.setRange(0, 5);
        }
        
        VisGraf3 visGraf3 = new VisGraf3();
        visGraf3.start();
        //3 fine
        XYSeriesCollection dataset4 = new XYSeriesCollection(c4);
        dataset4.addSeries(ts4);
        JFreeChart chart4 = ChartFactory.createXYLineChart(
            "FFT DOWN",
            "Frequency (kHz)",
            "Mag",
            dataset4,
            PlotOrientation.VERTICAL,
            false,
            true,
            false
        );
        
        
        final XYPlot plot4 = chart4.getXYPlot();
        
        plot4.getRenderer().setSeriesPaint(0, Color.green);
        plot4.getRenderer().setSeriesPaint(1, Color.red);
        plot4.getRenderer().setSeriesStroke( 0 , new BasicStroke( 10.0f ) );
        plot4.getRenderer().setSeriesStroke( 1 , new BasicStroke( 2.0f ) );

        try{
            XYPlot tmpplot = (XYPlot) new StampaOggetto("plot4.conf").read();
            ValueAxis tmpa = tmpplot.getDomainAxis();
            
            plot4.setBackgroundPaint(tmpplot.getBackgroundPaint());
            a = plot4.getDomainAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
            
            tmpa = tmpplot.getRangeAxis();
            a = plot4.getRangeAxis();
            
            a.setAutoRange(tmpa.isAutoRange());
            a.setRange(tmpa.getRange());
        }catch(Exception e){
            plot4.setBackgroundPaint(Color.black);
            a = plot4.getDomainAxis();
            a.setAutoRange(false);
            a.setRange(0, 25);
            a = plot4.getRangeAxis();
            a.setAutoRange(false);
            a.setRange(0, 5);
        }
        
        VisGraf4 visGraf4 = new VisGraf4();
        visGraf4.start();
        //fine4
        
        JPanel hPanel1 = new JPanel();
        hPanel1.setBorder(new EmptyBorder(10, 10, 10, 10));
        hPanel1.setLayout(new BoxLayout(hPanel1, BoxLayout.Y_AXIS));
        hPanel1.setBackground(Color.white);
        JPanel p1 = new JPanel();
        p1.setBorder(new EmptyBorder(0, 0, 10, 0));
        BoxLayout boxLayout1 = new BoxLayout(p1, BoxLayout.X_AXIS);
        p1.setLayout(boxLayout1);
        
        JButton btn_freeze1 = new JButton("FREEZE");
        btn_freeze1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                freeze1 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p1.add(btn_freeze1);
        p1.setBackground(Color.WHITE);
        
        JButton btn_cursor1 = new JButton("SHOW CURSOR");
        btn_cursor1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                cursor1 ^= true;
                if(cursor1)
                    btn_cursor1.setText("HIDE CURSOR");
                else{
                    btn_cursor1.setText("SHOW CURSOR");
                    if(!c1.isEmpty()) c1.clear();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        p1.add(btn_cursor1);
        
        // esporta
        
        JButton btn_export1 = new JButton("EXPORT");
        btn_export1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                export1 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        p1.add(btn_export1);
        hPanel1.add(p1);
        ChartPanel cp1 = new ChartPanel(chart1);
        cp1.addChartMouseListener(new ChartMouseListener(){
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                double x = plot1.getDomainAxis().java2DToValue(cme.getTrigger().getX(), cp1.getScreenDataArea(), RectangleEdge.BOTTOM);
                curpos1 = (int)(x * freq1 / 1000);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {}
        });
        hPanel1.add(cp1);
        frame.add(hPanel1);
        
        JPanel hPanel2 = new JPanel();
        hPanel2.setBorder(new EmptyBorder(10, 10, 10, 10));
        hPanel2.setLayout(new BoxLayout(hPanel2, BoxLayout.Y_AXIS));
        JPanel p2 = new JPanel();
        p2.setBorder(new EmptyBorder(0, 0, 10, 0));
        BoxLayout boxLayout2 = new BoxLayout(p2, BoxLayout.X_AXIS);
        p2.setLayout(boxLayout2);
        
        JButton btn_freeze2 = new JButton("FREEZE"); // AHAHAHAHA CI SONO ANCHE IO NEI COMMENTI
        btn_freeze2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                freeze2 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p2.add(btn_freeze2);
        JButton btn_cursor2 = new JButton("SHOW CURSOR");
        btn_cursor2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                cursor2 ^= true;
                if(cursor2)
                    btn_cursor2.setText("HIDE CURSOR");
                else{
                    btn_cursor2.setText("SHOW CURSOR");
                    if(!c2.isEmpty()) c2.clear();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        p2.add(btn_cursor2);
        
        JButton btn_export2 = new JButton("EXPORT");
        btn_export2.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                export2 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        p2.add(btn_export2);
        p2.setBackground(Color.WHITE);
        hPanel2.add(p2);
        ChartPanel cp2 = new ChartPanel(chart2);
        cp2.addChartMouseListener(new ChartMouseListener(){
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                double x = plot2.getDomainAxis().java2DToValue(cme.getTrigger().getX(), cp2.getScreenDataArea(), RectangleEdge.BOTTOM);
                curpos2 = (int)(x * freq2 / 1000);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {}
        });
        hPanel2.add(cp2);
        hPanel2.setBackground(Color.white);
        frame.add(hPanel2);
        
        JPanel hPanel3 = new JPanel();
        hPanel3.setBorder(new EmptyBorder(10, 10, 10, 10));
        hPanel3.setLayout(new BoxLayout(hPanel3, BoxLayout.Y_AXIS));
        JPanel p3 = new JPanel();
        p3.setBorder(new EmptyBorder(0, 0, 10, 0));
        BoxLayout boxLayout3 = new BoxLayout(p3, BoxLayout.X_AXIS);
        p3.setLayout(boxLayout3);
        
        JButton btn_freeze3 = new JButton("FREEZE");
        btn_freeze3.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                freeze3 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p3.add(btn_freeze3);
        
        JButton btn_cursor3 = new JButton("SHOW CURSOR");
        btn_cursor1.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                cursor3 ^= true;
                if(cursor3)
                    btn_cursor3.setText("HIDE CURSOR");
                else{
                    btn_cursor3.setText("SHOW CURSOR");
                    if(!c3.isEmpty()) c3.clear();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p3.add(btn_cursor3);

        JButton btn_export3 = new JButton("EXPORT");
        btn_export3.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                export3 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        p3.add(btn_export3);

        
        p3.setBackground(Color.WHITE);
        
        hPanel3.add(p3);
        ChartPanel cp3 = new ChartPanel(chart3);
        cp3.addChartMouseListener(new ChartMouseListener(){
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                double x = plot3.getDomainAxis().java2DToValue(cme.getTrigger().getX(), cp3.getScreenDataArea(), RectangleEdge.BOTTOM);
                curpos3 = (int)(2 * x * length3 * 1000 / freq3);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {}
        });
        hPanel3.add(cp3);
        frame.add(hPanel3);
        
        JPanel hPanel4 = new JPanel();
        hPanel4.setBorder(new EmptyBorder(10, 10, 10, 10));
        hPanel4.setLayout(new BoxLayout(hPanel4, BoxLayout.Y_AXIS));
        JPanel p4 = new JPanel();
        p4.setBorder(new EmptyBorder(0, 0, 10, 0));
        BoxLayout boxLayout4 = new BoxLayout(p4, BoxLayout.X_AXIS);
        hPanel3.setBackground(Color.white);
        p4.setLayout(boxLayout4);
        
        JButton btn_freeze4 = new JButton("FREEZE");
        btn_freeze4.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                freeze4 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p4.add(btn_freeze4);
        JButton btn_cursor4 = new JButton("SHOW CURSOR");
        btn_cursor4.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                cursor4 ^= true;
                if(cursor4)
                    btn_cursor4.setText("HIDE CURSOR");
                else{
                    btn_cursor4.setText("SHOW CURSOR");
                    if(!c4.isEmpty()) c4.clear();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p4.add(btn_cursor4);
        
        JButton btn_export4 = new JButton("EXPORT");
        btn_export4.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                export4 ^= true;
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }
        );
        
        p4.add(btn_export4);
        p4.setBackground(Color.WHITE);
        hPanel4.add(p4);
        ChartPanel cp4 = new ChartPanel(chart4);
        cp4.addChartMouseListener(new ChartMouseListener(){
            @Override
            public void chartMouseClicked(ChartMouseEvent cme) {
                double x = plot4.getDomainAxis().java2DToValue(cme.getTrigger().getX(), cp4.getScreenDataArea(), RectangleEdge.BOTTOM);
                curpos4 = (int)(2 * x * length4 * 1000 / freq4);
            }

            @Override
            public void chartMouseMoved(ChartMouseEvent cme) {}
        });
        hPanel4.add(cp4);
        hPanel4.setBackground(Color.white);
        frame.add(hPanel4);
        frame.pack();
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowListener(){
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                try{
                    new StampaOggetto("plot1.conf").write(plot1);
                    new StampaOggetto("plot2.conf").write(plot2);
                    new StampaOggetto("plot3.conf").write(plot3);
                    new StampaOggetto("plot4.conf").write(plot4);
                }catch(Exception er){}
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
            
        });
    }
    
}
