package com.mygdx.game;
import org.usb4java.Device;

public class HardWare {

	public McuWithPeriBoard peri;
	public Device[] devices;
	public boolean [] isSwitchPress;
	
	public void initHardWare() {//throws Exception{
		isSwitchPress = new boolean [World.NBOFCOLOR];
		
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
		for(int i=0;i<World.NBOFCOLOR;i++) {
			isSwitchPress[i] = peri.getSwitch(i+1);
		}
	}
}
