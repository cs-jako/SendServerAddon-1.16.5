package net.crazy.sendserveraddon.commands;

import net.crazy.sendserveraddon.SendServerAddon;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.events.client.chat.MessageSendEvent;

/**
 * @author CrazySchnetzler1
 */
public class ShortcutCommand extends Command {

    public ShortcutCommand(String name) {
        super(name);
    }

    @Subscribe
    public void onMessageSend(MessageSendEvent event) {
        this.message = event.getMessage().toLowerCase();

        if (!message.startsWith("-" + name))
            return;
        event.setCancelled(true);

        String[] splits = message.split(" ");
        String[] args = new String[splits.length - 1];

        if (splits.length - 1 <= 0) {
            sendToUser("§c-shortcut <add | remove | list>");
            return;
        }
        System.arraycopy(splits, 1, args, 0, splits.length -1);

        String arg = args[0].toLowerCase();
        switch (arg) {
            case "list":
                displayList();
                break;
            case "add":
                if (args.length < 3) {
                    sendToUser("§c-shortcut add <shortcut> <serverIp>");
                    return;
                }

                addShortcut(args[1], args[2]);
                sendToUser("§aShortcut has been added.");
                break;
            case "remove":
                if (args.length < 2) {
                    sendToUser("§c-shortcut remove <shortcut>");
                    return;
                }
                removeShortcut(args[1]);
                sendToUser("§aShortcut has been removed.");
                break;
            default:
                sendToUser("§c-shortcut <add | remove | list>");
                break;
        }
    }

    private void displayList() {
        sendToUser("§aShortcuts");
        SendServerAddon.config.getConfigAsJsonObject().get("shortcuts").getAsJsonObject()
                .entrySet().forEach(entry ->
                        sendToUser("§e" + entry.getKey() + " §7-> §2" + entry.getValue().getAsString()));
    }
}
