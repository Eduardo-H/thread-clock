package clock;

import java.util.Scanner;

import clock.Clock.ChronometerThread;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		int escolha;
		Scanner strScanner = new Scanner(System.in);
		Scanner numScanner = new Scanner(System.in);
		
		Clock clock = new Clock();
		
		while(true) {
			System.out.println(" ----------------------- ");
			System.out.println("|   SELECT AN OPTION    |");
			System.out.println(" ----------------------- ");
			System.out.println("|1 - Show time          |");
			System.out.println("|2 - Change time        |");
			System.out.println("|3 - Start chronometer  |");
			System.out.println("|4 - Stop chronometer   |");
			System.out.println("|5 - Reset chronometer  |");
			System.out.println("|6 - Set alarm          |");
			System.out.println(" ----------------------- ");
			System.out.print("~: ");
			escolha = numScanner.nextInt();
			
			switch(escolha) {
				case 1:
					System.out.println("Current time: " + clock.getTime());
					break;
				case 2:
					System.out.print("\nInform the time: ");
					clock.setTime(strScanner.nextLine());
					break;
				case 3:
					clock.startChronometer();
					Thread.currentThread().sleep(200);
					break;
				case 4:
					clock.stopChronometer();
					System.out.println("Chronometer time: " + clock.chronometer.showTime());
					break;
				case 5:
					clock.chronometer.reset();
					break;
				case 6:
					System.out.print("Type the time for the alarm (hour:minute): ");
					clock.alarm.setAlarme(strScanner.nextLine());
					break;
				default:
					System.out.println("Invalid option.");
					break;
			}
		}
	}

}
