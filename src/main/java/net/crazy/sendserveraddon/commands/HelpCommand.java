package net.crazy.sendserveraddon.commands;


import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

/**
 * @author CrazySchnetzler1
 */
public class HelpCommand extends Command {

    public HelpCommand(String name) {
        super(name);
    }

    @Subscribe
    public void onMessageSend(MessageSendEvent event) {
        this.message = event.getMessage().toLowerCase();

        if (!message.startsWith("-" + name))
            return;
        event.setCancelled(true);

        sendToUser("§a--------- §6Help §a---------");
        sendToUser("§6-send <shortcut> §7» §aConnect to a server using a shortcut.");
        sendToUser("§6-shortcut add <shortcut> <serverIp> §7» §aadd a new shortcut for a server.");
        sendToUser("§6-shortcut remove <shortcut> §7» §aRemove a shortcut from your list.");
        sendToUser("§6-shortcut list §7» §aLists all of your shortcuts.");
    }
}
