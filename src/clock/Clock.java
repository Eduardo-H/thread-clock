package clock;

public class Clock {
	
	private int hour;
	private int minute;
	private int second;
	public ChronometerThread chronometer = new ChronometerThread();
	public AlarmThread alarm = new AlarmThread();
	
	public Clock() {
		this.hour = 0;
		this.minute = 0;
		this.second = 0;
		ThreadRelogio tRelogio = new ThreadRelogio();
		tRelogio.start();
	}
	
	public String getTime() {
		return (hour + ":" + minute + ":" + second);
	}
	
	public void setTime(String time) {
		String valores[] = time.split(":");
		
		if (valores.length != 3) {
			System.out.println("Incorrect time format.");
			return;
		}
		
		hour = Integer.parseInt(valores[0]);
		minute = Integer.parseInt(valores[1]);
		second = Integer.parseInt(valores[2]);
	}
	
	public void startChronometer() {
		if (chronometer.isAlive())
			chronometer.unpause();
		else
			chronometer.start();
	}
	
	public void stopChronometer() throws InterruptedException {
		chronometer.pause();
	}
	
	public void ajustAlarm(String horario) {
		alarm.setAlarme(horario);
	}
	
	public class ThreadRelogio extends Thread {
		@Override
		public void run() {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// Verifies if it's time to reset the seconds counter
				if (second >= 59) {
					// Verifies if it's time to reset the minutes counter
					if (minute >= 59) {
						// Verifies if it's time to reset the hours counter
						if (hour >= 23) {
							hour = 0;
							minute = 0;
						} else {
							hour ++;
							minute = 0;
						}
					} else {
						minute ++;
					}
					second = 0;
				} else {
					second ++;
				}
			}
		}
	}
	
	public class ChronometerThread extends Thread {
		
		private int hour;
		private int minute;
		private int second;
		private Boolean paused;
		
		public ChronometerThread() {
			this.hour = 0;
			this.minute = 0;
			this.second = 0;
			this.paused = false;
		}
		
		@Override
		public void run() {
			System.out.println("Chronometer successfully started!");
			while(true) {
				if (!paused) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					if (this.second >= 59) {
						if (this.minute >= 59) {
							if (this.hour >= 23) {
								this.hour = 0;
								this.minute = 0;
							} else {
								this.hour ++;
								this.minute = 0;
							}
						} else {
							this.minute ++;
						}
						this.second = 0;
					} else {
						this.second ++;
					}
				} else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		public void reset() {
			this.hour = 0;
			this.minute = 0;
			this.second = 0;
			System.out.println("Chronometer successfully reseted!");
		}
		
		public String showTime() {
			return (this.hour + ":" + this.minute + ":" + this.second);
		}
		
		public void pause() {
			this.paused = true;
		}
		
		public void unpause() {
			this.paused = false;
		}
	}
	
	public class AlarmThread extends Thread {
		
		private int alarmHour;
		private int alarmMinute;
		
		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				if (hour == alarmHour && minute == alarmMinute) {
					System.out.println("BIP BIP BOP BOP, LOOK!!! IT'S THE ALARM!!!");
					return;
				}
			}
		}
		
		public void setAlarme(String time) {
			String valores[] = time.split(":");
			
			if (valores.length != 2) {
				System.out.println("Incorrect time format.");
				return;
			}
			
			this.alarmHour = Integer.parseInt(valores[0]);
			this.alarmMinute = Integer.parseInt(valores[1]);
			start();
			System.out.println("Alarm successfully set!");
		}
	}
}
