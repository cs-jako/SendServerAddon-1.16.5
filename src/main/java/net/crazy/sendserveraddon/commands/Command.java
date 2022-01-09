package net.crazy.sendserveraddon.commands;

import net.crazy.sendserveraddon.SendServerAddon;

/**
 * @author CrazySchnetzler1
 */
public abstract class Command {
    protected String name;
    protected String message;

    public Command(String name) {
        this.name = name;
    }

    public void sendToUser(String msg) {
        SendServerAddon.addon.getApi().displayMessageInChat(SendServerAddon.prefix + msg);
    }

    public void addShortcut(String key, String server) {
        SendServerAddon.config.getConfigAsJsonObject().get("shortcuts").getAsJsonObject()
                .addProperty(key, server);
        SendServerAddon.config.save();
    }

    public boolean removeShortcut(String key) {
        if (!SendServerAddon.config.getConfigAsJsonObject().get("shortcuts").getAsJsonObject().has(key))
            return false;

        SendServerAddon.config.getConfigAsJsonObject().get("shortcuts").getAsJsonObject().remove(key);
        SendServerAddon.config.save();
        return true;
    }
}
