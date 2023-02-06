package me.gameisntover.iranmcreportsspigot.ui;

import com.cryptomorin.xseries.XEnchantment;
import com.cryptomorin.xseries.XMaterial;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import me.gameisntover.iranmcreportsspigot.util.EncryptionUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static me.gameisntover.iranmcreportsspigot.util.StringUtil.color;

public class ButtonBuilder {
    private XMaterial material = XMaterial.GRAY_STAINED_GLASS_PANE;
    private String name = " ";
    public List<String> lores = new ArrayList<>();
    private XEnchantment[] enchantments = new XEnchantment[]{};
    private ItemFlag[] itemFlags = new ItemFlag[]{};
    private Consumer<InventoryClickEvent> consumer = inventoryClickEvent -> {
    };
    private String texture;

    public ButtonBuilder mat(XMaterial material) {
        this.material = material;
        return this;
    }

    public ButtonBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ButtonBuilder lores(String... lores) {
        this.lores = color(Arrays.stream(lores).collect(Collectors.toList()));
        return this;
    }

    public ButtonBuilder enchants(XEnchantment... enchantment) {
        this.enchantments = enchantment;
        return this;
    }

    public ButtonBuilder itemFlags(ItemFlag... flags) {
        this.itemFlags = flags;
        return this;
    }

    public ButtonBuilder clickEvent(Consumer<InventoryClickEvent> e) {
        this.consumer = e;
        return this;
    }

    public InventoryButton build() {
        InventoryButton button = new InventoryButton(material, name) {
            @Override
            public void onClick(InventoryClickEvent e) {
                consumer.accept(e);
            }
        };
        button.addItemFlags(itemFlags);
        for (XEnchantment enchantment : enchantments) button.addEnchantment(enchantment.getEnchant(), 1);
        if (button.getItemMeta() instanceof SkullMeta) {

            SkullMeta meta = (SkullMeta) button.getItemMeta();
            GameProfile profile = new GameProfile(UUID.randomUUID(), null);
            profile.getProperties().put("textures", new Property("textures", texture));
            Field profileField;

            try {
                profileField = meta.getClass().getDeclaredField("profile");
                profileField.setAccessible(true);
                profileField.set(meta, profile);
            } catch (SecurityException | IllegalAccessException | NoSuchFieldException var7) {
                var7.printStackTrace();
            }
        }
        return button;
    }


    public ButtonBuilder skullSkin(String s) {
        if (s != null && !s.isEmpty())
            if (s.startsWith("https://") || s.startsWith("http://"))
                texture = EncryptionUtils.getBase64EncodedString(String.format("{textures:{SKIN:{url:\"%s\"}}}", s));
        return this;
    }

}
