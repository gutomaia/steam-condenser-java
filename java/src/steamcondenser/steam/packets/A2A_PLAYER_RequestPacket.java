package steamcondenser.steam.packets;

import steamcondenser.Helper;

/**
 * @author Sebastian Staudt
 * @version $Id$
 */
public class A2A_PLAYER_RequestPacket extends SteamPacket
{
	public A2A_PLAYER_RequestPacket(int challengeNumber)
	{
		super(SteamPacket.A2A_PLAYER_REQUEST_HEADER, Helper.byteArrayFromInteger(Integer.reverseBytes(challengeNumber)));
	}
}
