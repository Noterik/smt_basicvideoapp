/* 
* HelloworldApplication.java
* 
* Copyright (c) 2012 Noterik B.V.
* 
* This file is part of Lou, related to the Noterik Springfield project.
* It was created as a example of how to use the multiscreen toolkit
*
* Helloworld app is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* Helloworld app is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with Helloworld app.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.springfield.lou.application.types;
import org.springfield.fs.Fs;
import org.springfield.fs.FsNode;
import org.springfield.lou.application.*;
import org.springfield.lou.screen.*;

public class BasicvideoApplication extends Html5Application{
	
 	public BasicvideoApplication(String id) {
		super(id); 
	}
	
    public void onNewScreen(Screen s) {
    	Capabilities cap = s.getCapabilities();
        loadStyleSheet(s, "generic");
        loadContent(s, "video");
       
        int mode = s.getCapabilities().getDeviceMode();
        if (cap.getDeviceMode()==cap.MODE_HBBTV) {
        	s.setRole("mainscreen");
            s.setContent("video",getCEHtmlVideoTag());
    		this.componentmanager.getComponent("video").put("app", "play()");
            loadContent(s, "overlay");
        } else {
        	String override = s.getParameter("role");
        	if (override!=null && override.equals("mainscreen")) {
            	s.setRole("mainscreen");
        		s.setContent("video",getHtml5VideoTag());
                loadContent(s, "overlay");
        	} else {
            	System.out.println("controller");
            	s.setRole("controller"); 	
                loadContent(s, "controller");
        	}
        }
    }
    
    private String getHtml5VideoTag() {
		String body="<video id=\"video1\" autoplay controls preload=\"none\" data-setup=\"{}\">";
		String url = "http://images1.noterik.com/euscreen/previewtool_screencast_beta.mp4";
		body+="<source src=\""+url+"\" type=\"video/mp4\" /></video>";
		return body;
    }
    
    private String getCEHtmlVideoTag() {
		String url = "http://images1.noterik.com/euscreen/previewtool_screencast_beta.mp4";
		String body="<object type=\"video/mp4\" id=\"video1\" data=\""+url+"\" width=\"640\" height=\"480\"></object>";
		return body;
    }
    
    public void pause(Screen s) {
 		this.componentmanager.getComponent("video").put("app", "pause()");
        setContentAllScreensWithRole("mainscreen","overlay","PAUSE HIT");
    }
    
    public void play(Screen s) {
 		this.componentmanager.getComponent("video").put("app", "play()");
        setContentAllScreensWithRole("mainscreen","overlay","PLAY HIT");
    }

}
