package ch.makery.address.view;

import javafx.collections.FXCollections;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;
import domain.RateDomainModel;


public class MortgageController {


	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * The constructor.
	 * The constructor is called before the initialize() method.
	 */
	public MortgageController() {
	}

	/**
	 * Initializes the controller class. This method is automatically called
	 * after the fxml file has been loaded.
	 */
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		ObservableList<String> options = 
				FXCollections.observableArrayList(
						"15 Years",
						"30 Years"   		        
						);
		comboTerm.setItems(options); //adds the options of the combobox

	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private Label lblResult;

	@FXML
	private Label lblMortgageTitle;

	@FXML
	private TextField textCredit;

	@FXML
	private TextField textIncome;

	@FXML
	private TextField textHouse;

	@FXML
	private TextField textExpense;

	@FXML
	private ComboBox comboTerm;



	@FXML
	private Button btnCalculate;

	@FXML
	private void handleCalculate(){
		ArrayList<TextField> alTF = new ArrayList<TextField>();
		TextField[] allTextFields = new TextField[]{textHouse, textExpense, textIncome, textCredit};
		alTF.addAll(Arrays.asList(allTextFields)); //all user input text fields in an array
		double primaryPercent = .36; //the percent variable for the first requirement for income
		double secondaryPercent = .28; //the percent multiple for the second requirement of income and expenses
		double expense = 0; 
		double income = 0;
		double incomeMax = 0; //the maximum income 
		boolean comboClicked = false; //boolean to make sure that the combobox has a selection, defaulted to false
		double result = 0; //the number of payments determined by the combobox
		int months = 12;
		
		//true if the combobox has an item selected, else false
		if(comboTerm.getSelectionModel().isSelected(0) || comboTerm.getSelectionModel().isSelected(1)){
			comboClicked = true;
		}
		System.out.println(alTF.iterator().next().getText().isEmpty());
		//if any of the text fields are not filled, alert the user. else run the calculation
		if(alTF.iterator().next().getText().isEmpty() == true || comboClicked ==false){
			JOptionPane.showMessageDialog(null, "Please Complete all Fields before Calculating");
		}else{
			if(600 >Integer.parseInt(textCredit.getText().toString()) || Integer.parseInt(textCredit.getText().toString())>800){
				JOptionPane.showMessageDialog(null, "Valid Credit Scores: 600 to 800");
			}else{
			Rate payment = new Rate(Integer.parseInt(textCredit.getText().toString()),	Double.parseDouble(textHouse.getText().toString()));
			try{
				income = Double.parseDouble(textIncome.getText().toString());
				expense = Double.parseDouble(textExpense.getText().toString());
				incomeMax = income*primaryPercent; 
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(null, "Please only enter Appropriate Number Values"); //exception gets thrown if the user inputs non-numeric numbers
				System.out.println("Non-numeric characters entered");
			}
			
			switch(comboTerm.getSelectionModel().getSelectedIndex()){
			case 0:
				result = payment.getPayment(15*months); //for 15 years it is multiplied by 12 to get the number of months
				break;
			case 1:
				result = payment.getPayment(30*months); //for 30 years it is multiplied by 12 to get the number of months, or twice the months of 15
				break;
			}

			
			//checks to make sure the persons income and expenses are not too low for the calculated mortgage
			if(result <= incomeMax || result <= (income + expense)* secondaryPercent){
				NumberFormat formatter = new DecimalFormat("###,###,###,###.##");
				lblResult.setText("$"+formatter.format(result));
				System.out.println(formatter.format(result));
			}else{
				lblResult.setText("You cannot afford this mortgage");			
			}
		}
		}
		
		
	}

}