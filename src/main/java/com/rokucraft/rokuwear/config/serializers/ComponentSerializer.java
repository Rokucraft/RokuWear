package com.rokucraft.rokuwear.config.serializers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.serialize.TypeSerializer;

import java.lang.reflect.Type;

public class ComponentSerializer implements TypeSerializer<Component> {
    public static final ComponentSerializer INSTANCE = new ComponentSerializer();

    @Override
    public Component deserialize(Type type, ConfigurationNode node) {
        // Replace this when 1.16 compatibility is dropped:
        // return MiniMessage.get().deserializeOrNull(node.getString());
        return MiniMessage.get().deserializeOr(node.getString(), null);
    }

    @Override
    public void serialize(Type type, @Nullable Component obj, ConfigurationNode node) throws SerializationException {
        node.set(MiniMessage.get().serializeOrNull(obj));
    }
}
