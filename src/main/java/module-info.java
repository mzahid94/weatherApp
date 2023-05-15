module Weather_app.Weathers_app {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires org.apache.httpcomponents.httpclient;
	requires org.apache.httpcomponents.httpcore;
	requires org.json;
	requires java.sql;


    opens Weather_app.Weathers_app to javafx.fxml;
    exports Weather_app.Weathers_app;
}
