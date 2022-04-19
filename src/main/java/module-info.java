module com.example.loanthe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.LT to javafx.fxml;
    exports com.LT;
}