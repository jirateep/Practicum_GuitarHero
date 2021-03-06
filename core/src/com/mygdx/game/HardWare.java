package com.mygdx.game;
import org.usb4java.Device;

public class HardWare {

	public McuWithPeriBoard peri;
	public Device[] devices;
	public boolean [] isSwitchPress;
	public boolean [] LED;
	public boolean foundHardWare;
	public static final int REDPORT = 0;
	public static final int BLUEPORT = 1;
	public static final int GREENPORT = 2;
	public static final int YELLOWPORT = 3;
	
	public void initHardWare() {
		isSwitchPress = new boolean [World.NBOFCOLOR];
		LED = new boolean [World.NBOFCOLOR];
		for(int i=0;i<LED.length;i++) {
			LED[i] = false;
		}
		
		McuBoard.initUsb();
		
       	devices = McuBoard.findBoards();
       	
        try 
        {	
        	if (devices.length == 0) {
        		System.out.format("** Practicum board not found **\n");
        		foundHardWare = false;
        		return;
        	}
        	else {
        		System.out.format("** Found %d practicum board(s) **\n", devices.length);
        		foundHardWare = true;
        	}
        	
        	peri = new McuWithPeriBoard(devices[0]);
        	
        	System.out.format("** Practicum board found **\n");
        	System.out.format("** Manufacturer: %s\n", peri.getManufacturer());
        	System.out.format("** Product: %s\n", peri.getProduct());
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
	}
	
	public void update() {
		updateSwitch();
		//printSwitch();
		//updateLED();
	}
	
	public void updateSwitch() {
		if(foundHardWare) {
			for(int i=0;i<World.NBOFCOLOR;i++) {
				isSwitchPress[i] = peri.getSwitch(i+1);
			}
		}
	}
	
	public void printSwitch() {
		for(int i=0;i<World.NBOFCOLOR;i++) {
			System.out.println(isSwitchPress[i]+"\t");
		}
	}
	
	public void updateLED() {
		for(int i=0;i<World.NBOFCOLOR;i++) {
			int value = 0;
			if(LED[i] == true) {
				value = 1;
			}
			System.out.println(value);
			switch(i) {
				case World.RED:
					peri.setLed(REDPORT, value);
					break;
				case World.BLUE:
					peri.setLed(BLUEPORT, value);
					break;
				case World.YELLOW:
					peri.setLed(YELLOWPORT, value);
					break;
				case World.GREEN:
					peri.setLed(GREENPORT, value);
					break;
				default:
					break;
			}
		}
	}
}
