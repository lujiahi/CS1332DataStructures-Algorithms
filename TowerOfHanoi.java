//Tower of Hanoi is a game where you have three pegs, and you have a bunch of distinct rings, 
//each of different radii, that can be put on a peg. The starting position is as shown in the image below, 
//with all of the rings in order based on radius, starting on peg A.
//The goal of the game is to move the rings into the same configuration, except on peg C by moving the rings
//one by one according to the following rules:
//You can only move one ring each time you make a move.
//Smaller radius rings can never be below larger radius rings.
//When you move a ring, you can only take the ring at the top of the peg and drop it to the top of another peg.
//Write a program that prints the shortest sequence of moves that solves the Tower of Hanoi problem for  rings.

public class TowerOfHanoi {
	
	public static int play(int numOfRings, String fromPeg, String emptyPeg, String toPeg) {
		if(numOfRings == 1) {
			return move(fromPeg, toPeg);
		} else {
			int counter = 0;
			counter += play(numOfRings - 1, fromPeg, toPeg, emptyPeg);
			counter += move(fromPeg, toPeg);
			counter += play(numOfRings - 1, emptyPeg, fromPeg, toPeg);
			return counter;
		}
	}
	
	public static int move(String fromPeg, String toPeg) {
		System.out.println("Move the top ring from " + fromPeg + " to " + toPeg);
		return 1;
	}
	
	public static void main(String[] args) {
		play(4, "A", "B", "C");
	}

}
