package reader;

import modular.Item;

import java.io.*;

public class ItemsCsvReader {
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
            System.out.println("Item data file doesn't exists in the path "+path);
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
    public Item read(){
        try {
            if(!headerRead){
                readHeader();
            }
            String line = this.br.readLine();
            if(line!=null){
                String[] itemData = line.split(",");
                if(itemData.length!=4){
                    return null;
                }
                String category = itemData[0];
                String name = itemData[1];
                int quantity = Integer.parseInt(itemData[2]);
                double price = Double.parseDouble(itemData[3]);
                return new Item(category, name, price, quantity);
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
