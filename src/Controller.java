

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;

import java.net.URL;
import java.rmi.server.ExportException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Button btSearch;
    @FXML
    public Button btAdd;

    @FXML
    public TextField tfSearchedWord;

    @FXML
    public ListView<String> lvWords;

    @FXML
    public WebView browser;

    Map<String, String> dictionary = new HashMap<String, String>();
    Dictionary dic = new Dictionary();

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        tfSearchedWord.setOnKeyReleased(event -> {

            String newWord = tfSearchedWord.getText() ;
            System.out.println(newWord.length());
            System.out.println(newWord);
            lvWords.getItems().clear();
            lvWords.getItems().addAll(dic.Suggest(newWord));
            if (newWord.length() == 0) {
                lvWords.getItems().clear();
            }
        });
        btSearch.setOnMouseClicked(event -> {
            System.out.println("Search!!!");
            String searchedWord = tfSearchedWord.getText();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = dic.get(searchedWord);
                browser.getEngine().loadContent(wordMeaning);
            }
        });
        this.initializeWordList();
        lvWords.setOnMouseClicked(event -> {
            String searchedWord = lvWords.getSelectionModel().getSelectedItem();
            if (searchedWord != null && searchedWord.equals("") == false) {
                System.out.println("Searched World: " + searchedWord);
                String wordMeaning = dic.get(searchedWord);
                tfSearchedWord.setText(searchedWord);
                browser.getEngine().loadContent(wordMeaning);
            }
        });
    }

    public void initializeWordList()  {
        dictionary.put("hello", "Xin chao");
        dictionary.put("thank you", "Cam on");
        dictionary.put("school", "Truong hoc");
        dictionary.put("class", "Lop hoc");
        lvWords.getItems().addAll(dictionary.keySet());
    }
}
