package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.*;
import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
import java.io.IOException;
//import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


//public class State{
//    boolean IsFinish = false;

//}

public class NFA{
    ArrayList<Integer>[] g;
    Vector<Boolean> state;
    Vector<Boolean> isFinish;

    Vector<Vector<Integer> > chains;

    public NFA(int n){
        g = new ArrayList[n];
        chains = new Vector<Vector<Integer>>();
    }

    private String states, alphabet, initialStates, finalStates;
    private List<String> letters = new ArrayList<String>();
    private List<String> transactions = new ArrayList<String>();

    public NFA(String filePath) {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedReader reader = (BufferedReader) Files.newBufferedReader(Paths.get(filePath), charset)) {
            String line = reader.readLine();

            this.alphabet = line;
            line = reader.readLine();
            this.states = line;
            line = reader.readLine();
            this.initialStates = line;
            line = reader.readLine();
            this.finalStates = line;

            //read transaction
            while ((line = reader.readLine()) != null) {
                if (!(transactions.contains(line))) {
                    transactions.add(line);
                }

                String letter = line.substring(2, 3);
                if (!(letters.contains(letter))) {
                    letters.add(letter);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> splitTransaction(String transaction, List<String> list) {
        String[] splitted = transaction.split(" ");
        for (String part : splitted) {
            list.add(part);
        }
        return list;
    }

    void dfs(int v, String s, int pos, int current_chain_id) {
        chains[current_chain_id].addElement(new Integer(v));

        vector<int> temp = chains[current_chain_id];

        int next_chain = current_chain_id;

        if (pos == s.length())
            if (state[v] == true)
                is_correct[current_chain_id] = true;

        for (int i = 0; i < g[v].size(); ++i) {
            if (g[v][i].perehid == s[pos]) {
                if (next_chain != current_chain_id) {
                    chains.push_back(temp);
                    is_correct.push_back(false);
                }

                dfs(g[v][i].state, s, pos + 1, next_chain);
                next_chain = chains.size();
            }
        }
    }
}

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
