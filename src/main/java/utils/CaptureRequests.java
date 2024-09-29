package utils;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.Request;

public class CaptureRequests {
	private DevTools devTools = null;
	
	public DevTools captureRequestUrl(WebDriver driver) {
		
		devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devTools.addListener(Network.requestWillBeSent(), request-> {
			Request req = request.getRequest();
			System.out.println(req.getUrl());
			System.out.println(req.getMethod());
			
		});
		return devTools;
	}
	
	public void stopCapturingRequestUrl() {
		devTools.close();
	}

}
