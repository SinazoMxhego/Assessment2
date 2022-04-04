package iLAB.data;

import java.sql.ResultSet;

public class DbConnection {
    public ResultSet ConnectDb() {
        data connect = new data();
        ResultSet FillingFormData;


        String url = "jdbc:mysql://localhost:3306/ilabdb";
        String user = "root";
        String pass = "Sinazo@176";


        String credQuery = "select * from apply";
        FillingFormData = connect.ConnectAndQuerySQL(url, user, pass, credQuery);

        return FillingFormData;
    }
}
