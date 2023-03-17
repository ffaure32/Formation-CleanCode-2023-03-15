package com.gildedrose;

public class DefaultUpdater implements ItemUpdater {

    public void updateItem(Item item) {
        item.decreaseQualityIfNotMin();

        item.decreaseSellIn();

        if (item.sellIn < 0) {
            item.decreaseQualityIfNotMin();
        }
    }
}