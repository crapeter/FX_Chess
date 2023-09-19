module com.example.fxchess {
  requires javafx.controls;
  requires javafx.fxml;
  requires jpro.webapi;

  opens com.example.fxchess to javafx.fxml;
  exports com.example.fxchess;
}