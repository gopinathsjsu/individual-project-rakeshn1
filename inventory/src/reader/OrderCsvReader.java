package reader;


import modular.OrderItem;

import java.io.*;

public class OrderCsvReader {
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
            // this.close();
            System.out.println("Order data file doesn't exists in the path "+path);
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
    public OrderItem read(){
        try {
            if(!headerRead){
                readHeader();
            }
            String line = this.br.readLine();
            if(line!=null){
                String[] itemData = line.split(",");
                if(itemData.length!=3){
                    return null;
                }
                String name = itemData[0];
                int quantity = Integer.parseInt(itemData[1]);
                String card = itemData[2];
                return new OrderItem(name, quantity, card);
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
