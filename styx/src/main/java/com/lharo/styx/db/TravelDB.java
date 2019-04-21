package com.lharo.styx.db;

import java.sql.*;
import java.util.Vector;

public class TravelDB {
    private static String dbURL = "jdbc:derby:Restaurant;create=false";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String queryShowDB = "Select t1.Name as name, t2.NAME as Cat_name, t1.PRICE price " +
                                              "From APP.FOODS t1 LEFT JOIN APP.CATEGORY t2 " +
                                              "ON t1.CATEGORY_ID = t2.ID";

    private static Connection conn = null;
    private static Statement stmt = null;

    private static void createConnection()
    {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(dbURL);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addOne(Object...args){
        if(conn == null) { createConnection();}
        String name = args[0].toString();
        try {
            stmt = conn.createStatement();
            //Нахожу максимальный айди и потом к нему +1
            ResultSet results = stmt.executeQuery("SELECT MAX(ID) From APP.FOODS");
            results.next();
            int newID = results.getInt(1)+1;
            //Добавляю новую строку
            if(args.length==3){
                results = stmt.executeQuery("SELECT ID From APP.CATEGORY WHERE NAME='"+args[1].toString()+"'");
                results.next();
                int cat_id = results.getInt(1);

                stmt.executeUpdate("INSERT INTO APP.FOODS(ID, NAME, CATEGORY_ID, PRICE) VALUES("+newID+",'"+name+"',"+cat_id+","+(int)args[2]+")");}
            if(args.length==2){
                stmt.executeUpdate("INSERT INTO APP.FOODS(ID, NAME, PRICE) VALUES("+newID+",'"+name+"',"+(int)args[1]+")");
                }
            stmt.close();



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void showDB(){
        Vector columnNames = new Vector();
        Vector data = new Vector();


        try {
            if(conn == null) { createConnection();}
            stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery(queryShowDB);
            //Вывод результата запроса в виде таблицы
            ResultSetMetaData rsmd = results.getMetaData(); //заголовок
            int columns = rsmd.getColumnCount(); //Кол-во заголовков
            for (int i = 1; i <= columns; i++) {
                columnNames.addElement(rsmd.getColumnName(i));

            }
            while (results.next()) {
                Vector row = new Vector(columns);
                for (int i = 1; i <= columns; i++) {
                    row.addElement(results.getString(i));
                }
                data.addElement(row);
            }



            results.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
 /*       JFrame frame = new JFrame("Restaurant table");
        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        frame.getContentPane().add(scrollPane);
        frame.setPreferredSize(new Dimension(450, 200));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);*/


    }
    public static void delOne(String name){
        //Проверяю подключение к базе данных
        if(conn == null) { createConnection();}
        try {
            //Удалю строку
            stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM APP.FOODS WHERE NAME='"+name+"'");
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
