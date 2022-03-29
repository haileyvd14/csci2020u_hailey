import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.util.HashMap;

import java.util.Map;

import java.util.Scanner;

import javafx.application.Application;

import static javafx.application.Application.launch;

import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.geometry.Side;

import javafx.scene.Scene;

import javafx.scene.chart.PieChart;

import javafx.scene.layout.Pane;

import javafx.stage.Stage;

public class PieChartDisplay extends Application {

    private Map<String, Integer> map;
  

    private void loadData(String filename) {

        try {

            Scanner scanner = new Scanner(new FileReader(new File(filename)));

            while (scanner.hasNext()) {

                String line = scanner.nextLine();

                String columns[] = line.split(",");

                if (columns.length >= 6) {

                    String field = columns[5].trim();

                    if (map.containsKey(field)) {

                        int count = map.get(field);
                        count += 1;
                        map.put(field, count);

                    } else {
                        map.put(field, 1);

                    }

                }

            }

        } catch (FileNotFoundException ex) {

            System.out.println(ex);
        }
    }

    ObservableList<PieChart.Data> createChartData() {

      ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        for (String key : map.keySet()) {
            data.add(new PieChart.Data(key, map.get(key)));

        }
        return data;

    }

    @Override

    public void start(Stage primaryStage) {

        //initializing map

        map = new HashMap<>();

        loadData("weatherwarnings-2015.csv");

        PieChart chart = new PieChart(createChartData());
      
        chart.setLegendSide(Side.LEFT);

        Pane root = new Pane();

        root.getChildren().add(chart);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.setTitle("Lab 07");

        primaryStage.show();

    }

    public static void main(String[] args) {

        launch(args);

    }

}
