package com.mygdx.game;

import org.usb4java.Device;

public class TestPeri
{
    public static void main(String[] args) throws Exception
    {
        McuBoard.initUsb();

        try
        {
        	Device[] devices = McuBoard.findBoards();
        	
        	if (devices.length == 0) {
                System.out.format("** Practicum board not found **\n");
                return;
        	}
        	else {
        		System.out.format("** Found %d practicum board(s) **\n", devices.length);
        	}
        	
        	McuWithPeriBoard peri = new McuWithPeriBoard(devices[0]);
        	
        	System.out.format("** Practicum board found **\n");
            System.out.format("** Manufacturer: %s\n", peri.getManufacturer());
            System.out.format("** Product: %s\n", peri.getProduct());
            
            int count = 0;

            while (true) 
            {
            	//ต่อ 02 sw  //13 led ซซ ขข
                Thread.sleep(300);
                peri.setLedValue(count);
                boolean sw1 = peri.getSwitch(1);
                boolean sw2 = peri.getSwitch(2);
                boolean sw3 = peri.getSwitch(3);
                boolean sw4 = peri.getSwitch(4);
                //int light = peri.getLight();
                System.out.format("LED set to %d | Switch state: %s %s %s %s \n",count,sw1, sw2,sw3,sw4);

                count++;
                if (count > 7) count = 0;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        McuBoard.cleanupUsb();
    }
}