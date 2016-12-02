package com.mygdx.game;
import org.usb4java.Device;

public class HardWare {

	public McuWithPeriBoard peri;
	public Device[] devices;
	public boolean [] isSwitchPress;
	public boolean [] LED;
	public void initHardWare() {//throws Exception{
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
        		return;
        	}
        	else {
        		System.out.format("** Found %d practicum board(s) **\n", devices.length);
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
		//updateLED();
	}
	
	public void updateSwitch() {
		for(int i=0;i<World.NBOFCOLOR;i++) {
			isSwitchPress[i] = peri.getSwitch(i+1);
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
					peri.setLed(1, value);
					break;
				case World.BLUE:
					peri.setLed(3, value);
					break;
				case World.YELLOW:
					peri.setLed(6, value);
					break;
				case World.GREEN:
					peri.setLed(5, value);
					break;
				default:
					break;
			}
		}
		//peri.setLedValue(5);
	}
}
