package com.gildedrose;

public class AgedBrieUpdater implements ItemUpdater {

    public void updateItem(Item item) {
        item.increaseQualityIfNotMax();

        item.decreaseSellIn();

        if (item.sellIn < 0) {
            item.increaseQualityIfNotMax();
        }
    }
}