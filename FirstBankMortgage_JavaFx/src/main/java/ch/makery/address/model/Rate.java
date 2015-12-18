package ch.makery.address.model;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JOptionPane;

import org.apache.poi.ss.formula.functions.FinanceLib;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.view.MortgageController;
import domain.RateDomainModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Rate extends RateDomainModel {
	
	private double pv;
	private int creditScore;

	public Rate(){
		
	}
	
	public Rate(int creditScore, double pv){
		this.creditScore = creditScore;
		this.pv = pv;
	}
	
	public double getPayment(int NumberOfPayments)
	{
		//FinalExam
		//	Normally this kind of method would be in a BLL, but alas...

		//	Figure out payment based on:
		//	Interest rate
		//	PV
		//	FV (make FV = 0, unless you want a balloon payment
		//	Compounding = True
		//	Number of Payments (passed in)

		double pmt = 0; //the payment on the mortgage
		boolean compounding = true;
		final double fv = 0;   //the future value, 0 because you want to pay off the mortgage	
		

		//rate is divided by number of months in year (program would freeze if divison somewhere else)
		pmt = FinanceLib.pmt(RateDAL.getRate(creditScore)/12, NumberOfPayments, -pv, fv, compounding);
		



		return pmt;
	}
}
