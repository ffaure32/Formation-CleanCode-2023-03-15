package com.gildedrose;

public class BackstagePassUpdater implements ItemUpdater {

    @Override
    public void updateItem(Item item) {
        item.increaseQualityIfNotMax();

        if (item.sellIn <= 10) {
            item.increaseQualityIfNotMax();
        }

        if (item.sellIn <= 5) {
            item.increaseQualityIfNotMax();
        }

        item.decreaseSellIn();

        if (item.sellIn < 0) {
            item.resetQuality();
        }
    }
}