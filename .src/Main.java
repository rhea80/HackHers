import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.io.PrintWriter;
import java.lang.ProcessBuilder.Redirect;
import java.io.File;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.*;

public class Main implements ActionListener{
    public String city;
    public JButton button;
    

    boolean isClicked = false;

    public static void initialize() {
        JFrame frame = new JFrame("Database");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setVisible(true);

        //create textbox
        JPanel panel = new JPanel();
        JTextField tf = new JTextField(30);
        JLabel label = new JLabel("Enter Text");
        JButton button = new JButton("Send");

        panel.add(label);
        panel.add(tf);  
        panel.add(button);
        button.setFocusable(false);
        button.addActionListener(
            public void actionPerformed(ActionEvent e){
                city = tf.getText();
                isClicked = true;
        
            }
        );


        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.setVisible(true);
        
    }

    

        return city;
    }
    


    /*public String findByCity(ArrayList<Store> stores){
        Scanner input = new Scanner(System.in);
        String city = input.nextLine();
        input.close();
        int count = 0;
        String response = "";
        for(int i = 0; i < stores.size(); i++){
            if(stores.get(i).getCity().compareToIgnoreCase(city) == 0){
                count++;
                response += stores.get(i).getFullAddress() + "\n\n";
            }
        }
        if(count == 0){
            return "No stores found in this city :(";
        }
        return count + " stores found in your city:\n\n" + response;
    }*/

    public String findByZip(String zipcode, ArrayList<Store> stores){
        int count = 0;
        String response = "";
        for(int i = 0; i < stores.size(); i++){
            if(stores.get(i).getZipcode().compareToIgnoreCase(zipcode) == 0){
                count++;
                response += stores.get(i).getFullAddress() + "\n\n";
            }
        }
        if(count == 0){
            return "No stores found in this city :(";
        }
        return count + " stores found in your city:\n\n" + response;
    }
    public static void main(String [] args) {
        initialize();
        
        /** getStoreDetails and getItemDetails URL for demo*/
        String getStoreDetailsURL = "https://apimdev.wakefern.com/mockexample/V1/getStoreDetails";
        String getItemDetailsURL = "https://apimdev.wakefern.com/mockexample/V1/getItemDetails";
        ArrayList<Store> stores = new ArrayList<>();
        
/**
 * Get Request Template
 */
        // 1 - Create Client
        HttpClient clientStores = HttpClient.newHttpClient();

        // 2 - Build Request with headers
        HttpRequest requestStores = HttpRequest.newBuilder()
                .uri(URI.create(getStoreDetailsURL))
                .header("Ocp-Apim-Subscription-Key", "4ae9400a1eda4f14b3e7227f24b74b44")      //Set Other Headers
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpRequest requestProducts = HttpRequest.newBuilder()
                .uri(URI.create(getItemDetailsURL))
                .header("Ocp-Apim-Subscription-Key", "4ae9400a1eda4f14b3e7227f24b74b44")      //Set Other Headers
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        
        // 3 - Client sends request and saves the response in a variable
        try {
            HttpResponse responseStores = clientStores.send(requestStores, HttpResponse.BodyHandlers.ofString());
            HttpResponse responseProducts = clientStores.send(requestProducts, HttpResponse.BodyHandlers.ofString());

            // 4 - Print response in console
            /** Print Response Here */
            File storeFile = new File("StoreFile.txt");
            PrintWriter printStores = new PrintWriter(storeFile);
            printStores.print(responseStores.body());
            printStores.close(); 

            File productFile = new File("ProductFile.txt");
            PrintWriter printProducts = new PrintWriter(productFile);
            printProducts.print(responseProducts.body());
            printProducts.close();

            String readLine;
            String actualString;

            Scanner storeReader = new Scanner(storeFile);
            while(storeReader.hasNext()){
                readLine = storeReader.nextLine();
                readLine = storeReader.nextLine();

                if(readLine.compareTo("]") == 0){
                    break;
                }

                readLine = storeReader.nextLine();
                actualString = readLine.substring(13, readLine.length()-1);
                int storeNum = Integer.parseInt(actualString);

                readLine = storeReader.nextLine();
                String address = readLine.substring(16, readLine.length()-2);

                readLine = storeReader.nextLine();
                String city = readLine.substring(13, readLine.length()-2);

                readLine = storeReader.nextLine();
                String state = readLine.substring(14, readLine.length()-2);

                readLine = storeReader.nextLine();
                String zipcode = readLine.substring(16, readLine.length()-2);

                readLine = storeReader.nextLine();
                String phone = readLine.substring(14, readLine.length()-1);

                Store newStore = new Store(storeNum, address, city, state, zipcode, phone);
                stores.add(newStore);
            }
            storeReader.close();

            Scanner input = new Scanner(System.in);
            System.out.println("Please enter your city: ");
            String city = input.nextLine();
            input.close();
            int count = 0;
            String response = "";
            for(int i = 0; i < stores.size(); i++){
                if(stores.get(i).getCity().compareToIgnoreCase(city) == 0){
                    count++;
                    response += stores.get(i).getFullAddress() + "\n\n";
                }
            }
            if(count == 0){
                String slay = "No stores found in this city :(";
            }
            else {
                String slay = count + " stores found in your city:\n\n" + response;
                
            }
            
            Scanner productReader = new Scanner("ProductFile.txt");


            // 5 - Error Handling
            /**
             * Error Handling Conventions:
             * Errors are generally saved in a separate log file to be accessed in the future
             * In production, code generally should not print anything on the console
             * Console logging is okay for development/debugging purposes
             */
        } catch (IOException e) {
           /** Set Error Handling Here */
        } catch (InterruptedException e) {
            /** Set Error Handling Here */
        }

    }

    

}