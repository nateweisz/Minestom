package net.minestom.server.item.armor;

import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.minestom.server.item.Material;
import net.minestom.server.registry.Registry;
import net.minestom.server.registry.StaticProtocolObject;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public interface TrimPattern extends StaticProtocolObject {
    static @NotNull TrimPattern create(@NotNull NamespaceID namespace,
                                       @NotNull NamespaceID assetID,
                                       @NotNull Material template,
                                       @NotNull Component description,
                                       boolean decal,
                                       @NotNull Registry.Properties custom) {
        return new TrimPatternImpl(
                new Registry.TrimPatternEntry(namespace, assetID, template, description, decal, custom)
        );
    }

    static @NotNull TrimPattern create(@NotNull NamespaceID namespace,
                                       @NotNull NamespaceID assetID,
                                       @NotNull Material template,
                                       @NotNull Component description,
                                       boolean decal) {
        return new TrimPatternImpl(
                new Registry.TrimPatternEntry(namespace, assetID, template, description, decal, null)
        );
    }

    static @Nullable TrimPattern fromId(int id) {
        return TrimPatternImpl.fromId(id);
    }

    static @Nullable TrimPattern fromNamespaceId(@NotNull String id) {
        return TrimPatternImpl.fromNamespaceId(id);
    }

    static Collection<TrimPattern> values() {
        return TrimPatternImpl.values();
    }

    @Contract(pure = true)
    @NotNull Registry.TrimPatternEntry registry();

    @Override
    default @NotNull NamespaceID namespace() {
        return registry().namespace();
    }

    default @NotNull NamespaceID assetID() {
        return registry().assetID();
    }

    default @NotNull Material template() {
        return registry().template();
    }

    default @NotNull Component description() {
        return registry().description();
    }

    default boolean decal() {
        return registry().decal();
    }

    CompoundBinaryTag asNBT();

}
