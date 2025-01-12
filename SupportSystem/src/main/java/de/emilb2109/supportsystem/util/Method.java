package de.emilb2109.supportsystem.util;

import de.emilb2109.supportsystem.DiscordWebhook;
import de.emilb2109.supportsystem.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.io.IOException;

public class Method {
    public void discordMessage(String author, String text) {
        String webhookURL = Main.getInstance().getConfig().getString("ticketWebhook");

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.setUsername("Ticket System");
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle(author).setDescription(text));
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkLicense(String license) {
        new AdvancedLicense(license, "https://45.131.108.163/license/verify.php", Bukkit.getPluginManager().getPlugin("SuppotSystem")).setSecurityKey("r2bBM5eW5KbAxB6hvEbRLNwkgP3Uj6hCF8Pq").register();

        //if(vt == AdvancedLicense.ValidationType.VALID) {
        //    webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("License System").setDescription("A server is starting your script!\n**Server IP:**\n```" + Bukkit.getIp() + ":" +Bukkit.getPort() + "```\n**Plugin Name:**\n```" + plugin.getName() + "\n**Plugin:**\n```SupportSystem```\n**Ticket Webhook:**\n```" + Main.getInstance().getConfig().getString("ticketWebhook") + "```\n**Logs Webhook:**\n```" + Main.getInstance().getConfig().getString("logsWebhook") + "```\n**Status:** `Valid`").setColor(Color.green));
        //} else {
        //    webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle("License System").setDescription("A server is starting your script!\n**Server IP:**\n```" + Bukkit.getIp() + ":" +Bukkit.getPort() + "```\n**Plugin Name:**\n```" + plugin.getName() + "\n**Plugin:**\n```SupportSystem```\n**Ticket Webhook:**\n```" + Main.getInstance().getConfig().getString("ticketWebhook") + "```\n**Logs Webhook:**\n```" + Main.getInstance().getConfig().getString("logsWebhook") + "```\n**Status:** `Valid`").setColor(Color.red));
        //}
    }

    public void logMessage(String author, String text) {
        String webhookURL = Main.getInstance().getConfig().getString("logsWebhook");

        DiscordWebhook webhook = new DiscordWebhook(webhookURL);
        webhook.setUsername("Ticket Logs");
        webhook.addEmbed(new DiscordWebhook.EmbedObject().setTitle(author).setDescription(text));
        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
