/*
 *  Copyright (C) <2024> <XiaoMoMi>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.momirealms.customcrops.api.core.block;

import net.momirealms.customcrops.api.action.Action;
import net.momirealms.customcrops.api.core.ExistenceForm;
import net.momirealms.customcrops.api.core.item.FertilizerType;
import net.momirealms.customcrops.api.misc.water.WateringMethod;
import net.momirealms.customcrops.api.core.world.CustomCropsBlockState;
import net.momirealms.customcrops.api.misc.water.WaterBar;
import net.momirealms.customcrops.api.requirement.Requirement;
import net.momirealms.customcrops.common.util.Pair;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class PotConfigImpl implements PotConfig {

    private final String id;
    private final Pair<String, String> basicAppearance;
    private final HashMap<FertilizerType, Pair<String, String>> potAppearanceMap;
    private final Set<String> blocks = new HashSet<>();
    private final Set<String> wetBlocks = new HashSet<>();
    private final int storage;
    private final boolean isRainDropAccepted;
    private final boolean isNearbyWaterAccepted;
    private final WateringMethod[] wateringMethods;
    private final WaterBar waterBar;
    private final int maxFertilizers;
    private final Requirement<Player>[] placeRequirements;
    private final Requirement<Player>[] breakRequirements;
    private final Requirement<Player>[] useRequirements;
    private final Action<CustomCropsBlockState>[] tickActions;
    private final Action<Player>[] reachLimitActions;
    private final Action<Player>[] interactActions;
    private final Action<Player>[] placeActions;
    private final Action<Player>[] breakActions;
    private final Action<Player>[] addWaterActions;
    private final Action<Player>[] fullWaterActions;

    public PotConfigImpl(
            String id,
            Pair<String, String> basicAppearance,
            HashMap<FertilizerType, Pair<String, String>> potAppearanceMap,
            int storage,
            boolean isRainDropAccepted,
            boolean isNearbyWaterAccepted,
            WateringMethod[] wateringMethods,
            WaterBar waterBar,
            int maxFertilizers,
            Requirement<Player>[] placeRequirements,
            Requirement<Player>[] breakRequirements,
            Requirement<Player>[] useRequirements,
            Action<CustomCropsBlockState>[] tickActions,
            Action<Player>[] reachLimitActions,
            Action<Player>[] interactActions,
            Action<Player>[] placeActions,
            Action<Player>[] breakActions,
            Action<Player>[] addWaterActions,
            Action<Player>[] fullWaterActions
    ) {
        this.id = id;
        this.basicAppearance = basicAppearance;
        this.potAppearanceMap = potAppearanceMap;
        this.storage = storage;
        this.isRainDropAccepted = isRainDropAccepted;
        this.isNearbyWaterAccepted = isNearbyWaterAccepted;
        this.wateringMethods = wateringMethods;
        this.waterBar = waterBar;
        this.maxFertilizers = maxFertilizers;
        this.placeRequirements = placeRequirements;
        this.breakRequirements = breakRequirements;
        this.useRequirements = useRequirements;
        this.tickActions = tickActions;
        this.reachLimitActions = reachLimitActions;
        this.interactActions = interactActions;
        this.placeActions = placeActions;
        this.breakActions = breakActions;
        this.addWaterActions = addWaterActions;
        this.fullWaterActions = fullWaterActions;
        this.blocks.add(basicAppearance.left());
        this.blocks.add(basicAppearance.right());
        this.wetBlocks.add(basicAppearance.right());
        for (Pair<String, String> pair : potAppearanceMap.values()) {
            this.blocks.add(pair.left());
            this.blocks.add(pair.right());
            this.wetBlocks.add(pair.right());
        }
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public int storage() {
        return storage;
    }

    @Override
    public boolean isRainDropAccepted() {
        return isRainDropAccepted;
    }

    @Override
    public boolean isNearbyWaterAccepted() {
        return isNearbyWaterAccepted;
    }

    @Override
    public WateringMethod[] wateringMethods() {
        return wateringMethods;
    }

    @Override
    public Set<String> blocks() {
        return blocks;
    }

    @Override
    public boolean isWet(String blockID) {
        return wetBlocks.contains(blockID);
    }

    @Override
    public WaterBar waterBar() {
        return waterBar;
    }

    @Override
    public int maxFertilizers() {
        return maxFertilizers;
    }

    @Override
    public String getPotAppearance(boolean watered, FertilizerType type) {
        if (type != null) {
            Pair<String, String> appearance = potAppearanceMap.get(type);
            if (appearance != null) {
                return watered ? appearance.right() : appearance.left();
            }
        }
        return watered ? basicAppearance.right() : basicAppearance.left();
    }

    @Override
    public Requirement<Player>[] placeRequirements() {
        return placeRequirements;
    }

    @Override
    public Requirement<Player>[] breakRequirements() {
        return breakRequirements;
    }

    @Override
    public Requirement<Player>[] useRequirements() {
        return useRequirements;
    }

    @Override
    public Action<CustomCropsBlockState>[] tickActions() {
        return tickActions;
    }

    @Override
    public Action<Player>[] reachLimitActions() {
        return reachLimitActions;
    }

    @Override
    public Action<Player>[] interactActions() {
        return interactActions;
    }

    @Override
    public Action<Player>[] placeActions() {
        return placeActions;
    }

    @Override
    public Action<Player>[] breakActions() {
        return breakActions;
    }

    @Override
    public Action<Player>[] addWaterActions() {
        return addWaterActions;
    }

    @Override
    public Action<Player>[] fullWaterActions() {
        return fullWaterActions;
    }

    public static class BuilderImpl implements Builder {

        private String id;
        private ExistenceForm existenceForm;
        private Pair<String, String> basicAppearance;
        private HashMap<FertilizerType, Pair<String, String>> potAppearanceMap;
        private int storage;
        private boolean isRainDropAccepted;
        private boolean isNearbyWaterAccepted;
        private WateringMethod[] wateringMethods;
        private WaterBar waterBar;
        private int maxFertilizers;
        private Requirement<Player>[] placeRequirements;
        private Requirement<Player>[] breakRequirements;
        private Requirement<Player>[] useRequirements;
        private Action<CustomCropsBlockState>[] tickActions;
        private Action<Player>[] reachLimitActions;
        private Action<Player>[] interactActions;
        private Action<Player>[] placeActions;
        private Action<Player>[] breakActions;
        private Action<Player>[] addWaterActions;
        private Action<Player>[] fullWaterActions;

        @Override
        public PotConfig build() {
            return new PotConfigImpl(id, basicAppearance, potAppearanceMap, storage, isRainDropAccepted, isNearbyWaterAccepted, wateringMethods, waterBar, maxFertilizers, placeRequirements, breakRequirements, useRequirements, tickActions, reachLimitActions, interactActions, placeActions, breakActions, addWaterActions, fullWaterActions);
        }

        @Override
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        @Override
        public Builder isRainDropAccepted(boolean isRainDropAccepted) {
            this.isRainDropAccepted = isRainDropAccepted;
            return this;
        }

        @Override
        public Builder isNearbyWaterAccepted(boolean isNearbyWaterAccepted) {
            this.isNearbyWaterAccepted = isNearbyWaterAccepted;
            return this;
        }

        @Override
        public Builder wateringMethods(WateringMethod[] wateringMethods) {
            this.wateringMethods = wateringMethods;
            return this;
        }

        @Override
        public Builder waterBar(WaterBar waterBar) {
            this.waterBar = waterBar;
            return this;
        }

        @Override
        public Builder maxFertilizers(int maxFertilizers) {
            this.maxFertilizers = maxFertilizers;
            return this;
        }

        @Override
        public Builder placeRequirements(Requirement<Player>[] requirements) {
            this.placeRequirements = requirements;
            return this;
        }

        @Override
        public Builder breakRequirements(Requirement<Player>[] requirements) {
            this.breakRequirements = requirements;
            return this;
        }

        @Override
        public Builder useRequirements(Requirement<Player>[] requirements) {
            this.useRequirements = requirements;
            return this;
        }

        @Override
        public Builder tickActions(Action<CustomCropsBlockState>[] tickActions) {
            this.tickActions = tickActions;
            return this;
        }

        @Override
        public Builder reachLimitActions(Action<Player>[] reachLimitActions) {
            this.reachLimitActions = reachLimitActions;
            return this;
        }

        @Override
        public Builder interactActions(Action<Player>[] interactActions) {
            this.interactActions = interactActions;
            return this;
        }

        @Override
        public Builder placeActions(Action<Player>[] placeActions) {
            this.placeActions = placeActions;
            return this;
        }

        @Override
        public Builder breakActions(Action<Player>[] breakActions) {
            this.breakActions = breakActions;
            return this;
        }

        @Override
        public Builder addWaterActions(Action<Player>[] addWaterActions) {
            this.addWaterActions = addWaterActions;
            return this;
        }

        @Override
        public Builder fullWaterActions(Action<Player>[] fullWaterActions) {
            this.fullWaterActions = fullWaterActions;
            return this;
        }

        @Override
        public Builder basicAppearance(Pair<String, String> basicAppearance) {
            this.basicAppearance = basicAppearance;
            return this;
        }

        @Override
        public Builder potAppearanceMap(HashMap<FertilizerType, Pair<String, String>> potAppearanceMap) {
            this.potAppearanceMap = potAppearanceMap;
            return this;
        }
    }
}