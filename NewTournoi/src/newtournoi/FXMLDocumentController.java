package newtournoi;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import jfxtras.scene.control.ImageViewButton;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FXMLDocumentController implements Initializable {

    public static final Random random1 = new Random();

    String Namee;

    int data1[] = new int[9];
    int data2[] = new int[9];
    int data3[] = new int[9];
    int data4[] = new int[9];

    @FXML
    private JFXTextArea Yellow, Red;

    @FXML
    private ImageViewButton imageV1;
    @FXML
    private ImageViewButton imageV2;
    @FXML
    private ImageViewButton imageV3;
    @FXML
    private ImageViewButton imageV4;

    ArrayList<DataEquipe> ls = new ArrayList<DataEquipe>();

    public static int A;
    public static int B;
    public static int C;
    public static int D;

    public static String logoeq1;
    public static String logoeq2;
    public static String logoeq3;
    public static String logoeq4;
    public static String eq1;
    public static String eq2;
    public static String eq3;
    public static String eq4;

    @FXML
    private TextField m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12, m13, m14, m15, m16, m17, m18, m19, m20, m21, m22, m23, m24;
    @FXML
    private Label e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24;
    @FXML
    private Tab tabscore, tab0, tab1, tab2, tab3, tab4;
    @FXML
    private Label team3, team4;
    @FXML
    private JFXComboBox<String> C1, C2, C3, C4;
    @FXML
    private ToggleGroup group;
    @FXML
    private JFXRadioButton r1, r2, r3;
    @FXML
    private Pane SelectTeamsNomber, SelectTeams, pnclassemt;
    @FXML
    private TableView<Equipe> tableview1;
    @FXML
    private TableColumn<Equipe, String> NomEquipe;

    @FXML
    private TableColumn<Equipe, Integer> N, mj, mg, mn, mp, bp, bc, dfb, pointE;

    @FXML
    private JFXTextField winner;

    @FXML
    void Fram2(ActionEvent event) {

        if (group.getSelectedToggle() != null) {

            SelectTeams.setVisible(true);
            SelectTeamsNomber.setVisible(false);

        }
        if (r2.isSelected()) {
            team3.setVisible(true);
            C3.setVisible(true);
        } else if (r3.isSelected()) {
            team3.setVisible(true);
            C3.setVisible(true);
            team4.setVisible(true);
            C4.setVisible(true);
        }
    }

    @FXML
    void clear(ActionEvent event) {
        group.selectToggle(null);
    }

    @FXML
    void BackA(ActionEvent event) {
        SelectTeamsNomber.setVisible(true);
        SelectTeams.setVisible(false);
        group.selectToggle(null);
        C1.setValue("");
        C2.setValue("");
        C3.setValue("");
        C4.setValue("");
        C3.setVisible(false);
        C4.setVisible(false);
        team3.setVisible(false);
        team4.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Calendar now = Calendar.getInstance();
        System.out.println(now.get(Calendar.DATE) + "/" + (now.get(Calendar.MONTH) + 1 + "/" + now.get(Calendar.YEAR)));

        getNTournoi();

        SelectTeams.setVisible(false);
        SelectTeamsNomber.setVisible(true);
        pnclassemt.setVisible(false);

        DBConnexion db = new DBConnexion("", ""); // user pass of Database
        String sql = "SELECT * FROM Equipe";

        try {
            db.connect();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("NomEquipe");

                C1.getItems().addAll(title);
                C2.getItems().addAll(title);
                C3.getItems().addAll(title);
                C4.getItems().addAll(title);

            }
            rs.close();
            db.closeConnection();

        } catch (SQLException sqli) {
        } catch (Exception e) {
        }

        UnaryOperator<Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };

        m1.setTextFormatter(new TextFormatter<String>(integerFilter));
        m2.setTextFormatter(new TextFormatter<String>(integerFilter));
        m3.setTextFormatter(new TextFormatter<String>(integerFilter));
        m4.setTextFormatter(new TextFormatter<String>(integerFilter));
        m5.setTextFormatter(new TextFormatter<String>(integerFilter));
        m6.setTextFormatter(new TextFormatter<String>(integerFilter));
        m7.setTextFormatter(new TextFormatter<String>(integerFilter));
        m8.setTextFormatter(new TextFormatter<String>(integerFilter));
        m9.setTextFormatter(new TextFormatter<String>(integerFilter));
        m10.setTextFormatter(new TextFormatter<String>(integerFilter));
        m11.setTextFormatter(new TextFormatter<String>(integerFilter));
        m12.setTextFormatter(new TextFormatter<String>(integerFilter));
        m13.setTextFormatter(new TextFormatter<String>(integerFilter));
        m14.setTextFormatter(new TextFormatter<String>(integerFilter));
        m15.setTextFormatter(new TextFormatter<String>(integerFilter));
        m16.setTextFormatter(new TextFormatter<String>(integerFilter));
        m17.setTextFormatter(new TextFormatter<String>(integerFilter));
        m18.setTextFormatter(new TextFormatter<String>(integerFilter));
        m19.setTextFormatter(new TextFormatter<String>(integerFilter));
        m20.setTextFormatter(new TextFormatter<String>(integerFilter));
        m21.setTextFormatter(new TextFormatter<String>(integerFilter));
        m22.setTextFormatter(new TextFormatter<String>(integerFilter));
        m23.setTextFormatter(new TextFormatter<String>(integerFilter)); 
        m24.setTextFormatter(new TextFormatter<String>(integerFilter));
    }

    @FXML
    void Classement(ActionEvent event) {

        eq1 = (String) C1.getValue();
        eq2 = (String) C2.getValue();
        eq3 = (String) C3.getValue();
        eq4 = (String) C4.getValue();

        if (r1.isSelected()) {
            equal2();
            setTab2Text("Table", eq1, eq2, eq3, eq4);
        }
        if (r2.isSelected()) {
            equal3();
            setTab2Text("Table", eq1, eq2, eq3, eq4);
        }
        if (r3.isSelected()) {
            equal4();
            setTab2Text("Table", eq1, eq2, eq3, eq4);
        }
        buildtab();

    }

    void loadimage() {

        DBConnexion db = new DBConnexion("", ""); // user pass of Database
        String sql = "SELECT * FROM Equipe";

        try {
            db.connect();
            ResultSet rs = db.getStatement().executeQuery(sql);
            while (rs.next()) {

                DataEquipe de = new DataEquipe(rs.getString("NomEquipe"), rs.getString("LogoEquipe"));
                ls.add(de);

            }
            rs.close();
            db.closeConnection();
            if (r3.isSelected()) {
                for (int i = 0; i < ls.size(); i++) {
                    if (eq1.equals(ls.get(i).getName())) {
                        logoeq1 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq1);
                        Image image = new Image(file.toURI().toString());
                        imageV1.setImage(image);
                    }
                    if (eq2.equals(ls.get(i).getName())) {
                        logoeq2 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq2);
                        Image image = new Image(file.toURI().toString());
                        imageV2.setImage(image);
                    }
                    if (eq3.equals(ls.get(i).getName())) {
                        logoeq3 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq3);
                        Image image = new Image(file.toURI().toString());
                        imageV3.setImage(image);
                    }
                    if (eq4.equals(ls.get(i).getName())) {
                        logoeq4 = ls.get(i).getLogon().toString();
                        System.out.println(logoeq4);
                        File file = new File(logoeq4);
                        Image image = new Image(file.toURI().toString());
                        imageV4.setImage(image);
                    }

                }
            }
            if (r2.isSelected()) {
                for (int i = 0; i < ls.size(); i++) {
                    if (eq1.equals(ls.get(i).getName())) {
                        logoeq1 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq1);
                        Image image = new Image(file.toURI().toString());
                        imageV1.setImage(image);
                    }
                    if (eq2.equals(ls.get(i).getName())) {
                        logoeq2 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq2);
                        Image image = new Image(file.toURI().toString());
                        imageV2.setImage(image);
                    }
                    if (eq3.equals(ls.get(i).getName())) {
                        logoeq3 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq3);
                        Image image = new Image(file.toURI().toString());
                        imageV3.setImage(image);
                    }

                }
            }
            if (r1.isSelected()) {
                for (int i = 0; i < ls.size(); i++) {
                    if (eq1.equals(ls.get(i).getName())) {
                        logoeq1 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq1);
                        Image image = new Image(file.toURI().toString());
                        imageV1.setImage(image);
                    }
                    if (eq2.equals(ls.get(i).getName())) {
                        logoeq2 = ls.get(i).getLogon().toString();

                        File file = new File(logoeq2);
                        Image image = new Image(file.toURI().toString());
                        imageV2.setImage(image);
                    }
                }
            }

        } catch (SQLException sqli) {
        } catch (Exception e) {
        }
    }

    void setTab2Text(String text0, String text1, String text2, String text3, String text4) {
        tab0.setText(text0);
        tab1.setText(text1);
        tab2.setText(text2);
        tab3.setText(text3);
        tab4.setText(text4);
    }

    void data1plus(int max, int min) {
        data1[0] = data1[1] + 1;
        data1[1] = data1[1] + 1;
        data1[4] = data1[4] + max;
        data1[5] = data1[5] + min;
        data1[6] = data1[6] + max - min;
        data1[7] = data1[7] + 3;
    }

    void data1moin(int min, int max) {
        data1[0] = data1[0] + 1;
        data1[3] = data1[3] + 1;
        data1[4] = data1[4] + min;
        data1[5] = data1[5] + max;
        data1[6] = data1[6] + min - max;
    }

    void data1null(int nul1, int nul2) {
        data1[0] = data1[0] + 1;
        data1[2] = data1[2] + 1;
        data1[4] = data1[4] + nul1;
        data1[5] = data1[5] + nul2;
        data1[6] = data1[6] + nul1 - nul2;
        data1[7] = data1[7] + 1;
    }

    void data2plus(int max, int min) {
        data2[0] = data2[0] + 1;
        data2[1] = data2[1] + 1;
        data2[4] = data2[4] + max;
        data2[5] = data2[5] + min;
        data2[6] = data2[6] + max - min;
        data2[7] = data2[7] + 3;
    }

    void data2moin(int min, int max) {
        data2[0] = data2[0] + 1;
        data2[3] = data2[3] + 1;
        data2[4] = data2[4] + min;
        data2[5] = data2[5] + max;
        data2[6] = data2[6] + min - max;
    }

    void data2null(int nul1, int nul2) {
        data2[0] = data2[0] + 1;
        data2[2] = data2[2] + 1;
        data2[4] = data2[4] + nul1;
        data2[5] = data2[5] + nul2;
        data2[6] = data2[6] + nul1 - nul2;
        data2[7] = data2[7] + 1;
    }

    void data3plus(int max, int min) {
        data3[0] = data3[0] + 1;
        data3[1] = data3[1] + 1;
        data3[4] = data3[4] + max;
        data3[5] = data3[5] + min;
        data3[6] = data3[6] + max - min;
        data3[7] = data3[7] + 3;
    }

    void data3moin(int min, int max) {
        data3[0] = data3[0] + 1;
        data3[3] = data3[3] + 1;
        data3[4] = data3[4] + min;
        data3[5] = data3[5] + max;
        data3[6] = data3[6] + min - max;
    }

    void data3null(int nul1, int nul2) {
        data3[0] = data3[0] + 1;
        data3[2] = data3[2] + 1;
        data3[4] = data3[4] + nul1;
        data3[5] = data3[5] + nul2;
        data3[6] = data3[6] + nul1 - nul2;
        data3[7] = data3[7] + 1;
    }

    void data4plus(int max, int min) {
        data4[0] = data4[0] + 1;
        data4[1] = data4[1] + 1;
        data4[4] = data4[4] + max;
        data4[5] = data4[5] + min;
        data4[6] = data4[6] + max - min;
        data4[7] = data4[7] + 3;
    }

    void data4moin(int min, int max) {
        data4[0] = data4[0] + 1;
        data4[3] = data4[3] + 1;
        data4[4] = data4[4] + min;
        data4[5] = data4[5] + max;
        data4[6] = data4[6] + min - max;
    }

    void data4null(int nul1, int nul2) {
        data4[0] = data4[0] + 1;
        data4[2] = data4[2] + 1;
        data4[4] = data4[4] + nul1;
        data4[5] = data4[5] + nul2;
        data4[6] = data4[6] + nul1 - nul2;
        data4[7] = data4[7] + 1;
    }

    public void match1() {

        int field1 = Integer.parseInt(m1.getText());
        int field2 = Integer.parseInt(m2.getText());

        if (r1.isSelected() || r2.isSelected() || r3.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data2moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data2plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data2null(field2, field1);
            }
        }

    }

    public void match2() {
        int field1 = Integer.parseInt(m3.getText());
        int field2 = Integer.parseInt(m4.getText());

        if (r2.isSelected()) {
            if (field1 > field2) {
                data3plus(field1, field2);
                data1moin(field2, field1);
            }
            if (field1 < field2) {
                data3moin(field1, field2);
                data1plus(field2, field1);
            }
            if (field1 == field2) {
                data3null(field1, field2);
                data1null(field2, field1);
            }
        }
        if (r3.isSelected()) {
            if (field1 > field2) {
                data3plus(field1, field2);
                data4moin(field2, field1);
            }
            if (field1 < field2) {
                data3moin(field1, field2);
                data4plus(field2, field1);
            }
            if (field1 == field2) {
                data3null(field1, field2);
                data4null(field2, field1);
            }
        }

    }

    public void match3() {
        int field1 = Integer.parseInt(m5.getText());
        int field2 = Integer.parseInt(m6.getText());

        if (r2.isSelected()) {
            if (field1 > field2) {
                data2plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data2moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data2null(field1, field2);
                data3null(field2, field1);
            }
        }
        if (r3.isSelected()) {
            if (field1 > field2) {
                data4plus(field1, field2);
                data1moin(field2, field1);
            }
            if (field1 < field2) {
                data4moin(field1, field2);
                data1plus(field2, field1);
            }
            if (field1 == field2) {
                data4null(field1, field2);
                data1null(field2, field1);
            }
        }

    }

    public void match4() {
        int field1 = Integer.parseInt(m7.getText());
        int field2 = Integer.parseInt(m8.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data2plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data2moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data2null(field1, field2);
                data3null(field2, field1);
            }
        }

    }

    public void match5() {
        int field1 = Integer.parseInt(m9.getText());
        int field2 = Integer.parseInt(m10.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data3null(field2, field1);
            }
        }
    }

    public void match6() {
        int field1 = Integer.parseInt(m11.getText());
        int field2 = Integer.parseInt(m12.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data2plus(field1, field2);
                data4moin(field2, field1);
            }
            if (field1 < field2) {
                data2moin(field1, field2);
                data4plus(field2, field1);
            }
            if (field1 == field2) {
                data2null(field1, field2);
                data4null(field2, field1);
            }
        }
    }

    public void match7() {
        int field1 = Integer.parseInt(m14.getText());
        int field2 = Integer.parseInt(m13.getText());

        if (r1.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data2moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data2plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data2null(field2, field1);
            }
        }
        if (r2.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data3null(field2, field1);
            }
        }

        if (r3.isSelected()) {
            if (field1 > field2) {
                data2plus(field1, field2);
                data4moin(field2, field1);
            }
            if (field1 < field2) {
                data2moin(field1, field2);
                data4plus(field2, field1);
            }
            if (field1 == field2) {
                data2null(field1, field2);
                data4null(field2, field1);
            }

        }
    }

    public void match8() {
        int field1 = Integer.parseInt(m16.getText());
        int field2 = Integer.parseInt(m15.getText());

        if (r2.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data2moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data2plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data2null(field2, field1);
            }
        }
        if (r3.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data3null(field2, field1);
            }

        }

    }

    public void match9() {
        int field1 = Integer.parseInt(m18.getText());
        int field2 = Integer.parseInt(m17.getText());
        if (r3.isSelected() || r2.isSelected()) {
            if (field1 > field2) {
                data2plus(field1, field2);
                data3moin(field2, field1);
            }
            if (field1 < field2) {
                data2moin(field1, field2);
                data3plus(field2, field1);
            }
            if (field1 == field2) {
                data2null(field1, field2);
                data3null(field2, field1);
            }

        }
    }

    public void match10() {
        int field1 = Integer.parseInt(m20.getText());
        int field2 = Integer.parseInt(m19.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data4plus(field1, field2);
                data1moin(field2, field1);
            }
            if (field1 < field2) {
                data4moin(field1, field2);
                data1plus(field2, field1);
            }
            if (field1 == field2) {
                data4null(field1, field2);
                data1null(field2, field1);
            }

        }
    }

    public void match11() {
        int field1 = Integer.parseInt(m22.getText());
        int field2 = Integer.parseInt(m21.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data3plus(field1, field2);
                data4moin(field2, field1);
            }
            if (field1 < field2) {
                data3moin(field1, field2);
                data4plus(field2, field1);
            }
            if (field1 == field2) {
                data3null(field1, field2);
                data4null(field2, field1);
            }

        }
    }

    public void match12() {
        int field1 = Integer.parseInt(m24.getText());
        int field2 = Integer.parseInt(m23.getText());
        if (r3.isSelected()) {
            if (field1 > field2) {
                data1plus(field1, field2);
                data2moin(field2, field1);
            }
            if (field1 < field2) {
                data1moin(field1, field2);
                data2plus(field2, field1);
            }
            if (field1 == field2) {
                data1null(field1, field2);
                data2null(field2, field1);
            }

        }
    }

    public void setvisible2() {
        e7.setVisible(false);
        e8.setVisible(false);
        e9.setVisible(false);
        e10.setVisible(false);
        e11.setVisible(false);
        e12.setVisible(false);
        m7.setVisible(false);
        m8.setVisible(false);
        m9.setVisible(false);
        m10.setVisible(false);
        m11.setVisible(false);
        m12.setVisible(false);
        m19.setVisible(false);
        m20.setVisible(false);
        m21.setVisible(false);
        m22.setVisible(false);
        m23.setVisible(false);
        m24.setVisible(false);
        e19.setVisible(false);
        e20.setVisible(false);
        e21.setVisible(false);
        e22.setVisible(false);
        e23.setVisible(false);
        e24.setVisible(false);
    }

    public void setvisible1() {
        e3.setVisible(false);
        e4.setVisible(false);
        e5.setVisible(false);
        e6.setVisible(false);
        e7.setVisible(false);
        e8.setVisible(false);
        e9.setVisible(false);
        e10.setVisible(false);
        e11.setVisible(false);
        e12.setVisible(false);
        e15.setVisible(false);
        e16.setVisible(false);
        e17.setVisible(false);
        e18.setVisible(false);
        e19.setVisible(false);
        e20.setVisible(false);
        e21.setVisible(false);
        e22.setVisible(false);
        e23.setVisible(false);
        e24.setVisible(false);
        m3.setVisible(false);
        m4.setVisible(false);
        m5.setVisible(false);
        m6.setVisible(false);
        m7.setVisible(false);
        m8.setVisible(false);
        m9.setVisible(false);
        m10.setVisible(false);
        m11.setVisible(false);
        m12.setVisible(false);
        m15.setVisible(false);
        m16.setVisible(false);
        m17.setVisible(false);
        m18.setVisible(false);
        m19.setVisible(false);
        m20.setVisible(false);
        m21.setVisible(false);
        m22.setVisible(false);
        m23.setVisible(false);
        m24.setVisible(false);
    }

    public void Equality() {
        String remp1 = " 2";
        String remp2 = " 3";
        String remp3 = " 4";

        if (r1.isSelected() && eq1.equals(eq2)) {
            eq2 = eq2 + remp1;
        }

        if (r2.isSelected() && eq1.equals(eq2)) {
            eq2 = eq2 + remp1;
        }
        if (r2.isSelected() && eq1.equals(eq3)) {
            eq3 = eq3 + remp1;
        }
        if (r2.isSelected() && eq2.equals(eq3)) {
            eq3 = eq3 + remp1;
        }
        if (r2.isSelected() && eq1.equals(eq2) && eq2.equals(eq3)) {
            eq2 = eq2 + remp1;
            eq3 = eq3 + remp2;
        }
        if (r3.isSelected() && eq1.equals(eq2)) {
            eq2 = eq2 + remp1;
        }
        if (r3.isSelected() && eq1.equals(eq3)) {
            eq3 = eq3 + remp1;
        }
        if (r3.isSelected() && eq1.equals(eq4)) {
            eq4 = eq4 + remp1;
        }
        if (r3.isSelected() && eq2.equals(eq3)) {
            eq3 = eq3 + remp1;
        }
        if (r3.isSelected() && eq2.equals(eq4)) {
            eq4 = eq4 + remp1;
        }
        if (r3.isSelected() && eq3.equals(eq4)) {
            eq4 = eq4 + remp1;
        }
        if (r3.isSelected() && eq1.equals(eq2) && eq2.equals(eq3)) {
            eq2 = eq2 + remp1;
            eq3 = eq2 + remp2;
        }
        if (r3.isSelected() && eq1.equals(eq2) && eq2.equals(eq3)
                    && eq3.equals(eq4)) {
            eq2 = eq2 + remp1;
            eq3 = eq2 + remp2;
            eq4 = eq2 + remp3;
        }
    }

    public void setV3() {
        if (r2.isSelected() && eq1 != null && eq2 != null && eq3 != null) {
            Randomize3();
            SelectTeams.setVisible(false);
            SelectTeamsNomber.setVisible(false);
            pnclassemt.setVisible(true);
            tab1.setDisable(false);
            tab2.setDisable(false);
            tab3.setDisable(false);

            tabscore.setDisable(false);
            Equality();
        }

    }

    public void equal3() {
        if (r2.isSelected() && eq1 != null && eq2 != null && eq3 != null) {
            Randomize3();
            SelectTeams.setVisible(false);
            SelectTeamsNomber.setVisible(false);
            pnclassemt.setVisible(true);
            tab1.setDisable(false);
            tab2.setDisable(false);
            tab3.setDisable(false);
            data1[8] = 1;
            data2[8] = 2;
            data3[8] = 3;

            tabscore.setDisable(false);
            Equality();

            if (A == 1 && B == 2 && C == 3) {
                e1.setText(C1.getValue());
                e2.setText(C2.getValue());
                e3.setText(C3.getValue());
                e4.setText(C1.getValue());
                e5.setText(C2.getValue());
                e6.setText(C3.getValue());
                e13.setText(C1.getValue());
                e14.setText(C3.getValue());
                e15.setText(C2.getValue());
                e16.setText(C1.getValue());
                e17.setText(C3.getValue());
                e18.setText(C2.getValue());
                setvisible2();
            } else if (A == 2 && B == 3 && C == 1) {
                e1.setText(C2.getValue());
                e2.setText(C3.getValue());
                e3.setText(C1.getValue());
                e4.setText(C2.getValue());
                e5.setText(C3.getValue());
                e6.setText(C1.getValue());
                e13.setText(C2.getValue());
                e14.setText(C1.getValue());
                e15.setText(C1.getValue());
                e16.setText(C3.getValue());
                e17.setText(C3.getValue());
                e18.setText(C2.getValue());
                setvisible2();
            } else if (A == 3 && B == 1 && C == 2) {
                e1.setText(C3.getValue());
                e2.setText(C1.getValue());
                e3.setText(C2.getValue());
                e4.setText(C3.getValue());
                e5.setText(C1.getValue());
                e6.setText(C2.getValue());
                e13.setText(C3.getValue());
                e14.setText(C2.getValue());
                e15.setText(C2.getValue());
                e16.setText(C1.getValue());
                e17.setText(C1.getValue());
                e18.setText(C3.getValue());
                setvisible2();
            } else if (A == 3 && B == 2 && C == 1) {
                e1.setText(C3.getValue());
                e2.setText(C2.getValue());
                e3.setText(C1.getValue());
                e4.setText(C3.getValue());
                e5.setText(C2.getValue());
                e6.setText(C1.getValue());
                e13.setText(C3.getValue());
                e14.setText(C1.getValue());
                e15.setText(C1.getValue());
                e16.setText(C2.getValue());
                e17.setText(C2.getValue());
                e18.setText(C3.getValue());
                setvisible2();
            } else if (A == 2 && B == 1 && C == 3) {
                e1.setText(C2.getValue());
                e2.setText(C1.getValue());
                e3.setText(C3.getValue());
                e4.setText(C2.getValue());
                e5.setText(C1.getValue());
                e6.setText(C3.getValue());
                e13.setText(C2.getValue());
                e14.setText(C3.getValue());
                e15.setText(C3.getValue());
                e16.setText(C1.getValue());
                e17.setText(C1.getValue());
                e18.setText(C2.getValue());
                setvisible2();
            } else if (A == 1 && B == 3 && C == 2) {
                e1.setText(C1.getValue());
                e2.setText(C3.getValue());
                e3.setText(C2.getValue());
                e4.setText(C1.getValue());
                e5.setText(C3.getValue());
                e6.setText(C2.getValue());
                e13.setText(C1.getValue());
                e14.setText(C2.getValue());
                e15.setText(C2.getValue());
                e16.setText(C3.getValue());
                e17.setText(C3.getValue());
                e18.setText(C1.getValue());
                setvisible2();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Selection");
            alert.setHeaderText("Look, an Error Select nomber team");
            alert.showAndWait();
        }
    }

    public void equal2() {
        if (r1.isSelected() && eq1 != null && eq2 != null) {
            Equality();
            SelectTeams.setVisible(false);
            SelectTeamsNomber.setVisible(false);
            pnclassemt.setVisible(true);
            setTab2Text("Matchs", eq1, eq2, eq3, eq4);
            tab1.setDisable(false);
            tab2.setDisable(false);
            tabscore.setDisable(false);

            if (A == 2 || B == 1) {
                e2.setText(C1.getValue());
                e1.setText(C2.getValue());
                e13.setText(C1.getValue());
                e14.setText(C2.getValue());
                setvisible1();

            } else {
                e1.setText(eq1);
                e2.setText(eq2);
                e13.setText(eq2);
                e14.setText(eq1);
                setvisible1();
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Selection");
            alert.setHeaderText("Look, an Error Select nomber team");
            alert.showAndWait();
        }
    }

    public void equal4() {
        if (r3.isSelected() && eq1 != null && eq2 != null && eq3 != null && eq4 != null) {
            Equality();
            Randomize4();
            SelectTeams.setVisible(false);
            SelectTeamsNomber.setVisible(false);
            pnclassemt.setVisible(true);
            tab1.setDisable(false);
            tab2.setDisable(false);
            tab3.setDisable(false);
            tab4.setDisable(false);
            tabscore.setDisable(false);

            if (A == 1 && B == 2 && C == 3 && D == 4) {
                e1.setText(C1.getValue());
                e2.setText(C2.getValue());

                e3.setText(C3.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C1.getValue());

                e7.setText(C2.getValue());
                e8.setText(C3.getValue());

                e9.setText(C1.getValue());
                e10.setText(C3.getValue());

                e11.setText(C2.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C2.getValue());

                e15.setText(C3.getValue());
                e16.setText(C1.getValue());

                e17.setText(C3.getValue());
                e18.setText(C2.getValue());

                e19.setText(C1.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C3.getValue());

                e23.setText(C2.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 1 && B == 3 && C == 2 && D == 4) {
                e1.setText(C1.getValue());
                e2.setText(C3.getValue());

                e3.setText(C2.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C1.getValue());

                e7.setText(C3.getValue());
                e8.setText(C2.getValue());

                e9.setText(C1.getValue());
                e10.setText(C2.getValue());

                e11.setText(C3.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C3.getValue());

                e15.setText(C2.getValue());
                e16.setText(C1.getValue());

                e17.setText(C2.getValue());
                e18.setText(C3.getValue());

                e19.setText(C1.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C2.getValue());

                e23.setText(C3.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 1 && B == 3 && C == 4 && D == 2) {
                e1.setText(C1.getValue());
                e2.setText(C3.getValue());

                e3.setText(C4.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C1.getValue());

                e7.setText(C3.getValue());
                e8.setText(C4.getValue());

                e9.setText(C1.getValue());
                e10.setText(C4.getValue());

                e11.setText(C3.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C3.getValue());

                e15.setText(C4.getValue());
                e16.setText(C1.getValue());

                e17.setText(C4.getValue());
                e18.setText(C3.getValue());

                e19.setText(C1.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C4.getValue());

                e23.setText(C3.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 1 && B == 2 && C == 4 && D == 3) {
                e1.setText(C1.getValue());
                e2.setText(C2.getValue());

                e3.setText(C4.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C1.getValue());

                e7.setText(C2.getValue());
                e8.setText(C4.getValue());

                e9.setText(C1.getValue());
                e10.setText(C4.getValue());

                e11.setText(C2.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C2.getValue());

                e15.setText(C4.getValue());
                e16.setText(C1.getValue());

                e17.setText(C4.getValue());
                e18.setText(C2.getValue());

                e19.setText(C1.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C4.getValue());

                e23.setText(C2.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 1 && B == 4 && C == 2 && D == 3) {
                e1.setText(C1.getValue());
                e2.setText(C4.getValue());

                e3.setText(C3.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C1.getValue());

                e7.setText(C4.getValue());
                e8.setText(C3.getValue());

                e9.setText(C1.getValue());
                e10.setText(C3.getValue());

                e11.setText(C4.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C4.getValue());

                e15.setText(C3.getValue());
                e16.setText(C1.getValue());

                e17.setText(C3.getValue());
                e18.setText(C4.getValue());

                e19.setText(C1.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C3.getValue());

                e23.setText(C4.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 1 && B == 4 && C == 2 && D == 3) {
                e1.setText(C1.getValue());
                e2.setText(C4.getValue());

                e3.setText(C2.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C1.getValue());

                e7.setText(C4.getValue());
                e8.setText(C2.getValue());

                e9.setText(C1.getValue());
                e10.setText(C2.getValue());

                e11.setText(C4.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C4.getValue());

                e15.setText(C2.getValue());
                e16.setText(C1.getValue());

                e17.setText(C2.getValue());
                e18.setText(C4.getValue());

                e19.setText(C1.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C2.getValue());

                e23.setText(C4.getValue());
                e24.setText(C1.getValue());
            }
            if (A == 2 && B == 1 && C == 4 && D == 3) {
                e1.setText(C2.getValue());
                e2.setText(C1.getValue());

                e3.setText(C4.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C2.getValue());

                e7.setText(C1.getValue());
                e8.setText(C4.getValue());

                e9.setText(C2.getValue());
                e10.setText(C4.getValue());

                e11.setText(C1.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C1.getValue());

                e15.setText(C4.getValue());
                e16.setText(C2.getValue());

                e17.setText(C4.getValue());
                e18.setText(C1.getValue());

                e19.setText(C2.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C4.getValue());

                e23.setText(C1.getValue());
                e24.setText(C2.getValue());
            }
            if (A == 2 && B == 3 && C == 1 && D == 4) {
                e1.setText(C2.getValue());
                e2.setText(C3.getValue());

                e3.setText(C1.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C2.getValue());

                e7.setText(C3.getValue());
                e8.setText(C1.getValue());

                e9.setText(C2.getValue());
                e10.setText(C1.getValue());

                e11.setText(C3.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C3.getValue());

                e15.setText(C1.getValue());
                e16.setText(C2.getValue());

                e17.setText(C1.getValue());
                e18.setText(C3.getValue());

                e19.setText(C2.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C1.getValue());

                e23.setText(C3.getValue());
                e24.setText(C2.getValue());
            }
            if (A == 2 && B == 4 && C == 3 && D == 1) {
                e1.setText(C2.getValue());
                e2.setText(C4.getValue());

                e3.setText(C3.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C2.getValue());

                e7.setText(C4.getValue());
                e8.setText(C3.getValue());

                e9.setText(C2.getValue());
                e10.setText(C3.getValue());

                e11.setText(C4.getValue());
                e12.setText(C1.getValue());

                e13.setText(C1.getValue());
                e14.setText(C4.getValue());

                e15.setText(C3.getValue());
                e16.setText(C2.getValue());

                e17.setText(C3.getValue());
                e18.setText(C4.getValue());

                e19.setText(C2.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C3.getValue());

                e23.setText(C4.getValue());
                e24.setText(C2.getValue());
            }
            if (A == 2 && B == 4 && C == 1 && D == 3) {
                e1.setText(C2.getValue());
                e2.setText(C4.getValue());

                e3.setText(C1.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C2.getValue());

                e7.setText(C4.getValue());
                e8.setText(C1.getValue());

                e9.setText(C2.getValue());
                e10.setText(C1.getValue());

                e11.setText(C4.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C4.getValue());

                e15.setText(C1.getValue());
                e16.setText(C2.getValue());

                e17.setText(C1.getValue());
                e18.setText(C4.getValue());

                e19.setText(C2.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C1.getValue());

                e23.setText(C4.getValue());
                e24.setText(C2.getValue());
            }
            if (A == 2 && B == 3 && C == 4 && D == 1) {
                e1.setText(C2.getValue());
                e2.setText(C3.getValue());

                e3.setText(C4.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C2.getValue());

                e7.setText(C3.getValue());
                e8.setText(C4.getValue());

                e9.setText(C2.getValue());
                e10.setText(C4.getValue());

                e11.setText(C3.getValue());
                e12.setText(C1.getValue());

                e13.setText(C1.getValue());
                e14.setText(C3.getValue());

                e15.setText(C4.getValue());
                e16.setText(C2.getValue());

                e17.setText(C4.getValue());
                e18.setText(C3.getValue());

                e19.setText(C2.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C4.getValue());

                e23.setText(C3.getValue());
                e24.setText(C2.getValue());
            }
            if (A == 2 && B == 1 && C == 3 && D == 4) {
                e1.setText(C2.getValue());
                e2.setText(C1.getValue());

                e3.setText(C3.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C2.getValue());

                e7.setText(C1.getValue());
                e8.setText(C3.getValue());

                e9.setText(C2.getValue());
                e10.setText(C3.getValue());

                e11.setText(C1.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C1.getValue());

                e15.setText(C3.getValue());
                e16.setText(C2.getValue());

                e17.setText(C3.getValue());
                e18.setText(C1.getValue());

                e19.setText(C2.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C3.getValue());

                e23.setText(C1.getValue());
                e24.setText(C2.getValue());
            }

            if (A == 3 && B == 2 && C == 1 && D == 4) {
                e1.setText(C3.getValue());
                e2.setText(C2.getValue());

                e3.setText(C1.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C3.getValue());

                e7.setText(C2.getValue());
                e8.setText(C1.getValue());

                e9.setText(C3.getValue());
                e10.setText(C1.getValue());

                e11.setText(C2.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C2.getValue());

                e15.setText(C1.getValue());
                e16.setText(C3.getValue());

                e17.setText(C1.getValue());
                e18.setText(C2.getValue());

                e19.setText(C3.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C1.getValue());

                e23.setText(C2.getValue());
                e24.setText(C3.getValue());
            }

            if (A == 3 && B == 2 && C == 4 && D == 1) {
                e1.setText(C3.getValue());
                e2.setText(C2.getValue());

                e3.setText(C4.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C3.getValue());

                e7.setText(C2.getValue());
                e8.setText(C4.getValue());

                e9.setText(C3.getValue());
                e10.setText(C4.getValue());

                e11.setText(C2.getValue());
                e12.setText(C1.getValue());

                e13.setText(C1.getValue());
                e14.setText(C2.getValue());

                e15.setText(C4.getValue());
                e16.setText(C3.getValue());

                e17.setText(C4.getValue());
                e18.setText(C2.getValue());

                e19.setText(C3.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C4.getValue());

                e23.setText(C2.getValue());
                e24.setText(C3.getValue());
            }
            if (A == 3 && B == 4 && C == 2 && D == 1) {
                e1.setText(C3.getValue());
                e2.setText(C4.getValue());

                e3.setText(C2.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C3.getValue());

                e7.setText(C4.getValue());
                e8.setText(C2.getValue());

                e9.setText(C3.getValue());
                e10.setText(C2.getValue());

                e11.setText(C4.getValue());
                e12.setText(C1.getValue());

                e13.setText(C1.getValue());
                e14.setText(C4.getValue());

                e15.setText(C2.getValue());
                e16.setText(C3.getValue());

                e17.setText(C2.getValue());
                e18.setText(C4.getValue());

                e19.setText(C3.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C2.getValue());

                e23.setText(C4.getValue());
                e24.setText(C3.getValue());
            }
            if (A == 3 && B == 4 && C == 1 && D == 2) {
                e1.setText(C3.getValue());
                e2.setText(C4.getValue());

                e3.setText(C1.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C3.getValue());

                e7.setText(C4.getValue());
                e8.setText(C1.getValue());

                e9.setText(C3.getValue());
                e10.setText(C1.getValue());

                e11.setText(C4.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C4.getValue());

                e15.setText(C1.getValue());
                e16.setText(C3.getValue());

                e17.setText(C1.getValue());
                e18.setText(C4.getValue());

                e19.setText(C3.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C1.getValue());

                e23.setText(C4.getValue());
                e24.setText(C3.getValue());
            }
            if (A == 3 && B == 1 && C == 2 && D == 4) {
                e1.setText(C3.getValue());
                e2.setText(C1.getValue());

                e3.setText(C2.getValue());
                e4.setText(C4.getValue());

                e5.setText(C4.getValue());
                e6.setText(C3.getValue());

                e7.setText(C1.getValue());
                e8.setText(C2.getValue());

                e9.setText(C3.getValue());
                e10.setText(C2.getValue());

                e11.setText(C1.getValue());
                e12.setText(C4.getValue());

                e13.setText(C4.getValue());
                e14.setText(C1.getValue());

                e15.setText(C2.getValue());
                e16.setText(C3.getValue());

                e17.setText(C2.getValue());
                e18.setText(C1.getValue());

                e19.setText(C3.getValue());
                e20.setText(C4.getValue());

                e21.setText(C4.getValue());
                e22.setText(C2.getValue());

                e23.setText(C1.getValue());
                e24.setText(C3.getValue());
            }
            if (A == 3 && B == 1 && C == 4 && D == 2) {
                e1.setText(C3.getValue());
                e2.setText(C1.getValue());

                e3.setText(C4.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C3.getValue());

                e7.setText(C1.getValue());
                e8.setText(C4.getValue());

                e9.setText(C3.getValue());
                e10.setText(C4.getValue());

                e11.setText(C1.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C1.getValue());

                e15.setText(C4.getValue());
                e16.setText(C3.getValue());

                e17.setText(C4.getValue());
                e18.setText(C1.getValue());

                e19.setText(C3.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C4.getValue());

                e23.setText(C1.getValue());
                e24.setText(C3.getValue());
            }
            if (A == 4 && B == 1 && C == 3 && D == 2) {
                e1.setText(C4.getValue());
                e2.setText(C1.getValue());

                e3.setText(C3.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C4.getValue());

                e7.setText(C1.getValue());
                e8.setText(C3.getValue());

                e9.setText(C4.getValue());
                e10.setText(C3.getValue());

                e11.setText(C1.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C1.getValue());

                e15.setText(C3.getValue());
                e16.setText(C4.getValue());

                e17.setText(C3.getValue());
                e18.setText(C1.getValue());

                e19.setText(C4.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C3.getValue());

                e23.setText(C1.getValue());
                e24.setText(C4.getValue());
            }
            if (A == 4 && B == 2 && C == 3 && D == 1) {
                e1.setText(C4.getValue());
                e2.setText(C2.getValue());

                e3.setText(C3.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C4.getValue());

                e7.setText(C2.getValue());
                e8.setText(C3.getValue());

                e9.setText(C4.getValue());
                e10.setText(C3.getValue());

                e11.setText(C2.getValue());
                e12.setText(C1.getValue());

                e13.setText(C2.getValue());
                e14.setText(C1.getValue());

                e15.setText(C3.getValue());
                e16.setText(C4.getValue());

                e17.setText(C3.getValue());
                e18.setText(C2.getValue());

                e19.setText(C4.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C3.getValue());

                e23.setText(C2.getValue());
                e24.setText(C4.getValue());
            }
            if (A == 4 && B == 2 && C == 1 && D == 3) {
                e1.setText(C4.getValue());
                e2.setText(C2.getValue());

                e3.setText(C1.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C4.getValue());

                e7.setText(C2.getValue());
                e8.setText(C1.getValue());

                e9.setText(C4.getValue());
                e10.setText(C1.getValue());

                e11.setText(C2.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C2.getValue());

                e15.setText(C1.getValue());
                e16.setText(C4.getValue());

                e17.setText(C1.getValue());
                e18.setText(C2.getValue());

                e19.setText(C4.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C1.getValue());

                e23.setText(C2.getValue());
                e24.setText(C4.getValue());
            }
            if (A == 4 && B == 3 && C == 2 && D == 1) {
                e1.setText(C4.getValue());
                e2.setText(C3.getValue());

                e3.setText(C2.getValue());
                e4.setText(C1.getValue());

                e5.setText(C1.getValue());
                e6.setText(C4.getValue());

                e7.setText(C3.getValue());
                e8.setText(C2.getValue());

                e9.setText(C4.getValue());
                e10.setText(C2.getValue());

                e11.setText(C3.getValue());
                e12.setText(C1.getValue());

                e13.setText(C1.getValue());
                e14.setText(C3.getValue());

                e15.setText(C2.getValue());
                e16.setText(C4.getValue());

                e17.setText(C2.getValue());
                e18.setText(C3.getValue());

                e19.setText(C4.getValue());
                e20.setText(C1.getValue());

                e21.setText(C1.getValue());
                e22.setText(C2.getValue());

                e23.setText(C3.getValue());
                e24.setText(C4.getValue());
            }
            if (A == 4 && B == 3 && C == 1 && D == 2) {
                e1.setText(C4.getValue());
                e2.setText(C3.getValue());

                e3.setText(C1.getValue());
                e4.setText(C2.getValue());

                e5.setText(C2.getValue());
                e6.setText(C4.getValue());

                e7.setText(C3.getValue());
                e8.setText(C1.getValue());

                e9.setText(C4.getValue());
                e10.setText(C1.getValue());

                e11.setText(C3.getValue());
                e12.setText(C2.getValue());

                e13.setText(C2.getValue());
                e14.setText(C3.getValue());

                e15.setText(C1.getValue());
                e16.setText(C4.getValue());

                e17.setText(C1.getValue());
                e18.setText(C3.getValue());

                e19.setText(C4.getValue());
                e20.setText(C2.getValue());

                e21.setText(C2.getValue());
                e22.setText(C1.getValue());

                e23.setText(C3.getValue());
                e24.setText(C4.getValue());
            }
            if (A == 4 && B == 1 && C == 2 && D == 3) {
                e1.setText(C4.getValue());
                e2.setText(C1.getValue());

                e3.setText(C2.getValue());
                e4.setText(C3.getValue());

                e5.setText(C3.getValue());
                e6.setText(C4.getValue());

                e7.setText(C1.getValue());
                e8.setText(C2.getValue());

                e9.setText(C4.getValue());
                e10.setText(C2.getValue());

                e11.setText(C1.getValue());
                e12.setText(C3.getValue());

                e13.setText(C3.getValue());
                e14.setText(C1.getValue());

                e15.setText(C2.getValue());
                e16.setText(C4.getValue());

                e17.setText(C2.getValue());
                e18.setText(C1.getValue());

                e19.setText(C4.getValue());
                e20.setText(C3.getValue());

                e21.setText(C3.getValue());
                e22.setText(C2.getValue());

                e23.setText(C1.getValue());
                e24.setText(C4.getValue());
            }

        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Selection");
            alert.setHeaderText("Look, an Error Select nomber team");
            alert.showAndWait();
        }
    }

    public void buildtab() {
        for (int i = 0; i < 8; i++) {
            data1[i] = 0;
            data2[i] = 0;
            data3[i] = 0;
            data4[i] = 0;
        }

        N.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("N"));
        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("NomEquipe"));
        mj.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("mje"));
        mg.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("mge"));
        mn.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("mne"));
        mp.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("mpe"));
        bp.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("bpe"));
        bc.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("bce"));
        dfb.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("dfb"));
        pointE.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("Point"));

        if (r1.isSelected()) {
            loadimage();
            Randomize2();
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        } else if (r2.isSelected()) {
            loadimage();
            Randomize3();
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        } else if (r3.isSelected()) {
            loadimage();
            Randomize4();
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));

        }
        tableview1.setFixedCellSize(75);

    }

    public void Randomize2() {
        A = random1.nextInt(2) + 1;
        do {
            B = random1.nextInt(2) + 1;
        } while (A == B);
        if (A == 1 && B == 2) {
            eq1 = C1.getValue();
            eq2 = C2.getValue();
        } else {
            eq1 = C2.getValue();
            eq2 = C1.getValue();
        }

    }

    public void Randomize3() {

        A = random1.nextInt(3) + 1;
        do {
            B = random1.nextInt(3) + 1;
            C = random1.nextInt(3) + 1;
        } while (A == B || B == C || C == A);

        if (A == 1 && B == 2 && C == 3) {
            eq1 = C1.getValue();
            eq2 = C2.getValue();
            eq3 = C3.getValue();

        } else if (A == 2 && B == 1 && C == 3) {
            eq1 = C2.getValue();
            eq2 = C1.getValue();
            eq3 = C3.getValue();
        } else if (A == 3 && B == 2 && C == 1) {
            eq1 = C3.getValue();
            eq2 = C2.getValue();
            eq3 = C1.getValue();
        } else if (A == 1 && B == 3 && C == 2) {
            eq1 = C1.getValue();
            eq2 = C3.getValue();
            eq3 = C2.getValue();
        } else if (A == 2 && B == 3 && C == 1) {
            eq1 = C3.getValue();
            eq2 = C1.getValue();
            eq3 = C2.getValue();
        } else if (A == 3 && B == 1 && C == 2) {
            eq1 = C2.getValue();
            eq2 = C3.getValue();
            eq3 = C1.getValue();
        }

    }

    public void Randomize4() {

        A = random1.nextInt(4) + 1;
        do {
            B = random1.nextInt(4) + 1;
            C = random1.nextInt(4) + 1;
            D = random1.nextInt(4) + 1;
        } while (A == B || B == C || C == A || D == A || D == B || D == C);

        if (A == 1 && B == 2 && C == 3 && D == 4) {
            eq1 = C1.getValue();
            eq2 = C2.getValue();
            eq3 = C3.getValue();
            eq4 = C4.getValue();
        }
        if (A == 1 && B == 3 && C == 2 && D == 4) {
            eq1 = C1.getValue();
            eq2 = C3.getValue();
            eq3 = C2.getValue();
            eq4 = C4.getValue();
        }
        if (A == 1 && B == 3 && C == 4 && D == 2) {
            eq1 = C1.getValue();
            eq2 = C3.getValue();
            eq3 = C4.getValue();
            eq4 = C2.getValue();
        }
        if (A == 1 && B == 2 && C == 4 && D == 3) {
            eq1 = C1.getValue();
            eq2 = C2.getValue();
            eq3 = C4.getValue();
            eq4 = C3.getValue();
        }
        if (A == 1 && B == 4 && C == 3 && D == 2) {
            eq1 = C1.getValue();
            eq2 = C4.getValue();
            eq3 = C3.getValue();
            eq4 = C2.getValue();
        }
        if (A == 1 && B == 4 && C == 2 && D == 3) {
            eq1 = C1.getValue();
            eq2 = C4.getValue();
            eq3 = C2.getValue();
            eq4 = C3.getValue();
        }
        if (A == 2 && B == 1 && C == 3 && D == 4) {
            eq1 = C2.getValue();
            eq2 = C1.getValue();
            eq3 = C3.getValue();
            eq4 = C4.getValue();
        }
        if (A == 2 && B == 1 && C == 4 && D == 3) {
            eq1 = C2.getValue();
            eq2 = C1.getValue();
            eq3 = C4.getValue();
            eq4 = C3.getValue();
        }
        if (A == 2 && B == 3 && C == 1 && D == 4) {
            eq1 = C2.getValue();
            eq2 = C3.getValue();
            eq3 = C1.getValue();
            eq4 = C4.getValue();
        }
        if (A == 2 && B == 4 && C == 3 && D == 1) {
            eq1 = C4.getValue();
            eq2 = C1.getValue();
            eq3 = C3.getValue();
            eq4 = C2.getValue();
        }
        if (A == 2 && B == 4 && C == 1 && D == 3) {
            eq1 = C3.getValue();
            eq2 = C1.getValue();
            eq3 = C4.getValue();
            eq4 = C2.getValue();
        }
        if (A == 2 && B == 3 && C == 1 && D == 4) {
            eq1 = C3.getValue();
            eq2 = C1.getValue();
            eq3 = C2.getValue();
            eq4 = C4.getValue();
        }
        if (A == 3 && B == 2 && C == 1 && D == 4) {
            eq1 = C3.getValue();
            eq2 = C2.getValue();
            eq3 = C1.getValue();
            eq4 = C4.getValue();
        }
        if (A == 3 && B == 2 && C == 4 && D == 1) {
            eq1 = C4.getValue();
            eq2 = C2.getValue();
            eq3 = C1.getValue();
            eq4 = C3.getValue();
        }
        if (A == 3 && B == 4 && C == 2 && D == 1) {
            eq1 = C4.getValue();
            eq2 = C3.getValue();
            eq3 = C1.getValue();
            eq4 = C2.getValue();
        }
        if (A == 3 && B == 4 && C == 1 && D == 2) {
            eq1 = C3.getValue();
            eq2 = C4.getValue();
            eq3 = C1.getValue();
            eq4 = C2.getValue();
        }
        if (A == 3 && B == 1 && C == 2 && D == 4) {
            eq1 = C2.getValue();
            eq2 = C3.getValue();
            eq3 = C1.getValue();
            eq4 = C4.getValue();
        }
        if (A == 3 && B == 1 && C == 4 && D == 2) {
            eq1 = C2.getValue();
            eq2 = C4.getValue();
            eq3 = C1.getValue();
            eq4 = C3.getValue();
        }
        if (A == 4 && B == 1 && C == 3 && D == 2) {
            eq1 = C2.getValue();
            eq2 = C4.getValue();
            eq3 = C3.getValue();
            eq4 = C1.getValue();
        }
        if (A == 4 && B == 2 && C == 3 && D == 1) {
            eq1 = C4.getValue();
            eq2 = C2.getValue();
            eq3 = C3.getValue();
            eq4 = C1.getValue();
        }
        if (A == 4 && B == 1 && C == 2 && D == 3) {
            eq1 = C2.getValue();
            eq2 = C3.getValue();
            eq3 = C4.getValue();
            eq4 = C1.getValue();

        }
        if (A == 4 && B == 3 && C == 1 && D == 2) {
            eq1 = C3.getValue();
            eq2 = C3.getValue();
            eq3 = C2.getValue();
            eq4 = C1.getValue();

        }
        if (A == 4 && B == 3 && C == 2 && D == 1) {
            eq1 = C4.getValue();
            eq2 = C3.getValue();
            eq3 = C2.getValue();
            eq4 = C1.getValue();
        }
        if (A == 4 && B == 2 && C == 1 && D == 3) {
            eq1 = C3.getValue();
            eq2 = C2.getValue();
            eq3 = C4.getValue();
            eq4 = C1.getValue();
        }

    }

    @FXML
    void Class(ActionEvent event) {
        if (r1.isSelected()) {
            match1();
            C1();
        }

        if (r2.isSelected()) {
            match1();
            C2();
        }

        if (r3.isSelected()) {
            match1();
            C3();
        }
    }

    @FXML
    void Class1(ActionEvent event) {
        if (r2.isSelected()) {
            match2();
            C2();
        }
        if (r3.isSelected()) {
            match2();
            C3();
        }
    }

    @FXML
    void Class2(ActionEvent event) {
        if (r2.isSelected()) {
            match3();
            C2();
        }
        if (r3.isSelected()) {
            match3();
            C3();
        }
    }

    @FXML
    void Class3(ActionEvent event) {
        match4();
        C3();
    }

    @FXML
    void Class4(ActionEvent event) {
        match5();
        C3();
    }

    @FXML
    void Class5(ActionEvent event) {
        match6();
        C3();
    }

    @FXML
    void Compe(ActionEvent event) {
        if (r1.isSelected()) {
            match7();
            C1();
        }

        if (r2.isSelected()) {
            match7();
            C2();
        }

        if (r3.isSelected()) {
            match7();
            C3();
        }
    }

    @FXML
    void Compe1(ActionEvent event) {
        if (r2.isSelected()) {
            match8();
            C2();
        }
        if (r3.isSelected()) {
            match8();
            C3();
        }
    }

    @FXML
    void Compe2(ActionEvent event) {
        if (r2.isSelected()) {
            match9();
            C2();
        }
        if (r3.isSelected()) {
            match9();
            C3();
        }
    }

    @FXML
    void Compe3(ActionEvent event) {
        if (r3.isSelected()) {
            match10();
            C3();
        }
    }

    @FXML
    void Compe4(ActionEvent event) {
        if (r3.isSelected()) {
            match11();
            C3();
        }
    }

    @FXML
    void Compe5(ActionEvent event) {
        if (r3.isSelected()) {
            match12();
            C3();
        }
    }

    @FXML
    void ActionExcel(ActionEvent event) throws Exception {
        if (winner.getText().equals("")) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("SomeThing WARNING !");
            alert.setHeaderText("Choose Winner Name !!");
            alert.showAndWait();
        } else {
            writeExcel();
        }
    }

    public void C1() {
        tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                    data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                    data2[4], data2[5], data2[6], data2[7])));
    }

    public void C2() {

        data1[8] = 1;
        data2[8] = 2;
        data3[8] = 3;
        tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                    data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                    data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                    data3[4], data3[5], data3[6], data3[7])));
    }

    public void C3() {
        String equ1 = e1.getText();
        String equ2 = e2.getText();
        String equ3 = e3.getText();
        String equ4 = e4.getText();
        if (eq1.equals(equ1) && eq2.equals(equ2) && eq3.equals(equ3) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ1) && eq2.equals(equ3) && eq3.equals(equ2) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ1) && eq2.equals(equ3) && eq3.equals(equ4) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }
        if (eq1.equals(equ1) && eq2.equals(equ2) && eq3.equals(equ4) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ1) && eq2.equals(equ4) && eq3.equals(equ3) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }
        if (eq1.equals(equ1) && eq2.equals(equ4) && eq3.equals(equ2) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ1) && eq3.equals(equ4) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ4) && eq3.equals(equ1) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ4) && eq3.equals(equ3) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ3) && eq3.equals(equ4) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ3) && eq3.equals(equ1) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ2) && eq2.equals(equ1) && eq3.equals(equ3) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ2) && eq3.equals(equ1) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ2) && eq3.equals(equ4) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ4) && eq3.equals(equ1) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ4) && eq3.equals(equ2) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ1) && eq3.equals(equ2) && eq4.equals(equ4)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7])));
        }
        if (eq1.equals(equ3) && eq2.equals(equ1) && eq3.equals(equ4) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ1) && eq3.equals(equ3) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ1) && eq3.equals(equ2) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ2) && eq3.equals(equ1) && eq4.equals(equ3)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ2) && eq3.equals(equ3) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(3, eq3, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ3) && eq3.equals(equ2) && eq4.equals(equ1)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7]), new Equipe(4, eq4, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7])));
        }
        if (eq1.equals(equ4) && eq2.equals(equ3) && eq3.equals(equ1) && eq4.equals(equ2)) {
            tableview1.setItems(FXCollections.observableArrayList(new Equipe(1, eq1, data4[0], data4[1], data4[2], data4[3],
                        data4[4], data4[5], data4[6], data4[7]), new Equipe(2, eq2, data3[0], data3[1], data3[2], data3[3],
                        data3[4], data3[5], data3[6], data3[7]), new Equipe(3, eq3, data1[0], data1[1], data1[2], data1[3],
                        data1[4], data1[5], data1[6], data1[7]), new Equipe(4, eq4, data2[0], data2[1], data2[2], data2[3],
                        data2[4], data2[5], data2[6], data2[7])));
        }

    }

    public Collection<String> getTournoiwin() {
        C3();
        tableview1.getItems();
        Collection<String> list = new ArrayList<String>();

        return list;
    }

    public void writeExcel() throws Exception {

        try {

            int t1 = 0, t2 = 0, t3 = 0, t4 = 0;
            Calendar now = Calendar.getInstance();
            String date = now.get(Calendar.DATE) + (now.get(Calendar.MONTH) + 1 + "" + now.get(Calendar.YEAR));
            int re1[] = new int[12];
            int re2[] = new int[12];
            int re3[] = new int[12];
            int re4[] = new int[12];
            if (r3.isSelected()) {

                re1[1] = Integer.parseInt(m1.getText());
                re2[1] = Integer.parseInt(m2.getText());
                re3[1] = Integer.parseInt(m3.getText());
                re4[1] = Integer.parseInt(m4.getText());

                re4[2] = Integer.parseInt(m5.getText());
                re1[2] = Integer.parseInt(m6.getText());
                re2[2] = Integer.parseInt(m7.getText());
                re3[2] = Integer.parseInt(m8.getText());

                re1[3] = Integer.parseInt(m9.getText());
                re3[3] = Integer.parseInt(m10.getText());
                re2[3] = Integer.parseInt(m11.getText());
                re4[3] = Integer.parseInt(m12.getText());

                re4[4] = Integer.parseInt(m13.getText());
                re2[4] = Integer.parseInt(m14.getText());
                re3[4] = Integer.parseInt(m15.getText());
                re1[4] = Integer.parseInt(m16.getText());

                re3[5] = Integer.parseInt(m17.getText());
                re2[5] = Integer.parseInt(m18.getText());
                re1[5] = Integer.parseInt(m19.getText());
                re4[5] = Integer.parseInt(m20.getText());

                re4[6] = Integer.parseInt(m21.getText());
                re3[6] = Integer.parseInt(m22.getText());
                re2[6] = Integer.parseInt(m23.getText());
                re1[6] = Integer.parseInt(m24.getText());

                re1[7] = re1[1] + re1[2] + re1[3] + re1[4] + re1[5] + re1[6];
                re2[7] = re2[1] + re2[2] + re2[3] + re2[4] + re2[5] + re2[6];
                re3[7] = re3[1] + re3[2] + re3[3] + re3[4] + re3[5] + re3[6];
                re4[7] = re4[1] + re4[2] + re4[3] + re4[4] + re4[5] + re4[6];

                if (re1[1] > re2[1]) {
                    t1 = t1 + 3;
                }
                if (re2[1] > re1[1]) {
                    t2 = t2 + 3;
                }
                if (re2[1] == re1[1]) {
                    t1 = t1 + 1;
                    t2 = t2 + 1;
                }
                if (re3[1] > re4[1]) {
                    t3 = t3 + 3;
                }
                if (re4[1] > re3[1]) {
                    t4 = t4 + 3;
                }
                if (re3[1] == re4[1]) {
                    t3 = t4 + 1;
                    t3 = t4 + 1;
                }
                if (re4[2] > re1[2]) {
                    t4 = t4 + 3;
                }
                if (re1[2] > re4[2]) {
                    t1 = t1 + 3;
                }
                if (re1[2] == re4[2]) {
                    t1 = t1 + 1;
                    t4 = t4 + 1;
                }
                if (re2[2] > re3[2]) {
                    t2 = t2 + 3;
                }
                if (re3[2] > re2[2]) {
                    t3 = t3 + 3;
                }
                if (re2[2] == re3[2]) {
                    t2 = t2 + 1;
                    t3 = t3 + 1;
                }
                if (re1[3] > re3[3]) {
                    t1 = t1 + 3;
                }
                if (re3[3] > re1[3]) {
                    t3 = t3 + 3;
                }
                if (re1[3] == re3[3]) {
                    t1 = t1 + 1;
                    t3 = t3 + 1;
                }
                if (re2[3] > re4[3]) {
                    t2 = t2 + 3;
                }
                if (re4[3] > re2[3]) {
                    t4 = t4 + 3;
                }
                if (re2[3] == re4[3]) {
                    t2 = t2 + 1;
                    t4 = t4 + 1;
                }

                if (re1[4] > re2[4]) {
                    t1 = t1 + 3;
                }
                if (re2[4] > re1[4]) {
                    t2 = t2 + 3;
                }
                if (re2[4] == re1[4]) {
                    t1 = t1 + 1;
                    t2 = t2 + 1;
                }
                if (re3[4] > re4[4]) {
                    t3 = t3 + 3;
                }
                if (re4[4] > re3[4]) {
                    t4 = t4 + 3;
                }
                if (re3[4] == re4[4]) {
                    t3 = t4 + 1;
                    t3 = t4 + 1;
                }
                if (re4[5] > re1[5]) {
                    t4 = t4 + 3;
                }
                if (re1[5] > re4[5]) {
                    t1 = t1 + 3;
                }
                if (re1[5] == re4[5]) {
                    t1 = t1 + 1;
                    t4 = t4 + 1;
                }
                if (re2[5] > re3[5]) {
                    t2 = t2 + 3;
                }
                if (re3[5] > re2[5]) {
                    t3 = t3 + 3;
                }
                if (re2[5] == re3[5]) {
                    t2 = t2 + 1;
                    t3 = t3 + 1;
                }
                if (re1[6] > re3[6]) {
                    t1 = t1 + 3;
                }
                if (re3[6] > re1[6]) {
                    t3 = t3 + 3;
                }
                if (re1[6] == re3[6]) {
                    t1 = t1 + 1;
                    t3 = t3 + 1;
                }
                if (re2[6] > re4[6]) {
                    t2 = t2 + 3;
                }
                if (re4[6] > re2[6]) {
                    t4 = t4 + 3;
                }
                if (re2[6] == re4[6]) {
                    t2 = t2 + 1;
                    t4 = t4 + 1;
                }

                if (t1 > t2 && t1 > t3 && t1 > t4) {
                    Namee = e1.getText();
                }
                if (t2 > t1 && t2 > t3 && t1 > t4) {
                    Namee = e2.getText();
                }
                if (t3 > t1 && t3 > t2 && t3 > t4) {
                    Namee = e3.getText();
                }
                if (t4 > t1 && t4 > t2 && t4 > t3) {
                    Namee = e4.getText();
                } else {

                    if (re1[7] > re2[7] && re1[7] > re3[7] && re1[7] > re4[7]) {
                        Namee = e1.getText();
                    }
                    if (re2[7] > re1[7] && re2[7] > re3[7] && re2[7] > re4[7]) {
                        Namee = e2.getText();
                    }
                    if (re3[7] > re1[7] && re3[7] > re2[7] && re3[7] > re4[7]) {
                        Namee = e3.getText();
                    }
                    if (re4[7] > re1[7] && re4[7] > re2[7] && re4[7] > re3[7]) {
                        Namee = e4.getText();
                    }
                }
                if (re1[7] == re2[7] && re1[7] == re3[7] && re1[7] == re4[7]) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Repeat Tournoi !");
                    alert.showAndWait();
                }

            }

            if (r2.isSelected()) {

                re1[1] = Integer.parseInt(m1.getText());
                re2[1] = Integer.parseInt(m2.getText());
                re3[1] = Integer.parseInt(m3.getText());
                re1[2] = Integer.parseInt(m4.getText());
                re2[2] = Integer.parseInt(m5.getText());
                re3[2] = Integer.parseInt(m6.getText());

                re1[3] = Integer.parseInt(m13.getText());
                re3[3] = Integer.parseInt(m14.getText());
                re2[3] = Integer.parseInt(m15.getText());
                re1[4] = Integer.parseInt(m16.getText());
                re3[4] = Integer.parseInt(m17.getText());
                re2[4] = Integer.parseInt(m18.getText());

                re1[7] = re1[1] + re1[2] + re1[3] + re1[4];
                re2[7] = re2[1] + re2[2] + re2[3] + re2[4];
                re3[7] = re3[1] + re3[2] + re3[3] + re3[4];

                if (re1[7] > re2[7] && re1[7] > re3[7]) {
                    Namee = e1.getText();
                }
                if (re2[7] > re1[7] && re2[7] > re3[7]) {
                    Namee = e2.getText();
                }
                if (re3[7] > re1[7] && re3[7] > re2[7]) {
                    Namee = e3.getText();
                }
            }

            String filename = "C:\\Tournoi\\nbrT\\Tournoi\\" +winner.getText()+" "+ Namee + date + ".xls";

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Teams Data");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell(0).setCellValue("Equipe 1");
            rowhead.createCell(1).setCellValue("But");
            rowhead.createCell(2).setCellValue("But");
            rowhead.createCell(3).setCellValue("Equipe 2");
            HSSFRow row1 = sheet.createRow((short) 1);
            row1.createCell(0).setCellValue(e1.getText());
            row1.createCell(1).setCellValue(m1.getText());
            row1.createCell(2).setCellValue(m2.getText());
            row1.createCell(3).setCellValue(e2.getText());
            HSSFRow row2 = sheet.createRow((short) 2);
            row2.createCell(0).setCellValue(e3.getText());
            row2.createCell(1).setCellValue(m3.getText());
            row2.createCell(2).setCellValue(m4.getText());
            row2.createCell(3).setCellValue(e4.getText());
            HSSFRow row3 = sheet.createRow((short) 3);
            row3.createCell(0).setCellValue(e5.getText());
            row3.createCell(1).setCellValue(m5.getText());
            row3.createCell(2).setCellValue(m6.getText());
            row3.createCell(3).setCellValue(e6.getText());
            HSSFRow row4 = sheet.createRow((short) 4);
            row4.createCell(0).setCellValue(e7.getText());
            row4.createCell(1).setCellValue(m7.getText());
            row4.createCell(2).setCellValue(m8.getText());
            row4.createCell(3).setCellValue(e8.getText());
            HSSFRow row5 = sheet.createRow((short) 5);
            row5.createCell(0).setCellValue(e9.getText());
            row5.createCell(1).setCellValue(m9.getText());
            row5.createCell(2).setCellValue(m10.getText());
            row5.createCell(3).setCellValue(e10.getText());
            HSSFRow row6 = sheet.createRow((short) 6);
            row6.createCell(0).setCellValue(e11.getText());
            row6.createCell(1).setCellValue(m11.getText());
            row6.createCell(2).setCellValue(m12.getText());
            row6.createCell(3).setCellValue(e12.getText());
            HSSFRow row7 = sheet.createRow((short) 7);
            row7.createCell(0).setCellValue(e13.getText());
            row7.createCell(1).setCellValue(m13.getText());
            row7.createCell(2).setCellValue(m14.getText());
            row7.createCell(3).setCellValue(e14.getText());
            HSSFRow row8 = sheet.createRow((short) 8);
            row8.createCell(0).setCellValue(e15.getText());
            row8.createCell(1).setCellValue(m15.getText());
            row8.createCell(2).setCellValue(m16.getText());
            row8.createCell(3).setCellValue(e16.getText());
            HSSFRow row9 = sheet.createRow((short) 9);
            row9.createCell(0).setCellValue(e17.getText());
            row9.createCell(1).setCellValue(m17.getText());
            row9.createCell(2).setCellValue(m18.getText());
            row9.createCell(3).setCellValue(e18.getText());
            HSSFRow row10 = sheet.createRow((short) 10);
            row10.createCell(0).setCellValue(e19.getText());
            row10.createCell(1).setCellValue(m19.getText());
            row10.createCell(2).setCellValue(m20.getText());
            row10.createCell(3).setCellValue(e20.getText());
            HSSFRow row11 = sheet.createRow((short) 11);
            row11.createCell(0).setCellValue(e21.getText());
            row11.createCell(1).setCellValue(m21.getText());
            row11.createCell(2).setCellValue(m22.getText());
            row11.createCell(3).setCellValue(e22.getText());
            HSSFRow row12 = sheet.createRow((short) 12);
            row12.createCell(0).setCellValue(e23.getText());
            row12.createCell(1).setCellValue(m23.getText());
            row12.createCell(2).setCellValue(m24.getText());
            row12.createCell(3).setCellValue(e24.getText());

            FileOutputStream fileOut = new FileOutputStream(new File(filename));
            workbook.write(fileOut);
            fileOut.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void getFiles(File f) {
        File files[];
        String str;
        if (f.isFile()) {
            str = f.getAbsolutePath();
            str = str.replaceAll("[0-9]", "");
            str = str.replace("C:\\Tournoi\\nbrT\\Tournoi", "");
            str = str.replace(".xls", "");
            System.out.println(str);

        } else {
            files = f.listFiles();

            for (int i = 0; i < files.length; i++) {
                getFiles(files[i]);

                System.out.println(files.length);
            }
        }
    }

    void getNTournoi() {
        File mainFolder = new File("C:\\Tournoi\\nbrT\\Tournoi");
        getFiles(mainFolder);
    }
}
