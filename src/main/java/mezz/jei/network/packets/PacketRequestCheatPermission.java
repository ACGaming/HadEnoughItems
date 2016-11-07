package mezz.jei.network.packets;

import java.io.IOException;

import mezz.jei.JustEnoughItems;
import mezz.jei.ProxyCommon;
import mezz.jei.network.IPacketId;
import mezz.jei.network.PacketIdServer;
import mezz.jei.util.CommandUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;

public class PacketRequestCheatPermission extends PacketJei {
	@Override
	public IPacketId getPacketId() {
		return PacketIdServer.CHEAT_PERMISSION_REQUEST;
	}

	@Override
	public void writePacketData(PacketBuffer buf) {

	}

	public static class Handler implements IPacketJeiHandler {
		@Override
		public void readPacketData(PacketBuffer buf, EntityPlayer player) throws IOException {
			if (player instanceof EntityPlayerMP) {
				EntityPlayerMP sender = (EntityPlayerMP) player;
				boolean hasPermission = CommandUtil.hasPermission(sender, new ItemStack(Items.NETHER_STAR, 64));
				PacketCheatPermission packetCheatPermission = new PacketCheatPermission(hasPermission);

				ProxyCommon proxy = JustEnoughItems.getProxy();
				proxy.sendPacketToClient(packetCheatPermission, sender);
			}
		}
	}
}
