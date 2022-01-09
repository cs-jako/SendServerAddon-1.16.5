package net.crazy.sendserveraddon.commands;

import com.google.gson.JsonElement;
import net.crazy.sendserveraddon.SendServerAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

import java.util.Map;

/**
 * @author CrazySchnetzler1
 */
public class SendCommand extends Command {

    public SendCommand(String name) {
        super(name);
    }

    @Subscribe
    public void onMessageSend(MessageSendEvent event) {
        this.message = event.getMessage().toLowerCase();

        if (!message.startsWith("-" + name))
            return;
        event.setCancelled(true);

        String target = null;
        try {
            target = message.split(" ")[1];
        } catch (IndexOutOfBoundsException exception) {
            sendToUser("§c-send <server>");
            return;
        }

        for (Map.Entry<String, JsonElement> entry : SendServerAddon.config.getConfigAsJsonObject().get("shortcuts")
                .getAsJsonObject().entrySet()) {
            if (target.equals(entry.getKey())) {
                target = entry.getValue().getAsString();
                break;
            }
        }

        if (target == null) {
            sendToUser("§cThis shortcut doesn't exist.");
            return;
        }

        SendServerAddon.addon.getApi().connectToServer(target);
    }
}
