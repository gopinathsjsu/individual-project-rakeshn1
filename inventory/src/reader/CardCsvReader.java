package reader;

import modular.Card;

import java.io.*;
public class CardCsvReader {
    BufferedReader br;
    boolean headerRead = false;
    String[] headers = new String[0];
    private void getCsvReader(String path) throws FileNotFoundException {
        File file = new File(path);
        FileReader fr = new FileReader(file);
        this.br = new BufferedReader(fr);
    }

    public void open(String path){
        try {
            this.getCsvReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("Card data file doesn't exists in the path"+path);
            // this.close();
        }
    }
    public String[] readHeader(){
        if(!headerRead){
            try {
                this.headers = this.br.readLine().split(",");
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.headerRead = true;
        }
        return this.headers;
    }
    public Card read(){
        try {
            if(!headerRead){
                readHeader();
            }
            String line = this.br.readLine();
            if(line!=null){
                return new Card(line);
            }else{
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void close(){
        try {
            this.br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
