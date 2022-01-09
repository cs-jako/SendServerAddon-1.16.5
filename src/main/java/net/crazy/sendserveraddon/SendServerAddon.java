package net.crazy.sendserveraddon;

import java.util.List;

import net.crazy.sendserveraddon.commands.HelpCommand;
import net.crazy.sendserveraddon.commands.SendCommand;
import net.crazy.sendserveraddon.commands.ShortcutCommand;
import net.labymod.api.LabyModAddon;
import net.labymod.api.event.EventService;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.ModColor;

public class SendServerAddon extends LabyModAddon {
  public static SendServerAddon addon;
  public static String prefix = ModColor.cl('7') + "[" + ModColor.cl('6') + "SendServerAddon" +
          ModColor.cl('7') + "] ";
  public static Config config;

  @Override
  public void onEnable() {
    addon = this;
    config = new Config("SendServerAddon");

    EventService eventService = getApi().getEventService();
    eventService.registerListener(new HelpCommand("help"));
    eventService.registerListener(new SendCommand("send"));
    eventService.registerListener(new ShortcutCommand("shortcut"));
  }

  @Override
  public void loadConfig() {

  }

  @Override
  protected void fillSettings(List<SettingsElement> list) {
  }
}
