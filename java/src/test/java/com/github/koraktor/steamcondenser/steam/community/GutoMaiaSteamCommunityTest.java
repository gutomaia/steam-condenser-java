package com.github.koraktor.steamcondenser.steam.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.xerces.parsers.DOMParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.w3c.dom.Document;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DocumentBuilderFactory.class, DocumentBuilder.class})
public class GutoMaiaSteamCommunityTest {
		
	public Document getGutoMaiaProfileFromFile() throws Exception {
		try {
			DOMParser parser = new DOMParser();
			parser.parse("src/test/resources/gutomaia.xml");
			return parser.getDocument();
		}catch (Exception e) {
			throw e;
		}
	}

	DocumentBuilder parser = mock(DocumentBuilder.class);
	DocumentBuilderFactory factory = mock(DocumentBuilderFactory.class);
	
	@Before
	public void init() throws Exception{
		mockStatic(DocumentBuilderFactory.class);
		when(DocumentBuilderFactory.newInstance()).thenReturn(factory);
		when(factory.newDocumentBuilder()).thenReturn(parser);
	}
	
	@Test
	public void getProfile() throws Exception{
		when(parser.parse("http://steamcommunity.com/id/gutomaia?xml=1")).thenReturn(getGutoMaiaProfileFromFile());
				
		SteamId steamId = SteamId.create("gutomaia");
		
		verify(parser).parse("http://steamcommunity.com/id/gutomaia?xml=1");
		
		assertEquals("gutomaia", steamId.getNickname());
		assertEquals("gutomaia",steamId.getCustomUrl());
		assertEquals("Salvador, Bahia, Brazil",steamId.getLocation());
	}

}
