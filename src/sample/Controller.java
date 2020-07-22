package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;

class MyClass {
    //объявление массива
    //для хранения инфы на время всей программы
    static int myArray[][] = new int[5][5];
    public static int minValue; //хранение минимального значения массива
    public static int maxValue; //хранение максимального значения массива
}

public class Controller implements Initializable {
    @FXML
    public TableView<Matrix> table1;
    @FXML
    public Button btnFillNum;
    @FXML
    public Button btnExec;
    @FXML
    public Label label1;
    @FXML
    public Label label2;
    @FXML
    public VBox pane1;
    @FXML
    public TableColumn<Matrix, String> col1;
    @FXML
    public TableColumn<Matrix, String> col2;
    @FXML
    public TableColumn<Matrix, String> col3;
    @FXML
    public TableColumn<Matrix, String> col4;
    @FXML
    public TableColumn<Matrix, String> col5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        col1.setCellValueFactory(new PropertyValueFactory<>("col1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("col2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("col3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("col4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("col5"));
    }

    //обработчик нажатия кнопки Заполнить таблицу случайными числами
    public void onClickBtnFillNum(ActionEvent actionEvent) {
        table1.getItems().clear();
        //мин макс элементы
        MyClass.minValue = MyClass.myArray[0][0];
        MyClass.maxValue = MyClass.myArray[0][0];
        //заполнение массива
        for (int i = 0; i < MyClass.myArray.length; i++) {
            Matrix mat = new Matrix();
            for (int j = 0; j < MyClass.myArray.length; j++) {
                MyClass.myArray[i][j] = (int) (Math.random() * 20) - 10;
                //System.out.print(" " + myArray[i][j]);
                //поиск минимального
                if (MyClass.myArray[i][j] < MyClass.minValue)
                    MyClass.minValue = MyClass.myArray[i][j];
                //поиск максимального
                if (MyClass.myArray[i][j] > MyClass.maxValue)
                    MyClass.maxValue = MyClass.myArray[i][j];
            }
            mat.setCol1(MyClass.myArray[i][0]);
            mat.setCol2(MyClass.myArray[i][1]);
            mat.setCol3(MyClass.myArray[i][2]);
            mat.setCol4(MyClass.myArray[i][3]);
            mat.setCol5(MyClass.myArray[i][4]);
            table1.getItems().add(mat);
        }
        label1.setText("Максимальный элемент: " + MyClass.maxValue);
        label2.setText("Минимальный элемент: " + MyClass.minValue);

    }
    //обработчик нажатия кнопки Выполнить задание
    public void onClickBtnExec(ActionEvent actionEvent) {
        //если макс элемент больше мин в 10 раз
        if (MyClass.maxValue > MyClass.minValue*10)
        {
            for (int i = 0; i < MyClass.myArray.length; i++) {
                for (int j = 0; j < MyClass.myArray.length; j++) {
                    //нули заменяем 1
                    if (MyClass.myArray[i][j] == 0)
                        MyClass.myArray[i][j] = 1;

                    //отрицательные числа заменяем модулями
                    if (MyClass.myArray[i][j] < 0)
                        MyClass.myArray[i][j] = Math.abs(MyClass.myArray[i][j]);

                }
            }
        }
        //очищаем таблицу
        table1.getItems().clear();
        for (int i = 0; i < MyClass.myArray.length; i++) {
            Matrix mat = new Matrix();
            mat.setCol1(MyClass.myArray[i][0]);
            mat.setCol2(MyClass.myArray[i][1]);
            mat.setCol3(MyClass.myArray[i][2]);
            mat.setCol4(MyClass.myArray[i][3]);
            mat.setCol5(MyClass.myArray[i][4]);
            table1.getItems().add(mat);
        }

    }
}

