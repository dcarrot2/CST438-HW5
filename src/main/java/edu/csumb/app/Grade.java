package edu.csumb.app;

public class Grade {
	private double score;
	private char letter;
	
	public Grade(double score, char letter){
		this.score = score;
		this.letter = letter;
	}

	@Override
	public String toString() {
		return score + " " + "(" + letter + ")";
	}
	
	public double getScore(){
		return score;
	}
}