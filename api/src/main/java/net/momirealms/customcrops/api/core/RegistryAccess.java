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

package net.momirealms.customcrops.api.core;

import net.momirealms.customcrops.common.util.Key;
import net.momirealms.customcrops.api.core.block.CustomCropsBlock;
import net.momirealms.customcrops.api.core.item.CustomCropsItem;
import net.momirealms.customcrops.api.core.item.FertilizerType;

public interface RegistryAccess {

    void registerBlockMechanic(CustomCropsBlock block);

    void registerItemMechanic(CustomCropsItem item);

    void registerFertilizerType(FertilizerType type);

    Registry<Key, CustomCropsBlock> getBlockRegistry();

    Registry<Key, CustomCropsItem> getItemRegistry();

    Registry<String, FertilizerType> getFertilizerTypeRegistry();
}