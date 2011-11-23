/**
 * This code is free software; you can redistribute it and/or modify it under
 * the terms of the new BSD License.
 *
 * Copyright (c) 2008-2011, Sebastian Staudt
 */

package com.github.koraktor.steamcondenser.steam.community;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
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

import com.github.koraktor.steamcondenser.steam.community.tf2.TF2Class;
import com.github.koraktor.steamcondenser.steam.community.tf2.TF2Inventory;
import com.github.koraktor.steamcondenser.steam.community.tf2.TF2Stats;

/**
 * @author Guto Maia
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ DocumentBuilderFactory.class, DocumentBuilder.class })
public class TF2StatsTest {
	
	public Document loadXml(String file) throws Exception {
		try {
			DOMParser parser = new DOMParser();
			parser.parse("src/test/resources/" + file);
			return parser.getDocument();
		} catch (Exception e) {
			throw e;
		}
	}

	DocumentBuilder parser = mock(DocumentBuilder.class);
	DocumentBuilderFactory factory = mock(DocumentBuilderFactory.class);

	@Before
	public void init() throws Exception {
		mockStatic(DocumentBuilderFactory.class);
		when(DocumentBuilderFactory.newInstance()).thenReturn(factory);
		when(factory.newDocumentBuilder()).thenReturn(parser);
		when(parser.parse("http://steamcommunity.com/id/gutomaia?xml=1"))
				.thenReturn(loadXml("gutomaia.xml"));
		when(parser.parse("http://steamcommunity.com/id/gutomaia/games?xml=1"))
				.thenReturn(loadXml("gutomaia-games.xml"));
		when(
				parser.parse("http://steamcommunity.com/id/gutomaia/stats/tf2?xml=all"))
				.thenReturn(loadXml("gutomaia-tf2.xml"));
		SteamId steamId = SteamId.create("gutomaia", true, false);
		tf2Stats = (TF2Stats) steamId.getGameStats("tf2");

	}
	
	TF2Stats tf2Stats;

	@Test
	public void getTF2Stats() throws Exception {
		assertEquals("Team Fortress 2", tf2Stats.getGameName());
		assertEquals("TF2", tf2Stats.getGameFriendlyName());
		assertEquals(440, tf2Stats.getAppId());
		assertEquals("13.5", tf2Stats.getHoursPlayed());
		assertEquals(76561197985077150l, tf2Stats.getSteamId64());
	}

	@Test
	public void achievements() throws Exception {
		assertEquals(314, tf2Stats.getAchievementsDone());
	}
	
	@Test
	public void inventory() throws Exception {
		//TODO: TF2Inventory inventory = tf2Stats.getInventory();
		//assertEquals(1, inventory.getItems().size());
	}
	
	@Test
	public void classStats() throws Exception{
		assertEquals(9, tf2Stats.getClassStats().size());
		
	    TF2Class soldier = tf2Stats.getClassStats().get(0);
	    assertEquals("Soldier", soldier.getName());
	    assertEquals(7, soldier.getMaxBuildingsDestroyed());
	    assertEquals(5, soldier.getMaxCaptures());
	    assertEquals(3981, soldier.getMaxDamage());
	    assertEquals(3, soldier.getMaxDefenses());
	    assertEquals(4, soldier.getMaxDominations());
	    assertEquals(11, soldier.getMaxKillAssists());
	    assertEquals(13, soldier.getMaxKills());
	    assertEquals(2, soldier.getMaxRevenges());
	    assertEquals(18, soldier.getMaxScore());
	    assertEquals(344, soldier.getMaxTimeAlive());
	    assertEquals(218955, soldier.getPlayTime());
	    
	    TF2Class spy = tf2Stats.getClassStats().get(1);
	    assertEquals("Spy", spy.getName());
	    assertEquals(9, spy.getMaxBuildingsDestroyed());
	    assertEquals(2, spy.getMaxCaptures());
	    assertEquals(2825, spy.getMaxDamage());
	    assertEquals(5, spy.getMaxDefenses());
	    assertEquals(2, spy.getMaxDominations());
	    assertEquals(5, spy.getMaxKillAssists());
	    assertEquals(15, spy.getMaxKills());
	    assertEquals(1, spy.getMaxRevenges());
	    assertEquals(31, spy.getMaxScore());
	    assertEquals(438, spy.getMaxTimeAlive());
	    assertEquals(194204, spy.getPlayTime());

	    TF2Class pyro = tf2Stats.getClassStats().get(2);
	    assertEquals("Pyro", pyro.getName());
	    assertEquals(4, pyro.getMaxBuildingsDestroyed());
	    assertEquals(3, pyro.getMaxCaptures());
	    assertEquals(1905, pyro.getMaxDamage());
	    assertEquals(4, pyro.getMaxDefenses());
	    assertEquals(3, pyro.getMaxDominations());
	    assertEquals(7, pyro.getMaxKillAssists());
	    assertEquals(13, pyro.getMaxKills());
	    assertEquals(2, pyro.getMaxRevenges());
	    assertEquals(16, pyro.getMaxScore());
	    assertEquals(611, pyro.getMaxTimeAlive());
	    assertEquals(190730, pyro.getPlayTime());

	    TF2Class engineer = tf2Stats.getClassStats().get(3);
	    assertEquals("Engineer", engineer.getName());
	    assertEquals(3, engineer.getMaxBuildingsDestroyed());
	    assertEquals(5, engineer.getMaxCaptures());
	    assertEquals(3193, engineer.getMaxDamage());
	    assertEquals(4, engineer.getMaxDefenses());
	    assertEquals(5, engineer.getMaxDominations());
	    assertEquals(10, engineer.getMaxKillAssists());
	    assertEquals(15, engineer.getMaxKills());
	    assertEquals(1, engineer.getMaxRevenges());
	    assertEquals(21, engineer.getMaxScore());
	    assertEquals(795, engineer.getMaxTimeAlive());
	    assertEquals(188409, engineer.getPlayTime());

	    TF2Class sniper = tf2Stats.getClassStats().get(4);
	    assertEquals("Sniper", sniper.getName());
	    assertEquals(4, sniper.getMaxBuildingsDestroyed());
	    assertEquals(3, sniper.getMaxCaptures());
	    assertEquals(2799, sniper.getMaxDamage());
	    assertEquals(4, sniper.getMaxDefenses());
	    assertEquals(3, sniper.getMaxDominations());
	    assertEquals(7, sniper.getMaxKillAssists());
	    assertEquals(15, sniper.getMaxKills());
	    assertEquals(1, sniper.getMaxRevenges());
	    assertEquals(22, sniper.getMaxScore());
	    assertEquals(588, sniper.getMaxTimeAlive());
	    assertEquals(122459, sniper.getPlayTime());

	    TF2Class heavy = tf2Stats.getClassStats().get(5);
	    assertEquals("Heavy", heavy.getName());
	    assertEquals(6, heavy.getMaxBuildingsDestroyed());
	    assertEquals(14, heavy.getMaxCaptures());
	    assertEquals(3827, heavy.getMaxDamage());
	    assertEquals(5, heavy.getMaxDefenses());
	    assertEquals(4, heavy.getMaxDominations());
	    assertEquals(9, heavy.getMaxKillAssists());
	    assertEquals(15, heavy.getMaxKills());
	    assertEquals(1, heavy.getMaxRevenges());
	    assertEquals(29, heavy.getMaxScore());
	    assertEquals(478, heavy.getMaxTimeAlive());
	    assertEquals(104456, heavy.getPlayTime());

	    TF2Class demoman = tf2Stats.getClassStats().get(6);
	    assertEquals("Demoman", demoman.getName());
	    assertEquals(5, demoman.getMaxBuildingsDestroyed());
	    assertEquals(7, demoman.getMaxCaptures());
	    assertEquals(3050, demoman.getMaxDamage());
	    assertEquals(5, demoman.getMaxDefenses());
	    assertEquals(2, demoman.getMaxDominations());
	    assertEquals(7, demoman.getMaxKillAssists());
	    assertEquals(13, demoman.getMaxKills());
	    assertEquals(2, demoman.getMaxRevenges());
	    assertEquals(22, demoman.getMaxScore());
	    assertEquals(403, demoman.getMaxTimeAlive());
	    assertEquals(74357, demoman.getPlayTime());

	    TF2Class scout = tf2Stats.getClassStats().get(7);
	    assertEquals("Scout", scout.getName());
	    assertEquals(5, scout.getMaxBuildingsDestroyed());
	    assertEquals(3, scout.getMaxCaptures());
	    assertEquals(1486, scout.getMaxDamage());
	    assertEquals(4, scout.getMaxDefenses());
	    assertEquals(2, scout.getMaxDominations());
	    assertEquals(4, scout.getMaxKillAssists());
	    assertEquals(8, scout.getMaxKills());
	    assertEquals(1, scout.getMaxRevenges());
	    assertEquals(12, scout.getMaxScore());
	    assertEquals(266, scout.getMaxTimeAlive());
	    assertEquals(60785, scout.getPlayTime());

	    TF2Class medic = tf2Stats.getClassStats().get(8);
	    assertEquals("Medic", medic.getName());
	    assertEquals(1, medic.getMaxBuildingsDestroyed());
	    assertEquals(5, medic.getMaxCaptures());
	    assertEquals(1304, medic.getMaxDamage());
	    assertEquals(4, medic.getMaxDefenses());
	    assertEquals(2, medic.getMaxDominations());
	    assertEquals(15, medic.getMaxKillAssists());
	    assertEquals(6, medic.getMaxKills());
	    assertEquals(2, medic.getMaxRevenges());
	    assertEquals(19, medic.getMaxScore());
	    assertEquals(410, medic.getMaxTimeAlive());
	    assertEquals(35802, medic.getPlayTime());
	}
	
	

}
