package de.relulu.MobAge.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import de.relulu.MobAge.MobAgeManager;
import de.relulu.MobAge.util.ConfigManager;
import de.relulu.MobAge.util.MessageHandler;
import org.bukkit.util.StringUtil;

/**
 * Diese Klasse handhabt die Befehle hinter /daily
 *
 * @author ReLuLu
 *
 */
public class MobAgeCommand implements CommandExecutor, TabCompleter {

    private MessageHandler mh;
    private ConfigManager confman;
    private PluginDescriptionFile pdf;

    private final List<String> subcommands = new ArrayList<>(Arrays.asList(
            "start",
            "check",
            "end",
            "nodamage",
            "nohunger",
            "antigrief",
            "checkbuttons",
            "checkplates",
            "checkblocks",
            "antigriefobjects",
            "version"
    ));

    public MobAgeCommand(MobAgeManager dman, PluginDescriptionFile pdf) {
        this.mh = dman.getMessageHandler();
        this.confman = dman.getConfigManager();
        this.pdf = pdf;
    }

    /**
     * Handelt den /daily-Befehl sowie Unterbefehle ab
     * @param sender wer hat den Befehl abgesetzt
     * @param command Befehl als Objekt
     * @param comname Name des Befehls (/daily)
     * @param comparams zugehörige Parameter (/daily nodamage status)
     * @return true
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String comname, String[] comparams) {

        if(sender instanceof Player) {

            Player p = (Player)sender;

            if(p.isOp()) {

                // wenn der Befehl ohne Parameter (=Unterbefehl) daherkommt
                if(comparams.length < 1) {
                    // dann gib die Hilfe aus
                    helpMessage(p);
                }

                // wenn der Befehl mit mind. 1 Parameter (=Unterbefehl + Parameter) daherkommt
                else {

                    // Unterbefehl auslesen und zwischenspeichern
                    String subcommand = comparams[0];

                    // den Unterbefehl switchen
                    switch (subcommand) {

                        case "x":
                            break;

                        case "y":
                            break;

                        case "z":
                            break;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Handhabt die Tab-Vervollständigung des daily-Befehls, um mögliche Unterbefehle sowie Parameter vorschlagen zu lassen
     * @param sender wer hat den Befehl abgesetzt
     * @param command Befehl als Objekt
     * @param comname Name des Befehls (/daily)
     * @param comparams zugehörige Parameter (/daily nodamage status)
     * @return tabsuggestions Liste mit möglichen Befehls-/Parametervorschlägen
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String comname, String[] comparams) {

        List<String> tabsuggestions = new ArrayList<>();

        // /daily ohne irgendwas, alle Möglichkeiten offen
        if(comparams.length == 0) {
            return tabsuggestions;
        }

        // /daily mit mindestens angefangenem Parameter
        else if(comparams.length == 1) {
            StringUtil.copyPartialMatches(comparams[0], subcommands, tabsuggestions);
            Collections.sort(tabsuggestions);
        }

        // /daily xy az mit mindestens angefangenem zweitem Parameter
        else if(comparams.length == 2) {

            // unterscheiden, welcher subcommand es nun ist

            // zunächst start, check und end, da diese die gleiche Vervollständigung geben (online-players)
            if(comparams[0].equalsIgnoreCase("start")
                    || comparams[0].equalsIgnoreCase("check")
                    || comparams[0].equalsIgnoreCase("end")) {

                List<String> players = new ArrayList<>();
                Bukkit.getOnlinePlayers().forEach(p -> players.add(p.getName()));
                StringUtil.copyPartialMatches(comparams[1], players, tabsuggestions);

            }

            // dann alle mit booleschen Parametern (nohunger, nodamage, antigrief)
            else if(comparams[0].equalsIgnoreCase("nohunger")
                    || comparams[0].equalsIgnoreCase("nodamage")
                    || comparams[0].equalsIgnoreCase("antigrief")) {

                List<String> getset = new ArrayList<>(Arrays.asList("status", "true", "false"));
                StringUtil.copyPartialMatches(comparams[1], getset, tabsuggestions);

            }

        }

        return tabsuggestions;
    }

    /**
     * Gibt eine Auflistung administrativer Befehle des Plugins aus.
     * @param cs der, der den Befehl ausgeführt hat
     */
    private void helpMessage(CommandSender cs) {
        mh.tell(cs, "Administrative Daily-Befehle");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "start " + "<" + mh.getSecondaryFormat() + "Spielername" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "check " + "<" + mh.getSecondaryFormat() + "Spielername" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "end <" + mh.getSecondaryFormat() + "Spielername" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "nodamage <" + mh.getSecondaryFormat() + "true" + mh.getPrimaryColor() + "|" + mh.getSecondaryFormat() + "false" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "nohunger <" + mh.getSecondaryFormat() + "true" + mh.getPrimaryColor() + "|" + mh.getSecondaryFormat() + "false" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "antigrief <" + mh.getSecondaryFormat() + "true" + mh.getPrimaryColor() + "|" + mh.getSecondaryFormat() + "false" + mh.getPrimaryColor() + ">");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "checkbuttons");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "checkplates");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "antigriefobjects");
        mh.tell(cs, mh.getPrimaryColor() + "/" + mh.getSecondaryFormat() + "daily " + mh.getPrimaryColor() + "version");
    }

}
