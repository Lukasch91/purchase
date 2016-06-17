package com.lukas.cashRegister;

import com.lukas.model.item.Item;

interface Scanner {
    Item scan(Long itemCode);
}
