package com.portingdeadmods.minimal_exchange.capabilities.matter;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.INBTSerializable;
import org.jetbrains.annotations.NotNull;

public class MatterStorageImpl implements MatterStorage, INBTSerializable<CompoundTag> {
    private int matter;
    private int matterCapacity;

    public MatterStorageImpl(int initialCapacity) {
        this.matterCapacity = initialCapacity;
    }

    protected void onChanged() {

    }

    @Override
    public int getMatter() {
        return this.matter;
    }

    @Override
    public int getMatterCapacity() {
        return this.matterCapacity;
    }

    @Override
    public void setMatter(int matter) {
        this.matter = matter;
        onChanged();
    }

    @Override
    public void setMatterCapacity(int matterCapacity) {
        this.matterCapacity = matterCapacity;
    }

    @Override
    public @NotNull CompoundTag serializeNBT(HolderLookup.Provider provider) {
        CompoundTag tag = new CompoundTag();
        tag.putInt("matter", this.matter);
        return tag;
    }

    @Override
    public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
        this.matter = nbt.getInt("matter");
    }

}