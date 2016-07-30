import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.awt.event.KeyEvent;
import java.util.*;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.util.*;
import javafx.event.*;
import java.security.*;
import javax.crypto.*;

// This was programmed by Gilbert Gomez.
// I won't bother how the JAVAFX window is made, only how the encryption is done IN THE WINDOW.
public class Main extends Application
{
	@Override
	// Will throw a Exception if there was no selected Algorithm in MessageDigest.
	   public void start(Stage stage) throws NoSuchAlgorithmException 
	{
	       BorderPane root = new BorderPane();
	       // Making a new MessageDigest.
	       // MessageDigest is used to encrypt the string was put into its digest into the selected algorithm such as SHA-256.
	       MessageDigest crypt = MessageDigest.getInstance("SHA-256");
	       Scene scene = new Scene(root, 800, 600, Color.WHITE);
	       stage.setTitle("Password Encrypter");
	       stage.setScene(scene);
	       BorderPane boxallign = new BorderPane();
	       TextField textfield = new TextField("Your encrypted password will show up here.");
	       textfield.setEditable(false);
	       TextField textfield2 = new TextField("Enter a password you want encrypted.");
	       Button about = new Button("About");
	       Alert info = new Alert(AlertType.INFORMATION);
	       HBox shrinker = new HBox(textfield);
	       HBox shrinker2 = new HBox();
	       shrinker2.getChildren().addAll(textfield2, about);
	       shrinker2.setAlignment(Pos.BOTTOM_CENTER);
	       shrinker2.setPadding(new Insets(150));
	       shrinker2.setHgrow(textfield2, Priority.ALWAYS);
	       shrinker.setAlignment(Pos.CENTER);
	       shrinker.setPadding(new Insets(200));
	       shrinker.setHgrow(textfield, Priority.ALWAYS);
	       textfield.setVisible(true);
	       textfield2.setVisible(true);
	       shrinker2.setSpacing(5);
	       root.setTop(shrinker2);
	       root.setCenter(shrinker);
	       stage.show();
	       
	    // This is the top text field in the window. This will be triggered when you press the enter key.
	       textfield2.setOnAction(enter -> 
	       {
	    	   String text = textfield2.getText();
	    	   /*
	    	    *  I'm getting the bytes of the string shown in text field 2.
	    	    *  and storing it into an array.
	    	    */
	    	   byte[] textfieldinbytes = text.getBytes();
	    	   // Then I'm going to put the bytes of text field 2 into a message digest stream using the "update" method.
	    	   crypt.update(textfieldinbytes);
	    	   // putting the digest of crypt into a variable.
	    	   byte[] cryptdump = crypt.digest();
	    	   // Making a new StringBuffer to modify the string into hexadecimal.
	    	   // If you didn't know already you need to have the output string in hexadecimal for the algorithm.
	    	   StringBuffer hexdigest = new StringBuffer();
	    	   // I'm modifying every byte in Text field 2 which is in the digest stream of crypt into hexadecimal.
	    	   for (int c = 0; c < cryptdump.length; c++)
	    	   {
	    		   /* I'm pretty sure there's an easier way of doing this.
	    		    * I'm changing the string to hexadecimal by first anding it with 255(0xff). Which will AND all the bits of the string's bytes and make it unsigned.
	    		    * I'm doing this because Byte[] is 32 bit signed.
	    		    * Next I'm adding 256 to the bytes to make it hexadecimal with 3 hexes.
	    		    * The second argument is asking for the radix(the base) of the string. So it's set to 16 since it's hexadecimal.  
	    		    */
	    		   hexdigest.append(Integer.toString(cryptdump[c] & 0xff + 0x100,16));
	    	   }
	    	   
	    	   // When you press enter, it will show the encrypted string of text field 2(Top Text field in the window) in, Text Field 1 (The bottom Text Field).
	    	   textfield.setText(hexdigest.toString());
	       });
	       
	       
	       // What will be executed upon clicking the "About" button.
	       about.setOnAction(enter -> {
	    	   info.setTitle("About");
	    	   info.setContentText("This program was made by Gilbert Gomez on 7/9/2016." + 
	    	   "\nFor more information please contact me at: gomezgilbert786@gmail.com"
	    	   + "\nThis program uses SHA-2 algorithm to encrypt your password."
	    	   + "\nThis type of algorithm is what is used to make your password secure on applications like Snapchat or Instagram."
	    	   + "\nEnjoy!");
	    	   info.setHeaderText(null);
	    	   info.showAndWait();});
	       
	       
	}
	   public static void main(String[] args) {
	       launch(args);
	   }


}
