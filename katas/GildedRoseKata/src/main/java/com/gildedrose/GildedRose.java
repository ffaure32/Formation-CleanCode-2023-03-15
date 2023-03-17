package com.gildedrose;

import java.util.Arrays;
import java.util.Optional;

class GildedRose {
    Item[] items;

    public enum ItemType {
        AGED_BRIE("Aged Brie", new AgedBrieUpdater()),
        BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert", new BackstagePassUpdater()),
        SULFURAS("Sulfuras, Hand of Ragnaros", new SulfurasUpdater()),
        DEFAULT("", new DefaultUpdater());

        private String itemName;
        private ItemUpdater updater;

        ItemType(String itemName, ItemUpdater updater) {
            this.itemName = itemName;
           this.updater = updater;
        }

        public static ItemUpdater getUpdater(String name) {
            ItemType result = Arrays.stream(ItemType.values()).filter(it -> it.itemName.equals(name)).findFirst().orElse(DEFAULT);
            return result.updater;
        }
    }
    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
        }
    }

    private void updateItemQuality(Item item) {
        ItemUpdater updater = ItemType.getUpdater(item.name);
        updater.updateItem(item);
    }

}